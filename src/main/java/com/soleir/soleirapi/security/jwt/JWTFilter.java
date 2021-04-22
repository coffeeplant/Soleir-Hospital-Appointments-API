package com.soleir.soleirapi.security.jwt;

import com.soleir.soleirapi.service.SoleirUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");
    private final SoleirUserService service;

    //parses token from the header
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        logger.info("Entering JWTFiler.doFilterInternal");
        getToken(request)
                .map(service::loadUserByToken)
                //2.wrapping in pre authenticated authentication token class using implementation class
                .map(userDetails -> JWTPreAuthenticationToken
                        .builder()
                        .principal(userDetails)
                        //1.setting up proper authentication
                        .details(new WebAuthenticationDetailsSource().buildDetails(request))
                        .build())
                //3. storing in a security context
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
        filterChain.doFilter(request, response);
    }//end of doFilterInternal

    private Optional<String> getToken(HttpServletRequest request) {
        logger.info("Entering JWTFiler.getToken");
        return Optional
                .ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(not(String::isEmpty))
                .map(BEARER_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1));
    }
}

