package by.ermakovich.contacts.validator;

import by.ermakovich.contacts.entity.ContactEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContactValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ContactEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ContactEntity contactEntity =(ContactEntity)o;
        if(contactEntity.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}