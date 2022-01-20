package net.javaguides.springboot;

//import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    List<User> fetchUsers();

    User saveUser(User user);

    User getById(int id);

    User getByEmail(String email);

    Page<User> getByName(String name, Pageable pageable);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
