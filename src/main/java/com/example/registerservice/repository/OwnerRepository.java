package com.example.registerservice.repository;

import com.example.registerservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner , Integer> {
    Owner findByEmail(String username);

    boolean existsByEmail(String email);
}
