/**
 * Kiểm tra tính hợp lệ của chuỗi dấu ngoặc (Valid Parentheses)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng cấu trúc dữ liệu Stack (java.util.Stack)
 * - Ý tưởng: Duyệt qua chuỗi ký tự. Khi gặp ngoặc mở (`(`, `[`, `{`), đẩy ngoặc đóng tương ứng vào Stack. 
 *            Khi gặp ngoặc đóng, so sánh với phần tử được pop ra từ Stack. Nếu lệch hoặc Stack rỗng, trả về false.
 *            Cuối cùng, chuỗi hợp lệ nếu Stack rỗng.
 * - Ưu điểm:
 *   + Code ngắn gọn, trực quan, dễ hiểu. Logic map ngoặc mở sang ngoặc đóng trực tiếp lúc push giúp so sánh đơn giản.
 * - Nhược điểm:
 *   + Hiệu năng trung bình: Lớp `java.util.Stack` được thiết kế có cơ chế đồng bộ hóa (synchronized) làm chậm tốc độ thực thi.
 *   + Tốn bộ nhớ: Việc lưu trữ đối tượng `Character` (thay vì kiểu nguyên thủy `char`) gây ra chi phí boxing/unboxing và cấp phát vùng nhớ heap.
 * 
 * CÁCH 2: Tự định nghĩa Stack bằng mảng ký tự nguyên thủy (Custom Primitive Array Stack)
 * - Ý tưởng: Dùng một mảng `char[] stack` có kích thước tối đa bằng chiều dài chuỗi `s` và biến `head` trỏ đỉnh Stack.
 *            Khi gặp ngoặc mở, thêm vào mảng và tăng `head`. Khi gặp ngoặc đóng, kiểm tra khớp với ký tự tại đỉnh mảng (`stack[--head]`).
 * - Ưu điểm:
 *   + Tối ưu hiệu năng vượt trội: Không có overhead từ đồng bộ hóa hay boxing/unboxing, tốc độ chạy đạt mức tối đa.
 *   + Tiết kiệm bộ nhớ: Chỉ dùng mảng nguyên thủy `char[]` nằm liền kề trên bộ nhớ.
 * - Nhược điểm:
 *   + Code dài hơn, đòi hỏi lập trình viên tự quản lý chỉ số mảng (`head`) cẩn thận để tránh lỗi chỉ số.
 */

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> myStack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                myStack.push(')');
            } else if (c == '[') {
                myStack.push(']');
            } else if (c == '{') {
                myStack.push('}');
            } else if (myStack.isEmpty() || c != myStack.pop()) {
                return false;
            }
        }
        return myStack.isEmpty();
    }
}

//// cách 2
class Solution2 {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) return false;

        char[] stack = new char[n];
        int head = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack[head++] = c;
            } else {
                if (head == 0) return false;
                char open = stack[--head];
                if ((c == ')' && open != '(') ||
                    (c == ']' && open != '[') ||
                    (c == '}' && open != '{')) {
                    return false;
                }
            }
        }
        return head == 0;
    }
}