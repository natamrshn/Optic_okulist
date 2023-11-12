package spring.boot.optic.okulist.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.Liquid;

import java.util.List;

@Repository
public interface LiquidRepository extends JpaRepository<Liquid, Long> {
    List<Liquid> findAll(Specification<Liquid> specification);
}
