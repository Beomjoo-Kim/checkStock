package self.prac.checkStock.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import self.prac.checkStock.item.domain.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId")
    private long id;

    private long quantity;
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
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
    public static OrderItem createOrderItem(Item item, long quantity, long price) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .quantity(quantity)
                .price(price).build();
        return orderItem;
    }
}
