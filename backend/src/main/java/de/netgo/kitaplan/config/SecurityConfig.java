package de.netgo.kitaplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final ErrorHandler errorHandler;

  public SecurityConfig(final ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Bean
  protected SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            r -> r.requestMatchers("/api/public/**").permitAll().anyRequest()
                .authenticated())
        .exceptionHandling(e -> e.authenticationEntryPoint(errorHandler))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(
            httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer.jwt(
                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                    new JwtAuthenticationConverter())));

    return http.build();
  }

}
