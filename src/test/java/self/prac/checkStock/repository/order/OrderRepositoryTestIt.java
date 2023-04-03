package self.prac.checkStock.repository.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.domain.member.Member;
import self.prac.checkStock.domain.order.Order;
import self.prac.checkStock.domain.order.OrderItem;
import self.prac.checkStock.repository.member.MemberRepository;

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
        Member member = new Member();
        member.setName("testMember");

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
        Member member = new Member();
        member.setName("testMember");
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
