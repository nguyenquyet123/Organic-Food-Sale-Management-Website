package com.edu.poly.assgn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.edu.poly.assgn.service.SessionService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountDetailService accountDetailService;

    @Autowired
    SessionService session;

    private final String[] ADMIN_ENDPOINTS = { "/rest/authorities", "/rest/authorities/**", "/rest/accounts/**",
            "/rest/accounts", "/rest/roles/**", "/rest/roles" };

    private final String[] ADMIN_STAFF_ENDPOINTS = { "/rest/category", "/rest/category/**", "/rest/products/**",
            "/rest/products", "/rest/orders/**", "/rest/orders", "/rest/orderdetails/**", "/rest/orderdetails",
            "/shop/admin" };

    private final String[] PUBLIC_ENDPOINTS = { "/shop/home", "/shop/about",
            "/shop/blog-single", "/shop/blog", "/shop/wishlist", "/shop/product-single",
            "/shop/product-single/**", "/shop/shop", "/shop/shop/**", "/shop/contact",
            "/shop/cart", "/shop/cart/**", "/shop/login", "/shop/logout" };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // .csrf(AbstractHttpConfigurer::disable)
        // .httpBasic(Customizer.withDefaults())

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/spa/**").permitAll()
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/shop/checkout").hasAnyRole("STAFF", "ADMIN", "CUSTOMER")

                        .requestMatchers(HttpMethod.GET, ADMIN_STAFF_ENDPOINTS).hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.POST, ADMIN_STAFF_ENDPOINTS).hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.PUT, ADMIN_STAFF_ENDPOINTS).hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.DELETE, ADMIN_STAFF_ENDPOINTS).hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(PUBLIC_ENDPOINTS).hasAnyRole("STAFF", "ADMIN")

                        .requestMatchers(HttpMethod.GET, ADMIN_ENDPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, ADMIN_ENDPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ADMIN_ENDPOINTS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ADMIN_ENDPOINTS).hasRole("ADMIN")
                        .anyRequest().permitAll());

        http.formLogin(login -> {
            login
                    .loginPage("/shop/login")
                    .failureUrl("/shop/fail")
                    .successHandler(new AuthenticationSuccessHandler(session));
        });

        http.logout(logout -> {
            logout
                    .logoutUrl("/shop/logout")
                    .logoutSuccessUrl("/shop/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
        });

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return accountDetailService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
