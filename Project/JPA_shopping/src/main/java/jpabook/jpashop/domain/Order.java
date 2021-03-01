package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블을 적어줘야한다.
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // Order의 입장에서 다 대 1 관계라서 ManyToMany, 만약에 다 대 다 관계면은 ManyToMany로 매핑
    @JoinColumn(name = "member_id") // 매핑을 뭘로 할 것이냐 !? foreign key 이름이 member_id로 한다는 뜻이다.
    private Member member;

    @OneToMany(mappedBy= "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne // OneToOne은 엑세스를 많이 하는 거에 FK를 주는 것이 좋은 경우가 많다 - 김영한
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    // 자바8에서 나온 LocalDateTime이고, 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

}
