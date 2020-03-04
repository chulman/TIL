# Adapter Pattern

- 이름 대로 Adapter 처럼 사용되는 패턴

- Wrapper 패턴으로 불리기도 함

- 감싸는 것의 의미로 무엇인가를 한 번 포장해서 다른 용도로 사용할 수 있게 교환해 주는 뜻

- 어댑터 패턴을 통해, 기존에 존재하던 클래스에 대한 재사용이 용이하다.
 
- Wrapping 했기 때문에, 기존 클래스에 대한 예외 검출도 쉽다. 

## 종류 및 예제

- 하위 예제의 PrintBanner 클래스가 어댑터에 해당한다.

- 클래스에 의한 Adapter 패턴 (상속)

```java
class Main{
    public static void main(String[] args){
        Print print = new PrintBanner("hello");
        print.showString();
    }
}

public interface Print {
    void showString();
}

public class Banner {
    private String text;
    
    public Banner(String text){
        this.text = text;
    }
    public void showBanner(){
        System.out.println(text);
    }


}

public class PrintBanner extends Banner implements Print{
    
    public PrintBanner(String text){
        super(text);
    }
    public void showString(){
        showBanner();
    }
}

```

- 인스턴스에 의한 Adapter 패턴 (위임)

```java
class Main{
    public static void main(String[] args){
        Print print = new PrintBanner("hello");
        print.showString();
    }
}

public interface Print {
    void showString();
}

public class Banner {
    private String text;
    
    public Banner(String text){
        this.text = text;
    }
    public void showBanner(){
        System.out.println(text);
    }


}

public class PrintBanner implements Print{
    
    private Banner banner;    

    public PrintBanner(String text){
        banner = new Banner(text);
    }
    public void showString(){
        banner.showBanner();
    }
}

```