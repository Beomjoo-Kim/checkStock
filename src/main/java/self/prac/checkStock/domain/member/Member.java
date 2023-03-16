package self.prac.checkStock.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
// delete setter after test
@Setter
// delete setter after test
@ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    private String password;
    private String name;
    private String email;
    private String phone;
    private MemberStatus status;
}

