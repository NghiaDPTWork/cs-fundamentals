# Tổng quan về Cấu trúc dữ liệu và Giải thuật (DSA)

> **Tóm tắt cốt lõi:**
> - **DSA (Data Structures & Algorithms):** Là nghệ thuật tổ chức dữ liệu và áp dụng phương pháp xử lý tối ưu nhất để máy tính truy xuất, thao tác thông tin nhanh nhất, tiết kiệm bộ nhớ nhất.
> - **Big O (Độ phức tạp):** Thước đo hiệu năng. $O(1)$ (siêu tốc), $O(\log n)$ (tăng chậm), $O(n)$ (tăng đều), $O(n^2)$ (cần tránh khi dữ liệu lớn).
> - **Data Structures:** Vùng chứa. Array (liền kề, truy cập nhanh), Linked List (kết nối động, chèn dễ), Stack/Queue (vào trước/sau), Hash Table (truy xuất cực đại).
> - **Algorithms:** Cách giải. Brute Force (thử mọi cách), Greedy (chọn cái tốt nhất ngay), Divide & Conquer (chia để trị).

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

### 2.1. Ứng dụng thực tế của DSA (Trong đời sống số)

Bạn tương tác với DSA mỗi giây mà không hề hay biết:
*   **Mạng xã hội (Facebook/TikTok):** Sử dụng **Đồ thị (Graph)** để quản lý các mối quan hệ bạn bè, thuật toán lọc nội dung tối ưu hóa cho bạn xem video bạn thích nhất (**Greedy** + Machine Learning).
*   **Google Maps:** Sử dụng **Đồ thị** và thuật toán **Dijkstra (BFS cải tiến)** để tìm con đường ngắn nhất từ nhà đến công ty bạn chỉ trong vài mili giây.
*   **Tìm kiếm trên Google:** Sử dụng **Bảng băm (Hash Table)** và các dạng Cây (Tree) khổng lồ để chỉ mục hàng tỷ website, trả về kết quả ngay khi bạn vừa gõ xong.
*   **Chức năng Undo (Ctrl + Z):** Tất cả các trình soạn thảo đều dùng **Ngăn xếp (Stack)** để "lưu chồng" lịch sử hành động, cho phép "bốc" hành động cuối ra hủy bỏ.

---

## 3. Độ phức tạp thuật toán (Big O Notation)

Big O Notation là công cụ toán học dùng để mô tả hiệu suất hoặc độ phức tạp của một thuật toán khi kích thước đầu vào ($n$) tiến tới vô hạn. Nó tập trung vào trường hợp xấu nhất (Worst-case).

### 3.1. Các mức độ phức tạp phổ biến (Từ tốt nhất đến tệ nhất)

1. **$O(1)$ - Constant Time (Thời gian hằng số)**
   <details>
   <summary>Tại sao truy cập mảng là O(1) và các phép toán O(1) gồm những gì?</summary>
   
   - **Bản chất:** Một thuật toán là $O(1)$ khi thời gian thực thi của nó không đổi, không phụ thuộc vào việc bạn truyền vào 1 phần tử hay 1 triệu phần tử.
   - **CHỨNG MINH TRỰC QUAN: Tại sao truy cập mảng là $O(1)$?**
     Giả sử ta có một mảng số nguyên (mỗi số chiếm 4 byte). Máy tính lưu mảng này tại ô nhớ bắt đầu từ địa chỉ **1000**.
     
     Nếu ta muốn lấy phần tử tại vị trí `index = 500`. Thay vì đếm từ 1 tới 500, CPU chỉ thực hiện **Duy nhất 1 phép toán số học**:
     $$ \text{Địa chỉ cần tìm} = 1000 + (500 \times 4) = 3000 $$
     
     Dù index là 5, hay index là 50 triệu, CPU cũng chỉ mất đúng **1 chu kỳ thực hiện phép tính nhân và cộng** này để "nhảy" ngay lập tức tới địa chỉ bộ nhớ chứa dữ liệu. Đây là tốc độ ánh sáng trong máy tính!
   - **Các phép toán $O(1)$ thường gặp:**
     - Các phép toán số học cơ bản (+, -, *, /) trên các biến số.
     - Đọc giá trị từ một biến đã biết địa chỉ.
     - Truy cập mảng theo chỉ số `arr[i]` hoặc `myMap.get(key)`.
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
  - **VÍ DỤ THỰC TẾ DỄ HIỂU:**
    - **Bảng điểm lớp học:** Danh sách 40 học sinh cố định, bạn muốn biết điểm học sinh số 23 ngay lập tức.
    - **Bảng màu pixel của một bức ảnh:** Một lưới cố định kích thước $1920 \times 1080$, mỗi điểm ảnh nằm nguyên một vị trí không di dịch.
  </details>

- **Linked List (Danh sách liên kết)**
  <details>
  <summary>Thế mạnh về chỉnh sửa linh hoạt và bộ nhớ động</summary>
  
  - **Tối ưu cho:** **Chỉnh sửa (Insertion/Deletion)**. Để thêm hoặc xóa, bạn chỉ cần thay đổi "mối nối" (con trỏ) của các node bên cạnh ($O(1)$ nếu đã biết vị trí).
  - **Điểm yếu:** Truy xuất. Bạn không thể nhảy cóc tới phần tử thứ 100 mà phải đi bộ từ phần tử đầu tiên ($O(n)$).
  - **VÍ DỤ THỰC TẾ DỄ HIỂU:**
    - **Danh sách phát nhạc (Music Playlist):** Bạn đang nghe bài A, nhấn "Next" để đi tới Node chứa bài B, nhấn "Prev" quay lại bài A qua các con trỏ liên kết. Việc chèn thêm bài hát mới vào giữa playlist cực kỳ dễ dàng mà không cần sắp xếp lại ổ đĩa.
    - **Lướt Newsfeed (Vô tận):** Cho phép liên tục "nối" thêm các bài viết mới tải từ server vào cuối danh sách mà không cần xin hệ điều hành một vùng nhớ khổng lồ liền mạch ngay từ đầu.
  </details>

- **Stack (Ngăn xếp - LIFO)**
  <details>
  <summary>Quản lý lịch sử và các tác vụ lồng nhau</summary>
  
  - **Cơ chế:** "Vào sau, ra trước" (Last-In-First-Out). Giống như xếp chồng đĩa, chiếc đĩa đặt cuối cùng sẽ là chiếc lấy ra đầu tiên.
  - **Tối ưu cho:** Quản lý trạng thái hiện tại và quay lại quá khứ.
  - **VÍ DỤ THỰC TẾ DỄ HIỂU:**
    - **Nút Back trên trình duyệt:** Mỗi lần bạn vào link mới, trang đó được "đè" lên đỉnh stack. Nhấn Back, trình duyệt "bốc" trang trên cùng vứt đi để hiện ra trang bên dưới.
    - **Công cụ Undo (Ctrl + Z):** Mỗi chữ bạn gõ được lưu vào Stack. Nhấn Ctrl+Z sẽ lấy hành động cuối cùng ra để xóa bỏ nó.
  </details>

- **Queue (Hàng đợi - FIFO)**
  <details>
  <summary>Đảm bảo tính công bằng và xử lý theo thứ tự</summary>
  
  - **Cơ chế:** "Vào trước, ra trước" (First-In-First-Out). Giống như xếp hàng mua vé buffet, ai đến trước được ăn trước.
  - **Tối ưu cho:** Xử lý các tác vụ theo đúng thứ tự xuất hiện, điều phối tài nguyên.
  - **VÍ DỤ THỰC TẾ DỄ HIỂU:**
    - **Đặt vé online/Săn Sale Shopee:** Hệ thống dùng Queue để đưa hàng nghìn user ấn nút mua vào một hàng chờ. Đơn hàng nào tới Server trước 0.001s sẽ được xử lý trừ kho trước.
    - **Lệnh in (Printer Queue):** Cả công ty ấn in cùng lúc, máy in sẽ in tài liệu của người nhấn lệnh đầu tiên rồi mới sang người thứ hai.
  </details>

- **Hash Table (Bảng băm - Cặp Key/Value)**
  <details>
  <summary>Vua tốc độ trong việc tra cứu dữ liệu</summary>
  
  - **Cơ chế:** Dùng một hàm băm (Hash Function) để biến một "Khóa" (như "Tên người") thành địa chỉ ô nhớ, cất dữ liệu trực tiếp vào ô đó.
  - **Tối ưu cho:** Tìm kiếm cực nhanh không cần duyệt ($O(1)$).
  - **VÍ DỤ THỰC TẾ DỄ HIỂU:**
    - **Từ điển Anh - Việt:** Gõ từ "Hello" (Key), lập tức tìm ra nghĩa "Xin chào" (Value) mà không cần lật từng trang từ đầu quyển sách tới cuối.
    - **Danh bạ điện thoại:** Tìm theo tên "Mẹ" phát hiện ra số điện thoại ngay lập tức.
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
  <summary>Phân biệt Linear Search và Binary Search (Kèm Code)</summary>
  
  ##### Linear Search Code & Flow
  ```java
  public int linearSearch(int[] arr, int x) {
      for (int i = 0; i < arr.length; i++) { // Đi từ trái qua phải
          if (arr[i] == x) return i; // Khớp là trả về ngay
      }
      return -1; // Đi hết vẫn không thấy
  }
  ```
  *   **Mô phỏng chạy:** Cho mảng `[1, 5, 8]`, tìm số `8`.
      1. `i=0`: Kiểm tra số `1`. Sai $\rightarrow$ sang phải.
      2. `i=1`: Kiểm tra số `5`. Sai $\rightarrow$ sang phải.
      3. `i=2`: Kiểm tra số `8`. **ĐÚNG** $\rightarrow$ Trả về chỉ số 2. Kết thúc.

  ##### Binary Search Code (Chia để trị)
  ```java
  public int binarySearch(int[] arr, int x) {
      int L = 0, R = arr.length - 1;
      while (L <= R) {
          int M = L + (R - L)/2;
          if (arr[M] == x) return M; 
          if (arr[M] < x) L = M + 1; // Bỏ nửa trái
          else R = M - 1;            // Bỏ nửa phải
      }
      return -1;
  }
  ```
  </details>

- **Sắp xếp (Sorting)**
  <details>
  <summary>Sắp xếp đơn giản (O(n²)) vs Sắp xếp hiệu quả (O(n log n))</summary>
  
  ##### Bubble Sort Code (O(N²))
  ```java
  public void bubbleSort(int[] arr) {
      int n = arr.length;
      for (int i = 0; i < n-1; i++)
          for (int j = 0; j < n-i-1; j++)
              if (arr[j] > arr[j+1]) { // Cặp đứng sai chỗ?
                  // Đổi chỗ (Swap) để đẩy số lớn về sau
                  int temp = arr[j];
                  arr[j] = arr[j+1];
                  arr[j+1] = temp;
              }
  }
  ```
  *   **Mô phỏng chạy:** Cho mảng `[5, 1, 4]`.
      1. Lần quét 1: So sánh 5 & 1 $\rightarrow$ Đổi chỗ thành `[1, 5, 4]`.
      2. Tiếp: So sánh 5 & 4 $\rightarrow$ Đổi chỗ thành `[1, 4, 5]`. Số 5 "nổi" về cuối. Xong!
  </details>

- **Đệ quy (Recursion) & Quy hoạch động (Dynamic Programming)**
  <details>
  <summary>Mối quan hệ giữa Đệ quy và "Bộ nhớ" của DP (Kèm Code)</summary>
  
  ##### Đệ quy tính Giai thừa (Factorial)
  ```java
  public int factorial(int n) {
      if (n == 1) return 1; // Điểm dừng
      return n * factorial(n - 1); // Tự gọi lại chính mình
  }
  ```
  *   **Mô phỏng chạy:** `factorial(3)`
      1. Tính `3 * factorial(2)`.
      2. Tính `2 * factorial(1)`.
      3. `factorial(1)` chạm đáy $\rightarrow$ Trả về 1.
      4. Ngược dòng: $2 \times 1 = 2$, sau đó $3 \times 2 = 6$. Xong!

  ##### Quy hoạch động (DP - Có ghi nhớ Memoization)
  ```java
  // Tính Fibonacci số thứ n bằng mảng lưu trữ 'memo'
  public int fibDP(int n, int[] memo) {
      if (memo[n] != -1) return memo[n]; // ĐÃ TÍNH RỒI? Lấy ra dùng ngay (O(1))
      memo[n] = fibDP(n - 1, memo) + fibDP(n - 2, memo); // Tính & Cất vào tủ
      return memo[n];
  }
  ```
  </details>

- **Tham lam (Greedy) vs Quy hoạch động (DP)**
  <details>
  <summary>Lựa chọn cục bộ vs Tối ưu toàn cục (Kèm Code)</summary>
  
  ##### Tham lam (Greedy) - Đổi tiền 
  ```java
  public void greedyCoinChange(int money) {
      int[] coins = {50, 20, 10, 5};
      for (int coin : coins) {
          int sl = money / coin; // CỨ CHỌN XU TO NHẤT NGAY LẬP TỨC
          System.out.println("Trả " + sl + " tờ mệnh giá " + coin);
          money %= coin;
      }
  }
  ```
  *   **Mô phỏng chạy:** Đổi 75 đồng.
      1. Nhặt tờ 50 lớn nhất $\rightarrow$ Cần 1 tờ. Còn thừa 25 đồng.
      2. Nhặt tờ 20 tiếp theo $\rightarrow$ Cần 1 tờ. Còn thừa 5 đồng.
      3. Nhặt tờ 5 $\rightarrow$ Cần 1 tờ. Hoàn tất siêu tốc.
  </details>

- **Duyệt đồ thị (Graph Traversal): BFS vs DFS**
  <details>
  <summary>Tìm kiếm theo chiều rộng (Loang) vs Chiều sâu (Kèm Code)</summary>
  
  ##### BFS (Dùng Hàng đợi - Queue) - Như vết loang dầu
  ```java
  public void bfs(Node root) {
      Queue<Node> q = new LinkedList<>();
      q.add(root); // Đưa gốc vào hàng đợi
      while (!q.isEmpty()) {
          Node current = q.poll(); // Lấy người đứng đầu ra
          System.out.print(current.val + " "); 
          q.addAll(current.children); // Đẩy tất cả HÀNG XÓM TRỰC TIẾP vào hàng đợi
      }
  }
  ```
  *   **Dấu ấn tư duy:** Thăm hết tầng 1 mới nhảy xuống tầng 2. Phù hợp tìm đường ngắn nhất.

  ##### DFS (Dùng Đệ quy/Stack) - Như đi lạc trong mê cung
  ```java
  public void dfs(Node node) {
      if (node == null) return;
      System.out.print(node.val + " "); // Thăm người này
      for (Node neighbor : node.neighbors) {
          dfs(neighbor); // ĐÂM SÂU VÀO NHÁNH NÀY MÃI CHO TỚI KHI HẾT ĐƯỜNG MỚI QUAY LẠI
      }
  }
  ```
  </details>
### 5.2. Phân tích chi tiết các Patterns phổ biến + Code mẫu minh họa

Dưới đây là kiến thức cốt lõi về các lối tư duy giải thuật (Patterns) phổ biến nhất trong thi đấu & phỏng vấn, kèm theo Code mẫu Java và giải thích luồng chạy (Flow).

---

#### 1. Vét cạn (Brute Force)
*   **Triết lý:** "Có công mài sắt, có ngày nên kim". Thử tất cả các khả năng có thể xảy ra cho đến khi tìm thấy kết quả đúng. Không cần suy nghĩ cao siêu, nhưng hiệu suất kém ($O(n^2)$).

##### Code Mẫu Kinh Điển (Two Sum bản thô)
```java
public int[] twoSumBruteForce(int[] nums, int target) {
    // Chạy vòng 1 lấy số thứ nhất
    for (int i = 0; i < nums.length; i++) {
        // Chạy vòng 2 lấy số thứ hai ở sau nó
        for (int j = i + 1; j < nums.length; j++) {
            // Kiểm tra tổng có bằng target không
            if (nums[i] + nums[j] == target) {
                return new int[] { i, j }; // Tìm thấy, trả về liền
            }
        }
    }
    return new int[] {};
}
```
*   **Lý giải luồng code (Flow):**
    1.  **Cố định:** Vòng lặp ngoài giữ chặt `nums[i]`.
    2.  **Quét:** Vòng lặp trong chạy qua toàn bộ các số còn lại phía sau.
    3.  **Tính toán:** Thử cộng từng cặp một.
    4.  **Đặc điểm:** Bắt mọi cặp phải "bắt tay" nhau một lần, cực kỳ tốn thời gian khi danh sách dài ra.

---

#### 2. Thuật toán Tham lam (Greedy)
*   **Triết lý:** "Ăn chắc mặc bền, cái gì ngon nhất ở hiện tại thì chọn luôn". Tại mỗi bước, chọn lựa phương án tốt nhất cục bộ với hy vọng nó sẽ dẫn đến kết quả tốt nhất toàn cục. Thường rất nhanh ($O(n)$).

##### Code Mẫu Kinh Điển (Đổi tiền - Giả sử mệnh giá chia hết cho nhau)
```java
public int countCoins(int money) {
    int[] denominations = {500, 200, 100, 50, 10}; // Các mệnh giá từ to đến nhỏ
    int coinCount = 0;

    for (int coin : denominations) {
        // Luôn ưu tiên nhặt đồng to nhất để giảm số lượng xu nhiều nhất có thể
        coinCount += money / coin; 
        money %= coin; // Phần tiền thừa còn lại sau khi đã đổi
    }
    return coinCount;
}
```
*   **Lý giải luồng code (Flow):**
    1.  **Sắp xếp:** Các cơ hội ngon nhất (đồng tiền to nhất) được xếp lên hàng đầu.
    2.  **Tham lam:** Code sẽ tự động "húp" trọn số tờ 500k nhiều nhất có thể.
    3.  **Rút ngắn bài toán:** Cập nhật số tiền còn lại rồi chuyển sang tờ mệnh giá tiếp theo.
    4.  **Kết thúc:** Dừng lại khi tiền thừa về 0. Không cần quan tâm tương lai sẽ ra sao.

---

#### 3. Chia để trị (Divide and Conquer)
*   **Triết lý:** "Bẻ đũa từng chiếc". Chia bài toán lớn thành nhiều bài toán nhỏ tương tự nhau, giải quyết chúng rồi gộp kết quả lại. Đặc trưng là tính **Đệ quy**.

##### Code Mẫu Kinh Điển (Binary Search - Tìm kiếm nhị phân)
```java
public int binarySearch(int[] sortedArray, int target) {
    int left = 0;
    int right = sortedArray.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2; // Lấy mốc ở giữa (Divide)

        if (sortedArray[mid] == target) return mid; // Tìm thấy rồi!

        // Nếu mốc ở giữa nhỏ hơn target, bài toán gốc ĐÃ ĐƯỢC CHIA làm đôi
        if (sortedArray[mid] < target) {
            left = mid + 1; // Chỉ giải bài toán ở nửa bên PHẢI (Conquer)
        } else {
            right = mid - 1; // Chỉ giải bài toán ở nửa bên TRÁI (Conquer)
        }
    }
    return -1;
}
```
*   **Lý giải luồng code (Flow):**
    1.  **Divide (Chia):** Nhìn vào phần tử ở chính giữa.
    2.  **Conquer (Trị):** So sánh nó với số cần tìm. Lập tức **bỏ đi một nửa** dữ liệu vô dụng.
    3.  **Lặp lại:** Bài toán mới bây giờ chỉ bằng một nửa bài toán cũ. Cứ thế "băm nhỏ" cho đến khi còn đúng 1 phần tử.
    4.  **Hiệu suất:** Mỗi bước đi chém đứt nửa dữ liệu $\rightarrow$ chính là sự ra đời của $O(\log n)$ đầy uy lực!

---

#### 4. Hai con trỏ (Two Pointers)
*   **Triết lý:** "Kẹp hai đầu". Thay vì duyệt tuần tự 1 con trỏ tốn $O(N^2)$, ta dùng 2 biến trỏ đồng thời (thường là Đầu và Cuối mảng) và thu hẹp dần khoảng cách dựa trên điều kiện logic ($O(N)$). Rất mạnh trên mảng đã sắp xếp.

##### Code Mẫu Kinh Điển (Two Sum II - Mảng ĐÃ SORT)
```java
public int[] twoSumTwoPointers(int[] sortedNums, int target) {
    int left = 0;
    int right = sortedNums.length - 1;

    while (left < right) {
        int sum = sortedNums[left] + sortedNums[right];
        if (sum == target) {
            return new int[]{left, right}; // Khớp luôn!
        } else if (sum < target) {
            left++; // Tổng đang quá nhỏ -> Cần lấy số to hơn bên PHẢI
        } else {
            right--; // Tổng quá lớn -> Cần lấy số nhỏ hơn bên TRÁI
        }
    }
    return new int[]{};
}
```
*   **Lý giải luồng code (Flow):**
    1.  **Bóp nghẹt:** Chốt 2 đầu biên. 
    2.  **Phán xét:** Cộng thử 2 phần tử to nhất và nhỏ nhất hiện tại.
    3.  **Tịnh tiến:** Dựa vào tổng lớn hay nhỏ hơn target, ta quyết định CHỈ DI CHUYỂN 1 TRONG 2 con trỏ để nhắm tới mục tiêu. Loại bỏ hàng loạt cặp không cần thử nghiệm.

---

#### 5. Cửa sổ trượt (Sliding Window)
*   **Triết lý:** "Cuốn chiếu". Thay vì tính lại từ đầu cho mỗi tập hợp con, ta duy trì một "khung hình" cố định, khi trượt sang phải thì **Cộng thêm phần tử mới vào, trừ đi phần tử cũ phía sau**. Cực kỳ tối ưu cho các bài toán chuỗi/mảng con.

##### Code Mẫu Kinh Điển (Tìm tổng lớn nhất của K phần tử liên tiếp)
```java
public int maxSubArrayOfSizeK(int[] arr, int k) {
    int windowSum = 0, maxSum = 0;
    // 1. Tính tổng của cửa sổ đầu tiên
    for (int i = 0; i < k; i++) windowSum += arr[i];
    maxSum = windowSum;

    // 2. Trượt cửa sổ sang phải
    for (int i = k; i < arr.length; i++) {
        // Cửa sổ trượt: Thêm thằng mới [i], bớt thằng cũ [i-k] ra khỏi cửa
        windowSum += arr[i] - arr[i - k]; 
        maxSum = Math.max(maxSum, windowSum); // Cập nhật kỷ lục
    }
    return maxSum;
}
```
*   **Lý giải luồng code (Flow):**
    1.  **Đóng khung:** Khởi tạo tổng cho $k$ phần tử đầu.
    2.  **Trượt:** Khi đi sang ô tiếp theo, ta không cần dùng vòng `for` tính lại cả $k$ số.
    3.  **Tận dụng:** Chỉ tốn đúng 2 phép tính `+ mới` và `- cũ` để có ngay tổng mới. Tối ưu tốc độ khủng khiếp từ $O(N \times K)$ về thẳng $O(N)$.
