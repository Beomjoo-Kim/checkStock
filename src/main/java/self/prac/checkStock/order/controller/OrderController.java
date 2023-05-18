package self.prac.checkStock.order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.jwt.JwtUtil;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.service.ItemService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.service.MemberService;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderStatus;
import self.prac.checkStock.order.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final ItemService itemService;
    private final OrderService orderService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @GetMapping("/all")
    public void findOrders(@RequestHeader(value = "Authorization") String rawToken) {
        String token = jwtUtil.extractToken(rawToken);
        String userEmail = jwtUtil.extractUserEmail(token);
        Member member = (Member) memberService.loadUserByUsername(userEmail);
    }

    @GetMapping("/status")
    public List<Order> getItemListByStatus(@RequestParam String statusCode) {
        OrderStatus orderStatus = OrderStatus.fromCode(statusCode);
        return orderService.findOrderByStatus(orderStatus);
    }
}
