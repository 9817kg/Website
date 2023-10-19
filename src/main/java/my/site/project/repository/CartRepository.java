package com.fullstack2.website.repository;

import com.fullstack2.website.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByMember(Long member);
}
