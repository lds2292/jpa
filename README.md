# 단방향 맵핑
```java
import javax.persistence.*;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
```
```java
import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
}
```
## @Id
Entity의 키값을 설정한다.
## @GeneratedValue
@Id로 지정한 키값을 자동으로 만들어준다. 이때 다음과 같이 전략(strategy)을 설정하여 어떻게 만들지 설정할수 있다.

|strategy|내용|
|---|---|
|GenerationType.AUTO|RDBMS에 따라 자동설정해준다|
|GenerationType.IDENTITY|AutoIncrement 설정사용|
|GenerationType.SEQUENCE|시퀀스를 사용하여 자동생성|
|GenerationType.TABLE|테이블을 생성하여 자동생성|

## @ManyToOne
단방향 연관관계 설정시 다대일 관계 설정 어노테이션  
fetch 옵션으로 LAZY, EAGER(default) 설정을 할수 있다.

|fetchType|내용|
|---|---|
|LAZY|지연로딩. 해당 객체를 사용할 시점에 조회 한다|
|EAGER|즉시로딩. 해당 엔티티를 가져올때 연관된 객체도 전부 조회하여 가져온다(Default)|
## @JoinColumn
외래키를 설정하기 위한 어노테이션  
name으로 외래키 필드 이름을 설정 할 수 있다.