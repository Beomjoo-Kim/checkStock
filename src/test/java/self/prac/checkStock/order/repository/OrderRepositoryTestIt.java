package self.prac.checkStock.order.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.member.repository.MemberRepository;
import self.prac.checkStock.order.repository.OrderItemRepository;
import self.prac.checkStock.order.repository.OrderRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestConfig.class)
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
        Member member = new Member("testEmail", "testMember", "testPw", "testPh");

        Order order = new Order();
        order.setMember(member);

        //when
        memberRepository.save(member);
        long id = orderRepository.save(order);
        Order searchedOrder = orderRepository.findOne(id);

        //then
        assertThat(searchedOrder).isEqualTo(order);
    }

    @Test
    @Transactional
    public void find() {
        //given
        Member member = new Member("testEmail", "testMember", "testPw", "testPh");
        memberRepository.save(member);

        OrderItem orderItem = new OrderItem();
        orderItemRepository.save(orderItem);

        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        orderRepository.save(order);

        Order order2 = new Order();
        order2.setMember(member);
        orderRepository.save(order2);

        //when
        List<Order> orderList1 = orderRepository.findByMember(member);
        List<Order> orderList2 = orderRepository.findByOrderItem(orderItem);
        List<Order> orderList3 = orderRepository.findAll();

        //then
        assertThat(orderList1).contains(order, order2);
        assertThat(orderList2).contains(order);
        assertThat(orderList3).contains(order, order2);

    }
}
