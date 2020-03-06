# Prototype 패턴

- 객체에 의해 생성될입 인스턴스의 타입이 결정되는 패턴
- 이 패턴은 새로운 객체를 생성하기 위해 clone 을 사용
- 생성된 인스턴스로부터 또 다른 인스턴스를 생성할 수 있다.

```java
interface Prototype {
    public abstract Object clone () throws CloneNotSupportedException;
}

public class prototypeExample implements Prototype {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```