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

### 5.1. Cơ chế lan truyền sự kiện (Event Propagation)
Khi một sự kiện xảy ra trên một phần tử nằm sâu trong DOM, sự kiện đó không chỉ kích hoạt riêng trên phần tử đó mà lan truyền qua 3 giai đoạn:

```text
 1. Capturing Phase (Giai đoạn bắt) -> Đi từ Document gốc đi xuống phần tử đích.
 2. Target Phase (Giai đoạn đích)    -> Sự kiện kích hoạt tại chính phần tử đích.
 3. Bubbling Phase (Giai đoạn nổi bọt)-> Sự kiện lan truyền ngược lên Document gốc.
```

*   **Event Bubbling (Nổi bọt sự kiện):** Mặc định, các hàm lắng nghe sự kiện (`addEventListener`) sẽ lắng nghe ở giai đoạn nổi bọt. Nếu bạn click vào một thẻ `<button>` nằm trong `<div>`, sự kiện click sẽ kích hoạt ở `<button>` trước, sau đó nổi bọt lên kích hoạt sự kiện click của `<div>`, rồi lên `<body>`, và cuối cùng là `document`.
    *   *Ngăn chặn nổi bọt:* Sử dụng `event.stopPropagation()` để chặn không cho sự kiện lan truyền lên các thẻ cha.
    *   *Ngăn chặn hành vi mặc định:* Sử dụng `event.preventDefault()` để chặn hành động mặc định của trình duyệt (ví dụ: click thẻ `<a>` không bị chuyển trang, submit form không bị load lại trang).

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
