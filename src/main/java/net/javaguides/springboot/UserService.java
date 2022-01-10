package net.javaguides.springboot;

import java.util.List;

public interface UserService {
    List<User> fetchUsers();

    User saveUser(User user);

    User getById(int id);

    User getByEmail(String email);

    User updateUser(int id, User user);

    void deleteUser(int id);
}