package self.prac.checkStock.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestItemDto {
    long itemId;
    long quantity;

}
