package self.prac.checkStock.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    private String name;
    private long price;
    private long quantity;
    private String detail;
    private String sellYn;
    private String reason;

    @OneToOne
    @Column(name = "item_category_id")
    private long item_category_id;
}
