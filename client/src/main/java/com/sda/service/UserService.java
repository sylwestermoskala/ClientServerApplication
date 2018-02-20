package com.sda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUsers() throws Exception {
        URI uri = new URI("http://localhost:8181/users");
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    }

    public List<User> getUsersByEmail(String email) throws Exception {
        URI uri = new URI("http://localhost:8181/users/"+email);
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    }

    public boolean emailExist(String email) {
        String sql = "SELECT email from users where email=" + "\'" + email+ "\'"+ ";";
        if(email.equals(jdbcTemplate.queryForObject(sql,String.class))){
            return true;
        }
        return false;
    }
}
