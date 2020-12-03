package by.ermakovich.contacts.controller;

import by.ermakovich.contacts.config.jwt.JwtProvider;
import by.ermakovich.contacts.controller.entity.AuthRequest;
import by.ermakovich.contacts.controller.entity.RegistrationRequest;
import by.ermakovich.contacts.controller.entity.ServerResponce;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ServerResponce registerUser(@RequestBody  @Valid RegistrationRequest registrationRequest ){
        UserEntity user = new UserEntity();
        String password =registrationRequest.getPassword();
        String login =registrationRequest.getLogin();
        String email =registrationRequest.getEmail();
        String number =registrationRequest.getNumber();
        String username =registrationRequest.getUsername();

        if(password!=null&&login!=null&&email!=null&&number!=null&&username!=null&&
            password!=""&&login!=""&&email!=""&&number!=""&&username!="") {
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            user.setEmail(registrationRequest.getEmail());
            user.setIs_blocked(false);
            user.setNumber(registrationRequest.getNumber());
            user.setUsername(registrationRequest.getUsername());
            if (userService.saveUser(user) != null) {
                return new ServerResponce(true, "User register", null);
            } else {
                return new ServerResponce(false, "Already exist", null);
            }
        }
        return new ServerResponce(false, "Fields is empty!", null);
    }

    @PostMapping("/auth")
    public ServerResponce auth(@RequestBody AuthRequest request){
        if(request.getLogin()!=null&&request.getPassword()!=null) {
            UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
            if (userEntity != null ) {
                if(!userEntity.getIs_blocked()){
                    String token = jwtProvider.generateToken(request.getLogin());
                    return new ServerResponce(true, "Successful", token);
                }
                return new ServerResponce(false, "User is blocked", null);
            }
        }
        return new ServerResponce(false, "Wrong login or password", null);
    }

}
