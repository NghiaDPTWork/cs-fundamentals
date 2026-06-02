# CHU TRÌNH PHÁT TRIỂN PHẦN MỀM NÂNG CAO (SDLC ADVANCED)

Tài liệu này đi sâu vào các giai đoạn chi tiết của SDLC, phân tích các mô hình phát triển phần mềm phổ biến (Waterfall, Iterative, Agile, Scrum, XP) và vai trò của các công cụ quản lý mã nguồn (SCM).

---

## 1. PHÂN TÍCH CHI TIẾT CÁC PHA TRONG SDLC

Để xây dựng một sản phẩm phần mềm thành công, giai đoạn chuẩn bị ban đầu đóng vai trò quyết định. Quy trình chi tiết bao gồm:

```
[ Planning & Problem Definition ] ──> [ Defining Scope ] ──> [ Design ] ──> [ Build ] ──> [ Test ] ──> [ Deploy ]
```

### Giai đoạn Xác định Vấn đề & Thiết lập Phạm vi (Planning -> Define Problem + Set Scope)
*   **Xác định vấn đề (Define Problem):** Tìm hiểu xem người dùng thực sự đang gặp khó khăn gì, sản phẩm giải quyết bài toán gì cho thị trường.
*   **Thiết lập phạm vi (Set Scope):** Xác định các tính năng bắt buộc phải có trong phiên bản hiện tại (MVP - Minimum Viable Product) và các tính năng sẽ phát triển sau để tránh tình trạng phình to phạm vi (Scope Creep).

### Tại sao giai đoạn này đặc biệt quan trọng đối với Công ty Product?
*   **Đối với Công ty Outsource (OC):** Khách hàng là người đưa ra bài toán và chịu trách nhiệm về mặt ý tưởng kinh doanh. Công ty Outsource chỉ cần tập trung làm đúng theo phạm vi (Scope) đã chốt trong hợp đồng để bàn giao. Rủi ro về mặt thị trường (sản phẩm không ai dùng) do khách hàng gánh chịu.
*   **Đối với Công ty Product:** Công ty tự bỏ vốn đầu tư và tự chịu trách nhiệm về định hướng sản phẩm. Nếu định nghĩa sai vấn đề của người dùng hoặc thiết lập sai phạm vi, sản phẩm làm ra sẽ **không có thị trường (No Product-Market Fit)**, dẫn đến việc lãng phí toàn bộ chi phí phát triển và có nguy cơ phá sản. Do đó, giai đoạn Planning và Scope Definition ở công ty Product mang tính sinh tử và đòi hỏi sự nghiên cứu số liệu cực kỳ nghiêm ngặt.

---

## 2. CÁC MÔ HÌNH PHÁT TRIỂN PHẦN MỀM (DEVELOPMENT MODELS)

Tùy thuộc vào quy mô dự án và mức độ biến động của yêu cầu, các đội ngũ phát triển lựa chọn các mô hình khác nhau:

### 2.1. Mô hình Thác nước (Waterfall Model)
*   **Cơ chế hoạt động:** Phát triển phần mềm theo một dòng chảy tuyến tính nghiêm ngặt từ trên xuống dưới. Giai đoạn trước phải hoàn thành 100% và được phê duyệt thì mới được phép chuyển sang giai đoạn sau. Không có đường quay lui.
*   **Ưu điểm:** Cấu trúc rõ ràng, dễ quản lý tiến độ, tài liệu đặc tả cực kỳ chi tiết.
*   **Nhược điểm:** Cực kỳ cứng nhắc. Rất khó và tốn kém nếu muốn thay đổi yêu cầu giữa chừng. Người dùng chỉ nhìn thấy sản phẩm thực tế ở giai đoạn cuối cùng, dễ dẫn đến việc sản phẩm làm ra không đúng kỳ vọng của họ.
*   **Tình huống áp dụng:** Dự án có yêu cầu cực kỳ rõ ràng, cố định ngay từ đầu và không bao giờ thay đổi (ví dụ: phần mềm hàng không, thiết bị y tế, dự án chính phủ).

---

### 2.2. Mô hình Lặp (Iterative Model)
*   **Cơ chế hoạt động:** Thay vì làm toàn bộ hệ thống từ đầu, dự án được chia nhỏ thành các phân đoạn. Mỗi phân đoạn (Iteration) sẽ thực hiện đầy đủ các bước (Design -> Build -> Test -> Deploy) cho một nhóm tính năng nhỏ. Các phiên bản tiếp theo sẽ nâng cấp dần dựa trên phiên bản trước theo dạng cuốn chiếu.
*   **Ưu điểm:** Khách hàng có sản phẩm chạy được để dùng thử từ rất sớm. Dễ dàng nhận diện lỗi kiến trúc ở các giai đoạn đầu.
*   **Nhược điểm:** Yêu cầu thiết kế kiến trúc ban đầu phải cực tốt để có khả năng tích hợp và mở rộng các phần lặp sau này.

---

### 2.3. Khung tư duy Agile & Khung làm việc Scrum

Nhiều người thường nhầm lẫn giữa hai khái niệm này. Thực chất chúng nằm ở hai cấp độ khác nhau:

```
[ AGILE ] -> Khung tư duy / Triết lý (Mindset)
   │
   └─── [ SCRUM ] -> Khung làm việc thực thi cụ thể (Framework)
```

*   **Agile (Khung tư duy - Mindset):**
    *   *Triết lý cốt lõi:* Chia nhỏ bài toán phức tạp để xử lý dần dần (Break down / Divide and Conquer). Tập trung vào sự linh hoạt, thích ứng nhanh với thay đổi, phản hồi của người dùng và sự cộng tác con người hơn là quy trình cứng nhắc.
*   **Scrum (Khung làm việc thực thi - Framework):**
    *   *Triết lý cốt lõi:* Là phần "da thịt" đắp lên khung xương Agile để áp dụng vào thực tế. Scrum chia chu kỳ phát triển thành các khoảng thời gian cố định ngắn gọi là **Sprints** (thường kéo dài từ 1 đến 4 tuần).
    *   *Cơ chế vận hành:* Mỗi Sprint bắt đầu bằng *Sprint Planning* (Lập kế hoạch), họp hàng ngày *Daily Scrum* (15 phút), cuối Sprint có *Sprint Review* (Demo sản phẩm) và *Retrospective* (Họp cải tiến quy trình).

---

### 2.4. Mô hình Lập trình cực hạn (Extreme Programming - XP)
*   **Cơ chế hoạt động:** Là một mô hình Agile tập trung sâu sắc vào các thực hành kỹ thuật tốt nhất (Engineering Practices) để nâng cao chất lượng phần mềm.
*   **Kỹ thuật Pair Programming (Lập trình cặp):**
    *   *Cơ chế:* Hai lập trình viên cùng ngồi làm việc trên một máy tính. Một người viết code (Driver), người còn lại ngồi nhìn, phân tích logic, kiểm tra lỗi cú pháp và định hướng chiến lược (Navigator). Hai người liên tục đổi vai trò cho nhau.
    *   *Lợi ích thực tế:* Cực kỳ hiệu quả trong làm việc nhóm. Nó giúp giảm thiểu lỗi lập trình ngay từ khi viết, chia sẻ kiến thức nghiệp vụ giữa các thành viên ngay lập tức và tăng tốc độ giải quyết các bài toán hóc búa.

---

## 3. CÔNG CỤ QUẢN LÝ MÃ NGUỒN (SOURCE CONTROL MANAGEMENT - SCM)

Công cụ quản lý mã nguồn là nền tảng bắt buộc để kiểm soát phiên bản và cộng tác code trong mọi chu trình SDLC:

*   **Git (Distributed SCM - Phân tán):**
    *   Mỗi lập trình viên có một bản sao lưu lịch sử đầy đủ dưới máy cục bộ. Tạo nhánh (branch) và merge cực nhanh. Là công cụ chuẩn công nghiệp hiện đại nhất ngày nay.
*   **SVN - Subversion (Centralized SCM - Tập trung):**
    *   Chỉ có một máy chủ trung tâm lưu lịch sử. Lập trình viên phải kết nối mạng để check-out và commit code. Tốc độ chậm và dễ bị gián đoạn nếu server sập.
*   **Mercurial (Distributed SCM - Phân tán):**
    *   Tương tự như Git về cơ chế phân tán nhưng cú pháp lệnh đơn giản và trực quan hơn. Tuy nhiên, mức độ phổ biến kém hơn Git rất nhiều.
