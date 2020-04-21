# netty in action

![네티 인 액션](https://wikibook.co.kr/images/cover/l/9791158390327.jpg)

## 1장. 비동기식 이벤트 기반 네트워킹 프레임워크
- 네티는 유지관리가 용이한 고성능 프로토콜 서버와 클라이언트를 신속하게 개발하기 위한 비동기식 이벤트 기반 네트워크 애플리케이션 프레임워크.
    + 오래 사용해본 결과 네티는 기본적으로 비동기식 프레임워크라서, 동기작업에 대한 부분은 까다로울 수 있다.
    
- LOW-LEVEL API (java socket)을 직접 이용하면 복잡성이 높아진다.
   
- 네티를 통해 성능, 설계, 이용 편이성을 극대화 할 수 있다.

- 핵심 컴포넌트
    + Channel : IO 데이터를 위한 운송수단
        + 각 채널에는 이벤트 루프가 할당 되어 있으며, 이벤트 루프는 이벤트를 등록, 핸들러로 발송, 스케쥴링과 같은 작업을 수행할 수 있다.
    + 콜백 : 다른 메소드로 자신에 대한 참조를 제공할 수 있는 메소드
    + Future : 작업이 완료되면 이를 애플리케이션에 알리는 방법
    + 이벤트와 핸들러 : 작업의 상태 변화를 알리기 위한 이벤트를 이용하며, 핸들러를 통해 적절히 트리거 할 수 있음
        + 동작 : 로깅, 데이터변환, 흐름 제어, 애플리케이션 논리
        + 인바운드 데이터로 인한 이벤트 : 연결 활성화/비활성화, 데이터 읽기, 사용자이벤트, 오류이벤트
        + 아웃바운드 이벤트로 인한 동작 : 원격 피어로 연결 열기 또는 닫기, 소켓으로 데이터 쓰기 또는 플러시
    
    
- 네티는 결국 저수준의 자바 API(Selector, Future.. 등)를 감싼 프레임워크로써 고수준의 API를 제공한다.

## 2장. 네티 애플리케이션
- 모든 네티 서버는 하나 이상의 ChannelHandler와 bootstrap이 필요함
    + channelHandler: 클라이언트로부터 전송받은 데이터를 서버측에서 처리하는 비지니스 로직을 구성
    + bootstrap : 서버를 구성하는 시동 코드를 의미한다. 한마디로 서버를 구동하기 위한 옵션을 설정하는 헬퍼 클래스  
- 채널핸들러에서 read, readComplete, exceptionCaught 등 처리를 할 수 있는데 예외의 경우 핸들러 체인 어디에서도 처리되지 않을 경우지 수신된 예외가 파이프라인의 끝까지 이동한후에 로깅한다.

- simpleChannelInboundHandler와 ChannelInboundHandler 비교
    + channelRead 시점에 netty의 설정에 따라 handler는 heap memory 혹은 native memory 영역에 할당된 byteBuf를 참조한다.
    + simpleChannelInboundHandler는 channelRead()를 완료하고 반환하는 시점에 핸들러가 참조한 byteBuf에 대한 메모리 참조를 해제한다. 

- [에코 서버/클라이언트 예제 구현해보기](https://swiftymind.tistory.com/56?category=694951)     
   

## 3장. 네티 컴포넌트 설계

- 네티는 자바 NIO 기반의 비동기식 이벤트 기반 구현을 이용해 고부하 조건에서도 애플리케이션의 성능과 확장성을 최대한 보장한다.
- 네티는 애플리케이션 논리를 네트워크 레이어로부터 분리하는 다양한 설계 패턴을 활용해 테스트 용이성, 모듈성, 재사용성을 극대화해 개발을 간소화한다.

- channel 인터페이스: 자바 기반 네트워크에서 기본 구조는 socket 클래스. 채널 인터페이스는 소켓 작업의 복잡성을 완화한다.
- EventLoop 인터페이스: 이벤트루프는 연결의 수명주기 중 발생하는 이벤트를 처리하는 네티의 핵심 추상화를 정의한다. 생명주기는 다음과 같다.
    + 하나의 이벤트 루프 그룹은 하나 이상의 이벤트루프를 포함한다.
    + 한 이벤트 루프는 라이프사이클 동안 하나의 스레드로 바인딩된다.
    + 한 이벤트 루프에서 처리되는 모든 입출력 이벤트는 해당 전용 쓰레드에서 처리된다.
    + 한 채널은 수명주ㅜ기 동안 한 이벤트루프에 등록할 수 있다.
    + 한 이벤트 루프는 한 채널 이상 할당할 수 있다.
    
- ChannelFuture 인터페이스: 네티의 모든 작업은 비동기다.즉 작업이 즉시완료되지 않은 상태에 대해 ChannelFuture를 통해 처리할 수 있다.
- ChannelHandler 인터페이스: 개발자 관점에서 네티의 핵심 컴포넌트는 데이터 처리가 적용되는 ChannelHandler이다. 네트워크 이벤트(입 출력, 연결 등) 채널 핸들러의 메서드가 트리거 된다.
- ChannelPipeline 인터페이스: ChannelHandler 체인을 위한 컨테이너를 제공하며, 체인 상에서 인바운드 혹은 아웃바운드 이벤트를 전파한다. 채널이 생성되면 자동으로 파이프라인이 할당된다.
- Encoder, Decoder: 네티는 메시지를 전송하거나 수신할 때 데이터를 변환해야 한다. 인바운드 메시지는 바이트에서 다른 포맷으로 변환되는 디코딩을 거칠 수 있고, 아웃바운드 메시지는 다른 포맷에서 바이트로 변환 될 수 있다.(인코더와 디코더는 네티의 가장 강력한 컴포넌트 중 하나이다.)
- BootStrap: 부트스트랩 클래스는 프로세스를 지정된 포트로 바인딩하거나 다른 호스트로 연결하는 등 네트워크 레이어를 구성하는 컨테이너를 제공한다.   

## 4장. 전송
- 네티의 전송방식
    + OIO (Old IO) : 블로킹 전송(동기). java.net 패키지 기반으로 이용
    + NIO (New IO) : 논블로킹 전송(비동기). java.nio.channel 기반으로 이용 - 셀렉터 방식
    + 로컬 전송 : JVM 내에서 비동기 통신. VM에서 파이프를 통해 통신하는데 이용하는 로컬 전송
    + 임베디드 전송 : ChannelHandler 테스트. 실제 네트워크 기반 전송 없이 ChannelHandler를 이용 할 수 있게 해주는 임베디드 전송. 주로 채널핸들러 내부에서 전송이 이뤄지는지 단위테스트 용도로 주로 사용된다.  
    + EPoll : 논블로킹 전송. epoll()가 논블로킹 입출력을 위해 JNI를 이용. 이전송은 SO_REUSEPORT와 마찬가지로 리눅스에서만 이용할 수 있음.
        + Epoll은 기존의 POSIX select 및 Poll 시스템 호출에 비해 높은 성능을 제공하는 API로 리눅스 커널 2.544에 버전에 도입됐고 리눅스 논블로킹 네트워크에서 사실상 표준으로 자리 잡았다.
        + 리눅스 JDK NIO API는 이러한 Epoll 호출을 이용한다,
        + 만일 JDK 사용이 리눅스 플랫폼으로 한정된다면, 네티의 EPOLL API 사용을 고려하여 고부하 조건의 우수한 성능을 기대할 수 있다.

- 네트워크를 통해 전송하는 데이터는 모두 동일한 형식(바이트)이다.
- Java API로 동기, 비동기 전송을 구현하고자 한다면 구현코드가 매우 달라지고 복잡성이 증가한다. 하지만 네티는 동일성있는 코드로 모든 전송의 구현에 동일한 API를 노출하므로 전송 방식을 변경해도 코드는 거의 영향이 없다.

- 채널 API는 네티 기반 전송 API의 핵심 인터페이스이다. Channel에 채널 파이프라인과 채널 Config가 할당된다. 채널은 고유하므로 정렬 순서를 보장하기 위해 Comparable의 하위 인터페이스로 선언하고, 등록된 고유한 두 채널 인스턴스가 동일한 해쉬 코드를 반환하면 AbstractChannel의 CompareTo 메소드에 의해 에러가 발생한다.
- 가로채기 필터: 채널 핸들러 인스턴스를 필요에 따라 파이프라인에 추가하거나 제거할 수 있다. 일명 동적 파이프라인이라고 일컫는데, 잘 활용하면 유연한 애플리케이션을 개발 할 수 있다.
- 네티의 채널은 쓰레드에 안전하다.

## 5장. ByteBuf

- 네티의 기본 데이터 전송방식은 항상 바이트
    + 복잡한 자바 NIO API ByteBuffer를 대체하는 ByteBuf 추상클래스와 ByteBufHolder 인터페이스를 제공한다.

> 장점
- 사용자 정의 버퍼 형식으로 확장 할 수 있음
- 내장 복합 버퍼 형식을 통해 투명한 제로 카피를 달성할 수 있음
- 용량을 필요에 따라 확장할 수 있음
- ByteBuffer의 flip 메소드를 호출하는 것 없이 간단하게 read,write 모드를 전환할 수 있음
- 읽기와 쓰기에 고유 인덱스를 적용함
- 메소드 체인이 지원
- 참조 카운팅 지원
- 풀링 지원

> 인덱스
- ByteBuf 배열에는 인덱스가 존재하며, 읽기/쓰기 시에 인덱스가 증가하며 ByteBuf index의 범위 밖에서 읽으려면 exception이 발생함

> 메모리 할당 및 해제 
- 네티는 메모리 할당과 해제 시 발생하는 오버헤드를 줄이기 위해 ByteBufAllocator 인터페이스를 통해 풀링을 구현할 수 있다.
- 기본적으로 PooledByteBufAllocator를 이용하지만 ChannelConfig 혹은 bootstrap을 통해 다른 할당자를 지정할 수 있다.

> 사용 패턴

### 1. 힙버퍼

- JVM 힙영역에 데이터를 저장한다.
- 풀링이 사용되지 않은 경우 빠른 할당과 해제 속도를 보여준다.
- 다이렉트 버퍼에 비해 느릴 수 있음. 하지만 메모리 영역의 관리가 jvm heap에 포함되기 때문에 관리는 용이
```java
ByteBuf heapBuf = ...;

if(heapBuf.hashArray()){  // 보조배열이 있는지 여부 체크
    byte[] array = heapBuf.array(); // 참조 할당
    ...
}
```

### 2.다이렉트 버퍼

- 네이티브 영역에 메모리 할당을 허용
- 그렇기 때문에 장점은 결국 성능. 입출력 속도가 빠르다. 
- 다이렉트 버퍼의 내용이 내용은 일반적으로 가비지 컬렉션이 적용되는 힙 바깥에 위치한다.
- 데이터가 힙 할당 버퍼에 있으면 JVM은 소켓을 통해 전송하기 전 내부적으로 버퍼를 다이렉트 버퍼로 복사한다.
- 단점은 관리가 어렵다.(메모리의 할당 및 해제의 부담 비용)

```java
ByteBuf directBuf = ...;

if(!directBuf.hashArray()){  // 보조배열이 없으면 다이렉트 버퍼
    byte[] array = new Byte[directBuf.readableBytes()];
    directBuf.getBytes(directBuf.readerIndex(), array);     // array 배열로 복사
    ...
}
```

### 3. 복합 버퍼

- ByteBuf 인스턴스를 필요에 따라 추가 및 삭제를 할 수 있다.
    + CompositeByteBuf를 이용해야 하는데 경험에 비춰 보면 바이트 헤더와 바디의 분리를 예를 들 수 있다. 
    + 일반적으로 바이트 데이터를 보낼 때 헤더에 사이즈를 지정해서 보내는데 ByteBuf 헤더와 바디를 구분하여 불필요하게 재생성 및 할당을 할 필요가 없다.

```java
// 복합 패턴을 이용한 byteBuf 생성 
CompositeByteBuf messageBuf = Unpooled.compositeBuffer();)
ByteBuf headerBuf = ...;
ByteBuf bodyBuf = ...;

messageBuf.addComponent(headerBuf, bodyBuf);
messageBuf.removeComponent(0); //header buf 삭제


```

> ByteBufHolder 인터페이스

- 실제 데이터 페이로드와 함께 다양한 속성 값을 저장해야 하는 경우가 많다.
    + 예를 들면 http response의 경우 상태 코드 및 쿠키

> 참조 카운팅
- 다른 객체에서 더 이상 참조하지 않ㄴ는 객체가 보유한 리소스를 해제해 메모리 사용량과 성능을 최적화하는 기법
- 일반적으로 활성 참조 카운트 1을 가지고 시작하며, 참조 카운트가 1 이상이면 객체가 해제되지 않지만 0으로 감소하면 인스턴스가 해제된다.
- 해제의 책임은 일반적으로 객체에 마지막으로 접근하는 영역에 있다.
- 특정 객체에 대한 활성 참조의 수를 추적하는데 바탕을 둔다.


   
## 6장. ChannelHandler 와 ChannelPipeline

###  Channel 수명주기

-  ChannelRegister -> ChannelActive -> ChannelInactive ->  Channel UnRegister

- ChannelRegister : 채널이 EventLoop에 등록됨
- ChannelActive : 채널이 활성화됨(피어와 연결됨)
- ChannelInactive : 피어와 연결되지 않음
- ChannelUnRegistered : 채널이 생성됐지만 eventloop에 등록되지 않음

### ChannelHandler 생명주기

- HandlerAdded : 파이프라인에 채널 핸들러가 추가될 때 호출됨
- HandlerRemoved : 파이프라인에 채널 핸들러가 제거될 때 호출됨
-  ExceptionCaught  : 채널파이프라인에서 처리 중 에러가 발생했을 때 호출됨

> InboundHandler

- 인바운드 핸들러는 모든 유형의 인바운드 데이터와 상태 변경을 처리한다.
- 인바운드 핸들러 인터페이스는 보통 ChannelInboundHandlerAdapter, SimPleChannelInboundHandler를 통해 구현한다.

- ChannelInboundHandlerAdapter를 상속받아 channelRead를 구현한 경우, byteBuf의 release를 명시적으로 구현해야 한다.
```java
public class DisCardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ReferenceUtils.release(msg);
    }
}
```

- 그러나 이렇게 매번 리소스를 관리하기는 여간 어려운게 아니다. 그래서 SimPleChannelInboundHandler를 사용하면 명시적으로 해제하는 작업을 하지 않아도 된다.

> OutBoundHandler

- 아웃바운드 데이터를 처리하고 모든 작업의 가로채기를 허용한다.

- ChannelOutBoundHandler는 주문식으로 작업이나 이벤트를 지연하는 강력한 기능이 있어 정교하게 요청을 처리할 수 있다. 예를 들어 원격 피어에 대한 기록이 일시 중단된 경우 플러시 작업을 지연하고 추후 재개할 수 있다.

> ChannelHandlerAdapter

- ChannelHandler를 작성하는 시작점으로 ChannelInboundAdapter와 ChanelOutBoundAdpter 클래스를 이용할 수 있다.
- 각각 어뎁터는 ChannelHandler 인터페이스를 구현한 인터페이스이다.
- 각 인터페이스에서 제공되는 메서드 본문에서는 구현된 ChannelHandlerContext의 해당 메소드를 호출해 이벤트를 파이프라인의 다음 채널 핸들러로 전달한다.

### 리소스 관리

- 각 핸들러의 ChannelRead 또는 write를 호출해 데이터를 대상으로 작업할 때는 리소스 누출이 발생하지 않게 주의해야 한다.
- 앞서 살펴본 것 처럼 네티는 referenceCount를 통해 풀링되는 bytebuf를 관리하는데, 사용이 끝난 bytebuf에 대해 참조 카운트를 조정하는 것은 중요하다.

- 그래서 네티는 잠재적인 문제 진단을 돕기 위해 애플리케이션 버퍼 할당의 약 1%를 샘플링해 메모리 누출을 검사하는 ResourceLeakDetector 클래스를 제공한다.
- 누출 감지 수준은 다음과 같으며 jvm option으로 조정이 가능하다. 

> java -Dio.netty.leakDetectionLEVEL=ADVANCED

- DISABLED : 누출 감지를 비활성화한다. 이 설정은 포괄정인 테스트를 거친 후에만 이용한다.
- SIMPLE : 기본 샘플링 비율 1%를 이용해 발견된 누출을 보고한다. 기본 샘플링 비율은 대부분 경우에 적합하다.
- ADVANCED : 발견된 누출과 메시지에 접근한 위치를 보고한다. 기본 샘플링 비율을 이용한다.
- PARANOID : ADVANCED와 비슷하지만 모든 접근을 샘플링한다. 성능에 큰 영향을 미치므로 디버그 단계에서만 이용한다.

### ChannelPipeLine

- ChannelPipeLine을 채널을 통해 오가는 인바운드 아웃바운드 이벤트를 가로채는 채널핸들러 인스턴스의 체인이라고 생각하면, 채널 핸들러의 상호작용을 쉽게 이해할 수 있다.
- 새로운 채널이 할당 될 때마다 새로운 파이프라인이 할당된다. 이 연결은 영구적이며 네티 수명주기에 고정된 작업이므로 개발자가 관여하지 않아도 된다.

- 채널 파이프라인의 이벤트 전달 방향은 인바운드는 왼쪽에서 오른 쪽 끝으로, 아웃바운드는 오른쪽 끝에서 왼쪽으로 잔달된다.
- 채널 파이프라인은 유연하게 추가/삭제가 가능해서 손쉽게 동적으로 재구성이 가능하다.

> 채널 핸들러 실행과 블로킹

- 일반적으로 채널 파이프라인의 핸들러는 이벤트 루프를 통해 처리 되기 때문에, 전체 성능의 부정적인 영향을 방지하기 위해 이 스레드가 블로킹되지 않게 하는 것이 중요하다.
- 하지만 레거시 코드와 인터페이스 해야할 경우가 있는데, 이와 같은 경우에 채널 파이프라인에 EventExecutorGroup를 받는 add 메소드가 있다.
- 이벤트가 커스텀 이벤트 그룹으로 전달되면, 이벤트 그룹에 포함된 executor 중 하나에 의해 처리되며 채널의 이벤트루프에서는 제거된다.
- defaultEventExecutorGroup을 통해 구현할 수 있다.

### ChannelHandlerContext

- CTX(ChannelHandlerContext) 채널핸들러와 파이프라인 간의 연결을 나타내며 채널 핸들러를 파이프라인에 추가 할 때마다 생성된다.
- 주된 기능은 연결된 핸들러와 동일 파이프라인 내의 다른 핸들러간의 상호작용이다.
- 요약하면, ChannelHandlerContext는 핸들러마다 존재하며 파이프라인 내 다른 핸들러에게 이벤트를 전파할 수 있다.

### @Sharable

- @Sharable 어노테이션을 통해 여러 파이프라인에서 단일 채널 핸들러를 공유할 수 있다.
    : 같은 채널 핸들러를 여러 채널에 공유하는 이유는 일반적인 이유로 어러 채널에서의 통계 정보를 얻기 위한 목적


### 예외 처리

- inboundhandler에서의 exceptionCaught 메소드 override를 구현하여 이벤트의 예외(연결, 읽기, 종료.. 등)를 감지할 수 있다.
- 핸들러에서 예외가 발생하면 다음 핸들러로 이벤트는 전달되지 않는다.

- outBoundHandler에서는 다음 메커니즘을 따른다.
    + 모든 아웃바운드 작업은 ChannelFutureHandler를 반환하고, 작업이 완료되면 리스너를 통해 성공이나 오류에 대한 알림을 제공한다.
    + 아웃 바운드 핸들러의 거의 모든 메소드는 channelPromise가 전달된다.
 
> ChannelFuture 리스너를 통한 예외 처리 예 : 채널을 통해 이벤트를 전파할 경우 자주 쓰임.

```java
    ChannelFuture future = channel.writeAndFlush(byteBuf);
    
    future.addListener(new ChannelFutureListener() {  
    
        @override
        public void operationComplete(ChannelFuture f) {
            if(!f.isSuccess()){
                f.cause().printStackTrace();
                ...
            }
        }
    })
```

> ChannelPromise 예 : 아웃바운드 자체 내에서 예외처리를 할 경우 promise를 통해 처리함.

```java
public class OutBoundExceptionHandler extends ChannelOutBoundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise){
        promise.addLisener(new ChannelFutureListener(){
           @Override
            public void operationComplete(ChannelFuture f){
               if(!f.isSuccess()){
                   f.cause().printStackTrace();
                    ..
               }
            }
        });
    }

}
```


## 7장. EventLoop 와 스레딩 모델

## 8장. 부트스트랩

## 9장. 단위테스트

## 10장 ~ 11장. 코덱

## 12장. 웹소켓

## 13장. UDP를 이용한 이벤트 브로드 캐스팅
