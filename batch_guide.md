# Spring Boot Batch Guide

### 스프링 배치 동작 구조
<img src="https://velog.velcdn.com/images%2Fgkskaks1004%2Fpost%2Ffa42e4ee-a137-4be7-bc9e-73b3197588e1%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-07-22%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%2011.42.02.png">

- ### Job
    * 배치 처리 과정을 하나의 단위로 만들어 표현한 객체이다.
    * 하나의 Job객체는 여러 Step 인스턴스를 포함하는 컨테이너이다.


- ### JobRepository
    * 배치 처리 정보를 담고 있다. Job이 실행되었으면 배치 처리에 대한 모든 메타 데이터가 담겨있다.


- ### JobLauncher
    * 배치를 실행시키는 인터페이스로 클라이언트의 요청을 받아 Job을 실행하는 객체이다.
    * Job, 파라미터를 받아서 실행하며 JobExecution을 반환한다.


- ### Step
    * Job 내부에 구성되어 실제 배치 작업 수행을 정의하고 제어한다. Job을 처리하는 단위이다.
    * 독립적이고 순차적인 단계를 캡슐화하는 도메인 객체이다.
    * 청크 지향 프로세싱 방식이 아닌 tasklet 방식으로 구현하고자 한다면 Step 내부에서 chunk() 대신 tasklet()을 사용한다.


- ### Item
    * 처리할 데이터의 가장 작은 요소
      * ItemReader
* Step의 대상이 되는 배치 데이터를 읽어오는 인터페이스이다. 읽어올 item이 없을 때는 read() 메서드에서 null을 반환하며 그 전까지 순차적인 값을 리턴한다. 지정된 청크 사이즈 만큼 읽을 수 있다.
```
    public interface ItemReader<T> {
        T read() throws Exception;
    }
```


- ### ItemWriter
    * ItemReader에서 읽어온 값을 Insert, Update 처리한다. 리스트의 데이터 수는 설정한 청크 단위로 불러와 사이즈 만큼 처리할 수 있다.
```
    public interface ItemWriter<T> {
        void write(List<? extends T> items) throws Exception;
    }
```


- ### ItemProcessor
    * ItemReader에서 읽어 들인 Item에 대해 ItemWriter 하기 전에 필요한 로직을 처리한다.
```
    public interface ItemProcessor<I, O> {
        O process(I item) throws Exception;
    }
```

<img src="https://velog.velcdn.com/images%2Fgkskaks1004%2Fpost%2F05ed18af-a9c6-46a7-8df8-67d2bf91f675%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-07-22%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%204.00.27.png">

### [참고](https://javabom.tistory.com/114)