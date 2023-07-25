package jpastudy.japshop.service;

import jpastudy.japshop.domain.Delivery;
import jpastudy.japshop.domain.Member;
import jpastudy.japshop.domain.Order;
import jpastudy.japshop.domain.OrderItem;
import jpastudy.japshop.domain.item.Item;
import jpastudy.japshop.repository.ItemRepository;
import jpastudy.japshop.repository.MemberRepository;
import jpastudy.japshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문저장
        orderRepository.save(order);

        return order.getId();

    }



}
