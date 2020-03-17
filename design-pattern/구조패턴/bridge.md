# Bridge Pattern

- 두 장소를 연결하는 역할을 하는 패턴

- "기능의 클래스 계층"과 "구현의 클래스 계층" 사이를 연결하는 역할

- 구현(implementation)으로부터 추상(abstraction) 레이어를 분리하여 이 둘이 서로 독립적으로 변화할 수 있도록 한다 

## 특징

- 기능의 클래스 계층과 구현의 클래스 계층을 분리 후 연결
- 분리해 두면 확장이 편해짐(독립적으로 확장이 가능)
- 기능을 추가하고 싶으면 기능의 클래스 계층에 추가할 수 있음
- 구현의 클래스 계층은 전혀 수정 할 필요가 없음

## 예제

```java
//인터페이스
public interface Drawing { 
    public void drawLine(int x, int y); 
    public void fill();
}
// 추상 클래스 
public abstract class Shape {
    private Drawing drawing;

    protected Shape(Drawing drawing) { 
        this.drawing = drawing;
     } 
    
    public abstract void draw(); 
    //구현부는 인터페이스로 모두 위임한다.
    public void drawLine(int x, int y) { 
        drawing.drawLine(x, y); 
    } 
    //구현부는 인터페이스로 모두 위임한다.
    public void fill() { 
        drawing.fill(); 
    } 
}
```

```java
//인터페이스를 구현한 클래스
public class RectDrawing implements Drawing{ 
    @Override 
    public void drawLine(int x, int y) { 
        System.out.println("Rect Draw line from " + x + " to " + y); 
    } 
    
    @Override 
    public void fill() { 
        System.out.println("Rect Fill!");
     } 
}


public class Rectangle extends Shpe{
    protected Rectangle(Drawing drawing){
        super(drawing);
    }
    @Override 
    public void draw() { 
        System.out.println("Rect draw extend"); 
    }
}
```

```java

//main
public class Main {
    public static void main(String[] args) { 
        Shape rectangle = new Rectangle(new RectDrawing()); 
         
        rectangle.drawLine(1, 2); 
        rectangle.fill(); 
        rectangle.draw();
    }
}
```

### Bridge vs Adapter 패턴

- 두 패턴 모두 Interface의 구현을 감추고, 구조적인 차이가 없다. 하지만 목적이 다르다.

- 어댑터: 기능은 같은데 인터페이스의 동작이 다를 때, 어댑터처럼 연결해준다.
- 브릿지: 추상부와 구현부의 분리. 확장성 주 목적이 있다.

  