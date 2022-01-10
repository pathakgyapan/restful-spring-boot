package net.javaguides.springboot;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.var;
import net.javaguides.springboot.Exception.ItemNotFoundException;

@Service
//they are holding the business logic and annotates classes at the service layer
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//common recommendation to use @override on all inherited methods
    //indicates that a method declartion is intended to over ride a method declaration
    @Override
    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(int id) {
        // throw new CustomException(HttpStatus.BAD_REQUEST, "Resource not found");
        return userRepository
            .findById(id)
            .orElseThrow(ItemNotFoundException::new);
        // return userRepository.findById(id).orElseThrow(CustomException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository
            .findByEmail(email)
            .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public User updateUser(int id, User user) {
        var oldUser = getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFullname(user.getFullname());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(int id) {
        getById(id);
        userRepository.deleteById(id);
    }
}
