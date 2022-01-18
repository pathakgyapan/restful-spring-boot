package net.javaguides.springboot;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository
    extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    List<User> findByFullnameLike(String fullName);

    Page<User> findByFullnameContaining(String fullName, Pageable pageable);

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

    @Query(
        value = "SELECT * FROM user u WHERE u.email = ?1",
        nativeQuery = true
    )
    Optional<User> getByEmailNative(String email);

    @Query(
        value = "SELECT * FROM user u LEFT JOIN user_detail ud ON u.user_detail_id = ud.id  WHERE ud.experience = :experience",
        nativeQuery = true
    )
    Optional<User> getByExperienceNative(
        @Param(value = "experience") String experience
    );
}
