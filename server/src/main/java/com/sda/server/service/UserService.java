package com.sda.server.service;

import com.sda.server.domain.Users;
import com.sda.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        List<Users> result = new ArrayList<>();
        Iterable<Users> iterable =  userRepository.findAll();
        iterable.forEach(e-> result.add(e));
        return result;
    }


    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
