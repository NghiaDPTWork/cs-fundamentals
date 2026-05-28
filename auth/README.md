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

### 2.3. Chiến lược lưu trữ Token & Tại sao cần cặp đôi AT & RT?

#### A. So sánh các vị trí lưu trữ Token trên Client
Khi sử dụng Token-based auth, việc lưu trữ token ở đâu là quyết định sống còn đối với bảo mật:

*   **LocalStorage / SessionStorage**:
    *   *Ưu điểm*: Dễ code, dễ truy xuất bằng Javascript (`localStorage.getItem('token')`).
    *   *Mối nguy hiểm (XSS)*: Rất dễ bị đánh cắp nếu trang web bị tấn công **XSS** (Cross-Site Scripting). Kẻ tấn công có thể tiêm mã độc Javascript vào trang web (qua các thư viện bên thứ ba, input không được lọc kỹ) và chạy lệnh để lấy toàn bộ dữ liệu trong LocalStorage gửi về server của chúng.
*   **Cookie thông thường**:
    *   *Ưu điểm*: Có thể truyền tự động theo HTTP Request.
    *   *Mối nguy hiểm*: Vẫn có thể bị Javascript đọc được nếu không bật cờ `HttpOnly`.
*   **Cookie HttpOnly (Được khuyên dùng)**:
    *   *Ưu điểm*: Trình duyệt ngăn chặn tuyệt đối việc Javascript đọc Cookie này. Do đó, **hoàn toàn chống được tấn công XSS**.
    *   *Mối nguy hiểm (CSRF)*: Dễ bị tấn công **CSRF** (Cross-Site Request Forgery). Kẻ tấn công dụ người dùng bấm vào một link độc hại, trình duyệt sẽ tự động đính kèm Cookie chứa token này và gửi request giả mạo lên server của bạn. Tuy nhiên, ta có thể khắc phục triệt để CSRF bằng cách cấu hình thuộc tính **`SameSite=Strict`** hoặc **`SameSite=Lax`** cho Cookie.

---

#### B. Vai trò của Access Token (AT) và Refresh Token (RT)

Tại sao không dùng một token duy nhất mà phải sinh ra 2 loại token khác nhau?

| Đặc trưng | Access Token (AT) | Refresh Token (RT) |
| :--- | :--- | :--- |
| **Vai trò chính** | Dùng để xác thực và ủy quyền trực tiếp cho các request gọi API lấy dữ liệu. | Dùng duy nhất cho việc yêu cầu Server cấp lại một Access Token mới khi AT cũ hết hạn. |
| **Nơi gửi đến** | Gửi kèm mọi API Request lấy dữ liệu (đính kèm trong Header `Authorization: Bearer <AT>`). | Chỉ gửi đến endpoint xác thực (ví dụ `/api/auth/refresh`) để đổi lấy AT mới. |
| **Thời gian sống (TTL)** | Cực kỳ ngắn (thường từ 5 đến 15 phút). | Dài hạn (thường từ 7 ngày đến 30 ngày hoặc vài tháng). |
| **Cách thức lưu trữ** | Nên lưu trong **RAM** (biến cục bộ của ứng dụng) đối với ứng dụng SPA, để Javascript sử dụng tạm thời. | Lưu trong **Cookie HttpOnly (SameSite=Strict, Secure)** để lưu trữ lâu dài trên trình duyệt. |

---

#### C. Tại sao cần cả hai? Nếu loại bỏ 1 cái chỉ dùng 1 cái thì có được không?

**Về mặt kỹ thuật**: HOÀN TOÀN ĐƯỢC. Bạn chỉ cần phát hành 1 token duy nhất (chính là Access Token) và sử dụng nó mãi mãi.

**Tuy nhiên, về mặt bảo mật và trải nghiệm người dùng (UX), điều này là một THẢM HỌA vì những lý do sau**:

1.  **Nếu chỉ dùng 1 token và đặt hạn dùng ngắn (ví dụ: 15 phút)**:
    *   *UX tệ hại*: Cứ sau mỗi 15 phút sử dụng ứng dụng, người dùng lại bị log out ra ngoài và bắt buộc phải nhập lại username/password. Điều này không thể chấp nhận được trong các ứng dụng thực tế.
2.  **Nếu chỉ dùng 1 token và đặt hạn dùng dài (ví dụ: 7 ngày)**:
    *   *Lỗ hổng bảo mật cực lớn*: Token này sẽ phải liên tục gửi kèm theo mọi API request. Nếu người dùng bị tấn công XSS hoặc bị nghe lén mạng, kẻ tấn công chiếm được token này sẽ có toàn quyền truy cập tài khoản người dùng trong suốt 7 ngày.
    *   *Không thể thu hồi (Revoke)*: Vì JWT là stateless (không lưu trạng thái ở server), một khi token đã được phát hành và còn hạn, server KHÔNG THỂ vô hiệu hóa nó (trừ khi đổi Secret Key của toàn hệ thống, làm tất cả user bị logout). Kẻ tấn công có thể thoải mái phá hoại cho đến khi token tự hết hạn sau 7 ngày.

**Giải pháp cứu cánh khi kết hợp AT và RT**:
*   **Access Token (Hạn ngắn - 15 phút)**: Phải gửi đi liên tục nên nguy cơ bị lộ cao hơn. Nhưng nếu bị lộ, kẻ tấn công cũng chỉ dùng được tối đa 15 phút.
*   **Refresh Token (Hạn dài - 7 ngày)**: Được cất giấu rất kỹ trong **Cookie HttpOnly** (Javascript không chạm vào được) và chỉ gửi đi rất ít (15 phút 1 lần). Do đó nguy cơ bị lộ gần như bằng 0.
*   **Khả năng thu hồi (Revokable)**: Vì Refresh Token chỉ được dùng tại Auth Server để cấp AT mới, ta có thể lưu trữ danh sách các Refresh Token hợp lệ (hoặc một trường `token_version` trên DB). Nếu phát hiện tài khoản bị hack, Admin chỉ cần xóa Refresh Token đó trên Database. Khi AT cũ hết hạn (tối đa 15 phút), kẻ tấn công gửi RT lên để refresh sẽ bị từ chối và bị logout vĩnh viễn.

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
