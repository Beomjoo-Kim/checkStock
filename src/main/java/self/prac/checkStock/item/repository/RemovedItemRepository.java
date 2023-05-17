package self.prac.checkStock.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import self.prac.checkStock.item.domain.RemovedItem;

public interface RemovedItemRepository extends JpaRepository<RemovedItem, Long> {
}
