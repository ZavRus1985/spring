package com.ruslan.springsecurity.config;

import com.ruslan.springsecurity.filter.CustomAuthenticationFilter;
import com.ruslan.springsecurity.filter.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final DaoUserDetailsService daoUserDetailsService;
    private final JwtUtil jwtUtil;

//    @Bean
//    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

    /*  module 3

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http,
                                            AuthenticationManager authManager) throws Exception {
        return http
                .securityContext(c -> c.requireExplicitSave(false))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(new CustomAuthenticationFilter(authManager))
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();
    }

     */

//module 4
    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http,
                                            AuthenticationManager authManager) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(new CustomAuthenticationFilter(authManager, jwtUtil))
                .addFilterAfter(new JwtTokenVerifier(jwtUtil), CustomAuthenticationFilter.class)
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(c -> c
//                        .requestMatchers(HttpMethod.GET).authenticated()
//                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Защищаем API администратора
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .authenticationProvider(inMemoryAuthenticationProvider())
//                .authenticationProvider(daoAuthenticationProvider())
//                .build();
//    }

//    @Bean
//    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults())
//                .authenticationProvider(inMemoryAuthenticationProvider())
//                .authenticationProvider(daoAuthenticationProvider())
//                .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(daoUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
/*
    public DaoAuthenticationProvider inMemoryAuthenticationProvider() {
        DaoAuthenticationProvider inMemoryAuthenticationProvider = new DaoAuthenticationProvider();
        inMemoryAuthenticationProvider.setUserDetailsService(inMemoryUserDetailsService());
        inMemoryAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return inMemoryAuthenticationProvider;
    }

    public InMemoryUserDetailsManager inMemoryUserDetailsService() {
        UserDetails user = User
                .withUsername("Kate")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

 */
}
