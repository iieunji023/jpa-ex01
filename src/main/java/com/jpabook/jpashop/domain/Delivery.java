package com.jpabook.jpashop.domain;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    private DeliveryStatus status;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}
