package self.prac.checkStock.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import self.prac.checkStock.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
// delete setter after test
@Setter
// delete setter after test
@ToString
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
}

