package com.enchereseni.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Autowired
        private DataSource dataSource;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/", "/user", "/index", "/logout", "/login", "/register", "/encheres", "/css/**", "/error", "/images/**", "/CreationEnchere/**").permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login").permitAll()
                            .failureUrl("/login?error=true")
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                    ).csrf(csrf -> csrf
                            .ignoringRequestMatchers("/register")
                            .ignoringRequestMatchers("/user")
                            .ignoringRequestMatchers("/user/deleteMyAccount")
                            .ignoringRequestMatchers("/encheres")
                    );
            return http.build();
        }


        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource) {
            JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

            manager.setUsersByUsernameQuery(
                    "SELECT pseudo AS username, mot_de_passe AS password, 1 FROM UTILISATEURS WHERE pseudo = ?");

            manager.setAuthoritiesByUsernameQuery(
                    "SELECT pseudo AS username, CASE WHEN administrateur = 1 THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END " +
                            "FROM UTILISATEURS WHERE pseudo = ?");

            return manager;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


    public class MessageConfig {
        @Bean
        public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }
    }
}
