package com.vermeg.bookland;

import java.io.File;

import com.vermeg.bookland.entities.Role;
import com.vermeg.bookland.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vermeg.bookland.controllers.ControllerBook;

@SpringBootApplication
public class BooklandApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
    	new File(ControllerBook.uploadDirectory).mkdir();
		new File(ControllerBook.pdfDirectory).mkdir();
        SpringApplication.run(BooklandApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByRole("ROLE_USER") == null)
            roleRepository.save(new Role("ROLE_USER"));
        if(roleRepository.findByRole("ROLE_ADMIN") == null)
            roleRepository.save(new Role("ROLE_ADMIN"));
    }
}
