# springboot-mustache-bbs5

# 엔티티 다이어그램

![제목 없는 다이어그램 drawio](https://user-images.githubusercontent.com/1642243/206071295-05c3b4f4-31b7-40f8-ae3f-90ed0613ad3d.png)

# API정의

POST /api/v1/visits → create기능, Token인증

RequstHeader {
    Authorization:Bearer <JWT>
}

RequstBody {
    hospitalId:<int>,
    disease:<String>,
    amount:<float>
}

### 조회 기능

등록된 방문 내역을 조회하는 기능

GET /api/v1/visits

GET /api/v1/visits?page=<page_no>

## 배포2
