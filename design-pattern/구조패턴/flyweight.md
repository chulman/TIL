# Flyweight 패턴

- 중복되는 데이터를 객체로 만들어서 공유하는 패턴
- 만들어진 객체의 인스턴스는 메모리 소비를 적게할 수 있다.
- 공유하고 있는 인스턴스를 변경하면 여러 장소에 영향을 미치기 때문에 공유시켜야 할 본질적인 정보와 공유시켜서는 안 되는 외부적인 정보를 의식해서 구별할 필요가 있다.


- 즉 ***인스턴스를 가능한 대로 공유시켜서 불필요한 객체 생성 하지 않도록 한다***



### 예제

- big0.txt
```text
....######......
..##......##....
..##......##....
..##......##....
..##......##....
..##......##....
....######......
................

```
- big0.txt ~ bigN.txt를 읽어서 출력하는 프로그램
```java
public class Main {
    public static void main(String[] args) {
        //String args = "012345";
        String args = "0";
        BigString bs = new BigString(args);
        bs.print();
    }
}

```

```java
public class BigString {
    // 「큰 문자」
    private BigChar[] bigChars;
    
    public BigString(String string) {
        bigchars = new BigChar[string.length()];
        BigCharFactory factory = BigCharFactory.getInstance();
        for (int i = 0; i < bigchars.length; i++) {
            bigchars[i] = factory.getBigChar(string.charAt(i));
        }
    }
    
    public void print() {
        for (int i = 0; i < bigchars.length; i++) {
            bigchars[i].print();
        }
    }
}
```

```java
class BigCharFactory {
    // 이미 만들어진 BigChar의 인스턴스를 관리
    private HashMap pool = new HashMap();
    // Singleton 패턴
    private static BigCharFactory singleton = new BigCharFactory();

    // 생성자
    private BigCharFactory() {
    }

    // 유일한 인스턴스를 얻는다
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // BigChar의 인스턴스 생성(공유)
    public synchronized BigChar getBigChar(char charName) {
        BigChar bc = (BigChar) pool.get("" + charName);
        //공유데이터를 해시맵에 관리하고, 맵에 bigChar 객체가 없을 때만 생성하고 불필요하게 객체를 생성하지 않도록 한다.
        if (bc == null) {
            bc = new BigChar(charName); // 여기에서 BigChar의 인스턴스를 생성 
            pool.put(""+ charName, bc);
        }
        return bc;
    }
}
```

```java
class BigChar {
    // 문자의 이름
    private char charname;
    // 큰 문자를 표현하는 문자열 ('#' '.' '\n'의 열)
    private String fontdata;

    // 생성자
    public BigChar(char charname) {
        this.charname = charname;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("big" + charname + ".txt")
            );
            String line;
            StringBuffer buf = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            reader.close();
            this.fontdata = buf.toString();
        } catch (IOException e) {
            this.fontdata = charname + "?";
        }
    }

    // 큰 문자를 표현한다
    public void print() {
        System.out.print(fontdata);
    }
}
```