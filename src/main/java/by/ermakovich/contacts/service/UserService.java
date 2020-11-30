package by.ermakovich.contacts.service;

import by.ermakovich.contacts.entity.RoleEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.repos.RoleEntityRepos;
import by.ermakovich.contacts.repos.UserEntityRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepos userEntityRepos;

    @Autowired
    private RoleEntityRepos roleEntityRepos;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity){
        RoleEntity userRole = roleEntityRepos.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepos.save(userEntity);
    }

    public UserEntity findByLogin(String login){
        return userEntityRepos.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
