package self.prac.checkStock.member.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MemberStatus {

    NORMAL("USC001"),       //정상
    BANNED("USC002"),       //추방
    WITHDRAW("USC003");     //탈퇴

    private static Map<String, MemberStatus> valueMap =
            Arrays.stream(MemberStatus.values()).collect(Collectors.toMap(MemberStatus::getCode, Function.identity()));

    private String code;

    MemberStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MemberStatus fromCode(String code) {
        if (Objects.isNull(code)) {
            throw new IllegalArgumentException("code is null");
        }
        return valueMap.get(code);
    }
}
