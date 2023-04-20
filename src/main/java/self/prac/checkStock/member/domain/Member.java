package self.prac.checkStock.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import self.prac.checkStock.global.security.Role;
import self.prac.checkStock.order.domain.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "member")
@ToString
@Getter
@NoArgsConstructor
public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private long id;

    private String password;
    private String name;
    private String email;
    private String role;
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

    public void setRole(String role) {
        this.role = role;
    }

    public void modifyMember(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : role.split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == MemberStatus.NORMAL;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == MemberStatus.NORMAL;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == MemberStatus.NORMAL;
    }

    @Override
    public boolean isEnabled() {
        return this.status == MemberStatus.NORMAL;
    }

}

