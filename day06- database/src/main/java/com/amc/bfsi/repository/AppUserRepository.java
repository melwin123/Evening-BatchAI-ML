package com.amc.bfsi.repository;

import com.amc.bfsi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    
    // Custom query method to find a user by their username
    Optional<AppUser> findByUsername(String username);
    
    // Optional helper method to check if a username already exists
    boolean existsByUsername(String username);
}