package com.example.Springboot_project.Repositories;
import com.example.Springboot_project.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    boolean existsById(long id);
    Optional<User> findByEmail(String Email);
    Optional<User> findByUsername(String Username);

}
