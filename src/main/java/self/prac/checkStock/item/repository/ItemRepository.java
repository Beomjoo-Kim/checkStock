package self.prac.checkStock.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.item.domain.Item;


import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    List<Item> findByNameContains(String name);
    List<Item> findBySellYnIgnoreCase(String sellYn);

//    public List<Item> findZeroQuantity() {
//        return jpaQueryFactory.selectFrom(item).where(item.quantity.eq(0L)).fetch();
//    }
//
//    public long deleteItem(Item deleteItem) {
//        return jpaQueryFactory.delete(item).where(item.id.eq(deleteItem.getId())).execute();
//    }
}
