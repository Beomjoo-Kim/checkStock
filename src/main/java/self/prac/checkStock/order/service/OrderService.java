package self.prac.checkStock.order.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.order.domain.OrderStatus;
import self.prac.checkStock.order.repository.OrderItemRepository;
import self.prac.checkStock.order.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order registerOrder(RequestItemDto requestItemDto, Member member) {
        Item item = itemRepository.findById(requestItemDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("no item found"));
        item.removeStock(requestItemDto.getQuantity());

        //TODO : discount logic here later
        long price = item.getPrice();

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .quantity(requestItemDto.getQuantity())
                .price(price).build();
        orderItemRepository.save(orderItem);

        Order order = Order.createOrder(member, orderItem);
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public Order cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("no order found"));
        order.setOrderStatus(OrderStatus.CANCELED);
        return order;
    }

    public List<Order> findOrderByStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }
}
