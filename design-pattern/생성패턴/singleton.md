# Singleton 패턴

- 일반적으로 프로그램 실행 시 많은 인스턴스가 있는데 하나만 필요가 경우가 있다.

- 인스턴스가 반드시 1개만 존재한다고 보증할 때 해당 패턴을 사용한다.


## 조건
- 생성되는 인스턴스 내에 공유되는 상태가 없는 경우 (공유변수가 없어야 함) 

- 생성되는 인스턴스 내에 immutable 상태만 있는 경우 (데이터 변경 불가)

3. 생성되는 인스턴스 내에 공유되는 상태가 있더라도 상태를 변경할 때 동기화를 보장할 수 있는 경우


## 예제

- 가장 심플한 싱글톤 패턴 예제
```java

public class Singleton {

    private Singleton singleton = null;
    
    private singleton(){
    }  
    public static Signleton getInstance(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
```


## 문제점

- 싱글톤 클래스를 통해 인스턴스를 생성하여, 내부에서 너무 많은 데이터를 공유하면 OCP를 위배할 수 있다.

- 또한 위와 같은 코드의 경우 멀티 쓰레드 환경에서 2개의 인스턴스가 생성될 수 있는 상황이 발생할 수 있다. 

- 이에 따라 synchronized 키워드를 사용하거나 LazyHolder 기법으로 아래와 같이 multi thread 환경에서 안전한 싱글쓰레드를 생성할 수 있다.

```java
public class Singleton {
    private Singleton() {}

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
}
``` 