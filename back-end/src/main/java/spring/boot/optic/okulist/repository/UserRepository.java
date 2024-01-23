package spring.boot.optic.okulist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.RegisteredUser;
import spring.boot.optic.okulist.model.Role;
import spring.boot.optic.okulist.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<RegisteredUser> findByEmail(String email);

    List<RegisteredUser> findUsersByRolesContainingAndIsDeletedFalse(Role role);

    @Query("SELECT u.email, u.firstName, u.lastName, u.phoneNumber FROM User u "
            + "WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findDetailsByEmail(@Param("email") String email);
    }