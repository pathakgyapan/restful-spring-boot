package net.javaguides.springboot;

import net.javaguides.springboot.Exception.ItemNotFoundException;
import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.criteria.Predicate;
import org.hibernate.Session;

import org.jvnet.hk2.annotations.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;

import lombok.var;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserServiceImpl(
    		UserRepository userRepository,
    		EntityManager entityManager
    		){	
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }
//common recommendation to use @override on all inherited methods
    //indicates that a method declartion is intended to over ride a method declaration
   
    @Override
    public List<User> fetchUsers() {
        var users = userRepository.findAll();
       
        // for (User user : users) {
        //     System.out.println(user.getEmail());
        // }

        // users.forEach(user -> {
        //     System.out.println(user.getEmail());
        // });

        // // ["xyz@gmail.com"]

        // var filterdUser = users
        //     .stream()
        //     .filter(user -> {
        //         System.out.println(user.getEmail());
        //         return user.getEmail().contains("gmail.com");
        //     }).collect(Collectors.toList());
        
        return users;
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
            .orElseThrow(() -> new ItemNotFoundException());
        // return userRepository.findById(id).orElseThrow(CustomException::new);
    }
    @Override
    public User getByEmail(String email) {
        return userRepository
            .getByEmailNative(email)
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

    @Override
    public Page<User> getByName(String name, Pageable pageable) {
        // return userRepository.findByFullnameContaining(name, pageable);
       // Specification<User> specification = (
           // root,
           // criteriaQuery,
           // criteriaBuilder
       // ) -> {
          //  Predicate finalPredicate;
          //  Predicate namePredicate = criteriaBuilder.like(
            //    root.get("fullname"),
           //     "%" + name + "%"
         //   );
          //  finalPredicate = namePredicate;
           // if (name != null) {
              //  Predicate emailPredicate = criteriaBuilder.like(
                  //  root.get("email"),
                  //  "%" + name + "%"
              //  );
              //  finalPredicate =
                 //   criteriaBuilder.and(finalPredicate, emailPredicate);
          //  }
            //return finalPredicate;
       // };
    	 var session = entityManager.unwrap(Session.class);

         if (name != null) {
             session
                 .enableFilter("nameEmailFilter")
                 .setParameter("email", "%" + name + "%")
                 .setParameter("name", "%" + name + "%");
         }

         var filteredUsers = userRepository.findAll(pageable);
         session.disableFilter("emailFilter");

         return filteredUsers;
     }
 }


