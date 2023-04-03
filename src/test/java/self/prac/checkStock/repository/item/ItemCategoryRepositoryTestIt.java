package self.prac.checkStock.repository.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.domain.item.Item;
import self.prac.checkStock.domain.item.ItemCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestConfig.class)
public class ItemCategoryRepositoryTestIt {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void save() throws Exception {
        //given
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName("testItemCategory");

        //when
        long id = itemCategoryRepository.save(itemCategory);
        ItemCategory searchedItemCategory = itemCategoryRepository.findOne(id);

        //then
        assertThat(itemCategory).isEqualTo(searchedItemCategory);
    }

    @Test
    @Transactional
    public void find() {
        //given
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName("testItemCategory");
        ItemCategory itemCategory2 = new ItemCategory();
        itemCategory2.setName("testItemCategory2");

        Item item = new Item();
        item.setItemCategory(itemCategory);
        itemCategory.setItem(item);

        //when
        itemRepository.save(item);
        itemCategoryRepository.save(itemCategory);
        itemCategoryRepository.save(itemCategory2);
        List<ItemCategory> searchedItemCategoryList1 = itemCategoryRepository.findByName("testItemCategory");
        List<ItemCategory> searchedItemCategoryList2 = itemCategoryRepository.findAll();
        List<ItemCategory> searchedItemCategoryList3 = itemCategoryRepository.findByName("testItemCategory2");
        ItemCategory searchedItemCategory = itemCategoryRepository.findByItem(item);

        //then
        assertThat(searchedItemCategoryList1).contains(itemCategory, itemCategory2);
        assertThat(searchedItemCategoryList2).contains(itemCategory, itemCategory2);
        assertThat(searchedItemCategoryList3).contains(itemCategory2);
        assertThat(itemCategory).isEqualTo(searchedItemCategory);
    }
}
