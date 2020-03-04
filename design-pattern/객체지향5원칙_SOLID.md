#객체지향 5원칙

- [SRP(단일 책임 원칙)](#SRP-(Single-Responsibility-Principle))
- [OCP(개방-폐쇄 원칙)](#OCP-(Open-Closed-Principle))
- [LSP(리스코프 치환 원칙)](#LSP-(Liskov-Substitution-Principle))
- [ISP(인터페이스 분리 원칙)](#ISP-(Interface-Segregation-Principle))
- [DIP(의존 역전 원칙)](#DIP-(Dependency-Inversion-Principle))


## SRP (Single Responsibility Principle)

- 모든 클래스는 단일 책임을 갖는다.

- 하나의 클래스에 너무 많은 책임을 부과하지 말자. 예를 들어, 로또 생성 클래스는 로또 생성의 책임만 다해야 한다. 로또를 Shuffle하거나 추출하는 기능의 책임을 부과하지 말자.

 

## OCP (Open Closed Principle)

- 확장에 대해서는 열려 있어야 하고 수정에 대해서는 닫혀 있어야 한다.

- 무엇이 변하는지, 무엇이 변하지 않는지에 집중해야 한다.

- 정의된 행위를 수정하지 못해야 한다. 예를 들면 추상 클래스를 들 수 있는데, 추상화된 클래스의 추상 메소드는 오버라이딩 되어 재정의할 수 있고 추가 기능이 가능한 메소드를 정의할 수 있다. 하지만 하위 클래스에서 해당 추상 메소드는 변경하지 못한다.



## LSP (Liskov Substitution Principle)

- sub type은 언제나 base type을 대체할 수 있어야 한다.

- LSP는 상속의 룰이다. OCP를 위반하지 않도록하는 원칙이다.

## ISP (Interface Segregation Principle)

- 인터페이스를 클래스에 특화되도록 분리한다.

- SRP와 밀접하며 ISP가 지켜지면, SRP가 지켜질 수 있다.(SRP가 지켜진다고 해서 ISP가 지켜지는 것은 아니다.)

-인터페이스가 너무 비대해질 경우 문제가 생길 수 있다. ISP 를 위반 하는 예를 들면 다음과 같다.

 
```java
public interface Analyzer {
	
    public void analyze(SocketChannel channel, byte[] data);
    public void analyze(HttpChannel channel, String data);
}

public class tcpAnayzer implements Analyzer {
	
    public void analyze(SocketChannel channel, byte[] data){
    ...
    }
   	// ??? 구현하지 않음
    public void analyze(HttpChannel channel, String data){
    }

}

public class HttpAnalyzer implements Analyzer {
	
    // ??? 구현하지 않음
    public void analyze(SocketChannel channel, byte[] data){
    }
    public void analyze(HttpChannel channel, String data){
    ...
    }

}
```

- 위와 같이 Analyzer 인터페이스라고해서 TCP 채널에서 발생한 데이터도 구현하고, Http 채널에서 발생한 데이터도 구현하도록 모두 정의 해놓으면 해당 인터페이스를 구현한 클래스는 모든 기능에 대해 강요 받게 된다.  

- ISP의 핵심은 변화 대응에 대해 유연해야하고, 일반적인 인터페이스보다 구체적인 여러개의 인터페이스를 구현해야 한다는 점이다.


### DIP (Dependency Inversion Principle)

- 의존 관계를 맺을 때, 변하기 쉬운 것 보다는 변하기 어려운 것에 의존해야 한다. (OCP와 밀접)

- 예를 들면.. 구체화 된 클래스에 의존하지 말고 추상화된 인터페이스에 의존하라.