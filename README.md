## [실전 예제 1 - 요구사항 분석과 기본 매핑](https://github.com/iieunji023/jpa-ex01/blob/main/ex01.md)
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

<br>

## [실전 예제 2 - 연관관계 매핑 시작](https://github.com/iieunji023/jpa-ex01/blob/main/ex02.md)

### 테이블 구조

- 이전과 같음

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/테이블구조.png" width="300">

### 객체 구조

- 참조를 사용하도록 변경

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/객체구조.png" width="300">

## [실전 예제 3 - 다양한 연관관계 매핑](https://github.com/iieunji023/jpa-ex01/blob/main/ex03.md)

### 배송, 카테고리 추가

> 엔티티

- 주문과 배송은 1:1(@OneToOne)
- 상품과 카테고리는 N:M(@ManyToMany)

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex03_엔티티.png" width="300">

> ERD

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex03_erd.png" width="300">

> 엔티티 상세

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex03_엔티티_상세.png" width="300">

## [실전 예제 4 - 상속관계 매핑](https://github.com/iieunji023/jpa-ex01/blob/main/ex04.md)

### 요구사항 추가

- 상품의 종류는 음반, 도서, 영화가 있고 이후 더 확장될 수 있다.
- 모든 데이터는 등록일과 수정일이 필수다.

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex04_도메인모델.png" width="300">

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex04_도메인모델_상세.png" width="300">

<img src="https://github.com/iieunji023/jpa-ex01/blob/main/images/ex04_테이블.png" width="300">

## [실전 예제 5 - 연관관계 관리](https://github.com/iieunji023/jpa-ex01/blob/main/ex05.md)