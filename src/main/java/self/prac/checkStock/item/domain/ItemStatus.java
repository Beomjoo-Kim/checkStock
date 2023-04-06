package self.prac.checkStock.item.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ItemStatus {
    NORMAL("ISC001"),           //정상
    WAITING_REMOVAL("ISC002"),   //삭제대기
    ;

    private static Map<String, ItemStatus> valueMap =
            Arrays.stream(ItemStatus.values()).collect(Collectors.toMap(ItemStatus::getCode, Function.identity()));

    private String code;

    ItemStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ItemStatus fromCode(String code) {
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("code is null");
        }
        return valueMap.get(code);
    }
}
