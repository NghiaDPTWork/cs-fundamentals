# MẠNG MÁY TÍNH CĂN BẢN (NETWORK FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức mạng máy tính nền tảng. Thiết bị mạng và các giao thức được tích hợp đồng bộ theo từng Tầng (Layer) để làm rõ cơ chế đóng gói, mở gói và luồng đi của dữ liệu qua các trạm.

---

## 1. ĐỊNH NGHĨA & PHÂN LOẠI MẠNG MÁY TÍNH

*   **Định nghĩa:** Mạng máy tính là một hệ thống gồm hai hoặc nhiều thiết bị độc lập được kết nối với nhau thông qua phương tiện truyền dẫn vật lý hoặc không dây để giao tiếp, trao đổi dữ liệu và chia sẻ tài nguyên.

### Bảng phân loại mạng theo quy mô địa lý

| Loại mạng | Định nghĩa phỏng vấn | Phạm vi địa lý | Đặc trưng hiệu năng | Ví dụ thực tế |
| :--- | :--- | :--- | :--- | :--- |
| **PAN** | Mạng kết nối cá nhân xung quanh một người. | Cực nhỏ (< 10 mét) | Tiêu thụ ít điện năng, thiết lập tức thời. | Tai nghe Bluetooth kết nối điện thoại, chuột không dây. |
| **LAN** | Mạng cục bộ trong phạm vi văn phòng, gia đình. | Nhỏ (< 2 km) | Băng thông cực cao (1-10+ Gbps), độ trễ cực thấp (1-5 ms). | Kết nối Wi-Fi hoặc mạng dây cắm giữa các máy tính trong văn phòng. |
| **MAN** | Mạng kết nối đô thị giữa nhiều mạng LAN. | Trung bình (10-100 km) | Thuộc sở hữu viễn thông hoặc liên minh doanh nghiệp. | Mạng camera giao thông thành phố, mạng nội bộ ngân hàng cùng thành phố. |
| **WAN** | Mạng diện rộng liên quốc gia, toàn cầu. | Rất lớn (Toàn cầu) | Băng thông biến động lớn, độ trễ cao (30-300+ ms). Internet là ví dụ lớn nhất. | Kết nối các văn phòng chi nhánh toàn cầu của một tập đoàn qua cáp quang biển. |

---

## 2. CÁC MÔ HÌNH KIẾN TRÚC MẠNG (NETWORK ARCHITECTURE MODELS)

Để thiết kế và vận hành một mạng máy tính hiệu quả, người ta chia các mô hình kiến trúc theo hai góc nhìn: **Giao tiếp giao thức (Protocol & Software)** và **Thiết kế hạ tầng vật lý (Physical/Logical Design)**.

### 2.1. Tại sao lại dùng mô hình phân tầng? (Why Layering?)

Trước khi có mô hình phân tầng, việc kết nối hai máy tính cực kỳ gian nan vì một chương trình mạng phải tự xử lý từ đầu đến cuối: vừa điều khiển dòng điện trên dây cáp, vừa dò lỗi, vừa mã hóa dữ liệu. Phân tầng ra đời dựa trên nguyên lý **"Chia để trị" (Divide and Conquer)** với các lợi ích cốt lõi sau:

1.  **Tính độc lập & Ghép nối lỏng (Loose Coupling):** Mỗi tầng chỉ thực hiện một nhiệm vụ chuyên biệt và chỉ giao tiếp với tầng ngay trên và ngay dưới nó thông qua các giao diện chuẩn (API). Sự thay đổi công nghệ ở tầng này (ví dụ: chuyển từ cáp đồng sang cáp quang ở Tầng 1) không bắt buộc các tầng trên (như HTTP ở Tầng 5) phải thay đổi hay viết lại code.
2.  **Đơn giản hóa việc phát triển:** Nhà phát triển ứng dụng web chỉ cần tập trung vào Tầng 5 (Application) mà không cần quan tâm gói tin của mình sẽ được định tuyến đi qua những con đường nào (Tầng 3) hay truyền qua sóng vô tuyến hay cáp vật lý (Tầng 1).
3.  **Chuẩn hóa quốc tế (Standardization):** Tạo ra tiếng nói chung cho các nhà sản xuất thiết bị phần cứng (Cisco, Juniper, HP) và phần mềm (Microsoft, Linux, Google) khác nhau có thể tương thích và truyền tin được với nhau.

---

### 2.2. So sánh các mô hình kiến trúc giao tiếp (Protocol & Communication Architectures)

Dưới đây là bảng phân biệt giữa mô hình phân tầng và các mô hình kiến trúc giao tiếp khác trong mạng:

| Mô hình kiến trúc | Định nghĩa bản chất | Đặc điểm luồng truyền | Ưu điểm cốt lõi | Nhược điểm lớn nhất | Tình huống ứng dụng thực tế |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Phân tầng (Layered)** | Chia nhỏ chức năng giao tiếp thành các lớp chồng lên nhau, lớp dưới phục vụ lớp trên. | Dữ liệu đi tuần tự từ trên xuống dưới (đóng gói) rồi từ dưới lên trên (mở gói). | Dễ bảo trì, tính linh hoạt cực cao, chuẩn hóa tốt. | Độ trễ tăng nhẹ do phải đi qua nhiều tầng bóc/đóng gói vỏ. | Các mô hình mạng tiêu chuẩn như **OSI**, **TCP/IP**. |
| **Nguyên khối (Monolithic)** | Tất cả chức năng giao tiếp gom chung vào một khối mã nguồn duy nhất, không chia lớp. | Dữ liệu được xử lý trực tiếp bởi một chương trình lớn, truy cập thẳng phần cứng. | Tốc độ xử lý cực nhanh, hiệu năng tối đa, tốn ít bộ nhớ. | Cực kỳ khó bảo trì, thay đổi một chi tiết nhỏ là phải viết lại toàn bộ hệ thống. | Các thiết bị nhúng siêu nhỏ (IoT), chip điều khiển hoặc hệ thống thời gian thực (Real-time). |
| **Khách - Chủ (Client-Server)** | Mô hình bất đối xứng gồm một máy yêu cầu dịch vụ (Client) và một máy phản hồi (Server). | Luồng truyền tập trung: Client chủ động gửi Request $\rightarrow$ Server xử lý và trả về Response. | Dễ quản lý dữ liệu tập trung, bảo mật tốt, kiểm soát tài nguyên dễ dàng. | Server là điểm nghẽn cổ chai (SPOF - Single Point of Failure); dễ bị quá tải (DDoS). | Các dịch vụ Web (HTTP), Email, Database Server, ứng dụng di động thông thường. |
| **Ngang hàng (Peer-to-Peer - P2P)** | Mô hình đối xứng trong đó mỗi thiết bị (Peer) vừa đóng vai trò là Client vừa là Server. | Dữ liệu truyền phi tập trung trực tiếp giữa các Peer với nhau mà không qua máy chủ trung gian. | Không lo sập máy chủ, càng nhiều người tham gia mạng truyền tải càng nhanh. | Khó kiểm soát an ninh thông tin, khó quản lý tính nhất quán của dữ liệu. | Tải file Torrent (BitTorrent), mạng Blockchain (Bitcoin, Ethereum), cuộc gọi Skype P2P. |

---

### 2.3. So sánh mô hình thiết kế hạ tầng mạng (Network Design Perspectives)

Khi thiết kế hạ tầng mạng LAN/WAN trong doanh nghiệp, kỹ sư mạng phải chọn lựa giữa mô hình mạng phẳng và mạng phân cấp:

| Tiêu chí so sánh | Mạng phẳng (Flat Network) | Mạng phân cấp (Hierarchical Network) |
| :--- | :--- | :--- |
| **Khái niệm bản chất** | Tất cả các thiết bị nằm chung trong một phân vùng mạng duy nhất (cùng một broadcast domain), kết nối trực tiếp qua các Switch Layer 2. | Mạng được chia nhỏ và tổ chức thành 3 tầng logic riêng biệt (Core - Distribution - Access) để quản lý luồng dữ liệu một cách khoa học. |
| **Cách thức định tuyến** | Hầu như không định tuyến nội bộ, dữ liệu truyền đi dựa trên địa chỉ MAC (Layer 2 broadcast). | Định tuyến chặt chẽ ở tầng trung gian (Distribution) giúp cô lập sự cố và định hướng đường đi tối ưu cho dữ liệu. |
| **Khả năng mở rộng (Scalability)** | **Kém.** Khi số lượng thiết bị tăng lên, lưu lượng Broadcast (như ARP Request) sẽ làm nghẽn toàn bộ băng thông mạng (Broadcast Storm). | **Cực tốt.** Có thể dễ dàng thêm mới các Switch, Router hoặc phân vùng mạng mà không làm ảnh hưởng đến hiệu năng của hệ thống hiện tại. |
| **Tính bảo mật & Quản lý** | **Thấp.** Khó phân chia quyền truy cập, một thiết bị nhiễm mã độc có thể dễ dàng tấn công hoặc nghe lén toàn bộ thiết bị khác trong mạng. | **Cao.** Dễ dàng áp dụng các chính sách bảo mật, tường lửa, ACL (Access Control List) tại tầng Distribution để kiểm soát lưu lượng giữa các vùng. |
| **Chi phí đầu tư** | Thấp, cấu hình đơn giản, không đòi hỏi thiết bị đắt tiền hay trình độ quản trị cao. | Cao, yêu cầu các thiết bị Switch L3/Router cao cấp và đội ngũ kỹ sư vận hành chuyên nghiệp. |
| **Tình huống khuyên dùng** | Mạng gia đình, văn phòng nhỏ (dưới 20-30 thiết bị), quán cà phê quy mô nhỏ. | Mạng doanh nghiệp vừa và lớn, trường học, bệnh viện, tòa nhà văn phòng, trung tâm dữ liệu. |

> [!NOTE]
> **Mô hình phân cấp 3 lớp của Cisco bao gồm:**
> 1. **Tầng Access (Truy cập):** Điểm kết nối trực tiếp cho các thiết bị đầu cuối (PC, IP Phone, Camera, Wifi AP) vào mạng.
> 2. **Tầng Distribution (Phân phối):** Nơi thực thi các chính sách bảo mật (ACL), định tuyến giữa các VLAN, và gom lưu lượng từ tầng Access lên tầng Core.
> 3. **Tầng Core (Lõi/Xương sống):** Nhiệm vụ duy nhất là chuyển mạch gói tin với tốc độ siêu nhanh (high-speed switching) giữa các vùng mạng lớn mà không xử lý chính sách để tránh làm chậm hệ thống.

---

### 2.4. Đi sâu vào mô hình phân tầng tiêu biểu (OSI và TCP/IP)

Sau khi hiểu lý do phân tầng và phân biệt các kiến trúc mạng, chúng ta đi vào hai mô hình phân tầng kinh điển: **OSI (7 tầng)** và **TCP/IP (5 hoặc 4 tầng)**.

*   **So sánh cốt lõi:** Mô hình **OSI** là mô hình lý thuyết tham chiếu phục vụ giảng dạy và chuẩn hóa thiết kế mạng; còn mô hình **TCP/IP** là mô hình thực tế thương mại, là nền tảng vận hành toàn bộ mạng Internet hiện đại ngày nay.

#### Bảng đối chiếu các tầng và giao thức tiêu biểu

| Tầng OSI | Tên tầng OSI | Mô hình TCP/IP | Chức năng cốt lõi | Giao thức / Thiết bị tiêu biểu |
| :---: | :--- | :---: | :--- | :--- |
| **7, 6, 5** | Application, Presentation, Session | **Application** | Tiếp nhận dữ liệu, định dạng mã hóa, mã hóa bảo mật (TLS/SSL) và quản lý phiên. | HTTP, HTTPS, DNS, SMTP, FTP, TLS/SSL, Sockets |
| **4** | Transport (Giao vận) | **Transport** | Truyền tải dữ liệu đầu-cuối (end-to-end), kiểm soát lỗi, chia nhỏ gói tin và quản lý cổng Port. | TCP, UDP |
| **3** | Network (Mạng) | **Network** | Định tuyến (routing) gói tin dựa trên địa chỉ IP đích xuyên mạng. | IP (IPv4/IPv6), ICMP. Thiết bị: **Router** |
| **2** | Data Link (Liên kết dữ liệu) | **Data Link** | Đóng gói Frame vật lý, kiểm soát lỗi đường truyền nội bộ LAN bằng địa chỉ MAC vật lý. | Ethernet, Wi-Fi, ARP. Thiết bị: **Switch, NIC** |
| **1** | Physical (Vật lý) | **Physical** | Chuyển đổi dữ liệu nhị phân thành tín hiệu điện, ánh sáng, sóng vô tuyến để truyền qua dây cáp/không khí. | Cáp quang, cáp đồng, sóng vô tuyến. Thiết bị: **Hub, Repeater** |

---

## 3. MÔI TRƯỜNG TRUYỀN DẪN (TRANSMISSION MEDIA)

Dữ liệu được chuyển đổi thành các dạng năng lượng vật lý để di chuyển dọc theo môi trường truyền dẫn.

### Bảng phân biệt các môi trường truyền dẫn chính

| Môi trường | Bản chất vật lý | Vai trò | Ý nghĩa | Chức năng | Tình huống NÊN dùng | Tình huống TRÁNH dùng |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Ánh sáng** *(Cáp quang)* | Dẫn tia sáng laser đi trong sợi thủy tinh bằng hiện tượng phản xạ toàn phần. | Xương sống của mạng Internet toàn cầu (cáp quang biển, trục kết nối). | Băng thông cực lớn (Gbps-Tbps), không bị nhiễu điện từ. | Truyền tải dữ liệu đi cực xa với tốc độ ánh sáng và độ suy hao tín hiệu thấp nhất. | Kết nối giữa các quốc gia, các trung tâm dữ liệu (Datacenter). | Kết nối di động hoặc các vị trí uốn cong góc hẹp (sợi thủy tinh giòn, dễ gãy). |
| **Sóng vô tuyến** *(Không dây)* | Lan tỏa sóng điện từ trong không gian tự do. | Kết nối các thiết bị di động linh hoạt (Wifi, 4G, 5G, Bluetooth). | Giúp thiết bị kết nối mạng mọi lúc mọi nơi không cần dây cáp vật lý. | Phát tín hiệu sóng mang dữ liệu ra môi trường xung quanh để thiết bị thu nhận. | Thiết bị di động, IoT cầm tay, các địa hình phức tạp không thể đi dây cáp mạng. | Ứng dụng yêu cầu độ tin cậy tuyệt đối, độ trễ cực thấp (giao dịch tài chính tốc độ cao, phẫu thuật từ xa). |
| **Cáp đồng** *(Cáp mạng đồng)* | Truyền dòng điện mang điện áp cao/thấp dọc theo lõi đồng. | Kết nối nội bộ cự ly gần (văn phòng, gia đình). | Chi phí rẻ, dễ lắp đặt và uốn cong, tương thích với hầu hết thiết bị văn phòng. | Chuyển đổi dữ liệu nhị phân thành các xung điện để truyền dẫn cự ly ngắn. | Nối mạng nội bộ LAN từ Switch tới máy tính bàn (PC), máy in, camera giám sát. | Khoảng cách kết nối xa vượt quá 100 mét (suy hao tín hiệu mạnh) hoặc khu vực nhiễu điện từ nặng. |

---

## 4. HƯỚNG DẪN TÍCH HỢP PHÂN TẦNG: THIẾT BỊ & GIAO THỨC (LAYER-BY-LAYER INTEGRATION)

Dưới đây là chi tiết tích hợp của từng Tầng mạng. Mỗi tầng làm rõ: Đơn vị dữ liệu, Thiết bị hoạt động (nhận tín hiệu ở đâu, dạng nào, tại sao phải dùng dạng đó) và các Giao thức điều phối.

### 4.1. Tầng 1: Vật lý (Physical Layer)
*   **Chức năng chính:** Chuyển đổi dữ liệu nhị phân thành các tín hiệu vật lý để truyền dẫn qua không khí hoặc dây cáp.
*   **Đơn vị dữ liệu (Data Unit):** **Bits** (dưới dạng các xung điện thế cao/thấp trên cáp đồng, xung ánh sáng tắt/mở trên cáp quang, hoặc sóng điện từ điều tần trên wifi).

#### Thiết bị hoạt động ở Tầng 1:
1.  **Hub (Bộ tập trung):**
    *   *Nhận tín hiệu ở đâu/như nào:* Nhận trực tiếp các xung điện thông qua các cổng RJ45 vật lý nối dây cáp đồng từ các máy tính.
    *   *Nhận dưới dạng nào:* Nhận dưới dạng **Bits (tín hiệu xung điện thô)**.
    *   *Tại sao nhận dưới dạng đó:* Vì Hub là thiết bị phần cứng thô sơ, không có CPU hay bộ xử lý thông minh để đọc được địa chỉ MAC hay IP. Nó chỉ biết nhân bản tín hiệu điện nhận vào ở một cổng rồi phát tán (Broadcast) mù quáng ra tất cả các cổng còn lại.
2.  **Repeater (Bộ lặp):**
    *   *Nhận tín hiệu ở đâu/như nào:* Nhận xung điện/ánh sáng bị yếu đi sau quãng đường truyền xa thông qua cổng vật lý.
    *   *Nhận dưới dạng nào:* Nhận dưới dạng **Bits (tín hiệu vật lý suy hao)**.
    *   *Tại sao nhận dưới dạng đó:* Để thực hiện nhiệm vụ duy nhất: lọc nhiễu, khuếch đại dòng điện/ánh sáng mạnh lên và truyền tiếp sang đoạn cáp tiếp theo nhằm kéo dài cự ly đường truyền.

#### Giao thức hoạt động ở Tầng 1:
*   **10Base-T / 100Base-TX / 1000Base-T:** Các chuẩn giao thức định nghĩa thông số vật lý của cáp mạng Ethernet (tốc độ truyền, số sợi đồng, cách bấm đầu RJ45).

---

### 4.2. Tầng 2: Liên kết dữ liệu (Data Link Layer)
*   **Chức năng chính:** Đóng gói dữ liệu thành các khung truyền, kiểm soát lỗi nội bộ mạng LAN và xác định cơ chế truy cập đường truyền vật lý.
*   **Đơn vị dữ liệu (Data Unit):** **Frames** (gồm Header chứa địa chỉ MAC nguồn/đích, dữ liệu IP Packet ở giữa, và phần đuôi FCS/CRC để kiểm tra lỗi).

#### Thiết bị hoạt động ở Tầng 2:
1.  **Switch (Bộ chuyển mạch):**
    *   *Nhận tín hiệu ở đâu/như nào:* Nhận tín hiệu nhị phân từ các cổng RJ45 kết nối trực tiếp với các thiết bị trong cùng mạng LAN.
    *   *Nhận dưới dạng nào:* Nhận dưới dạng **Frames**.
    *   *Tại sao nhận dưới dạng đó:* Vì Switch có bộ xử lý thông minh để đọc được phần Header của Frame. Nó cần đọc địa chỉ **MAC nguồn** để ghi vào bảng MAC (MAC Table) cổng tương ứng, và đọc địa chỉ **MAC đích** để chuyển tiếp chính xác (Unicast) gói tin sang cổng chứa thiết bị nhận, tránh va chạm tín hiệu. đồng thời đọc phần đuôi Frame để kiểm tra xem dữ liệu có bị lỗi trong quá trình truyền hay không.
2.  **NIC (Network Interface Card - Card mạng):**
    *   *Nhận tín hiệu ở đâu/như nào:* Đón nhận dòng điện từ cáp mạng hoặc sóng điện từ từ ăng-ten Wi-Fi.
    *   *Nhận dưới dạng nào:* Chuyển đổi từ tín hiệu điện/sóng (Bits) thành cấu trúc **Frame** nhị phân để giao tiếp với hệ điều hành.
    *   *Tại sao nhận dưới dạng đó:* Card mạng là trạm biên dịch. Nó cần bóc vỏ Frame để kiểm tra xem địa chỉ MAC đích của Frame có khớp với địa chỉ MAC vật lý của chính nó hay không. Nếu khớp, nó mới gửi dữ liệu lên CPU xử lý, nếu không khớp (của máy khác gửi nhầm), nó lập tức vứt bỏ (drop) để tránh làm phiền CPU.

#### Giao thức hoạt động ở Tầng 2:
*   **Ethernet:** Giao thức chuẩn hóa định dạng Frame và quy tắc gửi tin trong mạng dây LAN.
*   **ARP (Address Resolution Protocol):**
    *   *Định nghĩa:* Giao thức dịch chuyển địa chỉ logic (IP) thành địa chỉ vật lý (MAC) trong cùng mạng LAN.
    *   *Cơ chế:* Khi máy tính muốn gửi dữ liệu đến IP `192.168.1.5` nhưng chưa biết địa chỉ MAC của máy đó, nó gửi một yêu cầu **ARP Request Broadcast** ra toàn mạng LAN. Thiết bị có IP tương ứng sẽ trả lời bằng **ARP Reply Unicast** chứa địa chỉ MAC của nó.

---

### 4.3. Tầng 3: Mạng (Network Layer)
*   **Chức năng chính:** Định tuyến (tìm đường đi tối ưu) cho gói tin đi qua nhiều mạng khác nhau trên toàn cầu.
*   **Đơn vị dữ liệu (Data Unit):** **Packets** (gồm Header chứa địa chỉ IP nguồn/đích và dữ liệu payload).

#### Thiết bị hoạt động ở Tầng 3:
*   **Router (Bộ định tuyến):**
    *   *Nhận tín hiệu ở đâu/như nào:* Nhận thông qua các cổng mạng kết nối các đường truyền khác nhau (cổng LAN nối mạng nội bộ, cổng WAN nối mạng Internet).
    *   *Nhận dưới dạng nào:* Nhận dưới dạng **Packets** (sau khi đã bóc lớp vỏ Frame Layer 2 bên ngoài).
    *   *Tại sao nhận dưới dạng đó:* Vì Router là thiết bị liên mạng. Nhiệm vụ của nó là định tuyến xuyên quốc gia. Nó cần đọc địa chỉ **IP đích** nằm trong IP Header của Packet, tra cứu bảng định tuyến (Routing Table) để tìm ra cổng mạng ra tối ưu nhất để chuyển tiếp gói tin đi tiếp đến đích.

#### Giao thức hoạt động ở Tầng 3:
*   **IP (Internet Protocol - IPv4/IPv6):** Giao thức định nghĩa địa chỉ IP logical và đóng gói gói tin để định tuyến xuyên mạng.
*   **ICMP (Internet Control Message Protocol):** Giao thức truyền tải các thông điệp chẩn đoán lỗi mạng (được sử dụng bởi lệnh `ping` và `traceroute`).

---

### 4.4. Tầng 4: Giao vận (Transport Layer)
*   **Chức năng chính:** Truyền tải dữ liệu tin cậy hoặc không tin cậy giữa hai tiến trình ứng dụng đầu-cuối (end-to-end).
*   **Đơn vị dữ liệu (Data Unit):** **Segments** (đối với TCP) hoặc **Datagrams** (đối với UDP) (chứa Port nguồn/đích).
*   **Thiết bị hoạt động:** Hoạt động trực tiếp trên CPU và Hệ điều hành (OS Kernel) của các thiết bị đầu cuối (PC, điện thoại, máy chủ). Hệ điều hành bóc vỏ IP Packet để lấy ra TCP Segment/UDP Datagram, đọc số **Port đích** để biết phải chuyển dữ liệu này cho tiến trình ứng dụng nào đang chạy (ví dụ Port 80 chuyển cho Nginx, Port 3306 chuyển cho MySQL).

#### Giao thức hoạt động ở Tầng 4:
*   **TCP (Transmission Control Protocol):** Hướng kết nối (Connection-oriented), bắt tay 3 bước, đảm bảo truyền tin tin cậy 100%, tự gửi lại khi mất gói và kiểm soát tắc nghẽn.
*   **UDP (User Datagram Protocol):** Phi kết nối (Connectionless), không bắt tay, truyền tin nhanh tối đa nhưng không đảm bảo tin cậy (livestream, game online).

---

### 4.5. Tầng 5: Ứng dụng (Application Layer - Gộp L5, L6, L7 OSI)
*   **Chức năng chính:** Nơi các ứng dụng thực tế trao đổi dữ liệu, định dạng mã hóa và bảo mật.
*   **Đơn vị dữ liệu (Data Unit):** **Data / Message** (dữ liệu thô như văn bản, JSON, file hình ảnh, video).
*   **Thiết bị hoạt động:** Các thiết bị đầu cuối (máy tính, điện thoại) chạy phần mềm của người dùng, hoặc **Gateway** ở tầng cao (thực hiện dịch chuyển giao thức ứng dụng).

#### Giao thức hoạt động ở Tầng 5:
*   **HTTP / HTTPS:** Giao thức truyền dữ liệu Web chạy trên TCP qua cổng 80 / 443 (HTTPS mã hóa bằng TLS/SSL).
*   **DNS:** Phân giải tên miền thành địa chỉ IP, chạy trên UDP cổng 53.
*   **FTP:** Truyền nhận file, dùng TCP cổng 21 (truyền lệnh) và cổng 20 (truyền dữ liệu file).
*   **SMTP:** Gửi thư điện tử đi, chạy trên TCP cổng 25 hoặc 587.

---

## 5. LUỒNG CHUYỂN TIẾP GÓI TIN QUA CÁC TRẠM (FORWARDING FLOW THROUGH STATIONS)

Để hiểu rõ sự liên kết logic giữa thiết bị, giao thức và định dạng dữ liệu, hãy xem quy trình chuyển tiếp gói tin từ máy gửi qua các trạm trung gian (Switch, Router) đến máy nhận:

```
[Máy gửi] ===> [ SWITCH (Trạm L2) ] ===> [ ROUTER (Trạm L3) ] ===> [Máy nhận]
```

### Bước 1: Tại máy gửi (Đóng gói từ Layer 5 $\rightarrow$ Layer 1)
*   Dữ liệu được đóng gói từ trên xuống dưới: Data $\rightarrow$ Segment (gắn Port) $\rightarrow$ Packet (gắn IP nguồn/đích) $\rightarrow$ Frame (gắn MAC nguồn của máy gửi, MAC đích của Router).
*   Card mạng NIC chuyển đổi Frame thành các tín hiệu điện (Bits) và truyền lên dây cáp.

### Bước 2: Tại Trạm Switch (Layer 2)
*   Switch nhận tín hiệu điện từ cổng vật lý, gom lại thành **Frame**.
*   Switch đọc địa chỉ MAC đích ở phần đầu Frame. 
*   **Cơ chế xử lý:** Nó tra bảng MAC, tìm thấy cổng ra tương ứng và chuyển tiếp Frame đó đi.
*   *Lưu ý cốt lõi:* **Switch không hề thay đổi bất kỳ thông tin nào trong Frame và không bóc lớp vỏ IP Packet bên trong.** Nó chỉ là trạm trung chuyển Frame nguyên vẹn ở Layer 2.

### Bước 3: Tại Trạm Router (Layer 3)
*   Router nhận tín hiệu điện từ cổng LAN, gom thành **Frame**.
*   Router đọc địa chỉ MAC đích của Frame. Thấy trùng với địa chỉ MAC cổng của nó, Router **bóc bỏ lớp vỏ Frame Layer 2** (vứt bỏ MAC nguồn/đích cũ).
*   Router lấy ra **IP Packet** ở Layer 3 và đọc địa chỉ IP đích.
*   **Cơ chế xử lý:** Router tra cứu bảng định tuyến để tìm cổng ra.
*   *Lưu ý cốt lõi:* Trước khi gửi gói tin ra cổng mới, Router bắt buộc phải **đóng vỏ Frame Layer 2 mới**:
    *   MAC nguồn mới = MAC cổng ra của Router.
    *   MAC đích mới = MAC của Router tiếp theo (hoặc MAC của máy nhận nếu đã cùng mạng LAN đích).
*   **Qua mỗi Router, IP nguồn và IP đích của Packet ở Layer 3 luôn giữ nguyên vẹn, chỉ có địa chỉ MAC ở vỏ Frame Layer 2 thay đổi theo từng trạm đi qua.**

### Bước 4: Tại máy nhận (Mở gói từ Layer 1 $\rightarrow$ Layer 5)
*   Card mạng NIC nhận tín hiệu điện $\rightarrow$ dịch thành Frame $\rightarrow$ kiểm tra MAC khớp $\rightarrow$ bóc vỏ Frame L2 lấy Packet $\rightarrow$ bóc vỏ Packet L3 lấy Segment $\rightarrow$ đọc Port đích L4 đưa dữ liệu vào Socket Buffer của ứng dụng $\rightarrow$ ứng dụng Layer 5 đọc và hiển thị dữ liệu thô.
