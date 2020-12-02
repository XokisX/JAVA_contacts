package by.ermakovich.contacts.service;

import by.ermakovich.contacts.entity.ContactEntity;
import by.ermakovich.contacts.entity.UserEntity;
import by.ermakovich.contacts.repos.ContactEntityRepos;
import by.ermakovich.contacts.repos.UserEntityRepos;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
@Service
public class ContactService {

    @Autowired
    private UserEntityRepos userEntityRepos;

    @Autowired
    private ContactEntityRepos contactEntityRepos;

    @Autowired
    private JavaMailSender emailSender;

    public boolean saveContact(Long my_id,Long friend_id){
        for (ContactEntity contact:contactEntityRepos.findAll()) {
            if(contact.getUser1().getId().equals(my_id) && contact.getUser2().getId().equals(friend_id)){
                return false;
            }
        }
        ContactEntity contact = new ContactEntity();
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        for (UserEntity user:userEntityRepos.findAll()) {
            if(user.getId()==my_id){
                user1=user;
            }else if(user.getId()==friend_id){
                user2=user;
            }
        }
        if(user1.getId()!=null&&user2.getId()!=null){
            contact.setUser1(user1);
            contact.setUser2(user2);
            contactEntityRepos.save(contact);
            return true;
        }
        return false;

    }

    public List<ContactEntity> loadContacts(String login){
        UserEntity user = userEntityRepos.findByLogin(login);
        List<ContactEntity> list = new ArrayList<>();
        if(user!=null){
            for (ContactEntity contact :contactEntityRepos.findAll()) {
                if(user.getId()==contact.getUser1().getId()){
                    list.add(contact);
                }
            }
        }
        return list;
    }

    public Boolean deleteContact(String login,Long my_id,ArrayList<Long> contacts){
        UserEntity user = userEntityRepos.findByLogin(login);
        List<ContactEntity> list = new ArrayList<>();
        if(user!=null&& user.getId().equals(my_id)){
            for (ContactEntity contact :contactEntityRepos.findAll()) {
                if(user.getId().equals(contact.getUser1().getId())){
                    list.add(contact);
                }
            }
            for (ContactEntity contact:list) {
                if(contacts.contains(contact.getUser2().getId())){
                    contactEntityRepos.delete(contact);
                }
            }
            return true;
        }
        return false;
    }

    public Boolean sendMessageToContacts(String login,Long my_id,ArrayList<Long> contacts,String msg) throws IOException, MessagingException {
        UserEntity user = userEntityRepos.findByLogin(login);
        List<ContactEntity> list = new ArrayList<>();
        if(user!=null&& user.getId().equals(my_id)){
            for (ContactEntity contact :contactEntityRepos.findAll()) {
                if(user.getId().equals(contact.getUser1().getId())){
                    list.add(contact);
                }
            }
            for (ContactEntity contact:list) {
                if(contacts.contains(contact.getUser2().getId())){
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("javacontactssender@gmail.com");
                    message.setTo(contact.getUser2().getEmail());
                    message.setSubject(contact.getUser1().getUsername() + " Send to you message");
                    message.setText(msg);
                    emailSender.send(message);
                }
            }
            return true;
        }
        return false;
    }

}
