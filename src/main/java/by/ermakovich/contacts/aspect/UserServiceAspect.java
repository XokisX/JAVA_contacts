package by.ermakovich.contacts.aspect;

import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.exception.ControllerException;
import by.ermakovich.contacts.repos.UserEntityRepos;
import by.ermakovich.contacts.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class UserServiceAspect {
    @Autowired
    private UserService userService;
    @Autowired
    private UserEntityRepos userRepository;


    @Pointcut("execution(public * by.ermakovich.contacts.service.UserService.saveUser(..))")
    public void callAtUserServiceSaveUser() {
    }

    @Pointcut("execution(public * by.ermakovich.contacts.service.UserService.findByLogin(..))")
    public void callAtUserServiceFindByLogin() {
    }

    @Before("callAtUserServiceSaveUser()")
    public void beforeCallAtUserServiceSaveUser(JoinPoint jp) throws ControllerException {
        Object[] objects = jp.getArgs();
        long id = (long) objects[0];

    }

    @Before("callAtUserServiceFindByLogin()")
    public void beforeCallAtUserServiceExistsUserByLogin(JoinPoint jp) throws ControllerException {
        Object[] objects = jp.getArgs();
        String login = (String) objects[0];

    }

    @After("callAtUserServiceFindByLogin()")
    public void callAtUserServiceFindByLogin(JoinPoint jp) {
        log.info("After callAtUserServiceFindByLogin");
    }

    @After("callAtUserServiceSaveUser()")
    public void afterCallAtUserServiceSaveUser(JoinPoint jp) {
        log.info("After callAtUserServiceSaveUser");
    }
}
