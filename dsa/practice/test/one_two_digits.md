# Bài toán: one_two_digits (Phân rã chuỗi thành các số 1 hoặc 2 chữ số)

### 1. Đề bài giả định (Decode Ways / String Partitioning)
Cho một chuỗi ký tự `S` chỉ chứa các chữ số từ `'0'` đến `'9'`. Hãy tính số cách phân rã chuỗi này thành các số có độ dài **1 chữ số** hoặc **2 chữ số** hợp lệ.

*Một số có độ dài 1 hoặc 2 chữ số được coi là hợp lệ nếu:*
*   Không chứa chữ số `'0'` ở đầu (ví dụ: `"05"` là không hợp lệ, phải phân rã thành `"0"` và `"5"`, hoặc theo quy tắc mã hóa chữ cái từ `1` đến `26` của bài toán *Decode Ways*).
*   Nếu áp dụng theo chuẩn bài toán mã hóa chữ cái (Decode Ways - LeetCode 91): Mỗi phần tử sau khi phân rã phải nằm trong khoảng từ `1` đến `26` (tương ứng với các ký tự từ `'A'` đến `'Z'`).

---

### 2. Phân tích luồng giải (Solution Flow)

Nếu sử dụng đệ quy thông thường không nhớ (Backtracking), độ phức tạp thời gian sẽ là lũy thừa $O(2^N)$ dẫn đến việc bị giới hạn thời gian (Time Limit Exceeded) trên các test case lớn, giải thích tại sao điểm Performance của bạn chỉ đạt 75%.

Để đạt hiệu năng tối đa **100% Correctness & 100% Performance**, ta sử dụng **Quy hoạch động (Dynamic Programming)** với độ phức tạp thời gian $O(N)$:

#### Các bước tư duy:
1.  **Trạng thái:** Gọi `dp[i]` là số cách phân rã hợp lệ cho chuỗi con độ dài `i` (từ chỉ số `0` đến `i-1`).
2.  **Trường hợp cơ sở:**
    *   `dp[0] = 1` (Chuỗi rỗng có đúng 1 cách phân rã mặc định).
    *   `dp[1] = (S.charAt(0) != '0') ? 1 : 0` (Chuỗi 1 ký tự chỉ có cách phân rã nếu ký tự đó khác `'0'`).
3.  **Công thức chuyển trạng thái (Duyệt từ `i = 2` đến `N`):**
    *   **Xét phần tử 1 chữ số** tại vị trí `i-1`: Nếu ký tự `S.charAt(i-1)` khác `'0'`, nó có thể đứng độc lập. Số cách phân rã tăng thêm: `dp[i] += dp[i-1]`.
    *   **Xét phần tử 2 chữ số** tại vị trí `i-2` đến `i-1`: Lấy số tạo bởi 2 chữ số này: `twoDigitValue = Integer.parseInt(S.substring(i-2, i))`.
        *   Nếu số này nằm trong khoảng hợp lệ (từ `10` đến `26` theo luật Decode Ways, hoặc từ `10` đến `99` tùy theo định nghĩa đề bài), ta cộng thêm số cách phân rã trước đó: `dp[i] += dp[i-2]`.
4.  **Kết quả:** `dp[N]` là kết quả cần tìm.

---

### 3. Mã nguồn tối ưu (Java 21)

Dưới đây là cài đặt chuẩn hóa tối ưu $O(N)$ thời gian và $O(N)$ không gian (có thể tối ưu thêm về $O(1)$ không gian bằng cách chỉ lưu 2 trạng thái trước đó):

```java
public class Solution {
    public int solution(String S) {
        if (S == null || S.length() == 0 || S.charAt(0) == '0') {
            return 0;
        }

        int n = S.length();
        int[] dp = new int[n + 1];
        
        // Cơ sở quy hoạch động
        dp[0] = 1; 
        dp[1] = 1; // S.charAt(0) đã được kiểm tra khác '0' ở trên

        for (int i = 2; i <= n; i++) {
            // 1. Xét cụm 1 chữ số ở vị trí i-1
            char oneDigit = S.charAt(i - 1);
            if (oneDigit != '0') {
                dp[i] += dp[i - 1];
            }

            // 2. Xét cụm 2 chữ số ở vị trí i-2 đến i-1
            String twoDigitsStr = S.substring(i - 2, i);
            int twoDigitsVal = Integer.parseInt(twoDigitsStr);
            
            // Điều kiện hợp lệ: không bắt đầu bằng '0' và giá trị nằm trong khoảng [10, 26] (Decode Ways)
            // (Nếu đề bài chỉ yêu cầu phân rã thành số 2 chữ số bất kỳ, điều kiện sẽ là từ 10 đến 99)
            if (twoDigitsVal >= 10 && twoDigitsVal <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
```

*   **Độ phức tạp thời gian:** $O(N)$ - Chỉ cần duyệt qua chuỗi đúng 1 lần.
*   **Độ phức tạp không gian:** $O(N)$ - Lưu trữ mảng `dp`. (Có thể tối ưu về $O(1)$ bằng cách dùng 2 biến lưu `prev1` và `prev2`).
