package self.prac.checkStock.domain.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private long memberId;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderItem> orderItem;
}
