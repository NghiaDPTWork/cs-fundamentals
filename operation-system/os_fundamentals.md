# KIẾN TRÚC VÀ NGUYÊN LÝ VẬN HÀNH CỦA HỆ ĐIỀU HÀNH

---

## TÓM TẮT NỘI DUNG (ABSTRACT)

Báo cáo này nghiên cứu cấu trúc, phân tầng kiến trúc và nguyên lý vận hành của hệ thống máy tính hiện đại dưới sự quản lý của Hệ điều hành (Operating System - OS). Nội dung báo cáo phân tích theo mạch liên kết nhân quả logic từ bản chất vật lý của tập chỉ thị, mối quan hệ tương tác giữa mã nguồn và hệ điều hành, cho đến các cơ chế kỹ thuật cụ thể như quản lý bộ nhớ ảo, phân chia tiến trình, lập lịch CPU và cấu trúc hệ thống tập tin. Cuối cùng, báo cáo tiến hành so sánh đối chiếu kiến trúc của ba hệ điều hành phổ biến: Windows, macOS và Linux.

---

## 1. BẢN CHẤT VẬT LÝ CỦA MÁY TÍNH VÀ VAI TRÒ CỦA MÃ NGUỒN (SOURCE CODE)

### 1.1 Cơ sở vật lý và hệ thống nhị phân

Bộ vi xử lý trung tâm (CPU) được cấu thành từ hàng tỷ bóng bán dẫn (transistor) hoạt động như các cổng logic với hai trạng thái điện áp bật và tắt (on/off). Trạng thái này được biểu diễn dưới dạng số nhị phân: `0` (không có dòng điện) và `1` (có dòng điện). Đây là ngôn ngữ duy nhất mà phần cứng máy tính có thể trực tiếp tiếp nhận.

### 1.2 Kiến trúc tập chỉ thị (Instruction Set Architecture - ISA)

Nhằm điều khiển hoạt động tính toán của CPU, nhà sản xuất phần cứng định nghĩa một bộ mã lệnh nhị phân thô gọi là **Mã máy (Machine Code)** dựa trên kiến trúc ISA (như x86-64, ARMv9, RISC-V). Mỗi mã máy đại diện cho một lệnh vi mô sơ cấp (như dịch bit, tải dữ liệu từ thanh ghi).

### 1.3 Sự cần thiết của Mã nguồn (Source Code)

- **Nguyên nhân**: Việc lập trình và bảo trì các ứng dụng phức tạp bằng hệ thống số nhị phân thô `0` và `1` vượt quá khả năng xử lý hiệu quả của con người.
- **Kết quả**: Sự ra đời của **Mã nguồn (Source Code)** viết bằng các ngôn ngữ lập trình bậc cao (C, Java, Python).
- **Cơ chế chuyển đổi**: Mã nguồn đóng vai trò là tập chỉ thị logic trừu tượng, được biên dịch (Compile) hoặc thông dịch (Interpret) sang mã máy nhị phân trước khi đưa vào CPU thực thi.

---

## 2. MỐI QUAN HỆ KIẾN TRÚC TƯƠNG TÁC: CODE - OS - PHẦN MỀM

Hệ thống máy tính hiện đại vận hành dựa trên mô hình phân tầng chức năng nghiêm ngặt:

```
┌────────────────────────────────────────────────────────┐
│                      Người Dùng                        │
└──────────────────────────┬─────────────────────────────┘
                           ▼
┌────────────────────────────────────────────────────────┐
│                  Phần Mềm (Software)                   │
│      (Gồm mã nguồn đã được đóng gói và biên dịch)      │
└──────────────────────────┬─────────────────────────────┘
                           ▼ (System Calls / APIs)
┌────────────────────────────────────────────────────────┐
│                 Hệ Điều Hành (OS)                      │
│     (Môi trường runtime & Quản trị tài nguyên hệ thống)│
└──────────────────────────┬─────────────────────────────┘
                           ▼ (Mã máy / Tín hiệu nhị phân)
┌────────────────────────────────────────────────────────┐
│                   Phần Cứng (Hardware)                 │
└────────────────────────────────────────────────────────┘
```

- **Mã nguồn (Code)**: Là các câu lệnh logic tĩnh thể hiện giải thuật của lập trình viên.
- **Phần mềm (Software)**: Tập hợp mã nguồn và tài nguyên liên quan đã được đóng gói thành tệp thực thi (như định dạng `.exe` hoặc `.elf`) nhằm giải quyết một nhóm tác vụ cụ thể.
- **Hệ điều hành (OS)**: Đóng vai trò môi trường runtime và quản trị viên hệ thống. Phần mềm không tương tác trực tiếp với phần cứng mà phải thông qua các lời gọi hệ thống (System Calls) do OS cung cấp để yêu cầu tài nguyên như bộ nhớ, chu kỳ CPU hoặc truy cập thiết bị I/O.

---

## 3. ĐỊNH NGHĨA VÀ KIẾN TRÚC CỦA HỆ ĐIỀU HÀNH (OPERATING SYSTEM)

Hệ điều hành là hệ thống phần mềm nền tảng thực hiện chức năng quản lý tài nguyên phần cứng và cung cấp các dịch vụ hạ tầng cho phần mềm ứng dụng. Thành phần trung tâm của OS là **Nhân (Kernel)**.

### 3.1 Kernel Space và User Space

Để bảo vệ hệ thống khỏi các lỗi phần mềm và nguy cơ bảo mật, OS phân chia không gian bộ nhớ thành hai vùng đặc quyền:

- **Kernel Space (Không gian nhân)**: Vùng bộ nhớ dành riêng cho Kernel và các driver phần cứng. Vùng này có quyền truy cập tối cao, không giới hạn vào mọi tài nguyên vật lý.
- **User Space (Không gian người dùng)**: Vùng bộ nhớ chạy các ứng dụng thông thường. Các ứng dụng này không có quyền truy cập trực tiếp phần cứng và bị giới hạn đặc quyền.

---

## 4. PHƯƠNG THỨC GIAO TIẾP HỆ THỐNG: GUI VÀ CLI

Hệ điều hành cung cấp các lớp giao tiếp (Shell) để tiếp nhận lệnh từ phía phần mềm hoặc người dùng:

- **GUI (Graphical User Interface)**: Giao diện đồ họa cho phép tương tác trực quan thông qua các phần tử hình ảnh. GUI là lớp ứng dụng chạy trên User Space, chuyển đổi thao tác người dùng thành các cuộc gọi hệ thống tương ứng.
- **CLI/CMD (Command Line Interface)**: Giao diện dòng lệnh văn bản thô. Người dùng gõ trực tiếp các lệnh chuẩn hóa gửi đến Shell để thực thi trực tiếp trên OS.

### Nguyên nhân lập trình viên ưu tiên sử dụng CLI:

1. **Tối ưu hóa tài nguyên**: GUI tiêu tốn tài nguyên RAM và GPU đáng kể để dựng đồ họa. CLI xử lý luồng văn bản thô nên tiêu thụ tài nguyên cực kỳ thấp, đáp ứng yêu cầu vận hành các máy chủ không màn hình hiển thị (Headless Servers).
2. **Khả năng tự động hóa**: Cho phép liên kết các dòng lệnh thành tập tin kịch bản (Scripting) để tự động hóa hàng loạt tác vụ quản trị hệ thống phức tạp mà không cần sự can thiệp thủ công.

---

## 5. TIẾN TRÌNH KHỞI CHẠY VÀ NẠP CHƯƠNG TRÌNH VÀO BỘ NHỚ

Khi có lệnh khởi chạy một tệp tin thực thi, OS thực hiện một chuỗi các tác vụ kỹ thuật tuần tự:

```
[Thiết bị lưu trữ (File tĩnh)] ──(Loader)──> [RAM vật lý (Mã máy động)]
                                                    │
                                         (Ánh xạ địa chỉ ảo)
                                                    │
                                                    ▼
[CPU (Đăng ký thanh ghi PC)] <─── [Khởi tạo Process & Main Thread]
```

1. **Kích hoạt System Call**: Hệ thống tiếp nhận lệnh và gọi hàm thực thi (như `execve` trên Unix-like).
2. **Phân tích định dạng**: Bộ nạp (**Loader**) đọc cấu trúc file thực thi để xác định các vùng dữ liệu (Data Segment) và mã lệnh (Code Segment).
3. **Cấp phát bộ nhớ ảo**: OS khởi tạo không gian địa chỉ bộ nhớ ảo (**Virtual Address Space**) trong RAM vật lý cho chương trình.
4. **Khởi tạo Tiến trình**: OS cấp phát định danh tiến trình (**PID**) và cấu trúc dữ liệu quản lý tương ứng.
5. **Đăng ký thanh ghi CPU**: OS nạp địa chỉ điểm vào (Entry Point - hàm `main`) của chương trình vào thanh ghi bộ đếm chương trình (**Program Counter - PC**) của CPU để bắt đầu chu kỳ thực thi chỉ thị lệnh.

---

## 6. NGUYÊN LÝ QUẢN LÝ BỘ NHỚ: RAM, STORAGE VÀ VIRTUAL MEMORY

### 6.1 So sánh đặc tính RAM vật lý và Storage

- **RAM (Physical Memory)**: Sử dụng cấu trúc linh kiện bán dẫn (tụ điện/transistor) để lưu trữ thông tin tạm thời, có đặc tính bay hơi (**Volatile** - mất dữ liệu khi ngắt điện). Tốc độ truy xuất siêu nhanh (độ trễ ~10ns), cho phép CPU truy cập trực tiếp qua Bus hệ thống.
- **Storage (SSD/HDD)**: Lưu trữ dữ liệu vĩnh viễn trên chip nhớ flash hoặc đĩa từ, có đặc tính không bay hơi (**Non-volatile**). Tốc độ truy xuất chậm (độ trễ ~50µs đến 10ms). CPU không thể giao tiếp trực tiếp với dữ liệu lưu trên Storage.

### 6.2 Cơ chế Bộ nhớ ảo (Virtual Memory) và Phân trang (Paging)

Nhằm giải quyết giới hạn dung lượng của RAM vật lý khi chạy đồng thời nhiều tác vụ nặng, OS triển khai cơ chế **Virtual Memory**:

- **Paging (Phân trang)**: OS chia không gian bộ nhớ ảo thành các trang (**Pages** - thường là 4KB) và chia RAM vật lý thành các khung trang (**Page Frames**). Bảng trang (**Page Table**) đảm nhận nhiệm vụ ánh xạ địa chỉ ảo sang địa chỉ vật lý.
- **Swapping & Page Fault**:
  - Khi RAM đầy, OS tự động chuyển (swap) các trang ít sử dụng xuống phân vùng đệm trên ổ cứng (Swap Space/Pagefile).
  - Khi CPU yêu cầu truy cập một trang đã bị swap, sự kiện ngắt **Page Fault** được kích hoạt. OS sẽ tạm dừng tiến trình, nạp trang từ ổ cứng trở lại RAM (Page In) và cập nhật lại bảng ánh xạ trước khi cho tiến trình chạy tiếp.

---

## 7. CẤU TRÚC THỰC THI: TIẾN TRÌNH (PROCESS) VÀ LUỒNG (THREAD)

- **Process (Tiến trình)**: Thực thể chạy độc lập do OS quản lý, sở hữu một không gian địa chỉ ảo riêng, các tài nguyên hệ thống (file descriptors, sockets) và thông tin bảo mật.
- **Thread (Luồng)**: Đơn vị lập lịch thực thi nhỏ nhất của CPU nằm trong một tiến trình. Các luồng thuộc cùng một tiến trình sẽ chia sẻ chung mã nguồn, heap, và tài nguyên hệ thống, nhưng có các thanh ghi (registers) và vùng nhớ ngăn xếp (stack) riêng.

### Bảng so sánh đặc tính kỹ thuật:

| Tiêu chí                      | Tiến trình (Process)                                     | Luồng (Thread)                                                          |
| :---------------------------- | :------------------------------------------------------- | :---------------------------------------------------------------------- |
| **Không gian bộ nhớ**         | Tách biệt hoàn toàn và cô lập.                           | Chia sẻ chung không gian địa chỉ của Process cha.                       |
| **Chi phí tài nguyên**        | Cao (Cần tạo bảng trang, cấp PID, phân bổ vùng nhớ).     | Thấp (Chỉ phân bổ vùng stack nhỏ và tập thanh ghi).                     |
| **Thời gian chuyển ngữ cảnh** | Chậm (Yêu cầu thay đổi bảng trang và xóa bộ đệm TLB).    | Nhanh (Không thay đổi bảng trang bộ nhớ).                               |
| **Độ ổn định hệ thống**       | Cao. Tiến trình lỗi sập không ảnh hưởng tiến trình khác. | Thấp. Một luồng lỗi nghiêm trọng có thể làm sập toàn bộ tiến trình cha. |

---

### 7.1 Cơ chế cô lập bộ nhớ của Tiến trình (Virtual Address Space Isolation)

- **Nguyên nhân**: Trong môi trường đa nhiệm, các tiến trình chạy đồng thời dễ dẫn đến nguy cơ xung đột truy cập bộ nhớ: một tiến trình độc hại đọc trộm dữ liệu nhạy cảm hoặc ghi đè làm lỗi dữ liệu của tiến trình khác.
- **Giải pháp**: OS triển khai cơ chế **Cô lập không gian địa chỉ ảo**. Mỗi tiến trình có một bảng ánh xạ trang bộ nhớ riêng. Địa chỉ logic của một tiến trình được chuyển dịch sang các địa chỉ vật lý độc lập trên RAM. Một tiến trình hoàn toàn không thể truy cập vật lý vào vùng nhớ của tiến trình khác nếu không thông qua cơ chế giao tiếp liên tiến trình (IPC) chịu sự kiểm soát nghiêm ngặt của OS.

---

## 8. CƠ CHẾ ĐIỀU PHỐI CPU (CPU SCHEDULING)

Để phân chia năng lực tính toán của số lượng hữu hạn các nhân CPU vật lý cho hàng trăm luồng công việc đang chờ đợi, hệ điều hành thiết lập bộ lập lịch (**Scheduler**):

- **Time-slicing (Lát cắt thời gian)**: Mỗi luồng được cấp một khoảng thời gian chạy tối đa gọi là **Quantum** (thường từ 10ms - 100ms) trên CPU core.
- **Context Switching (Chuyển đổi ngữ cảnh)**: Khi hết Quantum hoặc luồng bị chặn do đợi I/O, hệ thống sẽ thực hiện chuyển đổi ngữ cảnh: Lưu trạng thái thanh ghi CPU hiện tại của luồng vào cấu trúc lưu trữ (TCB), chọn luồng tiếp theo từ Ready Queue, khôi phục trạng thái và nạp lệnh cho luồng mới.
- **Thuật toán lập lịch phổ biến**:
  - _Round Robin (RR)_: Phân phối xoay vòng Quantum bằng nhau cho các luồng trong hàng đợi.
  - _Shortest Job First (SJF)_: Ưu tiên thực hiện luồng có thời gian xử lý ngắn nhất trước để giảm thời gian chờ trung bình.
  - _Priority Scheduling_: Lập lịch dựa trên chỉ số mức độ ưu tiên của tiến trình.

---

## 9. HỆ THỐNG TẬP TIN (FILE SYSTEM)

Thiết bị lưu trữ vật lý chỉ nhận dạng dữ liệu ở dạng chuỗi các khối vật lý tuần tự (Logical Block Address - LBA).

- **Chức năng**: File System là cấu trúc dữ liệu logic và hệ thuật toán được tích hợp vào OS để ánh xạ các khối đĩa vật lý thô thành cấu trúc thư mục dạng cây và các tập tin logic trực quan.
- **Metadata (Siêu dữ liệu)**: Lưu trữ thuộc tính tệp tin như tên tệp, quyền sở hữu, quyền truy cập đọc/ghi, kích thước, và quan trọng nhất là bảng danh sách các khối vật lý chứa dữ liệu thực tế trên đĩa cứng.

### So sánh các hệ thống tập tin tiêu biểu:

- **NTFS**: Khả năng chịu lỗi cao nhờ cơ chế ghi nhật ký (Journaling), hỗ trợ nén tệp tin và bảo mật phân quyền ACL trên Windows.
- **APFS**: Tối ưu hóa sâu cho bộ nhớ flash/SSD, sao chép tức thời (Copy-on-write), mã hóa mạnh mẽ trên hệ sinh thái Apple.
- **ext4**: Hệ thống tập tin hiệu năng cao của Linux, tối ưu hóa phân phối dung lượng cho file cực lớn và độ ổn định cao trên máy chủ.

---

## 10. SO SÁNH KIẾN TRÚC CÁC HỆ ĐIỀU HÀNH PHỔ BIẾN

| Tiêu chí so sánh         | Windows (Kiến trúc NT)                                                                        | macOS (Hệ điều hành Darwin)                                                   | Linux (Nhân Linux)                                                                                                               |
| :----------------------- | :-------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------- |
| **Kiến trúc Kernel**     | **Hybrid Kernel**. Kết hợp mô hình mô-đun của Microkernel và hiệu năng của Monolithic Kernel. | **Hybrid Kernel (XNU)**. Nhân lai ghép dựa trên Mach microkernel và BSD Unix. | **Monolithic Kernel**. Nhân đơn khối, toàn bộ driver và hệ quản lý bộ nhớ chạy trực tiếp ở Kernel Space để đạt hiệu năng tối đa. |
| **File System mặc định** | NTFS                                                                                          | APFS                                                                          | ext4                                                                                                                             |
| **Môi trường Shell**     | PowerShell, Command Prompt                                                                    | Zsh (Z Shell)                                                                 | Bash (Bourne Again Shell)                                                                                                        |
| **Triết lý thiết kế**    | Ưu tiên tính tương thích ngược lâu dài cho doanh nghiệp và thương mại.                        | Khép kín, đồng bộ hóa tối ưu giữa phần cứng và phần mềm của Apple.            | Mã nguồn mở (Open-source), tính linh hoạt và khả năng tùy biến sâu cho máy chủ và lập trình.                                     |
