/**
 * ĐỀ BÀI: one_two_digits (Phân rã chuỗi thành các số 1 hoặc 2 chữ số)
 * 
 * Cho một chuỗi ký tự S chỉ chứa các chữ số từ '0' đến '9'. Hãy tính số cách phân rã 
 * chuỗi này thành các số có độ dài 1 chữ số hoặc 2 chữ số hợp lệ.
 * 
 * Luật hợp lệ (theo chuẩn bài toán Decode Ways - LeetCode 91):
 * - Không chứa chữ số '0' ở đầu (ví dụ: "05" không hợp lệ, "0" một mình thì tùy đề bài, 
 *   ở đây '0' không thể đứng một mình để decode).
 * - Mỗi số phân rã phải nằm trong khoảng từ 1 đến 26 (tương ứng với ký tự 'A' đến 'Z').
 * 
 * PHÂN TÍCH LUỒNG GIẢI (FLOW):
 * - Sử dụng Quy hoạch động (Dynamic Programming) với độ phức tạp thời gian O(N).
 * - dp[i] lưu số cách phân rã hợp lệ cho chuỗi con độ dài i.
 * - Cơ sở: dp[0] = 1 (mặc định), dp[1] = (S.charAt(0) != '0') ? 1 : 0.
 * - Duyệt i từ 2 đến N:
 *   + Nếu S.charAt(i-1) != '0', ta cộng dp[i-1] vào dp[i] (xét phần tử 1 chữ số).
 *   + Nếu số 2 chữ số tại vị trí i-2 đến i-1 nằm trong khoảng [10, 26], ta cộng dp[i-2] vào dp[i].
 * 
 * ĐỘ PHỨC TẠP:
 * - Thời gian: O(N) - duyệt qua chuỗi 1 lần.
 * - Không gian: O(N) - có thể tối ưu về O(1) bằng cách dùng 2 biến trạng thái.
 */
public class one_two_digits {
    public int solution(String S) {
        if (S == null || S.length() == 0 || S.charAt(0) == '0') {
            return 0;
        }

        int n = S.length();
        int[] dp = new int[n + 1];
        
        // Trường hợp cơ sở
        dp[0] = 1;
        dp[1] = 1; // Ký tự đầu tiên đã được check khác '0'

        for (int i = 2; i <= n; i++) {
            // 1. Kiểm tra phần tử 1 chữ số ở vị trí i-1
            char oneDigit = S.charAt(i - 1);
            if (oneDigit != '0') {
                dp[i] += dp[i - 1];
            }

            // 2. Kiểm tra phần tử 2 chữ số ở vị trí i-2 đến i-1
            String twoDigitsStr = S.substring(i - 2, i);
            int twoDigitsVal = Integer.parseInt(twoDigitsStr);
            
            // Điều kiện hợp lệ từ 10 đến 26
            if (twoDigitsVal >= 10 && twoDigitsVal <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    // Hàm Main để test trực tiếp
    public static void main(String[] args) {
        one_two_digits solver = new one_two_digits();
        System.out.println("S = \"111\" -> Cách giải: " + solver.solution("111")); // Output: 3
        System.out.println("S = \"26\" -> Cách giải: " + solver.solution("26"));   // Output: 2
        System.out.println("S = \"06\" -> Cách giải: " + solver.solution("06"));   // Output: 0
    }
}
