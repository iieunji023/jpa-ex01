## 실전 예제 1 - 요구사항 분석과 기본 매핑
### 요구사항 분석
- 회원은 상품을 주문할 수 있다.
- 주문 시 여러 종류의 상품을 선택할 수 있다.

### 기능 목록
- 회원 기능
    - 회원등록
    - 회원조회
- 상품기능
    - 상품등록
    - 상품수정
    - 상품조회
- 주문기능
    - 상품주문
    - 주문내역조회
    - 주문취소

### 도메인 모델 분석

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/도메인.png" width="250">
- 회원과 주문의 관계(일대다)
    - 회원은 여러 번 주문할 수 있다.
- 주문과 상품의 관계(다대다)
    - 주문할 때 여러 상품을 선택할 수 있다.
    - 반대로 같은 상품도 여러 번 주문될 수 있다.
    - 주문 상품이라는 모델을 만들어서 다대다 관계를 일대다, 다대일 관계로 풀어냄

### 테이블 설계

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/테이블구조.png" width="250">

### 엔티티 설계와 매핑

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/엔티티.png" width="250">

### 엔티티 생성

<details>
      <summary>Member.java</summary>

    @Entity
    public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

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

</details>

<details>
      <summary>Order.java</summary>

    @Entity
    @Table(name="ORDERS")
    public class Order {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @Column(name="MEMBER_ID")
    private Long memberId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    }

</details>

<details>
      <summary>Item.java</summary>

    @Entity
    public class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    }

</details>

<details>
      <summary>OrderItem.java</summary>

    @Entity
    public class OrderItem {
    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @Column(name="ORDER_ID")
    private Long orderId;

    @Column(name="ITEM_ID")
    private Long itemId;

    private int orderPrice;

    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    }

</details>

<details>
      <summary>OrderStatus.java</summary>

    package com.jpabook.jpashop.domain;

    public enum OrderStatus {
    ORDER, CANCEL;
    }

</details>

### 테이블 생성하기
<details>
      <summary>JpaMain.java</summary>

    public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
    }

</details>
<details>
      <summary>출력 결과(테이블 생성)</summary>

    Hibernate: 
    create sequence Item_SEQ start with 1 increment by 50
    14:19:04.278 [main] INFO org.hibernate.orm.connections.access -- HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@27e2287c] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
    Hibernate:
    create sequence Member_SEQ start with 1 increment by 50
    Hibernate:
    create sequence OrderItem_SEQ start with 1 increment by 50
    Hibernate:
    create sequence ORDERS_SEQ start with 1 increment by 50
    Hibernate:
    create table Item (
    price integer not null,
    stockQuantity integer not null,
    ITEM_ID bigint not null,
    name varchar(255),
    primary key (ITEM_ID)
    )
    Hibernate:
    create table Member (
    MEMBER_ID bigint not null,
    city varchar(255),
    name varchar(255),
    street varchar(255),
    zipcode varchar(255),
    primary key (MEMBER_ID)
    )
    Hibernate:
    create table OrderItem (
    count integer not null,
    orderPrice integer not null,
    ITEM_ID bigint,
    ORDER_ID bigint,
    ORDER_ITEM_ID bigint not null,
    primary key (ORDER_ITEM_ID)
    )
    Hibernate:
    create table ORDERS (
    MEMBER_ID bigint,
    ORDER_ID bigint not null,
    orderDate timestamp(6),
    status varchar(255) check (status in ('ORDER','CANCEL')),
    primary key (ORDER_ID)
    )

</details>

- h2 콘솔에서 확인

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/h2결과.png" width="200">

### h2 콘솔 연결
> <persistence.xml>

```
<property name="jakarta.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/jpashop"/>
```

- jpashop으로 변경


<details>
      <summary>⚠️ h2 콘솔 접속 안되는 문제</summary>
1. ip 주소 localhost로 바꿔서 접속

    [`http://localhost:8082/login.do?jsessionid=e7dc39bdbf019db257539b0508232b51`](http://localhost:8082/login.do?jsessionid=e7dc39bdbf019db257539b0508232b51)

2. jdbc URL: `jdbc:h2:~/jpashop`

</details>

### 데이터 중심 설계의 문제점
- 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
- 테이블의 외래키를 객체에 그대로 가져옴
- 객체 그래프 탐색이 불가능
- 참조가 없으므로 UML도 잘못됨

## 실전 예제 2 - 연관관계 매핑 시작

### 테이블 구조

- 이전과 같음

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/테이블구조.png" width="300">

### 객체 구조

- 참조를 사용하도록 변경

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/객체구조.png" width="300">

💡 Member 엔티티 내에 Orders 필드가 있는 것은 매우 잘못된 설계!
(예제니까 넘어가는걸로…😂)

> 엔티티 생성

<details>
      <summary>Member.java</summary>

```
@Entity
public class Member {
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

```

- order_id 객체 대신 order 객체를 필드로 생성
- ⚠️ Member에 Order가 들어가는것은 좋은 구조는 아님!

</details>

<details>
      <summary>Order.java</summary>

```
@Entity
@Table(name="ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne  // Order가 N, member가 1
    private Member member;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}


```

- member_id 대신 Member 객체 필드 추가
- item_id 대신 orderItem 필드 추가
    - `mappedBy`를 통해 연관관계의 주인인 OrderItem 필드의 order과 연결

</details>

<details>
      <summary>OrderItem.java</summary>

```
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @ManyToOne // OrderItem이 N, Order가 1
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne  // OrderItem N, Item 1
    @JoinColumn(name="ITEM_ID")
    private Item item;

    private int orderPrice;

    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

```

- order_id 필드 대신 Order 객체 필드 추가
- item_id 필드 대신 Item 객체 필드 추가

</details>

> 엔티티에 값 등록

<details>
      <summary>JpaMain.java</summary>

```
Order order = new Order();
order.addOrderItem(new OrderItem());
em.persist(order);
em.persist(orderItem);

tx.commit();
```

</details>

<details>
      <summary>Order.java</summary>

```
public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);   // 양방향 연관관계되도록
}
```

- Order 엔티티 내의 필드인 orderItems에 값 등록
- 매개변수로 들어오는 orderItem 엔티티 내에도 order 값 등록
- 양방향 연관관계가 되도록 값을 설정해주는 것!

</details>

💡 이렇게 양방향으로 하지 않고 아래 코드와 같이 개발을 진행해도 상관없다

<details>
      <summary>JpaMain.java</summary>

```
Order order = new Order();
em.persist(order);

OrderItem orderItem = new OrderItem();
orderItem.setOrder(order);

em.persist(orderItem);

tx.commit();
```

</details>

<details>
      <summary>🗯️ 강의를 들으면서 든 의문점</summary>

- Order과 Item은 왜 다대다 관계인가?
- 한번 주문(Order)할 때 여러 상품(Item)이 들어가는 건 이해
    - 그렇다면 일대다 아닌가? 라는 생각이 들어서 검색을 해봤다([참고1](https://www.inflearn.com/community/questions/955559/orderitem-item-%EA%B4%80%EA%B3%84%EC%97%90-%EB%8C%80%ED%95%B4-%EA%B6%81%EA%B8%88%ED%95%A9%EB%8B%88%EB%8B%A4), [참고2](https://www.inflearn.com/community/questions/1120720/order%EC%99%80-item%EA%B0%84%EC%9D%98-%EA%B4%80%EA%B3%84%EC%97%90-%EB%8C%80%ED%95%B4%EC%84%9C-%EA%B6%81%EA%B8%88%ED%95%9C%EC%A0%90%EC%9D%B4-%EC%9E%88%EC%8A%B5%EB%8B%88%EB%8B%A4?focusComment=306781))
- 예시로 설명하니 이해가 잘 되었다.
- 예를들어 주문은 O1부터 시작하는 ID를 생성
- 상품은 I1부터 시작하는 ID를 생성한다고 가정해보자
- 상품 비누(I1), 치약(I2), 샴푸(I3)가 있다고 하자
- A, B 고객이 상품을 담았을 때 장바구니는
- A(O1) ⇒ I1, I2, I3
- B(O2) ⇒ I1, I3
- 이렇게 구성되어 있을 것이다.
- 그러면 결국 **주문 하나에는 여러 상품이 들어가고, 각 상품들은 여러 주문에 포함**될 수 있다!
- 즉, 다대다의 관계라는 것.
- 이런 경우에는 **다대다 관계를 일대다, 다대일 관계로 풀어내서 표현**
- 따라서 본 예제에서는 Order 테이블과 Item 테이블 사이에 OrderItem이라는 테이블을 사이에 두고 연관관계를 설정했다

</details>

