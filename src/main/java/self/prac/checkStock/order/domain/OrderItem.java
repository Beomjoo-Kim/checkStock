package self.prac.checkStock.order.domain;

import lombok.Getter;
import lombok.Setter;
import self.prac.checkStock.item.domain.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId")
    private long id;

    private long quantity;
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    //==association method
    public void setItem(Item item) {
        this.item = item;
        item.getOrderItems().add(this);
    }

    public void setOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }

    //==creation method
    public OrderItem createOrderItem(Item item, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrder(order);
        return orderItem;
    }
}
