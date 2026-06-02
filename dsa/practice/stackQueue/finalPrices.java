/**
 * Giá cuối cùng sau khi áp dụng giảm giá đặc biệt (Final Prices With a Special Discount in a Shop)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Duyệt trâu hai vòng lặp (Brute Force)
 * - Ý tưởng: Với mỗi phần tử i, duyệt qua các phần tử j từ i + 1 để tìm phần tử đầu tiên nhỏ hơn hoặc bằng nó.
 *            Nếu tìm thấy, trừ giá trị đi và chuyển sang phần tử tiếp theo.
 * - Ưu điểm:
 *   + Đơn giản, trực quan, không cần cấu trúc dữ liệu bổ trợ nào khác ngoài mảng kết quả.
 *   + Không gian bộ nhớ bổ trợ tối ưu O(1) (nếu không tính mảng kết quả).
 * - Nhược điểm:
 *   + Độ phức tạp thời gian là O(N^2), không tối ưu khi kích thước mảng lớn.
 * 
 * CÁCH 2: Sử dụng Monotonic Stack (Ngăn xếp đơn điệu bằng mảng nguyên thủy)
 * - Ý tưởng: Duy trì một stack lưu trữ các chỉ số (index) có giá trị tăng dần. 
 *            Khi duyệt qua phần tử hiện tại, nếu nó nhỏ hơn hoặc bằng phần tử ở đỉnh stack, ta tiến hành
 *            trừ giá trị (áp dụng giảm giá) của phần tử ở đỉnh stack đó và pop ra. Sau đó đẩy chỉ số hiện tại vào stack.
 * - Ưu điểm:
 *   + Tối ưu hóa thời gian chạy vượt trội với độ phức tạp thời gian O(N) (mỗi phần tử chỉ được đẩy và lấy ra khỏi stack tối đa 1 lần).
 *   + Bộ nhớ tối ưu nhờ sử dụng mảng nguyên thủy để mô phỏng stack thay vì sử dụng Stack/Deque đối tượng.
 * - Nhược điểm:
 *   + Khó hiểu hơn một chút so với cách duyệt trâu, đòi hỏi kiến thức về cấu trúc dữ liệu Monotonic Stack.
 */

class Solution {
    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = prices[i];

            for (int j = i + 1; j < len; j++) {

                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
                
            }
        }  

        return result;
    }
}


//// cách 2
class Solution2 {
    public int[] finalPrices(int[] prices) {
        int len = prices.length;
        int[] stack = new int[len];
        int top = -1;

        for (int i = 0; i <= len - 1; i++) {

            while (top >= 0 && prices[i] <= prices[stack[top]]) {
                prices[stack[top]] -= prices[i];
                top--;
            }

            top++;
            stack[top] = i;
        }  

        return prices;
    }
}