package by.ermakovich.contacts.controller;

import by.ermakovich.contacts.model.User;
import by.ermakovich.contacts.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("contacts")
public class ContactsController {
    @Autowired
    private UserRepo userRepo;
    @Value("${welcome.message}")
    private String message;

    @GetMapping
    public Iterable<User> list(){
        log.info("User request all contacts");
        Iterable<User> users = userRepo.findAll();
        return users;
    }

//
//    @GetMapping(value = {"/", "/index"})
//    public ModelAndView index(Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        log.info("/index was called");
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
//
//    @GetMapping(value = { "/allContacts"})
//    public ModelAndView allContacts(Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("allContacts");
//        log.info("User request all contacts");
//        Iterable<User> users = userRepo.findAll();
//        model.addAttribute("users", users);
//        return modelAndView;
//    }
//    @GetMapping(value = { "/allContacts"})
//    public String allContacts(Map<String,Object> model){
//        User newUser = new User("1","1","1","1");
//        userRepo.save(newUser);
//
//
//        return "allContacts";
//    }


}
