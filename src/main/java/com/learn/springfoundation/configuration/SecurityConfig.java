package com.learn.springfoundation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
//        configuration.setAllowedMethods(List.of("GET", "POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    @Order(1)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/actuator/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasRole(ADMIN))
                .httpBasic(withDefaults())
                .build();
    }

//    @Bean
//    @Order(0)
//    public SecurityFilterChain h2FilterChain(HttpSecurity http) throws Exception {
//        return http
//                .securityMatcher("/h2/")
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
//                .httpBasic(withDefaults())
//                .build();
//    }

    @Bean
    @Order(0)
    public SecurityFilterChain customActuatorFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/actuator/custom")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .cors(AbstractHttpConfigurer::disable) // todo configure cors
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .cors(AbstractHttpConfigurer::disable) // todo configure cors
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain dsiApiFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/dsi")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain oneSqlFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/sql/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain errorFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/error**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .httpBasic(withDefaults())
                .build();
    }


//    @Bean
//    @Order(2)
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .securityMatcher("/api/**")
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasAnyRole(ADMIN, USER))
//                .httpBasic(withDefaults())
//                .build();
//    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).formLogin(withDefaults());
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles(USER).build());            // IT'S NOT A REAL APP!!
        manager.createUser(users.username("admin").password("password").roles(USER, ADMIN).build());    // SERIOUSLY GO BE SMART SOMEWHERE ELSE!
        return manager;
    }

}
