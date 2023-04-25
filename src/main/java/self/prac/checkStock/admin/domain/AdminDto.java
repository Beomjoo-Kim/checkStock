package self.prac.checkStock.admin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminDto {
    private Long id;
    private String email;
    private String name;
    private String role;

    public AdminDto(Long id, String name, String email, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
