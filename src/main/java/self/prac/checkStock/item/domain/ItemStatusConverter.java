package self.prac.checkStock.item.domain;


import javax.persistence.AttributeConverter;
import java.util.Objects;

public class ItemStatusConverter implements AttributeConverter<ItemStatus, String> {

    @Override
    public String convertToDatabaseColumn(ItemStatus attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public ItemStatus convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return ItemStatus.fromCode(dbData);
    }
}
