# NGÔN NGỮ TRUY VẤN SQL (STRUCTURED QUERY LANGUAGE)

Tài liệu này tập trung chi tiết vào cú pháp, các thành phần cấu thành một câu truy vấn SQL, sự khác biệt giữa thứ tự viết lệnh và thứ tự thực thi vật lý của cơ sở dữ liệu, và phân tích sâu sắc các phép JOIN kèm ví dụ thực tế.

---

## 1. THÀNH PHẦN VÀ CẤU TRÚC CÂU LỆNH SELECT

Một câu lệnh truy vấn dữ liệu (SELECT) trong SQL có cấu trúc chặt chẽ. Khi viết và khi Database Engine thực thi, thứ tự xử lý hoàn toàn khác nhau. Đây là điểm mấu chốt thường xuyên được khai thác khi phỏng vấn.

### 1.1. Thứ tự viết (Writing Order) vs Thứ tự thực thi thực tế (Execution Order)

Khi viết code, ta tuân theo cú pháp ngữ pháp quy định. Tuy nhiên, Database Engine sẽ thực thi các mệnh đề theo một trình tự logic hoàn toàn khác để tối ưu hóa việc lọc dữ liệu.

| Thứ tự | Mệnh đề khi viết (Writing) | Mệnh đề khi thực thi (Execution) | Vai trò và cơ chế xử lý vật lý |
| :---: | :--- | :--- | :--- |
| **1** | `SELECT` | **`FROM`** | Xác định bảng nguồn dữ liệu cần truy vấn. Database cần biết bắt đầu lấy dữ liệu từ đâu. |
| **2** | `FROM` | **`ON`** | Xác định điều kiện liên kết (JOIN) giữa các bảng. |
| **3** | `JOIN` | **`JOIN`** | Thực hiện phép kết hợp các bảng để tạo ra bảng tạm thời chứa đầy đủ thông tin. |
| **4** | `WHERE` | **`WHERE`** | Lọc các dòng dữ liệu của bảng tạm thời dựa trên các điều kiện lọc (chỉ giữ lại các dòng thỏa mãn). |
| **5** | `GROUP BY` | **`GROUP BY`** | Gom nhóm các dòng dữ liệu có cùng giá trị trên các cột được chỉ định thành một nhóm duy nhất. |
| **6** | `HAVING` | **`HAVING`** | Lọc các nhóm dữ liệu sau khi đã gom nhóm (khác `WHERE` là lọc từng dòng đơn lẻ trước khi gom). |
| **7** | `ORDER BY` | **`SELECT`** | Chọn ra các cột dữ liệu cụ thể cần hiển thị cho người dùng và tính toán các hàm tổng hợp (SUM, AVG...). |
| **8** | `LIMIT / OFFSET` | **`DISTINCT`** | Loại bỏ các dòng dữ liệu trùng lặp nếu có từ khóa DISTINCT. |
| **9** | - | **`ORDER BY`** | Sắp xếp kết quả trả về theo thứ tự tăng/giảm dần của các cột được chọn. |
| **10**| - | **`LIMIT / OFFSET`**| Giới hạn số lượng dòng dữ liệu trả về cho client (ví dụ lấy 10 dòng đầu). |

### 1.2. Giải thích logic tại sao có sự khác biệt?

Sự chênh lệch giữa thứ tự viết và thực thi dẫn đến các quy tắc bắt buộc trong SQL. Hãy nhớ câu trả lời phỏng vấn kinh điển sau:

*   **Tại sao không thể dùng ALIAS (Tên giả lập) của cột được định nghĩa trong SELECT ở mệnh đề WHERE?**
    *   *Câu trả lời:* Bởi vì mệnh đề `WHERE` được thực thi **trước** mệnh đề `SELECT`. Tại thời điểm `WHERE` đang lọc dữ liệu, Database Engine vẫn chưa chạy đến bước `SELECT` nên nó hoàn toàn chưa biết gì về các ALIAS được định nghĩa ở đó.
    *   *Ví dụ lỗi:*
        ```sql
        -- LỖI: Cột "total_salary" không tồn tại ở mệnh đề WHERE
        SELECT salary + bonus AS total_salary
        FROM employees
        WHERE total_salary > 5000;
        ```
    *   *Cách sửa đúng:*
        ```sql
        SELECT salary + bonus AS total_salary
        FROM employees
        WHERE (salary + bonus) > 5000;
        ```
*   **Tại sao có thể dùng ALIAS của cột trong SELECT ở mệnh đề ORDER BY?**
    *   *Câu trả lời:* Bởi vì `ORDER BY` được thực thi **sau** mệnh đề `SELECT`. Tại thời điểm này, các cột và ALIAS đã được tính toán và định hình xong, nên `ORDER BY` hoàn toàn có thể đọc và sắp xếp dữ liệu dựa trên các ALIAS đó.

---

## 2. KỸ THUẬT LIÊN KẾT BẢNG (JOIN OPERATIONS)

Phép JOIN là trung tâm của ngôn ngữ SQL, dùng để liên kết dữ liệu giữa nhiều bảng dựa trên các cột chung (thường là khóa ngoại trỏ tới khóa chính).

### Bảng mẫu để minh họa:
*   **Bảng `users` (Danh sách người dùng):**
    | id | name |
    | :--- | :--- |
    | 1 | An |
    | 2 | Bình |
    | 3 | Cường |

*   **Bảng `orders` (Danh sách đơn hàng):**
    | order_id | user_id | product |
    | :--- | :--- | :--- |
    | 101 | 1 | Laptop |
    | 102 | 1 | Chuột |
    | 103 | 4 | Bàn phím | *(User_id 4 không tồn tại trong bảng users)*

---

### 2.1. INNER JOIN (Khớp nối trong)
*   **Bản chất:** Chỉ trả về các dòng dữ liệu khi có sự trùng khớp giá trị cột liên kết ở cả hai bảng.
*   **Sơ đồ Venn mô tả:**
    ```
      Bảng A     Bảng B
     [  A  (  A=B  )  B  ]
              ^^^^^ -> Chỉ lấy phần giao nhau
    ```
*   **Ví dụ câu lệnh:**
    ```sql
    SELECT users.name, orders.product
    FROM users
    INNER JOIN orders ON users.id = orders.user_id;
    ```
*   **Kết quả trả về:**
    | name | product |
    | :--- | :--- |
    | An | Laptop |
    | An | Chuột |
    *(Bình, Cường bị loại vì không có đơn hàng; đơn hàng 103 bị loại vì user_id 4 không khớp với ai).*

---

### 2.2. LEFT JOIN / LEFT OUTER JOIN (Khớp nối bên trái)
*   **Bản chất:** Lấy toàn bộ dữ liệu ở bảng bên trái (bảng đứng trước từ khóa LEFT JOIN) và các dòng khớp ở bảng bên phải. Nếu không có dòng khớp ở bảng bên phải, các cột tương ứng sẽ được điền giá trị `NULL`.
*   **Sơ đồ Venn mô tả:**
    ```
      Bảng A     Bảng B
     [  A  (  A=B  )     ]
      ^^^^^^^^^^^^ -> Lấy toàn bộ bảng A (bao gồm cả phần giao)
    ```
*   **Ví dụ câu lệnh:**
    ```sql
    SELECT users.name, orders.product
    FROM users
    LEFT JOIN orders ON users.id = orders.user_id;
    ```
*   **Kết quả trả về:**
    | name | product |
    | :--- | :--- |
    | An | Laptop |
    | An | Chuột |
    | Bình | NULL |
    | Cường | NULL |
    *(Bình và Cường vẫn hiển thị dù không mua gì, thông tin sản phẩm của họ được điền là NULL).*

---

### 2.3. RIGHT JOIN / RIGHT OUTER JOIN (Khớp nối bên phải)
*   **Bản chất:** Ngược lại với LEFT JOIN, lấy toàn bộ dữ liệu ở bảng bên phải (bảng đứng sau từ khóa RIGHT JOIN) và các dòng khớp ở bảng bên trái. Nếu không có dòng khớp ở bảng bên trái, các cột tương ứng sẽ được điền giá trị `NULL`.
*   **Sơ đồ Venn mô tả:**
    ```
      Bảng A     Bảng B
             (  A=B  )  B  ]
              ^^^^^^^^^^^^ -> Lấy toàn bộ bảng B (bao gồm cả phần giao)
    ```
*   **Ví dụ câu lệnh:**
    ```sql
    SELECT users.name, orders.product
    FROM users
    RIGHT JOIN orders ON users.id = orders.user_id;
    ```
*   **Kết quả trả về:**
    | name | product |
    | :--- | :--- |
    | An | Laptop |
    | An | Chuột |
    | NULL | Bàn phím |
    *(Đơn hàng 103 mua Bàn phím có user_id = 4 không tồn tại trong bảng users nên cột name bị điền NULL).*

---

### 2.4. FULL OUTER JOIN (Khớp nối toàn phần)
*   **Bản chất:** Trả về tất cả các dòng dữ liệu của cả hai bảng. Nếu có dòng khớp thì kết hợp lại, nếu không khớp thì điền `NULL` vào các cột thiếu của bảng còn lại.
*   **Sơ đồ Venn mô tả:**
    ```
      Bảng A     Bảng B
     [  A  (  A=B  )  B  ]
      ^^^^^^^^^^^^^^^^^^^^ -> Lấy tất cả mọi thứ ở cả hai bảng
    ```
*   **Ví dụ câu lệnh:**
    ```sql
    SELECT users.name, orders.product
    FROM users
    FULL OUTER JOIN orders ON users.id = orders.user_id;
    ```
*   **Kết quả trả về:**
    | name | product |
    | :--- | :--- |
    | An | Laptop |
    | An | Chuột |
    | Bình | NULL |
    | Cường | NULL |
    | NULL | Bàn phím |
