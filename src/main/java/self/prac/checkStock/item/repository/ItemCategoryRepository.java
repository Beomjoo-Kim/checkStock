package self.prac.checkStock.item.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.domain.QItemCategory;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.item.domain.QItemCategory.itemCategory;


@Repository
@RequiredArgsConstructor
public class ItemCategoryRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,

    public long save(ItemCategory itemCategory) {
        em.persist(itemCategory);
        return itemCategory.getId();
    }

    public ItemCategory findOne(long id) {
        return jpaQueryFactory.selectFrom(itemCategory).where(itemCategory.id.eq(id)).fetchOne();
    }

    public List<ItemCategory> findAll() {
        return jpaQueryFactory.selectFrom(itemCategory).fetch();
    }

    public List<ItemCategory> findByName(String name) {
        return jpaQueryFactory.selectFrom(itemCategory).where(itemCategory.name.contains(name)).fetch();
    }

    public ItemCategory findByItem(Item item) {
        return jpaQueryFactory.selectFrom(itemCategory).where(itemCategory.item.eq(item)).fetchOne();
    }
}
