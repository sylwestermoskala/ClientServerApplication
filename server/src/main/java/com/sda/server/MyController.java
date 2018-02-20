package com.sda.server;

import com.sda.server.domain.Questions;
import com.sda.server.domain.Users;
import com.sda.server.domain.Usersdetails;
import com.sda.server.service.QuestionsService;
import com.sda.server.service.UserService;
import com.sda.server.service.UsersdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersdetailsService usersdetailsService;

    @Autowired
    private QuestionsService questionsService;

    @GetMapping(value = "/users")
    public List<Users> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{email}")
    public Users getUser(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/details/{email}")
    public Usersdetails getDetails(@PathVariable("email") String email){
        return usersdetailsService.getDetailsOfUserByEmail(email);
    }

    @GetMapping(value = "/questions")
    public List<Questions> getQuestions(){
        return questionsService.getQuestions();
    }

    @GetMapping(value = "/questions/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable("category") String category){
        return questionsService.getQuestionsByCategory(category);
    }


    @RequestMapping(value = "/adduserdetails", method = RequestMethod.POST)
    public String addActor(@Valid @ModelAttribute("gender") String gender,
                           @ModelAttribute("country") String country,
                           @ModelAttribute("email") String email,
                           //String email,
                           BindingResult result,
                           ModelMap modelMap){
        if (!result.hasErrors()){
            usersdetailsService.adduserdetails(new Usersdetails(email,gender, country));
            return "success";
        } else {
            return "error";
        }
    }

}
