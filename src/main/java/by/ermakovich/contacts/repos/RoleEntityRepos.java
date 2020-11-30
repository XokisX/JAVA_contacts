package by.ermakovich.contacts.repos;

import by.ermakovich.contacts.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepos extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByName(String name);
}
