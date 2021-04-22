package com.soleir.soleirapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import com.soleir.soleirapi.repository.SoleirDB.SoleirUserRepository;
import com.soleir.soleirapi.exception.BadTokenException;
import com.soleir.soleirapi.security.jwt.JWTSoleirUserDetails;
import com.soleir.soleirapi.security.config.SecurityProperties;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static java.util.function.Predicate.not;

//All Security files from: https://dimitr.im/graphql-spring-security
@Service
@RequiredArgsConstructor
public class SoleirUserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(SoleirUserService.class);

    private final SoleirUserRepository repository;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties properties;

    @Override
    public JWTSoleirUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Entering service.loadUserByName");
        return repository
                .findByEmail(email)
                .map(user -> getUserDetails(user, getToken(user)))
                .orElseThrow(() -> new UsernameNotFoundException("Username or password didn't match"));
    }

    @Transactional
    public JWTSoleirUserDetails loadUserByToken(String token){
        logger.info("Entering service.loadUserByToken");
        return getDecodedToken(token)
                //fetches user info and sets up UserDetails object
                .map(DecodedJWT::getSubject)
                .flatMap(repository::findByEmail)
                .map(user -> getUserDetails(user, token))
                .orElseThrow(() -> new BadTokenException("Invalid token"));

    }

    public SoleirUser getCurrentUser() {
        logger.info("Entering service.getCurrentUser");
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(repository::findByEmail)
                .orElse(null);
    }

    @Transactional
    public String getToken(SoleirUser soleirUser){
        logger.info("Entering service.getToken");
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(properties.getTokenExpiration());
        return JWT
                .create()
                .withIssuer(properties.getTokenIssuer())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .withSubject(soleirUser.getEmail())
                .sign(algorithm);
    }


    public boolean isAuthenticated() {
        logger.info("service.isAuthenicated");
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(not(this::isAnonymous))
                .isPresent();
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }

    private JWTSoleirUserDetails getUserDetails(SoleirUser soleirUser, String token) {
        logger.info("Entering service.getUserDetails");
        return JWTSoleirUserDetails
                .builder()
                .email(soleirUser.getEmail())
                //this encodes the soleirID on the db with the same password encoder , this is not
                //safe, bcrypt needs to be used to hash the passwords in the database
                .soleirID(passwordEncoder.encode(soleirUser.getSoleirID()))
                .token(token)
                .build();
    }

    //verifies the token using the JWTVerifier in the security config class
    private Optional<DecodedJWT> getDecodedToken(String token) {
        logger.info("Entering service.getDecodedToken");
        try{
            return Optional.of(verifier.verify(token));
        }catch(JWTVerificationException e){
            return Optional.empty();
        }
    }




}

