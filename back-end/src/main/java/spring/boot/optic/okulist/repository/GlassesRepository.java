package spring.boot.optic.okulist.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.Glasses;

import java.util.List;

@Repository
public interface GlassesRepository extends JpaRepository<Glasses, Long> {
    List<Glasses> findAll(Specification<Glasses> specification);

    List<Glasses> findByModelAndIsDeletedFalse(String model);

    List<Glasses> findByColorIgnoreCaseAndNameAndPriceAndIdentifierAndDescription(
            String color, String name, double price, String identifier, String description
    );
}
