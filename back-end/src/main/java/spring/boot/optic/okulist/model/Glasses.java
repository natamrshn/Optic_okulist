package spring.boot.optic.okulist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SQLDelete(sql = "Update glasses SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "glasses")
public class Glasses extends Product {
    private String color;
    private String model;
    private String manufacturer;
    private String productType = "Glasses";
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
