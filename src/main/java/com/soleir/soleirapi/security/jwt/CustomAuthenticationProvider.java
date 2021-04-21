package com.soleir.soleirapi.security.jwt;

import com.soleir.soleirapi.resolver.SoleirUserMutableResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger = LoggerFactory.getLogger(SoleirUserMutableResolver.class);
    //customer authentication code as authentication required 2 fields matching
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String soleirID = authentication.getCredentials().toString();
        try {
            if ("externaluser".equals(email) && "pass".equals(soleirID)) {
                return new UsernamePasswordAuthenticationToken(email, soleirID, new ArrayList<>());
            }
        }catch (AuthenticationException ex){
            System.out.println("Problem in Authentication Exception");
        }
        return new UsernamePasswordAuthenticationToken(email, soleirID, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
