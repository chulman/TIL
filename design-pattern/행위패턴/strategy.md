#Strategy 패턴

- 전략을 쉽게 바꿀 수 있도록 해주는 패턴
    + 전략: 어떤 목적을 달성하기 위해 일을 수행하는 방식, 비즈니스 규칙, 문제를 해결하는 알고리즘
    
- 행위를 클래스로 캡슐화해 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 패턴
          
- 같은 문제를 해결하는 여러 알고리즘이 클래스별로 캡슐화되어 있고 이들이 필요할 때 교체할 수 있도록 함으로써 동일한 문제를 다른 알고리즘으로 해결할 수 있게 하는 디자인 패턴


## 예제

```java
public interface paymentStrategy {
    void pay(); 
}

public class CardStrategy implements strategy {
    @Override
    public void pay(double fee){
        System.out.println("카드 결제로 " + fee + "원 지불");
    }
}

public class CashStrategy implements strategy {
 
    @Override
    public void pay(double fee){
        System.out.println("현금 결제로 " + fee +"원 지불");
    }
}
public class Human {
    private paymentStrategy strategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
    
    public void order(String product){
        System.out.println(product + " 주문 완료");
    }

    public void pay(double fee){
        paymentStrategy.pay(fee);
    }   
}


class Main {
    public static void main(String[] args){

        Human human = new Human();
        human.order("치킨");
    
        human.setPaymentStrategy(new CashStrategy());
        human.pay(356.24);
    }
}

```

