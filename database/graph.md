# NGÔN NGỮ TRUY VẤN ĐỒ THỊ: CYPHER

Tài liệu này tập trung chi tiết vào cú pháp truy vấn Cypher (được sử dụng phổ biến trong Neo4j), giải thích cấu trúc dựa trên nghệ thuật ASCII-art và cách viết các câu truy vấn từ cơ bản đến nâng cao để khai thác mối quan hệ chằng chịt trong đồ thị.

---

## 1. CÚ PHÁP ĐỒ THỊ DỰA TRÊN ASCII-ART

Điểm độc đáo nhất của Cypher là cú pháp mô tả mô hình đồ thị trực quan bằng cách sử dụng các ký tự văn bản thông thường (ASCII-art). Bạn vẽ mô hình đồ thị như thế nào, bạn viết câu truy vấn y hệt như thế:

*   **Đỉnh / Nút (Nodes) $\rightarrow$ Biểu diễn bằng cặp ngoặc đơn `()`**:
    *   Ngoặc đơn mô phỏng hình tròn của một nút.
    *   Cú pháp: `(tên_biến:Tên_Nhãn {các_thuộc_tính})`
    *   Ví dụ: `(u:User {name: "An", age: 28})`
        *   `u`: Biến đại diện cho node (dùng để tham chiếu ở phần sau của câu query).
        *   `:User`: Nhãn (Label) phân loại node.
        *   `{name: "An", age: 28}`: Thuộc tính lưu trữ trong node dưới dạng key-value.
*   **Mối quan hệ / Cạnh (Relationships/Edges) $\rightarrow$ Biểu diễn bằng nét vẽ mũi tên `-->` hoặc `<--`**:
    *   Mũi tên chỉ hướng của mối quan hệ từ node nguồn sang node đích.
    *   Nếu mối quan hệ không cần quan tâm đến hướng, viết dạng nét ngang `--`.
    *   Để khai báo thông tin của mối quan hệ, đặt trong ngoặc vuông `[]` ở giữa nét vẽ: `-[tên_biến:TÊN_LOẠI {các_thuộc_tính}]->`
    *   Ví dụ: `-[r:FRIEND_WITH {since: 2020}]->`
        *   `r`: Biến đại diện cho mối quan hệ.
        *   `:FRIEND_WITH`: Loại mối quan hệ (Relationship Type).
        *   `{since: 2020}`: Thuộc tính của mối quan hệ.

---

## 2. CÁC PHÉP TRUY VẤN CYPHER CƠ BẢN

Dưới đây là phân tích chi tiết cấu trúc các câu lệnh cơ bản phục vụ CRUD dữ liệu trong Graph Database.

### 2.1. Tạo mới dữ liệu (CREATE)
Sử dụng để tạo các Node và thiết lập mối quan hệ giữa chúng trong cùng một lệnh:

```cypher
// 1. Tạo node User tên "An"
CREATE (a:User {name: "An", age: 28})

// 2. Tạo node User tên "Bình"
CREATE (b:User {name: "Bình", age: 30})

// 3. Tạo mối quan hệ FRIEND_WITH hướng từ "An" sang "Bình"
CREATE (a)-[:FRIEND_WITH {since: 2020}]->(b)
```

---

### 2.2. Tìm kiếm dữ liệu (MATCH ... WHERE ... RETURN)
Cypher sử dụng cơ chế so khớp mẫu (Pattern Matching) bằng từ khóa `MATCH`.

*   **Tìm kiếm thông tin của một người cụ thể:**
    ```cypher
    MATCH (u:User)                 // Tìm tất cả các node có nhãn User
    WHERE u.name = "An"            // Điều kiện lọc thuộc tính (giống WHERE trong SQL)
    RETURN u.age                   // Trả về thuộc tính cần hiển thị (giống SELECT trong SQL)
    ```

*   **Tìm danh sách bạn bè của "An":**
    ```cypher
    MATCH (u:User {name: "An"})-[:FRIEND_WITH]->(f:User)
    RETURN f.name, f.age
    ```
    *Giải nghĩa mẫu vẽ:* Tìm node `u` là User tên "An", đi theo hướng mũi tên quan hệ `FRIEND_WITH` để tìm đến các node `f` là User. Trả về tên và tuổi của các node `f` tìm được.

---

### 2.3. Cập nhật thuộc tính (SET)
```cypher
MATCH (u:User {name: "An"})
SET u.age = 29, u.status = "active" // Cập nhật hoặc thêm thuộc tính mới
RETURN u
```

---

### 2.4. Xóa dữ liệu (DELETE & DETACH DELETE)
Trong cơ sở dữ liệu đồ thị, bạn không được phép xóa một Node nếu Node đó vẫn đang có các mối quan hệ (cạnh) kết nối với các Node khác (để tránh lỗi tham chiếu mồ côi).

*   **Xóa mối quan hệ (cạnh):**
    ```cypher
    MATCH (a:User {name: "An"})-[r:FRIEND_WITH]->(b:User {name: "Bình"})
    DELETE r
    ```
*   **Xóa Node an toàn (Xóa sạch các quan hệ liên quan trước khi xóa Node):**
    ```cypher
    MATCH (u:User {name: "An"})
    DETACH DELETE u  // Tự động tìm và xóa tất cả các cạnh kết nối vào u, sau đó xóa u
    ```

---

## 3. TRUY VẤN QUAN HỆ NHIỀU TẦNG (MULTI-HOP QUERY)

Sức mạnh thực sự của Cypher tỏa sáng ở các phép truy vấn duyệt đồ thị đa cấp, việc mà SQL phải viết hàng chục dòng JOIN rất chậm.

### 3.1. Tìm bạn của bạn (2-hop friends)
Tìm kiếm những người là bạn của bạn của "An", nhưng bản thân "An" chưa kết bạn với họ để đưa ra gợi ý kết bạn:

```cypher
MATCH (u:User {name: "An"})-[:FRIEND_WITH]->(friend)-[:FRIEND_WITH]->(fof:User)
WHERE NOT (u)-[:FRIEND_WITH]->(fof) AND u <> fof
RETURN DISTINCT fof.name AS SuggestedFriend
```

*   *Phân tích luồng đi của câu query:*
    1.  `MATCH (u:User {name: "An"})-[:FRIEND_WITH]->(friend)`: Đi 1 bước nhảy (1-hop) tìm bạn bè trực tiếp của An, gán vào biến `friend`.
    2.  `-[:FRIEND_WITH]->(fof:User)`: Từ node `friend` đi tiếp 1 bước nhảy nữa (2-hop) tìm bạn của họ, gán vào biến `fof` (Friend of Friend).
    3.  `WHERE NOT (u)-[:FRIEND_WITH]->(fof)`: Loại bỏ các node `fof` mà bản thân An đã kết bạn trực tiếp rồi.
    4.  `AND u <> fof`: Đảm bảo node `fof` tìm được không trùng lại chính An (tránh vòng lặp quay lại ban đầu).
    5.  `RETURN DISTINCT fof.name`: Trả về danh sách tên duy nhất của các ứng viên gợi ý.

---

### 3.2. Truy vấn độ sâu biến động (Variable-Length Path)
Nếu muốn tìm mối liên kết giữa An và một người tên là "Cường" qua tối đa 4 bước nhảy (tìm xem họ có mối quan hệ bắc cầu nào không):

```cypher
MATCH path = (u:User {name: "An"})-[:FRIEND_WITH*1..4]->(target:User {name: "Cường"})
RETURN path
```

*   *Ý nghĩa cú pháp:*
    *   `*1..4` chỉ định độ dài của đường đi có số bước nhảy dao động từ 1 đến 4.
    *   `path = (...)` gán toàn bộ chuỗi node và cạnh tìm được vào biến `path` để hiển thị sơ đồ đường đi chi tiết.
