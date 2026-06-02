# NỀN TẢNG REACT (REACT FUNDAMENTALS)

Tài liệu này hệ thống hóa các kiến thức từ căn bản đến nâng cao về React, bao gồm cơ chế hoạt động Virtual DOM, cấu trúc Component/JSX, quản lý luồng dữ liệu, cách làm chủ các React Hooks cốt lõi và tối ưu hóa hiệu năng render.

---

## 1. VIRTUAL DOM VÀ THUẬT TOÁN ĐỐI CHIẾU (RECONCILIATION)

### 1.1. Virtual DOM vs Real DOM
*   **Real DOM (DOM thật):**
    *   *Vấn đề:* DOM thật của trình duyệt rất nặng nề và phức tạp. Mỗi khi bạn dùng JS thuần để sửa đổi một thuộc tính nhỏ (như thêm text vào thẻ `p`), trình duyệt phải tính toán lại toàn bộ bố cục (Layout/Reflow) và vẽ lại giao diện (Repaint). Tiến trình này cực kỳ tốn hiệu năng nếu trang web có hàng ngàn phần tử.
*   **Virtual DOM (DOM ảo):**
    *   *Bản chất:* Là một bản sao biểu diễn nhẹ dạng Javascript Object (chỉ chứa các thông tin tối thiểu như tên thẻ, thuộc tính, con cái) nằm trên bộ nhớ RAM.
    *   *Hoạt động:* Khi dữ liệu (`state`) thay đổi, React không cập nhật thẳng lên DOM thật. Nó tạo ra một Virtual DOM Tree mới, so sánh với Virtual DOM Tree cũ để tìm ra các điểm khác biệt thực sự.

### 1.2. Thuật toán Diffing và Thuộc tính `key`
*   **Thuật toán Diffing:** Là thuật toán so sánh hai cây Virtual DOM để tối thiểu hóa số lần cập nhật DOM thật. Nó hoạt động với độ phức tạp $O(n)$ nhờ giả định: hai phần tử có kiểu khác nhau sẽ tạo ra các cây khác nhau, và lập trình viên có thể đánh dấu phần tử con bằng thuộc tính `key`.
*   **Tại sao thuộc tính `key` trong mảng lại cực kỳ quan trọng?**
    *   Khi render một danh sách các phần tử (ví dụ sử dụng `.map()`), React cần thuộc tính `key` duy nhất và cố định cho mỗi phần tử để nhận diện xem phần tử đó vừa bị thêm mới, bị xóa đi, hay chỉ dịch chuyển vị trí.
    *   *Tránh dùng Index làm key:* Nếu bạn dùng chỉ số mảng (index 0, 1, 2...) làm `key`, khi bạn xóa hoặc chèn một phần tử vào giữa danh sách, index của các phần tử phía sau sẽ bị thay đổi hoàn toàn. React sẽ tưởng rằng tất cả các phần tử đó bị thay đổi và ép buộc re-render lại toàn bộ, gây sụt giảm hiệu năng nghiêm trọng và làm mất trạng thái (state) nội bộ của các input con.

---

## 2. COMPONENT VÀ CÚ PHÁP JSX

### 2.1. JSX là gì?
*   JSX (JavaScript XML) là cú pháp mở rộng cho phép viết code giống HTML ngay bên trong mã nguồn JavaScript.
*   *Bản chất bên dưới:* Trình duyệt không hiểu được JSX. Trong quá trình build (qua Babel/SWC), toàn bộ mã JSX sẽ được biên dịch thành các hàm JavaScript thuần là `React.createElement()` (hoặc `jsx()` ở các phiên bản React mới).
    ```javascript
    // JSX:
    const element = <h1 className="title">Hello</h1>;

    // Sau khi biên dịch:
    const element = React.createElement('h1', { className: 'title' }, 'Hello');
    ```

### 2.2. Component Stateful vs Stateless
*   **Stateless Component (Component không trạng thái):** Chỉ nhận dữ liệu từ cha qua `props` và render giao diện tĩnh. Không tự quản lý vòng đời hay dữ liệu thay đổi của riêng nó. (Thường là các component giao diện thuần túy).
*   **Stateful Component (Component có trạng thái):** Tự quản lý và duy trì trạng thái dữ liệu nội bộ (`state`) có khả năng thay đổi theo thời gian thông qua hành vi người dùng, và tự động kích hoạt re-render khi `state` thay đổi.

---

## 3. PROPS VS STATE (LUỒNG DỮ LIỆU MỘT CHIỀU)

React tuân thủ chặt chẽ kiến trúc **Dòng dữ liệu một chiều (Unidirectional Data Flow)**: dữ liệu luôn chảy từ trên xuống (từ Component Cha xuống Component Con) và không thể chảy ngược lại.

| Tiêu chí | Props | State |
| :--- | :--- | :--- |
| **Bản chất** | Là tham số đầu vào được truyền từ Component Cha xuống. | Là trạng thái dữ liệu nội bộ do chính Component đó quản lý. |
| **Tính đột biến** | **Bất biến (Read-only)**. Component con tuyệt đối không được tự ý chỉnh sửa giá trị `props` nhận về. | **Có thể thay đổi** thông qua hàm cập nhật tương ứng (ví dụ: `setState` hoặc `setCount`). |
| **Kích hoạt render** | Khi `props` truyền vào thay đổi, Component con sẽ tự động re-render. | Khi `state` thay đổi, chính Component đó và tất cả con cái của nó sẽ re-render. |

---

## 4. LÀM CHỦ REACT HOOKS CỐT LÕI

Hooks (từ bản 16.8) cho phép Function Component sử dụng các tính năng quản lý trạng thái và vòng đời mà trước đây chỉ có Class Component mới làm được.

### 4.1. Quy tắc sử dụng Hooks (Rules of Hooks)
1.  Chỉ gọi Hooks ở cấp cao nhất (Top-level). Không gọi bên trong vòng lặp (`for`), câu lệnh điều kiện (`if`), hay các hàm lồng nhau. Điều này đảm bảo React luôn gọi Hooks theo đúng thứ tự cố định ở mỗi lần render.
2.  Chỉ gọi Hooks từ Function Component hoặc Custom Hooks của React. Không gọi từ hàm JS thường.

### 4.2. Các Hooks cốt lõi
*   **`useState(initialState)`:** Khởi tạo và cập nhật trạng thái của Component.
*   **`useEffect(callback, dependencyArray)`:** Xử lý các tác vụ ngoài lề (Side-Effects) như call API, đăng ký sự kiện, thao tác DOM trực tiếp.
    *   *Không truyền dependencies:* Chạy lại callback sau **mọi lần** render.
    *   *Truyền mảng rỗng `[]`:* Chỉ chạy **duy nhất 1 lần** sau khi component mount (lần render đầu tiên).
    *   *Truyền biến `[count]`:* Chạy callback ở lần đầu và bất cứ khi nào giá trị `count` thay đổi.
    *   *Clean-up Function:* Nếu hàm callback return về một function, React sẽ chạy function đó trước khi component bị unmount hoặc trước khi chạy lại callback lần sau để dọn dẹp bộ nhớ (ví dụ: `clearTimeout`, `removeEventListener`).

### 4.3. Hooks tối ưu hiệu năng và quản lý tham chiếu
*   **`useMemo(() => value, dependencies)`:** Ghi nhớ **giá trị** của một phép toán phức tạp. Tránh việc tính toán lại vô ích ở mỗi lần re-render nếu các dependency không thay đổi.
*   **`useCallback(() => function, dependencies)`:** Ghi nhớ **tham chiếu của một hàm**. Tránh việc tạo lại hàm mới ở mỗi lần render, rất hữu ích khi truyền hàm làm props cho các component con đã được tối ưu bằng `React.memo`.
*   **`useRef(initialValue)`:**
    *   *Vai trò 1:* Tạo tham chiếu trực tiếp đến một phần tử DOM thật (ví dụ để tự động focus vào input).
    *   *Vai trò 2:* Lưu trữ một giá trị bất biến qua các lần render mà **không làm kích hoạt re-render** khi giá trị đó thay đổi (ví dụ: lưu trữ ID của `setInterval`).

---

## 5. QUẢN LÝ TRẠNG THÁI TẬP TRUNG (STATE MANAGEMENT)

Khi ứng dụng phát triển lớn, việc truyền `state` qua quá nhiều cấp Component trung gian (vấn đề **Props Drilling**) sẽ làm code cực kỳ rối và khó kiểm soát. Chúng ta cần các giải pháp quản lý trạng thái tập trung:

1.  **React Context API (Tích hợp sẵn):**
    *   *Cách hoạt động:* Tạo ra một "kho chứa" dữ liệu ở cấp cha cao nhất (`Provider`), bất kỳ Component con cháu nào ở mọi cấp độ đều có thể đăng ký sử dụng trực tiếp dữ liệu này (`Consumer` hoặc `useContext`) mà không cần truyền qua các cấp trung gian.
    *   *Hạn chế:* Mỗi khi giá trị trong Context thay đổi, **tất cả** các component có tiêu thụ Context đó đều bị ép buộc re-render, ngay cả khi chúng chỉ sử dụng một thuộc tính nhỏ không đổi. Không phù hợp cho các dữ liệu cập nhật liên tục với tần suất cao.
2.  **Thư viện ngoài (Redux, Zustand):**
    *   *Zustand (Hiện đại & Khuyên dùng):* Cực kỳ nhẹ, khai báo đơn giản bằng các hàm hook, hiệu năng tối ưu nhờ cơ chế đăng ký sử dụng có chọn lọc (Selectors) - chỉ re-render khi thuộc tính cụ thể mà component đăng ký bị thay đổi.
    *   *Redux:* Theo mô hình luồng dữ liệu một chiều nghiêm ngặt (Action -> Reducer -> Store). Cấu trúc code khá dài dòng (boilerplate) nhưng cực kỳ mạnh mẽ cho các dự án lớn có luồng nghiệp vụ phức tạp.
