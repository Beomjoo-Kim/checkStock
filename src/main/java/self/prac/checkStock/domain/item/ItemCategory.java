package self.prac.checkStock.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_category")
@Getter
@Setter
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemCategoryId")
    private long id;
    private String name;

    @OneToOne(mappedBy = "itemCategory")
    private Item item;
}
