/**
 * ĐỀ BÀI: upside_down (Số đối xứng khi quay ngược 180 độ)
 * 
 * Một số nguyên (biểu diễn dưới dạng chuỗi S) được gọi là số "upside down" (Strobogrammatic) 
 * nếu khi ta xoay ngược nó 180 độ, giá trị hiển thị của nó vẫn giữ nguyên không đổi.
 * Hãy kiểm tra xem S có phải là số "upside down" hợp lệ hay không.
 * 
 * Quy tắc xoay chữ số:
 * - '0' -> '0'
 * - '1' -> '1'
 * - '8' -> '8'
 * - '6' -> '9'
 * - '9' -> '6'
 * Các số khác như '2', '3', '4', '5', '7' không thể xoay ngược để tạo thành số hợp lệ.
 * 
 * PHÂN TÍCH LUỒNG GIẢI (FLOW):
 * - Sử dụng kỹ thuật Hai con trỏ (Two Pointers) để kiểm tra đối xứng từ ngoài vào trong.
 * - Con trỏ left = 0, right = S.length() - 1.
 * - Lặp kiểm tra cặp (S.charAt(left), S.charAt(right)) xem có phải là các cặp đối xứng xoay hợp lệ không.
 * - Nếu phát hiện bất kỳ cặp nào không đúng quy tắc xoay, trả về false lập tức.
 * - Nếu duyệt hết mà không lỗi, trả về true.
 * 
 * ĐỘ PHỨC TẠP:
 * - Thời gian: O(N) - duyệt qua chuỗi tối đa N / 2 lần.
 * - Không gian: O(1) - không sử dụng bộ nhớ phụ.
 */
public class upside_down {
    public boolean solution(String S) {
        if (S == null || S.length() == 0) {
            return false;
        }

        int left = 0;
        int right = S.length() - 1;

        while (left <= right) {
            char cL = S.charAt(left);
            char cR = S.charAt(right);

            // Kiểm tra các cặp đối xứng xoay hợp lệ
            if (cL == '0' && cR == '0') {
                // Hợp lệ
            } else if (cL == '1' && cR == '1') {
                // Hợp lệ
            } else if (cL == '8' && cR == '8') {
                // Hợp lệ
            } else if (cL == '6' && cR == '9') {
                // Hợp lệ
            } else if (cL == '9' && cR == '6') {
                // Hợp lệ
            } else {
                // Có ký tự không hợp lệ hoặc không tạo thành cặp xoay đối xứng
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Hàm Main để test trực tiếp
    public static void main(String[] args) {
        upside_down solver = new upside_down();
        System.out.println("69 -> " + solver.solution("69"));   // Output: true
        System.out.println("88 -> " + solver.solution("88"));   // Output: true
        System.out.println("66 -> " + solver.solution("66"));   // Output: false (thành 99)
        System.out.println("962 -> " + solver.solution("962")); // Output: false (chứa chữ số 2)
    }
}
