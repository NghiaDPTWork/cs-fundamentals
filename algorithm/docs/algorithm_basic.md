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

1.  **$O(1)$ - Constant Time:** Thời gian thực hiện không đổi bất kể kích thước dữ liệu (Ví dụ: Truy cập phần tử mảng qua index).
2.  **$O(\log n)$ - Logarithmic Time:** Thời gian tăng theo hàm logarit (Ví dụ: Tìm kiếm nhị phân - Binary Search).
3.  **$O(n)$ - Linear Time:** Thời gian tăng tỉ lệ thuận với kích thước dữ liệu (Ví dụ: Tìm kiếm tuần tự - Linear Search).
4.  **$O(n \log n)$ - Linearithmic Time:** Thường thấy trong các thuật toán sắp xếp hiệu quả (Ví dụ: Merge Sort, Quick Sort).
5.  **$O(n^2)$ - Quadratic Time:** Thời gian tăng theo bình phương (Ví dụ: Bubble Sort, hai vòng lặp lồng nhau).
6.  **$O(2^n)$ - Exponential Time:** Thời gian tăng gấp đôi sau mỗi phần tử thêm vào (Ví dụ: Tính số Fibonacci bằng đệ quy chưa tối ưu).

---

## 4. Giới thiệu về Cấu trúc dữ liệu

Cấu trúc dữ liệu được chia làm hai loại chính:

### 4.1. Cấu trúc dữ liệu tuyến tính (Linear Data Structures)

Các phần tử được sắp xếp theo một thứ tự tuần tự.

- **Array:** Tập hợp các phần tử có cùng kiểu dữ liệu, nằm ở các ô nhớ liên tiếp.
- **Linked List:** Các phần tử (node) được liên kết với nhau bằng con trỏ.
- **Stack (LIFO):** Vào sau ra trước (như chồng đĩa).
- **Queue (FIFO):** Vào trước ra trước (như hàng đợi mua vé).

### 4.2. Cấu trúc dữ liệu phi tuyến tính (Non-Linear Data Structures)

Các phần tử không được sắp xếp theo tuần tự mà theo phân cấp hoặc mạng lưới.

- **Tree:** Cấu trúc phân cấp (Ví dụ: Cây nhị phân, Cây tìm kiếm nhị phân).
- **Graph:** Tập hợp các đỉnh (nodes) và các cạnh (edges) nối giữa chúng.

---

## 5. Giới thiệu về Giải thuật

Một giải thuật tốt thường phải đảm bảo các tính chất: Tính hữu hạn, Tính xác định, Tính đầu vào/đầu ra và Tính hiệu quả.

### 5.1. Các nhóm giải thuật phổ biến

- **Tìm kiếm (Searching):** Linear Search, Binary Search.
- **Sắp xếp (Sorting):** Bubble Sort, Insertion Sort, Merge Sort, Quick Sort.
- **Đệ quy (Recursion):** Giải quyết bài toán bằng cách gọi lại chính nó với bài toán nhỏ hơn.
- **Quy hoạch động (Dynamic Programming):** Chia bài toán lớn thành các bài toán con và lưu trữ kết quả để tránh tính toán lại.
- **Tham lam (Greedy):** Luôn chọn phương án tốt nhất tại thời điểm hiện tại.
- **Duyệt đồ thị:** BFS (Tìm kiếm theo chiều rộng), DFS (Tìm kiếm theo chiều sâu).
