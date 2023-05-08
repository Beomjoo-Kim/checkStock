package self.prac.checkStock.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestItemDto {
    long itemId;
    int quantity;

}
