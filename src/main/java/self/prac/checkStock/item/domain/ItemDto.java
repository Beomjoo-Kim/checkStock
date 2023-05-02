package self.prac.checkStock.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import self.prac.checkStock.order.domain.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ItemDto {
    private long id;
    private String name;
    private long price;
    private long quantity;
    private String detail;
    private String sellYn;
    private String reason;
    private Date removeRequestDate;
    private ItemStatus itemStatus;
    private long itemCategoryId;
    private String itemCatregoryNm;
    private List<OrderItem> orderItems = new ArrayList<>();

}
