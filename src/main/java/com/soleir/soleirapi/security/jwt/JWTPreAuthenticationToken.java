package com.soleir.soleirapi.security.jwt;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Getter
public class JWTPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {

    private static Logger logger = LoggerFactory.getLogger(JWTPreAuthenticationToken.class);

    @Builder
    public JWTPreAuthenticationToken(JWTSoleirUserDetails principal, WebAuthenticationDetails details){
        super(principal, null, principal.getAuthorities());
        super.setDetails(details);
    }

    @Override
    public Object getCredentials(){
        logger.info("Entering JWTPre.getCredentials");
        return null;
    }
}
