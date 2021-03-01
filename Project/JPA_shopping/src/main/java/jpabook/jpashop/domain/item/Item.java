package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single_Table, Table_PER_CLASS, JOINED 가 있는데 우린 싱글테이블 전략으로 간다
@DiscriminatorColumn(name = "dtype") // 북이면 어떻게 할꺼야? 앨범이면 어떻게 할거야?
@Getter @Setter
public abstract class Item { // 추상클래스 왜냐면 구현체를 가지고 할 거 기 때문에
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
