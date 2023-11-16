package spring.boot.optic.okulist.repository.lenses.paramsrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;

public interface CylinderRepository extends JpaRepository<Cylinder,Long> {
}
