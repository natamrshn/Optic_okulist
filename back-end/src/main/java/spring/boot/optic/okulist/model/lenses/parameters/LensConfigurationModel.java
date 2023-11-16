package spring.boot.optic.okulist.model.lenses.parameters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "Update lenses_config_model SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "lenses_config_model")
public class LensConfigurationModel {
    /*
    цей клас потрібен для створення конфігурацій які можна буде реюзати для
    створення нових товарів
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String configurationName;
    @ManyToOne
    private Manufacturer manufacturer;
    @ManyToOne
    private Color color;
    @ManyToOne
    private Cylinder cylinder;
    @ManyToOne
    private Degree degree;
    @ManyToOne
    private Diopter diopter;
    @ManyToOne
    private Material material;
    @ManyToOne
    private Sphere sphere;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}


