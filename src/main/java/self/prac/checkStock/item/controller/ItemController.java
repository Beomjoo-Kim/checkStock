package self.prac.checkStock.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.dto.RegisterItemDto;
import self.prac.checkStock.item.repository.ItemCategoryRepository;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.item.service.ItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    @PostMapping("/register/item")
    public ResponseEntity<Item> registerItem(@RequestBody RegisterItemDto item) {
        //TODO : NEED TEST
        if (item.getItemCategory() == null) throw new IllegalArgumentException("물품분류 입력 필요");
        Item registeredItem = itemService.registerItem(item);
        return ResponseEntity.ok(registeredItem);
    }

    @PostMapping("/register/category")
    public ResponseEntity<ItemCategory> registerItemCategory(@RequestBody ItemCategory itemCategory) {
        if(itemCategory == null) throw new IllegalArgumentException("물품분류 입력 필요");
        ItemCategory registeredItemCategory = itemService.registerItemCategory(itemCategory);
        return ResponseEntity.ok(registeredItemCategory);
    }
}
