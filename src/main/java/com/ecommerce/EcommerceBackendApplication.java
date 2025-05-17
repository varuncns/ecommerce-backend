package com.ecommerce;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceBackendApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = new User(null, "First User", "first@example.com");
        userService.createUser(user);
        System.out.println("✔ Test user inserted via UserService");
    }
}
