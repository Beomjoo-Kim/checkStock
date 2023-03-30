package self.prac.checkStock.domain.item;

import lombok.Getter;
import lombok.Setter;
import self.prac.checkStock.domain.order.OrderItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private long itemId;

    private String name;
    private long price;
    private long quantity;
    private String detail;
    private String sellYn;
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    private long itemCategoryId;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderItem> orderItem;
}
