package spring.boot.optic.okulist.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "Update orders SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "orders")
public class Order {
    public enum Status {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        REFUNDED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "total", nullable = false)
    private BigDecimal total;
    @Column(name = "order_date",nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // before lazy
    private Set<OrderItem> orderItems;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Override
    public int hashCode() {
        int hash = 17;
        if (getId() != null) {
            hash = 31 * hash + getId().hashCode();
        }
        if (getUser() != null) {
            hash = 31 * hash + getUser().getId().hashCode();
        }
        if (getStatus() != null) {
            hash = 31 * hash + getStatus().hashCode();
        }
        if (getTotal() != null) {
            hash = 31 * hash + getTotal().hashCode();
        }
        if (getOrderDate() != null) {
            hash = 31 * hash + getOrderDate().hashCode();
        }
        if (getShippingAddress() != null) {
            hash = 31 * hash + getShippingAddress().hashCode();
        }
        return hash;
    }
}
