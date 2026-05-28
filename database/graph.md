# Tìm Hiểu Sâu Về Cơ Sở Dữ Liệu Đồ Thị (Graph Database)

Cơ sở dữ liệu đồ thị (Graph Database) thuộc nhóm NoSQL nhưng được thiết kế chuyên biệt để lưu trữ và truy vấn các dữ liệu có mối quan hệ chằng chịt, phức tạp và đan xen nhiều tầng lớp. Thay vì cố gắng biểu diễn mối quan hệ bằng bảng trung gian hay các khóa ngoại, Graph Database coi **mối quan hệ là đối tượng ưu tiên hàng đầu** (first-class citizen) trong hệ thống.

---

## 1. Mô Hình Labeled Property Graph (LPG)

Mô hình phổ biến nhất được sử dụng trong các hệ cơ sở dữ liệu đồ thị như Neo4j là mô hình **Đồ thị Thuộc tính có Nhãn**:

```mermaid
graph LR
    User1((Node: User\nname: "An"\nage: 28)) -->|Edge: FRIEND_WITH\nsince: 2020| User2((Node: User\nname: "Bình"\nage: 30))
    User1 -->|Edge: LIKES| Product((Node: Product\nname: "MacBook Pro"\nprice: 1999))
```

- **Nodes (Đỉnh / Nút)**: Đại diện cho các thực thể (như Người, Sản phẩm, Địa điểm). Mỗi Node có thể có một hoặc nhiều **Labels** (Nhãn) để phân loại (ví dụ: `:User`, `:Product`).
- **Edges / Relationships (Cạnh / Mối quan hệ)**: Kết nối các Node lại với nhau. Mối quan hệ luôn có **hướng** (Direction - từ Node nguồn đến Node đích) và có một **Type** (Kiểu quan hệ - ví dụ: `[:FRIEND_WITH]`, `[:PURCHASED]`).
- **Properties (Thuộc tính)**: Cả Nodes và Edges đều có thể chứa các cặp key-value thuộc tính (ví dụ: Node User có thuộc tính `name`, `age`; Edge `FRIEND_WITH` có thuộc tính `since` để chỉ thời gian bắt đầu làm bạn).

> **Phân biệt Labeled Property Graph (LPG) vs RDF (Resource Description Framework)**:
> - **LPG**: Tập trung vào lưu trữ các thuộc tính phức tạp trên cả node và edge. Rất thích hợp cho các ứng dụng phần mềm doanh nghiệp, mạng xã hội nhờ tốc độ truy vấn cao.
> - **RDF**: Biểu diễn dữ liệu dưới dạng bộ ba (Subject - Predicate - Object). Thường dùng trong Semantic Web (web ngữ nghĩa), xây dựng Ontology chuẩn hóa và liên kết dữ liệu mở trên toàn cầu (Linked Open Data).

---

## 2. Cơ Chế Lưu Trữ Độc Đáo: Index-Free Adjacency

Sức mạnh lớn nhất giúp Graph Database vượt trội hoàn toàn so với SQL khi truy vấn liên kết đa cấp là cơ chế **Index-Free Adjacency (Kề cận không cần chỉ mục)**.

### 2.1. Vấn đề của SQL (Global Index Lookup)
Trong RDBMS, khi muốn tìm bạn của bạn của một người (2-hop friend), ta phải thực hiện phép `JOIN` giữa bảng User và bảng Friend, rồi `JOIN` tiếp một lần nữa.
Về mặt vật lý, DBMS phải sử dụng chỉ mục (Index) trên khóa ngoại để tìm kiếm trên cây B+ Tree:
- Với mỗi bước nhảy (hop), DBMS thực hiện tìm kiếm trên index cấp toàn cục (Global Index Lookup) có độ phức tạp $O(\log N)$.
- Khi số cấp nhảy tăng lên (3-hop, 4-hop, hoặc n-hop), thời gian truy vấn sẽ tăng theo cấp số nhân (exponential slowdown) và nhanh chóng làm nghẽn hệ thống.

### 2.2. Giải pháp của Graph Database (Index-Free Adjacency)
Với Index-Free Adjacency, Graph Database lưu trữ trực tiếp địa chỉ vật lý (memory address hoặc disk offset) của các node lân cận ngay trong bản ghi của node hiện tại.

```
[ Node: An ] ---------> Trỏ trực tiếp tới địa chỉ bộ nhớ của ------> [ Node: Bình ]
                        (Quan hệ: FRIEND_WITH)
```

- **Không dùng Global Index**: Khi duyệt đồ thị, bộ máy thực thi chỉ cần đi theo các con trỏ địa chỉ này để nhảy từ node này sang node kia.
- **Độ phức tạp cố định $O(1)$**: Thời gian duyệt qua một mối quan hệ (1 bước nhảy) chỉ mất thời gian hằng số $O(1)$ vì nó chỉ là một phép toán dereference con trỏ.
- **Hiệu năng độc lập với kích thước dữ liệu**: Dù cơ sở dữ liệu có 10 nghìn hay 10 tỷ node, tốc độ duyệt từ Node A sang các Node bạn bè kề cạnh của nó vẫn không thay đổi, bởi vì hệ thống không phải quét một bảng chỉ mục khổng lồ nào cả.

---

## 3. Ngôn Ngữ Truy Vấn Cypher

Cypher là ngôn ngữ truy vấn đồ thị mang tính khai báo (declarative) được Neo4j phát triển và đã trở thành một chuẩn công nghiệp mở (openCypher). Cypher sử dụng nghệ thuật ASCII-art để biểu diễn các mô hình đồ thị trực quan:

- `()` đại diện cho một Node.
- `-->` hoặc `<--` đại diện cho một Edge (Mối quan hệ có hướng).
- `[]` nằm trong mũi tên đại diện cho thông tin của Edge.

### Ví dụ các truy vấn cơ bản:

#### 1. Tạo dữ liệu (Create):
```cypher
CREATE (a:User {name: "An", age: 28})
CREATE (b:User {name: "Bình", age: 30})
CREATE (a)-[:FRIEND_WITH {since: 2020}]->(b)
```

#### 2. Tìm kiếm bạn bè của "An" (Match):
```cypher
MATCH (u:User {name: "An"})-[:FRIEND_WITH]->(f:User)
RETURN f.name, f.age
```

#### 3. Tìm kiếm bạn của bạn (2-hop friends) và gợi ý kết bạn:
```cypher
MATCH (u:User {name: "An"})-[:FRIEND_WITH]->(friend)-[:FRIEND_WITH]->(fof:User)
WHERE NOT (u)-[:FRIEND_WITH]->(fof) AND u <> fof
RETURN DISTINCT fof.name AS SuggestedFriend
```

---

## 4. Các Thuật Toán Đồ Thị Cốt Lõi (Graph Algorithms)

Graph Database tích hợp sẵn các thuật toán đồ thị kinh điển giúp giải quyết các bài toán phân tích mạng lưới phức tạp:

- **Pathfinding Algorithms (Tìm đường đi)**:
  - **Dijkstra / A\***: Tìm đường đi ngắn nhất giữa hai node (ví dụ ứng dụng trong Google Maps hoặc định tuyến mạng).
  - **All Pairs Shortest Path**: Tính toán khoảng cách giữa tất cả các cặp node.
- **Centrality Algorithms (Độ trung tâm / Đo lường tầm ảnh hưởng)**:
  - **PageRank**: Đo lường mức độ quan trọng của một node dựa trên số lượng và chất lượng các liên kết trỏ đến nó (thuật toán gốc của Google Search).
  - **Betweenness Centrality**: Xác định các node đóng vai trò là "cầu nối" (bridge) luân chuyển thông tin giữa các nhóm khác nhau trong đồ thị.
- **Community Detection (Phát hiện cộng đồng / Phân cụm)**:
  - **Louvain Algorithm**: Phát hiện các nhóm/cộng đồng có liên kết nội bộ cực kỳ chặt chẽ với nhau nhưng liên kết lỏng lẻo với nhóm ngoài (phát hiện hội nhóm lừa đảo, nhóm sở thích mạng xã hội).

---

## 5. Các Kịch Bản Sử Dụng Thực Tế (Use Cases)

Graph Database không thay thế hoàn toàn SQL hay Document Store, nhưng nó là lựa chọn bắt buộc cho các bài toán sau:

1. **Hệ thống Gợi ý (Recommendation Engines)**:
   - Gợi ý sản phẩm dựa trên hành vi mua sắm của những người có chung sở thích, bạn bè. Ví dụ: "Bạn bè của bạn cũng thích sản phẩm này".
2. **Phát hiện Gian lận tài chính (Fraud Detection)**:
   - Phát hiện các nhóm tài khoản ảo đăng ký bằng cách chia sẻ chung địa chỉ IP, số điện thoại, hoặc thẻ tín dụng bằng cách tìm kiếm các vòng lặp (rings) liên kết trong đồ thị giao dịch.
3. **Quản lý Phân quyền phức tạp (Identity & Access Management - IAM)**:
   - Phân quyền theo nhiều cấp độ: User thuộc Group A, Group A kế thừa Group B, Group B có quyền trên Resource C. Việc truy vấn quyền hạn n-cấp dạng này trong SQL cực kỳ chậm, nhưng trong Graph Database chỉ là một đường duyệt đồ thị đơn giản.
4. **Đồ thị tri thức (Knowledge Graph)**:
   - Kết nối các khái niệm, thực thể dữ liệu khác nhau để máy tính có thể hiểu được ngữ nghĩa mối quan hệ (ứng dụng nhiều trong AI và các hệ thống tìm kiếm thông minh).
