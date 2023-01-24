package com.clinicadh.proyecto.service;

import com.clinicadh.proyecto.model.AppUser;
import com.clinicadh.proyecto.model.AppUserRole;
import com.clinicadh.proyecto.repository.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String hashedPassword= passwordEncoder.encode("password");
        String hashedPassword2 = passwordEncoder.encode("password");

        userRepository.save(new AppUser("carina", "carina", "carina@gmail.com", hashedPassword, AppUserRole.ROLE_ADMIN));
        userRepository.save(new AppUser("nicolas", "nicolas", "nicolas@gmail.com", hashedPassword2, AppUserRole.ROLE_USER));
    }
}
