package com.iamofoe.queue.config;

import com.iamofoe.queue.domain.model.Role;
import com.iamofoe.queue.service.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            userService.createAndSaveRole(new Role(null, "ROLE_USER"));
            userService.createAndSaveRole(new Role(null, "ROLE_ADMIN"));
        };
    }
}
