# 💼 Spring Boot REST API Project

Spring Boot with JWT authentication, exception handling, password reset via email,file upload,pagination and secure REST APIs with full CRUD operations.

## 🚀 Features

- Full CRUD operations
- Build RESTful API design
- File Upload and Serve
- Exception handling
- Pagination and Sorting
- JWT Auth Implementation
- Forgot & Reset password flow

## 🔧 Tech Stack

- Java 21
- Spring Boot
- MySQL 
- JPA 
- Spring Security


Add Movie 

curl --location 'http://localhost:8080/api/v1/movie/add-movie' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWthc0BnbWFpbC5jb20iLCJpYXQiOjE3NDQ5NDQ4NjIsImV4cCI6MTc0NDk0NjM2Mn0.0yeM6-c4DYM7vOViD3afPkFNwd4lWbF6gdWmB2AMOFs' \
--form 'file=@"/C:/Users/HP/Downloads/abc.png"' \
--form 'movieDto="{
  \"title\": \"Sam\",
  \"director\": \"Adda\",
  \"studio\": \"ABC\",
  \"movieCast\": [
    \"Jay\",
    \"Kishan\"
  ],
  \"releaseYear\": \"2020\"
}"'

Update Movie 
curl --location --request PUT 'http://localhost:8080/api/v1/movie/update/1' \
--form 'file=@"/path/to/file"' \
--form 'movieDtoObj="{
  \"title\": \"Alone\",
  \"director\": \"Rohit Shetty\",
  \"studio\": \"marvel\",
  \"movieCast\": [
    \"Kartik\",
    \"Deepika\"
  ],
  \"releaseYear\": \"2023\"
}"'

Get Movie
curl --location 'http://localhost:8080/api/v1/movie/all' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NDUwMTQ4NjMsImV4cCI6MTc0NTAxNjM2M30.ZV1O8U-pZxIyXkoqsbzb5wEEOJ3-f9uV5fX_A0UfawI'

Delete Movie
curl --location --request DELETE 'http://localhost:8080/api/v1/movie/delete/1'


User Register
curl --location 'http://localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"mukuljain",
    "email":"jmukul987@gmail.com",
    "username":"mj29",
    "password":"98765",
    "userRole":"USER"
}'


Login User
curl --location 'http://localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"jmukul987@gmail.com",
    "password":"10001"
}'


Refresh Token
curl --location 'http://localhost:8080/api/v1/auth/refresh' \
--header 'Content-Type: application/json' \
--data '{
    "refreshToken" :"e28143a9-c44a-402c-bb55-67e7a7ca40b7"
}'

Verify Email
curl --location --request POST 'http://localhost:8080/forgotPassword/verifyMail/jmukul987@gmail.com'

Verify OTP
curl --location --request POST 'http://localhost:8080/forgotPassword/verifyOtp/990727/jmukul987@gmail.com'

Change Password
curl --location 'http://localhost:8080/forgotPassword/changePassword/jmukul987@gmail.com' \
--data '{
    "password":"10002",
    "repeatPassword":"10002"
}'
