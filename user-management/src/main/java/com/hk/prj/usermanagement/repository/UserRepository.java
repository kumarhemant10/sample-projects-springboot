package com.hk.prj.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.prj.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
