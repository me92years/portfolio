# portfolio
- 영화 DB 클론 코딩 연습. (개인 프로젝트입니다.)

# 프로젝트 개요
- AWS서버를 활용하기 위해 간단한 영화 DB사이트를 클론 제작합니다.

# 기술 스택
- JDK 11
- Eclipse, VSCode, HeidiSQL
- Gradle
- Back-End 
  - Spring Boot v2.7.10
  - JPA, JDBC
  - Thymeleaf
  - Security
  - oauth2
  - spring session
  - QueryDsl
  - Lombok
  - JSON
  - H2, MariaDB
- Front-End
  - HTML
  - Javascript
  - Css
  - WebComponent
- Git

# 구현 기능
- Spring Security를 활용 해 Spring Session방식의 로그인을 구현.
  - 로그인은 OAuth2를 활용 한 구글 로그인만 가능합니다.
- Querydsl을 사용하여 간단한 검색기능을 구현.
- 프리티어 서버의 한계로 React에서 서버사이드 렌더링방식으로 변경합니다.
  - Thymeleaf를 사용하였으며 화면을 렌더링하는 방식은 Javascript의 기본내장 기능인 WebComponent를 사용합니다.

# 배포 정보
- AWS의 프리티어 무료 서버입니다. (현재는 실제 운영 중이지 않습니다)
- AWS RDS를 활용하여 MariaDB를 사용합니다.
- 스크립트 쉘을 만들어 빌드 후 실행합니다.


