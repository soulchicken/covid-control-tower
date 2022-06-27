# covid-control-tower
 코로나 컨트롤 타워

 <br/>

## **00. 팀원 명단**

<br/>

-   **[김동현](https://github.com/soulchicken)** 
-   **[권준혁](https://github.com/junhyeukkwon)** 
-   **[박재운](https://github.com/uiet312)** 
-   **[김현진](https://github.com/Hyeonjin-ee)** 
-   **[소병권](https://github.com/SOBEUNGKEUN)** 

<br/>

## **01. 기술 스택**

<br/>

- <img src="https://img.shields.io/badge/jdk 11.0.14 (JAVA 11)-142d97?style=flat-square&logo=Java&logoColor=white"/>
- <img src="https://img.shields.io/badge/MAVEN-a52a2a?style=flat-square&logo=Maven&logoColor=white"/>
- <img src="https://img.shields.io/badge/Springboot 2.7.0-76cd51?style=flat-square&logo=Springboot&logoColor=white"/>
- <img src="https://img.shields.io/badge/Spring 5.3.20- 76cd51?style=flat-square&logo=Spring&logoColor=white"/>
-  <img src="https://img.shields.io/badge/MySQL 8.0.28-6f8eec?style=flat-square&logo=MYSQL&logoColor=white"/>
-  <img src="https://img.shields.io/badge/POSTMAN-fc9303?style=flat-square&logo=postman&logoColor=white"/>


<br/>

## **02. 프로젝트 주제**

### **covid control tower**
- 코로나 시국에 컨트롤 타워 입장에서 통제, 관리를 하는 방식에 대해서 스터디하고 최소한의 방식으로 구현했습니다.
- 환자 관리자, 병원, 고위험군, 자가격리자 등에 대한 연관 관계를 데이터베이스 상에 구현
- 스프링 API를 통해 백엔드단에서 할 수 있는 데이터 관리를 구현
- 공용 데이터베이스 서버를 만들어서 한 DB를 각자의 로컬에서 사용
<br/>

## **03. 도메인 관련 용어**

- **👨 환자(Patient)** : 코로나 확진 판정을 받은 자
- **👨‍💼 환자관리자(Manager)** : 코로나 확진을 받은 환자를 관리하는 관리자
- **🏤 병원(Hospital)** : 코로나 고위험군으로 판별된 환자들을 치료나 격리 할 수 있는 병원
- **🏢 병실(Hospitalroom)** : 코로나 고위험군으로 판별된 환자들이 치료나 격리 목적으로 입원하는 병원 내 병실
- **📝감염경로(Infectiontracking)** : 코로나 감염이 예상되는 경로들 
- **🏡자가격리(Self_qurantine)** : 코로나 확진 판정을 받은 환자 중 증상이 경미해 집에서 격리하는 환자
- **😡고위험군(Danger)** : 코로나 확진 판정을 받은 환자 중 증상이 심한 환자

## **04. 기능 명세**
[API DOCS](https://documenter.getpostman.com/view/21185842/UzBsHjMc) - PostMan API Documents

### 환자 관련기능
- 환자의 이름 및 개인정보를 입력하면 환자 테이블에 저장한다
- 환자의 고유번호를 이용하여 환자정보 조회 및 수정, 삭제 가능하다
### 병원 관련기능
- 환자들 중에서 고위험군 환자를 관리
- 병원의 고유번호를 통해서 병실 갯수, 환자 수 조회 및 수정, 삭제 가능하다
- 병실(hospitalroom) 조회를 통하여 환자 정보를 조회 할 수 있다
### 환자 추적 기능
- 감염된 환자의 동선을 조회 할 수있다
- 환자가 감염된 날짜, 장소를 조회 및 수정, 삭제 할 수 있다
### 자가격리 및 고위험군 환자 관리기능
- 날짜를 통하여 고위험군 환자, 자가격리환자들을 조회 할 수 있다
- 이름 및, 고유번호를 통하여 환자정보 조회 가능하다
- 환자의 정보들을 입력, 수정 및 삭제 할 수 있다
### 에러제어 기능
- 에러가 발생했을 때 특정 메세지를 출력하게하여 에러 통제 및 관리를 할 수 있다.
- 에러발생 장소 및 내용 보다 쉽게 파악가능하다.

## **05. ERD (Entity Relationship Diagram)**
 ![image](https://user-images.githubusercontent.com/85923524/175849491-49e9314f-71e7-43ea-aa76-f71a7243886d.png)

<br/>


### **Patient** 테이블
| column          | data type | 설명      |
|-----------------| --- |---------|
| people_id (PK) | LONG | 고유 인덱스  |
| people_name         | VARCHAR(45) | 환자 이름  |
| people_gender       | TINYINT     | 환자 성별   |
| people_home        | VARCHAR(45) | 환자 주소 |
| people_phone       | INT         | 환자 전화번호 |
| people_isdanger   | TINYINT      | 환자 위험군여부|
 
-  환자 정보가 저장된다.
-  환자 정보의 매니저 정보가 없으면 등록 안된다.

<br/>

### **Manager** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| manager_id (PK) | LONG | 고유 인덱스  |
| manager_name    | VARCHAR(45) | 환자관리자 이름  |
| manager_phone   | VARCHAR(45) | 환자관리자 전화번호  |

- 매니저 정보가 저장된다.

<br/>

### **Hospital** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| hosipital_id (PK) | LONG | 고유 인덱스  |
| hosipital_name   | VARCHAR(45) | 병원 이름  |
| hosipital_patientnum   | INT | 병원 내 환자 수  |
| hosipital_roomlimit    | INT | 병원 내 남은 병실 |

- 병원 정보가 저장된다.
- 환자들의 정보를 가지고 있다.

<br/>

### **Hospitalroom** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| hosipitalroom_roomnumber (PK) | LONG | 고유 인덱스  |
| hosipitalroom_capacity         | LONG | 병실 수용 인원  |

- 병실 정보가 저장된다.
- 병원 정보를 가지고 있다.
- 고위험군 환자들의 정보를 가지고 있다.

<br/>

### **Infectiontracking** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- 감염환자 동선 및 장소 정보가 저장된다.
- 환자의 정보를 가지고 있다.

<br/>


### **Self_qurantine** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| self_qurantine_id (PK) | LONG | 고유 인덱스  |
| self_quarantine_people_name         | VARCHAR(45) | 자가격리자 이름  |
| self_quarantine_date       | LOCALDATE | 자가 격리 시작일   |
| self_quarantine_release        | LOCALDATE | 자가 격리 해제일 |
- 자가격리자의 격리기간 정보가 저장된다.
- 환자의 정보를 가지고 있다.

<br/>

### **danger** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| danger_id (PK) | LONG | 고유 인덱스  |
| danger_care_date         | LOCALDATE | 고위험 환자 치료 시작일  |
| danger_care_release      | LOCALDATE | 고위험 환자 치료 중단일   |

- 고위험군 환자의 격리기간 정보가 저장된다.
- 병실의 정보를 가지고 있다.
- 환자의 정보를 가지고 있다.

<br/>

## **06. 트러블 슈팅**
1. 무한 루프 : 테이블들을 서로 매핑해주는 과정에서 Entity를 return했을 때 객체 안에 다른 Entity를 return해주는 무한 순환 참조가 발생됨. **DTO패턴** 을 사용하여 Entity를 직접 참조하지 않고도 return할 수 있게 코드를 변경하여 무한 루프를 해결.   
2. 타입 에러 : 내장함수를 사용할때 타입이 불일치하여 사용 못하는 문재 발생하여
**.orElseThrow(Exception::new)** 을 사용하여 해결.   
3. 에러 관리 : 에러발생시 출력되는 에러문을 정확히 이해하지 못하는 현상 발생하여 **log**문을 따로 출력하여 에러문을 보기쉽게 관리 및 통제   
4. 인스턴스 서버 연결 : 각자의 로컬 서버로 데이터베이스를 관리하는 부분에서 서로 필요한 부분에 데이터가 안 들어가거나 테이블이 미완성 되는 문제를 해결하고자 하나의 인스턴스 서버로 하나의 데이터베이스를 여러 사용자가 이용을 하여 테이블의 부재를 방지.

<br/>

## **07. 느낀점**
- **👨김동현** : 하고싶었던 에자일 방법론, 리펙토링, 빠른 사이클로 진행되는 폭포수 & 분할정복을 해볼 수 있는 기회였습니다. 오랜 시간 회의를 하는 것이 아닌 중간중간 10분, 20분씩 회의하면서 빠른 체킹을 하는 과정을 했는데, 비교적 원활하게 프로젝트가 진행되는 것을 보면서 지금까지의 프로젝트들이 생각보다 늘어지게 진행했었던 것을 알게 됐습니다. 깃 깃허브에 대한 이해도가 높아졌고 테이블 결합에 대한 연습도 충분히 할 수 있었습니다. 다들 고생하셨습니다. 어쩌다보니 팀장이었는데 잘 따라와줘서 감사합니다.


<br/>

- **👨권준혁** : 먼저 테이블을 7개를 엮을 수 있을까라는 걱정이 많았습니다. 같이 팀원들과 진행을 하면서 차근차근 하나씩 일궈 내는 과정이 좋았습니다. 그리고 에러를 통제하기 위해서 SLF4J를 활용해서 에러 로그를 확인 할 수 있는 과정이 좋았습니다. 마지막으로 DAO패턴은 배웠고 DTO 패턴은 개념만 알고 있었던 터라 실제로 적용하면서 처음에는 적응하는 것이 어려웠으나 역시 쓰다보면 왜 필요한지 알게 되었다. 마지막으로 어느 프로젝트든 리펙토링의 중요함을 느꼈습니다. 코드의 가독성이나 코드의 중복성을 줄이는 것이 기능을 구현하는 것도 중요하지만 못지 않게 불필요한 코드를 정리하는 것도 중요하다는 것을 느끼는 프로젝트 였습니다. 많은 경험을 할 수 있게 도와준 동현 재운 현진 그리고 병권께 감사를 드립니다.

<br/>

- **👨박재운** : 테이블 설계, CRUD, Table-Join 등 백엔드 단에서 데이터가 돌아가는 전반적인 과정을 학습할 수 있던 좋은 프로젝트가 되었다. 처음에는 기능을 구현하는 과정에서 막막한 감이 있었는데, 하나씩 단계를 밟아 나가면서 진행하니 조금 수월하게 할 수 있었던 것 같다. 다양한 기능들을 추가하는 과정에서 어려움이 있었는데 팀원들의 도움으로 하나씩 해결해 나아가니 협업의 중요성도 깨닫는 등 많은 것들을 배워간 것 같다. 프로젝트 이전부터 백엔드 단에서 데이터가 어떻게 돌아가는지 많이 궁금했었는데, 이번 프로젝트를 통해 이전부터 궁금한 것들을 많이 알게 된 것 같아서 정말 많은 것을 배워간 프로젝트인 것 같다.

<br/>

- **👨김현진** : 프로젝트 주제를 정할 당시, JPA 미니프로젝트를 끝낸 직후였어서 테이블 7개를 조인한다는 게
정말 가능할까?라는 생각이 먼저 들었었습니다. 하지만 단계별로 테이블을 하나씩 맡아서 CRUD작성, 조인, Controller, DTO작성 등 
많은 도움을 받으며 작성하다보니 아 이게 가능하구나라는 걸 몸소 느끼는 프로젝트였습니다. 수업 중간중간 쉬는 시간마다 소회의실에서
진행된 회의도 이번 프로젝트가 잘 진행될 수 있었던 요인이 아니었나 싶습니다. 프로젝트들마다 하는 일이 없이 느껴져서 스스로 많이 위축이 되어 있었는데 이 스터디를 통해서 성장할 수 있어 좋았습니다. 저희 팀원들 감사합니다 !


<br/>

- **👨소병권** : 이번 코로나 사태를 겪으면서 중앙 컨트롤타워 역할에 중요성을 크게 느껴서 이번주제를 정하게 되었다. 수많은 사람들을 체계적으로 관리하는 시스템을 구현하고 싶은 욕구가 생겼다. 프로젝트를 진행하면서 자주 회의를 진행한게 큰 도움이 되었다. 보다 자주 의견교환을 할 수 있었고, 서로 빠르게 피드백을 주고 받을 수 있었다. 이전부터 강력한 백엔드 기능을 구현하고 싶었는데 이번 프로젝트를 진행하면서 많은 도움이 되었다. 학원 수업을 들으면서 처음으로 제대로 된 백앤드 기능을 구현하였다. 여러 테이블들을 조인하면서 단순한 CRUD기능도 재밌게 구현할 수 있었다. 이번에 팀원들에 도움을 많이받아서 감사하면서도 아쉬운점이 있었다. 나중에 혼자서도 하나의 관리시스템을 구현하고싶은 목표가 생긴 프로젝트였다.

<br/>

## **08. 참고 자료** 
- [커밋 컨벤션 DOCS](https://udacity.github.io/git-styleguide/)
- [커밋 컨벤션 정리자료](https://overcome-the-limits.tistory.com/entry/%ED%98%91%EC%97%85-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8%EC%A0%81%EC%9D%B8-git-%EC%BB%A4%EB%B0%8B%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)
- [자바 컨벤션 DOCS](https://naver.github.io/hackday-conventions-java/)