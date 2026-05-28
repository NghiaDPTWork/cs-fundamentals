# Phân Biệt Authentication vs Authorization & Các Cơ Chế Xác Thực Cốt Lõi

Trong việc phát triển hệ thống phần mềm và bảo mật web, **Authentication** (Xác thực) và **Authorization** (Ủy quyền/Phân quyền) là hai cột mốc quan trọng nhất. Dù thường xuyên đi kèm nhau, chúng đại diện cho hai nhiệm vụ hoàn toàn khác biệt.

---

## 1. Phân Biệt Authentication vs Authorization

Cách đơn giản nhất để nhớ sự khác biệt giữa hai khái niệm này:
- **Authentication (Authen)** trả lời câu hỏi: **"Bạn là ai?"**
- **Authorization (Author)** trả lời câu hỏi: **"Bạn có quyền làm gì?"**

| Đặc trưng | Authentication (Xác thực) | Authorization (Ủy quyền / Phân quyền) |
| :--- | :--- | :--- |
| **Mục đích** | Xác minh danh tính của một người dùng hoặc hệ thống. | Xác định quyền hạn truy cập tài nguyên của danh tính đã được xác thực. |
| **Thời điểm chạy** | Diễn ra **trước** (Hệ thống phải biết bạn là ai trước khi kiểm tra quyền hạn của bạn). | Diễn ra **sau** khi quá trình xác thực (Authentication) thành công. |
| **Thông tin xác minh** | Tài khoản/Mật khẩu, mã OTP, sinh trắc học (vân tay, khuôn mặt), token mạng xã hội. | Vai trò (Roles), nhóm (Groups), quyền hạn cụ thể (Permissions/Policies). |
| **Ví dụ thực tế** | Bạn trình thẻ căn cước công dân hoặc hộ chiếu cho bảo vệ tòa nhà để chứng minh tên tuổi. | Thẻ của bạn chỉ cho phép bạn đi thang máy lên tầng 5 (phòng làm việc của bạn) mà không lên được tầng 10. |
| **Lỗi trả về (HTTP)** | **`401 Unauthorized`** (Thực chất lỗi này nghĩa là Unauthenticated - chưa xác thực danh tính). | **`403 Forbidden`** (Đã xác thực danh tính nhưng không có quyền truy cập tài nguyên). |

---

## 2. Các Cơ Chế Xác Thực Phổ Biến: Session vs Token

Có hai kiến trúc quản lý phiên làm việc phổ biến trong các ứng dụng Web:

### 2.1. Session-Based Authentication (Stateful)
Phương pháp truyền thống, thường được dùng trong các ứng dụng Multi-Page (SSR kiểu cũ) hoặc ứng dụng Monolith.

```mermaid
sequenceDiagram
    participant Browser as Trình duyệt Client
    participant Server as Web Server
    participant DB as Database/Redis

    Browser->>Server: 1. Gửi Đăng nhập (Username/Password)
    Server->>DB: 2. Kiểm tra thông tin & Tạo Session
    DB-->>Server: Lưu SessionID + Metadata
    Server-->>Browser: 3. Trả về SessionID qua Cookie (HttpOnly)
    Browser->>Server: 4. Gửi Request tiếp theo + Cookie (tự động đính kèm)
    Server->>DB: 5. Tra cứu SessionID trong DB/Redis
    DB-->>Server: Trả về thông tin User hợp lệ
    Server-->>Browser: 6. Trả về dữ liệu yêu cầu
```

- **Đặc điểm**:
  - **Stateful**: Server bắt buộc phải lưu trạng thái của Session (trong RAM của server, Database hoặc Redis/Memcached).
  - Trình duyệt lưu `Session ID` trong Cookie và tự động gửi kèm theo mọi request.
- **Ưu điểm**:
  - Dễ dàng thu hồi quyền truy cập (Revoke session) ngay lập tức bằng cách xóa Session trong database/Redis.
- **Nhược điểm**:
  - Khó mở rộng hàng ngang (Scaling): Nếu có nhiều server phía sau load balancer, các server cần dùng chung một kho lưu session (như Redis) để tránh tình trạng request tiếp theo rơi vào server khác không có session.
  - Dễ bị tấn công **CSRF (Cross-Site Request Forgery)** nếu Cookie cấu hình không an toàn.

### 2.2. Token-Based Authentication (Stateless)
Phương pháp hiện đại, được sử dụng rộng rãi trong các ứng dụng SPA (React, Vue, Angular) và kiến trúc Microservices.

```mermaid
sequenceDiagram
    participant Browser as Trình duyệt Client
    participant Server as API Server

    Browser->>Server: 1. Gửi Đăng nhập (Username/Password)
    Server->>Server: 2. Xác thực & Tạo Token (Ký số bằng Secret Key)
    Server-->>Browser: 3. Trả về Token (JWT - Access Token & Refresh Token)
    Browser->>Server: 4. Gửi Request tiếp theo + Header (Authorization: Bearer <Token>)
    Server->>Server: 5. Giải mã & Xác minh Chữ ký Token (Không cần truy vấn DB)
    Server-->>Browser: 6. Trả về dữ liệu yêu cầu
```

- **Đặc điểm**:
  - **Stateless**: Server không lưu trữ trạng thái của Token. Mọi thông tin cần thiết đều nằm sẵn bên trong Token. Server chỉ cần dùng Secret Key để giải mã và kiểm tra tính hợp lệ của chữ ký.
- **Ưu điểm**:
  - Dễ dàng scale ngang: Bất kỳ server nào có Secret Key đều có thể giải mã và xác thực token mà không cần chung DB.
  - Phù hợp cho kiến trúc Microservices và API kết nối đa nền tảng (Web, Mobile, IoT).
- **Nhược điểm**:
  - Khó thu hồi (Revoke): Rất khó để hủy bỏ một Token đang còn hạn trừ khi duy trì một danh sách đen (Blacklist) trong Redis (lúc này tính stateless bị giảm đi một phần).

---

## 3. Tìm Hiểu Sâu Về JWT (JSON Web Token)

JWT là định dạng token phổ biến nhất trong kiến trúc Stateless. Một chuỗi JWT gồm 3 phần được phân tách bằng dấu chấm (`.`): `Header.Payload.Signature`

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### 3.1. Cấu trúc 3 phần
1. **Header (Đầu)**:
   Chứa thông tin về thuật toán ký (ví dụ: HS256, RS256) và kiểu token (JWT).
   ```json
   { "alg": "HS256", "typ": "JWT" }
   ```
2. **Payload (Thân)**:
   Chứa các thông tin truyền tải (Claims). Gồm các claim tiêu chuẩn (`sub` - subject, `exp` - expiration time, `iat` - issued at) và các custom claim do bạn tự định nghĩa (ví dụ: `role`, `userId`).
   ```json
   {
     "sub": "1234567890",
     "name": "Nghia DPT",
     "role": "admin",
     "exp": 1716945600
   }
   ```
3. **Signature (Chữ ký)**:
   Được tạo ra bằng cách lấy phần Header mã hóa Base64 kết hợp với Payload mã hóa Base64, nối với nhau bởi dấu chấm, rồi ký bằng một thuật toán bảo mật với mã khóa bí mật (Secret Key / Private Key) trên Server.
   ```javascript
   Signature = HMACSHA256(
     base64UrlEncode(Header) + "." + base64UrlEncode(Payload),
     SecretKey
   )
   ```

> [!WARNING]
> Phần **Header** và **Payload** chỉ được mã hóa **Base64Url** chứ không hề được mã hóa bảo mật (encryption). Bất kỳ ai có chuỗi JWT đều có thể giải mã Base64 dễ dàng để đọc thông tin bên trong. Vì vậy, **TUYỆT ĐỐI không để các thông tin nhạy cảm như Mật khẩu, Số thẻ tín dụng vào Payload của JWT**.

---

## 4. Tổng Quan Về OAuth 2.0 & OpenID Connect (OIDC)

### 4.1. Phân biệt OAuth 2.0 vs OIDC
- **OAuth 2.0 (Ủy quyền - Authorization)**:
  - Thiết kế để ủy quyền cho ứng dụng bên thứ ba truy cập tài nguyên thay mặt người dùng mà không cần biết mật khẩu của họ (Ví dụ: Ứng dụng lịch muốn truy cập danh bạ Google của bạn).
  - Kết quả trả về là một **Access Token** (không chứa thông tin định danh người dùng trực tiếp, chỉ dùng để gọi API lấy tài nguyên).
- **OpenID Connect - OIDC (Xác thực - Authentication)**:
  - Là một lớp bảo mật bổ sung xây dựng trực tiếp trên nền tảng OAuth 2.0 để xác thực danh tính người dùng.
  - Kết quả trả về có thêm **ID Token** (dưới dạng JWT chứa thông tin hồ sơ của người dùng đăng nhập như tên, email, avatar).

### 4.2. Các Roles trong OAuth 2.0
1. **Resource Owner**: Người dùng (User) sở hữu dữ liệu.
2. **Client**: Ứng dụng bên thứ ba yêu cầu quyền truy cập (ví dụ: Web app của bạn).
3. **Authorization Server**: Server xác thực của bên cung cấp dịch vụ (ví dụ: Google Accounts Server).
4. **Resource Server**: Server lưu trữ dữ liệu thực tế (ví dụ: Google Drive API Server).

### 4.3. Authorization Code Flow với PKCE (Giải pháp an toàn nhất cho SPA/Mobile)
Để tránh lộ Access Token trên trình duyệt, các ứng dụng Client-Side hiện nay bắt buộc phải dùng luồng **Authorization Code Flow kết hợp PKCE (Proof Key for Code Exchange)**:

```mermaid
sequenceDiagram
    participant User as Người dùng
    participant SPA as Ứng dụng SPA (Client)
    participant AuthServer as Authorization Server
    participant Backend as Backend Server

    SPA->>SPA: Tạo Code Verifier (ngẫu nhiên) & Code Challenge (hash SHA256)
    User->>SPA: Click "Đăng nhập bằng Google"
    SPA->>AuthServer: Điều hướng đến trang login + Code Challenge
    User->>AuthServer: Nhập Username/Password & Đồng ý cấp quyền
    AuthServer-->>SPA: Redirect lại SPA kèm theo Authorization Code (ngắn hạn)
    SPA->>AuthServer: Gửi Authorization Code + Code Verifier gốc
    AuthServer->>AuthServer: Hash Code Verifier & So sánh với Code Challenge nhận lúc đầu
    AuthServer-->>SPA: Trả về Access Token & ID Token (Nếu khớp)
```

PKCE ngăn chặn tin tặc đánh cắp Authorization Code trên trình duyệt, vì không có Code Verifier gốc (chỉ lưu trong RAM của ứng dụng SPA), tin tặc không thể đổi Code lấy Token được.
