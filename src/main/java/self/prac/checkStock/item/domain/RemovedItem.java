package self.prac.checkStock.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "removed_item")
@Getter
@Builder
public class RemovedItem {
    @Id
    private Long id;
    private String name;
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemCategoryId")
    private ItemCategory itemCategory;

}
