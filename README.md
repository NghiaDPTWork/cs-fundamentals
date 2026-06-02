# SỔ TAY NỀN TẢNG KHOA HỌC MÁY TÍNH VÀ LẬP TRÌNH WEB

Chào mừng bạn đến với kho lưu trữ tài liệu cá nhân hóa phục vụ ôn tập, củng cố và nâng cao kiến thức Khoa học Máy tính (Computer Science Foundations) và Lập trình Web (Web Development) từ cơ bản đến nâng cao.

Tài liệu được trình bày theo phong cách trực diện, thực tế, phi hàn lâm, sử dụng sơ đồ ASCII và bảng so sánh trực quan, thích hợp làm cẩm nang ôn luyện phỏng vấn kỹ thuật (SDE/Web Developer).

---

## 1. BẢN ĐỒ THƯ MỤC KIẾN THỨC (REPOSITORY COMPASS)

Dưới đây là cấu trúc phân cấp các chủ đề học tập hiện tại trong kho lưu trữ:

```text
cs-fundamentals/
├── network/              # Mạng máy tính (Căn bản & Chuyên sâu)
├── database/             # Cơ sở dữ liệu (ACID, Kiến trúc, SQL, NoSQL, Graph)
├── dsa/                  # Cấu trúc dữ liệu & Giải thuật (Big O, CRUD, Search/Sort)
├── git/                  # Hệ thống quản lý phiên bản (Kiến trúc, Rebase, Reflog)
├── sdlc/                 # Chu trình phát triển phần mềm (Roles, Models, Agile/Scrum)
├── web-development/      # Lộ trình Web (HTML, CSS, JS, React, Next.js, Node, Express)
├── operation-system/     # Hệ điều hành (Nền tảng OS)
├── programming/          # Lập trình cơ bản (Tư duy, Khái niệm dịch/thực thi)
└── testing/              # Kiểm thử phần mềm (Quy trình test)
```

---

## 2. CHỈ MỤC CÁC CHUYÊN ĐỀ HỌC TẬP (STUDY PORTAL)

Hãy nhấp vào các liên kết trực tiếp dưới đây để bắt đầu ôn tập từng chủ đề:

### 2.1. Mạng máy tính (Computer Network)
*   [network_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/network/network_fundamentals.md): Tìm hiểu mô hình phân tầng (OSI vs TCP/IP), phân biệt IP vs MAC, cách hoạt động của các giao thức cốt lõi (DNS, DHCP, ARP, NAT).
*   [network_advanced.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/network/network_advanced.md): Đi sâu vào giao thức TCP, cơ chế bắt tay 3 bước (3-way handshake), kết thúc kết nối 4 bước, kiểm soát luồng (Flow Control) và kiểm soát tắc nghẽn (Congestion Control).

### 2.2. Hệ thống Cơ sở dữ liệu (Database)
*   [database_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/database/database_fundamentals.md): Tính chất ACID, cơ chế khóa bảng/dòng (Shared vs Exclusive Lock), toàn vẹn dữ liệu (Entity, Referential, Domain), quy trình 3 bước quyết định lựa chọn Database, định nghĩa ORM/ODM và thuật ngữ giao dịch.
*   [database_advanced.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/database/database_advanced.md): Quản lý phiên bản Database (State-based, Migration-based, Church-based), Materialized Views, giải pháp của GraphQL cho REST API (Over/Under-fetching), định lý CAP/PACELC, nhân bản (Replication) vs phân mảnh (Sharding), so sánh Storage Engines (B-Tree vs LSM-Tree, Row vs Column-oriented).
*   [sql.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/database/sql.md): Ngôn ngữ SQL, thứ tự viết vs thứ tự thực thi vật lý của câu lệnh SELECT, trực quan hóa các phép JOIN bằng sơ đồ Venn.
*   [nosql.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/database/nosql.md): Thao tác truy vấn MongoDB (CRUD, Query Operators, Aggregation Pipeline) và các câu lệnh tương tác dữ liệu Redis.
*   [graph.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/database/graph.md): Ngôn ngữ truy vấn Cypher trong Neo4j, cú pháp biểu diễn ASCII-art và cách viết query duyệt đồ thị đa tầng.

### 2.3. Cấu trúc dữ liệu & Giải thuật (DSA)
*   [data_structures.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/dsa/data_structures.md): Bản chất điểm chung của mọi cấu trúc dữ liệu (Cách lưu trữ vật lý trên RAM và bộ 4 thao tác CRUD), đi sâu vào các cấu trúc dữ liệu: Array, Linked List, Stack, Queue, Hash Table, Binary Tree, Graph.
*   [algorithms.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/dsa/algorithms.md): Cách tính độ phức tạp thời gian (Time Complexity - Big O) và độ phức tạp không gian (Space Complexity - Call Stack), lý thuyết và mã giả các thuật toán Tìm kiếm (Linear/Binary Search) và Sắp xếp (Bubble, Selection, Insertion, Merge, Quick Sort).

### 2.4. Quản lý phiên bản với Git (Git Version Control)
*   [git_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/git/git_fundamentals.md): So sánh Git vs GitHub vs GitLab, kiến trúc 3 vùng nhớ cục bộ (Working directory, Staging area, Local repo) và quy trình làm việc hàng ngày.
*   [git_advanced.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/git/git_advanced.md): Kỹ thuật Rebase cục bộ trước khi Merge, cách cứu dữ liệu và khôi phục lịch sử bằng `git reflog`, phân biệt `git reset` (Soft, Mixed, Hard) vs `git revert`.

### 2.5. Chu trình phát triển phần mềm (SDLC)
*   [sdlc_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/sdlc/sdlc_fundamentals.md): Khái niệm SDLC, các pha cơ bản, vai trò chi tiết của từng vị trí dự án (PO, BA, Dev, QE, SM, DevOps), và bảng so sánh toàn diện giữa mô hình công ty Outsource vs Product.
*   [sdlc_advanced.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/sdlc/sdlc_advanced.md): Giai đoạn Planning (Define Problem + Set Scope), phân tích chi tiết các mô hình Waterfall, Iterative, và triết lý Agile vs khung làm việc Scrum, kỹ thuật Pair Programming trong XP, các công cụ quản lý mã nguồn (SCM).

### 2.6. Lộ trình phát triển Web (Web Development Roadmap)
Một cổng thông tin học tập mới phân bổ từ căn bản đến nâng cao cho lập trình viên web:
*   [html_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/html/html_fundamentals.md): Thẻ ngữ nghĩa Semantic HTML, tính năng HTML5 (Canvas, SVG, Web Storage), xử lý Form và DOM Tree parsing.
*   [css_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/css/css_fundamentals.md): Lớp cấu trúc Box Model, hiển thị Display, hệ thống bố cục Flexbox & CSS Grid, Responsive Design và biến CSS.
*   [js_basic.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/js-basic/js_basic.md): Biến và kiểu dữ liệu (Primitive vs Reference), so sánh tương đối/tuyệt đối, cơ chế binding từ khóa `this` của hàm, hàm mảng nâng cao, Event Propagation và Event Delegation.
*   [js_advanced.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/frontend/js-advanced/js_advanced.md): Closure, Prototype-based inheritance, xử lý bất đồng bộ (Callbacks, Promises, Async/Await), Event Loop internals và cơ chế giải phóng bộ nhớ Garbage Collection.
*   [react_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/frontend/react/react_fundamentals.md): Virtual DOM & Reconciliation diffing, JSX, Props vs State, các Hooks cốt lõi (`useState`, `useEffect`, `useContext`, `useMemo`, `useCallback`, `useRef`), quản lý trạng thái tập trung.
*   [nextjs_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/frontend/nextjs/nextjs_fundamentals.md): Các chế độ render (CSR, SSR, SSG, ISR), Routing App Router, Server Components vs Client Components, cơ chế Hydration.
*   [nodejs_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/backend/nodejs/nodejs_fundamentals.md): Vòng lặp Libuv Event Loop 6 pha, non-blocking I/O, Buffer & Stream (Pipe), CommonJS vs ES Modules, và thực hành core modules.
*   [express_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/web-development/backend/expressjs/express_fundamentals.md): Tiếp nhận tham số đường dẫn/truy vấn/body, mô hình Middleware, chuẩn RESTful API, Error-handling bắt lỗi tập trung, cấu trúc dự án MVC.

### 2.7. Hệ điều hành & Kiểm thử (OS & Testing)
*   [os_fundamentals.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/operation-system/os_fundamentals.md): Các khái niệm nền tảng về hệ điều hành.
*   [testing_basic.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/testing/docs/testing_basic.md): Khái niệm kiểm thử cơ bản, quy trình kiểm thử và nguyên tắc quản lý lỗi.
*   [programming_basic.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/programming/programming_basic.md) & [programming_concepts.md](file:///d:/FOR_LEARN/TU_HOC/cs-fundamentals/programming/programming_concepts.md): Nền tảng lập trình cơ bản và các khái niệm cốt lõi.

---
*Last updated: 2026-06-02*
