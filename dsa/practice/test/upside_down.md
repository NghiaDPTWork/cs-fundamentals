# Bài toán: upside_down (Số đối xứng khi quay ngược 180 độ)

### 1. Đề bài
Một số nguyên (dưới dạng chuỗi `S`) được gọi là số **"upside down"** (Strobogrammatic Number) nếu khi ta xoay ngược nó 180 độ, giá trị hiển thị của nó vẫn giữ nguyên không đổi.

Hãy viết hàm kiểm tra xem chuỗi `S` cho trước có phải là số "upside down" hợp lệ hay không.

#### Quy tắc xoay các chữ số:
*   Các chữ số giữ nguyên giá trị khi xoay ngược 180 độ: `'0'`, `'1'`, `'8'`.
*   Các chữ số chuyển đổi qua lại cho nhau: `'6'` xoay ngược thành `'9'`, và `'9'` xoay ngược thành `'6'`.
*   Các chữ số khác (`'2'`, `'3'`, `'4'`, `'5'`, `'7'`) khi xoay ngược sẽ không tạo thành chữ số hợp lệ.

*Ví dụ:*
*   `"69"` -> Xoay ngược ra `"69"` -> Hợp lệ (`true`).
*   `"88"` -> Xoay ngược ra `"88"` -> Hợp lệ (`true`).
*   `"962"` -> Có chữ số `2` không thể xoay -> Không hợp lệ (`false`).
*   `"66"` -> Xoay ngược ra `"99"` -> Không giống ban đầu -> Không hợp lệ (`false`).

---

### 2. Phân tích luồng giải (Solution Flow)

Đây là bài toán kiểm tra tính chất đối xứng có biến đổi. Cách giải tối ưu nhất là sử dụng kỹ thuật **Hai con trỏ (Two Pointers)** kiểm tra từ ngoài vào trong:

1.  Đặt con trỏ `left = 0` (đầu chuỗi) và con trỏ `right = S.length() - 1` (cuối chuỗi).
2.  Lặp khi `left <= right`:
    *   Lấy ký tự tại vị trí `left` và `right`.
    *   Kiểm tra xem cặp ký tự `(charL, charR)` có nằm trong danh sách các cặp đối xứng hợp lệ sau đây hay không:
        *   `('0', '0')`
        *   `('1', '1')`
        *   `('8', '8')`
        *   `('6', '9')`
        *   `('9', '6')`
    *   Nếu không thỏa mãn bất kỳ cặp nào ở trên, kết luận ngay lập tức là không hợp lệ (`false`).
    *   Nếu thỏa mãn, tăng `left++` và giảm `right--`.
3.  Nếu đi qua hết vòng lặp mà không gặp lỗi, trả về `true`.

---

### 3. Mã nguồn tối ưu (Java 21)

```java
import java.util.Map;

public class Solution {
    public boolean solution(String S) {
        if (S == null || S.length() == 0) {
            return false;
        }

        int left = 0;
        int right = S.length() - 1;

        while (left <= right) {
            char cL = S.charAt(left);
            char cR = S.charAt(right);

            // Kiểm tra các trường hợp đối xứng hợp lệ
            if (cL == '0' && cR == '0') {
                // Hợp lệ
            } else if (cL == '1' && cR == '1') {
                // Hợp lệ
            } else if (cL == '8' && cR == '8') {
                // Hợp lệ
            } else if (cL == '6' && cR == '9') {
                // Hợp lệ
            } else if (cL == '9' && cR == '6') {
                // Hợp lệ
            } else {
                // Gặp bất kỳ ký tự nào không khớp cặp đối xứng xoay -> trả về false
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

*   **Độ phức tạp thời gian:** $O(N)$ - Duyệt qua chuỗi từ hai đầu vào giữa, số bước lặp tối đa là $N / 2$.
*   **Độ phức tạp không gian:** $O(1)$ - Không sử dụng cấu trúc dữ liệu phụ, bộ nhớ tối ưu tuyệt đối.
