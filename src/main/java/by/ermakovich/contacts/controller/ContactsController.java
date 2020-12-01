package by.ermakovich.contacts.controller;


import by.ermakovich.contacts.config.jwt.JwtProvider;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ContactsController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
    @GetMapping(value = "getUsers")
    public String list(){
//        log.info("User request all contacts");
//        UserEntity user = new UserEntity();
//        user.setPassword("asd");
//        user.setId(1);
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setId(1);
//        roleEntity.setName("ROLE_USER");
//        user.setRoleEntity(roleEntity);
//        user.setLogin("asd");
//        user.setEmail("asd");
//        user.setIs_blocked(false);
//        user.setNumber("asd");
//        user.setUsername("asd");
        return "hello";
    }

    @GetMapping("/getUserByToken")
    public ServerResponce getUser(@RequestHeader(name = "Authorization") String token){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        if(login!=null&& !login.equals("")){
            UserEntity user = userService.findByLogin(login);
            if(user!=null){
                user.setPassword("");
                return new ServerResponce(true,"Successful",user);
            }

        }
        return new ServerResponce(false ,"Wrong token",null);

    }

    @GetMapping("/checkAdminByToken")
    public ServerResponce checkAdmin(@RequestHeader(name = "Authorization") String token){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        if(login!=null && !login.equals("")){
            UserEntity user = userService.findByLogin(login);
            if(user!=null){
                if(user.getRoleEntity().getName().equals("ROLE_ADMIN")){
                    return new ServerResponce(true,"Successful",null);
                }
            }
        }
        return new ServerResponce(false ,"Wrong token",null);
    }


}
