package by.ermakovich.contacts.controller;

import by.ermakovich.contacts.config.jwt.JwtProvider;
import by.ermakovich.contacts.controller.entity.AuthRequest;
import by.ermakovich.contacts.controller.entity.RegistrationRequest;
import by.ermakovich.contacts.controller.entity.ServerResponce;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ServerResponce registerUser(@RequestBody  @Valid RegistrationRequest registrationRequest ){
        UserEntity user = new UserEntity();
        user.setPassword(registrationRequest.getPassword());
        user.setLogin(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        user.setIs_blocked(false);
        user.setNumber(registrationRequest.getNumber());
        user.setUsername(registrationRequest.getUsername());
        userService.saveUser(user);
        return new ServerResponce(true,"User register",null);
    }

    @PostMapping("/auth")
    public ServerResponce auth(@RequestBody AuthRequest request){
        if(request.getLogin()!=null&&request.getPassword()!=null) {
            UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
            if (userEntity != null) {
                String token = jwtProvider.generateToken(request.getLogin());
                return new ServerResponce(true, "Successful", token);
            }
        }
        return new ServerResponce(false, "Wrong login or password", null);
    }

}
