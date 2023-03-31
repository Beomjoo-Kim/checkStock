package self.prac.checkStock.domain.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter
@Setter
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private long id;

    private String email;
    private String password;
    private String name;
}
