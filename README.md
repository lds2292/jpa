# 상속관계 맵핑(JOIN 전략)
객체에서의 상속 관계를 어떻게 테이블화 할것인가에 대해 전략에 따라 다르게 생성되는 것을 확인한다.

|Item|
|---|
|id<br>name<br>price|

|Album|Movie|Book|
|---|---|---|
|artist|director<br>actor|author<br>isbn|

## 조인 전략
위의 엔티티들 모두 테이블로 만들고 자식 테이블이 부모 테이블의 기본키를 받아서 기본키 + 외래키로 사용하는 전략이고 조회할때는 조인을 사용하게 된다.  
ITEM 객체에서 **DTYPE**을 이용하여 타입을 구분한다.

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}

@Entity
@DiscriminatorValue("B")
//JOIN 컬럼명이 ITEM_ID가 아닌 BOOK_ID로 만들어진다.
@PrimaryKeyJoinColumn(name = "BOOK_ID")
public class Book extends Item {
    private String author;
    private String isbn;
}

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;
}
```

