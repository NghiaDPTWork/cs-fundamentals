/**
 * [LeetCode 205] Isomorphic Strings
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng mảng ánh xạ vị trí xuất hiện (Character Last Index Map - int[256])
 * - Ý tưởng: Dùng hai mảng số nguyên kích thước 256 (cho tập ký tự ASCII mở rộng) để lưu trữ chỉ số xuất hiện gần nhất của từng ký tự cộng thêm 1.
 *            Khi duyệt qua hai chuỗi cùng lúc, ta so sánh vị trí xuất hiện trước đó của ký tự s.charAt(i) và t.charAt(i).
 *            Nếu chúng có vị trí xuất hiện trước đó khác nhau, hai chuỗi không đồng cấu. 
 *            Ngược lại, ta cập nhật vị trí xuất hiện mới của cả hai ký tự bằng i + 1.
 * - Ưu điểm:
 *   + Tối ưu tuyệt đối về tốc độ và bộ nhớ: Độ phức tạp thời gian là O(N) và độ phức tạp không gian là O(1) do kích thước mảng cố định 256.
 *   + Mã nguồn ngắn gọn và trực quan.
 * - Nhược điểm:
 *   + Kém linh hoạt: Chỉ hỗ trợ tốt tập ký tự ASCII (256 ký tự). Nếu đầu vào chứa các ký tự Unicode nằm ngoài bảng ASCII,
 *     chương trình sẽ bị lỗi tràn chỉ số mảng (ArrayIndexOutOfBoundsException).
 * 
 * CÁCH 2: Sử dụng 2 HashMap ánh xạ hai chiều (Two HashMaps)
 * - Ý tưởng: Dùng một HashMap mapST ánh xạ ký tự từ chuỗi s sang t, và một HashMap mapTS ánh xạ ngược từ t sang s.
 *            Duyệt qua từng ký tự:
 *            + Nếu ký tự s.charAt(i) đã được ánh xạ sang t, kiểm tra xem nó có khớp với t.charAt(i) không.
 *            + Nếu ký tự t.charAt(i) đã được ánh xạ sang s, kiểm tra xem nó có khớp với s.charAt(i) không.
 *            Nếu bất kỳ điều kiện nào không thỏa mãn, trả về false. Nếu chưa ánh xạ, ghi nhận ánh xạ mới vào cả 2 map.
 * - Ưu điểm:
 *   + Độ linh hoạt cực kỳ cao: Xử lý hoàn hảo mọi tập ký tự Unicode phức tạp mà không bị giới hạn bởi kích thước mảng tĩnh.
 * - Nhược điểm:
 *   + Chi phí tài nguyên lớn hơn: Độ phức tạp không gian là O(U) với U là số ký tự duy nhất trong chuỗi.
 *   + Tốc độ chạy chậm hơn Cách 1: Do phải gánh chịu chi phí xử lý băm (hashing), autoboxing/unboxing của wrapper classes.
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length())    return false;

        int[] charS = new  int[256];
        int[] charT = new int[256];
        char characS = ' ';
        char characT = ' ';
        int len = s.length();

        for(int i = 0; i <= len - 1; i++){
            characS = s.charAt(i);
            characT = t.charAt(i);

            if(charS[characS] != charT[characT]){
                return false;
            }

            // Tránh trùng vs idx = 0
            charS[characS] = i + 1;
            charT[characT] = i + 1;
        }

        return true;
    }
}

//// cách 2
class Solution2 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Kiểm tra ánh xạ từ S sang T
            if (mapST.containsKey(charS)) {
                if (mapST.get(charS) != charT) return false;
            } else {
                mapST.put(charS, charT);
            }

            // Kiểm tra ánh xạ ngược từ T sang S
            if (mapTS.containsKey(charT)) {
                if (mapTS.get(charT) != charS) return false;
            } else {
                mapTS.put(charT, charS);
            }
        }

        return true;
    }
}