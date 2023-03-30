package self.prac.checkStock.domain.order;

import lombok.Getter;
import lombok.Setter;

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
    private long itemId;
    @ManyToOne(fetch = FetchType.LAZY)
    private long orderId;
}
