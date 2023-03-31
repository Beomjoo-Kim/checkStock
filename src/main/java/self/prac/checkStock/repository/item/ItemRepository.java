package self.prac.checkStock.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import self.prac.checkStock.domain.item.Item;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.domain.item.QItem.*;

public class ItemRepository {
    private EntityManager em;
    private JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,
    //name, sellYn, quantityZero
    public void save(Item item) {
        em.persist(item);
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
}
