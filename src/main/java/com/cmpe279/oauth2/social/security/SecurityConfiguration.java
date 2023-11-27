package com.cmpe279.oauth2.social.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequestsCustomizer)->authorizeHttpRequestsCustomizer.anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService).oidcUserService(customOidcUserService)));
        return http.build();
    }
}
