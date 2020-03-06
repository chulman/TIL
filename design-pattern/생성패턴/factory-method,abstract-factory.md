# Factory method 패턴

- 객체의 인스턴스를 생성해주는 공장의 개념으로 생각하면 쉽다.
- Factory method 패턴에서는 인스턴스를 만드는 방법을 상위클래스에서 결정하지만 구체적인 클래스 이름까지는 결정 하여 리턴하지 않음
  
- 구체적인 내용은 모두 하위 클래스 측에서 수행

## 예제

```java

interface Metric {
    void measure(); 
}

class CPUMetric extends Metric {

    public void measure(){
        System.out.println("CPU Resource Measured");
    }
}

class MemoryMetric extends Metric {

    public void measure(){
        System.out.println("Memory Resource Measured");
    }
}

class AWS_EC2_MetricFactory extends MetricFactory {
    public void measure(String name){
        if(name.equals("CPU")){
            new CPUMetric().measure();
        }
        if(name.equals("Memory")){
            new MemoryMetric().measure();
        }
    }
}


```

# Abstract Factory 패턴

- 객체 마다 팩토리를 만들어서 어떤 객체를 형성했는데 그 객체의 행위는 비슷하므로 관련 객체를 캡슐화하여 추상화함.

```java

public class AbstractMetricFactory {
    public void createFactory(String type){
        MetricFactory metricFactory= null;
        switch (type){
            case "EC2":
                return new AWS_EC2_MetricFactory();

            case "RDS":
                return new AWS_RDS_MetricFactory();
        }
    }
}

class Main {
    public static void main(String[] args){
        MetricFactory factory = new AbstractMetricFactory().createFactory("EC2");
        factory.measure("CPU");
        factory.measure("Memory");
    }

}
```