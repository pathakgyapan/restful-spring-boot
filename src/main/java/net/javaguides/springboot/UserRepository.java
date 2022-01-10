package net.javaguides.springboot;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
//user repository will now be able to save users, and find single ones by id and so on..
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByFullname(String fullName);
}
