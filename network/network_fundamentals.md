# CẨM NANG VẤN ĐÁP MẠNG CĂN BẢN (INTERVIEW-READY NETWORK FUNDAMENTALS)

Tài liệu này tổng hợp toàn bộ các kiến thức mạng máy tính nền tảng được tinh chỉnh chuyên biệt cho việc ôn luyện phỏng vấn kỹ thuật. Mỗi phần chỉ tập trung vào câu trả lời trực diện, từ khóa chuyên môn cốt lõi, bảng so sánh thiết bị vật lý và môi trường truyền dẫn.

---

## 📖 1. ĐỊNH NGHĨA & PHÂN LOẠI MẠNG MÁY TÍNH

*   **Định nghĩa:** Mạng máy tính là một hệ thống gồm hai hoặc nhiều thiết bị độc lập được kết nối với nhau thông qua phương tiện truyền dẫn vật lý hoặc không dây để giao tiếp, trao đổi dữ liệu và chia sẻ tài nguyên.

### Bảng phân loại mạng theo quy mô địa lý

| Loại mạng | Định nghĩa phỏng vấn | Phạm vi địa lý | Đặc trưng hiệu năng | Ví dụ thực tế |
| :--- | :--- | :--- | :--- | :--- |
| **PAN** | Mạng kết nối cá nhân xung quanh một người. | Cực nhỏ (< 10 mét) | Tiêu thụ ít điện năng, thiết lập tức thời. | Tai nghe Bluetooth kết nối điện thoại, chuột không dây. |
| **LAN** | Mạng cục bộ trong phạm vi văn phòng, gia đình. | Nhỏ (< 2 km) | Băng thông cực cao (1-10+ Gbps), độ trễ cực thấp (1-5 ms). | Kết nối Wi-Fi hoặc mạng dây cắm giữa các máy tính trong văn phòng. |
| **MAN** | Mạng kết nối đô thị giữa nhiều mạng LAN. | Trung bình (10-100 km) | Thuộc sở hữu viễn thông hoặc liên minh doanh nghiệp. | Mạng camera giao thông thành phố, mạng nội bộ ngân hàng cùng thành phố. |
| **WAN** | Mạng diện rộng liên quốc gia, toàn cầu. | Rất lớn (Toàn cầu) | Băng thông biến động lớn, độ trễ cao (30-300+ ms). Internet là ví dụ lớn nhất. | Kết nối các văn phòng chi nhánh toàn cầu của một tập đoàn qua cáp quang biển. |

---

## 🏗️ 2. MÔ HÌNH PHÂN TẦNG (NETWORK MODELS)

*   **So sánh:** Mô hình **OSI (7 tầng)** là mô hình lý thuyết tham chiếu phục vụ giảng dạy và chuẩn hóa thiết kế mạng; còn mô hình **TCP/IP (5 hoặc 4 tầng)** là mô hình thực tế thương mại, là nền tảng vận hành toàn bộ mạng Internet hiện đại ngày nay.

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
    *   **Định nghĩa:** Thiết bị kết nối mạng LAN đời cũ. Hoạt động bằng cách sao chép và phát tán (**Broadcast**) mù quáng mọi tín hiệu điện nhận được ra tất cả các cổng còn lại.
    *   *Từ khóa:* Collision Domain (xung đột dữ liệu lớn), không bảo mật, hiệu năng kém.
*   **Repeater (Bộ lặp):**
    *   **Định nghĩa:** Thiết bị thu nhận tín hiệu vật lý bị suy hao do truyền khoảng cách xa, lọc nhiễu, khuếch đại mạnh lên và truyền tiếp để kéo dài khoảng cách đường truyền.

### 3.2. Thiết bị Tầng Liên kết dữ liệu (Layer 2)
*   **Switch (Bộ chuyển mạch):**
    *   **Định nghĩa:** Thiết bị kết nối các máy tính trong mạng LAN hiện đại. Hoạt động bằng cách học địa chỉ vật lý (**MAC Address**) của các thiết bị cắm vào cổng để lưu vào bảng MAC, sau đó chuyển tiếp chính xác (**Unicast**) gói tin đến đúng cổng của thiết bị nhận.
    *   *Từ khóa:* Tránh xung đột dữ liệu, bảo mật, hoạt động ở Layer 2.
*   **NIC (Network Interface Card - Card mạng):**
    *   **Định nghĩa:** Bo mạch phần cứng chuyển đổi dữ liệu nhị phân của máy tính thành tín hiệu vật lý để truyền qua dây cáp/Wi-Fi và ngược lại. Mỗi card mạng chứa một địa chỉ MAC duy nhất toàn cầu do nhà sản xuất nạp sẵn.

### 3.3. Thiết bị Tầng Mạng (Layer 3) và Tầng Cao
*   **Router (Bộ định tuyến):**
    *   **Định nghĩa:** Thiết bị kết nối các mạng khác nhau (như mạng LAN gia đình với mạng Internet). Router đọc địa chỉ logic (**IP Address**) đích của gói tin, tra cứu bảng định tuyến để chuyển tiếp gói tin qua đường đi tối ưu nhất.
    *   *Từ khóa:* Hoạt động ở Layer 3, định tuyến gói tin (Routing), kết nối các mạng khác nhau.
*   **Gateway (Cổng chuyển đổi):**
    *   **Định nghĩa:** Thiết bị hoặc phần mềm làm cầu nối kết nối hai hệ thống mạng sử dụng các bộ giao thức khác nhau hoàn toàn, đóng vai trò là "thông dịch viên" chuyển đổi định dạng gói tin giữa hai bên.

---

## 📡 4. MÔI TRƯỜNG TRUYỀN DẪN (TRANSMISSION MEDIA)

Dữ liệu được chuyển đổi thành các dạng năng lượng vật lý để di chuyển dọc theo môi trường truyền dẫn.

### Bảng phân biệt các môi trường truyền dẫn chính

| Môi trường | Bản chất vật lý | Vai trò | Ý nghĩa | Chức năng | Tình huống NÊN dùng | Tình huống TRÁNH dùng |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Ánh sáng** *(Cáp quang)* | Dẫn tia sáng laser đi trong sợi thủy tinh bằng hiện tượng phản xạ toàn phần. | Xương sống của mạng Internet toàn cầu (cáp quang biển, trục kết nối). | Băng thông cực lớn (Gbps-Tbps), không bị nhiễu điện từ. | Truyền tải dữ liệu đi cực xa với tốc độ ánh sáng và độ suy hao tín hiệu thấp nhất. | Kết nối giữa các quốc gia, các trung tâm dữ liệu (Datacenter). | Kết nối di động hoặc các vị trí uốn cong góc hẹp (sợi thủy tinh giòn, dễ gãy). |
| **Sóng vô tuyến** *(Không dây)* | Lan tỏa sóng điện từ trong không gian tự do. | Kết nối các thiết bị di động linh hoạt (Wifi, 4G, 5G, Bluetooth). | Giúp thiết bị kết nối mạng mọi lúc mọi nơi không cần dây cáp vật lý. | Phát tín hiệu sóng mang dữ liệu ra môi trường xung quanh để thiết bị thu nhận. | Thiết bị di động, IoT cầm tay, các địa hình phức tạp không thể đi dây cáp mạng. | Ứng dụng yêu cầu độ tin cậy tuyệt đối, độ trễ cực thấp (giao dịch tài chính tốc độ cao, phẫu thuật từ xa). |
| **Cáp đồng** *(Cáp mạng đồng)* | Truyền dòng điện mang điện áp cao/thấp dọc theo lõi đồng. | Kết nối nội bộ cự ly gần (văn phòng, gia đình). | Chi phí rẻ, dễ lắp đặt và uốn cong, tương thích với hầu hết thiết bị văn phòng. | Chuyển đổi dữ liệu nhị phân thành các xung điện để truyền dẫn cự ly ngắn. | Nối mạng nội bộ LAN từ Switch tới máy tính bàn (PC), máy in, camera giám sát. | Khoảng cách kết nối xa vượt quá 100 mét (suy hao tín hiệu mạnh) hoặc khu vực nhiễu điện từ nặng. |

---

## 🌐 5. PHÂN TÍCH CHI TIẾT CÁC GIAO THỨC CỐT LÕI (CORE PROTOCOLS)

### 5.1. Các Giao thức Tầng Ứng dụng (Application Layer)

#### A. HTTP (Hypertext Transfer Protocol)
*   **Định nghĩa:** Giao thức truyền tải siêu văn bản (dữ liệu web như HTML, ảnh, JSON) giữa Client và Server hoạt động theo mô hình Request-Response.
*   **Cơ chế hoạt động:** Chạy trên giao thức TCP qua cổng (Port) 80. Là giao thức **Không trạng thái (Stateless)** - mỗi yêu cầu độc lập, server không nhớ lịch sử yêu cầu trước (cần dùng Cookie/Session/Token để duy trì trạng thái đăng nhập).
*   **Từ khóa cốt lõi:** Port 80, Stateless, Request/Response, TCP-based.

#### B. HTTPS (HTTP Secure)
*   **Định nghĩa:** Phiên bản bảo mật của HTTP, trong đó dữ liệu được mã hóa trước khi truyền qua mạng bằng giao thức **TLS/SSL**.
*   **Cơ chế hoạt động:** Chạy trên TCP qua cổng 443. Trước khi truyền dữ liệu HTTP, Client và Server thực hiện quá trình bắt tay bảo mật (TLS Handshake) để xác thực chứng chỉ số và thỏa thuận khóa đối xứng dùng để mã hóa thông tin.
*   **Từ khóa cốt lõi:** Port 443, Mã hóa TLS/SSL, Bảo mật dữ liệu, CA Certificate.

#### C. DNS (Domain Name System)
*   **Định nghĩa:** Hệ thống phân giải tên miền giúp chuyển đổi các tên miền dễ nhớ (như `google.com`) thành địa chỉ IP vật lý của máy chủ để máy tính kết nối.
*   **Cơ chế hoạt động:** Chạy chủ yếu trên giao thức UDP qua cổng 53 để lấy tốc độ phân giải nhanh nhất. Nếu phản hồi quá lớn hoặc cần đồng bộ cơ sở dữ liệu tên miền (Zone Transfer), DNS mới chuyển sang sử dụng TCP.
*   **Từ khóa cốt lõi:** Port 53, Phân giải IP, UDP-based (phổ biến) / TCP-based, Cây phân cấp tên miền.

#### D. FTP (File Transfer Protocol)
*   **Định nghĩa:** Giao thức truyền nhận tệp tin giữa Client và Server trên mạng Internet.
*   **Cơ chế hoạt động:** Sử dụng hai kết nối TCP riêng biệt:
    *   **Port 21 (Connection Control):** Dùng để truyền lệnh điều khiển (như đăng nhập, đổi thư mục).
    *   **Port 20 (Data Connection):** Dùng để truyền dữ liệu tệp tin thực tế.
*   **Từ khóa cốt lõi:** Port 20 & 21, Tách biệt luồng điều khiển và luồng dữ liệu, TCP-based.

#### E. SMTP (Simple Mail Transfer Protocol)
*   **Định nghĩa:** Giao thức tiêu chuẩn dùng để gửi thư điện tử (email) từ Client tới Mail Server hoặc giữa các Mail Server với nhau.
*   **Cơ chế hoạt động:** Chạy trên giao thức TCP qua cổng 25 (hoặc cổng bảo mật 587/465). Lưu ý: SMTP chỉ dùng để **Gửi đi**, để nhận thư về máy khách cần dùng giao thức **POP3** hoặc **IMAP**.
*   **Từ khóa cốt lõi:** Port 25 / 587, Gửi thư đi, TCP-based.

---

### 5.2. Các Giao thức Tầng Giao vận (Transport Layer)

#### A. TCP (Transmission Control Protocol)
*   **Định nghĩa:** Giao thức giao vận **hướng kết nối (Connection-oriented)**, cam kết đảm bảo truyền dữ liệu tin cậy 100%, đúng thứ tự và kiểm soát tắc nghẽn luồng truyền tải.
*   **Cơ chế hoạt động:** Bắt buộc phải thực hiện bắt tay 3 bước (SYN $\rightarrow$ SYN-ACK $\rightarrow$ ACK) để thiết lập kết nối trước khi truyền tin. Nó chia dữ liệu thành các Segment, đánh số thứ tự, kiểm tra tính toàn vẹn và bắt buộc bên nhận gửi lại phản hồi ACK. Nếu mất gói, nó tự động gửi lại.
*   **Từ khóa cốt lõi:** Connection-oriented, Bắt tay 3 bước, ACK, Tin cậy 100%, Kiểm soát luồng (Flow Control) & Tắc nghẽn (Congestion Control).

#### B. UDP (User Datagram Protocol)
*   **Định nghĩa:** Giao thức giao vận **phi kết nối (Connectionless)**, ưu tiên tối đa tốc độ truyền dữ liệu nhanh nhất mà không đảm bảo độ tin cậy.
*   **Cơ chế hoạt động:** Không cần bắt tay thiết lập kết nối trước. Dữ liệu được đóng gói thành các Datagram và bắn liên tục đi. UDP không có cơ chế xác nhận gói tin, không tự gửi lại khi mất gói và không quản lý thứ tự gói tin đến.
*   **Từ khóa cốt lõi:** Connectionless, Không tin cậy (Unreliable), Tốc độ tối đa, Không kiểm soát tắc nghẽn. Phù hợp cho Livestream, Voice IP, Game online.

---

### 5.3. Các Giao thức Tầng Mạng & Tầng Liên kết dữ liệu (Network & Data Link Layers)

#### A. IP (Internet Protocol - IPv4/IPv6)
*   **Định nghĩa:** Giao thức cốt lõi tầng Network, chịu trách nhiệm định nghĩa địa chỉ IP logical và đóng gói dữ liệu thành các Packet để định tuyến đi xuyên các mạng khác nhau.
*   **Cơ chế hoạt động:** 
    *   **IPv4:** Sử dụng địa chỉ 32-bit (khoảng 4.3 tỷ địa chỉ), viết dưới dạng 4 nhóm số thập phân ngăn cách bởi dấu chấm (ví dụ: `192.168.1.1`).
    *   **IPv6:** Sử dụng địa chỉ 128-bit (gần như vô hạn địa chỉ), viết dưới dạng 8 nhóm số hexa ngăn cách bởi dấu hai chấm để giải quyết triệt để vấn đề cạn kiệt địa chỉ IPv4.
*   **Từ khóa cốt lõi:** IP Address logical, Định tuyến (Routing), IPv4 32-bit, IPv6 128-bit.

#### B. ICMP (Internet Control Message Protocol)
*   **Định nghĩa:** Giao thức dùng để truyền tải các thông điệp chẩn đoán lỗi mạng và điều khiển thông tin giữa các thiết bị mạng.
*   **Cơ chế hoạt động:** Không dùng để truyền dữ liệu ứng dụng. Khi đường truyền lỗi hoặc router bị quá tải, router gửi thông điệp ICMP về thiết bị nguồn. Lệnh `ping` (kiểm tra kết nối) và `traceroute` (dò đường đi) hoạt động hoàn toàn dựa trên ICMP.
*   **Từ khóa cốt lõi:** Chẩn đoán lỗi mạng, lệnh Ping / Traceroute, Không truyền dữ liệu người dùng.

#### C. ARP (Address Resolution Protocol)
*   **Định nghĩa:** Giao thức dịch chuyển địa chỉ logic (IP Address) thành địa chỉ vật lý (MAC Address) của thiết bị trong cùng mạng nội bộ LAN.
*   **Cơ chế hoạt động:** Khi máy tính muốn gửi dữ liệu đến IP `192.168.1.5` nhưng chưa biết địa chỉ MAC của máy đó, nó gửi một yêu cầu **ARP Request Broadcast** ra toàn mạng hỏi: *"Ai có IP này, hãy trả lời địa chỉ MAC cho tôi"*. Thiết bị có IP tương ứng sẽ trả lời bằng **ARP Reply Unicast** chứa địa chỉ MAC của nó. Máy gửi lưu thông tin này vào bảng bộ nhớ đệm `ARP Cache` để dùng lại.
*   **Từ khóa cốt lõi:** Dịch IP sang MAC, ARP Request Broadcast, ARP Reply Unicast, ARP Cache.


