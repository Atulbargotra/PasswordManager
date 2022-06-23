package org.gradientworks.passwordmanager;

import org.gradientworks.passwordmanager.domain.Authority;
import org.gradientworks.passwordmanager.domain.User;
import org.gradientworks.passwordmanager.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class PasswordManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            Authority admin = userService.saveAuthority(new Authority("ADMIN"));
            Authority user = userService.saveAuthority(new Authority("USER"));

            userService.saveUser(new User("Atul Verma", "atulverma", "password", admin));
            userService.saveUser(new User("Mukul Verma", "mukulverma", "password", user));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
