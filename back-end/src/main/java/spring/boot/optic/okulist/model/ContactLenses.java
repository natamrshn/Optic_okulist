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
@SQLDelete(sql = "Update contact_lenses SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "contact_lenses")
public class ContactLenses extends Product {
    private String diopter;
    private String cylinder;
    private String angle;
    private String baseCurve;
    private String lensColor;
    private String adidation;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
