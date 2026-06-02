# CẨM NANG VẤN ĐÁP MẠNG CĂN BẢN (INTERVIEW-READY NETWORK FUNDAMENTALS)

Tài liệu này tổng hợp toàn bộ các kiến thức mạng máy tính nền tảng được tinh chỉnh chuyên biệt cho việc ôn luyện phỏng vấn kỹ thuật. Mỗi phần chỉ tập trung vào câu trả lời trực diện, từ khóa chuyên môn cốt lõi, bảng so sánh thiết bị vật lý và môi trường truyền dẫn.

---

## 📖 1. ĐỊNH NGHĨA & PHÂN LOẠI MẠNG MÁY TÍNH

*   **Trả lời phỏng vấn (1 câu):** Mạng máy tính là một hệ thống gồm hai hoặc nhiều thiết bị độc lập được kết nối với nhau thông qua phương tiện truyền dẫn vật lý hoặc không dây để giao tiếp, trao đổi dữ liệu và chia sẻ tài nguyên.

### Bảng phân loại mạng theo quy mô địa lý

| Loại mạng | Định nghĩa phỏng vấn | Phạm vi địa lý | Đặc trưng hiệu năng | Ví dụ thực tế |
| :--- | :--- | :--- | :--- | :--- |
| **PAN** | Mạng kết nối cá nhân xung quanh một người. | Cực nhỏ (< 10 mét) | Tiêu thụ ít điện năng, thiết lập tức thời. | Tai nghe Bluetooth kết nối điện thoại, chuột không dây. |
| **LAN** | Mạng cục bộ trong phạm vi văn phòng, gia đình. | Nhỏ (< 2 km) | Băng thông cực cao (1-10+ Gbps), độ trễ cực thấp (1-5 ms). | Kết nối Wi-Fi hoặc mạng dây cắm giữa các máy tính trong văn phòng. |
| **MAN** | Mạng kết nối đô thị giữa nhiều mạng LAN. | Trung bình (10-100 km) | Thuộc sở hữu viễn thông hoặc liên minh doanh nghiệp. | Mạng camera giao thông thành phố, mạng nội bộ ngân hàng cùng thành phố. |
| **WAN** | Mạng diện rộng liên quốc gia, toàn cầu. | Rất lớn (Toàn cầu) | Băng thông biến động lớn, độ trễ cao (30-300+ ms). Internet là ví dụ lớn nhất. | Kết nối các văn phòng chi nhánh toàn cầu của một tập đoàn qua cáp quang biển. |

---

## 🏗️ 2. MÔ HÌNH PHÂN TẦNG (NETWORK MODELS)

*   **Trả lời phỏng vấn (Phân biệt OSI vs TCP/IP):** Mô hình **OSI (7 tầng)** là mô hình lý thuyết tham chiếu phục vụ giảng dạy và chuẩn hóa thiết kế mạng; còn mô hình **TCP/IP (5 hoặc 4 tầng)** là mô hình thực tế thương mại, là nền tảng vận hành toàn bộ mạng Internet hiện đại ngày nay.

### Bảng đối chiếu các tầng và giao thức tiêu biểu

| Tầng OSI | Tên tầng OSI | Mô hình TCP/IP | Chức năng cốt lõi | Giao thức / Thiết bị tiêu biểu |
| :---: | :--- | :---: | :--- | :--- |
| **7, 6, 5** | Application, Presentation, Session | **Application** | Tiếp nhận dữ liệu, định dạng mã hóa, mã hóa bảo mật (TLS/SSL) và quản lý phiên. | HTTP, HTTPS, DNS, SMTP, FTP, TLS/SSL, Sockets |
| **4** | Transport (Giao vận) | **Transport** | Truyền tải dữ liệu đầu-cuối (end-to-end), kiểm soát lỗi, chia nhỏ gói tin và quản lý cổng Port. | TCP, UDP |
| **3** | Network (Mạng) | **Network** | Định tuyến (routing) gói tin dựa trên địa chỉ IP đích xuyên mạng. | IP (IPv4/IPv6), ICMP. Thiết bị: **Router** |
| **2** | Data Link (Liên kết dữ liệu) | **Data Link** | Đóng gói Frame vật lý, kiểm soát lỗi đường truyền nội bộ LAN bằng địa chỉ MAC vật lý. | Ethernet, Wi-Fi, ARP. Thiết bị: **Switch, NIC** |
| **1** | Physical (Vật lý) | **Physical** | Chuyển đổi dữ liệu nhị phân thành tín hiệu điện, ánh sáng, sóng vô tuyến để truyền qua dây cáp/không khí. | Cáp quang, cáp đồng, sóng vô tuyến. Thiết bị: **Hub, Repeater** |

---

## 🔌 3. CHI TIẾT THIẾT BỊ MẠNG VẬT LÝ (DEVICES)

### 3.1. Thiết bị Tầng Vật lý (Layer 1)
*   **Hub (Bộ tập trung):**
    *   *Trả lời phỏng vấn:* Thiết bị kết nối mạng LAN đời cũ. Hoạt động bằng cách sao chép và phát tán (**Broadcast**) mù quáng mọi tín hiệu điện nhận được ra tất cả các cổng còn lại.
    *   *Từ khóa:* Collision Domain (xung đột dữ liệu lớn), không bảo mật, hiệu năng kém.
*   **Repeater (Bộ lặp):**
    *   *Trả lời phỏng vấn:* Thiết bị thu nhận tín hiệu vật lý bị suy hao do truyền khoảng cách xa, lọc nhiễu, khuếch đại mạnh lên và truyền tiếp để kéo dài khoảng cách đường truyền.

### 3.2. Thiết bị Tầng Liên kết dữ liệu (Layer 2)
*   **Switch (Bộ chuyển mạch):**
    *   *Trả lời phỏng vấn:* Thiết bị kết nối các máy tính trong mạng LAN hiện đại. Hoạt động bằng cách học địa chỉ vật lý (**MAC Address**) của các thiết bị cắm vào cổng để lưu vào bảng MAC, sau đó chuyển tiếp chính xác (**Unicast**) gói tin đến đúng cổng của thiết bị nhận.
    *   *Từ khóa:* Tránh xung đột dữ liệu, bảo mật, hoạt động ở Layer 2.
*   **NIC (Network Interface Card - Card mạng):**
    *   *Trả lời phỏng vấn:* Bo mạch phần cứng chuyển đổi dữ liệu nhị phân của máy tính thành tín hiệu vật lý để truyền qua dây cáp/Wi-Fi và ngược lại. Mỗi card mạng chứa một địa chỉ MAC duy nhất toàn cầu do nhà sản xuất nạp sẵn.

### 3.3. Thiết bị Tầng Mạng (Layer 3) và Tầng Cao
*   **Router (Bộ định tuyến):**
    *   *Trả lời phỏng vấn:* Thiết bị kết nối các mạng khác nhau (như mạng LAN gia đình với mạng Internet). Router đọc địa chỉ logic (**IP Address**) đích của gói tin, tra cứu bảng định tuyến để chuyển tiếp gói tin qua đường đi tối ưu nhất.
    *   *Từ khóa:* Hoạt động ở Layer 3, định tuyến gói tin (Routing), kết nối các mạng khác nhau.
*   **Gateway (Cổng chuyển đổi):**
    *   *Trả lời phỏng vấn:* Thiết bị hoặc phần mềm làm cầu nối kết nối hai hệ thống mạng sử dụng các bộ giao thức khác nhau hoàn toàn, đóng vai trò là "thông dịch viên" chuyển đổi định dạng gói tin giữa hai bên.

---

## 📡 4. MÔI TRƯỜNG TRUYỀN DẪN (TRANSMISSION MEDIA)

Dữ liệu được chuyển đổi thành các dạng năng lượng vật lý để di chuyển dọc theo môi trường truyền dẫn.

### Bảng phân biệt các môi trường truyền dẫn chính

| Môi trường | Bản chất vật lý | Vai trò | Ý nghĩa | Chức năng | Tình huống NÊN dùng | Tình huống TRÁNH dùng |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Ánh sáng** *(Cáp quang)* | Dẫn tia sáng laser đi trong sợi thủy tinh bằng hiện tượng phản xạ toàn phần. | Xương sống của mạng Internet toàn cầu (cáp quang biển, trục kết nối). | Băng thông cực lớn (Gbps-Tbps), không bị nhiễu điện từ. | Truyền tải dữ liệu đi cực xa với tốc độ ánh sáng và độ suy hao tín hiệu thấp nhất. | Kết nối giữa các quốc gia, các trung tâm dữ liệu (Datacenter). | Kết nối di động hoặc các vị trí uốn cong góc hẹp (sợi thủy tinh giòn, dễ gãy). |
| **Sóng vô tuyến** *(Không dây)* | Lan tỏa sóng điện từ trong không gian tự do. | Kết nối các thiết bị di động linh hoạt (Wifi, 4G, 5G, Bluetooth). | Giúp thiết bị kết nối mạng mọi lúc mọi nơi không cần dây cáp vật lý. | Phát tín hiệu sóng mang dữ liệu ra môi trường xung quanh để thiết bị thu nhận. | Thiết bị di động, IoT cầm tay, các địa hình phức tạp không thể đi dây cáp mạng. | Ứng dụng yêu cầu độ tin cậy tuyệt đối, độ trễ cực thấp (giao dịch tài chính tốc độ cao, phẫu thuật từ xa). |
| **Cáp đồng** *(Cáp mạng đồng)* | Truyền dòng điện mang điện áp cao/thấp dọc theo lõi đồng. | Kết nối nội bộ cự ly gần (văn phòng, gia đình). | Chi phí rẻ, dễ lắp đặt và uốn cong, tương thích với hầu hết thiết bị văn phòng. | Chuyển đổi dữ liệu nhị phân thành các xung điện để truyền dẫn cự ly ngắn. | Nối mạng nội bộ LAN từ Switch tới máy tính bàn (PC), máy in, camera giám sát. | Khoảng cách kết nối xa vượt quá 100 mét (suy hao tín hiệu mạnh) hoặc khu vực nhiễu điện từ nặng. |
