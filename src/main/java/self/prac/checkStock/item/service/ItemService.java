package self.prac.checkStock.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.domain.ItemStatus;
import self.prac.checkStock.item.dto.RegisterItemDto;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.dto.UpdateItemDto;
import self.prac.checkStock.item.repository.ItemCategoryRepository;
import self.prac.checkStock.item.repository.ItemRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    @Transactional
    public Item registerItem(RegisterItemDto registerItemDto) {
        List<ItemCategory> searchedItemCategoryList = itemCategoryRepository.findByNameContains(registerItemDto.getItemCategory().getName());
        if (searchedItemCategoryList == null || searchedItemCategoryList.size() != 1) {
            throw new IllegalArgumentException("물품 분류 필요");
        }

        Item item = new Item();
        item.setName(registerItemDto.getName());
        item.setPrice(registerItemDto.getPrice());
        item.setQuantity(registerItemDto.getQuantity());
        item.setSellYn(registerItemDto.getSellYn());
        item.setItemCategory(searchedItemCategoryList.get(0));
        item.setItemStatus(ItemStatus.NORMAL);

        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(long itemId, UpdateItemDto updateItemDto) {
        Item updatedItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("no item found"));
        updatedItem.setName(updateItemDto.getName());
        updatedItem.setPrice(updateItemDto.getPrice());
        updatedItem.setQuantity(updateItemDto.getQuantity());
        updatedItem.setItemCategory(updateItemDto.getItemCategory());

        return updatedItem;
    }

    @Transactional
    public Item requestItemRemove(long itemId, String reason) {
        Item requestedItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("no item found"));
        requestedItem.setItemStatus(ItemStatus.WAITING_REMOVAL);
        requestedItem.setReason(reason);
        requestedItem.setRemoveRequestDate(new Date());
        return requestedItem;
    }

    @Transactional
    //delete / count
    public void removeItem(Item item) {
        itemRepository.delete(item);
    }

    public void checkQuantity(RequestItemDto requestItemDto) {
        Item requestedItem = itemRepository.findById(requestItemDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("no item found"));
        if (requestedItem.getQuantity() < requestItemDto.getQuantity()) {
            throw new CustomRuntimeException(CustomErrorCodes.NOT_ENOUGH_STOCK);
        }
    }


    @Transactional
    public ItemCategory registerItemCategory(ItemCategory itemCategory) {
        List<ItemCategory> searchedItemCategoryList = itemCategoryRepository.findByNameContains(itemCategory.getName());
        if (searchedItemCategoryList != null && searchedItemCategoryList.size() > 0) {
            throw new IllegalArgumentException("이미 존재하는 물품 분류");
        }
        itemCategoryRepository.save(itemCategory);
        return itemCategory;
    }

}
