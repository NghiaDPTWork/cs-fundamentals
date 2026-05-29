/**
 * [LeetCode 389] Find the Difference
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng HashMap (Map<Character, Integer>)
 * - Ý tưởng: Đếm tần suất của từng ký tự trong chuỗi s bằng HashMap. 
 *            Duyệt qua chuỗi t, với mỗi ký tự, kiểm tra nếu nó không có trong HashMap hoặc tần suất đã bằng 0 
 *            thì đó chính là ký tự được thêm vào. Ngược lại, giảm tần suất ký tự đó đi 1.
 * - Ưu điểm:
 *   + Độ linh hoạt cực kỳ cao: Làm việc được với bất kỳ tập ký tự nào (Unicode, ASCII, ký tự đặc biệt, hoa thường...).
 * - Nhược điểm:
 *   + Tốn bộ nhớ hơn: Độ phức tạp không gian là O(U) với U là số lượng ký tự duy nhất trong chuỗi s.
 *   + Chạy chậm hơn Cách 2: Chi phí xử lý của bảng băm (hashing, collision, autoboxing/unboxing) lớn hơn so với tính toán số học đơn giản.
 * 
 * CÁCH 2: Sử dụng tổng mã ASCII (ASCII / Character Sum Difference)
 * - Ý tưởng: Vì chuỗi t chỉ nhiều hơn chuỗi s đúng 1 ký tự và các ký tự còn lại giống hệt nhau,
 *            tổng giá trị mã ASCII của toàn bộ ký tự trong chuỗi t trừ đi tổng giá trị mã ASCII của toàn bộ ký tự 
 *            trong chuỗi s chính bằng mã ASCII của ký tự được thêm vào.
 * - Ưu điểm:
 *   + Cực kỳ tối ưu về bộ nhớ: Độ phức tạp không gian là O(1) do không cần cấu trúc dữ liệu phụ nào.
 *   + Tốc độ chạy siêu nhanh: Chỉ sử dụng các phép toán số học cộng trừ số nguyên cơ bản, độ phức tạp thời gian tối ưu O(N).
 * - Nhược điểm:
 *   + Kém linh hoạt: Chỉ giải quyết được bài toán cụ thể khi đề bài yêu cầu tìm đúng 1 ký tự khác biệt duy nhất được thêm vào. 
 *     Nếu bài toán đổi thành tìm nhiều hơn 1 ký tự, hoặc thứ tự thay đổi phức tạp hơn, cách này không áp dụng được.
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        char character = ' ';

        for(int i = 0; i <= s.length() - 1; i++){
            character = s.charAt(i);
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }

        for(int i = 0; i <= t.length() - 1; i++){
            character = t.charAt(i);
            if(!frequencyMap.containsKey(character) || frequencyMap.get(character) == 0){
                return character;
            }
            frequencyMap.put(character, frequencyMap.get(character) - 1);
        }

        return ' ';
    }
}

//// cách 2
class Solution2 {
    public char findTheDifference(String s, String t) {
        int sumCharacter = 0;


        for(int i = 0; i <= t.length() - 1; i++){
            sumCharacter += t.charAt(i);
        }

        for(int i = 0; i <= s.length() - 1; i++){
            sumCharacter -= s.charAt(i);
        }

        return (char) sumCharacter;
    }
}