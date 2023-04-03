package self.prac.checkStock.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.domain.member.Member;
import self.prac.checkStock.domain.order.Order;
import self.prac.checkStock.domain.order.OrderItem;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.domain.order.QOrder.*;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,
    //member, orderItem

    public long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findOne(long id) {
        return jpaQueryFactory.selectFrom(order).where(order.id.eq(id)).fetchOne();
    }

    public List<Order> findAll() {
        return jpaQueryFactory.selectFrom(order).fetch();
    }

    public List<Order> findByMember(Member member) {
        return jpaQueryFactory.selectFrom(order).where(order.member.eq(member)).fetch();
    }

    public List<Order> findByOrderItem(OrderItem orderItem) {
        return jpaQueryFactory.selectFrom(order).where(order.orderItems.contains(orderItem)).fetch();
    }
}
