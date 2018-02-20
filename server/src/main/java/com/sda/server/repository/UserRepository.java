package com.sda.server.repository;


import com.sda.server.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {


    Users findByEmail(String e);
}
