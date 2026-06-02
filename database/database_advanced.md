# KIẾN TRÚC CƠ SỞ DỮ LIỆU NÂNG CAO (DATABASE ADVANCED)

Tài liệu này đi sâu vào các khía cạnh kiến trúc nâng cao của Cơ sở dữ liệu, bao gồm quản lý phiên bản (Database Migrations), các khái niệm tối ưu nâng cao (Materialized Views, GraphQL), lý thuyết hệ thống phân tán và cơ chế lưu trữ vật lý bên dưới.

---

## 1. QUẢN LÝ PHIÊN BẢN CƠ SỞ DỮ LIỆU (DATABASE VERSION CONTROL)

Khi làm việc trong dự án thực tế, cấu trúc bảng (schema) liên tục thay đổi. Việc quản lý các thay đổi này được chia thành các trường phái chính:

### 1.1. State-based Version Control (Quản lý phiên bản theo trạng thái)
*   **Cách hoạt động:** Nhà phát triển định nghĩa một trạng thái schema mong muốn (mong muốn bảng có những cột nào, kiểu dữ liệu gì) trong một file cấu hình hoặc file mã nguồn. Khi chạy deploy, công cụ so sánh (Diff) trạng thái mong muốn này với cấu trúc database thực tế hiện tại, tự động sinh ra các lệnh SQL cần thiết (`ALTER TABLE`, `ADD COLUMN`...) và thực thi để đồng bộ database hiện tại lên trạng thái mong muốn.
*   **Ưu điểm:** Khai báo (declarative) đơn giản, lập trình viên không cần tự viết mã SQL thủ công.
*   **Nhược điểm:** Phức tạp khi thực hiện các thay đổi phá hủy dữ liệu (ví dụ: đổi tên cột từ `fullname` thành `first_name` và `last_name` - công cụ tự động sẽ không biết cách chia nhỏ dữ liệu mà chỉ đơn giản là xóa cột cũ và thêm cột mới, làm mất sạch dữ liệu).
*   **Công cụ tiêu biểu:** SQL Server Data Tools (SSDT), Prisma (ở chế độ db push).

### 1.2. Migration-based Version Control (Quản lý phiên bản theo dịch chuyển)
*   **Cách hoạt động:** Mọi thay đổi cấu trúc dữ liệu được lưu trữ thành các file script SQL (hoặc code script) có đánh số thứ tự hoặc timestamp tăng dần (ví dụ: `V1__create_users_table.sql`, `V2__add_email_to_users.sql`). Khi deploy, hệ thống đọc bảng lịch sử migration trong database (như bảng `schema_version`) để biết file nào đã chạy và chỉ chạy tiếp các file script mới hơn.
*   **Ưu điểm:** Tuyệt đối chính xác và an toàn. Nhà phát triển kiểm soát hoàn toàn cách thức dữ liệu được dịch chuyển và xử lý (chỉ định rõ cách chuyển đổi dữ liệu khi tách cột/đổi tên).
*   **Nhược điểm:** Mất thời gian viết và quản lý các file migration. Nếu lỡ sửa đổi file migration cũ đã chạy trên production, sẽ gây lỗi lệch checksum lịch sử.
*   **Công cụ tiêu biểu:** Flyway, Liquibase, Knex, Prisma Migrations.

### 1.3. Church-based Version Control (Database as a Value)
*   **Nguồn gốc & Triết lý:** Lấy cảm hứng từ phép tính Lambda của Alonzo Church và được hiện thực hóa trong các database hiện đại như **Datomic**. 
*   **Bản chất:** Coi toàn bộ cơ sở dữ liệu như một giá trị bất biến theo thời gian. 
    *   Trong database thông thường, khi bạn chạy câu lệnh `UPDATE users SET age = 29 WHERE id = 1`, giá trị `28` cũ bị ghi đè thô bạo và biến mất vĩnh viễn.
    *   Trong Church-based DB, dữ liệu không bao giờ bị ghi đè hay xóa bỏ. Mọi thay đổi đều được ghi lại dưới dạng một **Sự kiện tích lũy (Fact)** bất biến theo thời gian (ví dụ: *"Tại thời điểm T1, User 1 có age = 28"*, *"Tại thời điểm T2, User 1 được gán age = 29"*).
*   **Ưu điểm vượt trội (Time Travel Query):** Cho phép bạn truy vấn cơ sở dữ liệu tại bất kỳ thời điểm nào trong lịch sử (ví dụ: *"Hãy cho tôi biết giỏ hàng của User 1 trông như thế nào vào lúc 10h sáng ngày hôm qua"*). Nó giải quyết triệt để bài toán Audit Log và khôi phục dữ liệu mà không cần backup/restore phức tạp.

---

## 2. CÁC KHÁI NIỆM NÂNG CAO KHÁC

### 2.1. Materialized Views vs View thông thường
*   **View thông thường (Virtual View):**
    *   *Bản chất:* Chỉ là một câu truy vấn SELECT được lưu lại dưới một cái tên. Nó không hề chứa dữ liệu vật lý.
    *   *Hoạt động:* Mỗi khi bạn gọi `SELECT * FROM my_view`, database engine lại âm thầm chạy lại câu truy vấn gốc bên trong View để lấy dữ liệu.
    *   *Tình huống dùng:* Tiết kiệm việc viết lại các câu query dài dòng, bảo mật dữ liệu bằng cách ẩn đi một số cột của bảng gốc.
*   **Materialized View (View vật lý hóa):**
    *   *Bản chất:* Là một View được chạy trước câu truy vấn và **lưu trực tiếp kết quả dữ liệu xuống đĩa cứng** giống như một bảng thực sự.
    *   *Hoạt động:* Khi bạn gọi `SELECT * FROM my_materialized_view`, database trả về dữ liệu lưu sẵn ngay lập tức mà không cần chạy lại các phép tính phức tạp (như tính tổng, JOIN 5 bảng).
    *   *Đánh đổi:* Dữ liệu trong Materialized View sẽ bị cũ (stale) đi so với bảng gốc. Bạn phải định kỳ gọi lệnh làm mới (`REFRESH MATERIALIZED VIEW`) bằng tay hoặc qua cronjob để cập nhật dữ liệu mới nhất.
    *   *Tình huống dùng:* Phù hợp cho các trang báo cáo, thống kê dữ liệu lớn (BI/Dashboard) cần hiển thị nhanh và chấp nhận dữ liệu có độ trễ nhẹ.

---

### 2.2. GraphQL (Giải pháp thay thế REST API cho Database)
GraphQL là ngôn ngữ truy vấn dành cho API, giúp client lấy chính xác dữ liệu họ cần, tránh các nhược điểm của REST API:

*   **Over-fetching (Tải thừa dữ liệu):**
    *   *Vấn đề ở REST:* Khi gọi API `/users/1` để hiển thị tên user ở góc màn hình, server trả về toàn bộ thông tin gồm: tên, tuổi, địa chỉ, lịch sử mua hàng, mật khẩu đã hash... gây lãng phí băng thông mạng.
    *   *GraphQL giải quyết:* Client tự chỉ định chính xác các trường cần lấy: `query { user(id: 1) { name } }`. Server chỉ trả về đúng trường `name`.
*   **Under-fetching (Tải thiếu dữ liệu):**
    *   *Vấn đề ở REST:* Để hiển thị danh sách bài viết kèm danh sách comment của từng bài, client phải gọi `/posts` để lấy bài viết, sau đó với mỗi bài viết phải gọi tiếp `/posts/{id}/comments` (vấn đề $N+1$ query ở client).
    *   *GraphQL giải quyết:* Client có thể lấy toàn bộ thông tin lồng nhau trong duy nhất 1 request:
        ```graphql
        query {
          posts {
            title
            comments {
              content
            }
          }
        }
        ```

---

## 3. LÝ THUYẾT HỆ THỐNG PHÂN TÁN (DISTRIBUTED SYSTEMS)

### 3.1. CAP Theorem vs PACELC Theorem
Đây là hai định lý định hướng thiết kế hệ thống dữ liệu phân tán:

*   **CAP Theorem:** Trong một hệ thống phân tán, khi xảy ra sự cố mạng làm mất kết nối giữa các máy chủ (**P**artition Tolerance), ta bắt buộc phải chọn một trong hai:
    *   **C**onsistency (Nhất quán): Tất cả các máy chủ đều trả về cùng một dữ liệu mới nhất. Nếu một máy chủ chưa kịp cập nhật, nó sẽ trả về lỗi.
    *   **A**vailability (Khả dụng): Mọi yêu cầu đều được phản hồi thành công, nhưng chấp nhận dữ liệu có thể bị cũ.
*   **PACELC Theorem (Mở rộng CAP):** Định nghĩa hành vi hệ thống cả khi hoạt động bình thường (không bị đứt mạng):
    *   Nếu có **P**artition (mất mạng) $\rightarrow$ Chọn **A**vailability hay **C**onsistency?
    *   **E**lse (mạng bình thường) $\rightarrow$ Chọn **L**atency (tốc độ phản hồi nhanh) hay **C**onsistency (nhất quán dữ liệu tuyệt đối)?

---

### 3.2. Cơ chế nhân bản & mở rộng
*   **Replication (Nhân bản):** Sao chép dữ liệu sang nhiều node server.
    *   *Master-Slave (Primary-Replica):* Mọi thao tác Ghi đều chạy trên node Master, sau đó đồng bộ (sync/async) sang các node Slave. Thao tác Đọc chạy trên các node Slave để giảm tải Master.
    *   *Multi-Master:* Nhiều node cùng được phép ghi, sau đó giải quyết xung đột ghi chéo nhau.
*   **Sharding (Phân mảnh ngang):** Chia nhỏ bảng dữ liệu khổng lồ và đặt chúng ở các server vật lý hoàn toàn độc lập (mỗi máy chứa 1 phần dữ liệu, ví dụ: Shard A lưu user ID từ 1-1.000.000, Shard B lưu từ 1.000.001-2.000.000).
*   **Partitioning (Phân mảnh dọc/ngang nội bộ):** Chia nhỏ bảng dữ liệu lớn ngay trên cùng một server vật lý để tăng tốc truy vấn.

---

## 4. CHI TIẾT BỘ MÁY LƯU TRỮ VẬT LÝ (STORAGE INTERNALS)

### 4.1. B-Tree vs LSM-Tree Storage Engines
*   **B-Tree Engines (Tối ưu cho Đọc):**
    *   *Cơ chế:* Sắp xếp dữ liệu dạng cây tự cân bằng. Dữ liệu được đọc/ghi trực tiếp vào đúng vị trí trên đĩa.
    *   *Đặc điểm:* Đọc dữ liệu cực nhanh ($O(\log N)$) nhưng Ghi chậm do phải tìm vị trí và cập nhật cây trên đĩa liên tục để tránh phân mảnh đĩa.
    *   *Tiêu biểu:* MySQL InnoDB, PostgreSQL.
*   **LSM-Tree Engines (Tối ưu cho Ghi):**
    *   *Cơ chế:* Mọi thao tác Ghi mới được nạp vào bộ nhớ RAM trước (**MemTable**), ghi tuần tự vào file nhật ký. Khi RAM đầy, nó chép nguyên khối ra đĩa thành các file bất biến (**SSTables**). Định kỳ chạy ngầm quá trình gộp file (**Compaction**) để loại bỏ dữ liệu cũ/trùng lặp.
    *   *Đặc điểm:* Ghi siêu tốc vì chỉ ghi tuần tự liên tục xuống đĩa (không di chuyển đầu ghi nhiều). Đọc chậm hơn vì phải tìm trên cả RAM và nhiều file SSTables trên đĩa.
    *   *Tiêu biểu:* Apache Cassandra, ScyllaDB.

---

### 4.2. Row-Oriented vs Column-Oriented Storage
*   **Row-Oriented (Lưu trữ theo dòng - OLTP):**
    *   *Cơ chế:* Toàn bộ các trường của một dòng dữ liệu được đặt liền kề nhau trên đĩa.
    *   *Tình huống dùng:* Thích hợp cho các giao dịch trực tuyến hàng ngày cần đọc/ghi nhanh thông tin chi tiết của một thực thể (ví dụ: lấy thông tin cá nhân của User X).
*   **Column-Oriented (Lưu trữ theo cột - OLAP):**
    *   *Cơ chế:* Tất cả các giá trị của một cột được xếp liền kề nhau trên đĩa.
    *   *Tình huống dùng:* Thích hợp cho việc phân tích dữ liệu lớn. Nếu bạn muốn tính "Tổng doanh thu bán hàng cả năm", database chỉ cần đọc đúng cột `doanh_thu` trên đĩa mà không cần phí công đọc tất cả các cột khác như tên khách hàng, địa chỉ, ngày tạo... giúp tốc độ quét tăng lên hàng trăm lần.

---

### 4.3. Buffer Pool & Write-Ahead Log (WAL)
*   **Write-Ahead Log (WAL):** Khi có giao dịch ghi, database không chép thẳng dữ liệu vào file dữ liệu chính (Random Write rất chậm), mà chép tuần tự (Sequential Write) một dòng log mô tả hành động ghi đó vào cuối file nhật ký WAL trên đĩa. Ghi WAL cực nhanh và đảm bảo tính bền vững (Durability).
*   **Buffer Pool:** Dữ liệu sau đó được cập nhật trên RAM (Buffer Pool) tạo thành các trang dữ liệu bẩn (Dirty Pages).
*   **Checkpointing:** Định kỳ, database sẽ chạy ngầm tiến trình gom các Dirty Pages trên RAM này và đồng bộ chép đè xuống file dữ liệu chính trên đĩa cứng để giải phóng bộ nhớ RAM.
