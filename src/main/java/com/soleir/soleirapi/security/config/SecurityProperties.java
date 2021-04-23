package com.soleir.soleirapi.security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@ConstructorBinding
@ConfigurationProperties(prefix = "soleirapi.security")
@Getter
@RequiredArgsConstructor
public class SecurityProperties {

    //these could be asked at start-up for additional security, don't need to be defined here
    private final String tokenSecret = "Subjecteye63lead2580mainnine4558";

    private final String tokenIssuer = "soleir-graphql2";

    private final Duration tokenExpiration = Duration.ofHours(4);

}
