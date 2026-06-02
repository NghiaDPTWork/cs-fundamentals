/**
 * [LeetCode 768] Max Chunks to Make Sorted II
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA CÁC CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Monotonic Stack (Ngăn xếp đơn điệu)
 * - Ý tưởng: Ta lưu trữ giá trị lớn nhất đại diện cho mỗi chunk trong stack.
 *            Duyệt qua mảng:
 *            - Nếu stack rỗng hoặc phần tử hiện tại `val >= stack.peek()`, nó có thể tự tạo thành 1 chunk mới độc lập.
 *              Ta push `val` vào stack.
 *            - Ngược lại (`val < stack.peek()`), nghĩa là phần tử hiện tại nhỏ hơn giá trị lớn nhất của chunk trước đó,
 *              nên chúng ta buộc phải gộp phần tử này và các chunk trước đó có max lớn hơn `val` vào chung một chunk.
 *              Ta lưu lại giá trị lớn nhất đã pop ra (`curMax = stack.peek()`), tiếp tục pop các phần tử lớn hơn `val`,
 *              và cuối cùng push lại `curMax` đại diện cho chunk mới sau khi gộp.
 *            Kết quả chính là kích thước của Stack.
 * - Ưu điểm:
 *   + Độ phức tạp thời gian O(N), không gian O(N) trong trường hợp xấu nhất.
 *   + Tư duy rất tổng quát và là cách giải tối ưu sử dụng cấu trúc dữ liệu Stack.
 * 
 * CÁCH 2: Tiền xử lý mảng cực đại bên trái và cực tiểu bên phải (Left Max & Right Min)
 * - Ý tưởng: Ta có thể chia mảng tại chỉ số `i` thành một chunk nếu và chỉ nếu toàn bộ các phần tử phía trước `[0...i]`
 *            đều nhỏ hơn hoặc bằng toàn bộ các phần tử phía sau `[i+1...n-1]`.
 *            Điều này tương đương với: `max(arr[0...i]) <= min(arr[i+1...n-1])`.
 *            Ta xây dựng mảng `rightMin` chứa giá trị nhỏ nhất từ vị trí hiện tại đến cuối mảng.
 *            Sau đó duyệt qua mảng, duy trì một giá trị `leftMax` từ đầu đến hiện tại.
 *            Tại mỗi vị trí `i`, nếu `leftMax <= rightMin[i+1]`, ta có thể kết thúc một chunk tại đây.
 * - Ưu điểm:
 *   + O(N) thời gian, O(N) không gian.
 *   + Rất trực quan và dễ hiểu hơn so với giải thuật Stack, không cần thao tác với cấu trúc dữ liệu phức tạp.
 */

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int maxVal = stack.peek();
                while (!stack.isEmpty() && num < stack.peek()) {
                    stack.pop();
                }
                stack.push(maxVal);
            }
        }
        return stack.size();
    }
}

//// cách 2
class Solution2 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] rightMin = new int[n];
        
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        
        int chunks = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            if (leftMax <= rightMin[i + 1]) {
                chunks++;
            }
        }
        
        return chunks + 1;
    }
}
