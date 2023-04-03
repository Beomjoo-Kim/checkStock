package self.prac.checkStock.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.domain.item.Item;
import self.prac.checkStock.domain.item.ItemCategory;

import javax.persistence.EntityManager;

import java.util.List;

import static self.prac.checkStock.domain.item.QItemCategory.itemCategory;

@Repository
@RequiredArgsConstructor
public class ItemCategoryRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    //save, findOne, findAll, findBy~,

    public void save(ItemCategory itemCategory) {
        em.persist(itemCategory);
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
