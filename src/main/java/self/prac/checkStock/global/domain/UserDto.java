package self.prac.checkStock.global.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import self.prac.checkStock.global.jwt.RefreshToken;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String role;

    public UserDto(Long id, String name, String email, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public UserDto(RefreshToken refreshToken) {
        this.id = refreshToken.getId();
        this.email = refreshToken.getEmail();
        this.name = refreshToken.getName();
        this.role = refreshToken.getRole();
    }
}
