# JAVASCRIPT NÂNG CAO (JAVASCRIPT ADVANCED)

Tài liệu này hệ thống hóa các khái niệm nâng cao của JavaScript, bao gồm cách hoạt động của Scope & Closure, hệ thống kế thừa Prototype, lập trình bất đồng bộ, bộ máy hoạt động Event Loop và cơ chế quản lý bộ nhớ.

---

## 1. PHẠM VI HOẠT ĐỘNG VÀ CLOSURE (SCOPE & CLOSURE)

### 1.1. Lexical Scope và Execution Context
*   **Lexical Scope (Phạm vi tĩnh):** JavaScript xác định phạm vi hoạt động của một biến dựa vào **vị trí khai báo** của biến đó trong mã nguồn chứ không phải vị trí biến đó được gọi.
*   **Execution Context (Ngữ cảnh thực thi):** Mỗi khi một hàm được chạy, JavaScript engine tạo ra một Execution Context chứa:
    *   Các biến cục bộ và tham số của hàm.
    *   Ràng buộc từ khóa `this`.
    *   **Outer Scope Link (Scope Chain):** Liên kết tham chiếu đến phạm vi cha bao bọc nó.
*   **Call Stack (Ngăn xếp cuộc gọi):** Là nơi JS engine quản lý các Execution Context. Khi hàm bắt đầu chạy, context của nó được đẩy (push) vào đầu Stack. Khi hàm chạy xong, context của nó bị rút (pop) ra khỏi Stack.

### 1.2. Closure là gì?
*   **Định nghĩa thực tế:** Closure là khả năng của một hàm ghi nhớ và truy cập vào phạm vi chứa nó (outer scope) ngay cả khi hàm đó được thực thi bên ngoài phạm vi định nghĩa ban đầu.
*   **Ví dụ minh họa:**
    ```javascript
    function createCounter() {
      let count = 0; // Biến cục bộ
      
      return function() {
        count++; // Hàm con ghi nhớ biến count của hàm cha
        return count;
      };
    }

    const counter = createCounter();
    console.log(counter()); // 1
    console.log(counter()); // 2
    // Biến 'count' hoàn toàn được bảo vệ, code bên ngoài không thể truy cập hay sửa đổi trực tiếp.
    ```
*   **Ứng dụng thực tiễn:**
    *   **Đóng gói dữ liệu (Encapsulation):** Tạo các thuộc tính/phương thức private trong module pattern trước khi JS ES6 hỗ trợ thuộc tính `#` private.
    *   **Hàm tạo sẵn cấu hình (Currying & Partial Application):** Tạo ra các hàm tiện ích được thiết lập sẵn một vài tham số mặc định từ trước.

---

## 2. HỆ THỐNG KẾ THỪA PROTOTYPE (PROTOTYPES & INHERITANCE)

Không giống các ngôn ngữ như Java hay C++ sử dụng cơ chế kế thừa Class-based, JavaScript nguyên bản sử dụng **Prototype-based inheritance**.

### 2.1. Prototype hoạt động như thế nào?
Mọi Object trong JavaScript đều có một liên kết ngầm trỏ đến một Object khác gọi là **Prototype** (có thể truy cập qua thuộc tính `__proto__` hoặc hàm `Object.getPrototypeOf()`).
*   **Cơ chế tìm kiếm (Prototype Chain):** Khi bạn truy xuất một thuộc tính của Object (ví dụ: `obj.age`), JS engine sẽ tìm trên chính đối tượng đó trước. Nếu không thấy, nó sẽ đi dọc theo chuỗi Prototype (`obj.__proto__`) để tìm tiếp. Tiến trình này tiếp tục cho đến khi tìm thấy hoặc chạm tới `null` (cuối chuỗi của `Object.prototype`).

### 2.2. So sánh Class ES6 vs Constructor Function thuần túy
Từ khóa `class` xuất hiện từ ES6 chỉ là một lớp bọc cú pháp (syntactic sugar) giúp việc khai báo code giống Class truyền thống cho dễ đọc, nhưng bản chất bên dưới nó vẫn chạy dựa trên Prototype:

```javascript
// Cách 1: Dùng Constructor Function truyền thống
function Person(name) {
  this.name = name;
}
Person.prototype.sayHello = function() {
  return "Tôi là " + this.name;
};

// Cách 2: Dùng Class ES6 (Bản chất chạy giống hệt Cách 1)
class Person {
  constructor(name) {
    this.name = name;
  }
  sayHello() {
    return "Tôi là " + this.name;
  }
}
```

---

## 3. LẬP TRÌNH BẤT ĐỒNG BỘ (ASYNCHRONOUS JAVASCRIPT)

JavaScript là ngôn ngữ đơn luồng (Single-threaded). Để xử lý các tác vụ tốn thời gian (gửi HTTP request, đọc file, hẹn giờ) mà không làm đơ giao diện người dùng, JS sử dụng lập trình bất đồng bộ qua 3 thế hệ phát triển:

### 3.1. Callback và Callback Hell
*   Truyền một hàm (callback) làm đối số cho một hàm khác, và thực thi callback đó sau khi tác vụ bất đồng bộ hoàn thành.
*   *Vấn đề (Callback Hell):* Khi có nhiều tác vụ bất đồng bộ phụ thuộc nối tiếp nhau, code sẽ bị lồng sâu vào nhau theo hình tam giác (Pyramid of Doom), khiến code cực kỳ khó đọc và khó xử lý lỗi.

### 3.2. Promises (ES6)
Là một đối tượng đại diện cho kết quả (thành công hoặc thất bại) của một tác vụ bất đồng bộ trong tương lai. Một Promise luôn nằm trong 1 của 3 trạng thái:
1.  **Pending (Đang chờ):** Trạng thái ban đầu, tác vụ bất đồng bộ chưa chạy xong.
2.  **Fulfilled (Đã hoàn thành):** Tác vụ thành công. Hàm `.then()` sẽ được gọi để xử lý kết quả.
3.  **Rejected (Đã thất bại):** Tác vụ lỗi. Hàm `.catch()` sẽ được gọi để xử lý lỗi.
*   *Cơ chế nối chuỗi (Promise Chaining):* Cho phép giải quyết Callback Hell bằng cách viết code phẳng tuần tự thông qua việc `return` Promise mới trong hàm `.then()`.

### 3.3. Async/Await (ES8)
*   **Bản chất:** Là cú pháp bọc ngoài (syntactic sugar) giúp bạn viết mã bất đồng bộ trông giống như mã đồng bộ tuần tự, dễ đọc và dễ bảo trì hơn nhiều.
*   **Cách hoạt động:**
    *   Hàm khai báo với từ khóa `async` luôn tự động trả về một Promise.
    *   Từ khóa `await` chỉ được phép dùng bên trong hàm `async`. Nó tạm dừng việc thực thi dòng code tiếp theo cho đến khi Promise phía sau nó được xử lý xong.
    *   *Bắt lỗi:* Phải sử dụng cấu trúc `try/catch` để gom bắt mọi lỗi phát sinh từ `await`.

---

## 4. BỘ MÁY EVENT LOOP (VÒNG LẶP SỰ KIỆN)

Event Loop là cơ chế giúp JavaScript xử lý các hành vi bất đồng bộ hiệu quả dù chỉ chạy trên một luồng chính duy nhất.

```text
 ┌─────────────────────────────────────────────────────────────┐
 │                       BROWSER ENGINE                        │
 │                                                             │
 │  ┌─────────────┐        ┌──────────────┐                    │
 │  │ Call Stack  │ ───>   │ Web APIs     │ (setTimeout, fetch)│
 │  └─────────────┘        └──────────────┘                    │
 │         ▲                       │                           │
 │         │                       ▼                           │
 │         │               ┌──────────────┐                    │
 │         └────────────── │ Microtask Q  │ (Promises)         │
 │           Event Loop    ├──────────────┤                    │
 │                         │ Macrotask Q  │ (setTimeout, I/O)  │
 │                         └──────────────┘                    │
 └─────────────────────────────────────────────────────────────┘
```

### 4.1. Các thành phần chính
1.  **Call Stack:** Nơi thực thi các mã đồng bộ.
2.  **Web APIs (hoặc C++ APIs ở Node.js):** Chạy ngầm các tác vụ bất đồng bộ (Timer, Network Request, DOM Events) ngoài luồng JS chính.
3.  **Microtask Queue:** Nơi chứa các callback bất đồng bộ có độ ưu tiên cao nhất (chủ yếu là callback của `Promise.then/catch`, `queueMicrotask`, `MutationObserver`).
4.  **Macrotask Queue (hoặc Callback Queue):** Nơi chứa các callback của `setTimeout`, `setInterval`, các sự kiện click/render.

### 4.2. Thứ tự ưu tiên thực thi của Event Loop
1.  Chạy toàn bộ mã đồng bộ trong **Call Stack** cho đến khi Stack trống trơn.
2.  Kiểm tra và thực thi **tất cả** các tác vụ đang đợi trong **Microtask Queue** cho đến khi Microtask Queue hết sạch (kể cả các Microtask mới được sinh ra trong lúc đang chạy).
3.  Lấy **duy nhất 1** tác vụ đầu tiên trong **Macrotask Queue** đưa lên Call Stack để chạy.
4.  Quay lại bước 2 (lặp lại vô hạn).

---

## 5. QUẢN LÝ BỘ NHỚ VÀ RÒ RỈ BỘ NHỚ (MEMORY MANAGEMENT)

### 5.1. Stack vs Heap
*   **Stack:** Vùng nhớ tĩnh có kích thước cố định. Dùng để lưu trữ các biến cục bộ nguyên thủy và địa chỉ con trỏ của các đối tượng tham chiếu. Quản lý dọn dẹp theo cơ chế LIFO của hàm cực kỳ nhanh.
*   **Heap:** Vùng nhớ động không giới hạn kích thước cố định. Dùng để chứa các đối tượng có kích thước thay đổi (Object, Array, Function). Tốc độ cấp phát chậm hơn Stack.

### 5.2. Thuật toán Dọn rác (Garbage Collection)
JavaScript Engine tự động quản lý việc dọn dẹp bộ nhớ thông qua thuật toán dọn rác chính:
*   **Mark-and-Sweep (Đánh dấu và Quét):** 
    *   *Nguyên lý:* Bắt đầu từ một tập hợp các đối tượng gốc (Global Object - Root). Engine sẽ đi dọc theo các liên kết tham chiếu để đánh dấu (Mark) tất cả các đối tượng có thể tiếp cận được (Reachable).
    *   *Dọn dẹp:* Tất cả các đối tượng không được đánh dấu (Unreachable - không có cách nào truy cập được từ global root) sẽ bị quét (Sweep) và giải phóng bộ nhớ.

### 5.3. Các nguyên nhân gây Rò rỉ bộ nhớ (Memory Leak) phổ biến
Memory Leak xảy ra khi các đối tượng không còn dùng tới nhưng vẫn được liên kết tham chiếu từ Global, làm bộ dọn rác không thể giải phóng chúng:
1.  **Biến toàn cục ngoài ý muốn (Accidental Global Variables):** Khai báo biến không có từ khóa `let`/`const` khiến nó tự động gắn vào `window`, tồn tại vĩnh viễn cho đến khi tắt trang web.
2.  **Quên gỡ bỏ lắng nghe sự kiện hoặc Timers:** 
    *   Sử dụng `setInterval` hoặc `addEventListener` trên các phần tử DOM đã bị xóa bỏ khỏi trang web. Hàm callback vẫn giữ tham chiếu đến các biến xung quanh nó và không thể bị dọn rác.
    *   *Giải pháp:* Luôn dùng `clearInterval()` hoặc `removeEventListener()` khi không còn dùng tới phần tử.
3.  **Bộ nhớ đệm (Cache) không kiểm soát:** Lưu dữ liệu vô thời hạn trong một biến toàn cục làm cache mà không thiết lập cơ chế xóa bỏ khi cache quá lớn.
