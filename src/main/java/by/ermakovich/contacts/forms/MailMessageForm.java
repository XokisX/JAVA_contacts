package by.ermakovich.contacts.forms;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessageForm {
    private int id;
    private int sender;
    private int receiver;
    private Date date;
    private String title;
    private String message;
}
