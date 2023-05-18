package self.prac.checkStock.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.jwt.JwtUtil;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.domain.ItemCategory;
import self.prac.checkStock.item.domain.ItemDto;
import self.prac.checkStock.item.domain.RemovedItem;
import self.prac.checkStock.item.dto.RegisterItemDto;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.repository.ItemCategoryRepository;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.item.repository.RemovedItemRepository;
import self.prac.checkStock.item.service.ItemService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.service.MemberService;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderDto;
import self.prac.checkStock.order.domain.OrderStatus;
import self.prac.checkStock.order.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final RemovedItemRepository removedItemRepository;
    private final OrderService orderService;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @GetMapping("/all")
    public List<Item> getItemList(){
        return itemRepository.findAll();
    }



    @PostMapping("/register/item")
    public ItemDto registerItem(@RequestBody RegisterItemDto item) {
        // itemCategory 와 item 은 서로 참조하고 있기 때문에 item 을 그대로 반환하게 되면 순환참조가 발생하여 오류가 발생한다.
        // 이를 방지/해결 하기 위해 dto 를 사용한다.
        if (item.getItemCategory() == null) throw new IllegalArgumentException("물품분류 입력 필요");
        Item registeredItem = itemService.registerItem(item);

        ItemDto itemDto = ItemDto.builder()
                .itemStatus(registeredItem.getItemStatus())
                .itemCategoryId(registeredItem.getItemCategory().getId())
                .itemCategoryNm(registeredItem.getItemCategory().getName())
                .id(registeredItem.getId())
                .name(registeredItem.getName())
                .detail(registeredItem.getDetail())
                .price(registeredItem.getPrice())
                .quantity(registeredItem.getQuantity())
                .reason(registeredItem.getReason())
                .removeRequestDate(registeredItem.getRemoveRequestDate())
                .sellYn(registeredItem.getSellYn())
                .build();

        return itemDto;
    }

    @PostMapping("/register/category")
    public ItemCategory registerItemCategory(@RequestBody ItemCategory itemCategory) {
        if (itemCategory == null) throw new IllegalArgumentException("물품분류 입력 필요");
        ItemCategory registeredItemCategory = itemService.registerItemCategory(itemCategory);
        return registeredItemCategory;
    }

    @PostMapping("/order")
    public OrderDto orderItem(@RequestBody RequestItemDto requestItemDto,
                              @RequestHeader(value = "Authorization") String rawToken) {
        String token = jwtUtil.extractToken(rawToken);
        String userEmail = jwtUtil.extractUserEmail(token);
        Member member = (Member) memberService.loadUserByUsername(userEmail);
        Order order = orderService.registerOrder(requestItemDto, member);

        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .memberId(member.getId())
                .orderStatus(order.getOrderStatus())
                .build();

        return orderDto;
    }

    @PostMapping("/remove")
    public void removeItem(@RequestBody ItemDto itemDto) {
        itemService.requestRemoveItem(itemDto.getId(), itemDto.getReason());
    }

    @GetMapping("/remove")
    public List<RemovedItem> getRemovedItemList() {
        return removedItemRepository.findAll();
    }
}
