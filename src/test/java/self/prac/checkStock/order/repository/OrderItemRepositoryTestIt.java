package self.prac.checkStock.order.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.item.domain.Item;
import self.prac.checkStock.order.domain.Order;
import self.prac.checkStock.order.domain.OrderItem;
import self.prac.checkStock.item.repository.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderItemRepositoryTestIt {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;


    @Test
    @Transactional
    public void save() throws Exception {
        //given
        Item item = new Item();
        item.setName("testItem");

        OrderItem orderItem = OrderItem.builder().item(item).build();

        //when
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        itemRepository.save(item);
        OrderItem searchedOrderItem = orderItemRepository.findById(savedOrderItem.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        //then
        assertThat(searchedOrderItem).isEqualTo(orderItem);
    }

    @Test
    @Transactional
    public void find() {
        //given
        Item item = new Item();
        item.setName("testItem");
        itemRepository.save(item);
        Item item2 = new Item();
        item2.setName("testItem2");
        itemRepository.save(item2);

        OrderItem orderItem = OrderItem.builder().item(item).build();
        orderItemRepository.save(orderItem);
        OrderItem orderItem2 = OrderItem.builder().item(item2).build();
        orderItemRepository.save(orderItem2);

        Order order = new Order();
        order.addOrderItem(orderItem);
        order.addOrderItem(orderItem2);
        orderRepository.save(order);

        //when
        List<OrderItem> orderItemList1 = orderItemRepository.findByItem(item);
        List<OrderItem> orderItemList2 = orderItemRepository.findByOrder(order);
        List<OrderItem> orderItemList3 = orderItemRepository.findAll();


        //then
        assertThat(orderItem).isEqualTo(orderItemList1.get(0));
        assertThat(orderItemList2).contains(orderItem, orderItem2);
        assertThat(orderItemList3).contains(orderItem, orderItem2);
    }
}
