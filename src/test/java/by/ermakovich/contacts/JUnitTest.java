package by.ermakovich.contacts;

import by.ermakovich.contacts.entity.ContactEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.service.ContactService;
import by.ermakovich.contacts.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
class JUnitTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Test
    void foundUserByLogin() {
            Assert.assertFalse(userService.findByLogin("pavel").getIs_blocked());
    }
    @Test
    void findByLoginAndPassword() {
            Assert.assertTrue(userService.findByLoginAndPassword("pavel","pavel").getIs_blocked());
    }
    @Test
    void deleteContact() {
        Assert.assertFalse(contactService.deleteContact("lukashenko",(long)1,new ArrayList<Long>(1))!=null);
    }
    @Test
    void saveContact() {
            Assert.assertFalse(contactService.saveContact((long)1,(long)3)==null);
    }

}