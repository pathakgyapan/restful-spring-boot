package net.javaguides.springboot.configuration;

import net.javaguides.springboot.UserRepository;
import net.javaguides.springboot. UserService;
import  net.javaguides.springboot.UserServiceImpl;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceBean {

    UserRepository userRepository;

    public UserServiceBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  @Bean
    public UserService getUserService() {
        return new UserServiceImpl(userRepository);
    }
}
