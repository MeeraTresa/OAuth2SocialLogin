package com.cmpe279.oauth2.social.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class CustomOAuth2UserService  extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // Extract user attributes and assign roles based on some criteria
        Set<GrantedAuthority> authorities = new HashSet<>();
        String login = (String) oAuth2User.getAttribute("login");
        // Alternatively, you can check if email starts with "meera"

        if (login != null && login.startsWith("Meera")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //authorities.add((GrantedAuthority)oAuth2User.getAuthorities());

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "id");
    }
}
