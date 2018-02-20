package com.sda;

import com.sda.domain.User;
import com.sda.service.QuestionsService;
import com.sda.service.UserService;
import com.sda.service.UsersdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;


@Controller
public class MyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsersdetailsService usersdetailsService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/quiz1")
    public String quiz(ModelMap modelMap){
        try {
            modelMap.addAttribute("questions",questionsService.getQuestions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "quiz1";
    }

    @RequestMapping(value = "/quiz1/{category}")
    public String quiz(ModelMap modelMap, @ModelAttribute("category") String category) {
        try {
            modelMap.addAttribute("questions", questionsService.getQuestionsByCategory(category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "quiz1";
    }

    @GetMapping(value = "/userdetail")
    public String detail(HttpServletRequest request,
                         ModelMap modelMap){
        try {
            modelMap.addAllAttributes(usersdetailsService.getDetails(request.getUserPrincipal().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userdetail";
    }

    @RequestMapping(value = "/")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/log")
    public String logPage() {
        return "log";
    }

    @RequestMapping(value = "/registrationPage")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "registrationPage";
    }

    @RequestMapping(value = "/correct/answer")
    public ResponseEntity<Boolean> correctAnswer (@RequestParam(name = "id") Integer answerId,
                                         @RequestParam(name = "answer") String choosenAnswer) {
        String sql = String.format("SELECT answer from questions where id=%d", answerId);
        String result =  jdbcTemplate.queryForObject(sql, String.class);
        if(result.equals(choosenAnswer))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false,HttpStatus.OK);
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addusers(@ModelAttribute("email") String email,
                           @ModelAttribute("password") String password,
                           ModelMap modelMap){ // bo z posta

//        if (userService.emailExist(email)) {
//            return "redirect:/registrationPage";
//        }

        String sqlUser = String.format("INSERT INTO users(email,password,enabled) VALUES ('%s','%s',true)", email, password);
        jdbcTemplate.execute(sqlUser);
        String sqlRole = String.format("INSERT INTO user_roles(email,role) VALUES ('%s','%s')",email, "ROLE_USER");
        jdbcTemplate.execute(sqlRole);
//        List<User> userList = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
//        modelMap.addAttribute("users",userList);

        return "login";
    }



    @RequestMapping(value = "/adduserdetails", method = RequestMethod.POST)
    public String addActor(@ModelAttribute("gender") String gender,
                           @ModelAttribute("country") String country,
                           @ModelAttribute("email") String email,
                           //String email,
                           ModelMap modelMap) {
        try {
            modelMap.addAllAttributes(usersdetailsService.addUserDetails(email, gender, country));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/userdetail";
    }

}