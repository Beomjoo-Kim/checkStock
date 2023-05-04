package self.prac.checkStock.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByItem(Item item);
    List<OrderItem> findByOrder(Order order);
}
