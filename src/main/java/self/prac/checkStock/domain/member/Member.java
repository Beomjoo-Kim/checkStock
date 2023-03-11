package self.prac.checkStock.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
// delete setter after test
@Setter
@ToString
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String name;
    private String email;
    private String phone;
}
