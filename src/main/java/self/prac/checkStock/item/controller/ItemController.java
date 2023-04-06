package self.prac.checkStock.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.item.service.ItemService;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
}
