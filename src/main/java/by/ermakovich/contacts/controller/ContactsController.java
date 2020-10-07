package by.ermakovich.contacts.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ContactsController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/index was called");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = { "/allContacts"})
    public ModelAndView allContacts(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allContacts");
        log.info("User request all contacts");
        model.addAttribute("message", message);
        return modelAndView;
    }
}
