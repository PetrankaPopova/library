package library.repository;

import library.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    Optional<User> findByEmail(String email);
}
