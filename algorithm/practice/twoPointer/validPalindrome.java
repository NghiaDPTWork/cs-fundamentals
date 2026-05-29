/**
 * Hai con trỏ (Two Pointers) cho bài toán Valid Palindrome.
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Hai con trỏ (Two Pointers)
 * - Ý tưởng: Sử dụng hai con trỏ `left` bắt đầu từ đầu chuỗi và `right` từ cuối chuỗi.
 *            Tịnh tiến hai con trỏ lại gần nhau, bỏ qua các ký tự không phải chữ cái hoặc chữ số.
 *            So sánh hai ký tự (sau khi chuyển về chữ thường). Nếu khác nhau, trả về false.
 * - Ưu điểm:
 *   + Tối ưu bộ nhớ cực tốt: Đạt O(1) space do kiểm tra trực tiếp trên chuỗi gốc, không cần tạo chuỗi mới.
 *   + Tối ưu thời gian: Chỉ cần một vòng lặp duyệt qua chuỗi tối đa một lần.
 * - Nhược điểm:
 *   + Cài đặt phức tạp hơn, cần kiểm tra cẩn thận điều kiện biên (`left < right`) trong các vòng lặp phụ.
 * 
 * CÁCH 2: Chuỗi đảo ngược (String Reversal)
 * - Ý tưởng: Tạo một chuỗi mới chỉ chứa các ký tự hợp lệ (chữ cái và chữ số) từ chuỗi ban đầu, chuyển tất cả về chữ thường.
 *            Sau đó sử dụng `StringBuilder` để đảo ngược chuỗi đã làm sạch và so sánh chuỗi đảo ngược với chuỗi làm sạch ban đầu.
 * - Ưu điểm:
 *   + Code cực kỳ ngắn gọn, dễ đọc, dễ hiểu, tận dụng các hàm có sẵn của ngôn ngữ.
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Cấp phát thêm bộ nhớ cho chuỗi mới và chuỗi đảo ngược, độ phức tạp không gian là O(N).
 *   + Thời gian chạy chậm hơn do phải thực hiện nhiều lượt duyệt: 1 lượt làm sạch, 1 lượt đảo ngược, và 1 lượt so sánh.
 */
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Bỏ qua ký tự không phải chữ cái hoặc chữ số từ bên trái
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Bỏ qua ký tự không phải chữ cái hoặc chữ số từ bên phải
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // So sánh không phân biệt chữ hoa / chữ thường
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

//// cách 2
class Solution2 {
    public boolean isPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }

        String original = cleaned.toString();
        String reversed = cleaned.reverse().toString();
        return original.equals(reversed);
    }
}


