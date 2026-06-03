import java.util.*;

/**
 * ĐỀ BÀI: one_two_digits (Tìm số lớn nhất từ chuỗi chỉ chứa '1' và '2')
 * 
 * Cho một chuỗi ký tự S chỉ chứa các ký tự '1' và '2' ngẫu nhiên.
 * Hãy tìm chuỗi con liên tục (substring) của S sao cho số lần xuất hiện của 
 * mỗi chữ số ('1' và '2') trong chuỗi con đó đều là số CHẴN, và chuỗi con này 
 * biểu diễn số có giá trị LỚN NHẤT có thể.
 * 
 * PHÂN TÍCH LUỒNG GIẢI (FLOW):
 * 1. Ta theo dõi trạng thái chẵn lẻ (parity) của '1' và '2' từ đầu chuỗi bằng 2 bits (4 trạng thái: 0, 1, 2, 3).
 * 2. Lưu lại vị trí đầu tiên (first) và cuối cùng (last) của mỗi trạng thái.
 * 3. Chuỗi con dài nhất của mỗi trạng thái s chắc chắn bắt đầu từ first[s] và kết thúc tại last[s].
 * 4. Tìm độ dài lớn nhất maxLen = max(last[s] - first[s]) trên cả 4 trạng thái.
 * 5. Duyệt qua 4 trạng thái, nếu trạng thái nào đạt độ dài maxLen, ta lấy chuỗi con tương ứng 
 *    và so sánh bằng compareTo() để tìm chuỗi lớn nhất.
 * 
 * ĐỘ PHỨC TẠP:
 * - Thời gian: O(N) - chỉ duyệt qua chuỗi đúng 1 lần.
 * - Không gian: O(1) - chỉ sử dụng mảng cố định kích thước 4.
 */
public class one_two_digits {
    public String solution(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        int n = S.length();
        int[] first = new int[4];
        int[] last = new int[4];
        Arrays.fill(first, -2);
        Arrays.fill(last, -2);

        // Trạng thái ban đầu (độ dài 0) là 0
        first[0] = 0;
        last[0] = 0;

        int currentState = 0;
        for (int i = 1; i <= n; i++) {
            char c = S.charAt(i - 1);
            if (c == '1') {
                currentState ^= 1;
            } else if (c == '2') {
                currentState ^= 2;
            }
            if (first[currentState] == -2) {
                first[currentState] = i;
            }
            last[currentState] = i;
        }

        // Tìm độ dài lớn nhất (maxLen)
        int maxLen = 0;
        for (int s = 0; s < 4; s++) {
            if (first[s] != -2) {
                maxLen = Math.max(maxLen, last[s] - first[s]);
            }
        }

        if (maxLen == 0) {
            return "";
        }

        // So sánh tối đa 4 ứng viên để tìm chuỗi có giá trị lớn nhất
        String bestSub = "";
        for (int s = 0; s < 4; s++) {
            if (first[s] != -2 && (last[s] - first[s] == maxLen)) {
                String sub = S.substring(first[s], last[s]);
                if (sub.compareTo(bestSub) > 0) {
                    bestSub = sub;
                }
            }
        }

        return bestSub;
    }

    // Hàm Main để chạy thử nghiệm trực tiếp
    public static void main(String[] args) {
        one_two_digits solver = new one_two_digits();
        
        System.out.println("S = \"12122\" -> Số lớn nhất: " + solver.solution("12122"));    // Output: "1212"
        System.out.println("S = \"221122\" -> Số lớn nhất: " + solver.solution("221122"));  // Output: "221122"
        System.out.println("S = \"21121122\" -> Số lớn nhất: " + solver.solution("21121122")); // Output: "21121122"
    }
}
