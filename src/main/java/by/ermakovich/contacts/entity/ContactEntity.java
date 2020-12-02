package by.ermakovich.contacts.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
@Data
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @NotNull
    @NotEmpty
    @JoinColumn(name = "my_id")
    private UserEntity user1;

    @NotEmpty
    @NotNull
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private UserEntity user2;

    public ContactEntity(Long id) {
        this.id = id;
    }

    public ContactEntity() {
    }
}
