/**
 * Tìm phần tử lớn hơn tiếp theo II (Next Greater Element II)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Stack đối tượng (Deque) kết hợp vòng lặp mô phỏng mảng vòng (Circular Array)
 * - Ý tưởng: Vì mảng là mảng vòng, ta duyệt 2 lần độ dài của mảng (từ 0 đến 2*len - 1). 
 *            Dùng phép toán chia lấy dư `i % len` để tìm chỉ số thực tế trong mảng.
 *            Sử dụng một `Deque<Integer>` để làm stack lưu chỉ số của các phần tử.
 *            Khi tìm thấy phần tử lớn hơn đỉnh stack, ta cập nhật kết quả và pop khỏi stack.
 *            Chỉ push phần tử vào stack ở lượt duyệt đầu tiên (i < len).
 * - Ưu điểm:
 *   + Cực kỳ trực quan, tận dụng các cấu trúc dữ liệu chuẩn của Java, an toàn và dễ bảo trì.
 * - Nhược điểm:
 *   + Hiệu năng trung bình và tốn thêm chi phí bộ nhớ do cơ chế boxing/unboxing giữa `int` và `Integer` trong Deque.
 * 
 * CÁCH 2: Tối ưu hóa bằng mảng nguyên thủy (Custom Array Stack)
 * - Ý tưởng: Tương tự Cách 1 nhưng thay thế Deque bằng một mảng số nguyên `stack` tự quản lý thông qua con trỏ `top`.
 * - Ưu điểm:
 *   + Tốc độ xử lý cực kỳ nhanh và tối ưu hóa bộ nhớ tối đa (chạy mất 1ms - 2ms trên LeetCode) nhờ loại bỏ hoàn toàn các lớp bao đối tượng.
 * - Nhược điểm:
 *   + Cần quản lý con trỏ `top` của stack thủ công, nếu logic không chuẩn dễ dẫn đến các lỗi ArrayIndexOutOfBoundsException.
 */

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * len; i++) {
            int currentIdx = i % len;
            while (!stack.isEmpty() && nums[currentIdx] > nums[stack.peek()]) {
                result[stack.pop()] = nums[currentIdx];
            }
            if (i < len) {
                stack.push(currentIdx);
            }
        }

        return result;
    }
}

//// cách 2
class Solution2 {
    public int[] nextGreaterElements(int[] nums) {
        
        int len = nums.length;
        int[] stack = new int[len];
        int[] result = new int[len];
        int top = -1;

        for (int i = 0; i < len; i++) {
            result[i] = -1;
        }

        int currentIdx = 0;
        for(int i = 0; i <= 2*len - 1; i++){
            currentIdx = i % len; 

            while(top >= 0 && nums[currentIdx] > nums[stack[top]]){
                result[stack[top]] = nums[currentIdx];
                top--;
            }

            if (i <= len - 1) {
                top++;
                stack[top] = currentIdx;
            }
        }

         return result;
    }
}