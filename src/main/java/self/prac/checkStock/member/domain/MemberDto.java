package self.prac.checkStock.member.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String name;

    public MemberDto(Long id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
