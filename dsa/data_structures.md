# CẨM NANG VẤN ĐÁP CẤU TRÚC DỮ LIỆU (INTERVIEW-READY DATA STRUCTURES)

Tài liệu này tổng hợp toàn bộ các cấu trúc dữ liệu cốt lõi được cấu trúc chuyên biệt cho việc ôn luyện phỏng vấn kỹ thuật. Mỗi phần chỉ tập trung vào: Định nghĩa phỏng vấn trực diện, bảng phân loại các dạng con kèm cách nhận biết khi đọc đề, cơ chế duyệt cây (Tree Traversal) và phân tích sâu sắc xung đột băm (Hash Collision) của HashMap.

---

## 📖 1. ĐỊNH NGHĨA CẤU TRÚC DỮ LIỆU

*   **Định nghĩa:** Cấu trúc dữ liệu (Data Structure) là cách thức tổ chức, quản lý và lưu trữ dữ liệu trong bộ nhớ máy tính một cách khoa học để có thể truy xuất, thao tác và tối ưu hóa tài nguyên hiệu quả nhất.
*   **Đặc trưng cốt lõi khi phỏng vấn:**
    *   **Time Complexity (Độ phức tạp thời gian):** Thời gian thực hiện các tác vụ (Insert, Delete, Access, Search) được biểu diễn bằng Big O.
    *   **Space Complexity (Độ phức tạp không gian):** Lượng bộ nhớ RAM cần tiêu thụ để cấu trúc tự duy trì.
    *   **Memory Layout (Bố cục bộ nhớ):** Vùng nhớ liên tục (như Mảng) hay rải rác qua liên kết (như Danh sách liên kết).

---

## 📶 2. PHÂN TÍCH CẤU TRÚC DỮ LIỆU TUYẾN TÍNH (LINEAR DATA STRUCTURES)

### 2.1. Mảng (Array - Tĩnh và Động)
*   **Định nghĩa:** Mảng là cấu trúc dữ liệu lưu trữ một tập hợp các phần tử có cùng kiểu dữ liệu tại các vị trí bộ nhớ liên tiếp nhau trên RAM.

| Loại mảng | Định nghĩa ngắn gọn | Nhận biết khi đọc đề bài | Flow truy xuất dữ liệu |
| :--- | :--- | :--- | :--- |
| **Mảng tĩnh (Static Array)** | Kích thước cố định từ lúc biên dịch (Compile-time), không thể thay đổi sau khi tạo. | Cho trước kích thước tối đa ($N \le 10^5$), không có nhu cầu co giãn kích thước động. | Truy cập trực tiếp địa chỉ trong $O(1)$: `Address = BaseAddress + index * SizeOfElement`. |
| **Mảng động (Dynamic Array / ArrayList)** | Kích thước tự động nhân đôi sức chứa khi mảng bị đầy lúc chạy (Runtime). | Số lượng phần tử thay đổi liên tục, không biết trước dung lượng tối đa. | Ghi đè lên vùng nhớ liên tiếp. Khi đầy, OS cấp vùng nhớ mới to gấp đôi, sao chép mảng cũ sang và giải phóng mảng cũ. |

---

### 2.2. Danh sách liên kết (Linked List)
*   **Định nghĩa:** Danh sách liên kết là một cấu trúc dữ liệu tuyến tính động, gồm nhiều nút (Node) nằm rải rác trên RAM kết nối với nhau bằng con trỏ địa chỉ (Link).

| Loại Linked List | Định nghĩa ngắn gọn | Nhận biết khi đọc đề bài | Flow truy xuất dữ liệu |
| :--- | :--- | :--- | :--- |
| **Đơn (Singly Linked List)** | Mỗi Node chỉ chứa dữ liệu và 1 con trỏ `Next` trỏ đến Node kế tiếp. | Cần duyệt dữ liệu một chiều từ đầu đến cuối để tiết kiệm bộ nhớ con trỏ. | Bắt đầu từ `Head`, đi dọc theo các con trỏ `Next` đến khi gặp `null` ($O(n)$ access). |
| **Đôi (Doubly Linked List)** | Mỗi Node chứa 2 con trỏ: `Next` (tiếp theo) và `Prev` (phía trước). | Cần duyệt dữ liệu qua lại linh hoạt hai chiều, hoặc thường xuyên xóa/chèn ở vị trí biết trước. | Từ Node hiện tại có thể tiến lên qua `Next` hoặc lùi lại qua `Prev` ($O(1)$ insert/delete nếu đã có con trỏ). |
| **Vòng (Circular Linked List)** | Node cuối cùng trỏ ngược về Node đầu tiên (`Head`), tạo thành vòng khép kín. | Dữ liệu mang tính chất lặp lại tuần hoàn (như chia lượt chơi game, lập lịch CPU Round-Robin). | Duyệt tuần hoàn liên tục không điểm dừng, khi đi hết danh sách con trỏ tự động đưa về `Head`. |

---

### 2.3. Ngăn xếp (Stack)
*   **Định nghĩa:** Ngăn xếp là một cấu trúc dữ liệu tuyến tính động hoạt động theo nguyên lý **LIFO (Last In First Out - Vào sau, Ra trước)**.

| Loại Stack | Định nghĩa ngắn gọn | Nhận biết khi đọc đề bài | Flow truy xuất dữ liệu |
| :--- | :--- | :--- | :--- |
| **Stack bằng Mảng (Array-based)** | Sử dụng mảng để lưu phần tử, có biến chỉ số `top` làm con trỏ đỉnh. | Số lượng phần tử tối đa xác định, cần tối ưu bộ nhớ tối đa, tránh tạo rác rải rác. | Đọc/ghi trực tiếp tại chỉ số `arr[top]` ($O(1)$ push/pop). Bị giới hạn kích thước mảng. |
| **Stack bằng Linked List (List-based)** | Sử dụng danh sách liên kết, các thao tác thực hiện tại đầu danh sách (`Head`). | Không giới hạn số lượng phần tử trước, kích thước thay đổi liên tục. | `Push` là chèn Node vào đầu `Head` ($O(1)$), `Pop` là xóa Node tại đầu `Head` ($O(1)$). |

*   **Dấu hiệu nhận biết bài toán dùng Stack:** Hành động đảo ngược (Undo/Redo), kiểm tra các cặp ngoặc hợp lệ, duyệt cây DFS hoặc bài toán tìm phần tử lớn hơn tiếp theo (Next Greater Element).

---

### 2.4. Hàng đợi (Queue)
*   **Định nghĩa:** Hàng đợi là cấu trúc dữ liệu tuyến tính động hoạt động theo nguyên lý **FIFO (First In First Out - Vào trước, Ra trước)**.

| Loại Queue | Định nghĩa ngắn gọn | Nhận biết khi đọc đề bài | Flow truy xuất dữ liệu |
| :--- | :--- | :--- | :--- |
| **Hàng đợi thường (Linear Queue)** | Thêm ở cuối mảng (`Rear`) và lấy ra ở đầu mảng (`Front`). | Cần xử lý các tiến trình theo thứ tự thời gian yêu cầu gửi đến (in ấn, tin nhắn mạng). | Khi lấy ra (Dequeue), `Front` dịch chuyển sang phải, các ô nhớ trống bên trái bị lãng phí. |
| **Hàng đợi vòng (Circular Queue)** | Kết nối vị trí cuối mảng quay lại vị trí đầu mảng bằng toán tử chia lấy dư `%`. | Sử dụng mảng để cài đặt Queue nhưng muốn tái sử dụng các ô nhớ trống đã lấy ra. | Khi `Rear` chạm cuối mảng, nếu đầu mảng trống, con trỏ tự động quay về chỉ số 0. |
| **Hàng đợi hai đầu (Deque)** | Cho phép thêm và xóa phần tử ở cả hai đầu `Front` và `Rear`. | Bài toán cần trượt cửa sổ tối ưu (Sliding Window Maximum) hoặc cần thêm/xóa linh hoạt ở cả 2 phía. | Truy xuất đầu-cuối cực nhanh trong $O(1)$ cho cả thao tác thêm và xóa. |
| **Hàng đợi ưu tiên (Priority Queue)** | Phần tử lấy ra dựa vào độ ưu tiên lớn nhất/nhỏ nhất, không phụ thuộc thời gian xếp hàng. | Bài toán liên tục yêu cầu lấy phần tử cực trị (max/min) như thuật toán Dijkstra, Prim. | Cài đặt bằng cấu trúc **Heap**. Lấy ra phần tử ưu tiên nhất trong $O(\log n)$ và tái cấu trúc cây. |

---

## 🏗️ 3. PHÂN TÍCH CẤU TRÚC DỮ LIỆU PHI TUYẾN TÍNH (NON-LINEAR DATA STRUCTURES)

### 3.1. Cấu trúc Cây (Tree)
*   **Định nghĩa:** Cây là một cấu trúc dữ liệu phân cấp, không có chu trình, gồm một nút gốc (Root) và các nút con được liên kết với nhau bằng các cạnh.

| Loại Cây (Tree) | Định nghĩa ngắn gọn | Nhận biết khi đọc đề bài | Flow truy xuất dữ liệu |
| :--- | :--- | :--- | :--- |
| **Cây nhị phân thường (Binary Tree)** | Mỗi nút có tối đa 2 nút con (con trái và con phải). | Các bài toán phân cấp nhị phân đơn giản. | Đi từ Root, rẽ nhánh sang con trái hoặc con phải. |
| **Cây tìm kiếm nhị phân (BST)** | Nút con trái < nút cha, nút con phải > nút cha. | Cần lưu trữ dữ liệu động, luôn giữ thứ tự sắp xếp và tìm kiếm nhanh. | So sánh khóa tìm kiếm với nút hiện tại: Nhỏ hơn $\rightarrow$ rẽ trái; Lớn hơn $\rightarrow$ rẽ phải ($O(\log n)$ time). |
| **Cây cân bằng (AVL / Đỏ-Đen)** | Tự động xoay cây để giữ chiều cao cân bằng tối ưu, tránh bị thoái hóa. | Dữ liệu đầu vào cực kỳ lớn, ngẫu nhiên hoặc có thể bị sắp xếp sẵn. | Cam kết các thao tác tìm kiếm, chèn, xóa luôn ở mức $O(\log n)$ trong mọi trường hợp. |
| **Cây tiền tố (Trie)** | Mỗi nút đại diện cho một ký tự, các từ có chung tiền tố sẽ chia sẻ chung nhánh. | Bài toán về chuỗi: Gợi ý từ khóa (Auto-complete), kiểm tra chính tả, tìm kiếm từ điển. | Đi từ Root dọc theo từng ký tự của từ cần tìm. Tốc độ tìm kiếm $O(L)$ với $L$ là độ dài từ. |
| **Segment / Fenwick Tree** | Cây quản lý thông tin các đoạn/khoảng của một mảng số. | Đề bài yêu cầu cập nhật phần tử và truy vấn tổng/min/max đoạn $[L, R]$ với tần suất lớn. | Cập nhật và truy vấn đoạn trong thời gian $O(\log n)$ thay vì duyệt mảng tuần tự $O(n)$. |

#### 🔄 Quy tắc duyệt cây (Tree Traversal) - Cốt lõi phỏng vấn
Để duyệt qua toàn bộ các nút của cây, ta có hai phương pháp:

1.  **Duyệt theo chiều sâu (DFS):**
    *   **Pre-order (NLR - Duyệt trước):** Thăm nút cha $\rightarrow$ duyệt con trái $\rightarrow$ duyệt con phải.
        *   *Phỏng vấn trả lời:* Dùng để **sao chép cây, tạo biểu thức tiền tố**.
    *   **In-order (LNR - Duyệt giữa):** Duyệt con trái $\rightarrow$ thăm nút cha $\rightarrow$ duyệt con phải.
        *   *Phỏng vấn trả lời:* Dùng để **duyệt cây BST theo thứ tự tăng dần**.
    *   **Post-order (LRN - Duyệt sau):** Duyệt con trái $\rightarrow$ duyệt con phải $\rightarrow$ thăm nút cha.
        *   *Phỏng vấn trả lời:* Dùng để **xóa cây (xóa con trước khi xóa cha) hoặc tính kích thước thư mục**.
2.  **Duyệt theo chiều rộng (BFS / Level-order):** Duyệt theo từng tầng từ trên xuống, trái sang phải (sử dụng Hàng đợi - Queue).
    *   *Phỏng vấn trả lời:* Dùng để **tìm nút gần gốc nhất, duyệt cây theo khoảng cách**.

---

### 3.2. Bảng băm (Hash Table) & Xử lý Xung đột mã băm (Hash Collision)

*   **Định nghĩa:** Bảng băm là cấu trúc dữ liệu lưu trữ dạng cặp `Key-Value`, sử dụng hàm băm (Hash Function) để chuyển đổi Key thành một chỉ số mảng giúp truy xuất trực tiếp dữ liệu trong $O(1)$.
*   **Xung đột mã băm là gì?**
    Xảy ra khi hai Key khác nhau chạy qua hàm băm cho ra cùng một chỉ số index trong mảng (`hash(Key1) == hash(Key2)`).
    *Nguyên nhân:* Do số lượng Key có thể có là vô hạn, trong khi kích thước mảng lưu trữ là hữu hạn (Nguyên lý chuồng bồ câu).

#### Bảng so sánh các kỹ thuật giải quyết xung đột băm

| Phương pháp giải quyết | Cơ chế hoạt động ngắn gọn | Ưu điểm (Pros) | Nhược điểm (Cons) | Hiệu năng khi xung đột cao |
| :--- | :--- | :--- | :--- | :--- |
| **Separate Chaining (Dùng danh sách liên kết)** | Mỗi ô nhớ của mảng là một đầu danh sách liên kết. Nếu trùng băm, chỉ việc chèn thêm Node mới vào cuối danh sách liên kết đó. | * Đơn giản, bảng băm không bao giờ bị đầy thực sự.<br>* Dễ dàng xóa phần tử. | * Tốn thêm bộ nhớ lưu con trỏ.<br>* Hiệu năng cache CPU kém do dữ liệu nằm rải rác trên RAM. | Tìm kiếm từ $O(1)$ thoái hóa thành $O(n)$. *(Trong Java 8+, danh sách liên kết này tự động nâng cấp thành **Cây đỏ-đen** khi độ dài $\ge 8$ để giữ hiệu năng $O(\log n)$)*. |
| **Open Addressing: Linear Probing (Dò tuyến tính)** | Khi ô bị trùng băm, dò tìm tuần tự sang ô nhớ trống tiếp theo ngay bên cạnh: `index = (index + 1) % size`. | * Tiết kiệm bộ nhớ (không dùng con trỏ).<br>* Hiệu năng cache CPU cực tốt do dữ liệu nằm liên tục. | Xảy ra lỗi **Primary Clustering**: Các ô nhớ bị lấp đầy liên tiếp tạo thành các cụm lớn, tăng số lần dò tìm. | Hiệu năng giảm mạnh khi bảng băm đạt tải trọng cao (Load Factor lớn) vì phải dò qua hàng loạt ô kề nhau. |
| **Open Addressing: Quadratic Probing (Dò bậc hai)** | Dò tìm ô trống bằng cách tăng khoảng cách nhảy theo bình phương: `index = (index + i^2) % size`. | Giảm bớt hiện tượng Primary Clustering của dò tuyến tính. | Gặp hiện tượng **Secondary Clustering** (các phần tử có cùng mã băm ban đầu vẫn sẽ đi theo cùng lộ trình dò tìm). | Tốt hơn dò tuyến tính nhưng vẫn bị nghẽn khi Load Factor của bảng băm tăng cao. |
| **Open Addressing: Double Hashing (Băm kép)** | Sử dụng hàm băm thứ hai $hash_2(Key)$ để tính toán bước nhảy ô: `index = (index + i * hash_2(Key)) % size`. | Khắc phục hoàn toàn hiện tượng tụ tập cụm, phân phối các phần tử đều khắp bảng băm. | Tốn chi phí tính toán 2 hàm băm khác nhau cho mỗi lần xảy ra xung đột. | Đạt hiệu năng tốt nhất, đồng đều nhất trong các phương pháp địa chỉ mở khi xảy ra xung đột cao. |

