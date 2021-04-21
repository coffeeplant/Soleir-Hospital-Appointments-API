package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.model.SoleirDB.AuthData;
import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import com.soleir.soleirapi.security.jwt.CustomAuthenticationProvider;
import com.soleir.soleirapi.service.SoleirUserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoleirUserMutableResolver implements GraphQLMutationResolver {

    private static Logger logger = LoggerFactory.getLogger(SoleirUserMutableResolver.class);

    private final SoleirUserService service;
    private final CustomAuthenticationProvider authenticationProvider;

    //this annotation means the method can be used when user is not logged in yet
    @PreAuthorize("isAnonymous()")
    public SoleirUser signinUser(AuthData authData) throws Exception {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(authData.getEmail(), authData.getSoleirID());
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            return service.getCurrentUser();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid user credentials");
        }
    }





}
