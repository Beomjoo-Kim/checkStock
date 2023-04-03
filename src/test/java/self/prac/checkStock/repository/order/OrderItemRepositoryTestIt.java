package self.prac.checkStock.repository.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.domain.item.Item;
import self.prac.checkStock.domain.order.Order;
import self.prac.checkStock.domain.order.OrderItem;
import self.prac.checkStock.repository.item.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestConfig.class)
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

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);

        //when
        long id = orderItemRepository.save(orderItem);
        itemRepository.save(item);
        OrderItem searchedOrderItem = orderItemRepository.findOne(id);

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

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItemRepository.save(orderItem);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItem(item2);
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
