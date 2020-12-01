package by.ermakovich.contacts.controller;


import by.ermakovich.contacts.config.jwt.JwtProvider;
import by.ermakovich.contacts.controller.entity.AuthRequest;
import by.ermakovich.contacts.controller.entity.PaginationRequest;
import by.ermakovich.contacts.controller.entity.SearchRequest;
import by.ermakovich.contacts.controller.entity.ServerResponce;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
@RestController
public class ContactsController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

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

    @GetMapping("/apiAdmin/checkAdminByToken")
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

        @PostMapping("/api/getAllUsers")
    public ServerResponce getAllUsers(@RequestHeader(name = "Authorization") String token,@RequestBody PaginationRequest request){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        System.out.print(page);
        System.out.print(limit);
        if(login!=null){
            if(page!=null&&limit!=null) {
                List<UserEntity> listOfUsers = userService.getAllUser(page, limit);
                return new ServerResponce(true, "Successful", listOfUsers);
            }
        }
        return new ServerResponce(false,"Permissions error",null);
    }

    @PostMapping("/api/searchUser")
    public ServerResponce searchUser(@RequestHeader(name = "Authorization") String token,@RequestBody SearchRequest request){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        String info = request.getInfo();
        Integer page = request.getPage();
        Integer limit = request.getLimit();
        System.out.print(page);
        System.out.print(limit);
        if(login!=null){
            if(page!=null&&limit!=null) {
                List<UserEntity> listOfUsers = userService.searchUser(info,page, limit);
                return new ServerResponce(true, "Successful", listOfUsers);
            }
        }
        return new ServerResponce(false,"Permissions error",null);
    }


}
