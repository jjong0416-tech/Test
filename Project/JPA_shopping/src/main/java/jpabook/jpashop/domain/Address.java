package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // JPA Entity 안의 Column을 하나의 객체로써 사용하고 싶으면 이 어노테이션 사용
// 즉, city, street, zipCode 이 세개의 필드가 '주소'라는 객체로 변경하고 싶을 때 사용하는 것.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipCode;
}
