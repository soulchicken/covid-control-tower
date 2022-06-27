# covid-control-tower
 코로나 컨트롤 타워

## **00. 팀원 명단**

-   **[김동현](https://github.com/soulchicken)** 
-   **[권준혁](https://github.com/junhyeukkwon)** 
-   **[박재운](https://github.com/uiet312)** 
-   **[김현진](https://github.com/Hyeonjin-ee)** 
-   **[소병권](https://github.com/SOBEUNGKEUN)** 

## **01. 기술 스택**

-   Maven
-   jdk 11.0.14 (JAVA 11)
-   SPRING BOOT 2.7.0
-   SPRING 5.3.20
-   MySQL 8.0.28
-   JDBC
-   hibernate
-   lombok
-   jackson.core 2.13.3

## **02. 프로젝트 주제**

### **covid control tower**
- 프로젝트 주제를 기록하는 곳

## **03. 도메인 관련 용어**

- **👨 환자(patient)** : 코로나 확진 판정을 받은 사람
- **👨 병원(hospital)** : 코로나 환자들 치료 가능한 병원
- **👨 병실(hospitalroom)** : 코로나 환자들 입실 가능한 병실
- **👨 위험환자(danger)** : 코로나 증상이 심한 환자
- **👨 자가격리(self_quarantine)** : 코로나 확진 후 자가격리자
- **👨 감염경로(infectiontracking)** : 코로나 확진자 감염 경로
- **👨 관리자(manager)** : 코로나 환자 관리자
- 기타 등등 도메인 설명

## **04. 기능 명세**
[API DOCS] - 제작하기

### 무슨 기능
- 무슨 설명

## **05. ERD (Entity Relationship Diagram)**
- ERD 그림 캡처본![최종안](https://user-images.githubusercontent.com/64244851/175840178-41868100-de85-4533-83be-76d58f2e6705.JPG)

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |

- **👨 환자(patient)** : 코로나 확진 판정을 받은 사람

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 병원(hospital)** : 코로나 환자들 치료 가능한 병원

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 병실(hospitalroom)** : 코로나 환자들 입실 가능한 병실

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 위험환자(danger)** : 코로나 증상이 심한 환자

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 자가격리(self_quarantine)** : 코로나 확진 후 자가격리자

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 감염경로(infectiontracking)** : 코로나 확진자 감염 경로

### **patient** 테이블                                  
| column          | data type | 설명      |        
|-----------------| --- |---------|
| user_index (PK) | BIGINT | 고유 인덱스  |
| user_id         | VARCHAR(45) | 회원 아이디  |
| user_name       | VARCHAR(45) | 회원 이름   |
| password        | VARCHAR(45) | 회원 비밀번호 |
- **👨 관리자(manager)** : 코로나 환자 관리자

## **06. 트러블 슈팅**
1. 트러블 슈팅 내용을 기록

## **07. 참고 자료**
- [커밋 컨벤션 DOCS](https://udacity.github.io/git-styleguide/)
- [커밋 컨벤션 정리자료](https://overcome-the-limits.tistory.com/entry/%ED%98%91%EC%97%85-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B8%B0%EB%B3%B8%EC%A0%81%EC%9D%B8-git-%EC%BB%A4%EB%B0%8B%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)
- [자바 컨벤션 DOCS](https://naver.github.io/hackday-conventions-java/)
