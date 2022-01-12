package net.javaguides.springboot;

import java.util.List;

public interface UserService {
    List<User> fetchUsers();

    User saveUser(User user);

    User getById(int id);

    User getByEmail(String email);
    
    List<User> getByName(String name);

    User updateUser(int id, User user);

    void deleteUser(int id);
}
