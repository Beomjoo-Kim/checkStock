package self.prac.checkStock.order.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.domain.MemberStatus;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.member.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderRepositoryTestIt {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    @Transactional
    public void save() {
        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPw")
                .name("testMember")
                .phone("testPh")
                .build();

        Order order = new Order();
        order.setMember(member);

        //when
        memberRepository.save(member);
        Order savedOrder = orderRepository.save(order);
        Order searchedOrder = orderRepository.findById(savedOrder.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        //then
        assertThat(searchedOrder).isEqualTo(order);
    }

    @Test
    @Transactional
    public void find() {
        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPw")
                .name("testMember")
                .phone("testPh")
                .role("role")
                .status(MemberStatus.NORMAL)
                .withdrawDate(null)
                .build();
        memberRepository.save(member);

        OrderItem orderItem = OrderItem.builder().build();

        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setMember(member);
        orderRepository.save(order2);

        orderItemRepository.save(orderItem);
        //when
        List<Order> orderList1 = orderRepository.findByMember(member);
        List<Order> orderList2 = orderRepository.findByOrderItems(orderItem);
        List<Order> orderList3 = orderRepository.findAll();

        //then
        assertThat(orderList1).contains(order, order2);
        assertThat(orderList2).contains(order);
        assertThat(orderList3).contains(order, order2);

    }
}
