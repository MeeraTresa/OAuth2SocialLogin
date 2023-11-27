package com.cmpe279.oauth2.social.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomOidcUserService extends OidcUserService {
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (isMeeraAdmin(oidcUser)) {
            // Create a new set of authorities including ROLE_ADMIN
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        //Not possible to add original authorities
        //authorities.add((GrantedAuthority) oidcUser.getAuthorities());
        DefaultOidcUser newOidcUser = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        return newOidcUser;
    }
    private boolean isMeeraAdmin(OidcUser oidcUser) {
        String email = oidcUser.getAttribute("email"); // Replace with the actual attribute name
        return email != null && email.startsWith("meera");
    }
}
