package self.prac.checkStock.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.item.dto.RequestItemDto;
import self.prac.checkStock.item.repository.ItemRepository;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.order.repository.OrderItemRepository;
import self.prac.checkStock.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order registOrder(RequestItemDto requestItemDto, Member member) {
        Item item = itemRepository.findOne(requestItemDto.getItemId());

        //TODO : discount logic here later
        long price = item.getPrice();

        OrderItem orderItem = OrderItem.createOrderItem(item, requestItemDto.getQuantity(), price);

        Order order = Order.createOrder(member, orderItem);
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public Order cancelOrder(Order order) {


        return order;
    }

}
