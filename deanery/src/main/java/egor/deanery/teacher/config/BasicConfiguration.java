package egor.deanery.teacher.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails student = User.withUsername("student")
                .password(passwordEncoder.encode("student"))
                .roles("STUDENT")
                .build();

        UserDetails teacher = User.withUsername("teacher")
                .password(passwordEncoder.encode("teacher"))
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(student, teacher);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers("/students/**").hasRole("STUDENT")
                                .requestMatchers("/teachers/**").hasRole("TEACHER")
/*                                .requestMatchers(HttpMethod.POST,  "/students/student/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/teachers/teacher/**").permitAll()
                                .requestMatchers(HttpMethod.GET,  "/students/**").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "/teachers/**").hasRole("TEACHER")*/
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();

       // return http.build();

        /*http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/students/**").hasRole("STUDENT")
                        .requestMatchers("/teachers/**").hasRole("TEACHER")

                *//*.httpBasic(Customizer.withDefaults())
                .build();*//*

                        *//*.requestMatchers(HttpMethod.POST,  "/students/student/**").hasAuthority("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/teachers/teacher/**").hasAuthority("TEACHER")
                        .requestMatchers(HttpMethod.GET,  "/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/teachers/**").hasRole("TEACHER")*//*
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
