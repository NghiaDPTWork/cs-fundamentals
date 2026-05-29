/**
 * [LeetCode 242] Valid Anagram
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng mảng tần suất (Frequency Array - int[26])
 * - Ý tưởng: Vì chuỗi đầu vào chỉ gồm các chữ cái thường tiếng Anh ('a' đến 'z'), ta dùng mảng kích thước 26.
 *            Duyệt qua chuỗi s để cộng tần suất, duyệt qua chuỗi t để trừ tần suất.
 *            Cuối cùng, nếu tất cả các ô trong mảng đều bằng 0, hai chuỗi là Anagram.
 * - Ưu điểm:
 *   + Cực kỳ tối ưu về bộ nhớ: Độ phức tạp không gian là O(1) do kích thước mảng luôn cố định là 26.
 *   + Thời gian chạy siêu nhanh: Thao tác truy cập mảng bằng chỉ số mất O(1) trực tiếp, không có chi phí của bảng băm HashMap.
 * - Nhược điểm:
 *   + Kém linh hoạt: Chỉ áp dụng được cho tập ký tự cố định và nhỏ (ví dụ chỉ có 26 chữ cái thường). 
 *     Nếu chuỗi chứa ký tự Unicode hoặc ký tự đặc biệt khác, cách này không áp dụng trực tiếp được.
 * 
 * CÁCH 2: Sử dụng HashMap (Map<Character, Integer>)
 * - Ý tưởng: Dùng HashMap để đếm tần suất xuất hiện các ký tự trong chuỗi s, 
 *            sau đó kiểm tra và giảm tần suất tương ứng khi duyệt qua chuỗi t.
 * - Ưu điểm:
 *   + Độ linh hoạt cực kỳ cao: Làm việc được với bất kỳ tập ký tự nào (Unicode, ASCII, ký tự đặc biệt, hoa thường...).
 * - Nhược điểm:
 *   + Tốn bộ nhớ hơn: Độ phức tạp không gian là O(U) với U là số lượng ký tự duy nhất trong chuỗi.
 *   + Chạy chậm hơn Cách 1: Do phải tính toán mã băm (hashing), xử lý va chạm (collision) và chi phí autoboxing/unboxing của wrapper class (Character, Integer).
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {
        int[] frequent = new int[26];

        if(s.length() != t.length())    return false;

        char[] charS = s.trim().toCharArray();
        char[] charT = t.trim().toCharArray();

        for(int i = 0; i <= charT.length - 1; i++){
            frequent[charS[i] - 'a']++;
            frequent[charT[i] - 'a']--;
        } 

        for(int i = 0; i <= frequent.length - 1; i++){
            if(frequent[i] != 0){
                return false;
            }
        }

        return true;
    }
}

//// cách 2
class Solution2 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())    return false;

        Map<Character, Integer> frequencyMap = new HashMap<>();
        char character = ' ';

        for(int i = 0; i <= s.length() - 1; i++){
            character = s.charAt(i);
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        } 

        for(int i = 0; i <= t.length() - 1; i++){
            character = t.charAt(i);
            
            if(!frequencyMap.containsKey(character) || frequencyMap.get(character) == 0){
                return false;
            }
        }
        return true;
    }
}