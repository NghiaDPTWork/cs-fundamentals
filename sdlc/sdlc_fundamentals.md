# CHU TRÌNH PHÁT TRIỂN PHẦN MỀM CƠ BẢN (SDLC FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức nền tảng về Chu trình phát triển phần mềm (Software Development Life Cycle - SDLC), vai trò của các vị trí trong dự án, các pha cơ bản và phân biệt các loại hình công ty công nghệ.

---

## 1. KHÁI NIỆM SDLC VÀ CÁC PHA CƠ BẢN

### 1.1. Khái niệm SDLC
*   **Định nghĩa thực tế:** SDLC (Software Development Life Cycle) là một quy trình chuẩn hóa gồm nhiều bước liên tục nhằm tạo ra một sản phẩm phần mềm đảm bảo hai yếu tố cốt lõi:
    1.  **Work (Chạy được):** Đáp ứng đúng yêu cầu của người dùng, không có lỗi nghiêm trọng.
    2.  **Maintain (Có khả năng bảo trì):** Mã nguồn sạch sẽ, dễ dàng nâng cấp, sửa lỗi và mở rộng trong tương lai mà không phải đập đi xây lại.

### 1.2. Các pha cơ bản trong SDLC (Basic Phases)
Một chu trình SDLC cơ bản thường trải qua các giai đoạn tuần tự sau:

```
[ Design (Thiết kế) ] ──> [ Build (Lập trình) ] ──> [ Test (Kiểm thử) ] ──> [ Deploy (Triển khai) ]
```

1.  **Design (Thiết kế):** Thiết kế kiến trúc hệ thống, cơ sở dữ liệu và giao diện người dùng (UI/UX).
2.  **Build (Lập trình/Xây dựng):** Lập trình viên viết code thực tế để tạo nên các tính năng.
3.  **Test (Kiểm thử):** Đội ngũ QE/Tester tiến hành kiểm tra phần mềm để phát hiện và sửa các lỗi phát sinh.
4.  **Deploy (Triển khai):** Đưa sản phẩm lên các môi trường (Staging, Production) để người dùng sử dụng.

---

## 2. VAI TRÒ CỦA CÁC VỊ TRÍ TRONG DỰ ÁN (PROJECT ROLES)

Để vận hành một chu trình phát triển phần mềm, cần có sự phối hợp của nhiều vai trò chuyên biệt:

*   **PO (Product Owner - Người làm chủ sản phẩm):**
    *   *Vai trò:* Là người chịu trách nhiệm tối cao về sự thành bại của sản phẩm. PO thực hiện nghiên cứu thị trường (research), định hình tầm nhìn (vision), định nghĩa các tính năng và quyết định định hướng sản phẩm để làm sao sản phẩm thu hút được nhiều người dùng nhất và mang lại giá trị kinh doanh cao nhất.
*   **BA (Business Analyst - Nhà phân tích nghiệp vụ):**
    *   *Vai trò:* Là cầu nối dịch thuật ý tưởng từ PO sang ngôn ngữ kỹ thuật cho Dev hiểu. BA chi tiết hóa các yêu cầu nghiệp vụ thành tài liệu đặc tả tính năng (SRS) hoặc các User Stories.
    *   *🚨 Điểm ngách phỏng vấn:* Trong mô hình Scrum chuẩn, **BA không thuộc về duy nhất một Scrum team cố định**. BA có thể hoạt động độc lập hoặc hỗ trợ nhiều team khác nhau tùy theo luồng nghiệp vụ cần phân tích.
*   **Dev (Developer - Lập trình viên):**
    *   *Vai trò:* Tiếp nhận tài liệu phân tích nghiệp vụ từ BA và PO, trực tiếp viết mã nguồn để xây dựng và hiện thực hóa hệ thống phần mềm.
*   **QE (Quality Engineer - Kỹ sư đảm bảo chất lượng):**
    *   *Vai trò:* Thiết kế các kịch bản kiểm thử (test cases), thực hiện kiểm thử thủ công (manual test) hoặc tự động (automation test) để đảm bảo chất lượng phần mềm tốt nhất trước khi bàn giao.
*   **Scrum Master - SM (Người điều phối Scrum):**
    *   *Vai trò:* Đảm bảo đội ngũ tuân thủ đúng quy trình làm việc của khung làm việc Scrum. Loại bỏ các rào cản cản trở năng suất của team và hỗ trợ PO quản lý backlog.
    *   *Lưu ý thực tế:* Quy trình này rất linh hoạt. Một số team phát triển thực tế không cần một vị trí Scrum Master chuyên trách riêng, mà vai trò này có thể được đảm nhiệm chéo bởi Tech Lead hoặc Project Manager.
*   **DevOps / Platform Engineer:**
    *   *Vai trò:* Đảm nhiệm việc cấu hình hạ tầng mạng, máy chủ và tự động hóa quy trình triển khai phần mềm (CI/CD) để đẩy mã nguồn từ môi trường phát triển lên môi trường chạy thực tế (Production).
*   **Stakeholder (Bên liên quan):**
    *   *Vai trò:* Là những người có lợi ích liên quan đến dự án nhưng không trực tiếp tham gia build sản phẩm hàng ngày (ví dụ: khách hàng, ban giám đốc, phòng Sales, phòng Marketing). Họ cung cấp feedback và phê duyệt sản phẩm ở các giai đoạn cột mốc.

---

## 3. PHÂN BIỆT LOẠI HÌNH CÔNG TY CÔNG NGHỆ

Môi trường phát triển và yêu cầu kỹ năng của lập trình viên bị ảnh hưởng rất lớn bởi loại hình công ty:

| Tiêu chí | Công ty Outsource (Outsourcing Company) | Công ty Product (Product Company) |
| :--- | :--- | :--- |
| **Bản chất hoạt động** | Nhận các đơn đặt hàng xây dựng phần mềm từ các đối tác/khách hàng bên ngoài. | Tự nghiên cứu, lên ý tưởng, xây dựng và phát triển sản phẩm của chính mình. |
| **Đối tượng sử dụng** | Khách hàng ký hợp đồng (Client). | Người dùng cuối cùng trên thị trường (End-users). |
| **Yêu cầu đối với BA** | **Cực kỳ cao.** BA phải làm việc với nhiều khách hàng từ nhiều lĩnh vực khác nhau, cần khả năng khơi gợi yêu cầu (requirement elicitation) và đàm phán cực tốt để chốt phạm vi dự án (scope), tránh tình trạng khách hàng liên tục thay đổi yêu cầu làm bể tiến độ. | Tập trung sâu vào hành vi người dùng, phối hợp chặt chẽ với PO để thử nghiệm các giả thuyết tính năng mới dựa trên số liệu thực tế (Analytics). |
| **Ưu điểm môi trường** | Được tiếp xúc với nhiều dự án khác nhau, học hỏi nhiều công nghệ mới trong thời gian ngắn. | Hiểu sâu sắc về vòng đời của một sản phẩm, tập trung vào chất lượng code và khả năng bảo trì lâu dài. |
| **Nhược điểm** | Ít khi được tối ưu sâu mã nguồn vì dự án thường phải bàn giao nhanh để chạy dự án khác. | Có thể bị nhàm chán nếu sản phẩm đi vào giai đoạn bảo trì dài hạn mà không có tính năng mới. |
