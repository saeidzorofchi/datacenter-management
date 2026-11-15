package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
}
