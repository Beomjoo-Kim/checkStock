package self.prac.checkStock.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemStatus;
import self.prac.checkStock.item.dto.RegistItemDto;
import self.prac.checkStock.item.dto.UpdateItemDto;
import self.prac.checkStock.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item registItem(RegistItemDto registItemDto) {
        Item item = new Item();
        item.setName(registItemDto.getName());
        item.setPrice(registItemDto.getPrice());
        item.setQuantity(registItemDto.getQuantity());
        item.setSellYn(registItemDto.getSellYn());
        item.setItemCategory(registItemDto.getItemCategory());
        item.setItemStatus(ItemStatus.NORMAL);

        long id = itemRepository.save(item);
        return item;
    }

    public Item updateItem(long itemId, UpdateItemDto updateItemDto) {
        Item updatedItem = itemRepository.findOne(itemId);
        updatedItem.setName(updateItemDto.getName());
        updatedItem.setPrice(updateItemDto.getPrice());
        updatedItem.setQuantity(updateItemDto.getQuantity());
        updatedItem.setItemCategory(updateItemDto.getItemCategory());

        return updatedItem;
    }

    public Item requestItemRemove(long itemId) {
        Item requestedItem = itemRepository.findOne(itemId);
        requestedItem.setItemStatus(ItemStatus.WAITING_REMOVAL);
        return requestedItem;
    }

    //delete / count
    public long removeItem(Item item) {
        return itemRepository.deleteItem(item);
    }

}
