package by.ermakovich.contacts.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private int id;
    private String username;
    private String login;
    private String pass;
    private String email;
}
