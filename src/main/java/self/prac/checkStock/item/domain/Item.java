package self.prac.checkStock.item.domain;

import lombok.Getter;
import lombok.Setter;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private Date removeRequestDate;

    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemCategoryId")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
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

    public void requestRemove(String reason){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        this.removeRequestDate = calendar.getTime();
        this.reason = reason;
    }

    public boolean isRemoveRequestDateConfirmed(){
        // 삭제 요청 일과 같거나 지났을 경우 true
        Calendar today = Calendar.getInstance();
        if (this.getRemoveRequestDate().compareTo(today.getTime())<=0) {
            return true;
        }else return false;
    }
}
