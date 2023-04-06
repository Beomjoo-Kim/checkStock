package self.prac.checkStock.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.domain.order.QOrderItem.*;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,
    //item, order

    public long save(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem.getId();
    }

    public OrderItem findOne(long id) {
        return jpaQueryFactory.selectFrom(orderItem).where(orderItem.id.eq(id)).fetchOne();
    }

    public List<OrderItem> findAll() {
        return jpaQueryFactory.selectFrom(orderItem).fetch();
    }

    public List<OrderItem> findByItem(Item item) {
        return jpaQueryFactory.selectFrom(orderItem).where(orderItem.item.eq(item)).fetch();
    }

    public List<OrderItem> findByOrder(Order order) {
        return jpaQueryFactory.selectFrom(orderItem).where(orderItem.order.eq(order)).fetch();
    }
}
