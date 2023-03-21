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
    @Column(name = "item_category_id")
    private long item_category_id;
    private String name;

    @OneToOne
    @JoinColumn(name = "item_category_id")
    private Item item;
}
