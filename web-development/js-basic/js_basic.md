# JAVASCRIPT CƠ BẢN (JAVASCRIPT BASIC)

Tài liệu này hệ thống hóa các kiến thức căn bản nhất về JavaScript, bao gồm cách quản lý biến, kiểu dữ liệu, các loại hàm, kỹ thuật thao tác mảng/đối tượng nâng cao và cơ chế tương tác với DOM/Sự kiện.

---

## 1. BIẾN VÀ KIỂU DỮ LIỆU (VARIABLES & DATA TYPES)

### 1.1. So sánh `var` vs `let` vs `const`
JavaScript có 3 cách để khai báo biến. Sự khác biệt cốt lõi nằm ở phạm vi hoạt động (Scope) và cơ chế dịch chuyển khai báo lên đầu (Hoisting):

*   **`var` (Cách khai báo cũ - Không khuyên dùng):**
    *   *Scope:* Function Scope (Chỉ bị giới hạn bên trong hàm, nếu nằm trong các khối lệnh `if`, `for` thì vẫn bị rò rỉ ra ngoài).
    *   *Hoisting:* Có. Khi chạy, khai báo biến sẽ được đưa lên đầu phạm vi nhưng giá trị mang tính chất `undefined`. Không ném ra lỗi nếu gọi trước khi khai báo.
    *   *Khai báo lại:* Cho phép khai báo lại cùng một tên biến trong cùng phạm vi.
*   **`let` (Khai báo biến động):**
    *   *Scope:* Block Scope (Giới hạn nghiêm ngặt trong cặp dấu ngoặc nhọn `{}` chứa nó như `if`, `for`, `while`).
    *   *Hoisting:* Có, nhưng nằm trong vùng chết tạm thời (Temporal Dead Zone - TDZ). Nếu bạn gọi biến trước khi khai báo, JavaScript sẽ ném lỗi `ReferenceError` ngay lập tức.
    *   *Khai báo lại:* Không cho phép khai báo trùng tên trong cùng một phạm vi.
*   **`const` (Khai báo hằng số):**
    *   *Đặc điểm:* Giống hệt `let` (Block Scope, Hoisting có TDZ), nhưng giá trị của biến **không thể gán lại** sau khi khởi tạo.
    *   *Lưu ý quan trọng:* Đối với kiểu dữ liệu tham chiếu (Array, Object), `const` chỉ bảo vệ **tham chiếu bộ nhớ**, bạn vẫn có thể sửa đổi phần tử bên trong mảng hoặc thuộc tính của đối tượng bình thường.
        ```javascript
        const user = { name: "Nghia" };
        user.name = "Nghia DPT"; // Hợp lệ!
        user = {}; // Lỗi TypeError! Không được gán lại địa chỉ mới.
        ```

### 1.2. Kiểu dữ liệu nguyên thủy (Primitive) vs Tham chiếu (Reference)
*   **Primitive Types (Nguyên thủy):** `Number`, `String`, `Boolean`, `Null`, `Undefined`, `Symbol`, `BigInt`.
    *   *Cơ chế:* Lưu giá trị thực tế trực tiếp trong bộ nhớ **Stack**. Khi gán biến này cho biến khác, JavaScript sẽ sao chép giá trị thực tế đó (Copy by value). Thay đổi biến này không ảnh hưởng biến kia.
*   **Reference Types (Tham chiếu):** `Object`, `Array`, `Function`.
    *   *Cơ chế:* Giá trị thực của đối tượng được lưu trong bộ nhớ **Heap**. Biến chỉ lưu **địa chỉ con trỏ** trỏ đến ô nhớ đó trên Stack. Khi gán, JavaScript chỉ sao chép địa chỉ con trỏ (Copy by reference). Cả hai biến cùng trỏ vào một ô nhớ Heap. Thay đổi thuộc tính ở biến này sẽ làm biến kia thay đổi theo.
        ```javascript
        let arr1 = [1, 2, 3];
        let arr2 = arr1;
        arr2.push(4);
        console.log(arr1); // Kết quả: [1, 2, 3, 4] (arr1 cũng bị thay đổi!)
        ```

### 1.3. Cơ chế ép kiểu tự động (Type Coercion)
JS là ngôn ngữ định kiểu động (dynamically typed), nó sẽ tự động chuyển đổi kiểu dữ liệu để thực hiện phép toán nếu các toán hạng không cùng kiểu:
*   Phép cộng `+` có độ ưu tiên chuyển đổi thành Chuỗi nếu một trong hai bên là String: `1 + "2" = "12"`.
*   Các phép toán `-`, `*`, `/` luôn cố gắng ép kiểu về dạng Number: `"4" - 2 = 2`, `"4" * "2" = 8`.
*   Ép kiểu về Boolean: Các giá trị được coi là **Falsy** (khi ép kiểu sẽ ra `false`) gồm: `false`, `0`, `""` (chuỗi rỗng), `null`, `undefined`, `NaN`. Tất cả các giá trị khác đều là **Truthy** (kể cả mảng rỗng `[]` và đối tượng rỗng `{}`).

---

## 2. TOÁN TỬ VÀ ĐIỀU KHIỂN (OPERATORS & CONTROL FLOW)

### 2.1. So sánh `==` vs `===`
*   **`==` (So sánh tương đối - Loose Equality):** So sánh giá trị sau khi đã tự động ép kiểu về cùng một kiểu dữ liệu.
    *   *Ví dụ:* `1 == "1"` là `true` (vì chuỗi `"1"` được ép kiểu thành số `1`).
*   **`===` (So sánh tuyệt đối - Strict Equality):** So sánh cả giá trị và kiểu dữ liệu mà không ép kiểu. Chỉ trả về `true` khi cả hai trùng khớp hoàn toàn.
    *   *Ví dụ:* `1 === "1"` là `false` (vì kiểu Number khác kiểu String).
    *   *Thực tế:* **Luôn luôn sử dụng `===`** để tránh các lỗi logic ngầm khó debug.

### 2.2. Vòng lặp nâng cao: `for...of` vs `for...in`
*   **`for...of`:** Dùng để duyệt qua các **giá trị** của một đối tượng có thể duyệt (iterable) như Array, String, Map, Set.
    ```javascript
    const colors = ["red", "blue"];
    for (let color of colors) {
      console.log(color); // In ra: "red", "blue"
    }
    ```
*   **`for...in`:** Dùng để duyệt qua các **khóa (keys/properties)** của một Object hoặc chỉ số (indexes) của một Array.
    ```javascript
    const user = { name: "Nghia", age: 25 };
    for (let key in user) {
      console.log(key + ": " + user[key]); // In ra: "name: Nghia", "age: 25"
    }
    ```

### 2.3. Các toán tử logic đặc biệt: `??`, `?`, `!`, `!!`, `?.`, `.?`, `..`

Để tối ưu hóa mã nguồn và tránh các lỗi crash chương trình khi tương tác với dữ liệu động, JavaScript cung cấp nhiều toán tử logic đặc biệt. Dưới đây là phân biệt chi tiết:

#### 1. Toán tử Nullish Coalescing (`??`)
*   **Ý nghĩa:** Trả về toán hạng bên phải chỉ khi toán hạng bên trái là **`null` hoặc `undefined`**.
*   **So sánh với Toán tử OR (`||`):** 
    *   `||` sẽ lấy giá trị bên phải nếu bên trái là bất kỳ giá trị **Falsy** nào (`false`, `0`, `""`, `NaN`, `null`, `undefined`).
    *   `??` chỉ quan tâm đến `null` và `undefined`.
*   **Ví dụ:**
    ```javascript
    const userSpeed = 0;
    
    const speed1 = userSpeed || 10; // Kết quả: 10 (vì 0 là Falsy)
    const speed2 = userSpeed ?? 10; // Kết quả: 0  (vì 0 không phải là null/undefined)
    ```

#### 2. Toán tử ba ngôi (`?`)
*   **Cú pháp:** `điều_kiện ? giá_trị_nếu_đúng : giá_trị_nếu_sai`
*   **Ý nghĩa:** Viết tắt cho cấu trúc điều kiện `if...else` đơn giản.
*   **Ví dụ:**
    ```javascript
    const age = 20;
    const canVote = age >= 18 ? "Được bỏ phiếu" : "Chưa đủ tuổi";
    ```

#### 3. Toán tử phủ định (`!`) và Phủ định kép (`!!`)
*   **Toán tử `!` (Logical NOT):** Đảo ngược giá trị logic của một đối tượng. Chuyển Truthy thành `false` và Falsy thành `true`.
*   **Toán tử `!!` (Double NOT):** 
    *   **Ý nghĩa:** Ép kiểu nhanh một giá trị bất kỳ về dạng **Boolean thực tế (`true`/`false`)** tương ứng với trạng thái Truthy/Falsy của nó.
    *   **Ví dụ:**
        ```javascript
        const username = "NghiaDPT";
        
        console.log(!username);  // false (vì chuỗi có ký tự là Truthy, phủ định ra false)
        console.log(!!username); // true  (ép kiểu chuỗi sang true)
        
        const cartItems = 0;
        console.log(!!cartItems); // false (ép kiểu số 0 sang false)
        ```

#### 4. Toán tử Optional Chaining (`?.`)
*   **Ý nghĩa:** Cho phép truy cập an toàn các thuộc tính nằm sâu bên trong đối tượng mà không sợ bị crash ứng dụng nếu đối tượng cha là `null` hoặc `undefined`.
*   **Cách hoạt động:** Nếu phần đứng trước `?.` là `null` hoặc `undefined`, biểu thức sẽ lập tức dừng lại và trả về `undefined` chứ không ném lỗi `TypeError`.
*   **Ví dụ:**
    ```javascript
    const user = { profile: null };
    
    // Cách cũ (rất dài dòng):
    const avatar = user && user.profile && user.profile.avatar;
    
    // Cách mới với Optional Chaining:
    const avatarSafe = user?.profile?.avatar; // Trả về undefined, không crash!
    ```

#### 5. Ký tự `.?` và `..` có phải toán tử trong JavaScript không?
*   **Ký tự `.?` (Chấm hỏi sau dấu chấm):** **KHÔNG PHẢI** là toán tử hợp lệ trong JavaScript. Đây thường là lỗi gõ ngược của toán tử Optional Chaining (`?.`). Viết `user.?profile` sẽ gây lỗi cú pháp (`SyntaxError`).
*   **Ký tự `..` (Hai dấu chấm):** **KHÔNG PHẢI** là một toán tử phạm vi (range) hay toán tử chuẩn trong JavaScript (khác với Rust hay Ruby). Tuy nhiên, bạn có thể bắt gặp nó trong trường hợp đặc biệt sau:
    *   *Gọi method trên số nguyên trực tiếp:*
        ```javascript
        // 1.toString(); // Lỗi SyntaxError! Trình duyệt hiểu dấu chấm đầu tiên là phần thập phân của số.
        1..toString();   // Hợp lệ! Dấu chấm 1 là phần thập phân (1.0), dấu chấm 2 là để gọi hàm.
        (1).toString();  // Cách viết thay thế sạch sẽ và khuyên dùng hơn.
        ```

---

## 3. CÁC LOẠI HÀM (FUNCTIONS & THIS CONTEXT)

Có 3 cách phổ biến để định nghĩa hàm trong JavaScript, sự khác biệt nằm ở cơ chế Binding từ khóa `this`:

```javascript
// 1. Function Declaration (Khai báo hàm)
function welcome() { return "Hello"; }

// 2. Function Expression (Biểu thức hàm)
const welcome = function() { return "Hello"; };

// 3. Arrow Function (Hàm mũi tên)
const welcome = () => "Hello";
```

### Từ khóa `this` hoạt động như thế nào?
Từ khóa `this` đại diện cho ngữ cảnh (context) mà hàm đó được gọi:
*   Trong **Function Declaration/Expression** thông thường, giá trị `this` được xác định **tại runtime** dựa trên đối tượng gọi hàm (ai gọi thì `this` là người đó). Nếu gọi trực tiếp không có đối tượng, `this` sẽ là đối tượng toàn cục (`window` trong trình duyệt, hoặc `undefined` trong strict mode).
*   Trong **Arrow Function**, hàm **không có từ khóa `this` riêng**. Giá trị `this` của nó được kế thừa trực tiếp từ phạm vi bao quanh nó lúc định nghĩa (Lexical `this`). Nó không bị thay đổi ngữ cảnh kể cả khi dùng các hàm `call()`, `apply()`, hay `bind()`.
    *   *Ứng dụng:* Cực kỳ hữu dụng khi viết callback hoặc xử lý sự kiện trong React mà không muốn bị mất tham chiếu `this` của class/component.

---

## 4. XỬ LÝ MẢNG VÀ ĐỐI TƯỢNG NÂNG CAO

### 4.1. Các hàm thao tác mảng cốt lõi
*   **`map()`:** Tạo ra một mảng mới bằng cách biến đổi từng phần tử của mảng cũ thông qua hàm callback. Không làm thay đổi mảng cũ.
*   **`filter()`:** Tạo ra một mảng mới chứa các phần tử thỏa mãn điều kiện logic trong hàm callback.
*   **`reduce()`:** Chạy một hàm tích lũy trên từng phần tử để cuối cùng trả về một giá trị duy nhất (số, chuỗi, đối tượng, hoặc mảng mới).
    ```javascript
    const numbers = [1, 2, 3, 4];
    const sum = numbers.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
    console.log(sum); // Kết quả: 10
    ```
*   **`find()`:** Trả về giá trị của phần tử đầu tiên trong mảng thỏa mãn điều kiện.
*   **`some()`:** Trả về `true` nếu có ít nhất một phần tử thỏa mãn điều kiện.
*   **`every()`:** Trả về `true` chỉ khi tất cả phần tử trong mảng thỏa mãn điều kiện.

### 4.2. Destructuring, Rest & Spread Operators
*   **Destructuring (Rã cấu trúc):** Lấy nhanh các giá trị từ mảng hoặc thuộc tính từ đối tượng.
    ```javascript
    const user = { name: "Nghia", age: 25 };
    const { name, age } = user; // Tạo nhanh 2 biến name và age
    ```
*   **Spread Operator (Toán tử rải - `...`):** Sao chép nông (shallow copy) hoặc gộp mảng/đối tượng.
    ```javascript
    const arr1 = [1, 2];
    const arr2 = [...arr1, 3, 4]; // [1, 2, 3, 4]
    ```
*   **Rest Operator (Toán tử gom - `...`):** Gom các đối số còn lại thành một mảng. Dùng trong tham số của hàm hoặc phép destructuring.
    ```javascript
    const [first, ...rest] = [10, 20, 30];
    console.log(rest); // [20, 30]
    ```

---

## 5. TƯƠNG TÁC DOM VÀ CƠ CHẾ SỰ KIỆN (DOM & EVENTS)

### 5.1. Cơ chế xử lý sự kiện (Event Handlers) & Lan truyền sự kiện

#### a. Các khái niệm lan truyền sự kiện (Event Propagation):
Khi một sự kiện xảy ra trên một phần tử nằm sâu trong DOM, sự kiện đó không chỉ kích hoạt riêng trên phần tử đó mà lan truyền qua 3 giai đoạn của **Event Propagation**:
1.  **Event Capturing (Giai đoạn bắt):** Đi từ Document gốc đi xuống phần tử đích.
2.  **Target Phase (Giai đoạn đích):** Sự kiện kích hoạt tại chính phần tử đích.
3.  **Event Bubbling (Giai đoạn nổi bọt):** Sự kiện lan truyền ngược từ phần tử đích lên Document gốc.

*Mặc định, các hàm lắng nghe sự kiện (`addEventListener`) sẽ lắng nghe ở giai đoạn nổi bọt (Bubbling Phase).*

#### b. Các hàm điều khiển sự kiện:
*   **`event.stopPropagation()` (Dừng lan truyền):** Ngăn chặn sự kiện tiếp tục lan truyền/nổi bọt lên các thẻ cha trong cây DOM.
*   **`event.preventDefault()` (Chặn hành động mặc định):** 
    *   *Định nghĩa:* Ngăn chặn hành động mặc định được thiết lập bởi trình duyệt (browser) hoặc hệ thống khi một sự kiện xảy ra.
    *   *Ví dụ:* Khi click vào một thẻ `<a>` (link), hành động mặc định của trình duyệt là chuyển trang web; sử dụng `preventDefault()` để chặn hành động chuyển trang này lại. Tương tự đối với thẻ `<button type="submit">` (chặn reload trang).

### 5.2. Kỹ thuật Ủy quyền sự kiện (Event Delegation)
*   **Vấn đề:** Nếu bạn có một danh sách `<ul>` chứa 1000 thẻ `<li>` và muốn bắt sự kiện click cho từng thẻ. Việc gán 1000 hàm lắng nghe sự kiện click cho 1000 phần tử `<li>` sẽ tiêu tốn một lượng bộ nhớ khổng lồ và làm giảm hiệu năng ứng dụng.
*   **Giải pháp (Event Delegation):** Tận dụng cơ chế nổi bọt sự kiện, ta chỉ cần gán **duy nhất 1 hàm lắng nghe sự kiện** vào thẻ cha `<ul>`. Khi người dùng click vào bất kỳ thẻ `<li>` nào, sự kiện sẽ nổi bọt lên `<ul>`, ta chỉ cần kiểm tra xem phần tử thực tế được click (`event.target`) có phải là thẻ `<li>` hay không để xử lý.
    ```javascript
    const list = document.querySelector("ul");
    list.addEventListener("click", function(event) {
      if (event.target.tagName === "LI") {
        console.log("Bạn vừa click vào dòng: " + event.target.innerText);
      }
    });
    ```

---

## 6. LẬP TRÌNH BẤT ĐỒNG BỘ VÀ VÒNG LẶP (ASYNC/AWAIT IN LOOPS)

Khi làm việc với các tác vụ bất đồng bộ (như gọi API) trong vòng lặp, việc sử dụng `async/await` có sự khác biệt rất lớn giữa vòng lặp `for...of` và phương thức `forEach`.

### 6.1. Vòng lặp `for...of` + `async/await` (Tuần tự / Blocking)
*   **Cách hoạt động:** Vòng lặp `for...of` tôn trọng từ khóa `await`. Nó sẽ tạm dừng và **đợi** cho đến khi Promise của phần tử hiện tại được giải quyết (resolved) xong mới bước sang phần tử tiếp theo.
*   **Trường hợp sử dụng:** Khi các tác vụ bất đồng bộ cần thực hiện **tuần tự** (ví dụ: request sau cần thông tin từ kết quả của request trước).
*   **Ví dụ:**
    ```javascript
    const fetchUsers = async () => {
      const ids = [1, 2, 3];
      for (const id of ids) {
        const user = await callApi(id); // Chờ id 1 xong -> chạy id 2 -> chạy id 3
        console.log(`Đã lấy xong user ${id}`);
      }
      console.log("Hoàn thành tất cả!");
    };
    ```

### 6.2. Vòng lặp `forEach` + `async/await` (Song song / Non-blocking nhưng không đợi)
*   **Cách hoạt động:** Phương thức `forEach` được thiết kế đồng bộ và **không quan tâm/không đợi** kết quả của hàm callback bất đồng bộ. Khi truyền `async` callback vào `forEach`, nó sẽ kích hoạt tất cả các Promise **đồng thời** (gần như song song) và vòng lặp `forEach` sẽ kết thúc ngay lập tức mà không đợi bất kỳ Promise nào hoàn tất.
*   **Vấn đề:** Các dòng code viết phía sau `forEach` sẽ chạy trước khi các tác vụ bất đồng bộ bên trong `forEach` hoàn thành.
*   **Ví dụ:**
    ```javascript
    const fetchUsers = async () => {
      const ids = [1, 2, 3];
      ids.forEach(async (id) => {
        const user = await callApi(id);
        console.log(`Đã lấy xong user ${id}`);
      });
      console.log("Dòng này sẽ in ra TRƯỚC khi gọi API xong!");
    };
    ```

### 6.3. Bảng so sánh và Giải pháp thay thế cho song song thực sự

| Tiêu chí | `for...of` + `await` | `forEach` + `async` | `Promise.all()` |
| :--- | :--- | :--- | :--- |
| **Cơ chế chạy** | Tuần tự (Sequential) | Kích hoạt đồng thời, không đợi | Song song thực sự, có đợi |
| **Độ ưu tiên** | An toàn, dễ kiểm soát tuần tự | Dễ gây lỗi bất đồng bộ | Tối ưu hiệu năng nhất |

*   **Giải pháp cho chạy song song và đợi hoàn thành (Tối ưu hiệu năng):** Sử dụng `map()` để tạo mảng các Promise và dùng **`Promise.all()`** để đợi tất cả hoàn thành cùng lúc.
    ```javascript
    const fetchUsers = async () => {
      const ids = [1, 2, 3];
      // Kích hoạt tất cả các request song song và thu về mảng các Promise
      const promises = ids.map(id => callApi(id));
      // Đợi tất cả các Promise hoàn thành cùng lúc
      const users = await Promise.all(promises);
      console.log("Hoàn thành tất cả song song!");
    };
    ```

---

## 7. JAVASCRIPT CORE NÂNG CAO (ADVANCED JAVASCRIPT CORE)

Để nắm vững JavaScript ở cấp độ chuyên sâu, bạn cần hiểu rõ cơ chế vận hành bên dưới của JS Engine và cách quản lý tài nguyên của trình duyệt.

### 7.1. Event Loop: Cơ chế đa luồng giả lập của Single-threaded

JavaScript là ngôn ngữ **đơn luồng (single-threaded)**, nghĩa là tại một thời điểm chỉ có thể thực thi một dòng lệnh duy nhất trên một **Call Stack**.
Để xử lý các tác vụ bất đồng bộ (như gọi API, hẹn giờ) mà không làm đơ trình duyệt, JS Engine kết hợp với môi trường chạy (Web APIs của Browser hoặc C++ APIs của Node.js) thông qua cơ chế **Event Loop**:

```
[ Call Stack ] ──(Gặp tác vụ bất đồng bộ)──► [ Web APIs (Browser) ]
      ▲                                              │
      │ (Được Event Loop đẩy lên khi Stack trống)   │ (Hoàn thành)
      │                                              ▼
[ Event Loop ] ◄─────────────────────────── [ Callback Queues ]
                                              - Microtask Queue (Promise, queueMicrotask)
                                              - Macrotask Queue (setTimeout, DOM events)
```

*   **Quy trình vận hành:**
    1. Gặp tác vụ bất đồng bộ, JS gửi sang Web APIs xử lý ở nền và chạy tiếp các dòng lệnh đồng bộ khác trên Call Stack.
    2. Khi Web APIs xử lý xong, nó đẩy callback của tác vụ đó vào **Callback Queues**.
    3. **Event Loop** liên tục giám sát: Nếu **Call Stack** trống hoàn toàn, nó sẽ lấy các callback từ Callback Queues đẩy lên Call Stack để thực thi.
*   **Độ ưu tiên của Callback Queues:**
    *   **Microtask Queue (Ưu tiên cực cao):** Chứa các callback của `Promise.then/catch/finally`, `queueMicrotask`, `MutationObserver`. Event Loop sẽ quét sạch tất cả các tác vụ trong Microtask Queue cho đến khi rỗng hoàn toàn trước khi chuyển sang hàng đợi khác.
    *   **Macrotask Queue / Task Queue (Ưu tiên thấp hơn):** Chứa `setTimeout`, `setInterval`, `setImmediate`, các sự kiện click, keypress... Event Loop chỉ lấy ra **đúng 1 tác vụ** ở Macrotask Queue để thực thi, sau đó quay lại kiểm tra và dọn sạch Microtask Queue trước khi lấy tác vụ tiếp theo từ Macrotask.

### 7.2. Execution Context & Hoisting bản chất

*   **Execution Context (Ngữ cảnh thực thi):** Là môi trường mà mã nguồn JS được biên dịch và chạy. Gồm 2 giai đoạn:
    1.  **Creation Phase (Thiết lập):** JS Engine quét qua file code, cấp phát vùng nhớ cho các biến và khai báo hàm.
        *   Biến `var` được cấp vùng nhớ và gán mặc định là `undefined`.
        *   Biến `let` và `const` được cấp vùng nhớ nhưng **không khởi tạo giá trị**, đưa chúng vào vùng chết tạm thời (**Temporal Dead Zone - TDZ**).
        *   Các khai báo hàm (`function declaration`) được lưu toàn bộ thân hàm vào bộ nhớ.
    2.  **Execution Phase (Thực thi):** Chạy code từ trên xuống dưới, gán giá trị thực cho các biến và gọi hàm.
*   **Bản chất của Hoisting:** Hoisting không phải là "di chuyển vật lý" các dòng code lên đầu file, mà là kết quả của việc **cấp phát vùng nhớ trước khi chạy** (Creation Phase). Vì hàm đã được lưu vào bộ nhớ, bạn có thể gọi hàm trước khi định nghĩa nó. Nhưng gọi biến `let/const` trước khi khai báo sẽ gây lỗi `ReferenceError` do đang nằm trong TDZ.

### 7.3. Closure (Bao đóng) & Rò rỉ bộ nhớ (Memory Leak)

*   **Closure (Bao đóng):**
    *   *Định nghĩa:* Là một hàm có khả năng **ghi nhớ và truy cập** vào phạm vi chứa nó (Lexical Scope) ngay cả khi phạm vi đó đã thực thi xong và bị hủy khỏi Call Stack.
    *   *Vai trò thực tế:* Dùng để đóng gói dữ liệu an toàn (Encapsulation), tạo ra các biến Private mà bên ngoài không thể sửa đổi trực tiếp.
*   **Garbage Collection (Bộ thu gom rác):** JS tự động dọn dẹp các vùng nhớ không còn được tham chiếu từ gốc (GC Roots) bằng thuật toán **Mark-and-Sweep**.
*   **Memory Leak (Rò rỉ bộ nhớ):** Xảy ra khi các vùng nhớ không còn dùng tới nhưng vẫn bị tham chiếu vô ý làm GC không thể giải phóng:
    1.  *Accidental Globals:* Tạo biến toàn cục vô ý do quên viết từ khóa `let/const/var` (biến gắn vào window/global sẽ tồn tại mãi mãi).
    2.  *Forgotten Timers:* Dùng `setInterval` nhưng quên gọi `clearInterval` khi component/trang bị hủy (các biến trong callback vẫn bị giữ lại).
    3.  *Detached DOM Elements:* Xóa thẻ DOM khỏi giao diện nhưng vẫn giữ biến trỏ tới thẻ đó trong code JS.

### 7.4. Prototype & Prototype Chain (Cơ chế kế thừa nguyên mẫu)

JS không sử dụng lớp (Class-based OOP) làm nhân cốt lõi kế thừa như Java, mà kế thừa dựa trên **Nguyên mẫu (Prototype-based)**. Cú pháp `class` trong ES6 chỉ là Syntactic Sugar (lớp vỏ cú pháp bên ngoài).

```
[ Instance: user ] ──(__proto__)──► [ Prototype: User.prototype ] ──(__proto__)──► [ Object.prototype ] ──► null
```

*   **`prototype` vs `__proto__`:**
    *   `prototype`: Là thuộc tính chỉ có trên **Constructor Function** (hoặc Class). Nó định nghĩa những thuộc tính/phương thức mà các instance được tạo ra sau này sẽ được dùng chung.
    *   `__proto__`: Là thuộc tính ẩn có trên **mọi đối tượng (instance)**. Nó là con trỏ trỏ trực tiếp đến prototype của cha nó.
*   **Prototype Chain (Chuỗi nguyên mẫu):** Khi truy cập một thuộc tính của đối tượng, JS tìm trên chính đối tượng đó trước. Nếu không có, nó đi theo con trỏ `__proto__` lên prototype của cha, rồi lên ông nội, cho đến khi gặp `Object.prototype` (hoặc `null`). Nếu đi hết chuỗi mà vẫn không thấy, trả về `undefined`.

### 7.5. Explicit Binding: call(), apply(), bind()

Dùng để gán hoặc thay đổi ngữ cảnh của từ khóa `this` một cách chủ động:
*   **`call(thisArg, arg1, arg2, ...)`:** Gọi hàm ngay lập tức với ngữ cảnh `this` mới và truyền các tham số riêng biệt qua dấu phẩy.
*   **`apply(thisArg, [argsArray])`:** Giống `call()`, nhưng truyền các tham số dưới dạng một mảng (Array).
*   **`bind(thisArg, arg1, arg2, ...)`:** Không gọi hàm ngay. Nó trả về một **hàm mới** được liên kết (bound) vĩnh viễn với `thisArg`. Rất hữu ích khi làm việc với callback trong React hoặc Event Listener.

### 7.6. Advanced Comparisons & Copying

*   **`Object.is()` vs `===`:**
    *   `Object.is()` so sánh chính xác tuyệt đối mà không có các hành vi dị biệt của `===`:
        *   `NaN === NaN` -> `false` (lỗi kinh điển của JS). Nhưng `Object.is(NaN, NaN)` -> `true`.
        *   `-0 === +0` -> `true`. Nhưng `Object.is(-0, +0)` -> `false`.
*   **Shallow Copy vs Deep Copy:**
    *   *Shallow Copy (Sao chép nông):* Chỉ sao chép địa chỉ của các đối tượng con bên trong (dùng `...spread` hoặc `Object.assign`). Sửa đối tượng con ở bản copy sẽ làm thay đổi đối tượng con ở bản gốc.
    *   *Deep Copy (Sao chép sâu):* Sao chép toàn bộ các tầng đối tượng độc lập hoàn toàn.
        *   Cách nhanh: `JSON.parse(JSON.stringify(obj))` (Tuy nhiên sẽ mất các kiểu dữ liệu đặc biệt như Function, Date, undefined, RegExp).
        *   Cách chuẩn hóa hiện đại: Dùng hàm có sẵn **`structuredClone(obj)`** hỗ trợ deep copy chuẩn xác mọi kiểu dữ liệu.

---
*Tài liệu học tập - cs-fundamentals/web-development/js-basic*

