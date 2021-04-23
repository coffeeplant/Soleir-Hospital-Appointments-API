package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.exception.SignInDetailsNotFoundException;
import com.soleir.soleirapi.model.SoleirDB.AuthData;
import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
//import com.soleir.soleirapi.security.jwt.CustomAuthenticationProvider;
import com.soleir.soleirapi.service.SoleirUserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Component
@RequiredArgsConstructor
public class SoleirUserMutableResolver implements GraphQLMutationResolver {

    private static Logger logger = LoggerFactory.getLogger(SoleirUserMutableResolver.class);

    private final SoleirUserService service;
    private final AuthenticationProvider authenticationProvider;

    //this annotation means the method can be used when user is not logged in yet
    @PreAuthorize("isAnonymous()")
    public SoleirUser signinUser(AuthData authData) throws Exception {
        //logger.info("Entering mutableResolver.signInUser");
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(authData.getEmail(), authData.getSoleirID());
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            return service.getCurrentUser();
        } catch (AuthenticationException e) {
            throw new SignInDetailsNotFoundException(400, "Log in details not found, please contact your healthcare provider");
        }
    }





}
