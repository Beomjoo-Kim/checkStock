package self.prac.checkStock.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import self.prac.checkStock.item.domain.ItemCategory;

@Getter
@AllArgsConstructor
public class UpdateItemDto {
    private String name;
    private long price;
    private long quantity;
    private String detail;
    private String sellYn;
    private String reason;
    private ItemCategory itemCategory;

}
