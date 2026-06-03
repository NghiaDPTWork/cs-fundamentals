# THIẾT KẾ API CHUẨN: RESTFUL, HTTP STATUS CODE, IDEMPOTENCY & RFC 7807

Tài liệu này hệ thống hóa các kiến thức nền tảng và nâng cao về thiết kế giao diện lập trình ứng dụng (API), bao gồm kiến trúc RESTful, mã trạng thái HTTP, tính lũy đẳng (Idempotency) và chuẩn định dạng lỗi API RFC 7807.

---

## 1. API VÀ KIẾN TRÚC RESTFUL API

### 1.1. API là gì?
**API (Application Programming Interface - Giao diện lập trình ứng dụng)** là một tập hợp các quy tắc, giao thức và công cụ cho phép các phần mềm/ứng dụng khác nhau có thể giao tiếp, trao đổi dữ liệu với nhau.

### 1.2. RESTful API là gì?
**REST (Representational State Transfer)** KHÔNG PHẢI là một ngôn ngữ lập trình, không phải một thư viện, cũng không phải một phần mềm. REST là một tập hợp các quy tắc (hoặc phong cách kiến trúc) do một nhà khoa học máy tính tên là Roy Fielding định nghĩa ra vào năm 2000, nhằm chuẩn hóa cách các máy tính nói chuyện với nhau trên môi trường Internet. Một hệ thống API tuân thủ các quy tắc của REST được gọi là **RESTful API**.

#### 6 ràng buộc cốt lõi của kiến trúc REST:
1.  **Client-Server (Mô hình Khách - Chủ):** Phân tách rõ ràng giữa giao diện người dùng (Client) và nơi xử lý, lưu trữ dữ liệu (Server). Giúp hai thành phần này phát triển độc lập.
2.  **Stateless (Không lưu trạng thái):** Mỗi request từ Client gửi lên Server phải chứa đầy đủ thông tin để hiểu và thực thi request đó. Server không lưu trữ bất kỳ dữ liệu ngữ cảnh (context) nào của Client trên bộ nhớ của mình giữa các request.
3.  **Cacheable (Khả năng lưu bộ nhớ đệm):** Phản hồi từ Server phải tự định nghĩa xem nó có được phép lưu cache (bộ nhớ đệm) hay không để tối ưu hóa hiệu năng, giảm tải cho Server.
4.  **Uniform Interface (Giao diện đồng nhất):** Đây là ràng buộc quan trọng nhất. Nó yêu cầu việc định danh tài nguyên thông qua URL phải rõ ràng và sử dụng các phương thức chuẩn (như HTTP Method) để thao tác tài nguyên.
5.  **Layered System (Hệ thống phân lớp):** Client không thể biết mình đang kết nối trực tiếp với server đích hay qua các lớp trung gian (Load Balancer, CDN, API Gateway, Proxy). Giúp hệ thống dễ mở rộng và bảo mật.
6.  **Code on Demand (Tùy chọn):** Server có thể gửi mã thực thi (như JavaScript) về cho Client chạy trực tiếp.

#### Quy tắc thiết kế URL trong RESTful API:
- **Sử dụng Danh từ số nhiều, không dùng Động từ:**
  - *Sai:* `GET /getUserProfile`, `POST /createNewUser`, `POST /deleteProduct`
  - *Đúng:* `GET /users/me`, `POST /users`, `DELETE /products/123`
- **Sử dụng đúng HTTP Method tương ứng với hành động CRUD:**
  - `GET`: Lấy thông tin tài nguyên.
  - `POST`: Tạo mới tài nguyên.
  - `PUT`: Cập nhật/Ghi đè hoàn toàn tài nguyên.
  - `PATCH`: Cập nhật một phần tài nguyên.
  - `DELETE`: Xóa tài nguyên.

---

## 2. HTTP STATUS CODES (MÃ TRẠNG THÁI PHẢN HỒI)

Mã trạng thái HTTP giúp Client hiểu nhanh kết quả của request mà không cần phân tích sâu nội dung body. Chúng được chia làm 5 nhóm chính:

| Đầu mã | Loại trạng thái | Ý nghĩa và các mã phổ biến |
| :--- | :--- | :--- |
| **1xx** | Informational (Thông tin) | Server đã nhận request và đang xử lý. Ít dùng trong REST API thông thường. |
| **2xx** | Success (Thành công) | Request được tiếp nhận và xử lý thành công.<br>- **200 OK:** Thành công chung (GET/PUT/PATCH/DELETE).<br>- **201 Created:** Tạo mới tài nguyên thành công (POST).<br>- **204 No Content:** Thành công nhưng không trả dữ liệu về (thường dùng cho DELETE). |
| **3xx** | Redirection (Chuyển hướng) | Client cần thực hiện hành động bổ sung để hoàn thành request.<br>- **301 Moved Permanently:** Tài nguyên đã chuyển vĩnh viễn sang URL khác.<br>- **304 Not Modified:** Tài nguyên chưa thay đổi (Client nên dùng lại bản lưu cache trước đó). |
| **4xx** | Client Error (Lỗi phía Client) | Lỗi do Client gửi sai định dạng request, thiếu thông tin xác thực, v.v.<br>- **400 Bad Request:** Request sai cú pháp dữ liệu.<br>- **401 Unauthorized:** Chưa đăng nhập hoặc token sai/hết hạn.<br>- **403 Forbidden:** Đã đăng nhập nhưng không đủ quyền truy cập tài nguyên.<br>- **404 Not Found:** Tài nguyên không tồn tại.<br>- **409 Conflict:** Xung đột dữ liệu (Ví dụ đăng ký trùng email).<br>- **422 Unprocessable Entity:** Lỗi validate dữ liệu (Dữ liệu gửi lên đúng cú pháp JSON nhưng sai quy định nghiệp vụ). |
| **5xx** | Server Error (Lỗi phía Server) | Server gặp lỗi nội bộ hoặc quá tải không thể xử lý request.<br>- **500 Internal Server Error:** Lỗi code server tự phát sinh.<br>- **502 Bad Gateway:** Server Gateway nhận phản hồi lỗi từ server ứng dụng phía sau.<br>- **503 Service Unavailable:** Server tạm thời quá tải hoặc đang bảo trì.<br>- **504 Gateway Timeout:** Server Gateway hết thời gian chờ phản hồi từ ứng dụng. |

---

## 3. IDEMPOTENT API (TÍNH LŨY ĐẲNG CỦA API)

### 3.1. Tính Lũy Đẳng (Idempotency) là gì?
Một API được gọi là **Lũy đẳng (Idempotent)** khi: **Dù Client có gọi API đó 1 lần hay $N$ lần liên tiếp với cùng một dữ liệu đầu vào, trạng thái của cơ sở dữ liệu và hệ thống vẫn hoàn toàn giống nhau.**

*Ví dụ thực tế:* Hành động click nút "Thanh toán". Nếu API thanh toán không lũy đẳng, việc người dùng double-click do mạng lag sẽ tạo ra 2 giao dịch trừ tiền. Nếu API lũy đẳng, dù click 10 lần hệ thống vẫn chỉ trừ tiền 1 lần duy nhất.

### 3.2. Tính lũy đẳng của các HTTP Method

| HTTP Method | Lũy đẳng? | Giải thích |
| :--- | :--- | :--- |
| **GET** | **Có (Lũy đẳng & Safe)** | Chỉ đọc dữ liệu, không làm thay đổi bất kỳ trạng thái nào của DB. Gọi 100 lần kết quả DB vẫn nguyên vẹn. |
| **PUT** | **Có** | Ghi đè hoàn toàn một tài nguyên. Lần đầu ghi đè dữ liệu mới, các lần sau ghi đè chính dữ liệu mới đó $\rightarrow$ DB không thay đổi trạng thái sau lần đầu tiên. |
| **DELETE** | **Có** | Lần đầu tiên xóa tài nguyên thành công (trả về 200/204). Các lần sau tài nguyên đã mất (trả về 404), nhưng trạng thái của DB vẫn là "tài nguyên đã bị xóa", không có thêm dữ liệu nào bị thay đổi nữa. |
| **POST** | **KHÔNG** | Mỗi lần gọi POST là một lần tạo mới. Gọi 5 lần POST `/users` sẽ tạo ra 5 bản ghi người dùng khác nhau trong cơ sở dữ liệu. |
| **PATCH** | **KHÔNG** | PATCH có thể lũy đẳng hoặc không tùy thiết kế nghiệp vụ. Ví dụ: Nếu PATCH gửi `{ age: 20 }` (lũy đẳng). Nhưng nếu gửi `{ age: age + 1 }` (tăng tuổi lên 1) thì mỗi lần gọi sẽ tăng thêm 1 $\rightarrow$ không lũy đẳng. |

### 3.3. Giải pháp thiết kế API POST lũy đẳng (Idempotency Key)
Để biến một API POST (như Thanh toán/Tạo đơn) thành lũy đẳng nhằm tránh trùng lặp dữ liệu:
1.  **Client** sinh ra một chuỗi ngẫu nhiên duy nhất (UUID) gọi là **`Idempotency-Key`** và đính kèm vào Header của request.
2.  Khi nhận request, **Server** kiểm tra khóa này trong cache nhanh (Redis):
    - **Nếu khóa đã tồn tại:** Chứng tỏ request này đang bị gửi trùng. Server lập tức trả về kết quả đã xử lý lưu sẵn từ lần trước mà không thực hiện lại giao dịch.
    - **Nếu khóa chưa tồn tại:** Server lưu khóa vào Redis kèm trạng thái "đang xử lý", thực thi lưu trữ DB, sau khi thành công thì cập nhật kết quả vào Redis và trả về phản hồi cho Client.

---

## 4. CHUẨN RFC 7807 (PROBLEM DETAILS FOR HTTP APIS)

### 4.1. RFC 7807 là gì? Tại sao cần nó?
Thông thường, các API thiết kế tự phát sẽ trả lỗi về theo nhiều kiểu khác nhau (Chỗ thì `{ "error": "User not found" }`, chỗ thì `{ "success": false, "message": "Wrong password" }`). Điều này khiến Client cực kỳ khó khăn khi viết code xử lý lỗi chung.

**RFC 7807** là một tiêu chuẩn của IETF định nghĩa một định dạng chung (Schema JSON) để báo cáo lỗi trong các API HTTP một cách thống nhất và tường minh.

### 4.2. Cấu trúc JSON chuẩn của RFC 7807
Khi gặp lỗi, Server bắt buộc phải phản hồi về một Content-Type có tên là `application/problem+json` chứa các thuộc tính chuẩn sau:

- **`type`** (string - URI): Đường dẫn tài liệu mô tả chi tiết về loại lỗi này. Giúp lập trình viên click vào để đọc tài liệu sửa lỗi.
- **`title`** (string): Tiêu đề ngắn gọn, dễ hiểu về lỗi (không thay đổi giữa các request có cùng loại lỗi).
- **`status`** (number): Mã trạng thái HTTP (để Client không cần đọc header vẫn biết HTTP status).
- **`detail`** (string): Mô tả chi tiết lý do cụ thể xảy ra lỗi trong lượt request này.
- **`instance`** (string - URI): Đường dẫn URL cụ thể mà Client đã gọi dẫn đến lỗi này (dùng để truy vết log).

#### Các thuộc tính mở rộng (Extension Members):
Bạn có thể tự định nghĩa thêm các trường mở rộng ngoài chuẩn. Ví dụ: trường `invalid_params` hoặc `errors` chứa danh sách chi tiết các lỗi validate dữ liệu đầu vào.

---

### 4.3. Các ví dụ minh họa chuẩn RFC 7807

#### Ví dụ 1: Lỗi 403 Forbidden (Không đủ quyền)
```json
{
  "type": "https://example.com/probs/out-of-credit",
  "title": "You do not have enough credit.",
  "status": 403,
  "detail": "Your current balance is 30 USD, but this action costs 50 USD.",
  "instance": "/account/12345/withdraw"
}
```

#### Ví dụ 2: Lỗi 422 Unprocessable Entity (Validate dữ liệu đầu vào thất bại)
Đây là định dạng tối ưu để phản hồi lỗi validate form cho phía Frontend:
```json
{
  "type": "https://example.com/probs/validation-error",
  "title": "Validation Failed",
  "status": 422,
  "detail": "One or more request parameters failed to validate.",
  "instance": "/users/register",
  "errors": {
    "email": "Email format is invalid.",
    "password": "Password must be at least 8 characters and contain a number.",
    "date_of_birth": "Date of birth must be a valid ISO 8601 string."
  }
}
```
*Việc cấu hình lỗi theo chuẩn RFC 7807 giúp bất kỳ thư viện frontend nào cũng có thể tự động bóc tách lỗi và hiển thị lên UI một cách tự động và nhất quán.*
