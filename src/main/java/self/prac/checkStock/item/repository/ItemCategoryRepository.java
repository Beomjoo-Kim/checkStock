package self.prac.checkStock.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;


import java.util.List;



@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    List<ItemCategory> findByNameContains(String name);
    ItemCategory findByItems(Item item);
}
