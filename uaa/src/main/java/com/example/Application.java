package com.example;

import com.example.entity.Authority;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.AuthorityRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() < 1) {
            Role role = roleRepository.save(new Role("ADMIN",
                    Stream.of(authorityRepository.save(new Authority("user:create"))).collect(Collectors.toSet())));
            userRepository.save(new User("admin", passwordEncoder.encode("admin"), "admin",
                    Stream.of(role).collect(Collectors.toSet())));
        }
    }
}


