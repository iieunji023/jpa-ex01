package com.jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

    @OneToMany(mappedBy = "member") // mappedBy를 통해 연관관계 주인 지정
    private List<Order> orders = new ArrayList<>(); // 관례상 new ArrayList<>(); 많이 씀

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
