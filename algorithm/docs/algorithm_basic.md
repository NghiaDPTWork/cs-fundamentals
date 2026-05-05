# Tổng quan về Cấu trúc dữ liệu và Giải thuật (DSA)

## 1. Định nghĩa Cấu trúc dữ liệu và Giải thuật

### 1.1. Cấu trúc dữ liệu (Data Structure)

Cấu trúc dữ liệu là một cách lưu trữ và tổ chức dữ liệu trong máy tính sao cho chúng ta có thể sử dụng dữ liệu đó một cách hiệu quả. Nó không chỉ đơn thuần là các giá trị dữ liệu mà còn bao gồm mối quan hệ giữa chúng và các thao tác (hàm, phép toán) có thể thực hiện trên dữ liệu đó.

- **Ví dụ:** Mảng (Array), Danh sách liên kết (Linked List), Ngăn xếp (Stack), Hàng đợi (Queue).

### 1.2. Giải thuật (Algorithm)

Giải thuật là một tập hợp các hướng dẫn hữu hạn, rõ ràng, có thể thực hiện được nhằm giải quyết một bài toán cụ thể hoặc thực hiện một nhiệm vụ nào đó. Một giải thuật nhận vào một giá trị hoặc một tập các giá trị làm đầu vào (Input) và trả về một giá trị hoặc tập các giá trị làm đầu ra (Output).

- **Ví dụ:** Thuật toán sắp xếp (Sorting), thuật toán tìm kiếm (Searching).

### 1.3. Mối quan hệ giữa DSA

Niklaus Wirth, một nhà khoa học máy tính nổi tiếng, đã đưa ra công thức kinh điển:
**"Algorithms + Data Structures = Programs"**
Cấu trúc dữ liệu đóng vai trò là "nguyên liệu" và "khung xương", trong khi giải thuật là "phương pháp" để xử lý các nguyên liệu đó nhằm đạt được kết quả mong muốn.

---

## 2. Tại sao cần hiểu rõ DSA?

Việc nắm vững DSA là nền tảng cốt lõi của một lập trình viên giỏi vì những lý do sau:

- **Tối ưu hóa hiệu năng (Efficiency):** Đối với các ứng dụng xử lý dữ liệu lớn, việc chọn sai cấu trúc dữ liệu hoặc thuật toán có thể khiến ứng dụng chạy chậm hàng trăm lần hoặc gây tràn bộ nhớ. DSA giúp chúng ta chọn ra giải pháp nhanh nhất và tiết kiệm tài nguyên nhất.
- **Giải quyết các bài toán phức tạp:** Nhiều vấn đề trong lập trình không thể giải quyết bằng cách thủ công. DSA cung cấp các khuôn mẫu (patterns) đã được chứng minh hiệu quả để giải quyết các vấn đề như tìm đường đi ngắn nhất, tối ưu hóa lịch trình, v.v.
- **Tư duy logic và giải quyết vấn đề:** Học DSA giúp rèn luyện khả năng phân tích vấn đề, chia nhỏ bài toán và tư duy một cách có hệ thống.
- **Tiêu chuẩn tuyển dụng:** Tại các công ty công nghệ lớn (Big Tech), DSA là nội dung bắt buộc trong các vòng phỏng vấn kỹ thuật để đánh giá tư duy và khả năng tối ưu mã nguồn của ứng viên.

---

## 3. Độ phức tạp thuật toán (Big O Notation)

Big O Notation là công cụ toán học dùng để mô tả hiệu suất hoặc độ phức tạp của một thuật toán khi kích thước đầu vào ($n$) tiến tới vô hạn. Nó tập trung vào trường hợp xấu nhất (Worst-case).

### 3.1. Các mức độ phức tạp phổ biến (Từ tốt nhất đến tệ nhất)

1. **$O(1)$ - Constant Time (Thời gian hằng số)**
   <details>
   <summary>Tại sao truy cập mảng là O(1) và các phép toán O(1) gồm những gì?</summary>
   
   - **Bản chất:** Một thuật toán là $O(1)$ khi thời gian thực thi của nó không phụ thuộc vào kích thước dữ liệu đầu vào.
   - **Tại sao truy cập mảng là $O(1)$?** Trong bộ nhớ, mảng là một khối ô nhớ liên tiếp. Máy tính sử dụng công thức toán học: `Địa chỉ ô nhớ = Địa chỉ bắt đầu + (chỉ số index * kích thước 1 phần tử)`. Đây là một phép toán số học đơn giản và tốn thời gian như nhau dù mảng có 10 hay 1 tỷ phần tử.
   - **Các phép toán $O(1)$ thường gặp:**
     - Các phép toán số học (+, -, *, /) với các số có kích thước cố định.
     - Gán giá trị cho biến.
     - Truy cập một phần tử trong mảng qua index hoặc một key trong Hash Table.
     - Thêm/Xóa phần tử ở đầu/cuối của Stack hoặc Queue.
   </details>

2. **$O(\log n)$ - Logarithmic Time (Thời gian logarit)**
   <details>
   <summary>Tại sao Logarit lại tối ưu và bản chất toán học là gì?</summary>
   
   - **Bản chất:** Logarit là phép toán ngược của lũy thừa. Nếu $2^x = n$ thì $x = \log_2 n$. Trong khoa học máy tính, $O(\log n)$ thường xuất hiện khi bài toán được **chia đôi** sau mỗi bước xử lý.
   - **Sứ mệnh ra đời:** Logarit ra đời để giải quyết bài toán "bùng nổ" dữ liệu. Khi dữ liệu tăng theo cấp số nhân (ví dụ: dân số, giao dịch ngân hàng), nếu ta vẫn dùng cách tiếp cận tuyến tính ($O(n)$), hệ thống sẽ sụp đổ vì không đủ thời gian xử lý.
   - **Khắc phục điều gì?** Nó khắc phục sự kém hiệu quả của việc "duyệt từng phần tử". Thay vì kiểm tra từng cái một, Logarit cho phép ta **loại bỏ hàng loạt** dữ liệu không liên quan chỉ trong một bước.
   - **Tại sao nó tối ưu?** Hãy tưởng tượng bạn có 1 triệu phần tử. Với $O(n)$, bạn cần 1 triệu bước. Với $O(\log n)$, bạn chỉ cần khoảng 20 bước ($\log_2 1,000,000 \approx 20$). Sự khác biệt là cực kỳ khổng lồ: dữ liệu tăng gấp triệu lần nhưng thời gian xử lý chỉ tăng vài chục lần.
   - **Ví dụ điển hình:** Binary Search (Tìm kiếm nhị phân). Mỗi lần so sánh, bạn loại bỏ được một nửa số lượng phần tử cần tìm.
   </details>

3. **$O(n)$ - Linear Time (Thời gian tuyến tính)**
   <details>
   <summary>Tại sao 10n hay 100n đều được coi là O(n)?</summary>
   
   - **Bản chất:** Thời gian thực thi tỉ lệ thuận với kích thước đầu vào. Nếu dữ liệu tăng gấp đôi, thời gian chạy cũng tăng gấp đôi.
   - **Vấn đề 10n vs 100n:** Trong Big O, chúng ta **bỏ qua các hằng số**. Lý do là Big O quan tâm đến **tốc độ tăng trưởng** (growth rate) khi $n$ tiến tới vô cực. Khi $n$ cực lớn (hàng tỷ), sự khác biệt về hình dạng đường cong giữa $n, 10n$ và $100n$ đều là đường thẳng. Hằng số chỉ làm đường thẳng dốc hơn nhưng không thay đổi bản chất "tuyến tính" của nó.
   - **Ví dụ:** Vòng lặp đơn duyệt qua mảng, tìm kiếm tuần tự.
   </details>

4. **$O(n \log n)$ - Linearithmic Time**
   <details>
   <summary>Sự kết hợp giữa tuyến tính và chia để trị</summary>
   
   - **Bản chất:** Đây là sự kết hợp khi bạn thực hiện một thao tác $O(\log n)$ cho mỗi phần tử trong số $n$ phần tử đầu vào (hoặc ngược lại).
   - **Tầm quan trọng:** Đây là độ phức tạp của các thuật toán sắp xếp tối ưu nhất hiện nay trong thực tế.
   - **Ví dụ:** Merge Sort, Quick Sort (trung bình), Heap Sort.
   </details>

5. **$O(n^2)$ - Quadratic Time (Thời gian bình phương)**
   <details>
   <summary>Hiểm họa của các vòng lặp lồng nhau</summary>
   
   - **Bản chất:** Thời gian thực thi tỉ lệ thuận với bình phương kích thước đầu vào. Nếu $n=10$, tốn 100 bước. Nếu $n=100$, tốn 10,000 bước.
   - **Ví dụ:** Hai vòng lặp lồng nhau duyệt qua cùng một mảng để so sánh từng cặp phần tử. Các thuật toán sắp xếp đơn giản như Bubble Sort, Selection Sort.
   </details>

6. **$O(2^n)$ - Exponential Time (Thời gian mũ)**
   <details>
   <summary>Sự bùng nổ dữ liệu - "Ác mộng" của lập trình viên</summary>
   
   - **Bản chất:** Thời gian thực thi tăng gấp đôi sau mỗi lần thêm một phần tử đầu vào. Thuật toán này sẽ "đóng băng" máy tính rất nhanh ngay cả với $n$ nhỏ (khoảng 40-50).
   - **Ví dụ:** Giải bài toán tháp Hà Nội, tìm tất cả các tập con của một tập hợp, hoặc đệ quy tính số Fibonacci mà không dùng bộ nhớ đệm (memoization).
   </details>

---

## 4. Giới thiệu về Cấu trúc dữ liệu

Cấu trúc dữ liệu được chia làm hai loại chính:

### 4.1. Cấu trúc dữ liệu tuyến tính (Linear Data Structures)

Các phần tử được sắp xếp theo một thứ tự tuần tự. Mỗi loại được sinh ra để tối ưu cho các mục đích truy xuất hoặc chỉnh sửa khác nhau:

- **Array (Mảng)**
  <details>
  <summary>Thế mạnh về truy xuất ngẫu nhiên và lưu trữ cố định</summary>
  
  - **Tối ưu cho:** **Truy xuất (Access)**. Nhờ các ô nhớ liên tiếp, bạn có thể nhảy tới bất kỳ vị trí nào ngay lập tức ($O(1)$).
  - **Điểm yếu:** Chỉnh sửa (Thêm/Xóa). Khi bạn thêm hoặc xóa ở giữa mảng, tất cả phần tử phía sau phải "dịch hàng", cực kỳ tốn kém ($O(n)$).
  - **Sinh ra để làm gì?** Để quản lý danh sách có số lượng phần tử cố định hoặc ít thay đổi, nơi việc đọc dữ liệu là ưu tiên hàng đầu.
  </details>

- **Linked List (Danh sách liên kết)**
  <details>
  <summary>Thế mạnh về chỉnh sửa linh hoạt và bộ nhớ động</summary>
  
  - **Tối ưu cho:** **Chỉnh sửa (Insertion/Deletion)**. Để thêm hoặc xóa, bạn chỉ cần thay đổi "mối nối" (con trỏ) của các node bên cạnh ($O(1)$ nếu đã biết vị trí).
  - **Điểm yếu:** Truy xuất. Bạn không thể nhảy cóc tới phần tử thứ 100 mà phải đi bộ từ phần tử đầu tiên ($O(n)$).
  - **Sinh ra để làm gì?** Dành cho các danh sách có kích thước thay đổi liên tục, nơi bộ nhớ không cần phải liên tiếp nhau.
  </details>

- **Stack (Ngăn xếp - LIFO)**
  <details>
  <summary>Quản lý lịch sử và các tác vụ lồng nhau</summary>
  
  - **Cơ chế:** "Vào sau, ra trước" (Last-In-First-Out).
  - **Tối ưu cho:** Quản lý trạng thái hiện tại và quay lại trạng thái trước đó.
  - **Ứng dụng:** Chức năng Undo/Redo trong soạn thảo, nút "Back" trên trình duyệt, hoặc quản lý lời gọi hàm (Call Stack) trong lập trình.
  - **Sinh ra để làm gì?** Để giải quyết các bài toán có tính chất "đệ quy" hoặc cần tạm dừng việc này để làm việc kia rồi quay lại.
  </details>

- **Queue (Hàng đợi - FIFO)**
  <details>
  <summary>Đảm bảo tính công bằng và xử lý theo thứ tự</summary>
  
  - **Cơ chế:** "Vào trước, ra trước" (First-In-First-Out).
  - **Tối ưu cho:** Xử lý các tác vụ theo đúng thứ tự xuất hiện.
  - **Ứng dụng:** Hàng đợi in ấn, quản lý yêu cầu (request) gửi đến server, thuật toán tìm kiếm theo chiều rộng (BFS).
  - **Sinh ra để làm gì?** Để điều phối tài nguyên và đảm bảo mọi yêu cầu đều được phục vụ theo nguyên tắc "đến trước phục vụ trước".
  </details>

### 4.2. Cấu trúc dữ liệu phi tuyến tính (Non-Linear Data Structures)

Các phần tử không được sắp xếp theo tuần tự mà theo phân cấp hoặc mạng lưới, phản ánh các mối quan hệ phức tạp hơn trong thực tế:

- **Tree (Cây)**
  <details>
  <summary>Phân cấp dữ liệu và tối ưu hóa tìm kiếm</summary>
  
  - **Tối ưu cho:** Biểu diễn dữ liệu có tính **phân cấp** và **tìm kiếm nhanh** (đối với Cây tìm kiếm nhị phân - BST).
  - **Ứng dụng:** Cấu trúc thư mục máy tính, cây gia phả, cấu trúc DOM trong HTML, hoặc bộ định tuyến dữ liệu.
  - **Sinh ra để làm gì?** Để giải quyết bài toán truy xuất dữ liệu có tổ chức (như từ điển) nhanh hơn danh sách liên kết nhưng linh hoạt hơn mảng.
  - **Độ phức tạp:** Thường là $O(\log n)$ cho các thao tác cơ bản nếu cây cân bằng.
  </details>

- **Graph (Đồ thị)**
  <details>
  <summary>Mô hình hóa mạng lưới và các mối quan hệ phức tạp</summary>
  
  - **Tối ưu cho:** Biểu diễn các **kết nối** (connection) giữa các thực thể.
  - **Ứng dụng:** Mạng xã hội (bạn bè), bản đồ giao thông (đường đi ngắn nhất), mạng lưới internet, hệ thống gợi ý của Netflix/Amazon.
  - **Sinh ra để làm gì?** Để mô tả thế giới thực nơi mọi thứ không chỉ đứng cạnh nhau mà còn liên kết chéo với nhau theo nhiều cách.
  - **Thuật toán đi kèm:** Tìm đường đi ngắn nhất (Dijkstra), duyệt mạng lưới (BFS, DFS).
  </details>

---

## 5. Giới thiệu về Giải thuật

Một giải thuật tốt thường phải đảm bảo các tính chất: Tính hữu hạn, Tính xác định, Tính đầu vào/đầu ra và Tính hiệu quả.

### 5.1. Các nhóm giải thuật phổ biến

### 5.1. Các nhóm giải thuật phổ biến

Mỗi nhóm giải thuật đại diện cho một tư duy giải quyết vấn đề khác nhau. Dưới đây là sự phân biệt và mô hình hoạt động của chúng:

- **Tìm kiếm (Searching)**
  <details>
  <summary>Phân biệt Linear Search và Binary Search</summary>
  
  - **Linear Search (Tìm kiếm tuần tự):** 
    - *Mô hình:* Duyệt từ đầu đến cuối danh sách. 
    - *Quy trình (Flow):*
      1. Bắt đầu từ phần tử đầu tiên (index 0).
      2. So sánh phần tử hiện tại với giá trị cần tìm.
      3. Nếu khớp: Trả về vị trí và kết thúc.
      4. Nếu không khớp: Chuyển sang phần tử tiếp theo.
      5. Lặp lại cho đến hết danh sách. Nếu không tìm thấy, trả về -1.
  - **Binary Search (Tìm kiếm nhị phân):**
    - *Mô hình:* Luôn chia đôi tập dữ liệu đã sắp xếp để tìm kiếm.
    - *Quy trình (Flow):*
      1. Đặt `Trái = 0`, `Phải = n - 1`.
      2. Tính `Giữa = (Trái + Phải) / 2`.
      3. Nếu `Mảng[Giữa] == Giá trị`: Trả về `Giữa`.
      4. Nếu `Mảng[Giữa] < Giá trị`: Bỏ nửa trái, đặt `Trái = Giữa + 1`.
      5. Nếu `Mảng[Giữa] > Giá trị`: Bỏ nửa phải, đặt `Phải = Giữa - 1`.
      6. Lặp lại bước 2-5 cho đến khi `Trái > Phải`.
  </details>

- **Sắp xếp (Sorting)**
  <details>
  <summary>Sắp xếp đơn giản (O(n²)) vs Sắp xếp hiệu quả (O(n log n))</summary>
  
  - **Bubble Sort (Sắp xếp nổi bọt):** 
    - *Quy trình (Flow):* Duyệt mảng nhiều lần, tại mỗi lần duyệt, so sánh các cặp cạnh nhau và đẩy phần tử lớn nhất về cuối mảng như "nổi bọt".
  - **Merge Sort (Sắp xếp trộn):**
    - *Quy trình (Flow):* 
      1. Chia mảng hiện tại thành 2 nửa (Divide).
      2. Đệ quy sắp xếp từng nửa (Conquer).
      3. Trộn (Merge) 2 nửa đã sắp xếp thành mảng hoàn chỉnh.
  </details>

- **Đệ quy (Recursion) & Quy hoạch động (Dynamic Programming)**
  <details>
  <summary>Mối quan hệ giữa Đệ quy và "Bộ nhớ" của DP</summary>
  
  - **Recursion (Đệ quy):** 
    - *Quy trình (Flow):* 
      1. Kiểm tra Điều kiện dừng (Base case). Nếu đúng, trả về kết quả.
      2. Nếu không, thực hiện một phần công việc và gọi lại chính nó với bài toán nhỏ hơn.
      3. Kết hợp kết quả từ các lời gọi đệ quy.
  - **Dynamic Programming (DP):**
    - *Quy trình (Flow):* 
      1. Xác định bài toán con và trạng thái.
      2. Kiểm tra xem kết quả bài toán con đã có trong "Bảng nhớ" chưa.
      3. Nếu có: Lấy ra dùng ngay ($O(1)$).
      4. Nếu chưa: Tính toán, lưu vào bảng, rồi mới trả về.
  </details>

- **Tham lam (Greedy) vs Quy hoạch động (DP)**
  <details>
  <summary>Lựa chọn cục bộ vs Tối ưu toàn cục</summary>
  
  - **Greedy (Tham lam):** 
    - *Quy trình (Flow):* 
      1. Khởi tạo tập lời giải rỗng.
      2. Tại mỗi bước, chọn phần tử "tốt nhất" theo một tiêu chí nào đó (ví dụ: nhỏ nhất, lớn nhất).
      3. Kiểm tra xem phần tử đó có hợp lệ với lời giải không.
      4. Lặp lại cho đến khi đạt được mục tiêu.
  </details>

- **Duyệt đồ thị (Graph Traversal): BFS vs DFS**
  <details>
  <summary>Tìm kiếm theo chiều rộng vs Chiều sâu</summary>
  
  - **BFS (Tìm kiếm theo chiều rộng):**
    - *Quy trình (Flow):* 
      1. Đưa đỉnh bắt đầu vào một **Hàng đợi (Queue)** và đánh dấu đã thăm.
      2. Lấy một đỉnh ra khỏi Queue.
      3. Thăm tất cả các đỉnh kề chưa thăm của nó, đánh dấu đã thăm và đưa chúng vào Queue.
      4. Lặp lại bước 2-3 cho đến khi Queue rỗng.
  - **DFS (Tìm kiếm theo chiều sâu):**
    - *Quy trình (Flow):* 
      1. Đưa đỉnh bắt đầu vào một **Ngăn xếp (Stack)** hoặc dùng Đệ quy.
      2. Đánh dấu đã thăm đỉnh hiện tại.
      3. Đi tiếp tới một đỉnh kề chưa thăm (đi sâu nhất có thể).
      4. Nếu không còn đỉnh kề nào chưa thăm, quay lại (Backtrack) đỉnh trước đó.
  </details>
