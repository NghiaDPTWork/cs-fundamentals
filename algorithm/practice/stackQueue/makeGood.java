/**
 * Làm cho chuỗi trở nên "tốt" (Make The String Great)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng cấu trúc dữ liệu Stack (java.util.Stack)
 * - Ý tưởng: Duyệt qua từng ký tự của chuỗi `s`. So sánh ký tự hiện tại với ký tự ở đỉnh Stack:
 *            Nếu chúng tạo thành cặp hoa-thường trùng nhau (độ lệch mã ASCII bằng 32), ta pop phần tử đỉnh Stack.
 *            Ngược lại, ta push ký tự hiện tại vào Stack.
 *            Cuối cùng, lấy các ký tự trong Stack ra và nối lại thành chuỗi kết quả.
 * - Ưu điểm:
 *   + Trực quan, dễ hiểu, tận dụng các cấu trúc dữ liệu có sẵn giúp mã nguồn sạch sẽ.
 * - Nhược điểm:
 *   + Hiệu năng thấp và tốn bộ nhớ do chi phí boxing/unboxing kiểu dữ liệu (`char` sang `Character`), 
 *     đồng thời việc xây dựng lại chuỗi từ Stack thông qua StringBuilder hoặc nối chuỗi gây tốn thêm tài nguyên.
 * 
 * CÁCH 2: Tối ưu hóa bộ nhớ dùng mảng ký tự In-place (In-place Character Array Stack)
 * - Ý tưởng: Tận dụng trực tiếp mảng ký tự được chuyển đổi từ chuỗi gốc `stack = s.toCharArray()`.
 *            Sử dụng con trỏ `top = -1` đại diện cho đỉnh của Stack ảo.
 *            Duyệt qua từng ký tự ở chỉ số `idx`:
 *            + Nếu `top >= 0` và khoảng cách ASCII giữa `stack[idx]` và `stack[top]` là 32, ta giảm `top` (pop).
 *            + Ngược lại, ta tăng `top` và ghi đè `stack[top] = stack[idx]`.
 *            Cuối cùng, trả về chuỗi con từ chỉ số 0 đến `top + 1`.
 * - Ưu điểm:
 *   + Tốc độ thực thi cực nhanh và tối ưu bộ nhớ vượt trội do thao tác trực tiếp trên mảng ký tự nguyên thủy, 
 *     tránh tạo các đối tượng trung gian.
 * - Nhược điểm:
 *   + Logic sử dụng con trỏ và ghi đè in-place cần phải kiểm soát chỉ số cẩn thận để tránh lỗi truy cập mảng.
 */

import java.util.Stack;

class Solution {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(c - stack.peek()) == 32) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}

//// cách 2
class Solution2 {
    public String makeGood(String s) {
        char[] stack = s.toCharArray();
        int len = s.length();
        int top = -1;

        for (int idx = 0; idx <= len - 1; idx++) {
            if (top >= 0 && Math.abs(stack[idx] - stack[top]) == 32) {
                top--;
            } else {
                top++;
                stack[top] = stack[idx];
            }
        }

        return String.valueOf(stack, 0, top + 1);
    }
}