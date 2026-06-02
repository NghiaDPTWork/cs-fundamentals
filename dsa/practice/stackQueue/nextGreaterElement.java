/**
 * Tìm phần tử lớn hơn tiếp theo I (Next Greater Element I)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Stack (Deque) và HashMap
 * - Ý tưởng: Duyệt qua mảng nums2, duy trì một stack giảm dần. Khi gặp phần tử lớn hơn đỉnh stack,
 *            pop đỉnh stack ra và lưu cặp (phần tử bị pop, phần tử hiện tại) vào HashMap.
 *            Sau đó duyệt qua nums1 để lấy kết quả từ HashMap.
 * - Ưu điểm:
 *   + Cách tiếp cận tổng quát, hoạt động tốt với mọi dải giá trị của phần tử (kể cả số âm hay số rất lớn).
 *   + Sử dụng cấu trúc dữ liệu chuẩn của Java.
 * - Nhược điểm:
 *   + Hiệu năng thấp và tốn bộ nhớ hơn do chi phí cấp phát đối tượng (Map, Deque) và boxing/unboxing liên tục.
 * 
 * CÁCH 2: Tối ưu hóa bằng mảng nguyên thủy (Custom Array Stack & Map Simulation)
 * - Ý tưởng: Vì các phần tử trong nums1 và nums2 giới hạn trong khoảng [0, 10000], ta có thể dùng mảng
 *            `map` kích thước 10001 để thay thế cho HashMap và mảng `stack` để thay thế cho Deque.
 * - Ưu điểm:
 *   + Tốc độ thực thi cực nhanh (0ms - 1ms) và tối ưu hóa bộ nhớ vượt trội do loại bỏ hoàn toàn chi phí đối tượng.
 * - Nhược điểm:
 *   + Phụ thuộc chặt chẽ vào giới hạn dữ liệu đầu vào. Nếu dữ liệu chứa số âm hoặc số lớn vượt quá giới hạn mảng,
 *     giải pháp này sẽ bị lỗi ArrayIndexOutOfBoundsException.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[nums1.length];

        // Duyệt nums2 tìm next greater
        for(int num: nums2){

            while(!stack.isEmpty() && num > stack.peek()){
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // Duyệt nums1 và trả result
        for(int i = 0; i <= nums1.length - 1; i++){
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }
}

//// cách 2
class Solution2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        int[] map = new int[10001];
        int[] stack = new int[nums2.length];
        int[] result = new int[nums1.length];
        int top = -1;

        // Duyệt nums2 tìm next greater
        for(int num: nums2){

            while(top >= 0 && num > stack[top]){
                map[stack[top]] = num;
                top--;
            }

            top++;
            stack[top] = num;
        }

        // Duyệt nums1 và trả result
        int nextGreater = 0;
        for(int i = 0; i <= nums1.length - 1; i++){
            nextGreater = map[nums1[i]];

            result[i] = (nextGreater == 0) ? -1 : nextGreater;
        }

        return result;
    }
}