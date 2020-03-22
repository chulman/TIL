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

## 4장. 전송

## 5장. ByteBuf

## 6장. ChannelHandler 와 ChannelPipeline

## 7장. EventLoop 와 스레딩 모델

## 8장. 부트스트랩

## 9장. 단위테스트

## 10장 ~ 11장. 코덱

## 12장. 웹소켓

## 13장. UDP를 이용한 이벤트 브로드 캐스팅
