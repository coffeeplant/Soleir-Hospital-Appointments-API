//package com.soleir.soleirapi.security.jwt;
//
//import com.soleir.soleirapi.model.SoleirDB.SoleirUser;
//import com.soleir.soleirapi.resolver.SoleirUserMutableResolver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
////    @Override
////    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
////        return null;
////    }
////
////    @Override
////    public boolean supports(Class<?> aClass) {
////        return false;
////    }
//
//    private static Logger logger = LoggerFactory.getLogger(SoleirUserMutableResolver.class);
//    //customer authentication code as authentication required 2 fields matching
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        Object credentials = authentication.getCredentials();
//        logger.info("class:");
//        System.out.println(credentials.getClass());
//        logger.info(String.valueOf(credentials.getClass()));
//        if (!(credentials instanceof String)){
//            return null;
//        }
//        String password = credentials.toString();
//        logger.info("Password");
//        logger.info(password);
//
//       // Optional<SoleirUser> soleirUserOptional = soleiruser.stream()
//
//        try {
//            if ("externaluser".equals(email) && "pass".equals(password)) {
//                logger.info("Entering try");
//                return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
//            }
//        }catch (AuthenticationException ex){
//            System.out.println("Problem in Authentication Exception");
//        }
//        logger.info("Skipped try");
//        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
