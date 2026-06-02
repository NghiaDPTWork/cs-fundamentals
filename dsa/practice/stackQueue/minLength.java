/**
 * Tìm độ dài tối thiểu của chuỗi sau khi xóa các chuỗi con "AB" hoặc "CD" (Minimum String Length After Removing Substrings)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng mảng Stack phụ trợ (Custom Array Stack)
 * - Ý tưởng: Chuyển đổi chuỗi `s` thành mảng ký tự `charS`. Khởi tạo một mảng `stack` có kích thước bằng độ dài chuỗi 
 *            và con trỏ `top = -1`. Duyệt qua từng ký tự trong `charS`, nếu đỉnh `stack` là 'A' và ký tự hiện tại là 'B', 
 *            hoặc đỉnh `stack` là 'C' và ký tự hiện tại là 'D', ta pop bằng cách giảm `top`. Ngược lại, push ký tự đó vào `stack`.
 * - Ưu điểm:
 *   + Hiệu năng cao: Sử dụng mảng ký tự nguyên thủy giúp tránh các lớp đối tượng phức tạp như `Stack` hay `Character`.
 * - Nhược điểm:
 *   + Tốn bộ nhớ phụ: Cần khởi tạo thêm mảng `stack` có kích thước bằng chuỗi đầu vào s, độ phức tạp không gian phụ trợ là O(N).
 * 
 * CÁCH 2: Tối ưu hóa bộ nhớ dùng mảng ký tự In-place (In-place Character Array Stack)
 * - Ý tưởng: Tận dụng trực tiếp mảng ký tự được chuyển đổi từ chuỗi gốc `stack = s.toCharArray()`. 
 *            Duyệt `j` từ 0 đến hết mảng: nếu phát hiện cặp trùng khớp giữa phần tử hiện tại `stack[j]` 
 *            và đỉnh stack `stack[top]` (tức là "AB" hoặc "CD"), ta lùi con trỏ `top`. 
 *            Ngược lại, ghi đè ký tự `stack[j]` lên đỉnh mới `stack[++top]`.
 * - Ưu điểm:
 *   + Tiết kiệm bộ nhớ vượt trội: Đạt O(1) auxiliary space (ngoại trừ bộ nhớ của `s.toCharArray()` cần cho đầu vào). 
 *     Không cần khởi tạo mảng phụ để lưu stack.
 *   + Tốc độ thực thi cực nhanh nhờ giảm thiểu thao tác cấp phát vùng nhớ mới.
 * - Nhược điểm:
 *   + Logic ghi đè trực tiếp lên mảng đầu vào và điều khiển hai con trỏ (`top` và `j`) đòi hỏi lập trình viên phải hiểu rõ cơ chế hoạt động của in-place stack.
 */

class Solution {
    public int minLength(String s) {
        if (s.length() < 1)
            return -1;

        char[] charS = s.toCharArray();
        char[] stack = new char[s.length()];
        int top = -1;

        for (char c : charS) {
            if (top >= 0 && c == 'B' && stack[top] == 'A') {
                top--;
            } else if (top >= 0 && c == 'D' && stack[top] == 'C') {
                top--;
            } else {
                top++;
                stack[top] = c;
            }
        }
        return top + 1;
    }
}

//// cách 2
class Solution2 {
    public int minLength(String s) {
        if (s == null || s.length() == 0)
            return 0;

        char[] stack = s.toCharArray();
        int top = -1;
        
        for (int j = 0; j < stack.length; j++) {
            if (top >= 0 && ((stack[j] == 'B' && stack[top] == 'A') || 
                (stack[j] == 'D' && stack[top] == 'C'))) {
                top--; 
            } else {
                top++; 
                stack[top] = stack[j]; 
            }
        }
        
        return top + 1;
    }
}