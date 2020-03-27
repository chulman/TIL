# Facade(퍼사드) 패턴

- Facade 의 사전적 의미는 창구, 건물의 정면
- 사전적 의미와 같은 역할을 수행함

- 다양하고 복잡한 구조와 기능을 가진 클래스나 라이브러리가 있는데, 그걸 사용하는 개발자들은 그 내부 구조를 일일이 알 필요없이 특정 인터페이스 몇 개만 알아도 사용이 가능하도록 해주는 것이 바로 Facade 패턴
- 즉, 클래스나 라이브러리의 '창구' 역할

- 퍼사드에서 고수준 인터페이스를 정의하기 때문에 서브시스템을 더 쉽게 사용할수 있다.
  
  
 
### 예제

```java
public class Main {
	public static void main(String[] args) {
		PageMaker.makeWelcomePage("chlcjfals0122@gmail.com", "welcome.html");
	}
}
```


- Facade 역할 클래스
```java
public class PageMaker {
	private PageMaker() {} // 인스턴스는 만들지 않기 때문에 private을 선언한다.

	public static void makeWelcomePage(String mailAddr, String fileName) {
		try {
			Properties mailprop = Database.getProperties("maildata");
			String username = mailprop.getProperty(mailAddr);
			HtmlWriter writer = new HtmlWriter(new FileWriter(fileName));

			writer.title("Welcome to " + username + "'s page!");
			writer.paragraph(username + "의 페이지에 오신 걸 환영합니다.");
			writer.paragraph("메일을 기다리고 있습니다.");
			writer.mailto(mailaddr, username);
			writer.close();

			System.out.println(filename + " is created for " + mailaddr + " ("
					+ username + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

- 서브클래스
```java
public class HtmlWriter {
	private Writer writer;
	public HtmlWriter(Writer writer) { 
		this.writer=writer;
	}
	public void title(String title) throws IOException {
		writer.write("<html>");
		writer.write("<head>");
		writer.write("<title>" + title + "</title>");
		writer.write("</head>");
		writer.write("<body>\n");
		writer.write("<h1>" + title + "</h1>\n");
	}
	public void paragraph(String msg) throws IOException { 
		writer.write("<p>" + msg + "</p>\n");
	}
	public void link(String href, String caption) throws IOException {  
		paragraph("<a href=\"" + href + "\">" + caption + "</a>");
	}
	public void mailto(String mailaddr, String username) throws IOException { // 메일 주소 출력
		link("mailto:" + mailaddr, username);
	}
	public void close() throws IOException {
		writer.write("</body>");
		writer.write("</html>\n");
		writer.close();
	}
}
```

- 서브클래스
```java
public class Database {
	private Database() {}
	public static Properties getProperties(String dbname) { 
		// 데이터베이스 이름에서 Properties를 얻는다
		String filename=dbname + ".txt";
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(filename));
		} catch (IOException e) {
			System.out.println("Warning : " + filename + " is not found.");
		}
		
		return prop;
	}
}
```