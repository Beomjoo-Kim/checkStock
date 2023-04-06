package self.prac.checkStock.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import self.prac.checkStock.item.domain.ItemCategory;

@Getter
@AllArgsConstructor
public class RegistItemDto {
    private String name;
    private long price;
    private long quantity;
    private String sellYn;
    private ItemCategory itemCategory;
}
