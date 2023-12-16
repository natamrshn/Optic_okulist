package spring.boot.optic.okulist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "temporary_users")
public class TemporaryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String shoppingAddress;
    @Getter
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> product;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    private BigDecimal total;

    public void setProducts(List<Product> products) {
        this.product = products;
    }
}
