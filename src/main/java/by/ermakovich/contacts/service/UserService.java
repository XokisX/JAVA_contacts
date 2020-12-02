package by.ermakovich.contacts.service;

import by.ermakovich.contacts.entity.RoleEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.repos.RoleEntityRepos;
import by.ermakovich.contacts.repos.UserEntityRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

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
        for (UserEntity user: userEntityRepos.findAll()) {
            if(
                    userEntity.getLogin().equals(user.getLogin()) ||
                            userEntity.getUsername().equals(user.getUsername()) ||
                            userEntity.getEmail().equals(user.getEmail()) ||
                            userEntity.getNumber().equals(user.getNumber())
            ){
                return null;
            }
        }
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

    public List<UserEntity> getAllUser(int pagePar, int limit){
        List<UserEntity> result = new ArrayList<>();
        List<UserEntity> list = userEntityRepos.findAll();
        long totalUsers = userEntityRepos.count();

        int totalPage =0;
        if(totalUsers/limit>(int)(totalUsers/limit)){
            totalPage = (int)(totalUsers/limit) + 1;
        }else{
            totalPage = (int)(totalUsers/limit);
        }

        int currentPage = pagePar-1;
        if(totalPage<pagePar){
            currentPage = totalPage;
        }
        if((pagePar-1)<0){
            currentPage=0;
        }

        long startPos = currentPage*limit;
        long endPos= (currentPage+1)*limit;
        if((currentPage+1)*limit>totalUsers){
            endPos=totalUsers;
        }
        long index =0;

        for (UserEntity user:list) {
            if(index>=startPos&&index<endPos) {
                user.setPassword("");
                user.setLogin("");
                result.add(user);
            }
            index++;
        }
        return result;
    }

    public List<UserEntity> searchUser(String info,int pagePar, int limit){
        List<UserEntity> result = new ArrayList<>();
        List<UserEntity> list = new ArrayList<>();
        for (UserEntity user: userEntityRepos.findAll()) {
            if (user.getNumber().contains(info) ||
                    user.getEmail().contains(info) ||
                    user.getUsername().contains(info)) {
                user.setPassword("");
                user.setLogin("");
                list.add(user);
            }
        }
        long totalUsers = list.size();

        int totalPage =0;
        if(totalUsers/limit>(int)(totalUsers/limit)){
            totalPage = (int)(totalUsers/limit) + 1;
        }else{
            totalPage = (int)(totalUsers/limit);
        }

        int currentPage = pagePar-1;
        if(totalPage<pagePar){
            currentPage = totalPage;
        }

        long startPos = currentPage*limit;
        long endPos= (currentPage+1)*limit;
        if((currentPage+1)*limit>totalUsers){
            endPos=totalUsers;
        }
        long index =0;
        for (UserEntity user:list) {
            if(index>=startPos&&index<endPos) {
                result.add(user);
            }
            index++;
        }
        return result;
    }
}
