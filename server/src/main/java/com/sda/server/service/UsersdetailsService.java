package com.sda.server.service;

import com.sda.server.domain.Usersdetails;
import com.sda.server.repository.UsersdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersdetailsService {

    @Autowired
    private UsersdetailsRepository usersdetailsRepository;

    public Usersdetails getDetailsOfUserByEmail(String email){
        return usersdetailsRepository.findByEmail(email);
    }

    public Usersdetails adduserdetails(Usersdetails usersdetails) {
        return usersdetailsRepository.save(usersdetails);
    }
}
