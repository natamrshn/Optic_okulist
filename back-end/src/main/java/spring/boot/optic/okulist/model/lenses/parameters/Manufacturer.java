package spring.boot.optic.okulist.model.lenses.parameters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@SQLDelete(sql = "Update manufacturers SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cylinder_id")
    private Cylinder cylinder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diopter_id")
    private Diopter diopter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sphere_id")
    private Sphere sphere;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
