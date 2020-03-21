# Decorator Pattern

- 객체의 결합 을 통해 기능을 동적으로 유연하게 확장 할 수 있게 해주는 패턴

> 예를 들면, 크림을 바른 스펀지 케이크에 딸기를 얹으면 딸기 케이크가 되고, 초콜릿 장식을 하면 초코 케이크가 된다.

### 역할 
> Component
- 기본 기능을 뜻하는 ConcreteComponent와 추가 기능을 뜻하는 Decorator의 공통 기능을 정의
- 즉, 클라이언트는 Component를 통해 실제 객체를 사용함

> ConcreteComponent
- 기본 기능을 구현하는 클래스

> Decorator
- 많은 수가 존재하는 구체적인 Decorator의 공통 기능을 제공

> ConcreteDecorator
- Decorator의 하위 클래스로 기본 기능에 추가되는 개별적인 기능을 뜻함
- ConcreteDecorator 클래스는 ConcreteComponent 객체에 대한 참조가 필요한데, 이는 Decorator 클래스에서 Component 클래스로의 ‘합성(composition) 관계’를 통해 표현됨

### 예제
- Component
```java
public abstract class Display {
	public abstract int getColumns();          // 가로 문자수를 얻는다
	public abstract int getRows();             // 세로 행수를 얻는다  
	public abstract String getRowText(int row);  // row 번째의 문자열을 얻는다
	public final void show(){                  //출력
		for (int i=0; i<getRows(); i++){
			System.out.println(getRowText(i));
		}
	}
}
```

- ConcreteComponent
```java
public class StringDisplay extends Display {
	private String string;           		//표시문자열

	public StringDisplay(String string) {             //인수로 표시 문자열 지정
		this.string = string;
	}

	public int getColumns() {		        //문자 수
		return string.getBytes().length;
	}

	public int getRows() {			//행수는 1
		return 1;
	}
	
	public String getRowText(int row) {		// row가 0일 때만 반환
		if (row == 0) {
			return string;
		} else {
			return null;
		}
	}
}

```

- Decorator
```java
public abstract class Border extends Display {
	protected Display display;              // 이 장식이 둘러싸고 있는 '내용물'
	protected Border(Display display){  //인스턴스 생성시에 '내용물'을 인수로 지정	
		this.display = display;
	}
}
```

- ConcreteDecorator
```java
public class SideBorder extends Border {

    private char borderChar; // 장식이 되는 문자

	protected SideBorder(Display display, char ch) {
		// 생성자에서 Display 라는 장식 문자 지정
		super(display);
		this.borderChar = ch;
    }
    public void show() {
	    display.show();
        System.out.println(ch);
    }
}

```


```java
public class Main{
    public static void main(String[] args){
        Dispalay display = new StringDisplay("test");
        char suffix= "------------------";
        Border border = new SideBorder(display, suffx);
        border.show(); 
    }
}



```








