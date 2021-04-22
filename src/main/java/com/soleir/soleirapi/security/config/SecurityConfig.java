package com.soleir.soleirapi.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.soleir.soleirapi.service.SoleirUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//All Security files from: https://dimitr.im/graphql-spring-security
@Configuration
public class SecurityConfig {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public Algorithm jwtAlgorithm(SecurityProperties properties){
        logger.info("Entering jwtAlgorithm");
        return Algorithm.HMAC256(properties.getTokenSecret());
    }

    //verifies if token has correct algorithm and if issuer is correct
    @Bean
    public JWTVerifier verifier(SecurityProperties properties, Algorithm algorithm){
        logger.info("Entering verifier");
        return JWT
                .require(algorithm)
                .withIssuer(properties.getTokenIssuer())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.info("Entering passwordEncoder");
        return new BCryptPasswordEncoder(10);
    }
    //from https://dimitr.im/graphql-spring-security, took it out and made customer authentication provider class
    @Bean
    //configured for user credentials stored in database
    public AuthenticationProvider authenticationProvider(SoleirUserService soleirUserService, PasswordEncoder passwordEncoder){
        logger.info("Entering authenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(soleirUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}