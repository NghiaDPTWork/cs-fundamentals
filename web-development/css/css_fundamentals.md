# NỀN TẢNG CSS (CSS FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức cốt lõi và thực tiễn về CSS (Cascading Style Sheets), tập trung vào Box Model, các chế độ hiển thị Display, hệ thống bố cục Flexbox/Grid, Responsive Design và biến CSS.

---

## 1. MÔ HÌNH HỘP (BOX MODEL)

Mọi phần tử trên giao diện web đều được coi là một chiếc hộp hình chữ nhật. Box Model định nghĩa cấu trúc của chiếc hộp này gồm 4 lớp từ trong ra ngoài:

```text
 ┌──────────────────────────────────────────┐
 │                  Margin                  │  <- Khoảng cách ngoài (giữa các hộp)
 │   ┌──────────────────────────────────┐   │
 │   │              Border              │   │  <- Đường viền của hộp
 │   │   ┌──────────────────────────┐   │   │
 │   │   │         Padding          │   │   │  <- Khoảng cách trong (viền tới nội dung)
 │   │   │   ┌──────────────────┐   │   │   │
 │   │   │   │     Content      │   │   │   │  <- Nội dung chính (chứa chữ, ảnh...)
 │   │   │   └──────────────────┘   │   │   │
 │   │   └──────────────────────────┘   │   │
 │   └──────────────────────────────────┘   │
 └──────────────────────────────────────────┘
```

### 1.1. Chi tiết 4 thành phần
*   **Content:** Khu vực hiển thị văn bản, hình ảnh.
*   **Padding:** Khoảng đệm giữa Content và Border. Không nhận giá trị âm.
*   **Border:** Đường viền bao quanh phần tử.
*   **Margin:** Khoảng cách giữa phần tử này với các phần tử khác xung quanh. Có thể nhận giá trị âm (để dịch chuyển đè phần tử lên nhau).

### 1.2. Box-sizing: border-box vs content-box
Sự khác biệt sinh tử quyết định xem layout của bạn có bị vỡ hay không nằm ở thuộc tính `box-sizing`:

*   **`content-box` (Mặc định của trình duyệt):**
    *   *Cách tính:* Chiều rộng thực tế hiển thị trên trình duyệt = `width` + `padding` + `border`.
    *   *Rủi ro:* Nếu bạn định nghĩa `.box { width: 100px; padding: 20px; border: 5px solid black; }`, chiều rộng thực tế của nó sẽ là $100 + 40 + 10 = 150\text{px}$. Điều này khiến các hộp dễ bị tràn dòng và vỡ bố cục khi thiết kế.
*   **`border-box` (Chuẩn mực thiết kế hiện đại):**
    *   *Cách tính:* Chiều rộng thực tế hiển thị trên trình duyệt = `width` đã khai báo. Trình duyệt tự co cụm vùng `content` nhỏ lại để nhường chỗ cho `padding` và `border`.
    *   *Lợi ích:* Với cùng ví dụ trên, chiều rộng thực tế luôn cố định là $100\text{px}$. Giúp việc tính toán kích thước layout cực kỳ chính xác.
    *   *Thực tế khuyên dùng:* Luôn đặt reset mặc định ở đầu file CSS:
        ```css
        *, *::before, *::after {
          box-sizing: border-box;
        }
        ```

---

## 2. CHẾ ĐỘ HIỂN THỊ (DISPLAY PROPERTIES)

Thuộc tính `display` xác định cách một phần tử sẽ hiển thị và chiếm không gian trên trang web:

*   **`block` (Khối):**
    *   *Đặc điểm:* Luôn bắt đầu trên một dòng mới và cố gắng kéo dài chiếm toàn bộ chiều rộng có sẵn (100% chiều rộng cha). Có thể set `width`, `height`, `margin`, `padding` tự do.
    *   *Mặc định:* `<div>`, `<p>`, `<h1>` đến `<h6>`, `<ul>`, `<li>`.
*   **`inline` (Nội dòng):**
    *   *Đặc điểm:* Không bắt đầu trên dòng mới, chỉ chiếm không gian vừa đủ chứa nội dung bên trong nó. **Không thể** set `width` và `height`. Không thể set `margin-top` / `margin-bottom`.
    *   *Mặc định:* `<span>`, `<a>`, `<strong>`, `<em>`.
*   **`inline-block` (Khối nội dòng):**
    *   *Đặc điểm:* Kết hợp cả hai: hiển thị trên cùng một dòng giống `inline`, nhưng **có thể** set `width`, `height`, `margin`, `padding` thoải mái giống `block`.
    *   *Mặc định:* `<button>`, `<input>`, `<img>`.
*   **`none` vs `visibility: hidden`:**
    *   `display: none`: Ẩn hoàn toàn phần tử và **xóa bỏ** nó khỏi dòng chảy của trang web (không chiếm bất kỳ không gian nào, giống như chưa từng tồn tại).
    *   `visibility: hidden`: Ẩn phần tử đi nhưng **vẫn giữ nguyên không gian** chiếm chỗ của nó (giao diện sẽ để lại một khoảng trống trống trơn đúng bằng kích thước phần tử).

---

## 3. HỆ THỐNG BỐ CỤC (LAYOUT SYSTEMS)

### 3.1. Flexbox (Bố cục 1 chiều - Dòng hoặc Cột)
Thiết kế để sắp xếp các phần tử con theo một chiều trục chính (Main Axis).

*   **Dành cho Container (Phần tử cha):**
    *   `display: flex;`: Kích hoạt chế độ Flex.
    *   `flex-direction`: `row` (ngang - mặc định) hoặc `column` (dọc).
    *   `justify-content`: Căn chỉnh phần tử con dọc theo **Trục chính** (`flex-start`, `flex-end`, `center`, `space-between`, `space-around`, `space-evenly`).
    *   `align-items`: Căn chỉnh phần tử con dọc theo **Trục phụ** (`stretch` - mặc định, `flex-start`, `flex-end`, `center`, `baseline`).
    *   `flex-wrap`: `nowrap` (chặn không cho xuống dòng - mặc định) hoặc `wrap` (cho phép các phần tử con xuống dòng mới khi hết chỗ).
*   **Dành cho Items (Phần tử con):**
    *   `align-self`: Cho phép một phần tử con tự căn chỉnh trục phụ khác biệt so với các phần tử còn lại.
    *   `flex-grow`: Khả năng tự giãn nở để lấp đầy khoảng trống thừa (mặc định là `0`).
    *   `flex-shrink`: Khả năng co lại khi container bị thiếu diện tích (mặc định là `1`).

### 3.2. CSS Grid (Bố cục 2 chiều - Dòng và Cột cùng lúc)
Thiết kế để xây dựng các layout phức tạp dạng lưới có cả hàng và cột.

*   **Các thuộc tính cốt lõi:**
    *   `display: grid;`: Kích hoạt chế độ Grid.
    *   `grid-template-columns`: Định nghĩa số lượng và độ rộng của các cột (ví dụ: `grid-template-columns: 1fr 2fr 1fr;` tạo ra 3 cột với tỷ lệ không gian tương ứng).
    *   `grid-template-rows`: Định nghĩa số lượng và chiều cao các hàng.
    *   `grid-gap` (hoặc `gap`): Khoảng cách giữa các ô lưới.
    *   `grid-column` / `grid-row`: Chỉ định vị trí ô bắt đầu và kết thúc của một phần tử con trên lưới (ví dụ: `grid-column: 1 / 3;` giúp phần tử trải dài từ đường lưới 1 đến đường lưới 3).

---

## 4. THIẾT KẾ ĐÁP ỨNG (RESPONSIVE DESIGN)

### 4.1. Media Queries
Cho phép thay đổi giao diện trang web tùy thuộc vào kích thước màn hình thiết bị (Viewport):
```css
/* Áp dụng cho các màn hình có chiều rộng từ 768px trở xuống (thường là Tablet và Mobile) */
@media (max-width: 768px) {
  .sidebar {
    display: none; /* Ẩn thanh bên trên mobile */
  }
  .main-content {
    width: 100%;
  }
}
```

### 4.2. Chiến lược Mobile-first vs Desktop-first
*   **Mobile-first (Khuyên dùng):** Viết code CSS cho màn hình nhỏ nhất (mobile) trước làm gốc không cần media query, sau đó dùng `@media (min-width: ...)` để bổ sung/ghi đè CSS cho các màn hình lớn hơn (Tablet, Desktop).
    *   *Ưu điểm:* Tải trang mobile nhanh hơn, code sạch và dễ duy trì hơn.
*   **Desktop-first:** Viết code CSS cho màn hình lớn (desktop) trước làm gốc, sau đó dùng `@media (max-width: ...)` để thu hẹp giao diện cho màn hình nhỏ.

---

## 5. BIẾN TRONG CSS (CSS VARIABLES)

CSS Variables (Custom Properties) cho phép lưu trữ các giá trị CSS dùng đi dùng lại nhiều lần để dễ bảo trì toàn hệ thống.

### 5.1. Khai báo và sử dụng
Biến thường được khai báo trong selector `:root` (đại diện cho toàn bộ trang web) để có phạm vi toàn cục:
```css
:root {
  --primary-color: #3b82f6;
  --font-base: 16px;
  --spacing-md: 16px;
}

.button {
  background-color: var(--primary-color);
  font-size: var(--font-base);
  padding: var(--spacing-md);
}
```

### 5.2. So sánh Biến CSS vs Biến SASS/SCSS
*   **Biến SASS/SCSS (Ví dụ: `$primary-color: #3b82f6;`):**
    *   *Bản chất:* Được biên dịch ở phía nhà phát triển trước khi chạy. Sau khi chuyển thành CSS thuần, các biến này hoàn toàn biến mất.
    *   *Nhược điểm:* Trình duyệt không nhìn thấy biến, không thể thay đổi động bằng Javascript tại runtime.
*   **Biến CSS thuần (Ví dụ: `--primary-color`):**
    *   *Bản chất:* Tồn tại trực tiếp trên trình duyệt lúc chạy (Runtime).
    *   *Ưu điểm vượt trội:* Có tính kế thừa (inheritance). Có thể dễ dàng thay đổi động bằng Javascript. Rất thích hợp để làm tính năng thay đổi theme (Dark/Light mode) trong nháy mắt:
        ```javascript
        // Thay đổi biến CSS trực tiếp qua JS
        document.documentElement.style.setProperty('--primary-color', '#10b981');
        ```
