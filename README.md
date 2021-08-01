# @MappedSuperclass 매핑
테이블과 직접 매핑되지 않고 자식 클래스에 엔티티의 매핑 정보를 상속 하기 위해 사용됨  

|BaseEntity|
|---|
|id<br>name|

|Member|Seller|
|---|---|
|email|shopName|

```java
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

@Entity
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name="name", column = @Column(name = "MEMBER_NAME"))
})
public class Member extends BaseEntity {
    private String email;
}

@Entity
@AttributeOverride(name = "id", column = @Column(name = "SELLER_ID"))
public class Seller extends BaseEntity {
    private String shopName;
}
```

## 관련 어노테이션
### @AttributeOverride / @AttributeOverrides
@MappedSuperclass에서 물려받은 매핑정보를 재정의 할때 사용하는 어노테이션
```java
@AttributeOverrides({
        @AttributeOverride(name="id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name="name", column = @Column(name = "MEMBER_NAME"))
})

@AttributeOverride(name = "id", column = @Column(name = "SELLER_ID"))
```
![img.png](img.png)