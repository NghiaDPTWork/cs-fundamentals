# NỀN TẢNG NODE.JS (NODE.JS FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức cốt lõi về môi trường chạy mã Node.js, tập trung vào bộ máy xử lý đa pha Libuv Event Loop, cơ chế non-blocking I/O, Buffer/Stream, các hệ thống module và các module cốt lõi của hệ thống.

---

## 1. BỘ MÁY EVENT LOOP TRONG NODE.JS (LIBUV)

Khác với trình duyệt (vốn tối ưu cho giao diện người dùng), Event Loop của Node.js chạy dựa trên thư viện **Libuv** (viết bằng C++), được tối ưu hóa cho các tác vụ tương tác hệ thống và I/O mạng.

### 1.1. 6 pha thực thi của Libuv Event Loop
Vòng lặp sự kiện trong Node.js được phân tách rõ ràng thành 6 pha chạy tuần tự. Mỗi pha có một hàng đợi (Queue) các callback riêng cần xử lý:

```text
       ┌───────────────────────────────┐
 ┌───> │ 1. Timers Phase               │ -> Xử lý callback của setTimeout, setInterval.
 │     └──────────────┬────────────────┘
 │                    ▼
 │     ┌───────────────────────────────┐
 │     │ 2. Pending Callbacks Phase    │ -> Chạy callback của một số lỗi hệ thống (như TCP).
 │     └──────────────┬────────────────┘
 │                    ▼
 │     ┌───────────────────────────────┐
 │     │ 3. Idle, Prepare Phase        │ -> Sử dụng nội bộ hệ thống Node.js.
 │     └──────────────┬────────────────┘
 │                    ▼
 │     ┌───────────────────────────────┐
 │     │ 4. Poll Phase                 │ -> Nhận kết nối mạng mới, đọc file. Đợi tại đây.
 │     └──────────────┬────────────────┘
 │                    ▼
 │     ┌───────────────────────────────┐
 │     │ 5. Check Phase                │ -> Xử lý callback của setImmediate().
 │     └──────────────┬────────────────┘
 │                    ▼
 │     ┌───────────────────────────────┐
 │     │ 6. Close Callbacks Phase      │ -> Xử lý callback đóng (như socket.on('close')).
 └────────────────────┴────────────────┘
```

1.  **Timers Phase:** Kiểm tra và thực thi callback của các hàm hẹn giờ `setTimeout()` và `setInterval()`.
2.  **Pending Callbacks Phase:** Thực thi các callback I/O còn tồn đọng từ vòng lặp trước.
3.  **Idle, Prepare Phase:** Chỉ dùng nội bộ cho các tiến trình hệ thống của Node.js.
4.  **Poll Phase:** Pha quan trọng nhất. Node.js sẽ lắng nghe và nhận các kết nối mới (I/O), xử lý dữ liệu. Nếu hàng đợi trống và không có lệnh hẹn giờ nào khác, Node.js sẽ **dừng lại và đợi (block)** tại pha này để chờ các sự kiện tiếp theo xảy ra nhằm tiết kiệm CPU.
5.  **Check Phase:** Thực thi các callback được đăng ký bởi hàm `setImmediate()`.
6.  **Close Callbacks Phase:** Thực thi các sự kiện đóng kết nối, ví dụ: `socket.on('close', ...)`.

### 1.2. Hai hàng đợi ưu tiên đặc biệt: `process.nextTick()` và Promises
Nằm ngoài 6 pha trên, Node.js có hai hàng đợi có độ ưu tiên tuyệt đối, sẽ được thực thi **ngay lập tức giữa các pha** của Event Loop ngay khi Call Stack trống:
*   **`process.nextTick()` Queue:** Có độ ưu tiên cao nhất, chạy trước cả hàng đợi Promise.
*   **Microtask Queue (chứa Promises):** Chạy ngay sau hàng đợi `nextTick` và trước khi bước vào pha kế tiếp của Event Loop.

---

## 2. CƠ CHẾ EVENT-DRIVEN VÀ NON-BLOCKING I/O

### 2.1. Đơn luồng (Single-threaded) nhưng không bị nghẽn (Non-blocking)
*   **Single-threaded:** Node.js chạy mã JavaScript của bạn trên duy nhất một luồng chính (Main Thread).
*   **Non-blocking I/O:** Khi gặp các tác vụ nặng về tương tác phần cứng (đọc ghi ổ cứng, truy cập DB, gửi request mạng), Node.js không dừng luồng chính để đợi kết quả trả về. Nó chuyển giao toàn bộ tác vụ này cho **Thread Pool** (mặc định có 4 luồng chạy ngầm của thư viện Libuv) hoặc nhờ nhân hệ điều hành (Kernel) xử lý bất đồng bộ.
*   **Event-Driven:** Khi tác vụ ngầm hoàn thành, một sự kiện được bắn ra kèm callback tương ứng được đẩy vào hàng đợi của Event Loop để chờ luồng chính xử lý tiếp kết quả khi rảnh.
*   *Lợi ích:* Một server Node.js đơn luồng có thể chịu tải hàng triệu kết nối đồng thời (high concurrency) mà không sợ bị tốn tài nguyên RAM tạo luồng mới giống Java hay PHP.

---

## 3. BUFFER VÀ STREAM (XỬ LÝ DỮ LIỆU LỚN)

### 3.1. Buffer (Bộ đệm dữ liệu)
*   JavaScript nguyên bản ban đầu không có kiểu dữ liệu nhị phân. Node.js giới thiệu lớp **Buffer** để đại diện cho một vùng nhớ đệm có kích thước cố định bên ngoài V8 engine dùng để chứa dữ liệu nhị phân thô (raw binary data).
*   *Ứng dụng:* Đọc dữ liệu thô từ file, nhận dữ liệu gói tin mạng trước khi parse thành chuỗi.

### 3.2. Stream (Dòng dữ liệu liên tục)
Khi cần xử lý các file cực lớn (ví dụ file video 5GB), việc dùng hàm đọc toàn bộ file `fs.readFile` sẽ nạp toàn bộ 5GB dữ liệu vào RAM, gây tràn bộ nhớ (Out of Memory) và crash server.

**Stream** giải quyết bài toán này bằng cách chia nhỏ dữ liệu thành từng khúc nhỏ (chunks) và truyền đi liên tục:
*   **Readable Stream:** Dòng đọc dữ liệu (ví dụ đọc từng phần của file).
*   **Writable Stream:** Dòng ghi dữ liệu (ví dụ ghi từng phần dữ liệu vào file mới).
*   **Duplex / Transform Stream:** Dòng dữ liệu vừa đọc vừa ghi hoặc biến đổi dữ liệu (ví dụ nén file zip, mã hóa dữ liệu).
*   **Kỹ thuật Pipe (Đường ống):** Kết nối đầu ra của Readable Stream trực tiếp vào đầu vào của Writable Stream giúp dữ liệu chảy mượt mà mà không lo tốn RAM:
    ```javascript
    const fs = require('fs');
    const readable = fs.createReadStream('large_video.mp4');
    const writeable = fs.createWriteStream('copy_video.mp4');
    
    // Dữ liệu tự động chảy qua, RAM chỉ tốn một lượng nhỏ để lưu trữ các chunk tạm thời
    readable.pipe(writeable);
    ```

---

## 4. HỆ THỐNG MÔ-ĐUN (COMMONJS VS ES MODULES)

Node.js hỗ trợ song song hai hệ thống module quản lý mã nguồn:

| Tiêu chí | CommonJS (Mặc định trước đây) | ES Modules (Chuẩn Modern JS) |
| :--- | :--- | :--- |
| **Cú pháp** | Sử dụng `require()` và `module.exports`. | Sử dụng `import` và `export`. |
| **Cách load** | **Đồng bộ (Synchronous).** Đọc và chạy file tuần tự tại runtime. | **Bất đồng bộ (Asynchronous).** Phân tích dependencies trước khi chạy. |
| **Biến môi trường** | Có các biến mặc định: `__dirname`, `__filename`. | Không hỗ trợ `__dirname`, `__filename` (phải giả lập qua `import.meta.url`). |
| **Kích hoạt** | Mặc định cho mọi file `.js` trong Node.js. | Đặt `"type": "module"` trong file `package.json` hoặc dùng đuôi file `.mjs`. |

---

## 5. THỰC HÀNH CÁC MÔ-ĐUN CỐT LÕI (CORE MODULES)

### 5.1. Mô-đun `path` (Xử lý đường dẫn)
Giúp xử lý đường dẫn file/thư mục tương thích trên mọi hệ điều hành (Windows dùng `\`, Linux/macOS dùng `/`):
```javascript
const path = require('path');

// Gộp đường dẫn chuẩn xác
const fullPath = path.join(__dirname, 'uploads', 'images', 'avatar.png');
console.log(fullPath);
```

### 5.2. Mô-đun `fs` (Tương tác File System)
Hỗ trợ cả ba chế độ: Đồng bộ (Sync), Bất đồng bộ dùng Callback, và Bất đồng bộ dùng Promise (khuyên dùng):
```javascript
const fs = require('fs').promises;

async function handleFile() {
  try {
    // Đọc file
    const data = await fs.readFile('data.txt', 'utf-8');
    console.log(data);
    
    // Ghi file mới
    await fs.writeFile('output.txt', 'Kết quả: ' + data);
  } catch (error) {
    console.error('Lỗi thao tác file:', error);
  }
}
handleFile();
```

### 5.3. Tự tạo HTTP Server cơ bản bằng mô-đun `http`
Dưới đây là cách khởi tạo một máy chủ web chạy trực tiếp không cần thư viện ngoài:
```javascript
const http = require('http');

const server = http.createServer((req, res) => {
  if (req.url === '/' && req.method === 'GET') {
    res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('Chào mừng bạn đến với Node.js Server thô!');
  } else {
    res.writeHead(404, { 'Content-Type': 'text/plain' });
    res.end('Not Found');
  }
});

const PORT = 3000;
server.listen(PORT, () => {
  console.log(`Server đang chạy tại http://localhost:${PORT}`);
});
```
