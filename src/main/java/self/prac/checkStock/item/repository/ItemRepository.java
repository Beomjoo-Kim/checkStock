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

}
