package spring.boot.optic.okulist.repository.lenses;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.optic.okulist.model.lenses.parameters.LensConfigurationModel;

public interface LensConfigurationModelRepository
        extends JpaRepository<LensConfigurationModel, Long> {
}
