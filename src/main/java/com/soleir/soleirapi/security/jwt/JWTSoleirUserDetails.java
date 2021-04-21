package com.soleir.soleirapi.security.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class JWTSoleirUserDetails implements UserDetails {

    private final String email;
    private final Integer userID;
    private final String soleirID;
    private final List<SimpleGrantedAuthority> authorities;
    private final String token;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return soleirID;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
