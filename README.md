
# 경북대학교 2021 SW해커톤

## 크누 가드(KNU-GUARD)

경북대학교 및 교외 확진자 현황 확인 서비스

## **프로젝트 소개**

---

### 시연영상

[https://youtu.be/HmjkEZNpJrY](https://youtu.be/HmjkEZNpJrY)

### 팀 구성(팀명:KM)

1인팀구성

권용준(팀장)

### 사용기술 및 언어

JAVA Spring

JPA

MySQL

Python + BeautifulSoup(웹 크롤링)

Android JAVA

Volley (Http 전송) GoogleMap (지도)

REST API

## **프로젝트 내용**

---

### 주제

교내 확진자 현황 및 교외 확진자 현황을 손쉽게 확인하기 위한 어플리케이션(KNU-GUARD) 개발

### 개발 배경 및 목적

현재 정부에서는 **코로나19 감염확산 방지**를 위해 전송하고 있는 **재난안전문자**가 코로나19 장기화에 따라 지속되는 알림문자에 **피로감**을 느끼고있다는 여론에 따라 알림의 빈도와 정보량을 줄였다. 

각 대학교에서 코로나19 집단감염이 일어나지 않는 이상 정부 메뉴얼에 따라 재난안전 문자를 보내는 것은 필수가 아니다. 따라서 각 학교 학생들이 교내 코로나19 현황을 알기 위해서는 학교 홈페이지를 직접 접속하여 들어가야하는 **불편함**을 감수하고 확인해야한다. 더군다나 보통 학교 홈페이지에서는 게시글 형태로 코로나19 현황을 게시하는데 지도형태가 아니기 때문에 **한눈에 확인하기가 어렵다**. 또한 최근 재난문자를 보면 문자내용에 코로나19 현황 정보를 담고있는 별도의 링크를 첨부해서 보내기 때문에 곧바로 정보를 확인하기 어렵다. 

이러한 불편함을 해결하기 위해 지도위에 코로나19 발생 위치 및 관련 정보를 표기할 수 있다면 보다 **편리하게** 사람들이 정보를 확인할 수 있고 한눈에 현황을 볼 수 있기 때문에 **경각심**을 주어 더욱 방역지침 준수하도록 유도하는 효과를 줄 것이라 생각하였다.

### 용도

- 사용자는 코로나19 현황을 지도 형태로 한눈에 확인할 수 있다.
- 이용자가 소속된 교내 확진자 현황을 간편하게 확인할 수 있다.
- 교외로 나갈일이 있을때 해당 어플리케이션을 이용하여 확진자 발생구역을 확인 후 안전구역을 찾아 갈 수 있다.

### 시스템 아키텍쳐
![Untitled_Diagram_(1)](https://user-images.githubusercontent.com/76172759/126724693-fc03fff1-14c1-42d7-bb66-398522690bd6.png)

### 어플리케이션 화면
<img src="https://user-images.githubusercontent.com/76172759/126724707-5679af99-fbdd-4209-86ec-2d38a478ace0.png" width="300" height="450"/>
<img src="https://user-images.githubusercontent.com/76172759/126724710-d07c18a4-4acc-430c-888e-6b86a4127b52.png" width="300" height="400"/>
<img src = "https://user-images.githubusercontent.com/76172759/126724707-5679af99-fbdd-4209-86ec-2d38a478ace0.png" width="50%" height="50%">
<img src = "https://user-images.githubusercontent.com/76172759/126724710-d07c18a4-4acc-430c-888e-6b86a4127b52.png" width="40%" height="40%">
![스크린샷_23-07-2021_08 43 16](https://user-images.githubusercontent.com/76172759/126724707-5679af99-fbdd-4209-86ec-2d38a478ace0.png){: width="50%" height="50%"}
![스크린샷_23-07-2021_08 44 27](https://user-images.githubusercontent.com/76172759/126724710-d07c18a4-4acc-430c-888e-6b86a4127b52.png){: width="40%" height="40%"}


### 기능

---

**서버** 

- 크롤러가 수집한 정보를 받아서 DB에 저장함(중복 정보 예외처리함)
- 클라이언트의 요청이 들어오면 적절한 응답을 JSON형태로 응답함(REST API)
- 로그인 처리

---

**클라이언트**

- 로그인기능을 제공
- 서버에게 확진현황 데이터 요청
- 구글맵에 요청받은 데이터를 마커로 변환 후 렌더링

---

**크롤러**

- 학교 홈페이지 코로나19현황 게시판을 순회하며 확진자 현황 데이터 수집(중복 게시글 예외 처리)
- 서버에게 POST방식으로 수집한 데이터 전송

### 영향

기존 재난안전문자의 정보 표시 형태(텍스트)로는 사람들이 주변에 얼마나 많은 확진자가 발생했는지 파악하기가 쉽지 않았다. 크누가드(KNU-GUARD)를 이용하면 지도를 통해 한눈에 코로나19 확진 현황을 파악할 수 있으므로 사람들에게 경각심을 심어줄 수 있고 이는 곧 사람들에게 방역수칙준수를 유도할 것이고 이는 곧 방역성공에 기여하는 일이 될 것이라고 생각한다.
