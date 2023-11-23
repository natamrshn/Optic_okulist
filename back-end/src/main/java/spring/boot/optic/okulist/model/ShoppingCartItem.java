package spring.boot.optic.okulist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import spring.boot.optic.okulist.model.lenses.ContactLenses;

@Entity
@Data
@Getter
@Setter
@SQLDelete(sql = "UPDATE cart_items SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "cart_items")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shopping_carts_id", nullable = false)
    private ShoppingCart shoppingCart;
    @OneToOne
    @JoinColumn(name = "glasses_id", nullable = false)
    private Glasses glasses;
    @OneToOne
    @JoinColumn(name = "contact_Lenses_id", nullable = false)
    private ContactLenses contactLenses;
    @OneToOne
    @JoinColumn(name = "liquid_id", nullable = false)
    private Liquid liquid;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
