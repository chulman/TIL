# Proxy Pattern

- Proxy는 '대리인'이라는 뜻 

- 실제 기능을 수행하는 객체 Real Object 대신 가상의 객체인 Proxy Object를 사용해 로직의 흐름을 제어하는 디자인 패턴

- 중요한 점은 흐름제어만 할 뿐, 결과를 조작하거나 변형시키면 안된다.



### 특징

- 대리자는 실제 서비스와 같은 이름의 메서드를 구현한다. 이때 인터페이스를 사용한다.

- 대리자는 실제 서비스에 대한 참조 변수를 갖는다(합성)

- 대리자는 실제 서비스의 같은 이름을 가진 메서드를 호출하고 그 값을 클라이언트에게 돌려준다.

- 대리자는 실제 서비스의 메서드 호출 전후에도 별도의 로직을 수행할 수도 있다.
 

### 종류

- 원격 프록시 : 원격 객체에 대한 접근 제어가 가능합니다.

- 가상 프록시 (Virtual Proxy) : 객체의 생성비용이 많이 들어 미리 생성하기 힘든 객체에 대한 접근 및 생성시점 등을 제어합니다.

- 보호 프록시 (Protection Proxy) : 객체에 따른 접근 권한을 제어해야하는 객체에 대한 접근을 제어할 수 있습니다.

- 방화벽 프록시 : 일련의 네트워크 자원에 대한 접근을 제어함으로써 주 객체를 '나쁜' 클라이언트들로부터 보호하는 역할을 합니다.

- 스마트 레퍼런스 프록시 (Smart Reference Proxy) : 주 객체가 참조될 때마다 추가 행동을 제공합니다. ex) 객체 참조에 대한 선 작업, 후 작업 등

- 캐싱 프록시 (Caching Proxy) : 비용이 많이 드는 작업의 결과를 임시로 저장 하고, 추후 여러 클라이언트에 저장된 결과를 실제 작업처리 대신 보여주고 자원을 절약하는 역할을 합니다.

- 동기화 프록시 (Synchronization Proxy) : 여러 스레드에서 주 객체에 접근하는 경우에 안전하게 작업을 처리할 수 있게 해줍니다. 주로 분산 환경에서 일련의 객체에 대한 동기화 된 접근을 제어해주는 자바 스페이스에서 쓰입니다.

- 복잡도 숨김 프록시 (Complexity Hiding Proxy) : 복잡한 클래스들의 집합에 대한 접근을 제어하고, 복잡도를 숨깁니다. 

- 지연 복사 프록시 (Copy-On-Write Proxy) : 클라이언트에서 필요로 할 때까지 객체가 복사되는 것을 지연시킴으로써 객체의 복사를 제어합니다. '변형된 가상 프록시'라고 할 수 있으며, Java 5 의 CopyOnWriteArrayList 에서 쓰입니다.

> 참조 : https://developside.tistory.com/80


### 예제

```java
public interface Printable {
	public abstract void setPrinterName(String name);
	public abstract String getPrinterName(); 
	public abstract void print(String string);
}

public class PrinterProxy implements Printable {
	private String name; 
	private Printer real;
	
	public PrinterProxy() {
	}
	
	public PrinterProxy(String name) {
		this.name=name;
	}
	
    public synchronized void realize() { // 실제로 프록시 객체가 실행학고 행동할 실제 객체 생성 
		if(real==null) {
			real=new Printer(name);
		}
	}
	public synchronized void setPrinterName(String name) {
		if (real!=null) {
			real.setPrinterName(name);
		}
		this.name=name;
	}
	
	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String string) {
		realize();
		real.print(string);
	}

	
}

public class Printer implements Printable {
	private String name;
	public Printer() {
		heavyJob("Printer의 인스턴스를 생성 중");
	}
	
	public Printer(String name) {
		this.name=name;
		heavyJob("Printer의 인스턴스 (" + name + ")을 생성 중");
	}
	
	@Override
	public void setPrinterName(String name) {
		this.name=name;
	}

	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String string) {
		System.out.println("=== " + name + " ===");
		System.out.println(string);
	}

	public void heavyJob(String msg) {
		System.out.println(msg);
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.print(".");
		}
		System.out.println("완료");
	}
}
public class Main {
	public static void main(String[] args) {
		Printable p=new PrinterProxy("Alice");
		System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
		p.setPrinterName("Bob");
		System.out.println("이름은 현재 " + p.getPrinterName() + "입니다.");
		p.print("Hello, world");
	}
}
```

