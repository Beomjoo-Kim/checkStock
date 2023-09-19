package self.prac.checkStock.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import self.prac.checkStock.order.domain.Order;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "member")
@ToString
@Getter
@Builder
public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String password;
    private String name;
    private String email;
    private String role;
    private String phone;
    @Nullable
    private Date withdrawDate;
    @Convert(converter = MemberStatusConverter.class)
    private MemberStatus status;
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setWithdrawDate(Date date) {
        this.withdrawDate = date;
    }

    public void modifyMember(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public void requestWithdraw() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        this.withdrawDate = calendar.getTime();
        this.status = MemberStatus.WITHDRAW;
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

