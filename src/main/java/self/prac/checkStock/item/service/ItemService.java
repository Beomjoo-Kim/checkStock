package self.prac.checkStock.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemStatus;
import self.prac.checkStock.item.dto.RegistItemDto;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.dto.UpdateItemDto;
import self.prac.checkStock.item.repository.ItemRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
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

    @Transactional
    public Item updateItem(long itemId, UpdateItemDto updateItemDto) {
        Item updatedItem = itemRepository.findOne(itemId);
        updatedItem.setName(updateItemDto.getName());
        updatedItem.setPrice(updateItemDto.getPrice());
        updatedItem.setQuantity(updateItemDto.getQuantity());
        updatedItem.setItemCategory(updateItemDto.getItemCategory());

        return updatedItem;
    }

    @Transactional
    public Item requestItemRemove(long itemId, String reason) {
        Item requestedItem = itemRepository.findOne(itemId);
        requestedItem.setItemStatus(ItemStatus.WAITING_REMOVAL);
        requestedItem.setReason(reason);
        requestedItem.setRemoveRequestDate(new Date());
        return requestedItem;
    }

    @Transactional
    //delete / count
    public long removeItem(Item item) {
        return itemRepository.deleteItem(item);
    }

    public void checkQuantity(RequestItemDto requestItemDto) {
        Item requestedItem = itemRepository.findOne(requestItemDto.getItemId());
        if (requestedItem.getQuantity() < requestItemDto.getQuantity()) {
            throw new CustomRuntimeException(CustomErrorCodes.NOT_ENOUGH_STOCK);
        }
    }

}
