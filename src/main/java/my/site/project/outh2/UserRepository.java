package com.fullstack2.website.oauth2;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack2.website.entity.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findUserByEmailAndProvider(String email, String provider); 
}