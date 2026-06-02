# GIẢI THUẬT (ALGORITHMS)

Tài liệu này phân tích chi tiết về Giải thuật (Algorithm), bao gồm định nghĩa, chức năng, các hướng tư duy thiết kế (Paradigms) và kỹ thuật tối ưu hóa thực chiến (Techniques). Nội dung được giải thích trực quan theo cách hiểu thực tế, tránh các thuật ngữ hàn lâm phức tạp, đi kèm code demo ngắn gọn, dễ hiểu nhất.

---

## 📖 1. ĐỊNH NGHĨA, CHỨC NĂNG & ĐẶC TRƯNG CỦA GIẢI THUẬT

### 1.1. Giải thuật là gì? (Cách hiểu bình dân)
Hãy tưởng tượng bạn muốn tự tay nấu một bát phở bò chuẩn vị. Bạn cần một **công thức nấu ăn** bao gồm các bước cụ thể: *Bước 1: Ninh xương trong 6 tiếng; Bước 2: Nướng hành tây và gừng; Bước 3: Thái thịt bò mỏng; Bước 4: Chần bánh phở và chan nước dùng.* 

Nếu bạn làm đúng trình tự này, bạn chắc chắn sẽ có một bát phở bò ngon (Đầu ra - Output) từ các nguyên liệu thô (Đầu vào - Input).

**Giải thuật (Algorithm)** chính là một **"công thức nấu ăn" dành cho máy tính**. Nó là một tập hợp các bước chỉ dẫn rõ ràng, có tuần tự logic, bắt đầu từ lúc nhận dữ liệu cho đến khi giải quyết xong vấn đề và dừng lại.

### 1.2. Chức năng (Function) của Giải thuật
Giải thuật sinh ra để làm các nhiệm vụ cốt lõi sau:
*   **Giải quyết vấn đề tự động (Automation):** Tiếp nhận dữ liệu đầu vào (Input), tự động xử lý qua các bước logic để trả ra kết quả mong muốn (Output) mà không cần con người thao tác thủ công.
*   **Tối ưu hóa tài nguyên & Hiệu năng (Optimization):** Cùng một yêu cầu đầu ra, giải thuật tối ưu giúp hệ thống chạy tốn ít thời gian nhất (Độ phức tạp thời gian - Time Complexity) và chiếm dụng ít bộ nhớ RAM nhất (Độ phức tạp không gian - Space Complexity).
*   **Tái sử dụng (Reusability):** Tạo ra bộ khung logic chuẩn chỉnh có thể áp dụng lại cho nhiều bài toán tương tự với quy mô dữ liệu lớn hơn.

### 1.3. Đặc trưng cốt lõi (Characteristics) của Giải thuật
Một giải thuật tiêu chuẩn bắt buộc phải đáp ứng 5 đặc trưng sau (hiểu một cách đơn giản, trực quan):
1.  **Đầu vào xác định (Input):** Có 0 hoặc nhiều dữ liệu đầu vào được mô tả rõ ràng.
2.  **Đầu ra rõ ràng (Output):** Phải cho ra ít nhất 1 kết quả đầu ra sau khi kết thúc.
3.  **Tính xác định (Definiteness):** Các bước trong giải thuật phải rõ ràng, không mơ hồ. Máy tính đọc vào chỉ hiểu đúng một nghĩa duy nhất và thực thi chính xác.
4.  **Tính hữu hạn (Finiteness):** Giải thuật bắt buộc phải dừng lại sau một số bước hữu hạn, không được rơi vào vòng lặp vô hạn gây treo máy.
5.  **Tính khả thi (Feasibility):** Các chỉ dẫn phải đủ đơn giản để có thể thực thi được bằng các tài nguyên máy tính thực tế (RAM, CPU) hiện có.

---

## 🧠 2. CÁC HƯỚNG TƯ DUY THIẾT KẾ GIẢI THUẬT (PARADIGMS)

Dưới đây là chi tiết toàn bộ các hướng tư duy giải thuật trong sơ đồ của bạn, kèm định nghĩa trực quan và code demo đơn giản:

### 2.1. Tìm kiếm (Searching)

#### 1. Tìm kiếm tuần tự (Linear Search)
*   **Định nghĩa trực quan:** Giống như bạn tìm một quyển sách trong kệ sách xếp lộn xộn. Bạn đi dò từ cuốn đầu tiên bên trái, sang cuốn thứ hai, thứ ba... cho đến khi thấy cuốn cần tìm hoặc đi hết kệ sách mà không thấy.
*   **Code Demo:**
    ```java
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Tìm thấy, trả về vị trí ngay lập tức
            }
        }
        return -1; // Duyệt hết mảng mà không thấy
    }
    ```
*   **Luồng chạy (Flow):** Với mảng `[4, 2, 9, 1]`, tìm số `9`. Vòng lặp kiểm tra: `4` (không phải) $\rightarrow$ `2` (không phải) $\rightarrow$ `9` (Khớp! Trả về vị trí số 2).

#### 2. Tìm kiếm nhị phân (Binary Search)
*   **Định nghĩa trực quan:** Giống như bạn lật tìm một từ trong cuốn từ điển đã được sắp xếp theo bảng chữ cái. Thay vì lật từng trang, bạn mở đôi cuốn sách ở chính giữa. Nếu từ ở trang giữa đứng sau từ bạn tìm, bạn bỏ luôn nửa sau cuốn sách và chỉ tìm ở nửa đầu. Cứ thế lặp lại việc chia đôi.
*   **Code Demo:**
    ```java
    public int binarySearch(int[] sortedArr, int target) {
        int left = 0, right = sortedArr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // Tìm phần tử ở giữa
            if (sortedArr[mid] == target) return mid; // Khớp!
            
            if (sortedArr[mid] < target) {
                left = mid + 1; // Bỏ nửa bên trái
            } else {
                right = mid - 1; // Bỏ nửa bên phải
            }
        }
        return -1;
    }
    ```
*   **Luồng chạy (Flow):** Với mảng đã sort `[1, 3, 5, 7, 9]`, tìm số `7`. 
    *   Lần 1: Phần tử giữa là `5`. Vì `5 < 7`, ta chuyển hướng sang nửa phải `[7, 9]`.
    *   Lần 2: Phần tử giữa của khoảng mới là `7`. Khớp! Trả về kết quả.

---

### 2.2. Sắp xếp (Sorting)

#### 1. Sắp xếp nổi bọt (Bubble Sort)
*   **Định nghĩa trực quan:** Giống như các bong bóng khí dưới nước, bong bóng to hơn sẽ nổi lên trên nhanh hơn. Bạn đi dọc mảng từ trái qua phải, so sánh hai số cạnh nhau, nếu số trước to hơn số sau thì đổi chỗ cho nhau. Sau mỗi lượt quét, số to nhất sẽ "nổi" về cuối mảng.
*   **Code Demo:**
    ```java
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) { // Số trước to hơn số sau?
                    // Đổi chỗ hai số
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    ```

#### 2. Sắp xếp chèn (Insertion Sort)
*   **Định nghĩa trực quan:** Giống như cách bạn xếp bài tây khi chơi bài. Tay trái bạn cầm các lá bài đã được sắp xếp. Tay phải bạn bốc một lá bài mới, bạn duyệt từ phải sang trái các lá bài đang có trên tay để tìm vị trí thích hợp và "chèn" lá bài mới vào đó.
*   **Code Demo:**
    ```java
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Lá bài bốc lên
            int j = i - 1;
            // Dịch chuyển các lá bài to hơn lá bài bốc lên sang phải
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // Chèn lá bài vào vị trí trống phù hợp
        }
    }
    ```

#### 3. Sắp xếp nhanh (Quick Sort)
*   **Định nghĩa trực quan:** Chọn một số bất kỳ làm "cột mốc" (gọi là Pivot). Chia mảng làm hai phe: phe bé hơn pivot xếp sang bên trái, phe lớn hơn pivot xếp sang bên phải. Sau đó, lặp lại việc chọn cột mốc và phân phe này cho hai nửa đó độc lập cho đến khi mảng thẳng hàng.
*   **Code Demo:**
    ```java
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high); // Phân phe
            quickSort(arr, low, pivotIndex - 1);  // Sắp xếp phe bên trái
            quickSort(arr, pivotIndex + 1, high); // Sắp xếp phe bên phải
        }
    }
    
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Chọn số cuối cùng làm mốc
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp; // Đổi chỗ
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1; // Trả về vị trí của cột mốc
    }
    ```

#### 4. Sắp xếp trộn (Merge Sort)
*   **Định nghĩa trực quan:** Đầu tiên, bạn chia đôi mảng liên tục cho đến khi mỗi mảng con chỉ còn đúng 1 phần tử (vì mảng 1 phần tử thì luôn luôn được coi là đã sắp xếp xong). Sau đó, bạn lần lượt gộp (trộn) các mảng nhỏ này lại với nhau theo cặp sao cho mảng sau khi trộn vẫn giữ đúng thứ tự tăng dần.
*   **Code Demo:**
    ```java
    import java.util.Arrays;

    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);     // Chia nửa trái
            mergeSort(arr, m + 1, r); // Chia nửa phải
            merge(arr, l, m, r);      // Trộn hai nửa đã sắp xếp lại
        }
    }
    
    private void merge(int[] arr, int l, int m, int r) {
        // Tạo các mảng tạm để copy dữ liệu hai nửa
        int[] leftArr = Arrays.copyOfRange(arr, l, m + 1);
        int[] rightArr = Arrays.copyOfRange(arr, m + 1, r + 1);
        
        int i = 0, j = 0, k = l;
        // Trộn so sánh từng phần tử của hai mảng tạm để ghi đè lại vào mảng gốc
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }
    ```

---

### 2.3. Đệ quy (Recursion)

#### Định nghĩa trực quan
Giống như búp bê Nga Matryoshka. Bạn mở con búp bê to ra, bên trong có một con búp bê nhỏ hơn nhưng có hình dạng y hệt. Bạn tiếp tục mở cho đến khi gặp con búp bê nhỏ nhất trong cùng không thể mở được nữa (đây gọi là **Điểm dừng - Base Case**). Lúc này bạn dừng lại và xếp các con búp bê lại như cũ.

#### 1. Đệ quy tuyến tính (Linear Recursion)
*   **Định nghĩa dễ hiểu:** Trong thân hàm đệ quy, chỉ có **duy nhất một** lời gọi lại chính nó.
*   **Code Demo (Tính giai thừa - Factorial):**
    ```java
    public int factorial(int n) {
        if (n == 1) return 1; // Điểm dừng (Base case)
        return n * factorial(n - 1); // Gọi lại chính nó đúng 1 lần
    }
    ```

#### 2. Đệ quy nhị phân (Binary Recursion)
*   **Định nghĩa dễ hiểu:** Trong thân hàm có **hai lời gọi** đệ quy độc lập với nhau, tạo ra sơ đồ phân nhánh như một cái cây.
*   **Code Demo (Tính số Fibonacci):**
    ```java
    public int fibonacci(int n) {
        if (n <= 1) return n; // Điểm dừng
        return fibonacci(n - 1) + fibonacci(n - 2); // Hai lời gọi đệ quy
    }
    ```

#### 3. Đệ quy lồng (Nested Recursion)
*   **Định nghĩa dễ hiểu:** Tham số truyền vào cho hàm đệ quy lại chính là kết quả của một lời gọi đệ quy khác (đệ quy nằm trong đệ quy). Tốc độ tăng trưởng cực kỳ nhanh.
*   **Code Demo (Hàm Ackermann):**
    ```java
    public int ackermann(int m, int n) {
        if (m == 0) return n + 1;
        if (m > 0 && n == 0) return ackermann(m - 1, 1);
        // Lồng nhau: Lời gọi đệ quy bên trong làm tham số cho lời gọi bên ngoài
        return ackermann(m - 1, ackermann(m, n - 1));
    }
    ```

#### 4. Đệ quy hỗ tương (Mutual Recursion)
*   **Định nghĩa dễ hiểu:** Hàm A không gọi chính nó, mà gọi hàm B. Sau đó hàm B lại gọi ngược lại hàm A. Hai hàm "nói chuyện qua lại" để giải quyết vấn đề.
*   **Code Demo (Kiểm tra Chẵn / Lẻ):**
    ```java
    public boolean isEven(int n) {
        if (n == 0) return true;
        return isOdd(n - 1); // Hàm Even gọi hàm Odd
    }
    
    public boolean isOdd(int n) {
        if (n == 0) return false;
        return isEven(n - 1); // Hàm Odd gọi lại hàm Even
    }
    ```

---

### 2.4. Quy hoạch động (Dynamic Programming)

#### Định nghĩa trực quan
Hỏi bạn: $1+1+1+1+1$ bằng mấy? Bạn đếm và trả lời bằng 5.
Hỏi tiếp: Nếu viết thêm $+1$ vào sau thì bằng mấy? Bạn trả lời ngay bằng 6.
Tại sao bạn trả lời nhanh thế mà không cần đếm lại từ đầu? Bởi vì bạn đã **nhớ** kết quả phép tính trước là 5 rồi cộng thêm 1. Quy hoạch động chính là việc ghi nhớ này để tránh tính lại.

#### 1. Quy hoạch động từ trên xuống (Top-down DP / Memoization)
*   **Định nghĩa dễ hiểu:** Bạn đi từ bài toán lớn ban đầu, dùng đệ quy để chia nhỏ ra. Nhưng mỗi khi giải xong một bài toán con, bạn lấy bút viết kết quả vào một "cuốn sổ tay" (mảng bộ nhớ đệm). Lần sau nếu gặp lại bài toán con đó, bạn chỉ cần mở sổ ra lấy kết quả dùng luôn.
*   **Code Demo (Fibonacci có nhớ):**
    ```java
    public int fibTopDown(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n]; // Nếu đã có trong sổ tay, trả về luôn
        
        memo[n] = fibTopDown(n - 1, memo) + fibTopDown(n - 2, memo); // Tính và ghi vào sổ
        return memo[n];
    }
    ```

#### 2. Quy hoạch động từ dưới lên (Bottom-up DP / Tabulation)
*   **Định nghĩa dễ hiểu:** Không dùng đệ quy. Bạn đi từ những bài toán nhỏ nhất ở đáy (ví dụ F(0), F(1)), dùng vòng lặp để tính dần lên các bài toán lớn hơn và điền kết quả vào một cái bảng (mảng) cho đến khi đạt được kết quả của bài toán cần tìm.
*   **Code Demo (Fibonacci điền bảng):**
    ```java
    public int fibBottomUp(int n) {
        if (n <= 1) return n;
        int[] table = new int[n + 1];
        table[0] = 0; table[1] = 1; // Điểm xuất phát nhỏ nhất
        
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2]; // Tính dần lên dựa vào bảng
        }
        return table[n];
    }
    ```

---

### 2.5. Tham lam (Greedy)

#### Định nghĩa trực quan
Giống như bạn đi siêu thị mua sắm với chiếc ví giới hạn và muốn nhặt các món đồ giá trị cao nhất trước. Bạn cứ chọn món đắt tiền nhất có thể mua được tại thời điểm đó, nhét vào giỏ hàng mà không cần suy tính xem các bước sau mình sẽ mua gì.

#### 1. Thuật toán Dijkstra (Tìm đường đi ngắn nhất)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như đi chọn đường từ nhà đến trường. Tại mỗi ngã rẽ, bạn luôn chọn con đường ngắn nhất chưa từng đi qua, rồi từ đó cập nhật lại bản đồ đường đi của các điểm lân cận.
*   **Code Demo đơn giản:**
    ```java
    import java.util.Arrays;

    public class DijkstraDemo {
        // Tìm đường đi ngắn nhất từ đỉnh nguồn (src) đến tất cả các đỉnh khác
        public static void dijkstra(int[][] graph, int src) {
            int n = graph.length;
            int[] dist = new int[n]; // dist[i] lưu khoảng cách ngắn nhất từ src đến i
            boolean[] visited = new boolean[n]; // Đánh dấu đỉnh đã duyệt xong

            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0; // Khoảng cách từ nguồn đến chính nó là 0

            for (int i = 0; i < n - 1; i++) {
                // Bước tham lam: Chọn đỉnh có khoảng cách nhỏ nhất chưa duyệt
                int u = findMinDistance(dist, visited);
                visited[u] = true;

                // Cập nhật khoảng cách cho các đỉnh kề của u
                for (int v = 0; v < n; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            // In kết quả
            for (int i = 0; i < n; i++) {
                System.out.println("Đường đi ngắn nhất từ " + src + " đến " + i + " là: " + dist[i]);
            }
        }

        private static int findMinDistance(int[] dist, boolean[] visited) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            for (int v = 0; v < dist.length; v++) {
                if (!visited[v] && dist[v] <= min) {
                    min = dist[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }
    }
    ```

#### 2. Thuật toán Kruskal / Thuật toán Prim (Tìm cây khung nhỏ nhất - MST)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như bạn có nhiều hòn đảo và muốn xây cầu kết nối tất cả các đảo lại với nhau sao cho tốn ít tiền nhất (không cần tạo thành vòng lặp).
    *   **Kruskal (Tham lam theo cạnh):** Bạn liệt kê giá của tất cả các cây cầu từ rẻ nhất đến đắt nhất. Bạn cứ nhặt cây cầu rẻ nhất để xây, miễn là cây cầu đó không tạo thành một vòng tròn luẩn quẩn (vòng lặp).
    *   **Prim (Tham lam theo đỉnh):** Bạn xuất phát từ 1 đảo đầu tiên, sau đó chỉ chọn cây cầu rẻ nhất nối từ các đảo đã được thông với thế giới bên ngoài ra một đảo hoang mới.
*   **Code Demo Kruskal đơn giản:**
    ```java
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    public class KruskalDemo {
        static class Edge implements Comparable<Edge> {
            int u, v, weight;
            Edge(int u, int v, int weight) {
                this.u = u; this.v = v; this.weight = weight;
            }
            public int compareTo(Edge other) {
                return this.weight - other.weight; // Để sắp xếp cạnh từ rẻ đến đắt
            }
        }

        // Giả lập Union-Find đơn giản để kiểm tra vòng lặp
        static int findParent(int[] parent, int i) {
            if (parent[i] == i) return i;
            return findParent(parent, parent[i]);
        }

        public static void kruskal(List<Edge> edges, int numVertices) {
            Collections.sort(edges); // Sắp xếp cạnh tăng dần theo trọng số
            int[] parent = new int[numVertices];
            for (int i = 0; i < numVertices; i++) parent[i] = i;

            List<Edge> mst = new ArrayList<>();
            for (Edge edge : edges) {
                int rootU = findParent(parent, edge.u);
                int rootV = findParent(parent, edge.v);

                // Nếu không tạo thành vòng lặp
                if (rootU != rootV) {
                    mst.add(edge); // Nhặt cạnh này vào cây khung
                    parent[rootU] = rootV; // Gộp nhóm
                }
            }

            System.out.println("Các cạnh trong MST (Kruskal):");
            for (Edge e : mst) {
                System.out.println(e.u + " - " + e.v + " (Giá: " + e.weight + ")");
            }
        }
    }
    ```
*   **Code Demo Prim đơn giản:**
    ```java
    import java.util.Arrays;

    public class PrimDemo {
        public static void prim(int[][] graph) {
            int n = graph.length;
            int[] key = new int[n]; // Lưu trọng số cạnh nhỏ nhất nối đến mỗi đỉnh
            int[] parent = new int[n]; // Lưu vết cây khung
            boolean[] inMST = new boolean[n]; // Đánh dấu đỉnh thuộc MST

            Arrays.fill(key, Integer.MAX_VALUE);
            key[0] = 0; // Bắt đầu từ đỉnh 0
            parent[0] = -1;

            for (int i = 0; i < n - 1; i++) {
                int u = -1;
                for (int v = 0; v < n; v++) {
                    if (!inMST[v] && (u == -1 || key[v] < key[u])) {
                        u = v;
                    }
                }
                inMST[u] = true;

                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                        parent[v] = u;
                        key[v] = graph[u][v];
                    }
                }
            }

            System.out.println("Các cạnh trong MST (Prim):");
            for (int i = 1; i < n; i++) {
                System.out.println(parent[i] + " - " + i + " (Giá: " + graph[i][parent[i]] + ")");
            }
        }
    }
    ```

#### 3. Mã hóa Huffman (Huffman Coding)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như viết tắt tin nhắn để tiết kiệm ký tự. Chữ nào xuất hiện nhiều nhất (như 'e', 'a') sẽ được mã hóa bằng ký hiệu cực ngắn (ví dụ: `0`), còn chữ nào ít gặp (như 'z') thì chấp nhận mã hóa dài hơn (ví dụ: `1101`). Nhờ đó, tổng dung lượng file nhắn tin sẽ nhỏ đi đáng kể.
*   **Code Demo đơn giản:**
    ```java
    import java.util.PriorityQueue;

    public class HuffmanDemo {
        static class HuffmanNode {
            int data;
            char c;
            HuffmanNode left, right;
        }

        public static HuffmanNode buildTree(char[] charArray, int[] charfreq) {
            int n = charArray.length;
            PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, (a, b) -> a.data - b.data);

            for (int i = 0; i < n; i++) {
                HuffmanNode hn = new HuffmanNode();
                hn.c = charArray[i];
                hn.data = charfreq[i];
                q.add(hn);
            }

            HuffmanNode root = null;
            while (q.size() > 1) {
                HuffmanNode x = q.poll();
                HuffmanNode y = q.poll();

                HuffmanNode f = new HuffmanNode();
                f.data = x.data + y.data;
                f.c = '-';
                f.left = x;
                f.right = y;
                root = f;
                q.add(f);
            }
            return root;
        }

        public static void printCode(HuffmanNode root, String s) {
            if (root.left == null && root.right == null && Character.isLetter(root.c)) {
                System.out.println(root.c + " : " + s);
                return;
            }
            printCode(root.left, s + "0");
            printCode(root.right, s + "1");
        }
    }
    ```

---

### 2.6. Chia để trị (Divide and Conquer)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như việc bẻ một bó đũa. Bạn không thể bẻ cả bó đũa một lúc, nên bạn chia bó đũa làm hai nửa, tiếp tục chia nhỏ cho đến khi cầm từng chiếc đũa riêng lẻ để bẻ một cách dễ dàng, cuối cùng gộp lại.
*   **Code Demo đơn giản (Tính Lũy thừa $x^n$ nhanh bằng cách chia đôi số mũ):**
    ```java
    public class DivideAndConquerDemo {
        // Chia bài toán thành các phần nhỏ hơn để giải quyết cực nhanh trong O(log n)
        public static long power(int x, int n) {
            if (n == 0) return 1;
            
            long half = power(x, n / 2); // Chia đôi số mũ
            
            if (n % 2 == 0) {
                return half * half; // Gộp kết quả
            } else {
                return x * half * half;
            }
        }
    }
    ```

---

### 2.7. Vét cạn (Brute Force)
*   **Định nghĩa trực quan:** Giống như việc bạn thử bẻ khóa mật mã vali có 4 chữ số bằng cách quay số thử lần lượt từ `0000`, `0001`, `0002`... cho đến `9999`. Đơn giản, chắc chắn đúng nhưng cực kỳ mất thời gian.
*   **Code Demo (Tìm kiếm chuỗi con thô):**
    ```java
    public boolean bruteForceSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break; // Sai là chuyển sang vị trí tiếp theo
            }
            if (j == m) return true; // Khớp hoàn toàn
        }
        return false;
    }
    ```

---

### 2.8. Duyệt đồ thị (Graph Traversal)

#### 1. Duyệt theo chiều rộng (Breadth-First Search - BFS)
*   **Định nghĩa trực quan:** Giống như một vết dầu loang trên mặt nước hoặc sóng nước khi bạn ném một viên đá. Bạn thăm tất cả các nút lân cận trực tiếp xung quanh bạn (tầng 1), sau khi thăm hết bạn mới đi tiếp sang các nút xa hơn một chút (tầng 2). Thuật toán này dùng **Hàng đợi (Queue)**.
*   **Code Demo (Ý tưởng cấu trúc đồ thị):**
    ```java
    import java.util.*;

    public class BFSDemo {
        static class Node {
            int val;
            List<Node> neighbors = new ArrayList<>();
            Node(int val) { this.val = val; }
        }

        public void bfs(Node startNode) {
            Queue<Node> queue = new LinkedList<>();
            Set<Node> visited = new HashSet<>();
            
            queue.add(startNode); 
            visited.add(startNode);
            
            while (!queue.isEmpty()) {
                Node current = queue.poll(); // Lấy nút đầu hàng ra thăm
                System.out.print(current.val + " ");
                for (Node neighbor : current.neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor); 
                        queue.add(neighbor); // Loang ra hàng xóm
                    }
                }
            }
        }
    }
    ```

#### 2. Duyệt theo chiều sâu (Depth-First Search - DFS)
*   **Định nghĩa trực quan:** Giống như bạn đi khám phá một mê cung. Bạn chọn một lối rẽ và cắm đầu đi sâu hết mức có thể trên con đường đó cho đến khi gặp ngõ cụt. Lúc đó bạn mới quay lui lại ngã ba gần nhất để chọn lối rẽ khác. Thuật toán dùng **Đệ quy** hoặc **Ngăn xếp (Stack)**.
*   **Code Demo:**
    ```java
    import java.util.*;

    public class DFSDemo {
        static class Node {
            int val;
            List<Node> neighbors = new ArrayList<>();
            Node(int val) { this.val = val; }
        }

        public void dfs(Node node, Set<Node> visited) {
            if (node == null || visited.contains(node)) return;
            
            System.out.print(node.val + " "); // Thăm nút hiện tại
            visited.add(node);
            for (Node neighbor : node.neighbors) {
                dfs(neighbor, visited); // Đâm sâu tiếp vào các nhánh con
            }
        }
    }
    ```

---

### 2.9. Quay lui (Backtracking)
*   **Định nghĩa trực quan:** Bạn đi giải mê cung. Bạn đến một lối rẽ, bạn thử đi đường bên trái. Nếu đi một hồi phát hiện ngõ cụt, bạn **quay ngược lại (Backtrack)** ngã rẽ đó, xóa bỏ lựa chọn sai lầm và thử đi sang con đường bên phải.
*   **Code Demo (Tìm tất cả các tập hợp con):**
    ```java
    import java.util.*;

    public class BacktrackingDemo {
        public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] nums, int start) {
            ans.add(new ArrayList<>(temp)); // Thêm lựa chọn hiện tại vào kết quả
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]); // Thử chọn số nums[i]
                backtrack(ans, temp, nums, i + 1); // Đi tiếp con đường này
                temp.remove(temp.size() - 1); // Quay lui: Bỏ chọn số nums[i] để thử số khác
            }
        }
    }
    ```

---

## 🛠️ 3. KỸ THUẬT TỐI ƯU HÓA THỰC CHIẾN (TECHNIQUES)

Các mẫu kỹ thuật phổ biến dùng để giảm độ phức tạp thời gian khi viết code:

### 3.1. Kỹ thuật hai con trỏ (Two Pointers)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như hai người đi tìm mua nhà từ hai đầu phố. Một người đi từ đầu phố (`left`), một người đi từ cuối phố (`right`). Nếu giá nhà của hai người cộng lại quá cao, người ở cuối phố lùi lại (`right--`) tìm nhà rẻ hơn. Nếu tổng giá quá thấp, người ở đầu phố tiến lên (`left++`) tìm nhà đắt hơn. Họ sẽ gặp nhau ở đúng cặp nhà cần tìm cực kỳ nhanh mà không cần phải đi dò từng đôi nhà một.
*   **Code Demo đơn giản (Tìm 2 số có tổng bằng target trong mảng đã sắp xếp):**
    ```java
    public class TwoPointersDemo {
        public static int[] findTwoSum(int[] sortedArr, int target) {
            int left = 0;
            int right = sortedArr.length - 1;

            while (left < right) {
                int currentSum = sortedArr[left] + sortedArr[right];
                if (currentSum == target) {
                    return new int[]{left, right}; // Tìm thấy!
                } else if (currentSum > target) {
                    right--; // Tổng quá lớn, lùi con trỏ bên phải
                } else {
                    left++; // Tổng quá nhỏ, tiến con trỏ bên trái
                }
            }
            return new int[]{-1, -1}; // Không tìm thấy
        }
    }
    ```

### 3.2. Cửa sổ trượt (Sliding Window)
*   **Định nghĩa dễ hiểu (Không hàn lâm):** Giống như bạn kéo một chiếc kính lúp dịch chuyển dọc theo một dãy số dài. Thay vì mỗi lần dịch kính lúp sang phải bạn phải cộng lại tất cả các số nằm dưới kính, bạn chỉ cần lấy tổng cũ, cộng thêm số mới lọt vào kính bên phải, và trừ đi số vừa bị đẩy ra ngoài kính bên trái.
*   **Code Demo đơn giản (Tìm tổng lớn nhất của mảng con có kích thước K):**
    ```java
    public class SlidingWindowDemo {
        public static int maxSumSubarray(int[] arr, int k) {
            if (arr.length < k) return -1;

            int windowSum = 0;
            // Tính tổng của cửa sổ đầu tiên kích thước k
            for (int i = 0; i < k; i++) {
                windowSum += arr[i];
            }

            int maxSum = windowSum;
            // Trượt cửa sổ sang phải
            for (int i = k; i < arr.length; i++) {
                // Cộng thêm phần tử bên phải, trừ đi phần tử bị đẩy ra bên trái
                windowSum += arr[i] - arr[i - k];
                maxSum = Math.max(maxSum, windowSum);
            }
            return maxSum;
        }
    }
    ```

### 3.3. Các kỹ thuật tối ưu hóa khác
*   **Mảng cộng dồn (Prefix Sum):** Tạo mảng lưu tổng lũy kế giúp truy vấn tổng của bất kỳ đoạn con từ $L$ đến $R$ trong thời gian hằng số $O(1)$ bằng công thức: `sum = prefix[R] - prefix[L-1]`.
*   **Kỹ thuật Băm (Hashing):** Dùng HashSet/HashMap để lưu và kiểm tra sự tồn tại của phần tử đã duyệt qua trong $O(1)$.
*   **Ngăn xếp đơn điệu (Monotonic Stack):** Dùng Stack luôn duy trì thứ tự tăng/giảm dần để tìm phần tử lớn hơn/nhỏ hơn tiếp theo (Next Greater Element) trong $O(n)$.
*   **Thao tác Bit (Bit Manipulation):** Sử dụng các toán tử nhị phân cơ bản (`&`, `|`, `^`, `<<`, `>>`) để tính toán trực tiếp trên bit của số nguyên nhằm đạt tốc độ phần cứng tối đa.

---

## 📊 4. ĐỘ PHỨC TẠP THUẬT TOÁN (COMPLEXITY)

Big O là thước đo tốc độ tăng trưởng tài nguyên (thời gian hoặc bộ nhớ) của giải thuật khi dữ liệu đầu vào ($n$) tăng lên vô hạn.

| Big O | Tên gọi | Tốc độ | Ví dụ thực tế |
| :--- | :--- | :--- | :--- |
| **$O(1)$** | Constant Time | Siêu tốc (Hằng số) | Truy cập phần tử mảng `arr[i]`, lấy giá trị Map. |
| **$O(\log n)$** | Logarithmic Time | Cực nhanh | Tìm kiếm nhị phân (Binary Search). |
| **$O(n)$** | Linear Time | Tốt (Tuyến tính) | Tìm kiếm tuần tự (Linear Search), duyệt mảng 1 vòng lặp. |
| **$O(n \log n)$** | Linearithmic Time | Khá | Thuật toán sắp xếp Merge Sort, Quick Sort. |
| **$O(n^2)$** | Quadratic Time | Chậm | Hai vòng lặp lồng nhau duyệt mảng (Bubble Sort). |
| **$O(2^n)$** | Exponential Time | Ác mộng (Mũ) | Đệ quy Fibonacci thô không nhớ, tìm tập con. |
