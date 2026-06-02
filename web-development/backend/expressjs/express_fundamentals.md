# NỀN TẢNG EXPRESS.JS (EXPRESS.JS FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức căn bản đến nâng cao về web framework Express.js, bao gồm hệ thống định tuyến (Routing), mô hình xử lý trung gian Middleware, chuẩn thiết kế RESTful API, cơ chế bắt lỗi tập trung và cấu trúc dự án MVC.

---

## 1. ĐỊNH TUYẾN VÀ ĐỐI TƯỢNG YÊU CẦU/PHẢN HỒI (ROUTING & REQ/RES)

Express.js cung cấp một bộ định tuyến mạnh mẽ giúp ánh xạ các yêu cầu HTTP (HTTP Requests) tới các hàm xử lý tương ứng:

```javascript
const express = require('express');
const app = express();

// Middleware hỗ trợ đọc dữ liệu JSON gửi lên qua request body
app.use(express.json());
```

### 1.1. Xử lý các tham số đầu vào từ Client (`req`)
Có 3 cách chính để client gửi dữ liệu lên server và cách Express.js truy xuất chúng qua đối tượng `req` (Request):

1.  **`req.params` (Tham số đường dẫn - Route Parameters):**
    *   *Sử dụng:* Dành cho các tham số định danh bắt buộc nằm trên URL.
    *   *Khai báo:* `app.get('/users/:id', ...)`
    *   *Truy xuất:* Nếu URL là `/users/42` $\rightarrow$ `req.params.id` sẽ là `"42"`.
2.  **`req.query` (Tham số truy vấn - Query Parameters):**
    *   *Sử dụng:* Dành cho các tham số tùy chọn dùng để lọc, sắp xếp, phân trang dữ liệu. Nằm sau dấu chấm hỏi `?` trên URL.
    *   *URL ví dụ:* `/products?category=shoes&sort=price_asc`
    *   *Truy xuất:* `req.query.category` là `"shoes"`, `req.query.sort` là `"price_asc"`.
3.  **`req.body` (Thân yêu cầu - Request Body):**
    *   *Sử dụng:* Dành cho dữ liệu phức tạp, nhạy cảm (như mật khẩu, thông tin bài viết) gửi ngầm qua các method POST, PUT, PATCH.
    *   *Truy xuất:* `req.body.username`, `req.body.password`. (Yêu cầu phải có middleware `express.json()` được kích hoạt trước).

### 1.2. Trả về phản hồi cho Client (`res`)
Đối tượng `res` (Response) cung cấp các phương thức để hoàn thành yêu cầu và gửi dữ liệu về cho client:
*   `res.send(data)`: Gửi phản hồi dạng chuỗi hoặc HTML thô.
*   `res.json(object)`: Gửi phản hồi dạng JSON (Tự động set Header `Content-Type: application/json`).
*   `res.status(code)`: Thiết lập mã trạng thái HTTP trước khi gửi (ví dụ: `res.status(201).json(newUser)`).

---

## 2. MÔ HÌNH TRUNG GIAN (MIDDLEWARE PATTERN)

Middleware là trái tim của Express.js. Nó là các hàm trung gian nằm giữa luồng Yêu cầu (Request) và Phản hồi (Response).

```text
 Client Request ───> [ Middleware 1 ] ───> [ Middleware 2 ] ───> Route Handler ───> Client Response
                          │                     │
                    (Gọi next())          (Gọi next())
```

### 2.1. Cấu trúc hàm Middleware và vai trò của `next()`
Một hàm middleware nhận vào 3 tham số: `req`, `res`, và hàm `next`.
*   **Hàm `next()`:** Có vai trò chuyển giao quyền điều khiển sang hàm middleware hoặc route handler tiếp theo. Nếu trong middleware bạn không gọi `next()` và cũng không kết thúc request (bằng cách gọi `res.send`/`res.json`), request của client sẽ **bị treo vô hạn** cho đến khi bị timeout.

### 2.2. Phân loại Middleware
Express.js chia middleware thành 4 cấp độ quản lý:

1.  **Application-level Middleware (Cấp ứng dụng):** Gắn trực tiếp vào đối tượng `app` bằng `app.use()`. Nó sẽ chạy qua **mọi** request gửi tới server.
    ```javascript
    // Middleware ghi nhận log truy cập của mọi request
    app.use((req, res, next) => {
      console.log(`${new Date().toISOString()} - ${req.method} ${req.url}`);
      next(); // Bắt buộc phải gọi next() để đi tiếp
    });
    ```
2.  **Router-level Middleware (Cấp bộ định tuyến):** Gắn vào một thực thể `express.Router()`. Chỉ chạy qua các route thuộc nhóm đó (ví dụ: nhóm API `/api/v1/admin`).
3.  **Built-in Middleware (Tích hợp sẵn):** Có sẵn trong Express như `express.json()` (để parse JSON body), `express.static()` (để phục vụ các file tĩnh như ảnh, CSS, JS).
4.  **Error-handling Middleware (Xử lý lỗi):** Xem chi tiết ở Mục 4 dưới đây.

---

## 3. THIẾT KẾ RESTFUL API CHUẨN MỰC

RESTful API là kiến trúc thiết kế API tiêu chuẩn dựa trên giao thức HTTP:

### 3.1. Các HTTP Methods chuẩn
*   `GET`: Truy xuất/lấy thông tin dữ liệu (Không làm thay đổi dữ liệu trên server).
*   `POST`: Tạo mới một bản ghi dữ liệu.
*   `PUT`: Cập nhật toàn bộ bản ghi dữ liệu (Thay thế bản ghi cũ).
*   `PATCH`: Cập nhật một phần của bản ghi dữ liệu (Chỉ sửa một vài thuộc tính cụ thể).
*   `DELETE`: Xóa bỏ bản ghi dữ liệu.

### 3.2. Mã trạng thái HTTP (HTTP Status Codes) tương ứng
*   `200 OK`: Yêu cầu thành công (thường dùng cho GET, PUT, PATCH).
*   `201 Created`: Tạo mới thành công (thường dùng cho POST).
*   `400 Bad Request`: Dữ liệu gửi lên không hợp lệ hoặc thiếu thuộc tính bắt buộc.
*   `401 Unauthorized`: Client chưa đăng nhập hoặc token xác thực không hợp lệ.
*   `403 Forbidden`: Client đã đăng nhập nhưng không có quyền truy cập tài nguyên này.
*   `404 Not Found`: Không tìm thấy tài nguyên yêu cầu.
*   `500 Internal Server Error`: Lỗi hệ thống xảy ra trên server (lỗi code, crash database).

---

## 4. CƠ CHẾ XỬ LÝ LỖI TẬP TRUNG (CENTRALIZED ERROR HANDLING)

Nếu xảy ra lỗi trong quá trình xử lý (như đọc file lỗi, gọi DB lỗi) mà không được bắt (`catch`), ứng dụng Node.js sẽ crash lập tức. Express.js cung cấp cơ chế bắt lỗi tập trung cực kỳ trang nhã:

### 4.1. Cách viết Error-handling Middleware
Đây là loại middleware đặc biệt, được nhận diện bằng việc có **đúng 4 tham số đầu vào** (`err`, `req`, `res`, `next`). Node.js sẽ tự động chuyển hướng luồng chạy về đây khi bạn gọi hàm `next(err)` có truyền lỗi ở bất kỳ đâu trong ứng dụng.

```javascript
// Khai báo cuối cùng trong file code, sau tất cả các route khác
app.use((err, req, res, next) => {
  console.error('Lỗi hệ thống xảy ra:', err.stack);
  
  const statusCode = err.statusCode || 500;
  res.status(statusCode).json({
    status: 'error',
    message: err.message || 'Lỗi hệ thống nội bộ.'
  });
});
```

### 4.2. Cách kích hoạt chuyển hướng lỗi
```javascript
app.get('/data', async (req, res, next) => {
  try {
    const data = await readDataFromDatabase();
    res.json(data);
  } catch (error) {
    // Truyền lỗi vào next(), Express sẽ tự động chuyển qua Error-handling Middleware phía dưới
    next(error);
  }
});
```

---

## 5. CẤU TRÚC DỰ ÁN MVC VÀ KẾT NỐI DATABASE

Khi xây dựng ứng dụng lớn, ta cần chia nhỏ mã nguồn theo mô hình **MVC (Model-View-Controller)** kết hợp ORM/ODM để quản lý code dễ dàng:

### 5.1. Cấu trúc thư mục chuẩn
```text
src/
├── config/             # Cấu hình kết nối Database, môi trường (.env)
├── models/             # Định nghĩa cấu trúc bảng (Schema của Mongoose hoặc Prisma)
├── controllers/        # Xử lý logic nghiệp vụ, tiếp nhận req và trả res
├── routes/             # Định nghĩa các endpoints và gắn controllers tương ứng
├── middlewares/        # Chứa các middleware xác thực, phân quyền, validation
└── app.js              # Khởi tạo Express app, nạp cấu hình và khởi chạy server
```

### 5.2. Khởi tạo kết nối Database (Ví dụ với Mongoose - MongoDB)
```javascript
// config/db.js
const mongoose = require('mongoose');

const connectDB = async () => {
  try {
    await mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/my_app');
    console.log('Kết nối thành công tới MongoDB!');
  } catch (error) {
    console.error('Lỗi kết nối Database:', error);
    process.exit(1); // Dừng ứng dụng ngay lập tức
  }
};

module.exports = connectDB;
```
