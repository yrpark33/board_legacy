Spring Legacy 기반으로 구현한 CRUD 게시판 프로젝트입니다.  
게시글 CRUD, 댓글 비동기(AJAX) 처리, 첨부파일 업로드 기능을 포함하며  
MVC 아키텍처와 트랜잭션, 예외 처리 구조를 학습하고 적용했습니다.



**기술 스택**
- **Language**: Java 17  
- **Framework**: Spring MVC (Legacy), MyBatis
- **Database**: MariaDB
- **View**: JSP, JSTL, Bootstrap 5
- **Server**: Apache Tomcat 10.1.44
- **Tools**: Eclipse, Maven



UI 템플릿: https://startbootstrap.com/template/sb-admin 
화면 구성은 해당 템플릿을 일부 수정하여 사용하였으며, 
코드 중심 저장소를 유지하기 위해 .gitattributes로 대용량 리소스는 제외하였습니다.



DB 스키마 파일 위치:  
`src/main/resources/schema.sql`

**실행방법**

1. **MariaDB 데이터베이스 생성**  
   - `schema.sql` 파일을 실행하여 테이블 구조를 생성합니다.

2. **DB 접속 정보 수정**  
   - `src/main/webapp/WEB-INF/spring/root-context.xml` 파일에서  
     `<bean id="dataSource">` 부분의 URL, username, password를  
     실제 DB 환경에 맞게 수정합니다.

3. **서버 실행**  
   - Apache **Tomcat 10** 서버에 배포 및 실행합니다.

4. **접속 확인**  
   - 브라우저에서 http://localhost:8080/board/list 또는 http://localhost:8080/product/list 에 접속하여 메인화면을 확인합니다.
