package com.amc.bfsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amc.bfsi.entity.AppUser;
import com.amc.bfsi.repository.AppUserRepository;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    public boolean validateCredentials(String rawUsername, String rawPassword) {
        // Find user by username using the repository method
        AppUser user = appUserRepository.findByUsername(rawUsername).orElse(null);
        
        if (user == null) {
            return false; // User not found
        }

        // Check plain text password directly against the database column
        return user.getPassword().equals(rawPassword);
    }
}