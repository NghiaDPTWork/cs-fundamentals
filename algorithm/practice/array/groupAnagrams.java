/**
 * [LeetCode 49] Group Anagrams
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Sắp xếp ký tự (Sorting individual strings as Map keys)
 * - Ý tưởng: Duyệt qua từng chuỗi trong mảng strs. Với mỗi chuỗi, chuyển thành mảng ký tự char[],
 *            sắp xếp mảng tăng dần (ví dụ: "eat", "tea", "ate" đều sẽ có dạng sắp xếp chung là "aet") và chuyển lại thành chuỗi.
 *            Dùng chuỗi đã sắp xếp này làm Key trong HashMap để gom nhóm các chuỗi gốc có cùng tập ký tự.
 * - Ưu điểm:
 *   + Trực quan, dễ hiểu, lập luận đơn giản và dễ dàng cài đặt.
 *   + Làm việc tốt với mọi tập ký tự (Unicode, ASCII, hoa thường, ký tự đặc biệt) mà không phụ thuộc vào kích thước bảng chữ cái.
 * - Nhược điểm:
 *   + Hiệu năng trung bình: Với mỗi chuỗi có độ dài K, thuật toán sắp xếp tốn O(K log K) thời gian.
 *     Tổng độ phức tạp thời gian là O(N * K log K), sẽ chạy chậm khi độ dài các chuỗi K rất lớn.
 * 
 * CÁCH 2: Sử dụng Mảng tần suất làm Key (Character Count representation as Map keys)
 * - Ý tưởng: Thay vì sắp xếp chuỗi (mất O(K log K)), ta đếm tần suất xuất hiện của 26 chữ cái tiếng Anh thường trong chuỗi (chỉ mất O(K)).
 *            Sau đó ta biến mảng tần suất này thành một chuỗi đại diện (ví dụ dạng: "#1#0#2#0...#0") làm Key trong HashMap để gom nhóm.
 * - Ưu điểm:
 *   + Tối ưu thời gian tuyệt đối: Độ phức tạp thời gian giảm xuống còn O(N * K) do loại bỏ hoàn toàn thao tác sắp xếp ký tự.
 *     Hiệu quả vượt trội so với Cách 1 khi các chuỗi đầu vào có độ dài cực kỳ lớn.
 * - Nhược điểm:
 *   + Cài đặt phức tạp hơn một chút do khâu chuyển đổi mảng tần suất thành chuỗi Key đại diện.
 *   + Kém linh hoạt: Bị ràng buộc với bảng chữ cái cố định tiếng Anh thường (26 chữ cái).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> myMap = new HashMap<>();

        String sortedStr = "";

        for (String s : strs) {
            char[] charS = s.toCharArray();
            Arrays.sort(charS);
            sortedStr = new String(charS);

            if (!myMap.containsKey(sortedStr)) {
                myMap.put(sortedStr, new ArrayList<>());
            }
            
            myMap.get(sortedStr).add(s);
        }

        return new ArrayList<>(myMap.values());
    }
}

//// cách 2
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> myMap = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            // Tạo khóa đại diện từ mảng tần suất (ví dụ: #1#0#2...)
            StringBuilder sb = new StringBuilder();
            for (int freq : count) {
                sb.append('#');
                sb.append(freq);
            }
            String key = sb.toString();

            if (!myMap.containsKey(key)) {
                myMap.put(key, new ArrayList<>());
            }
            myMap.get(key).add(s);
        }

        return new ArrayList<>(myMap.values());
    }
}