package com.ruslan.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    //----------------------------------------------
    //@Value("${auth.type}")
    //private String authType;

    /*
    ДОБАВИТЬ В yaml файл ->

    auth:
    type: db  # Или "in-memory"
     */
    //----------------------------------------------

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

//    @Bean
//    public UserDetailsService inMemoryUserDetailsService() {
//        UserDetails user = User
//                .withUsername("Mike")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    //------------------------------------------------

    /*
    Часть 2 (по желанию):
1.	Реализовать так, чтобы одновременно были два бина – in-memory и db (необходимо добавить возможность перед запуском программы выбирать, какой вид аутентификации необходим).
2.	Создать пользователя двумя способами – через MySQL Workbench, через REST-API (controller-service-repository).


    SecurityFilterChain   ??????

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(
            "db".equals(authType) ? dbUserDetailsService : inMemoryUserDetailsService
        );
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }
     */
}
