# Builder 패턴

- 전체를 구성하고 있는 각 부분을 만들고 쌓아 올리는 방식. 즉, 구조를 가진 인스턴스를 쌓아 올리는 것을 빌더 패턴
- 객체 생성을 깜끔하고 유연하게

- 예제에서는 GOF의 빌더패턴은 다루지 않았으며, 아래 글을 참조하면 좋을 것 같다. 

    + 참고 : https://johngrib.github.io/wiki/builder-pattern/

### 예제

```java
public class Member {
    private final int id;
    private final String name;
    private final String country;
    private final String phoneNumber;

    public static class Builder {
        // Required parameters(필수 인자)
        private final int id;
        private final String name;

        // Optional parameters - initialized to default values(선택적 인자는 기본값으로 초기화)
        private String country = "";
        private String phoneNumber = "";

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    private Member(Builder builder) {
        id = builder.id;
        name = builder.name;
        country = builder.country;
        phoneNumber = builder.phoneNumber;

    }
}

```



