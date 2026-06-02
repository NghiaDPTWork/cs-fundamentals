# NỀN TẢNG NEXT.JS (NEXT.JS FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức cốt lõi về framework Next.js, bao gồm các chiến lược hiển thị (Rendering), hệ thống định tuyến (Routing), sự phân chia giữa Server/Client Components và cơ chế Hydration.

---

## 1. CÁC CHIẾN LƯỢC RENDER (RENDERING STRATEGIES)

React truyền thống sử dụng CSR (Client-Side Rendering). Next.js ra đời để giải quyết các nhược điểm của CSR bằng cách cung cấp đa dạng các giải pháp kết hợp render ở cả Client và Server:

### 1.1. CSR (Client-Side Rendering)
*   *Cách chạy:* Server trả về một file HTML rỗng tuếch chỉ có thẻ `<div id="root"></div>` cùng một file Javascript rất lớn. Trình duyệt tải JS về, thực thi để tự dựng giao diện và gọi API lấy dữ liệu.
*   *Ưu điểm:* Trải nghiệm mượt mà sau khi tải trang đầu tiên (như Single Page App), giảm tải cho Server.
*   *Nhược điểm:* Trang tải đầu tiên rất chậm (White screen of death) vì phải đợi tải file JS lớn. SEO rất kém vì bọ quét tìm kiếm khi tải HTML chỉ thấy trang rỗng.

### 1.2. SSR (Server-Side Rendering)
*   *Cách chạy:* Với mỗi lượt truy cập (request) của người dùng, Next.js server sẽ lập tức chạy code lấy dữ liệu từ DB/API, dựng sẵn toàn bộ cấu trúc HTML hoàn chỉnh chứa đầy đủ nội dung dữ liệu rồi trả về cho trình duyệt.
*   *Ưu điểm:* SEO cực tốt vì nội dung có sẵn ngay lập tức. Người dùng thấy giao diện ngay (FCP nhanh).
*   *Nhược điểm:* Server phải làm việc liên tục ở mỗi request, làm tăng độ trễ phản hồi (TTFB) nếu lượng truy cập lớn hoặc API lấy dữ liệu bị chậm.

### 1.3. SSG (Static Site Generation)
*   *Cách chạy:* Toàn bộ các trang web được dựng sẵn thành các file HTML tĩnh từ lúc bạn build dự án (Build time). Khi người dùng truy cập, server chỉ việc lấy file tĩnh có sẵn trả về ngay lập tức (nhanh tương đương lưu trữ trên CDN).
*   *Ưu điểm:* Tốc độ tải trang nhanh nhất, Server chịu tải cực tốt vì không phải tính toán gì. SEO hoàn hảo.
*   *Nhược điểm:* Dữ liệu bị tĩnh. Nếu dữ liệu thay đổi (ví dụ: đổi giá sản phẩm), bạn bắt buộc phải build và deploy lại toàn bộ dự án để cập nhật nội dung.

### 1.4. ISR (Incremental Static Regeneration)
*   *Cách chạy:* Là giải pháp kết hợp ưu điểm của SSG và SSR. Cho phép bạn cập nhật các trang tĩnh đã dựng sẵn ở chế độ ngầm theo một chu kỳ thời gian (ví dụ: `revalidate: 60` - 60 giây).
*   *Hoạt động:* Người dùng truy cập vẫn nhận được trang tĩnh cực nhanh của SSG. Tuy nhiên, sau 60 giây, nếu có người dùng tiếp theo truy cập, Next.js sẽ kích hoạt dựng lại trang đó ở chế độ nền. Khi dựng xong trang mới, Next.js sẽ tráo đổi trang cũ bằng trang mới cho các lượt truy cập sau.
*   *Ưu điểm:* Dữ liệu luôn được cập nhật tự động mà vẫn giữ được hiệu năng và tốc độ tải trang cực đỉnh của SSG.

---

## 2. HỆ THỐNG ĐỊNH TUYẾN (ROUTING SYSTEM)

Next.js sử dụng cơ chế định tuyến dựa trên cấu trúc thư mục (File-system based router):

### 2.1. Pages Router (Mô hình cũ)
*   Mọi file nằm trong thư mục `pages/` sẽ tự động trở thành một route (ví dụ: `pages/about.js` tương ứng với đường dẫn `/about`).
*   Sử dụng các hàm lấy dữ liệu đặc thù như `getServerSideProps` (cho SSR), `getStaticProps` (cho SSG) ở cấp độ trang để truyền dữ liệu vào Component qua `props`.

### 2.2. App Router (Mô hình hiện đại - Từ Next.js 13)
*   Sử dụng thư mục `app/` làm gốc. Đường dẫn được định nghĩa bằng cấu trúc thư mục, và file giao diện chính phải có tên là `page.js` (ví dụ: `app/about/page.js` tương ứng với đường dẫn `/about`).
*   Hỗ trợ layout lồng nhau (`layout.js`) giúp giữ nguyên trạng thái và không bị re-render các phần giao diện chung (Header/Sidebar) khi chuyển trang.
*   **Mặc định:** Tất cả các component trong thư mục `app/` đều là **React Server Components (RSC)**.

---

## 3. SERVER COMPONENTS VS CLIENT COMPONENTS

Trong mô hình App Router, Next.js chia component thành hai loại để tối ưu hiệu năng:

```text
 ┌─────────────────────────────────────────────────────────────────┐
 │                       RANH GIỚI SỬ DỤNG                         │
 │                                                                 │
 │   Server Components                     Client Components       │
 │   - Mặc định trong app/                 - Khai báo "use client" │
 │   - Fetch dữ liệu trực tiếp từ DB/API   - Sử dụng Hooks (state) │
 │   - Bảo mật API keys, Tokens            - Thêm sự kiện (onClick)│
 │   - Giảm dung lượng JS gửi về client    - Thao tác DOM trực tiếp│
 └─────────────────────────────────────────────────────────────────┘
```

### 3.1. Server Components (RSC)
*   *Mặc định:* Không cần khai báo gì đặc biệt.
*   *Cơ chế:* Chạy và render hoàn toàn trên Server. Chỉ có cấu trúc giao diện đã dựng sẵn được gửi về trình duyệt, mã nguồn JS của component **không hề** được gửi về client.
*   *Ưu điểm:* Giảm thiểu tối đa dung lượng tải file JS của ứng dụng, có thể gọi DB hoặc API trực tiếp cực nhanh không qua API trung gian, bảo mật thông tin an toàn.
*   *Hạn chế:* Không thể sử dụng các tính năng tương tác client: không dùng Hooks (`useState`, `useEffect`), không gán sự kiện (`onClick`, `onChange`), không gọi được các API của trình duyệt (`window`, `document`).

### 3.2. Client Components
*   *Cách dùng:* Khai báo dòng chữ `"use client";` ở dòng đầu tiên của file code.
*   *Cơ chế:* Vẫn được render trước một phần HTML tĩnh trên Server (để tối ưu SEO), sau đó file JS của nó được gửi về trình duyệt để chạy các tương tác động.
*   *Ưu điểm:* Sử dụng đầy đủ tất cả các tính năng của React truyền thống (Hooks, Sự kiện, DOM).

---

## 4. KHÁI NIỆM HYDRATION (BÙ NƯỚC)

### 4.1. Hydration là gì?
Khi bạn sử dụng SSR hoặc Client Component trong Next.js, trình duyệt sẽ nhận về một file HTML tĩnh chứa toàn bộ nội dung hiển thị (để hiển thị nhanh nhất cho người dùng và phục vụ SEO). Tuy nhiên, file HTML này hoàn toàn là tĩnh, người dùng click nút bấm sẽ không có phản hồi gì vì chưa được liên kết với mã JavaScript xử lý sự kiện.

**Hydration (Tiến trình "bù nước")** là quá trình React chạy trên trình duyệt đọc cấu trúc HTML tĩnh đã có sẵn, phân tích và gắn các hàm xử lý sự kiện (Event Listeners), khởi tạo các trạng thái (`state`) vào cấu trúc DOM tĩnh đó để biến nó thành một ứng dụng React động có khả năng tương tác.

### 4.2. Lỗi mất cân đối Hydration (Hydration Mismatch Error)
Đây là lỗi cực kỳ phổ biến khi làm việc với Next.js (Lỗi hiển thị: *"Text content did not match server-rendered HTML"*).

*   *Nguyên nhân:* Xảy ra khi cấu trúc HTML được dựng trên Server **khác biệt** so với cấu trúc HTML được dựng ở lần chạy đầu tiên trên Client.
    *   *Ví dụ:* Dùng các biến động phụ thuộc vào môi trường client như `window.innerWidth`, hoặc hiển thị thời gian bằng `new Date()` (thời gian ở server lúc build/render khác với thời gian ở client lúc render).
*   *Cách khắc phục:* 
    1.  Chỉ chạy các đoạn code phụ thuộc client trong Hook `useEffect` (vì `useEffect` chỉ chạy sau khi hydration hoàn thành).
    2.  Sử dụng thuộc tính tắt cảnh báo của React: `suppressHydrationWarning` trên thẻ HTML.
    3.  Tắt render phía server cho component cụ thể bằng cách nhúng động qua `next/dynamic` với tùy chọn `{ ssr: false }`.
