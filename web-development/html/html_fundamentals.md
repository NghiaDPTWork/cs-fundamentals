# NỀN TẢNG HTML (HTML FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức nền tảng và thực tế về HTML, bao gồm các thẻ ngữ nghĩa (Semantic HTML), các tính năng mới của HTML5, cách xử lý Form và cơ chế phân tích cấu trúc DOM Tree của trình duyệt.

---

## 1. THẺ NGỮ NGHĨA (SEMANTIC HTML)

### 1.1. Sự khác biệt giữa Semantic và Non-semantic HTML
*   **Thẻ phi ngữ nghĩa (Non-semantic):** Thẻ không mang bất kỳ thông điệp nào về mặt nội dung của nó.
    *   *Ví dụ:* `<div>`, `<span>`. Chúng chỉ đơn thuần là các hộp chứa (containers) để gom nhóm phần tử phục vụ cho việc style (CSS) hoặc lập trình (JS).
*   **Thẻ ngữ nghĩa (Semantic):** Thẻ mô tả rõ ràng ý nghĩa và vai trò của nội dung bên trong nó cho cả trình duyệt và nhà phát triển dễ hiểu.
    *   *Ví dụ:* `<header>`, `<nav>`, `<main>`, `<article>`, `<section>`, `<aside>`, `<footer>`.

### 1.2. Tại sao Semantic HTML lại quan trọng?
1.  **SEO (Tối ưu hóa công cụ tìm kiếm):** Bọ quét (crawlers) của các công cụ tìm kiếm (như Google, Bing) sẽ đọc cấu trúc Semantic HTML để hiểu đâu là tiêu chí chính, đâu là phần thanh điều hướng, đâu là nội dung bài viết quan trọng. Điều này giúp xếp hạng trang web của bạn chính xác hơn.
2.  **Khả năng tiếp cận (Accessibility - A11y):** Các phần mềm đọc màn hình (Screen Readers) dành cho người khiếm thị dựa vào các thẻ semantic để giúp họ di chuyển nhanh qua các phần của trang web (ví dụ: chuyển nhanh tới thẻ `<main>` để đọc nội dung chính thay vì phải nghe hết toàn bộ danh sách liên kết trong `<nav>`).
3.  **Bảo trì mã nguồn:** Code sạch sẽ, có cấu trúc phân tầng rõ ràng, không bị rơi vào tình trạng "Div Soup" (quá nhiều thẻ `div` lồng nhau không rõ vai trò).

---

## 2. CÁC TÍNH NĂNG MỚI CỦA HTML5 (HTML5 FEATURES)

HTML5 giới thiệu nhiều tính năng mạnh mẽ giúp giảm thiểu sự phụ thuộc vào các plugin bên ngoài (như Flash trước đây):

### 2.1. Thẻ Media mới
*   **`<video>`:** Nhúng trực tiếp video vào trang web.
    ```html
    <video src="intro.mp4" controls width="600">
      Trình duyệt của bạn không hỗ trợ thẻ video.
    </video>
    ```
*   **`<audio>`:** Nhúng âm thanh trực tiếp.
    ```html
    <audio src="music.mp3" controls></audio>
    ```

### 2.2. Đồ họa Web (Canvas vs SVG)
*   **`<canvas>` (Đồ họa Bitmap):**
    *   *Cơ chế:* Vẽ đồ họa động bằng Javascript trên một lưới pixel.
    *   *Đặc điểm:* Khi phóng to sẽ bị vỡ hình (pixelated). Tối ưu cho các tác vụ vẽ đồ họa phức tạp, hiệu ứng game 2D/3D chuyển động nhanh, xử lý pixel trực tiếp.
*   **`<svg>` (Đồ họa Vector):**
    *   *Cơ chế:* Biểu diễn hình ảnh bằng cấu trúc XML (đường cong, hình khối dựa trên công thức toán học).
    *   *Đặc điểm:* Phóng to không giới hạn vẫn giữ nguyên độ sắc nét tuyệt đối. Dễ dàng tác động bằng CSS và JS. Tối ưu cho việc vẽ icon, logo, biểu đồ tĩnh hoặc động nhẹ.

### 2.3. Bộ lưu trữ cục bộ (Client-Side Storage)
Trước đây chỉ có Cookie (giới hạn dung lượng ~4KB và tự động gửi lên server qua mỗi request gây tốn băng thông). HTML5 mang đến hai bộ lưu trữ trực tiếp trên trình duyệt với dung lượng lớn hơn (~5MB-10MB) và bảo mật hơn:

| Tiêu chí so sánh | LocalStorage | SessionStorage |
| :--- | :--- | :--- |
| **Thời gian tồn tại** | Vĩnh viễn (Cho đến khi người dùng tự xóa cache trình duyệt hoặc code gọi xóa). | Tạm thời (Bị xóa ngay lập tức khi tab trình duyệt hoặc cửa sổ bị đóng lại). |
| **Phạm vi hoạt động** | Chia sẻ dữ liệu giữa tất cả các tab/cửa sổ có cùng tên miền (origin). | Chỉ tồn tại riêng biệt trong tab hiện tại (Mở tab mới cùng link sẽ tạo session mới). |
| **Ứng dụng thực tế** | Lưu cấu hình giao diện (Dark/Light mode), lưu Token đăng nhập. | Lưu trạng thái giỏ hàng tạm thời, lưu dữ liệu biểu mẫu đang điền dở. |

---

## 3. BIỂU MẪU VÀ XỬ LÝ RÀNG BUỘC (FORM & VALIDATION)

### 3.1. Cấu trúc một Form chuẩn
Một biểu mẫu chuẩn cần có thẻ `<form>` bao bọc, các phần tử input và nút submit rõ ràng:
```html
<form action="/login" method="POST">
  <div>
    <label for="username_input">Tài khoản:</label>
    <input type="text" id="username_input" name="username" required minlength="4">
  </div>
  
  <div>
    <label for="password_input">Mật khẩu:</label>
    <input type="password" id="password_input" name="password" required>
  </div>
  
  <button type="submit">Đăng nhập</button>
</form>
```

### 3.2. Sự khác biệt giữa `id` và `name` trong các thẻ Input
*   **Thuộc tính `id` (Dành cho Client-side):**
    *   *Mục đích:* Là định danh duy nhất của phần tử trên toàn bộ trang HTML.
    *   *Ứng dụng:* Dùng để liên kết nhãn `<label for="id_cua_input">`, dùng cho CSS Selector (`#id`), hoặc để truy xuất phần tử bằng Javascript (`document.getElementById()`).
*   **Thuộc tính `name` (Dành cho Server-side / Form Submit):**
    *   *Mục đích:* Xác định tên khóa (key) của dữ liệu sẽ được gửi lên máy chủ khi submit form.
    *   *Ứng dụng:* Nếu một input không có thuộc tính `name`, dữ liệu của nó sẽ **hoàn toàn bị bỏ qua** và không được gửi lên server khi submit form.
    *   *Ví dụ:* Với đoạn code trên, server sẽ nhận được request body dạng: `username=gia_tri_nhap&password=gia_tri_nhap`.

### 3.3. Các loại Input mới và cơ chế Validation mặc định
*   HTML5 bổ sung các loại input thông minh như `type="email"`, `type="url"`, `type="tel"`, `type="date"`, `type="number"`.
*   Trình duyệt sẽ tự động bắt lỗi (ví dụ: gõ thiếu ký tự `@` trong email) mà bạn không cần viết code Javascript kiểm tra nhờ các thuộc tính bổ trợ:
    *   `required`: Bắt buộc nhập.
    *   `pattern`: Ràng buộc định dạng bằng biểu thức chính quy (Regex).
    *   `minlength` / `maxlength`: Giới hạn số lượng ký tự.
    *   `min` / `max`: Giới hạn giá trị số hoặc ngày tháng.

---

## 4. CƠ CHẾ PHÂN TÍCH CẤU TRÚC DOM TREE

### 4.1. DOM (Document Object Model) là gì?
DOM không phải là HTML. HTML là mã nguồn dạng văn bản tĩnh được tải về từ server. Khi trình duyệt nhận được mã HTML này, nó sẽ phân tích (parse) và dựng lên một cấu trúc cây đối tượng trong bộ nhớ RAM gọi là **DOM Tree**. Mỗi thẻ HTML, thuộc tính, đoạn văn bản đều trở thành một Node trong cây này.

```text
                  [ Document ]
                       │
                  [ <html> ]
             ┌─────────┴─────────┐
          [ <head> ]          [ <body> ]
             │                   │
         [ <title> ]         [ <main> ]
                                 │
                             [ <h1> ]
                                 │
                         (Text: "Hello")
```

### 4.2. Tiến trình xử lý của Trình duyệt (Critical Rendering Path)
1.  **Parse HTML:** Trình duyệt đọc từ trên xuống dưới. Khi gặp thẻ HTML, nó tạo ra các Node tương ứng trên DOM Tree.
2.  **Gặp thẻ `<link rel="stylesheet">`:** Trình duyệt tạm dừng dựng DOM hoặc chạy song song để tải file CSS về và dựng cây kiểu dáng **CSSOM** (CSS Object Model).
3.  **Gặp thẻ `<script>` (Không có thuộc tính bổ trợ):** Trình duyệt sẽ **dừng hoàn toàn** việc phân tích HTML để tải file Javascript về và thực thi nó ngay lập tức (vì JS có thể thay đổi cấu trúc DOM). Hành vi này gọi là **Parser Blocking**.
4.  **Giải pháp tối ưu hóa:** Sử dụng thuộc tính `defer` hoặc `async` trong thẻ `<script>` để tải file JS bất đồng bộ ở chế độ nền mà không chặn tiến trình dựng DOM Tree của trình duyệt.
    *   `defer`: Tải JS song song với dựng DOM, nhưng chỉ thực thi JS sau khi DOM đã dựng xong hoàn toàn. (Giữ đúng thứ tự khai báo).
    *   `async`: Tải JS song song với dựng DOM, tải xong là dừng DOM để thực thi ngay lập tức. (Không quan tâm thứ tự).
