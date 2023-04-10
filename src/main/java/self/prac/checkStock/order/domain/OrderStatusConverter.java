package self.prac.checkStock.order.domain;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return OrderStatus.fromCode(dbData);    }
}
