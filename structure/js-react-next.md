# Nền Tảng Front-end: React & Next.js

Để xây dựng giao diện web chuyên nghiệp, ta cần hiểu rõ từ các thư viện và framework nâng cao hỗ trợ xây dựng ứng dụng lớn như React và Next.js.

---

## 1. Thư Viện React

### 1.1. React là gì?
React là một thư viện JavaScript mã nguồn mở được Meta (Facebook) phát triển dùng để xây dựng giao diện người dùng (UI) dựa trên các thành phần độc lập, có thể tái sử dụng gọi là **Components**.

### 1.2. Sự khác biệt cốt lõi giữa React và Vanilla (Thuần) JS

Sự chuyển dịch từ Vanilla JS sang React đại diện cho sự thay đổi tư duy lập trình:

#### A. Tư duy Mệnh lệnh (Imperative - Vanilla JS) vs Tư duy Khai báo (Declarative - React)
*   **Vanilla JS (Mệnh lệnh)**: Bạn phải chỉ ra **từng bước cụ thể** để thay đổi giao diện.
    *   *Ví dụ*: Để tăng số đếm khi bấm nút, bạn phải tự tìm thẻ bằng ID, đọc giá trị cũ, ép kiểu, cộng 1, rồi cập nhật lại nội dung HTML bằng lệnh `document.getElementById('counter').innerText = newValue`.
*   **React (Khai báo)**: Bạn chỉ cần **mô tả giao diện trông như thế nào tương ứng với State (Trạng thái)**. Khi State thay đổi, React sẽ tự động cập nhật giao diện tương ứng mà bạn không cần trực tiếp động vào DOM.

#### B. Cơ chế Virtual DOM (DOM ảo)
Thao tác chỉnh sửa trực tiếp trên Real DOM của trình duyệt là một hoạt động cực kỳ đắt đỏ (chậm) vì mỗi lần thay đổi, trình duyệt phải tính toán lại Layout và vẽ lại (Repaint) giao diện.

```mermaid
graph TD
    StateChange[Trạng thái State thay đổi] --> NewVirtualDOM[Tạo cây Virtual DOM mới]
    NewVirtualDOM --> Diff[So sánh với cây Virtual DOM cũ - Diffing Algorithm]
    Diff --> ComputePatch[Tính toán những phần nhỏ thực sự thay đổi]
    ComputePatch --> UpdateRealDOM[Chỉ cập nhật những node đó trên Real DOM]
```

*   **Cách hoạt động**:
    1.  Khi dữ liệu thay đổi, React không cập nhật trực tiếp lên đĩa màn hình. Nó tạo ra một cây DOM ảo mới nằm trong bộ nhớ RAM (được biểu diễn dưới dạng các đối tượng JS gọn nhẹ).
    2.  React chạy thuật toán so sánh (**Diffing Algorithm / Reconciliation**) giữa cây DOM ảo mới và cây DOM ảo cũ để tìm ra chính xác những điểm khác biệt.
    3.  React gom tất cả thay đổi đó lại và chỉ cập nhật (patch) những phần thực sự thay đổi lên Real DOM của trình duyệt $\rightarrow$ Tăng hiệu năng ứng dụng lên gấp nhiều lần.

---

## 2. Khung Phát Triển Next.js (Framework)

### 2.1. Phân biệt Thư viện (Library - React) vs Framework (Next.js)
*   **React là Thư viện (Library)**: Chỉ tập trung vào việc render UI. Bạn có toàn quyền tự do lựa chọn các công cụ khác đi kèm (chọn thư viện Router nào, cách tổ chức thư mục ra sao, build tool gì). Nó giống như việc mua các viên gạch và bạn tự phải xây nhà.
*   **Next.js là Khung phát triển (Framework)**: Cung cấp sẵn một bộ khung hoàn chỉnh để phát triển ứng dụng production. Next.js quyết định sẵn cách bạn làm Routing (App Router), cách tối ưu hình ảnh, cách xử lý SSR/SSG. Bạn phải tuân thủ luật chơi của Next.js để đạt được hiệu quả tối ưu. Nó giống như một căn hộ chung cư đã xây thô sẵn khung, bạn chỉ vào hoàn thiện nội thất.

### 3.2. Ưu thế vượt trội của Next.js so với React (CRA - Client-Side Rendering)
React truyền thống sử dụng cơ chế **CSR (Client-Side Rendering)**: Server trả về một file HTML rỗng và một file JS khổng lồ. Trình duyệt tải file JS về rồi mới bắt đầu render giao diện.

| Tiêu chí | React CSR | Next.js (SSR / SSG / ISR) |
| :--- | :--- | :--- |
| **SEO (Search Engine Optimization)** | **Kém**. Bot tìm kiếm của Google quét trang HTML rỗng sẽ thấy không có nội dung, khó index từ khóa. | **Cực tốt**. Nội dung HTML đã được dựng đầy đủ chữ nghĩa trên Server trước khi trả về, bot index lập tức. |
| **Tốc độ tải trang đầu (FCP)** | **Chậm**. Người dùng phải chờ trình duyệt tải xong và thực thi đống file JS mới thấy giao diện (màn hình trắng). | **Nhanh**. Người dùng nhận được HTML hoàn chỉnh từ server và thấy giao diện ngay lập tức. |
| **Cách xử lý Routing** | Sử dụng thư viện ngoài (React Router DOM) chạy ở Client. | Tích hợp sẵn **File-based Routing** (đặt thư mục là tự động sinh Route) chạy ở Server. |
| **Tính bảo mật API** | API keys thường bị lộ trên trình duyệt do JS chạy ở Client. | An toàn hơn nhờ sử dụng **Server Components** và **Server Actions**, API keys được giấu kín hoàn toàn trên Server. |
