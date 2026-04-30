# INTERNET FUNDAMENTALS - THE BIG PICTURE

## 📝 Nội dung chi tiết (Phần 1)

### 1. Bản chất & Sự ra đời

- **Định nghĩa:** Internet là mạng lưới chung toàn cầu, nơi các thiết bị giao tiếp với nhau bằng các quy tắc chung gọi là **Protocol (Giao thức)**.
- **Lịch sử:** Xuất phát từ dự án **ARPANET** (DARPA - Mỹ) những năm 1960.
- **Tầm nhìn:** Xây dựng một hệ thống có tính "nguyên tử" và độc lập, tránh sự sụp đổ dây chuyền (domino) nếu một bộ phận bị phá hủy.

### 2. Tầm quan trọng đối với Lập trình viên

- Hầu hết các sản phẩm công nghệ hiện nay đều vận hành trên nền tảng Internet.
- Việc hiểu rõ cấu trúc mạng giúp lập trình viên:
  - **Tối ưu hóa:** Cải thiện tốc độ tải và hiệu năng.
  - **Debug:** Dễ dàng tìm ra lỗi nằm ở tầng nào (Client, Network, hay Server).
  - **Bản chất:** Nắm rõ cách dữ liệu di chuyển để thiết kế hệ thống tốt hơn.

### 3. Phân loại Mạng theo quy mô địa lý

| Loại mạng | Tên đầy đủ                | Phạm vi           | Đặc điểm                                                            |
| :-------- | :------------------------ | :---------------- | :------------------------------------------------------------------ |
| **PAN**   | Personal Area Network     | Vài mét           | Kết nối thiết bị cá nhân (Bluetooth).                               |
| **LAN**   | Local Area Network        | Nhà, văn phòng    | Tốc độ cao, độ trễ thấp. (Gồm cả WLAN/Wi-Fi).                       |
| **MAN**   | Metropolitan Area Network | Thành phố         | Kết nối nhiều mạng LAN lại với nhau.                                |
| **WAN**   | Wide Area Network         | Quốc gia, Lục địa | Độ trễ cao do khoảng cách. **Internet chính là mạng WAN lớn nhất.** |

![Network Classification](images/image.png)

---

## PHẦN 2: CÁC MÔ HÌNH MẠNG (NETWORKING MODELS)

### ⏳ Dòng thời gian & Sự tiến hóa
Mặc dù có nhiều mô hình ra đời, nhưng **TCP/IP** (thực tế) và **OSI** (lý thuyết) là hai trụ cột quan trọng nhất mà lập trình viên cần nắm vững.

| Thời gian | Mô hình | Trạng thái hiện tại |
| :--- | :--- | :--- |
| **1970s** | **DoD / TCP/IP** | **Tiêu chuẩn thực tế toàn cầu** |
| **1974** | **SNA (IBM)** | Chỉ còn dùng trong hệ thống Mainframe cũ |
| **1980s** | **IPX/SPX, AppleTalk, NetBIOS** | Đã bị khai tử hoặc thay thế bởi TCP/IP |
| **1984** | **OSI Model** | **Chuẩn tham chiếu lý thuyết (Reference)** |

---

### 🏆 Các mô hình chủ chốt (Core Models)

#### 1. Mô hình TCP/IP (Mô hình thực dụng)
*   **Nguồn gốc:** Phát triển bởi DARPA (Bộ Quốc phòng Mỹ) từ những năm 1970.
*   **Đặc điểm:** Là "ngôn ngữ" thực sự của Internet ngày nay. Nó gộp các tầng lại để tối ưu hóa việc truyền tải dữ liệu thực tế.
*   **Cấu trúc (4 Tầng):**
    1.  **Application** (Ứng dụng)
    2.  **Transport** (Giao vận)
    3.  **Internet** (Mạng)
    4.  **Network Access** (Truy cập mạng)

#### 2. Mô hình OSI (Mô hình tham chiếu)
*   **Nguồn gốc:** Do ISO phát triển năm 1984.
*   **Đặc điểm:** Dùng để giảng dạy và hiểu sâu về cách dữ liệu đi qua từng bước nhỏ. Giúp việc phân tách nhiệm vụ (Separation of Concerns) trở nên cực kỳ rõ ràng.
*   **Cấu trúc (7 Tầng):**
    *   *Top:* **Application → Presentation → Session**
    *   *Mid:* **Transport**
    *   *Bottom:* **Network → Data Link → Physical**

---

### 📜 Các mô hình khác (Lịch sử & Đặc thù)
*Để hiểu bối cảnh, bạn chỉ cần nắm sơ lược các mô hình này:*

*   **DoD Model:** Chính là "ông tổ" của TCP/IP. Một số tài liệu cũ vẫn dùng tên này.
*   **SNA (IBM - 1974):** Thiết kế riêng cho các máy chủ Mainframe khổng lồ của IBM.
*   **IPX/SPX (Novell):** Từng rất mạnh trong các văn phòng thập niên 80-90 nhưng đã thua cuộc trước sự bùng nổ của TCP/IP.
*   **AppleTalk (Apple):** Hệ sinh thái đóng của Apple ngày xưa, đã bị khai tử hoàn toàn từ năm 2009.
*   **NetBIOS / NetBEUI:** Đơn giản, dùng cho mạng nội bộ nhỏ, không có khả năng định tuyến đi xa.
