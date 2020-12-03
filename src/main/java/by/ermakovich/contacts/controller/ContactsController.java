package by.ermakovich.contacts.controller;


import by.ermakovich.contacts.config.jwt.JwtProvider;
import by.ermakovich.contacts.controller.entity.*;
import by.ermakovich.contacts.entity.ContactEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.exception.ControllerException;
import by.ermakovich.contacts.service.ContactService;
import by.ermakovich.contacts.service.UserService;
import by.ermakovich.contacts.validator.ContactValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequestExtensionsKt;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Slf4j
@RestController
public class ContactsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ContactValidator contactValidator;

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
        log.info(page.toString());
        if(login!=null){
            if(page!=null&&limit!=null) {
                List<UserEntity> listOfUsers = userService.getAllUser(page, limit);
                listOfUsers.remove(userService.findByLogin(login));
                return new ServerResponce(true, "Successful", listOfUsers);
            }
        }
        return new ServerResponce(false,"Permissions error",null);
    }

    @PostMapping ("/test")
    public ServerResponce getUsers(){
        return new ServerResponce(true,"SOSI IVAN",userService.getAllUser(1,1));
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

    @PostMapping("/api/addContact")
    public ServerResponce addContact(@RequestHeader(name = "Authorization") String token, @RequestBody @Validated ContactRequest request, BindingResult result) throws ControllerException {
        if(!result.hasErrors()){
            String login = jwtProvider.getLoginFromToken(token.substring(7));
            if(login!=null){
                Long my_id = request.getMy_id();
                Long friend_id = request.getFriend_id();
                contactValidator.validate(new ContactEntity(my_id),result);
                contactValidator.validate(new ContactEntity(friend_id),result);
                if(!result.hasErrors()){
                    if(my_id!=null&&friend_id!=null){
                        ContactEntity contact =contactService.saveContact(my_id,friend_id);
                        if(contact!=null){
                            return new ServerResponce(true, "Successful", contact);
                        }
                            return new ServerResponce(false,"Already added",null);
                    }
                }else{
                    throw  new ControllerException("Wrong data");
                }
            }
            return new ServerResponce(false,"Permissions error",null);
        }
        return new ServerResponce(false,"ERROR",null);
    }

    @GetMapping("/api/loadContacts")
    public ServerResponce loadContacts(@RequestHeader(name = "Authorization") String token){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        if(login!=null){
            List<ContactEntity> list =  contactService.loadContacts(login);
            return new ServerResponce(true,"Successful",list);
        }
        return new ServerResponce(false,"Permissions error",null);

    }

        @DeleteMapping("/api/deleteContacts")
    public ServerResponce deleteContacts(@RequestHeader(name = "Authorization") String token,@RequestBody ContactsRequest request){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        Long my_id = request.getMy_id();
        ArrayList<Long> friends_id = request.getFriend_id();
        if(login!=null) {
            ContactEntity contact =contactService.deleteContact(login,my_id,friends_id);
            if(contact!=null){
                return new ServerResponce(true,"Successful",contact);
            }
            return new ServerResponce(false,"Contacts not found",null);
        }
        return new ServerResponce(false,"Permissions error",null);
    }

    @PostMapping("/api/sendEmail")
    public ServerResponce sendEmails(@RequestHeader(name = "Authorization") String token,@RequestBody EmailSendRequest request) throws IOException, MessagingException {
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        Long my_id = request.getMy_id();
        ArrayList<Long> friends_id = request.getContacts();
        String message = request.getMessage();
        if(login!=null) {
            if(contactService.sendMessageToContacts(login,my_id,friends_id,message)){
                return new ServerResponce(true,"Successful",null);
            }
            return new ServerResponce(false,"Contacts not found",null);
        }
        return new ServerResponce(false,"Permissions error",null);

    }

    @PostMapping("/apiAdmin/deleteUser")
    public ServerResponce deleteUser(@RequestHeader(name = "Authorization") String token,@RequestBody DeleteRequest request){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        Long id = request.getUserId();
        if(login!=null) {
            if(id!=null) {
                UserEntity deletedUser = userService.deleteUser(id);
                return new ServerResponce(true, "User is deleted", deletedUser);
            }else{
                return new ServerResponce(false,"Null param",null);
            }
        }
        return new ServerResponce(false,"Permissions error",null);
    }

    @PostMapping("/apiAdmin/blockUser")
    public ServerResponce blockUser(@RequestHeader(name = "Authorization") String token,@RequestBody BlockRequest request){
        String login = jwtProvider.getLoginFromToken(token.substring(7));
        Long id = request.getUserId();
        if(login!=null) {
            if(id!=null) {
                UserEntity deletedUser = userService.blockUser(id);
                return new ServerResponce(true, "User is blocked/unlocked", deletedUser);
            }else{
                return new ServerResponce(false,"Null param",null);
            }
        }
        return new ServerResponce(false,"Permissions error",null);
    }

    @PostMapping("/apiAdmin/register")
    public ServerResponce register(@RequestHeader(name = "Authorization") String token,@RequestBody RegisterByAdminRequest registrationRequest){
        String loginOwner = jwtProvider.getLoginFromToken(token.substring(7));
        if(loginOwner!=null) {
            UserEntity user = new UserEntity();
            String password =registrationRequest.getPassword();
            String login =registrationRequest.getLogin();
            String email =registrationRequest.getEmail();
            String number =registrationRequest.getNumber();
            String username =registrationRequest.getUsername();
            boolean admin =registrationRequest.isAdmin();
            if(password!=null&&login!=null&&email!=null&&number!=null&&username!=null&&
                password!=""&&login!=""&&email!=""&&number!=""&&username!="") {
                user.setPassword(registrationRequest.getPassword());
                user.setLogin(registrationRequest.getLogin());
                user.setEmail(registrationRequest.getEmail());
                user.setIs_blocked(false);
                user.setNumber(registrationRequest.getNumber());
                user.setUsername(registrationRequest.getUsername());
                if (userService.saveUserByAdmin(user,registrationRequest.isAdmin()) != null) {
                    return new ServerResponce(true, "User register", null);
                } else {
                    return new ServerResponce(false, "Already exist", null);
                }
            }
            return new ServerResponce(false, "Fields is empty!", null);

        }
        return new ServerResponce(false,"Permissions error",null);
    }

}
