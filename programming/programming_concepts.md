# CÁC KHÁI NIỆM CƠ BẢN VỀ LẬP TRÌNH & KHOA HỌC MÁY TÍNH

Tài liệu này tổng hợp các kiến thức nền tảng trong lập trình, giúp giải đáp các câu hỏi cơ bản về cách máy tính vận hành, sự khác nhau giữa các công cụ dịch mã, các khái niệm cốt lõi trong code, sự phân biệt Java - JavaScript, và tầm quan trọng của thuật toán.

---

## 1. Lập trình là gì? Tại sao máy tính cần mã nguồn?

### 1.1. Lập trình là gì?
**Lập trình (Programming)** là nghệ thuật và khoa học thiết kế, viết, thử nghiệm và bảo trì các chỉ dẫn (được gọi là mã nguồn hoặc code) nhằm hướng dẫn máy tính thực hiện các tác vụ cụ thể. 

Về bản chất, lập trình là quá trình **dịch thuật tư duy**. Con người sử dụng lập trình để chuyển hóa các ý tưởng trừu tượng, cách giải quyết vấn đề trong đời thực thành một chuỗi các chỉ dẫn có logic chặt chẽ mà máy móc có thể hiểu và thực thi.

### 1.2. Tại sao máy tính cần mã nguồn?
* **Bản chất vật lý của máy tính:** Bộ vi xử lý (CPU) của máy tính thực chất là một mạng lưới khổng lồ chứa hàng tỷ bóng bán dẫn (transistor) siêu nhỏ. Các bóng bán dẫn này chỉ có hai trạng thái vật lý: **Đóng (Off)** hoặc **Mở (On)**, tương ứng với hai chữ số logic **0** và **1** (hệ nhị phân - Binary). Máy tính không có nhận thức hay trí thông minh tự nhiên; nó là một cỗ máy vô tri.
* **Sự cần thiết của chỉ dẫn chi tiết:** Để máy tính làm bất cứ việc gì (từ hiển thị một ký tự lên màn hình đến chạy các thuật toán AI phức tạp), ta phải cung cấp một chuỗi lệnh cực kỳ chi tiết, rõ ràng và không được phép mơ hồ.
* **Mã nguồn là cầu nối ngôn ngữ:** Vì con người không thể giao tiếp hiệu quả bằng hàng tỷ số 0 và 1 (`01001000 01000101...`), chúng ta viết **mã nguồn (code)** bằng các ngôn ngữ lập trình bậc cao (như Python, JS, C++) có cú pháp gần gũi với tiếng Anh và tư duy toán học. Mã nguồn này sau đó sẽ được chuyển đổi thành mã máy (Machine Code) để máy tính thực thi.

---

## 2. Chương trình, Trình biên dịch (Compiler) & Trình thông dịch (Interpreter)

Để chuyển đổi mã nguồn từ ngôn ngữ bậc cao sang ngôn ngữ máy, hệ thống cần các công cụ chuyển dịch chuyên biệt.

```
+--------------------------+     (Dịch toàn bộ)     +--------------------------+
|  Mã nguồn bậc cao (C++)  | ---------------------> |   Trình biên dịch (C)    | ===> File chạy (.exe)
+--------------------------+                        +--------------------------+
             |
             | (Dịch từng dòng khi chạy)
             v
+--------------------------+
|  Trình thông dịch (JS)   | ===> Thực thi kết quả lập tức
+--------------------------+
```

### 2.1. Chương trình (Program)
**Chương trình máy tính** là một tập hợp các chỉ dẫn logic được viết bằng ngôn ngữ lập trình để thực hiện một nhiệm vụ cụ thể. 
* Khi ở trạng thái tĩnh, chương trình lưu trên ổ cứng dưới dạng các file nguồn hoặc file thực thi (ví dụ: `.exe`, `.js`, `.py`).
* Khi được kích hoạt, chương trình được nạp vào bộ nhớ RAM và CPU sẽ tuần tự xử lý các chỉ dẫn trong đó.

### 2.2. Trình biên dịch (Compiler)
**Trình biên dịch** là chương trình dịch **toàn bộ** mã nguồn của một ngôn ngữ bậc cao sang mã máy (hoặc mã trung gian) trước khi chương trình được chạy.
* **Quy trình:** Đọc toàn bộ file nguồn -> Phân tích cú pháp & Tối ưu hóa -> Tạo ra một file thực thi độc lập (ví dụ: `.exe` trên Windows hoặc `.out` trên Linux).
* **Đặc điểm:**
  * **Ưu điểm:** Tốc độ thực thi cực kỳ nhanh vì mã máy đã được tạo sẵn; bảo mật mã nguồn tốt vì người dùng chỉ nhận được file chạy nhị phân.
  * **Nhược điểm:** Mất thời gian biên dịch lại mỗi khi thay đổi code; file thực thi phụ thuộc vào phần cứng/hệ điều hành cụ thể khi biên dịch.
  * **Ví dụ:** C, C++, Rust, Go.

### 2.3. Trình thông dịch (Interpreter)
**Trình thông dịch** là chương trình dịch và thực thi mã nguồn **từng dòng một (line-by-line)** ngay tại thời điểm chương trình đang chạy.
* **Quy trình:** Đọc dòng 1 -> Dịch sang mã máy -> Thực thi dòng 1 -> Đọc dòng 2 -> Dịch -> Thực thi dòng 2...
* **Đặc điểm:**
  * **Ưu điểm:** Phát triển và thử nghiệm nhanh (sửa code xong có thể chạy ngay không cần đợi biên dịch); độc lập nền tảng (chạy được trên mọi hệ điều hành có cài sẵn trình thông dịch).
  * **Nhược điểm:** Tốc độ thực thi chậm hơn trình biên dịch vì máy vừa phải dịch vừa phải chạy.
  * **Ví dụ:** Python, JavaScript, Ruby, PHP.

#### Bảng so sánh Compiler vs Interpreter:
| Tiêu chí | Trình biên dịch (Compiler) | Trình thông dịch (Interpreter) |
| :--- | :--- | :--- |
| **Cách dịch** | Dịch toàn bộ file mã nguồn trước khi chạy. | Dịch và chạy từng dòng mã tại thời điểm chạy. |
| **File đầu ra** | Tạo ra file thực thi trung gian (như `.exe`, `.bin`). | Không tạo file thực thi trung gian. |
| **Tốc độ thực thi**| Rất nhanh (vì đã được tối ưu hóa trước). | Chậm hơn (vì tốn công dịch khi đang chạy). |
| **Phát hiện lỗi** | Báo tất cả lỗi cú pháp sau khi quét toàn bộ code. | Dừng chương trình ngay tại dòng đầu tiên gặp lỗi. |
| **Ứng dụng** | Phù hợp dự án lớn cần tối ưu hiệu năng cao. | Phù hợp cho scripting, phát triển ứng dụng nhanh. |

---

## 3. Các khái niệm lập trình cốt lõi (Core Concepts)

Hầu hết mọi ngôn ngữ lập trình đều được xây dựng dựa trên 5 khái niệm nền tảng dưới đây:

### 3.1. Biến (Variables)
Biến là một vùng chứa dữ liệu được đặt tên trong bộ nhớ RAM. Biến giúp chương trình lưu trữ và thao tác với thông tin động trong quá trình chạy. Hãy tưởng tượng biến giống như một chiếc hộp dán nhãn: nhãn là tên biến, vật trong hộp là giá trị của biến.
* *Ví dụ (JavaScript):* 
  ```javascript
  let userName = "Nghia"; // Tạo biến userName chứa chuỗi "Nghia"
  let score = 95;        // Tạo biến score chứa số 95
  ```

### 3.2. Kiểu dữ liệu (Data Types)
Kiểu dữ liệu xác định loại giá trị mà biến có thể lưu trữ và các phép toán hợp lệ trên đó. Các kiểu dữ liệu cơ bản bao gồm:
* **Số (Numbers):** Số nguyên (Integer: `10`, `-30`) và số thực (Float/Double: `3.14`, `-0.5`).
* **Chuỗi ký tự (String):** Văn bản nằm trong dấu nháy kép hoặc nháy đơn (`"Xin chào"`, `'Hello'`).
* **Logic (Boolean):** Chỉ nhận giá trị đúng (**True**) hoặc sai (**False**).
* **Mảng/Danh sách (Array/List):** Tập hợp các phần tử có thứ tự (`[1, 2, 3, 4]`).
* **Đối tượng (Object/Dictionary):** Cấu trúc dữ liệu lưu dưới dạng cặp key-value để mô tả một thực thể phức tạp (`{ name: "Nghia", age: 25 }`).

### 3.3. Câu lệnh điều kiện (Conditionals)
Câu lệnh điều kiện giúp chương trình đưa ra quyết định dựa trên các biểu thức logic (đúng/sai). Đây là cách chương trình rẽ nhánh luồng xử lý.
* *Ví dụ:*
  ```javascript
  let age = 18;
  if (age >= 18) {
      console.log("Bạn đủ tuổi bầu cử.");
  } else {
      console.log("Bạn chưa đủ tuổi bầu cử.");
  }
  ```

### 3.4. Vòng lặp (Loops)
Vòng lặp cho phép thực thi lặp đi lặp lại một khối lệnh nhiều lần cho đến khi một điều kiện dừng cụ thể được thỏa mãn, tránh việc lặp lại mã nguồn một cách thủ công.
* **Vòng lặp `for`:** Thường dùng khi biết trước số lần lặp.
* **Vòng lặp `while`:** Lặp dựa trên điều kiện logic, chạy cho đến khi điều kiện đó không còn đúng nữa.
* *Ví dụ (Vòng lặp in ra số từ 1 đến 3):*
  ```javascript
  for (let i = 1; i <= 3; i++) {
      console.log("Lần lặp thứ: " + i);
  }
  ```

### 3.5. Hàm (Functions)
Hàm là một khối mã nguồn độc lập được đặt tên, được thiết kế để thực hiện một tác vụ cụ thể. Hàm giúp cấu trúc mã nguồn gọn gàng hơn, dễ quản lý và tái sử dụng nhiều lần.
* Hàm có thể nhận tham số đầu vào (**Parameters**) và trả về kết quả (**Return Value**).
* *Ví dụ:*
  ```javascript
  // Khai báo hàm tính diện tích hình chữ nhật
  function tinhDienTich(chieuDai, chieuRong) {
      return chieuDai * chieuRong;
  }
  
  // Gọi hàm và lưu kết quả
  let ketQua = tinhDienTich(5, 4); // ketQua sẽ là 20
  ```

---

## 4. Sự khác biệt giữa Java và JavaScript

Mặc dù có tên gọi tương tự nhau do yếu tố lịch sử tiếp thị vào những năm 1995, Java và JavaScript là hai ngôn ngữ lập trình hoàn toàn khác biệt về triết lý thiết kế, cú pháp và mục đích sử dụng.

| Đặc điểm | Java | JavaScript (JS) |
| :--- | :--- | :--- |
| **Người sáng lập** | James Gosling (Sun Microsystems) | Brendan Eich (Netscape) |
| **Triết lý thiết kế** | "Viết một lần, chạy mọi nơi" (hướng đối tượng nghiêm ngặt). | Tương tác nhanh trên trình duyệt web, gọn nhẹ và linh hoạt. |
| **Cơ chế kiểu dữ liệu**| **Static typing (Kiểu tĩnh):** Phải khai báo kiểu dữ liệu cho biến khi viết code và không thể thay đổi sau đó. <br> *Ví dụ:* `int a = 5;` | **Dynamic typing (Kiểu động):** Biến tự nhận kiểu dữ liệu tùy theo giá trị gán cho nó. <br> *Ví dụ:* `let a = 5; a = "hello";` |
| **Cơ chế thực thi** | Biên dịch code nguồn thành Bytecode (`.class`), sau đó chạy trên JVM (Java Virtual Machine). | Thông dịch/Biên dịch JIT trực tiếp bằng Engine của trình duyệt web (như V8 của Chrome) hoặc Node.js. |
| **Hướng đối tượng** | Hướng đối tượng dựa trên Lớp (**Class-based OOP**). Mọi thứ bắt buộc phải nằm trong Class. | Hướng đối tượng dựa trên Nguyên mẫu (**Prototype-based OOP**) và hỗ trợ Lập trình hàm (Functional). |
| **Môi trường chạy** | JVM (chạy được trên Server, Desktop, Android...). | Trình duyệt Web (Client-side) và Server-side (Node.js). |
| **Ứng dụng chính** | Hệ thống Backend doanh nghiệp lớn, ứng dụng Android, phần mềm tài chính ngân hàng. | Lập trình Web tương tác Frontend (React, Vue, Next.js), ứng dụng di động lai (React Native), Backend Node.js. |

---

## 5. Thuật toán (Algorithm) là gì và tại sao nó lại quan trọng?

### 5.1. Thuật toán là gì?
**Thuật toán (Algorithm)** là một chuỗi các bước logic, có giới hạn và không mơ hồ, được thiết lập để giải quyết một vấn đề cụ thể hoặc thực hiện một tác vụ từ một dữ liệu đầu vào (Input) cho trước để đạt được kết quả mong muốn (Output).

```
+------------+      +---------------------------+      +-------------+
|   Input    | ===> |         Thuật toán        | ===> |   Output    |
| (Dữ liệu)  |      | (Các bước xử lý logic)    |      | (Kết quả)   |
+------------+      +---------------------------+      +-------------+
```

*Một ví dụ minh họa:* Quy trình tìm số điện thoại của một người tên "Bình" trong cuốn danh bạ giấy dày 1000 trang:
* **Cách 1 (Tuần tự):** Lật từng trang từ 1 đến 1000. (Rất lâu, mất tối đa 1000 bước).
* **Cách 2 (Chia đôi - Binary Search):** Mở trang ở giữa danh bạ (trang 500). So sánh chữ cái đầu với chữ "B". Nếu chữ cái ở trang 500 là chữ "M", ta biết tên "Bình" phải nằm ở nửa đầu danh bạ (trang 1-499). Ta xé bỏ nửa sau và lặp lại thao tác chia đôi với nửa đầu. (Cực kỳ nhanh, chỉ mất tối đa 10 bước).

Cách 2 chính là một thuật toán tối ưu hơn rất nhiều so với cách 1.

### 5.2. Tại sao thuật toán lại quan trọng?
Trong phát triển phần mềm và khoa học máy tính, thuật toán là nhân tố quyết định hiệu quả của ứng dụng:

1. **Tối ưu hóa hiệu năng hệ thống:** 
   Một thuật toán tốt giúp giải quyết công việc nhanh hơn và tốn ít tài nguyên phần cứng (CPU, RAM, Pin) hơn. Lập trình viên đo lường chất lượng thuật toán thông qua **độ phức tạp thời gian** (Time Complexity) và **độ phức tạp không gian** (Space Complexity) bằng ký pháp **Big-O** (ví dụ: $O(N)$ hay $O(\log N)$).
2. **Tiết kiệm chi phí vận hành:** 
   Với lượng dữ liệu khổng lồ (Big Data), thuật toán kém tối ưu có thể làm treo máy chủ hoặc làm tăng chi phí thuê hạ tầng đám mây (Cloud) lên gấp hàng trăm lần. Thuật toán tốt giúp hệ thống quy mô lớn hoạt động ổn định và tiết kiệm.
3. **Nền tảng của các công nghệ đột phá:**
   Các công nghệ thay đổi thế giới hiện nay đều dựa trên các thuật toán nền tảng:
   * **Mạng xã hội:** Thuật toán gợi ý nội dung (Recommendation Algorithm) của TikTok, Facebook.
   * **Bản đồ:** Thuật toán tìm đường đi ngắn nhất (Dijkstra, A*) của Google Maps.
   * **Trí tuệ nhân tạo:** Thuật toán Lan truyền ngược (Backpropagation) và Mạng nơ-ron (Neural Networks).
   * **Bảo mật:** Thuật toán mã hóa khóa công khai (RSA, AES) giúp bảo vệ thông tin thẻ tín dụng và mật khẩu của bạn trực tuyến.

---
*Tài liệu học tập - cs-fundamentals/programming*
