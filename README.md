# 관연(Gwanyeon) - AI 기반 맞춤형 재활 운동 추천 서비스 (Backend)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

---

## 1. 프로젝트 개요

**관연(Gwanyeon)**은 사용자의 건강 상태와 재활 목표에 맞는 운동 루틴을 AI(GPT)를 통해 생성하고 추천해주는 맞춤형 재활 운동 서비스의 백엔드 API 서버입니다.

사용자는 설문을 통해 자신의 상태를 입력하고, AI는 이를 분석하여 최적의 재활 프로그램을 설계합니다. 또한, 커뮤니티와 운동 영상 라이브러리 기능을 통해 사용자들이 정보를 교류하고 올바른 자세로 운동할 수 있도록 지원합니다.

---

## 2. 주요 기능

- **AI 기반 맞춤 재활 프로그램 추천**: 사용자의 설문 데이터를 기반으로 GPT 모델을 활용하여 개인화된 운동 루틴과 식단(선택 사항)을 생성하고 처방합니다.
- **JWT 기반 사용자 인증/인가**: Spring Security와 JWT(JSON Web Token)를 사용하여 안전한 API 접근 제어를 구현합니다.
- **운동 영상 라이브러리**: 부위별, 종류별 운동 영상을 제공하여 사용자가 올바른 자세를 참고할 수 있도록 합니다.
- **커뮤니티**: 사용자들이 자유롭게 소통할 수 있는 게시판 기능을 제공합니다. (게시글/댓글 CRUD, 좋아요)
- **리뷰 및 평점**: 제공된 재활 프로그램에 대한 사용자 리뷰 및 평점 기능을 제공합니다.

---

## 3. 기술 스택

| 구분 | 기술 |
| --- | --- |
| **Backend** | Java 17, Spring Boot 3.x, Spring Security |
| **Database** | MySQL |
| **ORM** | MyBatis (Mapper XML) |
| **Build Tool** | Maven |
| **Authentication** | JWT (JSON Web Token) |
| **API Documentation** | Swagger (SpringDoc OpenAPI) |
| **AI** | OpenAI GPT API |

---

## 4. 아키텍처 및 파일 구조

프로젝트는 기능별로 패키지를 분리하여 유지보수성과 확장성을 높이는 구조로 설계되었습니다.

```
src/main/java/com/kimnjin/gwanyeon
├── GwanyeonBeApplication.java      // Spring Boot 메인 애플리케이션
├── article                         // 커뮤니티 게시판
├── auth                            // 사용자 인증 (JWT)
├── comment                         // 댓글
├── commons                         // 공통 모듈 (설정, 예외 처리, DTO 등)
│   ├── config                      //   - Security, Swagger, DB 등 설정
│   └── exception                   //   - 전역 예외 처리
├── exercisevideo                   // 운동 영상
├── likes                           // 좋아요
├── recommand                       // (미사용) 추천 관련 기능
├── rehabprogram                    // AI 재활 프로그램
│   └── util/PromptFormatter.java   //   - GPT 프롬프트 생성 유틸리티
├── review                          // 리뷰
├── target                          // 운동 부위
└── user                            // 사용자 정보
```

---

## 5. 데이터베이스 스키마

프로젝트에 필요한 테이블 및 초기 데이터는 아래 SQL 파일을 통해 생성할 수 있습니다.

- **테이블 생성**: `src/main/resources/gwanyeon.sql`
- **초기 데이터**: `src/main/resources/gwanyeon_insert.sql` (운동 부위, 관리자 계정 등)

---

## 6. API 명세

API 명세는 Swagger를 통해 확인하실 수 있습니다. 서버 실행 후, 아래 URL로 접속하여 모든 API 엔드포인트와 요청/응답 형식을 테스트할 수 있습니다.

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

---

## 7. 시작하기

### 7.1. 사전 요구사항

- Java 17
- Maven
- MySQL

### 7.2. 설치 및 실행

1.  **프로젝트 클론**
    ```bash
    git clone https://github.com/{your-username}/gwan-be.git
    cd gwan-be
    ```

2.  **데이터베이스 설정**
    - MySQL에 `gwanyeon` 스키마를 생성합니다.
    - `src/main/resources/gwanyeon.sql` 파일을 실행하여 테이블을 생성합니다.
    - `src/main/resources/gwanyeon_insert.sql` 파일을 실행하여 초기 데이터를 삽입합니다.

3.  **`application.properties` 설정**

    `src/main/resources/application.properties` 파일을 열어 본인의 환경에 맞게 DB 정보, JWT 비밀키, AI API 키 등을 설정합니다.

    ```properties
    # Database
    spring.datasource.url=jdbc:mysql://localhost:3306/gwanyeon?use_SSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    spring.datasource.username=YOUR_DB_USERNAME
    spring.datasource.password=YOUR_DB_PASSWORD

    # JWT
    jwt.secret=YOUR_JWT_SECRET_KEY

    # OpenAI
    openai.api.key=YOUR_OPENAI_API_KEY
    ```

4.  **애플리케이션 빌드 및 실행**
    ```bash
    # Maven으로 빌드
    ./mvnw clean install

    # Spring Boot 애플리케이션 실행
    ./mvnw spring-boot:run
    ```

5.  **서버 접속 확인**
    - 서버가 정상적으로 실행되면 `http://localhost:8080` 으로 접속할 수 있습니다.
    - API 테스트는 Swagger UI (`http://localhost:8080/swagger-ui/index.html`)를 이용해 진행합니다.
