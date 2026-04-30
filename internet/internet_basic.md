# INTERNET FUNDAMENTALS - THE BIG PICTURE

## PHẦN 1: BỐI CẢNH & TẦM NHÌN (THE BIG PICTURE)

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

### Dòng thời gian & Sự tiến hóa

| Thời gian | Mô hình                         | Trạng thái hiện tại                        |
| :-------- | :------------------------------ | :----------------------------------------- |
| **1970s** | **DoD / TCP/IP**                | **Tiêu chuẩn thực tế toàn cầu**            |
| **1974**  | **SNA (IBM)**                   | Chỉ còn dùng trong hệ thống Mainframe cũ   |
| **1980s** | **IPX/SPX, AppleTalk, NetBIOS** | Đã bị khai tử hoặc thay thế bởi TCP/IP     |
| **1984**  | **OSI Model**                   | **Chuẩn tham chiếu lý thuyết (Reference)** |

---

## PHẦN 3: CẤU TRÚC CHI TIẾT CÁC TẦNG (DETAILED LAYER EXPLORATION)

Đây là nơi chúng ta sẽ đi sâu vào từng tầng của mô hình TCP/IP để hiểu cách dữ liệu thực sự vận hành.

### 1. Tầng Ứng dụng (Application Layer) - Cửa ngõ giao tiếp

Đây là tầng cao nhất, nơi người dùng và ứng dụng tương tác trực tiếp.

#### Các giao thức cốt lõi (Protocols)

| Giao thức      | Tên đầy đủ                  | Vai trò                                                     |
| :------------- | :-------------------------- | :---------------------------------------------------------- |
| **HTTP/HTTPS** | HyperText Transfer Protocol | Xương sống của Web, truyền tải dữ liệu (Văn bản, Ảnh, API). |
| **DNS**        | Domain Name System          | "Danh bạ" Internet, dịch tên miền sang địa chỉ IP.          |
| **WebSocket**  | WebSocket Protocol          | Giao tiếp 2 chiều thời gian thực (Chat, Game).              |
| **FTP/SMTP**   | File/Mail Protocol          | Truyền file và gửi nhận Email.                              |
| **SSH**        | Secure Shell                | Điều khiển máy chủ từ xa một cách bảo mật.                  |

<details>
<summary><b>Xem chi tiết: HTTP/HTTPS - "Mạch máu" của thế giới Web</b></summary>

**Tại sao phải học kỹ HTTP?**
Mọi thứ bạn làm với React, Vue hay Node.js đều xoay quanh HTTP. Nếu không hiểu nó, bạn sẽ không bao giờ hiểu tại sao Request bị chậm, tại sao lỗi CORS xảy ra, hay làm thế nào để tối ưu hóa Cache. HTTP chính là hiện thực hóa của "Tầm nhìn" ở

Phần 1: Trao đổi tài nguyên toàn cầu.

**1. Luồng vận hành (The Flow):**

- **Handshake:** Trước khi gửi HTTP, máy tính phải thực hiện "bắt tay" TCP và TLS (nếu là HTTPS) để tạo đường hầm an toàn.
- **Request:** Client gửi "đơn yêu cầu" gồm:
  - _Request Line:_ Hành động (GET/POST) và địa chỉ.
  - _Headers:_ Chứa thông tin về trình duyệt, định dạng dữ liệu (JSON/HTML), và quan trọng nhất là `Cookie`.
- **Processing:** Server nhận đơn, xử lý Logic/Database.
- **Response:** Trả lời với một mã trạng thái (Status Code).

**2. Giải quyết sự "Mất trí nhớ" (Statelessness):**
HTTP bản chất không nhớ ai là ai. Để ứng dụng Web hoạt động (ví dụ: giỏ hàng, đăng nhập), chúng ta dùng:

- **Cookie & Session:** Server giữ "sổ hộ khẩu" (Session), Client giữ "thẻ căn cước" (Cookie ID).
- **JWT (JSON Web Token):** Một "tấm thẻ" đã được ký số, tự chứa thông tin người dùng, giúp hệ thống mở rộng (Scalability) cực tốt vì Server không cần lưu trữ Session.

**3. Liên kết với Phần 1 & 2:**
HTTP là tầng Ứng dụng chạy trên nền tảng TCP (Tầng Giao vận). Khi bạn nhấn "Gửi", HTTP tạo ra Message, và TCP sẽ chịu trách nhiệm băm nhỏ nó để truyền đi.

</details>

<details>
<summary><b>Xem chi tiết: DNS - Điểm bắt đầu của mọi hành trình</b></summary>

**Tại sao phải học DNS?**
DNS là bước đầu tiên của **mọi** kết nối Internet. Nếu DNS chết, Internet coi như tê liệt (dù hạ tầng vẫn thông). Hiểu DNS giúp bạn cấu hình tên miền, hiểu về độ trễ (latency) và cách các hệ thống lớn (như Facebook, Google) điều phối người dùng tới Server gần nhất.

**1. Cơ chế phân cấp (Hierarchy):**
DNS không tập trung ở một chỗ (tránh Single Point of Failure như đã nói ở Phần 1). Nó chia thành các cấp:

- **Root Server (.):** Trạm trung chuyển cao nhất.
- **TLD Server (.com, .org):** Quản lý phần đuôi.
- **Authoritative Server:** Nơi lưu giữ địa chỉ IP thực sự của bạn.

**2. Quy trình "Truy tìm" địa chỉ:**
Khi bạn gõ `google.com`, một chuỗi phản ứng dây chuyền xảy ra:

- **Trình duyệt hỏi OS:** "Anh có nhớ IP này không?" (Local Cache).
- **OS hỏi Resolver (Nhà mạng):** "Ông tìm hộ tôi với".
- **Resolver đi "du lịch" toàn cầu:** Hỏi Root -> Hỏi TLD -> Hỏi Authoritative để lấy bằng được con số IP (ví dụ: `172.217.161.206`).

**3. Liên kết:**
DNS biến URL (định danh tài nguyên ở Phần 2) thành IP (địa chỉ thực thể ở tầng Mạng). Đây là bước "thông dịch" từ ngôn ngữ con người sang ngôn ngữ máy tính.

</details>

<details>
<summary><b>Xem chi tiết: WebSocket - Phá vỡ giới hạn của HTTP</b></summary>

**Tại sao phải học WebSocket?**
HTTP chỉ cho phép "Khách hỏi - Chủ trả lời". Điều này làm chậm các ứng dụng như Chat, Game hay Chứng khoán. WebSocket sinh ra để tạo ra sự tương tác thời gian thực (Real-time).

**1. Sự khác biệt gốc rễ:**

- **HTTP:** Mở kết nối -> Gửi Request -> Nhận Response -> Đóng kết nối.
- **WebSocket:** Mở kết nối -> Giữ kết nối vĩnh viễn (cho đến khi 1 bên chủ động ngắt). Cả hai bên có thể nói chuyện cùng lúc (Full-duplex).

**2. Cách thức vận hành:**

- Nó bắt đầu bằng một cuộc "Bắt tay" HTTP (Handshake). Client gửi lời đề nghị: "Tôi muốn nâng cấp lên WebSocket".
- Nếu Server đồng ý, hai bên sẽ chuyển sang một "ngôn ngữ" mới nhanh hơn, nhẹ hơn, không tốn tài nguyên Headers cho mỗi lần gửi tin.

**3. Liên kết:**
WebSocket thể hiện sự linh hoạt của Tầng Ứng dụng. Nó chứng minh rằng cùng một hạ tầng Internet, chúng ta có thể tạo ra nhiều "cách giao tiếp" khác nhau tùy mục đích sản phẩm.

</details>

<details>
<summary><b>Xem chi tiết: FTP - Kênh vận chuyển hàng hóa hạng nặng</b></summary>

**Tại sao phải học FTP?**
Trong khi HTTP tối ưu cho văn bản/ảnh nhỏ, FTP được tối ưu cho việc truyền tải tệp tin khổng lồ. Hiểu FTP giúp bạn quản lý mã nguồn trên Server, hiểu cách các hệ thống Backup dữ liệu vận hành.

**1. Cơ chế 2 kênh (Dual-Channel):**
Đây là điểm độc đáo của FTP để đảm bảo hiệu suất:

- **Kênh điều khiển (Port 21):** Chỉ dùng để gửi lệnh (Login, Di chuyển thư mục).
- **Kênh dữ liệu (Port 20):** Một đường ống riêng biệt chỉ để đẩy dữ liệu file.
  => Khi bạn đang tải file 10GB, bạn vẫn có thể gửi lệnh "Dừng" ở kênh điều khiển mà không bị nghẽn.

**2. Liên kết:**
FTP là một ví dụ điển hình cho việc sử dụng nhiều Cổng (Ports) ở Tầng Giao vận (Transport) để tối ưu hóa công việc.

</details>

<details>
<summary><b>Xem chi tiết: SMTP/POP3 - Hệ thống bưu điện số</b></summary>

**Tại sao phải học về Mail Protocol?**
Dù là Dev Web hay App, bạn đều sẽ phải làm tính năng "Gửi mail xác thực" hoặc "Thông báo". Hiểu SMTP giúp bạn cấu hình các dịch vụ như SendGrid, Mailgun và hiểu tại sao mail lại bị rơi vào Spam.

**1. Phân chia nhiệm vụ:**

- **SMTP (Simple Mail Transfer Protocol):** Chuyên trách việc **Gửi** thư đi.
- **POP3 / IMAP:** Chuyên trách việc **Nhận** và đọc thư từ Server về máy.

**2. Quy trình "Bưu điện":**
Nó giống hệt cách bưu điện truyền thống vận hành: Bạn đưa thư cho bưu cục (SMTP Server của bạn) -> Bưu cục tìm địa chỉ và gửi tới bưu cục người nhận (SMTP Server người nhận) -> Người nhận dùng chìa khóa (POP3/IMAP) để mở hòm thư lấy thư về.

**3. Liên kết:**
Giao thức này cho thấy Internet không chỉ là Web (HTTP), mà còn là một mạng lưới các dịch vụ chuyên biệt chạy song song với nhau.

</details>

<details>
<summary><b>Xem chi tiết: SSH - "Chìa khóa vạn năng" cho máy chủ</b></summary>

**Tại sao phải học SSH?**
Là một lập trình viên, bạn không thể lúc nào cũng ngồi trước màn hình máy chủ. SSH cho phép bạn điều khiển bất kỳ máy tính nào trên thế giới như thể đang ngồi trước nó. Đây là kỹ năng sống còn của DevOps và Backend Dev.

**1. Bảo mật tuyệt đối:**
SSH sử dụng mã hóa bất đối xứng. Khi bạn kết nối, một đường hầm mã hóa (Tunnel) được thiết lập. Dù Hacker có bắt được gói tin ở giữa đường (Man-in-the-middle), họ cũng chỉ thấy những ký tự rác.

**2. Các ứng dụng thực tế:**

- **Remote Terminal:** Gõ lệnh điều khiển Server.
- **Port Forwarding:** "Mượn" một cổng từ Server về máy mình để debug.
- **SFTP:** Truyền file bảo mật dựa trên nền tảng SSH.

**3. Liên kết:**
SSH giải quyết bài toán "An ninh" đã đặt ra ở Phần 1. Nó chứng minh rằng ngay cả trên một mạng WAN công cộng đầy rủi ro, chúng ta vẫn có thể tạo ra những kết nối riêng tư và an toàn tuyệt đối.

</details>

---
