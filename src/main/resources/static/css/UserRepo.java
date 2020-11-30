package by.ermakovich.contacts.repos;

import by.ermakovich.contacts.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {
}
