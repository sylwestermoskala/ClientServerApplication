package com.sda.server.repository;


import com.sda.server.domain.Questions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionsRepository extends CrudRepository<Questions, Integer> {

    List<Questions> findAllByCategory(String category);
}
