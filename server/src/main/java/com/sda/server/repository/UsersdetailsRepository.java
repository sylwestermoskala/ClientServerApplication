package com.sda.server.repository;

import com.sda.server.domain.Usersdetails;
import org.springframework.data.repository.CrudRepository;

public interface UsersdetailsRepository extends CrudRepository<Usersdetails, String> {

    Usersdetails findByEmail(String email);
}
