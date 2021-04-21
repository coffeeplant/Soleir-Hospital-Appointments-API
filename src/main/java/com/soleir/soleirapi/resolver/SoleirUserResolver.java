package com.soleir.soleirapi.resolver;

import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
import com.soleir.soleirapi.service.SoleirUserService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoleirUserResolver implements GraphQLResolver <SoleirUser> {

    private final SoleirUserService service;

    //This resolver and method add the token field to SoleirUser in the graphql query
    @PreAuthorize("isAuthenticated()")
    public String getToken(SoleirUser soleirUser){
        return service.getToken(soleirUser);
    }
}

