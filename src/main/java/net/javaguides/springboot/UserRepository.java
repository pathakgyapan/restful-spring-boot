package net.javaguides.springboot;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//user repository will now be able to save users, and find single ones by id and so on..
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByFullname(String fullName);
    //fullname: user ko attribute hunuparcha
    //findByFullnameLIKE
    //findByFullnameCONTAINING
    //WildCard Characters
    
    List<User> findByFullnameContaining(String fullName);

    List<User> findByFullnameContainingOrderByIdDesc(String fullName);

    List<User> findByFullnameStartingWith(String fullName);

    List<User> findByFullnameEndingWith(String fullName);

    // List<User> findByActiveTrue(String fullName);

    List<User> findByFullnameContainingOrEmailEndingWith(
        String fullName,
        String email
    );

    List<User> findByIdLessThanEqual(String fullName);

    List<User> findByIdGreaterThanEqual(String fullName);

    // List<User> findByCreatedDateAfter(Date afterDate);

    // List<User> findByCreatedDateBefore(Date beforeDate);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> getByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userDetail.experience = :experience")
    Optional<User> getByExperience(
        @Param(value = "experience") String experience
    );
}


