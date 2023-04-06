package self.prac.checkStock.item.domain;

import lombok.Getter;
import lombok.Setter;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private long id;

    private String name;
    private long price;
    private long quantity;
    private String detail;
    private String sellYn;
    private String reason;

    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        long resultQuantity = this.quantity - quantity;
        if (resultQuantity < 0) {
            throw new CustomRuntimeException(CustomErrorCodes.NOT_ENOUGH_STOCK);
        }
        this.quantity = resultQuantity;
    }
}
