# 상속관계 맵핑(단일 테이블 전략)
말 그대로 하나의 테이블에 관련된 필드들을 모두 만들어 하나의 테이블로만 사용하게끔 하는 전략.

|Item|
|---|
|id(PK)<br>name<br>price<br>artist<br>director<br>actor<br>author<br>isbn<br>**DTYPE**|

![img.png](img.png)

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

