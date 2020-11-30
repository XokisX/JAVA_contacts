package by.ermakovich.contacts.controller;


import by.ermakovich.contacts.entity.RoleEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.model.User;
import by.ermakovich.contacts.repos.UserEntityRepos;
import by.ermakovich.contacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ContactsController {
    @Autowired
    private UserEntityRepos userEntityRepos;

//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
    @GetMapping(value = "getUsers")
    public String list(){
        log.info("User request all contacts");
        UserEntity user = new UserEntity();
        user.setPassword("asd");
        user.setId(1);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setName("ROLE_USER");
        user.setRoleEntity(roleEntity);
        user.setLogin("asd");
        user.setEmail("asd");
        user.setIs_blocked(false);
        user.setNumber("asd");
        user.setUsername("asd");
        userEntityRepos.save(user);
        return "hello";
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }
//
//    @PostMapping(value = "login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ServerResponce login(@RequestBody UserLoginForm data){
//        //userRepo.save(new User(1,data.getLogin(), data.getLogin(), data.getPass(), "test","test"));
//        Iterable<User> users = userRepo.findAll();
//            for (User user: users) {
//                if(user.getLogin().equals(data.getLogin()) && passwordEncoder.matches(data.getPass(),user.getPass())){
//                    System.out.print("User is found");
//                    return new ServerResponce(true,"User is found",user);
//                }
//            }
//            System.out.print("User not found");
//            return new ServerResponce(false,"User not found",null);
//    }
//
//    @PostMapping(value = "getUserData", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ServerResponce getUserData(@RequestBody UserLoginForm data){
//        System.out.print(data.getPass());
//        System.out.print(data.getLogin());
//        Iterable<User> users = userRepo.findAll();
//        for (User user: users) {
//            if(user.getLogin().equals(data.getLogin()) && user.getPass().equals(data.getPass())){
//                System.out.print("User is found");
//                return new ServerResponce(true,"User is found",user);
//            }
//        }
//        System.out.print("User not found");
//        return new ServerResponce(false,"User not found",null);
//    }
//
//    @PostMapping(value = "register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ServerResponce register(@RequestBody UserRegisterForm data){
//        System.out.print(data.getPass());
//        System.out.print(data.getLogin());
//        Iterable<User> users = userRepo.findAll();
//        log.info(data.getEmail());
//        if(!data.getEmail().equals("")&&!data.getLogin().equals("")&&
//                !data.getNumber().equals("")&&!data.getPass().equals("")&&!data.getUsername().equals("")){
//            for (User user: users) {
//                if(!user.getLogin().equals(data.getLogin()) ){
//
//                }else{
//                    return new ServerResponce(false,"This login already exists",null);
//                }
//
//                if(!user.getEmail().equals(data.getEmail())){
//
//                }else{
//                    return new ServerResponce(false,"This email already exists",null);
//                }
//
//                if(!user.getNumber().equals(data.getNumber())){
//
//                }else{
//                    return new ServerResponce(false,"This number already exists",null);
//                }
//            }
//            User newUser =new User(null,data.getLogin(), data.getLogin(), passwordEncoder.encode(data.getPass()), data.getEmail(), data.getNumber(),false);
//            userRepo.save(newUser);
//            return new ServerResponce(true,"User is created",newUser);
//        }
//        return new ServerResponce(false,"User not created",null);
//    }
//
//    @PostMapping(value = "deleteUser", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ServerResponce deleteUser(@RequestBody UserLoginForm data){
//        System.out.print(data.getPass());
//        System.out.print(data.getLogin());
//        Iterable<User> users = userRepo.findAll();
//        for (User user: users) {
//            if(user.getLogin().equals(data.getLogin()) && user.getPass().equals(data.getPass())){
//                System.out.print("User is found");
//                return new ServerResponce(true,"User is found",user);
//            }
//        }
//        System.out.print("User not found");
//        return new ServerResponce(false,"User not found",null);
//    }

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
