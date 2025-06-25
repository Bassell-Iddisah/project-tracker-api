# 🛠 Project Tracker API

A Spring Boot backend for managing projects, tasks, and users with JWT and Google OAuth2 authentication.

---

## 🗂 Project Structure

```
project-tracker-api/
├── src/
│   ├── main/
│   │   ├── java/com/gentleninja/
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── HomeController.java
│   │   │   │   └── ...
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Project.java
│   │   │   │   ├── Task.java
│   │   │   │   └── RoleType.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── RoleRepository.java
│   │   │   │   └── ...
│   │   │   ├── service/
│   │   │   │   ├── AuthenticationService.java
│   │   │   │   ├── CustomOAuth2UserService.java
│   │   │   │   └── ...
│   │   │   ├── security/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── jwt/
│   │   │   │       ├── JwtTokenProvider.java
│   │   │   │       └── JwtAuthenticationFilter.java
│   │   │   └── ...
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           ├── login.html
│   │           └── home.html
└── pom.xml
```


---

## ⚙️ Setup & Configuration

1. **Clone the repo**:
   ```bash
   git clone https://github.com/Bassell-Iddisah/project-tracker-api.git
   cd project-tracker-api
   ```

2. **Configure `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/projectdb
   spring.datasource.username=postgres
   spring.datasource.password=secret

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   app.jwt.secret=YourJwtSecretKey
   app.jwt.expiration-ms=3600000

   spring.security.oauth2.client.registration.google.client-id=45795267064-4sma74idsg3elc4qo95ie8ton5ntarmb.apps.googleusercontent.com
   spring.security.oauth2.client.registration.google.client-secret=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
   spring.security.oauth2.client.registration.google.scope=email,profile
   spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/{registrationId}
   spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/v2/auth
   spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
   spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
   spring.security.oauth2.client.provider.google.user-name-attribute=email
   ```

3. **Install Dependencies & Build**:
   ```bash
   ./mvnw clean install
   ```

4. **Run the App**:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🔐 JWT Token Flow & Usage

- **Login** via:
    - `POST /auth/register`
    - `POST /auth/login`

- **Response**:
  ```json
  {
    "token": "<JWT_TOKEN>",
    "type": "Bearer"
  }
  ```

- Add header:
  ```
  Authorization: Bearer <JWT_TOKEN>
  ```

- Token is validated in `JwtAuthenticationFilter`
- User is authenticated and authorized for secure endpoints

---

## 🎖 OAuth2 Login & User Mapping

1. Visit `http://localhost:8080/login`
2. Click **“Login with Google”**
3. `CustomOAuth2UserService` handles the user
4. User is created with `ROLE_USER` if not found

---

## 🛡 Secured Endpoints (Role-based)

| Endpoint             | Access             |
|----------------------|--------------------|
| `/api/admin/**`      | ADMIN              |
| `/projects/**`       | All roles          |
| `/tasks`             | MANAGER, ADMIN     |
| `/users/me`          | Authenticated only |
| `/admin/users`       | ADMIN              |

> Roles are checked using `@PreAuthorize` and loaded from the User entity.

---

## 🧪 Postman & Testing

- Register → Login → Copy token → Access protected routes
- Swagger UI

---

## ✅ Run Tests

```bash
./mvnw test
```
