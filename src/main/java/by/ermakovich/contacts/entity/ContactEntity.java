package by.ermakovich.contacts.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Data
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "my_id")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserEntity user2;
}
