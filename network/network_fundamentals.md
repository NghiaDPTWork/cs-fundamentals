# MẠNG MÁY TÍNH CĂN BẢN (NETWORK FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức mạng máy tính nền tảng, tập trung vào định nghĩa, phân loại mạng, mô hình phân tầng (OSI và TCP/IP), chi tiết các thiết bị mạng vật lý và môi trường truyền dẫn. Nội dung trình bày trực quan, tránh các thuật ngữ hàn lâm khó hiểu.

---

## 📖 1. ĐỊNH NGHĨA & PHÂN LOẠI MẠNG MÁY TÍNH

### 1.1. Định nghĩa (Cách hiểu bình dân)
Hãy tưởng tượng một người sống cô độc trên đảo hoang sẽ không thể trò chuyện hay trao đổi hàng hóa với ai. Khi có thêm người thứ hai, thứ ba xuất hiện và họ xây dựng đường sá, cầu cống để đi lại giao lưu, họ đã tạo nên một mạng lưới xã hội.

**Mạng máy tính (Computer Network)** cũng vậy. Nó là hệ thống gồm hai hoặc nhiều thiết bị độc lập (máy tính, điện thoại, máy in, camera...) kết nối với nhau thông qua cáp vật lý hoặc sóng không dây. Mục đích tối thượng là để các thiết bị **giao tiếp ("nói chuyện")**, **trao đổi dữ liệu**, và **chia sẻ tài nguyên** (như dùng chung máy in, dữ liệu ổ cứng, đường truyền Internet).

### 1.2. Phân loại mạng theo quy mô địa lý

| Loại mạng | Định nghĩa | Phạm vi địa lý | Đặc trưng tốc độ & độ trễ | Ví dụ thực tế |
| :--- | :--- | :--- | :--- | :--- |
| **PAN** *(Personal Area Network)* | Mạng cá nhân kết nối các thiết bị xung quanh một người. | Cực nhỏ (< 10 mét) | Tốc độ thấp, độ trễ thấp, tiêu thụ ít điện năng. | Điện thoại kết nối tai nghe Bluetooth, đồng hồ thông minh, chuột không dây. |
| **LAN** *(Local Area Network)* | Mạng cục bộ kết nối các thiết bị trong khu vực giới hạn. | Nhỏ (< 2 km), thường trong nhà, văn phòng, trường học. | Băng thông cực cao (1 - 10+ Gbps), độ trễ cực thấp (1 - 5 ms). | Mạng Wi-Fi và mạng dây cắm giữa các máy tính trong cùng một văn phòng công ty. |
| **MAN** *(Metropolitan Area Network)* | Mạng đô thị kết nối nhiều mạng LAN lại với nhau. | Trung bình (10 - 100 km), trong phạm vi một thành phố. | Tốc độ cao, độ trễ trung bình (10 - 20 ms). | Mạng lưới camera giao thông đô thị, mạng nội bộ kết nối các phòng giao dịch ngân hàng cùng thành phố. |
| **WAN** *(Wide Area Network)* | Mạng diện rộng kết nối các khoảng cách địa lý rất xa. | Rất lớn (Toàn cầu, liên quốc gia, liên lục địa). | Băng thông thay đổi, độ trễ cao (30 - 300+ ms). Internet là mạng WAN lớn nhất. | Hệ thống mạng kết nối văn phòng đại diện tại Việt Nam với trụ sở chính tại Mỹ của tập đoàn đa quốc gia. |

---

## 🏗️ 2. MÔ HÌNH MẠNG PHÂN TẦNG (NETWORK MODELS)

Để chuẩn hóa việc truyền tin giữa các thiết bị khác loại, các nhà khoa học sử dụng các mô hình phân tầng. Hai mô hình nổi tiếng nhất là:
*   **OSI (7 tầng):** Mô hình lý thuyết học thuật, phân chia nhiệm vụ vô cùng chi tiết để giảng dạy và sửa lỗi (troubleshooting).
*   **TCP/IP (4 hoặc 5 tầng):** Mô hình thực tế thương mại, là nền tảng vận hành toàn bộ mạng Internet hiện đại ngày nay.

### Bảng đối chiếu các tầng giữa mô hình OSI và TCP/IP

| Tầng OSI | Tên tầng OSI | Mô hình TCP/IP (5 tầng) | Chức năng chính | Giao thức / Thiết bị tiêu biểu |
| :---: | :--- | :---: | :--- | :--- |
| **7** | Application (Ứng dụng) | . | Tiếp nhận dữ liệu từ ứng dụng người dùng, mã hóa, giải mã bảo mật và quản lý phiên kết nối. | HTTP, HTTPS, DNS, SMTP, FTP, TLS/SSL, Sockets |
| **6** | Presentation (Trình diễn) | **Application** | . | . |
| **5** | Session (Phiên) | . | . | . |
| **4** | Transport (Giao vận) | **Transport** | Truyền tải dữ liệu đầu-cuối, kiểm soát lỗi, chia nhỏ gói tin và quản lý cổng kết nối (Port). | TCP, UDP |
| **3** | Network (Mạng) | **Network** | Định tuyến (tìm đường đi) cho gói tin xuyên qua các mạng dựa trên địa chỉ IP. | IP (IPv4/IPv6), ICMP, Thiết bị: **Router** |
| **2** | Data Link (Liên kết dữ liệu) | **Data Link** | Đóng gói dữ liệu thành các Frame vật lý, kiểm soát lỗi đường truyền nội bộ LAN bằng địa chỉ MAC. | Ethernet, Wi-Fi, ARP, Thiết bị: **Switch, NIC** |
| **1** | Physical (Vật lý) | **Physical** | Chuyển đổi dữ liệu nhị phân thành tín hiệu điện, ánh sáng, sóng vô tuyến để truyền qua dây cáp/không khí. | Cáp quang, cáp đồng, sóng Wifi, Thiết bị: **Hub, Repeater** |

---

## 🔌 3. CHI TIẾT THIẾT BỊ MẠNG VẬT LÝ (DEVICES)

Thiết bị mạng trung gian đóng vai trò chuyển tiếp dữ liệu từ thiết bị gửi tới thiết bị nhận một cách chính xác và hiệu quả.

### 3.1. Thiết bị ở Tầng Vật lý (Layer 1)

#### A. Hub (Bộ tập trung)
*   **Định nghĩa:** Thiết bị kết nối nhiều máy tính trong mạng LAN đời cũ.
*   **Vai trò & Cơ chế hoạt động:** Khi nhận được tín hiệu điện từ một cổng, Hub không cần suy nghĩ mà lập tức nhân bản tín hiệu đó rồi phát tán (**Broadcast**) ra tất cả các cổng còn lại.
*   **Mặt hạn chế:** Gây nghẽn mạng do xung đột dữ liệu (Collision Domain) và không an toàn (máy không cần nhận vẫn phải nhận gói tin).
*   **Sơ đồ kết nối:**
    ```
      [PC A] -----\
      [PC B] ----- [ HUB ] (Phát tán tín hiệu mù quáng)
      [PC C] -----/     |
                        v
                     [PC D] (Nhận gói tin của PC A dù không muốn)
    ```

#### B. Repeater (Bộ lặp tín hiệu)
*   **Định nghĩa:** Thiết bị khuếch đại tín hiệu đường truyền.
*   **Vai trò & Cơ chế hoạt động:** Khi tín hiệu vật lý truyền đi khoảng cách quá xa sẽ bị suy hao (yếu dần). Repeater thu tín hiệu yếu đó, làm sạch nhiễu, khuếch đại mạnh lên và truyền tiếp để kéo dài khoảng cách đường truyền.
*   **Sơ đồ kết nối:**
    ```
      [PC Gửi] === (Tín hiệu yếu dần) ===> [ REPEATER ] === (Tín hiệu khỏe lại) ===> [PC Nhận]
    ```

---

### 3.2. Thiết bị ở Tầng Liên kết dữ liệu (Layer 2)

#### A. Switch (Bộ chuyển mạch)
*   **Định nghĩa:** Thiết bị kết nối thông minh các máy tính trong mạng LAN hiện đại.
*   **Vai trò & Cơ chế hoạt động:** Switch tự động học địa chỉ vật lý (**MAC Address**) của các thiết bị cắm vào cổng của nó và lưu vào một bảng (`MAC Address Table`). Khi nhận gói tin, nó đọc địa chỉ MAC đích và chuyển tiếp chính xác (**Unicast**) đến cổng chứa thiết bị nhận, không phát tán bừa bãi như Hub.
*   **Sơ đồ kết nối:**
    ```
      [PC A] (MAC: A) ---\
      [PC B] (MAC: B) ---- [ SWITCH ] ---- [PC C] (MAC: C)
                           (Bảng MAC)
                           Port 1: MAC A
                           Port 2: MAC B
                           Port 3: MAC C
    ```

#### B. NIC (Network Interface Card - Card mạng)
*   **Định nghĩa:** Bo mạch phần cứng được tích hợp sẵn trong máy tính hoặc điện thoại.
*   **Vai trò & Cơ chế hoạt động:** Đóng vai trò là cầu nối vật lý giữa bo mạch máy tính và môi trường truyền dẫn (dây cáp/Wi-Fi). Nó chuyển dữ liệu nhị phân từ RAM thành tín hiệu điện/ánh sáng/sóng truyền đi và ngược lại. Mỗi NIC được nhà sản xuất nạp sẵn một địa chỉ MAC duy nhất trên toàn cầu.

---

### 3.3. Thiết bị ở Tầng Mạng (Layer 3) và Tầng Cao

#### A. Router (Bộ định tuyến)
*   **Định nghĩa:** Thiết bị kết nối các mạng khác nhau (như nối mạng LAN nhà bạn với mạng WAN của nhà mạng ISP).
*   **Vai trò & Cơ chế hoạt động:** Router đọc địa chỉ logical (**IP Address**) đích của gói tin, tra cứu bảng định tuyến (`Routing Table`) của nó để quyết định gửi gói tin qua ngã rẽ nào để đến đích nhanh nhất.
*   **Sơ đồ kết nối:**
    ```
      [ Mạng LAN Nhà Bạn ]                              [ Mạng WAN Internet ]
      [PC 1] -\                                           /- [Server Google]
      [PC 2] --- [ SWITCH ] --- [ ROUTER ] --- [ ISP ] ----- [Server Zalo]
    ```

#### B. Gateway (Cổng chuyển đổi)
*   **Định nghĩa:** Thiết bị hoặc phần mềm kết nối hai hệ thống mạng sử dụng các bộ giao thức hoàn toàn khác nhau.
*   **Vai trò & Cơ chế hoạt động:** Đóng vai trò là "thông dịch viên". Nó nhận dữ liệu từ mạng này, biên dịch cấu trúc gói tin sang định dạng của mạng kia để hai bên có thể hiểu nhau (Ví dụ: Gateway kết nối mạng viễn thông điện thoại di động với mạng Internet).

---

## 📡 4. MÔI TRƯỜNG TRUYỀN DẪN (TRANSMISSION MEDIA)

Dữ liệu được chuyển đổi thành các dạng năng lượng vật lý để di chuyển dọc theo môi trường truyền dẫn.

### Bảng phân biệt các môi trường truyền dẫn chính

| Môi trường | Bản chất vật lý | Vai trò | Ý nghĩa | Chức năng | Tình huống NÊN dùng | Tình huống TRÁNH dùng |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Ánh sáng** *(Cáp quang - Fiber Optics)* | Dẫn tia sáng laser đi bên trong các sợi thủy tinh siêu mỏng bằng hiện tượng phản xạ toàn phần. | Là xương sống của mạng toàn cầu. Kết nối các khoảng cách lớn, xuyên đại dương. | Cho phép truyền tải băng thông siêu lớn (Gbps đến Tbps), không bị nhiễu điện từ. | Truyền tải dữ liệu đi cực xa với tốc độ ánh sáng và độ suy hao tín hiệu thấp nhất. | * Kết nối giữa các quốc gia (cáp quang biển).<br>* Đường trục kết nối giữa các trung tâm dữ liệu (Datacenter). | Kết nối di động hoặc các góc hẹp trong nhà cần uốn cong dây cáp (sợi thủy tinh giòn, dễ gãy). |
| **Sóng vô tuyến** *(Không dây - Wi-Fi, 4G, 5G, Bluetooth)* | Lan tỏa sóng điện từ trong không gian tự do. | Cung cấp kết nối di động linh hoạt, không dây. | Giúp người dùng kết nối Internet mọi lúc mọi nơi mà không cần dây cáp loằng ngoằng. | Phát tín hiệu sóng mang dữ liệu ra xung quanh để các ăng-ten thiết bị thu nhận. | * Thiết bị di động (điện thoại, laptop, thiết bị IoT cầm tay).<br>* Khu vực địa hình phức tạp không thể đi dây cáp mạng. | * Các phòng thí nghiệm nhiễu sóng nặng.<br>* Ứng dụng yêu cầu độ tin cậy tuyệt đối, độ trễ cực thấp (phẫu thuật từ xa, giao dịch tài chính tốc độ cao). |
| **Cáp đồng** *(Cáp đồng xoắn đôi - Ethernet Cat5e/Cat6)* | Truyền dòng điện mang điện áp cao/thấp dọc theo lõi đồng. | Kết nối nội bộ cự ly gần trong văn phòng và gia đình. | Chi phí rẻ, dễ bấm đầu cáp, dễ lắp đặt và tương thích với hầu hết thiết bị văn phòng. | Chuyển đổi dữ liệu nhị phân thành các xung điện để truyền dẫn cự ly ngắn. | * Nối mạng nội bộ LAN từ Switch tới máy tính bàn (PC), máy in, camera giám sát.<br>* Cự ly kết nối dưới 100 mét. | * Khoảng cách kết nối xa vượt quá 100 mét (tín hiệu điện bị suy hao mạnh).<br>* Đi sát các dòng điện cao thế (gây nhiễu tín hiệu). |
