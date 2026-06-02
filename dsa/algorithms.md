# GIẢI THUẬT (ALGORITHMS)

Tài liệu này tổng hợp toàn bộ các giải thuật cốt lõi. Mỗi phần được cấu trúc tối giản: Định nghĩa phỏng vấn trực diện, ẩn dụ trực quan ngắn gọn, từ khóa chuyên môn cốt lõi (Big O, Trade-offs) và code demo Java tinh gọn nhất.

---

## 1. ĐỊNH NGHĨA GIẢI THUẬT

*   **Định nghĩa:** Giải thuật (Algorithm) là một tập hợp các chỉ dẫn logic, có tuần tự và hữu hạn để xử lý dữ liệu đầu vào (Input) nhằm giải quyết một bài toán cụ thể và cho ra kết quả mong muốn (Output).
*   **Ẩn dụ trực quan:** Giống như một công thức nấu ăn chuẩn xác từng bước dành cho máy tính.
*   **Đặc trưng cốt lõi khi phỏng vấn:**
    1.  **Finiteness (Tính hữu hạn):** Phải dừng lại sau một số bước hữu hạn, không lặp vô hạn.
    2.  **Definiteness (Tính xác định):** Mỗi bước phải rõ ràng, chỉ có duy nhất một cách hiểu cho máy tính.
    3.  **Feasibility (Tính khả thi):** Có thể thực thi được bằng tài nguyên phần cứng thực tế.
    4.  **Input/Output:** Có đầu vào (0 hoặc nhiều) và đầu ra rõ ràng (ít nhất 1).

---

## 2. CÁC HƯỚNG TƯ DUY THIẾT KẾ GIẢI THUẬT (PARADIGMS)

### 2.1. Tìm kiếm (Searching)

#### 1. Tìm kiếm tuần tự (Linear Search)
*   **Định nghĩa:** Duyệt tuần tự qua từng phần tử của mảng từ đầu đến cuối để tìm kiếm giá trị mục tiêu.
*   **Ẩn dụ trực quan:** Dò tìm từng quyển sách trên kệ từ trái sang phải cho đến khi thấy.
*   **Từ khóa cốt lõi:** $O(n)$ time, $O(1)$ space. Dùng khi dữ liệu chưa được sắp xếp hoặc mảng quá nhỏ.
*   **Code Demo:**
    ```java
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    ```

#### 2. Tìm kiếm nhị phân (Binary Search)
*   **Định nghĩa:** Tìm kiếm trên mảng đã sắp xếp bằng cách liên tục chia đôi khoảng tìm kiếm. So sánh giá trị giữa (`mid`) với `target` để loại bỏ một nửa khoảng không chứa kết quả.
*   **Ẩn dụ trực quan:** Mở đôi cuốn từ điển và chỉ tìm ở nửa chứa chữ cái cần tìm, bỏ nửa còn lại.
*   **Từ khóa cốt lõi:** $O(\log n)$ time, $O(1)$ space. **Bắt buộc mảng phải được sắp xếp trước**.
*   **Code Demo:**
    ```java
    public int binarySearch(int[] sortedArr, int target) {
        int left = 0, right = sortedArr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArr[mid] == target) return mid;
            if (sortedArr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    ```

---

### 2.2. Sắp xếp (Sorting)

#### 1. Sắp xếp nổi bọt (Bubble Sort)
*   **Định nghĩa:** Liên tục so sánh hai phần tử kề nhau và đổi chỗ nếu chúng sai thứ tự. Sau mỗi lượt quét, phần tử lớn nhất sẽ "nổi" về cuối mảng.
*   **Ẩn dụ trực quan:** Các bong bóng khí lớn hơn sẽ nổi lên mặt nước nhanh hơn.
*   **Từ khóa cốt lõi:** $O(n^2)$ time, $O(1)$ space. Stable, In-place. Rất chậm, thực tế chỉ dùng để giảng dạy.
*   **Code Demo:**
    ```java
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                }
            }
        }
    }
    ```

#### 2. Sắp xếp chèn (Insertion Sort)
*   **Định nghĩa:** Duyệt mảng và xây dựng một danh sách đã sắp xếp ở đầu bằng cách lấy từng phần tử mới chèn vào đúng vị trí thích hợp của nó trong danh sách đó.
*   **Ẩn dụ trực quan:** Cách bạn bốc thêm một lá bài tây mới và chèn vào đúng vị trí trên tay.
*   **Từ khóa cốt lõi:** $O(n^2)$ time, $O(1)$ space. Stable, In-place. Cực nhanh đối với mảng gần như đã được sắp xếp trước.
*   **Code Demo:**
    ```java
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    ```

#### 3. Sắp xếp nhanh (Quick Sort)
*   **Định nghĩa:** Chọn một phần tử làm mốc (`pivot`). Phân chia mảng sao cho các phần tử nhỏ hơn pivot nằm bên trái, lớn hơn pivot nằm bên phải. Tiếp tục đệ quy hai nửa đó.
*   **Ẩn dụ trực quan:** Chọn một người làm cột mốc chiều cao, chia hàng thành hai nhóm đứng hai bên cột mốc.
*   **Từ khóa cốt lõi:** $O(n \log n)$ average time, $O(n^2)$ worst-case (khi chọn pivot tệ). $O(\log n)$ space. Unstable, In-place.
*   **Code Demo:**
    ```java
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
    ```

#### 4. Sắp xếp trộn (Merge Sort)
*   **Định nghĩa:** Sử dụng tư duy Chia để trị. Chia đôi mảng liên tục cho đến khi còn các mảng con 1 phần tử, sau đó trộn (merge) các mảng con đã sắp xếp lại với nhau theo thứ tự tăng dần.
*   **Ẩn dụ trực quan:** Chia bó đũa ra thành từng chiếc đơn lẻ rồi gộp chúng lại theo cặp có thứ tự.
*   **Từ khóa cốt lõi:** Luôn là $O(n \log n)$ time trong mọi trường hợp. Tốn $O(n)$ space cho mảng tạm để trộn. Stable.
*   **Code Demo:**
    ```java
    import java.util.Arrays;
    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    private void merge(int[] arr, int l, int m, int r) {
        int[] leftArr = Arrays.copyOfRange(arr, l, m + 1);
        int[] rightArr = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < leftArr.length && j < rightArr.length) {
            arr[k++] = (leftArr[i] <= rightArr[j]) ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }
    ```

---

### 2.3. Đệ quy (Recursion)

*   **Định nghĩa:** Cơ chế một hàm tự gọi lại chính nó với các tham số nhỏ hơn cho đến khi chạm tới điểm dừng (Base Case).
*   **Ẩn dụ trực quan:** Bộ búp bê Nga Matryoshka - mở búp bê lớn chứa búp bê nhỏ hơn bên trong cho đến con nhỏ nhất.
*   **Từ khóa cốt lõi:** Nguy cơ tràn bộ nhớ stack (**Stack Overflow**) nếu không có Base Case hoặc độ sâu đệ quy quá lớn.

#### 1. Đệ quy tuyến tính (Linear Recursion)
*   **Định nghĩa ngắn:** Hàm tự gọi lại chính nó duy nhất 1 lần trong thân hàm.
*   **Demo (Tính Giai thừa):**
    ```java
    public int factorial(int n) {
        if (n == 1) return 1; // Base case
        return n * factorial(n - 1); // 1 lời gọi đệ quy
    }
    ```

#### 2. Đệ quy nhị phân (Binary Recursion)
*   **Định nghĩa ngắn:** Hàm tự gọi lại chính nó 2 lần độc lập trong thân hàm, tạo ra sơ đồ phân nhánh dạng cây.
*   **Demo (Số Fibonacci):**
    ```java
    public int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2); // 2 lời gọi đệ quy
    }
    ```

#### 3. Đệ quy lồng (Nested Recursion)
*   **Định nghĩa ngắn:** Tham số truyền vào cho hàm đệ quy lại chính là kết quả của một lời gọi đệ quy khác.
*   **Demo (Hàm Ackermann):**
    ```java
    public int ackermann(int m, int n) {
        if (m == 0) return n + 1;
        if (m > 0 && n == 0) return ackermann(m - 1, 1);
        return ackermann(m - 1, ackermann(m, n - 1)); // Lồng nhau
    }
    ```

#### 4. Đệ quy hỗ tương (Mutual Recursion)
*   **Định nghĩa ngắn:** Hai hoặc nhiều hàm gọi chéo lẫn nhau để giải quyết vấn đề.
*   **Demo (Kiểm tra Chẵn Lẻ):**
    ```java
    public boolean isEven(int n) {
        if (n == 0) return true;
        return isOdd(n - 1); // Hàm A gọi Hàm B
    }
    public boolean isOdd(int n) {
        if (n == 0) return false;
        return isEven(n - 1); // Hàm B gọi ngược lại Hàm A
    }
    ```

---

### 2.4. Quy hoạch động (Dynamic Programming)

*   **Định nghĩa:** Phương pháp giải quyết bài toán lớn bằng cách chia nhỏ thành các bài toán con, giải quyết chúng một lần duy nhất và lưu kết quả lại để tái sử dụng mà không cần tính toán lại.
*   **Ẩn dụ trực quan:** Ghi nhớ kết quả phép tính $1+1+1+1+1=5$, khi viết thêm $+1$ bạn trả lời ngay là $6$ thay vì đếm lại từ đầu.
*   **Từ khóa cốt lõi:** Overlapping Subproblems (bài toán con trùng lặp), Optimal Substructure (cấu trúc con tối ưu).

#### 1. Quy hoạch động từ trên xuống (Top-down DP / Memoization)
*   **Định nghĩa ngắn:** Đi từ bài toán lớn đệ quy xuống bài toán nhỏ, lưu kết quả các bài toán con vào mảng/Map (sổ tay).
*   **Code Demo:**
    ```java
    public int fibTopDown(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n]; // Đã nhớ thì lấy dùng luôn
        memo[n] = fibTopDown(n - 1, memo) + fibTopDown(n - 2, memo);
        return memo[n];
    }
    ```

#### 2. Quy hoạch động từ dưới lên (Bottom-up DP / Tabulation)
*   **Định nghĩa ngắn:** Giải quyết các bài toán con nhỏ nhất trước bằng vòng lặp, điền kết quả vào bảng (mảng) và tích lũy dần lên bài toán lớn.
*   **Code Demo:**
    ```java
    public int fibBottomUp(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Điền bảng tuyến tính
        }
        return dp[n];
    }
    ```

---

### 2.5. Tham lam (Greedy)

*   **Định nghĩa:** Tại mỗi bước đi, thuật toán luôn đưa ra lựa chọn tốt nhất hiện tại (tối ưu cục bộ) với hy vọng sẽ dẫn tới kết quả tốt nhất ở cuối cùng (tối ưu toàn cục).
*   **Ẩn dụ trực quan:** Siêu thị giới hạn số món đồ được nhặt, bạn cứ ưu tiên nhặt món đắt tiền nhất có thể mua được tại thời điểm đó.
*   **Từ khóa cốt lõi:** Lựa chọn tối ưu cục bộ (locally optimal choice), không phải bài toán nào giải thuật tham lam cũng cho ra kết quả đúng toàn cục.

#### 1. Thuật toán Dijkstra (Tìm đường đi ngắn nhất)
*   **Định nghĩa:** Tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh khác trên đồ thị có trọng số không âm. Tại mỗi bước, chọn đỉnh chưa duyệt có khoảng cách ngắn nhất, cập nhật khoảng cách cho các đỉnh kề của nó.
*   **Từ khóa cốt lõi:** Greedy, $O((V+E) \log V)$ dùng Heap/Priority Queue. **Không hoạt động trên đồ thị có trọng số âm**.
*   **Code Demo:**
    ```java
    import java.util.Arrays;
    public class DijkstraDemo {
        public static void dijkstra(int[][] graph, int src) {
            int n = graph.length;
            int[] dist = new int[n]; boolean[] visited = new boolean[n];
            Arrays.fill(dist, Integer.MAX_VALUE); dist[src] = 0;
            for (int i = 0; i < n - 1; i++) {
                int u = -1;
                for (int v = 0; v < n; v++) {
                    if (!visited[v] && (u == -1 || dist[v] < dist[u])) u = v;
                }
                visited[u] = true;
                for (int v = 0; v < n; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
    }
    ```

#### 2. Thuật toán Kruskal / Thuật toán Prim (Tìm cây khung nhỏ nhất - MST)
*   **Kruskal (Tham lam theo cạnh):** Sắp xếp tất cả các cạnh theo trọng số tăng dần. Lần lượt nhặt các cạnh nhỏ nhất nối các đỉnh lại với nhau, bỏ các cạnh tạo thành chu trình (dùng Union-Find).
    *   *Từ khóa:* $O(E \log E)$ hoặc $O(E \log V)$ time. Cực kỳ hiệu quả đối với đồ thị thưa.
*   **Prim (Tham lam theo đỉnh):** Bắt đầu từ 1 đỉnh nguồn, liên tục nhặt cạnh có trọng số nhỏ nhất nối từ nhóm đỉnh đã chọn sang nhóm đỉnh chưa chọn để kết nạp thêm đỉnh mới vào cây.
    *   *Từ khóa:* $O(E \log V)$ hoặc $O(V^2)$ time. Cực kỳ hiệu quả đối với đồ thị dày đặc.

#### 3. Mã hóa Huffman (Huffman Coding)
*   **Định nghĩa:** Giải thuật nén dữ liệu không mất mát (Lossless Compression). Tham lam gom các ký tự có tần suất xuất hiện nhỏ nhất lại để xây dựng cây nhị phân, gán mã bít ngắn nhất cho ký tự xuất hiện nhiều nhất và ngược lại.
*   **Từ khóa cốt lõi:** Greedy, nén dữ liệu, mã độ dài biến đổi (variable-length code).

---

### 2.6. Chia để trị (Divide and Conquer)

*   **Định nghĩa:** Chia bài toán lớn thành các bài toán con độc lập cùng loại, giải quyết các bài toán con một cách độc lập (thường dùng đệ quy), sau đó gộp kết quả của chúng lại để giải quyết bài toán lớn.
*   **Ẩn dụ trực quan:** Thay vì bẻ cả bó đũa, chia ra bẻ từng chiếc rồi gom đũa đã gãy lại.
*   **Từ khóa cốt lõi:** Chia (Divide), Trị (Conquer), Gộp (Combine). Ví dụ điển hình: Merge Sort, Quick Sort, Binary Search.
*   **Demo (Tính Lũy thừa nhanh $x^n$):**
    ```java
    public class DivideAndConquerDemo {
        public static long power(int x, int n) {
            if (n == 0) return 1;
            long half = power(x, n / 2); // Chia bài toán làm đôi
            return (n % 2 == 0) ? half * half : x * half * half; // Gộp kết quả
        }
    }
    ```

---

### 2.7. Vét cạn (Brute Force)

*   **Định nghĩa:** Duyệt qua tất cả các ứng viên hoặc tất cả các trường hợp có thể xảy ra để tìm ra kết quả chính xác nhất.
*   **Ẩn dụ trực quan:** Thử bẻ khóa vali số 4 chữ số bằng cách quay số lần lượt từ `0000` đến `9999`.
*   **Từ khóa cốt lõi:** Luôn đúng, cực kỳ dễ cài đặt nhưng độ phức tạp thời gian rất lớn ($O(2^n)$, $O(n!)$). Chỉ dùng khi tập dữ liệu cực nhỏ.
*   **Code Demo (Tìm kiếm chuỗi con thô):**
    ```java
    public boolean bruteForceSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break;
            }
            if (j == m) return true; // Tìm thấy
        }
        return false;
    }
    ```

---

### 2.8. Duyệt đồ thị (Graph Traversal)

#### 1. Duyệt theo chiều rộng (Breadth-First Search - BFS)
*   **Định nghĩa:** Duyệt đồ thị bắt đầu từ một đỉnh gốc, thăm hết toàn bộ các đỉnh kề trực tiếp (tầng 1) trước khi chuyển sang các đỉnh ở tầng tiếp theo. Sử dụng **Hàng đợi (Queue)** để lưu vết.
*   **Ẩn dụ trực quan:** Giống như sóng nước loang ra khi bạn ném một viên đá.
*   **Từ khóa cốt lõi:** $O(V + E)$ time, $O(V)$ space. Ứng dụng để **tìm đường đi ngắn nhất trên đồ thị không trọng số**.
*   **Code Demo:**
    ```java
    import java.util.*;
    public void bfs(Node startNode) {
        Queue<Node> queue = new LinkedList<>(); Set<Node> visited = new HashSet<>();
        queue.add(startNode); visited.add(startNode);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.val + " ");
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor); queue.add(neighbor);
                }
            }
        }
    }
    ```

#### 2. Duyệt theo chiều sâu (Depth-First Search - DFS)
*   **Định nghĩa:** Duyệt đồ thị bắt đầu từ một đỉnh gốc, đi sâu nhất có thể theo một nhánh cho đến khi gặp ngõ cụt mới quay lui lại ngã rẽ gần nhất để đi nhánh khác. Sử dụng **Đệ quy (Call Stack)** hoặc **Ngăn xếp (Stack)**.
*   **Ẩn dụ trực quan:** Khám phá một mê cung bằng cách đi sâu hết mức, gặp ngõ cụt thì lùi lại ngã ba gần nhất.
*   **Từ khóa cốt lõi:** $O(V + E)$ time, $O(V)$ space. Ứng dụng để **phát hiện chu trình, sắp xếp topo**.
*   **Code Demo:**
    ```java
    import java.util.*;
    public void dfs(Node node, Set<Node> visited) {
        if (node == null || visited.contains(node)) return;
        System.out.print(node.val + " ");
        visited.add(node);
        for (Node neighbor : node.neighbors) {
            dfs(neighbor, visited); // Đi sâu xuống dưới
        }
    }
    ```

---

### 2.9. Quay lui (Backtracking)

*   **Định nghĩa:** Một kỹ thuật cải tiến của Vét cạn bằng đệ quy. Thuật toán xây dựng dần các ứng viên cho lời giải, nếu phát hiện ứng viên hiện tại không thể dẫn tới lời giải đúng, nó sẽ **hủy bỏ lựa chọn đó (quay lui)** để thử hướng đi khác.
*   **Ẩn dụ trực quan:** Đi giải mê cung, đến ngõ cụt thì quay ngược lại ngã rẽ và xóa các bước đi sai lầm trước đó.
*   **Từ khóa cốt lõi:** Thử và sai (trial and error), tỉa nhánh (pruning) để giảm thiểu không gian trạng thái tìm kiếm.
*   **Code Demo (Tìm tất cả các tập hợp con):**
    ```java
    import java.util.*;
    public void backtrack(List<List<Integer>> ans, List<Integer> temp, int[] nums, int start) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]); // Chọn nums[i]
            backtrack(ans, temp, nums, i + 1); // Đi tiếp
            temp.remove(temp.size() - 1); // Quay lui: Bỏ chọn để thử số khác
        }
    }
    ```

---

## 3. KỸ THUẬT TỐI ƯU HÓA THỰC CHIẾN (TECHNIQUES)

Các mẫu kỹ thuật quan trọng giúp tối ưu chương trình khi phỏng vấn Coding:

### 3.1. Kỹ thuật hai con trỏ (Two Pointers)
*   **Định nghĩa:** Sử dụng hai biến chỉ số (chỉ vào đầu và cuối mảng, hoặc cùng đi từ đầu) dịch chuyển ngược chiều hoặc cùng chiều để quét mảng, giảm độ phức tạp thời gian từ $O(n^2)$ về $O(n)$.
*   **Từ khóa cốt lõi:** Thường áp dụng trên **mảng đã sắp xếp**. Tránh các vòng lặp lồng nhau.
*   **Code Demo (Tìm 2 số có tổng bằng target trong mảng đã sort):**
    ```java
    public int[] findTwoSum(int[] sortedArr, int target) {
        int left = 0, right = sortedArr.length - 1;
        while (left < right) {
            int sum = sortedArr[left] + sortedArr[right];
            if (sum == target) return new int[]{left, right};
            if (sum > target) right--;
            else left++;
        }
        return new int[]{-1, -1};
    }
    ```

### 3.2. Cửa sổ trượt (Sliding Window)
*   **Định nghĩa:** Duy trì một phân đoạn (cửa sổ) liên tục trên mảng. Khi dịch chuyển cửa sổ sang phải, chỉ cần cộng phần tử mới lọt vào bên phải và trừ phần tử bị đẩy ra bên trái để tính toán kết quả mới trong $O(1)$.
*   **Từ khóa cốt lõi:** Áp dụng cho các bài toán tìm **mảng con liên tục**. Giảm từ $O(k \cdot n)$ về $O(n)$.
*   **Code Demo (Tổng lớn nhất của mảng con kích thước K):**
    ```java
    public int maxSumSubarray(int[] arr, int k) {
        if (arr.length < k) return -1;
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += arr[i];
        int maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k]; // Thêm phải, bớt trái
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
    ```

### 3.3. Các kỹ thuật tối ưu khác
*   **Prefix Sum (Mảng cộng dồn):** Lưu tổng lũy kế từ đầu mảng để truy vấn tổng đoạn con từ $L$ đến $R$ trong $O(1)$ bằng công thức: `sum = prefix[R] - prefix[L-1]`.
*   **Hashing (Kỹ thuật băm):** Sử dụng HashSet/HashMap để kiểm tra sự tồn tại của phần tử đã duyệt qua trong $O(1)$.
*   **Monotonic Stack (Ngăn xếp đơn điệu):** Duy trì phần tử trong Stack theo thứ tự tăng/giảm dần để tìm phần tử lớn hơn/nhỏ hơn tiếp theo (Next Greater Element) trong $O(n)$.

---

## 4. ĐỘ PHỨC TẠP THUẬT TOÁN (COMPLEXITY)

### 4.1. Cách tính Độ phức tạp thời gian (Time Complexity - Big O)

Độ phức tạp thời gian đo lường sự tăng trưởng về số lượng phép tính của thuật toán khi kích thước dữ liệu đầu vào ($n$) tiến tới vô cùng ($n \rightarrow \infty$). Để tính Big O, chúng ta tập trung vào **trường hợp xấu nhất (Worst-case)** và tuân theo 4 quy tắc cốt lõi sau:

1.  **Quy tắc bỏ qua hằng số (Drop Constants):**
    *   *Cơ chế:* Khi $n$ cực kỳ lớn, các hệ số nhân và số cộng thêm trở nên không đáng kể.
    *   *Ví dụ:* Một thuật toán thực hiện $2n + 5$ phép tính. Ta bỏ số $5$ và hệ số $2$ đi $\rightarrow$ Độ phức tạp là $O(n)$.
2.  **Quy tắc giữ lại số hạng lớn nhất (Keep the Dominant Term):**
    *   *Cơ chế:* Giữ lại thành phần có tốc độ tăng trưởng nhanh nhất và bỏ qua các số hạng nhỏ hơn.
    *   *Ví dụ:* Thuật toán chạy mất $n^2 + 3n + 100$ bước. Khi $n \rightarrow \infty$, $n^2$ sẽ lấn át hoàn toàn $3n$ và $100$. Ta viết $\rightarrow$ $O(n^2)$.
3.  **Quy tắc cộng (Các thao tác tuần tự):**
    *   *Cơ chế:* Nếu các đoạn code chạy độc lập, nối tiếp nhau, ta cộng các Big O lại.
    *   *Ví dụ:*
        ```java
        // Đoạn 1: Duyệt mảng n phần tử -> O(n)
        for (int i = 0; i < n; i++) { ... }
        // Đoạn 2: Duyệt tiếp mảng n phần tử -> O(n)
        for (int j = 0; j < n; j++) { ... }
        ```
        Tổng cộng: $O(n) + O(n) = O(2n) \rightarrow O(n)$.
4.  **Quy tắc nhân (Các thao tác lồng nhau):**
    *   *Cơ chế:* Nếu các vòng lặp lồng nhau, ta nhân các Big O của chúng lại.
    *   *Ví dụ:*
        ```java
        // Vòng lặp ngoài chạy n lần -> O(n)
        for (int i = 0; i < n; i++) {
            // Vòng lặp trong chạy n lần -> O(n)
            for (int j = 0; j < n; j++) { ... }
        }
        ```
        Tổng cộng: $O(n \cdot n) = O(n^2)$.

*🚨 LƯU Ý KHI CHIA ĐÔI DỮ LIỆU:*
Mỗi bước thực thi, dữ liệu đầu vào bị chia đôi (ví dụ: Tìm kiếm nhị phân):
```java
while (n > 1) {
    n = n / 2; // Số bước thực hiện k thỏa mãn n / 2^k = 1 -> k = log2(n)
}
```
Độ phức tạp luôn là **$O(\log n)$**.

---

### 4.2. Cách tính Độ phức tạp không gian (Space Complexity)

Độ phức tạp không gian đo lường **lượng bộ nhớ RAM bổ sung** (Memory Overhead) mà thuật toán cần sử dụng để hoàn thành việc thực thi, **không tính** vùng nhớ của dữ liệu đầu vào ban đầu (Auxiliary Space).

Các kịch bản tính toán bộ nhớ phổ biến:
1.  **Không gian hằng số - $O(1)$ Space:**
    *   *Cơ chế:* Thuật toán chỉ khởi tạo thêm một vài biến đơn lẻ (như biến đếm `i`, biến tạm `temp`, con trỏ `left`, `right`). Bộ nhớ này không đổi bất kể dữ liệu đầu vào $n$ lớn bao nhiêu.
    *   *Ví dụ:* Giải thuật Two Pointers, Linear Search, Bubble Sort.
2.  **Không gian tuyến tính - $O(n)$ Space:**
    *   *Cơ chế:* Thuật toán khởi tạo thêm cấu trúc dữ liệu mới (Mảng, List, Map, Set) có số lượng phần tử tỷ lệ thuận với dữ liệu đầu vào $n$.
    *   *Ví dụ:* Copy mảng ban đầu sang mảng mới, thuật toán nén ký tự, lưu vết đỉnh đã đi qua trong đồ thị.
3.  **Không gian ngăn xếp đệ quy (Call Stack Space):**
    *   *Cơ chế:* Mỗi lần một hàm đệ quy được gọi, hệ thống phải lưu trạng thái của hàm đó vào **Call Stack** trong bộ nhớ. Độ phức tạp không gian tỷ lệ thuận với **chiều sâu tối đa** của cây đệ quy.
    *   *Ví dụ:* Hàm tính Fibonacci đệ quy thô sâu $n$ tầng tốn $O(n)$ Space.

---

### 4.3. Bảng đối chiếu hiệu năng Big O (Big O Comparison Table)

Bảng xếp hạng hiệu năng thuật toán giúp bạn so sánh trực tiếp khi trao đổi với người phỏng vấn:

| Big O | Tốc độ | Nhận xét thực tế | Ví dụ tiêu biểu |
| :--- | :--- | :--- | :--- |
| **$O(1)$** | Siêu tốc | Không phụ thuộc vào kích thước dữ liệu đầu vào. | Lấy phần tử mảng qua index, lấy từ HashMap. |
| **$O(\log n)$** | Cực nhanh | Bị chia đôi liên tục mỗi bước, rất tốt cho dữ liệu lớn. | Tìm kiếm nhị phân (Binary Search). |
| **$O(n)$** | Tốt | Thời gian tăng tuyến tính theo kích thước dữ liệu. | Tìm kiếm tuần tự (Linear Search), duyệt mảng 1 vòng lặp. |
| **$O(n \log n)$** | Khá | Tốc độ của các thuật toán sắp xếp tối ưu. | Merge Sort, Quick Sort (trung bình). |
| **$O(n^2)$** | Chậm | Thường gặp ở các thuật toán có hai vòng lặp lồng nhau. | Bubble Sort, chèn ép thô sơ. |
| **$O(2^n)$** | Rất chậm | Phân nhánh nhị phân ở mỗi bước, số phép tính bùng nổ. | Fibonacci đệ quy thô không nhớ, tìm tập con. |
| **$O(n!)$** | Ác mộng | Duyệt qua tất cả các hoán vị của dữ liệu. | Bài toán người đi du lịch (Traveling Salesman). |

