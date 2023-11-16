package spring.boot.optic.okulist.repository.lenses.paramsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
