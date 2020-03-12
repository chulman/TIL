

## MongoDB

> MongoDB is a general purpose, *document-based*, distributed database built for modern application developers and for the cloud era. No database makes you more productive.
  
 - https://www.mongodb.com/ 사이트에 들어가면 소개하는 인용구의 핵심은 문서 기반의 분산 데이터베이스 
 - 설치:  http://docs.mongodb.com/manual/installation/


### 특징

- 데이터의 중복을 허용하며 비정형화된 설계를 지향한다.

- 중첩 데이터 구조를 설계 할 수 있기 때문에 불필요한 JOIN을 최소화 시킬 수 있다.

- 다대다 관계 구조를 설계할 수 있고 구축할 수 있다.

-  Schema 중심으로 설계하지 않는다.
    +RDB와 기본적으로 다르기 때문에 RDB 와 같은 구조로 설계하지 않는다.

- mongoDB는 조인을 지원하지 않기 때문에 여러 컬렉션에서 문서를 모으려면 쿼리를 여러번 수행해야 한다.

- RDBMS가 데이터 모델링에서부터 시작해서 정규화를 통해(중복 제거) 테이블을 만들어내고, 해당 테이블을 통해 쿼리를 수행해 결과를 뽑아낸다고 하면, NoSQL은 이와 정반대의 접근이 필요하다. 먼저 수행할 쿼리를 정의하고 이에 맞춰 데이터 테이블을 정의하고, 성능을 높이기 위해 일부로 중복을 허용해 테이블을 정의해야 한다.
    


### Document Key/Value Store

- MongoDB는 대표적인 Document Key/Value Store의 특징을 갖는 NoSQL이다.

- Document 타입은 XML, JSON, YAML과 같이 구조화된 데이터 타입으로, 복잡한 계층 구조를 표현할 수 있다. Document Store 기반의 NoSQL은 제품에 따라 다르기는 하지만 대부분 추가적인 기능(Sorting, Join, Grouping 등)을 제공한다.


### 관계형 DB 와 NoSQL

- RDB는 데이터의 원자성, 정규화된 테이블, 트랜잭션 등 ACID를 완벽히 보장한다.
    + 하지만 이에 따라 확장성에 한계가 존재한다.

- NoSQL은 높은 확장성을 갖고 있다.
    + 그렇기 때문에 반드시 ACID가 보장될 필요는 없다.


### MongoDB CRUD

- https://docs.mongodb.com/manual/crud/

1. 생성

- 컬렉션이 존재하지 않으면 생성 존재하면 삽입

> insert into users(name, age) values('choi', 100)

```
db.users.insertOne(                <- collection
    {
        name: "choi",              <- field
        age: 100                   <- field
    }
)
```

2. 읽기 

> select _id, name from users where age > 18 limit 5

```  
db.users.find(                      <- collection
    { age : { $gt : 18 } },         <- query criteria
    { name: 1 }                     <- projection             
).limit(5)                          <- cursur modifier
```

- 쿼리의 처리 순서는 Collection 선택 -> Criteria 수행 -> Modifier 수행

```  
db.users.find(                      
    { age : { $gt : 18 } },         
    { name: 1, _id: 0 }                          
).limit(5)                          
```

- projection(추출할 값)의 필드가 0이면, 제외하겠다는 의미를 갖는다.


3. 수정

> update users set name="kim" where age > 25

```  
db.users.update(                      
    { age : { $gt : 25 } },         <- update criteria
    { $set: { name : "kim" }},      <- update action
    { multi: true }                 <- update option
)

```

- update option 중 upsert 키워드가 있는데 update 할 document가 없을 경우 insert 해준다.


4. 삭제

delete from users where age > 25

```
db.users.remove (
    { age : { $gt : 25 } } 
)

``` 









    
     


