package self.prac.checkStock.order.domain;

import self.prac.checkStock.item.domain.ItemStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OrderStatus {
    WAITING_DELIVERY("OSC001"),     //정상
    DELIVERING("OSC002"),           //삭제대기
    CANCELED("OSC003"),
    COMPLETE("OSC004"),
    RETURN("OSC005"),
    ;

    private static Map<String, OrderStatus> valueMap =
            Arrays.stream(OrderStatus.values()).collect(Collectors.toMap(OrderStatus::getCode, Function.identity()));

    private String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OrderStatus fromCode(String code) {
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("code is null");
        }
        return valueMap.get(code);
    }
}
