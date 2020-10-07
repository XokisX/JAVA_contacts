package by.ermakovich.contacts.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {
    private int id;
    private String name;
    private String number;
    private String email;
}
