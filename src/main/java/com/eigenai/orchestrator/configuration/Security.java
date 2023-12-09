package com.eigenai.orchestrator.configuration;

import com.eigenai.orchestrator.handler.CustomLogoutHandler;
import com.eigenai.orchestrator.handler.CustomizeAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Security.
 */
@Slf4j
@Configuration
//@EnableWebSecurity
public class Security {

    private final CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Value("${aws.cognito.logoutUrl}")
    private String logoutUrl;

    @Value("${aws.cognito.logout.success.redirectUrl}")
    private String logoutRedirectUrl;

    @Value("${spring.security.oauth2.client.registration.cognito.clientId}")
    private String clientId;

    /**
     * Instantiates a new Security.
     *
     * @param customizeAuthenticationSuccessHandler the customize authentication success handler
     */
    public Security(CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler) {
        this.customizeAuthenticationSuccessHandler = customizeAuthenticationSuccessHandler;
    }

    /**
     * User authorities mapper granted authorities mapper.
     *
     * @return the granted authorities mapper
     */
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            try {
                OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) new ArrayList<>(authorities).get(0);
                mappedAuthorities = ((ArrayList<?>) oidcUserAuthority.getAttributes().get("cognito:groups")).stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
            } catch (Exception exception) {
                log.info("Not Authorized!");
                log.info(exception.getMessage());
            }
            return mappedAuthorities;
        };
    }

    /**
     * Security filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/v1/general/*").permitAll()
                        .requestMatchers("/v1/user").hasAnyRole("ADMIN", "USER").anyRequest().authenticated())
                .oauth2Login(oauth -> oauth.redirectionEndpoint(endPoint -> endPoint.baseUri("/login/oauth2/code/cognito"))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userAuthoritiesMapper(userAuthoritiesMapper()))
                        .successHandler(customizeAuthenticationSuccessHandler))
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer.logoutSuccessHandler(
                            new CustomLogoutHandler(logoutUrl, logoutRedirectUrl, clientId));
                });
        return http.build();
    }
}