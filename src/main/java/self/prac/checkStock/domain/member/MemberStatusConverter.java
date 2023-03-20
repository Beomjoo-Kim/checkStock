package self.prac.checkStock.domain.member;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class MemberStatusConverter implements AttributeConverter<MemberStatus, String> {

    @Override
    public String convertToDatabaseColumn(MemberStatus attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public MemberStatus convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        return MemberStatus.fromCode(dbData);
    }
}
