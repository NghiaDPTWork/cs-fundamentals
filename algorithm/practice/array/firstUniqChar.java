/**
 * [LeetCode 387] First Unique Character in a String
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 3 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng HashMap (Map<Character, Integer>)
 * - Ý tưởng: Dùng HashMap để đếm tần suất xuất hiện của từng ký tự trong chuỗi s.
 *            Duyệt lại chuỗi s lần hai, ký tự đầu tiên có tần suất đếm bằng 1 chính là ký tự cần tìm, ta trả về chỉ số của nó.
 * - Ưu điểm:
 *   + Độ linh hoạt cực kỳ cao: Làm việc được với bất kỳ tập ký tự nào (Unicode, ASCII, ký tự đặc biệt, hoa thường...).
 * - Nhược điểm:
 *   + Tốn bộ nhớ hơn: Độ phức tạp không gian là O(U) với U là số lượng ký tự duy nhất trong chuỗi.
 *   + Chạy chậm nhất: Do chi phí liên quan đến tính toán mã băm (hashing), giải quyết va chạm (collision) và boxing/unboxing của wrapper classes.
 * 
 * CÁCH 2: Sử dụng mảng tần suất (Frequency Array - int[26])
 * - Ý tưởng: Vì chuỗi đầu vào chỉ chứa các chữ cái thường tiếng Anh ('a' đến 'z'), ta dùng mảng số nguyên kích thước 26 để đếm tần suất.
 *            Duyệt qua chuỗi lần đầu để đếm, duyệt lần hai để tìm ký tự đầu tiên có tần suất bằng 1.
 * - Ưu điểm:
 *   + Cực kỳ tối ưu về bộ nhớ: Độ phức tạp không gian là O(1) do kích thước mảng luôn cố định là 26.
 *   + Tốc độ chạy siêu nhanh: Việc truy cập mảng bằng chỉ số mất O(1) trực tiếp, không có chi phí của bảng băm HashMap.
 * - Nhược điểm:
 *   + Kém linh hoạt: Chỉ giới hạn hoạt động đúng với chuỗi chỉ gồm chữ cái thường tiếng Anh ('a' đến 'z').
 * 
 * CÁCH 3: Sử dụng indexOf và lastIndexOf
 * - Ý tưởng: Lặp qua 26 chữ cái tiếng Anh từ 'a' đến 'z'. Với mỗi chữ cái, dùng indexOf và lastIndexOf để tìm vị trí của nó.
 *            Nếu vị trí đầu tiên trùng vị trí cuối cùng và khác -1 thì chữ cái đó xuất hiện duy nhất 1 lần. 
 *            Ta lấy chỉ số nhỏ nhất trong số các chữ cái thỏa mãn.
 * - Ưu điểm:
 *   + Bộ nhớ tối ưu O(1) và chạy rất nhanh trên thực tế nhờ các phương thức indexOf/lastIndexOf được Java tối ưu hóa sâu dưới mã máy.
 * - Nhược điểm:
 *   + Về mặt lý thuyết, việc quét đi quét lại chuỗi 26 lần (26 * N) sẽ kém hiệu quả hơn so với chỉ 2 lần duyệt của Cách 2 khi độ dài chuỗi rất lớn.
 *   + Kém linh hoạt: Bị giới hạn vào tập ký tự phải lặp qua (26 chữ cái).
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> frenquencyMap = new HashMap<>();
        char character = ' ';  

        for(int i = 0; i <= s.length() - 1; i++){
            character = s.charAt(i);
            frenquencyMap.put(character,frenquencyMap.getOrDefault(character, 0) + 1);  
        }

        for(int i = 0; i <= s.length() - 1; i++){
            character = s.charAt(i);
            if(frenquencyMap.get(character) == 1){
                return i;
            }
        }
        return -1;
    }
}

//// cách 2
class Solution2 {
    public int firstUniqChar(String s) {
        int[] frequent = new int[26];

        for(int i = 0; i <= s.length() - 1; i++){
            frequent[s.charAt(i) - 'a']++;
        } 

        for(int i = 0; i <= s.length() - 1; i++){
            if(frequent[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }

        return -1;
    }
}

//// cách 3
class Solution3 {
    public int firstUniqChar(String s) {
        int minIndex = Integer.MAX_VALUE;

        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            
            if (first != -1 && first == s.lastIndexOf(c)) {
                minIndex = Math.min(minIndex, first);
            }
        }

        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }
}