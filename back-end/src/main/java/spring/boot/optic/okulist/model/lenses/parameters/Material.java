package spring.boot.optic.okulist.model.lenses.parameters;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "lenses_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id матеріалу
    private String material;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<LensConfigurationModel> lensConfigurations;
}
