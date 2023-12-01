package spring.boot.optic.okulist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.optic.okulist.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.id = :order_id"
            + " AND EXISTS (SELECT oi FROM o.orderItems oi WHERE oi.id = :order_item_id)")
    Optional<Order> findByOrderIdAndOrderItemsId(@Param("order_id") Long orderId,
                                                 @Param("order_item_id") Long orderItemsId);

    List<Order> findAllByUserEmail(String email);

}
