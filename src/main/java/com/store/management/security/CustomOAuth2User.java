package com.store.management.security;

import com.store.management.dtoRecord.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(UserDTO userDTO, Collection<? extends GrantedAuthority> authorities) {
        this.userDTO = userDTO;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "email", userDTO.email(),
                "name", userDTO.name()
        );
    }

    @Override
    public String getName() {
        return userDTO.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
