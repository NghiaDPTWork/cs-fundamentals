/**
 * So sánh chuỗi chứa ký tự Backspace (Backspace String Compare)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng cấu trúc dữ liệu Stack (java.util.Stack)
 * - Ý tưởng: Xây dựng một hàm bổ trợ nhận vào một chuỗi và trả về Stack chứa các ký tự sau khi xử lý:
 *            Duyệt qua từng ký tự, nếu là '#' thì pop (nếu Stack không rỗng), ngược lại push ký tự đó vào Stack.
 *            Sau đó so sánh hai Stack được trả về từ hai chuỗi đầu vào.
 * - Ưu điểm:
 *   + Trực quan, dễ hiểu, logic khớp hoàn toàn với bản chất hoạt động của phím Backspace (xóa ký tự vừa gõ gần nhất).
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Độ phức tạp không gian là O(S + T) để lưu trữ các đối tượng ký tự trong Stack.
 *   + Hiệu năng trung bình do chi phí boxing/unboxing liên tục giữa kiểu nguyên thủy `char` và lớp bao `Character`.
 * 
 * CÁCH 2: Xử lý In-place bằng mảng ký tự (Custom Array Stack / Two Pointers Simulation)
 * - Ý tưởng: Chuyển đổi các chuỗi thành mảng ký tự `char[]`.
 *            Xây dựng hàm `process` để thực hiện thao tác xóa ký tự trực tiếp trên mảng thông qua con trỏ `top`.
 *            Nếu gặp ký tự khác '#', ghi đè ký tự đó vào `arr[top]` và tăng `top`. Nếu gặp '#', lùi `top` lại 1 đơn vị (nếu `top > 0`).
 *            Cuối cùng, so sánh hai mảng ký tự trong phạm vi từ chỉ số 0 đến độ dài thực tế sau khi xử lý.
 * - Ưu điểm:
 *   + Tốc độ thực thi cực nhanh và tiết kiệm bộ nhớ vượt trội do tránh được việc cấp phát các đối tượng Stack hay Character phức tạp.
 * - Nhược điểm:
 *   + Cần viết thêm hàm tiện ích tự quản lý con trỏ ghi đè, nếu không cẩn thận dễ gây lỗi tràn mảng hoặc sai lệch chỉ số.
 */

import java.util.Stack;

class Solution {
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private Stack<Character> build(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack;
    }
}

//// cách 2
class Solution2 {
    public boolean backspaceCompare(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        int lenS = process(sArr);
        int lenT = process(tArr);

        if (lenS != lenT) {
            return false;
        }

        for (int i = 0; i < lenS; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }

        return true;
    }

    private int process(char[] arr) {
        int top = 0;
        int len = arr.length;

        for (int i = 0; i <= len - 1; i++) {
            if (arr[i] != '#') {
                arr[top] = arr[i];
                top++;
            } else if (top > 0) {
                top--;
            }
        }

        return top;
    }
}