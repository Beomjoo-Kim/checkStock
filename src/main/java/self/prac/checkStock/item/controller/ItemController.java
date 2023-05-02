package self.prac.checkStock.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.global.utils.JwtUtil;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.domain.ItemDto;
import self.prac.checkStock.item.dto.RegisterItemDto;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.repository.ItemCategoryRepository;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.item.service.ItemService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final OrderService orderService;

    @PostMapping("/register/item")
    public ResponseEntity<ItemDto> registerItem(@RequestBody RegisterItemDto item) {
        // itemCategory 와 item 은 서로 참조하고 있기 때문에 item 을 그대로 반환하게 되면 순환참조가 발생하여 오류가 발생한다.
        // 이를 방지/해결 하기 위해 dto 를 사용한다.
        if (item.getItemCategory() == null) throw new IllegalArgumentException("물품분류 입력 필요");
        Item registeredItem = itemService.registerItem(item);

        ItemDto itemDto = ItemDto.builder()
                .itemStatus(registeredItem.getItemStatus())
                .itemCategoryId(registeredItem.getItemCategory().getId())
                .itemCatregoryNm(registeredItem.getItemCategory().getName())
                .id(registeredItem.getId())
                .name(registeredItem.getName())
                .detail(registeredItem.getDetail())
                .price(registeredItem.getPrice())
                .quantity(registeredItem.getQuantity())
                .reason(registeredItem.getReason())
                .removeRequestDate(registeredItem.getRemoveRequestDate())
                .sellYn(registeredItem.getSellYn())
                .build();

        return ResponseEntity.ok(itemDto);
    }

    @PostMapping("/register/category")
    public ResponseEntity<ItemCategory> registerItemCategory(@RequestBody ItemCategory itemCategory) {
        if(itemCategory == null) throw new IllegalArgumentException("물품분류 입력 필요");
        ItemCategory registeredItemCategory = itemService.registerItemCategory(itemCategory);
        return ResponseEntity.ok(registeredItemCategory);
    }

    @PostMapping
    public ResponseEntity<Order> orderItem(@RequestBody RequestItemDto requestItemDto, @AuthenticationPrincipal Member member) {
        Order order = orderService.registerOrder(requestItemDto, member);
        return ResponseEntity.ok(order);
    }
}
