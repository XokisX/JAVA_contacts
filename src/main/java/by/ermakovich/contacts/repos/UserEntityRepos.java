package by.ermakovich.contacts.repos;

import by.ermakovich.contacts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface UserEntityRepos extends JpaRepository<UserEntity,Integer> {
    UserEntity findByLogin(String login);
}
