package self.prac.checkStock.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "removed_item")
@Getter
@Builder
public class RemovedItem {
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemCategoryId")
    private ItemCategory itemCategory;

}
