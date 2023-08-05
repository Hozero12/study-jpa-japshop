package jpastudy.japshop.service;

import jakarta.persistence.EntityManager;
import jpastudy.japshop.domain.Address;
import jpastudy.japshop.domain.Member;
import jpastudy.japshop.domain.Order;
import jpastudy.japshop.domain.OrderStatus;
import jpastudy.japshop.domain.item.Book;
import jpastudy.japshop.domain.item.Item;
import jpastudy.japshop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("1","2","3"));
        em.persist(member);

        Book book = new Book();
        book.setName("book1");
        book.setPrice(10000);
        book.setStockQuantity(20);

        //when

        Long orderId = orderService.order(member.getId(), book.getId(),  2);


        //then
        Order getorder = orderRepository.findOne(orderId);


        assertEquals("상품주문시 상태는 order", OrderStatus.ORDER, getorder.getStatus());




    }


    @Test
    public void 주문취소() throws Exception{
        //given

        //when

        //then

    }

    @Test
    public void 상품주문2() throws Exception{
        //given

        //when

        //then

    }
}