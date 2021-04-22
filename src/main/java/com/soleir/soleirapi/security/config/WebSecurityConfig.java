package com.soleir.soleirapi.security.config;

//import com.soleir.soleirapi.security.jwt.CustomAuthenticationProvider;
import com.soleir.soleirapi.security.jwt.JWTFilter;
import com.soleir.soleirapi.security.config.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestAttributeAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableConfigurationProperties(SecurityProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final AuthenticationProvider authenticationProvider;
    private final JWTFilter jwtFilter;

    //passes authentication provider bean from security config file
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        logger.info("Entering websecurity.configure.AuthenticationManager");
        auth.authenticationProvider(authenticationProvider);
    }
    //handles the http request from the client
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        logger.info("Entering websecurity.configure.HttpSecurity");
        http
                .csrf().disable()//.cors().and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtFilter, RequestAttributeAuthenticationFilter.class);
    }
}
