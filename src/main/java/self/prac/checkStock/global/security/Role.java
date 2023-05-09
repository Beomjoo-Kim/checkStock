package self.prac.checkStock.global.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER");

    private String value;
}
