package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Orderitem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;
    //생성 메서드//
    public static Orderitem createOrderitem(Item item, int orderPrice, int count){
        Orderitem orderitem = new Orderitem();
        orderitem.setItem(item);
        orderitem.setOrderPrice(orderPrice);
        orderitem.setCount(count);
        item.removeStock(count);
        return orderitem;
    }

    //비즈니스 로직
    public void cancel(){
        getItem().addStock(count);
    }

    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }



}
