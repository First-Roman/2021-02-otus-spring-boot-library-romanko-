package ru.otus.library.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
