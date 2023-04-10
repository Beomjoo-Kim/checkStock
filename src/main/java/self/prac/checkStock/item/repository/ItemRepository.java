package self.prac.checkStock.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.item.domain.Item;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.item.domain.QItem.item;


@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,
    //name, sellYn, quantityZero

    public long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    public Item findOne(long id) {
        return jpaQueryFactory.selectFrom(item).where(item.id.eq(id)).fetchOne();
    }

    public List<Item> findAll() {
        return jpaQueryFactory.selectFrom(item).fetch();
    }

    public List<Item> findByName(String name) {
        return jpaQueryFactory.selectFrom(item).where(item.name.contains(name)).fetch();
    }

    public List<Item> findBySellYn(String sellYn) {
        sellYn = sellYn.toLowerCase();
        return jpaQueryFactory.selectFrom(item).where(item.sellYn.eq(sellYn).or(item.sellYn.eq(sellYn.toUpperCase()))).fetch();
    }

    public List<Item> findZeroQuantity() {
        return jpaQueryFactory.selectFrom(item).where(item.quantity.eq(0L)).fetch();
    }

    public long deleteItem(Item deleteItem) {
        return jpaQueryFactory.delete(item).where(item.id.eq(deleteItem.getId())).execute();
    }
}
