/**
 * Tìm độ dài chuỗi con dài nhất không chứa ký tự lặp lại (Longest Substring
 * Without Repeating Characters)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Chuỗi con động mô phỏng Cửa sổ trượt (Dynamic String
 * Simulation)
 * - Ý tưởng: Sử dụng một chuỗi `expect` làm cửa sổ để lưu các ký tự hiện tại
 * không trùng lặp.
 * Với mỗi ký tự mới, tìm vị trí xuất hiện của nó trong `expect` bằng `indexOf`.
 * Nếu đã tồn tại (ở vị trí `idx`), cập nhật độ dài chuỗi lớn nhất nếu cần,
 * sau đó thu hẹp cửa sổ bằng cách lấy chuỗi con từ `idx + 1` đến hết.
 * Sau đó thêm ký tự mới vào cửa sổ.
 * - Ưu điểm:
 * + Trực quan, dễ hiểu đối với người mới bắt đầu vì tận dụng trực tiếp các thao
 * tác xử lý chuỗi cơ bản.
 * - Nhược điểm:
 * + Hiệu năng kém: Việc cộng chuỗi (`concat`) và cắt chuỗi (`substring`) liên
 * tục tạo ra nhiều đối tượng chuỗi mới trong Java, làm tăng chi phí bộ nhớ và
 * Garbage Collection.
 * + Độ phức tạp thời gian trong trường hợp xấu nhất là O(N^2) do các hàm
 * `indexOf` và `substring` có độ phức tạp tuyến tính O(K) (K là độ dài chuỗi
 * con).
 * 
 * CÁCH 2: Cửa sổ trượt tối ưu dùng Mảng lưu vị trí xuất hiện gần nhất
 * (Optimized Sliding Window)
 * - Ý tưởng: Sử dụng một mảng số nguyên `charIndex` kích thước 128 (phù hợp với
 * các ký tự ASCII cơ bản) để lưu chỉ số xuất hiện tiếp theo của từng ký tự.
 * Con trỏ `right` đóng vai trò mở rộng cửa sổ. Nếu gặp ký tự đã xuất hiện, con
 * trỏ `left` sẽ nhảy thẳng đến chỉ số sau vị trí cũ của ký tự đó (`left =
 * Math.max(left, charIndex[c])`).
 * Sau đó, cập nhật vị trí mới của ký tự này và tính toán độ dài chuỗi con dài
 * nhất.
 * - Ưu điểm:
 * + Tối ưu thời gian tuyệt đối: Đạt O(N) vì chỉ cần một vòng lặp duyệt qua
 * chuỗi đúng 1 lần, các thao tác truy xuất mảng mất O(1).
 * + Tiết kiệm bộ nhớ: Đạt O(1) space do kích thước mảng lưu vị trí là cố định
 * (128 phần tử).
 * - Nhược điểm:
 * + Khó hiểu hơn với người mới học do cơ chế "nhảy bước" của con trỏ `left`
 * bằng mảng số nguyên.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 0)
            return 0;

        char[] charS = s.toCharArray();
        String result = "";
        String expect = "";
        int len = s.length();
        int idx = 0;
        char c = ' ';

        for (int i = 0; i <= len - 1; i++) {
            idx = expect.indexOf(charS[i]);
            c = s.charAt(i);

            if (idx != -1) {
                if (expect.length() > result.length()) {
                    result = expect;
                }
                expect = expect.substring(idx + 1);
            }
            expect = expect.concat(String.valueOf(c));
        }

        if (expect.length() > result.length()) {
            result = expect;
        }

        return result.length();
    }
}

//// cách 2
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int[] charIndex = new int[128];
        int len = s.length();
        int maxLength = 0;
        int left = 0;
        char currentChar = ' ';

        for (int right = 0; right <= len - 1; right++) {
            currentChar = s.charAt(right);

            if (charIndex[currentChar] > 0 && charIndex[currentChar] >= left) {
                left = charIndex[currentChar];
            }

            charIndex[currentChar] = right + 1;

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}