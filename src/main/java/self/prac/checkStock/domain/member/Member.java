package self.prac.checkStock.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import self.prac.checkStock.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@ToString
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private long id;

    private String password;
    private String name;
    private String email;
    private String phone;
    @Convert(converter = MemberStatusConverter.class)
    private MemberStatus status;
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public Member(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public void modifyMember(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
}

