# URL SHORTENING SERVICE EXAMPLE

## 개발 환경

- 언어 : JAVA SPRING(JDK 1.8)
- Framework : Spring boot 2.1.5 RELEASE, JPA & Hibernate
- Database : h2 database
- 의존관리 : Maven 3.1.1

## 기능 및 설명서

[문제 1번] - webapp으로 개발하고 URL 입력폼 제공 및 결과 출력한다.
[문제 1번 설명서]
 1. 입력폼은 jsp 페이지로 구현하였으며, input box의 url을 입력한다.
 2. http://kakao.com/Url_shorting -> 입력 시 http://localhost:8080/U2achOsq 출력된다.

[문제 2번] - URL Shortening Key 8 Character 이내로 생성되어야 한다.
[문제 2번 설명서]
 1. 8자리 문자열을 추출 할 때, 마지막 "/" 문자를 저장 할 하나의 변수를 생성한다.
 2. 숫자 10개, 소문자 영문 26개, 대문자 영문 26개의 값은 62개이다.
 3. 랜덤함수를 이용하여 62개의 값 중 8개를 임의로 추출한다.
 4. 마지막으로 생성한 변수의 추출한 문자를 저장한다. 
 
[문제 3번] - 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답한다.
[문제 4번] - Shortening된 URL을 요청받으면 원래 URL로 리다이렉트한다.
[문제 3,4번 설명서] 
 1. h2 database의 기존에 등록된 아래의 URL을 저장한다.
  -> 입력 : http://kakao.com/Url_shorting  -> 출력 : http://localhost:8080/U2achOsq
 2. URL Shortenering 된 URL을 입력 시 리다이렉트한다.
  -> 입력 : http://localhost:8080/U2achOsq -> 출력 : http://kakao.com/Url_shorting
  
[문제 5번] - database 사용은 필수 아님(선택)
[문제 5번 설명서]
 1. database 사용은 h2 database를 사용하였습니다.
 2. database 경로는 application.properties 설정파일에 저장되어 있습니다.

### Example

- INPUT : http://kakaopay.com/demo
- OUTPUT : http://localhost:8080/zswHRas2

- INPUT : http://localhost:8080/zswHRas2
- OUPUT : http://kakaopay.com/demo

## 프로그램 빌드 및 실행방법

 1. github 프로젝트를 다운받는다.
 2. 최상위 경로로 이동한다.
 3. mvnw package 실행하여 빌드한다.
 4. mvnw spring-boot:run 실행하여 스프링부트를 실행한다.
 5. 아래의 테스트 URL 정보를 입력하여 확인한다.

#### 빌드 및 실행완료 후 테스트 URL

 1. MAIN URL : http://localhost:8080/urlShortener/main
 2. h2 Database 조회 URL : http://localhost:8080/urlShortener/info

#### Unit test 코드작성은 h2 database의 저장된 json data 조회하는 코드만 작성

#### jdk 버전확인(해당 애플리케이션은 JDK1.8기준)

#### 실행화면 테스트 화면(image 폴더 참고)
 1. main_IMG.png           -> main 화면
 2. Url_Shortener_IMG.PNG  -> 출력 시 화면
 3. database_Info_IMG.PNG  -> 현재 h2 database 저장된 화면
