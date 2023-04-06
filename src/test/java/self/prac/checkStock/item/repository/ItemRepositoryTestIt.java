package self.prac.checkStock.item.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
public class ItemRepositoryTestIt {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void save() throws Exception {
        //given
        Item item = new Item();
        item.setName("testItem");

        //when
        long id = itemRepository.save(item);
        Item searchedItem = itemRepository.findOne(id);

        //then
        assertThat(item).isEqualTo(searchedItem);
    }

    @Test
    @Transactional
    public void find() {
        //given
        Item item = new Item();
        item.setName("testItem");
        item.setSellYn("Y");
        item.setQuantity(0);

        Item item2 = new Item();
        item2.setName("testItem2");
        item2.setSellYn("N");
        item2.setQuantity(1);

        //when
        long id = itemRepository.save(item);
        Item searchedItem1 = itemRepository.findOne(id);
        List<Item> searchedItemList1 = itemRepository.findAll();
        List<Item> searchedItemList2 = itemRepository.findByName("testItem");
        List<Item> searchedItemList3 = itemRepository.findBySellYn("y");
        List<Item> searchedItemList4 = itemRepository.findZeroQuantity();

        //then
        assertThat(item).isEqualTo(searchedItem1);
        assertThat(item).isEqualTo(searchedItemList1.get(0));
        assertThat(item).isEqualTo(searchedItemList2.get(0));
        assertThat(item).isEqualTo(searchedItemList3.get(0));
        assertThat(item).isEqualTo(searchedItemList4.get(0));
    }

    @Test
    public void addStock() {
        //given
        Item item = new Item();

        //when
        item.addStock(1);

        //then
        assertThat(item.getQuantity()).isEqualTo(1);
    }

    @Test
    public void removeStock() {
        Item item = new Item();
        try {
            item.removeStock(1);
            fail("fail");
        } catch (CustomRuntimeException e) {
            item.addStock(1);
            item.removeStock(1);
            assertThat(item.getQuantity()).isEqualTo(0L);
        }
    }

    @Test
    public void removeItem() {
        //given
        Item item = new Item();
        item.setName("testItem");
        item.setSellYn("Y");
        item.setQuantity(0);
        itemRepository.save(item);

        //when
        itemRepository.deleteItem(item);

        //then
        assertThat(itemRepository.findOne(item.getId())).isNull();
    }

}
