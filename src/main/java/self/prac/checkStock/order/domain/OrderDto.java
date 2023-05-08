package self.prac.checkStock.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderDto {
    private Long id;
    private Long memberId;
    private OrderStatus orderStatus;
}
