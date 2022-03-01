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

---

## 4. 검증1- Validation

> 컨트롤러의 중요한 역할 중 하나는 HTTP 요청이 정상인지 검증하는 것

**클라이언트 검증과 서버 검증**
- 클라이언트 검증은 조작할 수 있어 보안에 취약
- 서버만 사용한 검증은 즉각적인 사용성이 부족
- 클라이언트 검증 + 서버 검증을 적절히 사용하는 것이 중요


### BindingResult

- 검증할 객체 파라미터의 다음에 위치

### FiledError

- 필드 오류

```java
public FieldError(String objectName, String field, String defaultMessage) {}

public FieldError(String objectName, String field, @Nullable Object rejectedValue, boolean bindingFailure, @Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage)
```

- `objectName`: `@ModelAttribute` 이름
- `field`: 오류가 발생한 객체의 필드 이름
- `rejectedValue`: 사용자가 입력한 값. 사용자가 입력한 값을 저장하기 위해 사용.
- `bindingFilure`: 바인딩 실패(타입 오류)인지, 검증 실패인지 구분하는 값
- `codes`: 메시지 코드
- `arguments`: 메시지에서 사용하는 인자
- `defaultMessage`: 오류 기본 메시지

**스프링의 바인딩 오류 처리**
- 타입 오류 시에 스프링은 `FieldError`을 생성
- 생성한 오류를 `BindingResult`에 담아 컨트롤러 호출


### ObjectError
- 글로벌 오류
- 필드오류가 아닌 오류

``` java
public ObjectError(String objectName, String defaultMessage) {}
```
- `objectName`: `@ModelAttribute` 이름
- `defaultMessage`: 오류 기본 메시지


### errors 메시지 파일
- `~.properties` 파일에 오류 메시지 작성
- `application.properties` 파일에 `spring.messages.basename=messages,errors` 설정 추가

**reject 함수**
``` java
void rejectValue(@Nullable String field, String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);

void reject(String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);
```
- `field`: 오류 필드명
- `errorCode`: 오류 코드
- `errorArgs`: 오류 메시지 인자
- `defaultMessage`: 기본 메시지

### MessageCodesResolver
- 인터페이스 (`DefaultMessageCodesResolver`)
- 오류 메시지 코드 생성

### DefaultMessageCodesResolver
- `MessageCodesResolver`의  기본 구현체
- 메시지 생성 규칙
  - 객체 오류
    1. code + "." + objectName
    2. code
  - 필드 오류
    1. code
  



---

## 6. 로그인 처리1 - 쿠키, 세션


### 쿠키의 종류

- 영속 쿠키: 만료 날짜까지 유지
- 세션 쿠키: 만료 날짜 혹은 브라우저 종료시까지만 유지

### 쿠키를 생성하여 클라이언트에 응답

``` java
Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId())); // 쿠리를 생성
response.addCookie(idCookie); // 응답 메세지에 쿠키를 저장
```
