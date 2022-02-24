# Spring MVC2

spring mvc를 공부한 내용 기록


# 🌱 커리큘럼
| section | title | progress |
| :-----: | ----- | :------: |
| 1 | 타임리프 - 기본 기능 | ✅ |
| 2 | 타임리프 - 스프링 통합과 폼 | ✅ |
| 3 | 메시지, 국제화 | ✅ |
| 4 | 검증1 - Validation | ✅ |
| 5 | 검증2 - Bean Validation | ✅ |
| 6 | 로그인 처리1 - 쿠키, 세션 | ✅ |
| 7 | 로그인 처리2 - 필터, 인터셉터 | ✅ |
| 8 | 예외 처리와 오류 페이지 |  |
| 9 | API 예외 처리 |  |
| 10 | 스프링 타입 컨버터 |  |
| 11 | 파일 업로드 |  |


# 🌱 정리

## 1. 타임리프 - 기본 기능

### 타임리프 특징
- 서버사이드 HTML 렌더링 (SSR): 서버에서 HTML을 동적으로 렌더링
- 네츄럴 템플릿: 순수 HTML 최대한 유지
- 스프링 통합 지원

### 타임리프 사용 선언

``` html
<html xmlns:th="http://www.thymeleaf.org">
```
- 위의 코드를 html 코드에 추가

### 표기법
- 표현
    - 변수 표헌: `${...}`
    - 선택 변수 표헌: `*{...}`
    - 메시지 표헌: `#{...}`
    - 링크 URL 표현: `@{...}`
    - 조각 표헌: `~{...}`
- 문자 연산
    - 문자 합치기: `+` 사용
    - 리터럴 대체: `|The name is ${name}|`
- boolean 연산: `and`, `or`, `!`, `not`

### 텍스트
- 태그에 `<span th:text="${data}">`
- 태그 사이에 `<span>[[${data}]]</span>`

### Unescape
- 태그에 `th:inline="none"` 옵션: 타임리프가 해석하지 말라는 옵션

### SpringEL (Spring Expression Language)
- Object
    - `user.username`
    - `user['username']`
    - `user.getUsername()`
- List
    - `user[0].username`
    - `list.get(0).getUsername()`
    - `user[0]['username']`
- Map
    - `userMap['userA'].username`
    - `userMap['userA']['username']`
    - `userMap['userA'].getUsername()`


## 6. 로그인 처리1 - 쿠키, 세션

### 쿠키의 종류

- 영속 쿠키: 만료 날짜까지 유지
- 세션 쿠키: 만료 날짜 혹은 브라우저 종료시까지만 유지

### 쿠키를 생성하여 클라이언트에 응답

``` java
Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId())); // 쿠리를 생성
response.addCookie(idCookie); // 응답 메세지에 쿠키를 저장
```
