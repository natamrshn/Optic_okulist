package spring.boot.optic.okulist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.TemporaryUser;

@Repository
public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Long> {
}
