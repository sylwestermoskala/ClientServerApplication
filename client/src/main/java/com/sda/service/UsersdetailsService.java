package com.sda.service;

import com.sda.domain.Usersdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class UsersdetailsService {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> getDetails(String email) throws Exception {
        URI uri = new URI("http://localhost:8181/details/"+email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("email", email);

        ResponseEntity<Usersdetails> response = restTemplate.getForEntity(uri, Usersdetails.class);
        map.add("response", response.getBody());
        return map.toSingleValueMap();
    }

    public Map<String, String> addUserDetails(String email, String gender, String country) throws URISyntaxException {
        URI uri = new URI("http://localhost:8181/adduserdetails");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("gender", gender);
        map.add("country", country);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        map.add("response", response.getBody());
        return map.toSingleValueMap();
    }
}
