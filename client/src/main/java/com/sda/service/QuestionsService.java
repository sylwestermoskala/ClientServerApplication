package com.sda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.domain.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;


@Service
public class QuestionsService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Questions> getQuestions() throws Exception {
        URI uri = new URI("http://localhost:8181/questions");
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValueAsString(data);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Questions.class));
    }

    public List<Questions> getQuestionsByCategory(String category) throws Exception {
        URI uri = new URI("http://localhost:8181/questions/"+category);
        String data = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValueAsString(data);
        return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Questions.class));
    }
}
