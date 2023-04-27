package self.prac.checkStock.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.dto.RegistItemDto;
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

    public void registItem(@RequestBody RegistItemDto item) {
        //TODO : NEED TEST
        if (itemCategoryRepository.findOne(item.getItemCategory().getId()) == null) {
            throw new CustomRuntimeException(CustomErrorCodes.NO_ITEM_CATEGORY);
        }
        itemService.registItem(item);
    }

}
