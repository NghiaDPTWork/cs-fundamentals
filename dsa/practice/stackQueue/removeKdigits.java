/**
 * Loại bỏ K chữ số (Remove K Digits)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng StringBuilder để mô phỏng Stack
 * - Ý tưởng: Duyệt qua từng chữ số của chuỗi `num`.
 *            Duy trì một cấu trúc dữ liệu Stack (ở đây dùng StringBuilder) tăng dần đơn điệu.
 *            Khi chữ số hiện tại nhỏ hơn chữ số ở cuối StringBuilder và vẫn còn quyền xóa (`k > 0`),
 *            ta xóa ký tự cuối cùng của StringBuilder và giảm `k`.
 *            Nếu duyệt hết mà vẫn còn `k > 0`, tiến hành cắt bỏ `k` ký tự ở cuối.
 *            Cuối cùng loại bỏ các số 0 ở đầu.
 * - Ưu điểm:
 *   + Dễ hiểu, cú pháp gọn gàng do StringBuilder hỗ trợ sẵn các phương thức `deleteCharAt` và `substring`.
 *   + Không tốn chi phí boxing/unboxing của Stack đối tượng thông thường (`Deque<Character>`).
 * - Nhược điểm:
 *   + StringBuilder vẫn có chi phí overhead nhất định của lớp đối tượng (ví dụ: cấp phát lại mảng động internally, kiểm tra bounds check).
 * 
 * CÁCH 2: Tối ưu hóa bằng mảng ký tự nguyên thủy (Custom Char Array Stack)
 * - Ý tưởng: Sử dụng một mảng `char[] stack` có kích thước bằng chiều dài chuỗi `num`, kết hợp với một con trỏ `top` để tự quản lý ngăn xếp.
 *            Tất cả thao tác thêm, xóa, và loại bỏ số 0 được thực hiện in-place thông qua chỉ số và con trỏ `top`.
 * - Ưu điểm:
 *   + Đạt tốc độ tối đa (0ms - 1ms trên LeetCode) và tiết kiệm tài nguyên bộ nhớ tối đa.
 * - Nhược điểm:
 *   + Phải xử lý chỉ số thủ công, cần tính toán cẩn thận để tránh lỗi tràn mảng hoặc lấy sai khoảng con của chuỗi.
 */

class Solution {
    public String removeKdigits(String num, int k) {
        StringBuilder stack = new StringBuilder();

        for(char c: num.toCharArray()){

            // stack > c thì xóa stack đi 
            while(k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > c){
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(c);
        }

        // 12345 
        while (k > 0 && stack.length() > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        // Loại bỏ 0 đi 
        int startIndex = 0;
        while (startIndex < stack.length() && stack.charAt(startIndex) == '0') {
            startIndex++;
        }

        String result = stack.substring(startIndex);

        return result.isEmpty() ? "0" : result;
    }
}

//// cách 2
class Solution2 {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == k) {
            return "0";
        }
        
        char[] stack = new char[len];
        int top = -1;
        
        for (int i = 0; i < len; i++) {
            char c = num.charAt(i);
            while (k > 0 && top >= 0 && stack[top] > c) {
                top--;
                k--;
            }
            top++;
            stack[top] = c;
        }
        
        // Cắt bỏ phần dư thừa ở đuôi nếu k vẫn lớn hơn 0
        top -= k;
        
        // Loại bỏ các chữ số 0 ở đầu
        int startIndex = 0;
        while (startIndex <= top && stack[startIndex] == '0') {
            startIndex++;
        }
        
        if (startIndex > top) {
            return "0";
        }
        
        return new String(stack, startIndex, top - startIndex + 1);
    }
}