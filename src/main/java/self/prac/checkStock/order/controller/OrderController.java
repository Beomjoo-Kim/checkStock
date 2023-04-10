package self.prac.checkStock.order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.service.ItemService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final ItemService itemService;
    private final OrderService orderService;

    @PostMapping("/request/{itemId}")
    public ResponseEntity<Order> requestOrder(@RequestBody RequestItemDto requestItemDto, HttpServletRequest request) {
        Member loginMember = (Member)request.getSession().getAttribute("loginMember");

        itemService.checkQuantity(requestItemDto);
        Order order = orderService.registOrder(requestItemDto, loginMember);

        return ResponseEntity.ok(order);
    }
}
