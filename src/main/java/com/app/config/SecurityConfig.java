package com.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.app.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    //     return httpSecurity
    //         .csrf(csrf -> csrf.disable())
    //         .httpBasic(Customizer.withDefaults())
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .authorizeHttpRequests(http -> {
    //             //configurar endpoints publico
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
    //             //configurar endpoints privados
    //             http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("READ");
    //             //cualquier otro endpoints
    //             http.anyRequest().denyAll();
    //         })    
    //         .build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     List<UserDetails> userDetailsList = new ArrayList<>();
    //     userDetailsList.add(User.withUsername("Alex")
    //             .password("1234")
    //             .roles("ADMIN")
    //             .authorities("READ","CREATE")
    //             .build());

    //     userDetailsList.add(User.withUsername("Gonzalo")
    //             .password("1234")
    //             .roles("USER")
    //             .authorities("READ")
    //             .build());        

    //     return new InMemoryUserDetailsManager(userDetailsList);        
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
