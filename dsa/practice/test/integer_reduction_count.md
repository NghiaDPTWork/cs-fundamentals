# Bài toán: integer_reduction_count (Đếm số bước giảm số nguyên về 0)

### 1. Đề bài
Cho một chuỗi `S` biểu diễn một số nguyên không âm cực kỳ lớn. Ta thực hiện các thao tác sau để giảm số nguyên này về 0:
*   Nếu số hiện tại là số **chẵn**: chia số đó cho 2.
*   Nếu số hiện tại là số **lẻ**: trừ số đó đi 1.

Hãy đếm số bước thực hiện tối thiểu để giảm số nguyên đó về 0.

*Ví dụ:*
*   Với số `13` (nhị phân: `"1101"`):
    *   13 là số lẻ -> trừ 1 còn 12 (1 bước)
    *   12 là số chẵn -> chia 2 còn 6 (2 bước)
    *   6 là số chẵn -> chia 2 còn 3 (3 bước)
    *   3 là số lẻ -> trừ 1 còn 2 (4 bước)
    *   2 là số chẵn -> chia 2 còn 1 (5 bước)
    *   1 là số lẻ -> trừ 1 còn 0 (6 bước)
    *   **Kết quả:** 6 bước.

---

### 2. Phân tích nguyên nhân điểm thấp (42% Correctness - 0% Performance)

Trong bài thi Codility của bạn, bài này chỉ đạt **27% tổng điểm** (42% Correctness và 0% Performance) vì các lý do sau:
1.  **Lỗi tràn bộ nhớ (Overflow):** Nếu bạn chuyển đổi chuỗi `S` trực tiếp sang kiểu `int` hoặc `long` (bằng `Integer.parseInt(S)` hoặc `Long.parseLong(S)`), chương trình sẽ bị sập ngay lập tức (ném ra `NumberFormatException`) khi gặp các chuỗi số siêu lớn có độ dài hàng ngàn chữ số. Điều này khiến bạn mất điểm Correctness ở hầu hết các test case lớn.
2.  **Hiệu năng kém (Timeout):** Việc mô phỏng chia và trừ trực tiếp trên các đối tượng số lớn (như `BigInteger`) lặp đi lặp lại nhiều lần sẽ có độ phức tạp thời gian rất lớn ($O(N^2)$), dẫn đến Time Limit Exceeded (0% Performance).

---

### 3. Thuật toán tối ưu $O(N)$ (Solution Flow)

Bản chất của phép toán chia cho 2 và trừ đi 1 trên hệ nhị phân (Binary) rất đơn giản:
*   **Chia cho 2:** Tương đương với việc dịch phải 1 bit (xóa bỏ chữ số `'0'` ở cuối cùng của chuỗi nhị phân). Tốn **1 bước**.
*   **Trừ đi 1:** Tương đương với việc chuyển bit cuối cùng từ `'1'` thành `'0'`. Tốn **1 bước**. Sau đó số đó trở thành số chẵn, ở bước tiếp theo ta lại chia 2 (xóa bit cuối). Do đó, một bit `'1'` ở cuối sẽ cần tổng cộng **2 bước** (1 bước trừ và 1 bước chia) để biến mất.
*   **Trường hợp đặc biệt:** Bit đầu tiên bên trái (bit có trọng số lớn nhất, luôn là `'1'`) chỉ cần đúng **1 bước** trừ để biến số đó về 0 (không cần bước chia tiếp theo vì số đã bằng 0).

#### Thuật toán cụ thể cho chuỗi nhị phân:
Nếu đề bài cho `S` là chuỗi nhị phân (Binary String):
1.  Bỏ qua các ký tự `'0'` thừa ở đầu chuỗi (nếu có).
2.  Nếu chuỗi sau khi lọc là rỗng hoặc chỉ là `"0"`, trả về 0 bước.
3.  Duyệt chuỗi nhị phân từ cuối lên đầu (từ `S.length() - 1` về `1`):
    *   Nếu gặp `'0'`, ta cộng **1 bước** (chỉ cần dịch phải).
    *   Nếu gặp `'1'`, ta cộng **2 bước** (1 bước trừ 1 để thành '0' và 1 bước dịch phải).
4.  Khi duyệt đến ký tự đầu tiên (chỉ số `0`), ký tự này luôn là `'1'`. Ta chỉ cần cộng thêm **1 bước** (bước cuối cùng để trừ về 0).

#### Thuật toán cụ thể cho chuỗi thập phân (Decimal String):
Nếu đề bài cho `S` là chuỗi thập phân thông thường:
1.  Ta dùng `BigInteger` của Java để chuyển đổi chuỗi thập phân sang chuỗi nhị phân: `String binary = new BigInteger(S).toString(2);`
2.  Áp dụng thuật toán đếm bước $O(N)$ trên chuỗi nhị phân vừa thu được. Việc chuyển đổi từ hệ 10 sang hệ 2 chỉ diễn ra 1 lần duy nhất, đảm bảo tốc độ cực nhanh.

---

### 4. Mã nguồn tối ưu (Java 21)

```java
import java.math.BigInteger;

public class Solution {
    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }

        // Trường hợp 1: Nếu đề bài cho S là chuỗi nhị phân (phổ biến trong Codility)
        return solveForBinary(S);

        // Trường hợp 2: Nếu đề bài cho S là chuỗi thập phân (Uncomment dòng dưới nếu cần)
        // return solveForDecimal(S);
    }

    private int solveForBinary(String binary) {
        // Loại bỏ các chữ số '0' ở đầu (nếu có)
        int start = 0;
        while (start < binary.length() && binary.charAt(start) == '0') {
            start++;
        }

        // Nếu chuỗi toàn số '0'
        if (start == binary.length()) {
            return 0;
        }

        int steps = 0;
        // Duyệt từ phải sang trái, dừng trước ký tự đầu tiên (chỉ số start)
        for (int i = binary.length() - 1; i > start; i--) {
            if (binary.charAt(i) == '0') {
                steps += 1; // Số chẵn: 1 bước chia 2 (dịch phải)
            } else {
                steps += 2; // Số lẻ: 1 bước trừ 1 + 1 bước chia 2
            }
        }

        // Ký tự đầu tiên (luôn là '1') chỉ cần 1 bước trừ về 0
        steps += 1;

        return steps;
    }

    private int solveForDecimal(String decimal) {
        if (decimal.equals("0")) {
            return 0;
        }
        // Chuyển đổi thập phân lớn sang chuỗi nhị phân
        BigInteger num = new BigInteger(decimal);
        String binary = num.toString(2);
        
        return solveForBinary(binary);
    }
}
```

*   **Độ phức tạp thời gian:** $O(N)$ với $N$ là độ dài chuỗi nhị phân (Duyệt qua chuỗi đúng 1 lần).
*   **Độ phức tạp không gian:** $O(1)$ nếu là chuỗi nhị phân, $O(N)$ nếu cần chuyển đổi hệ số (Tối ưu tuyệt đối so với phép chia/trừ BigInteger thông thường).
