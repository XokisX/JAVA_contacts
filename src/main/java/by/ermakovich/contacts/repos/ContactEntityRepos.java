package by.ermakovich.contacts.repos;

import by.ermakovich.contacts.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEntityRepos extends JpaRepository<ContactEntity,Integer> {
}
