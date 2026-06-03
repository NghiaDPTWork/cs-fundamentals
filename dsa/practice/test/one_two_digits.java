import java.util.*;

/**
 * ĐỀ BÀI MỚI: one_two_digits (Tìm số lớn nhất từ chuỗi chỉ chứa '1' và '2')
 * 
 * Cho một chuỗi ký tự S chỉ chứa các ký tự '1' và '2' ngẫu nhiên.
 * Hãy tìm chuỗi con liên tục (substring) của S sao cho số lần xuất hiện của 
 * mỗi chữ số ('1' và '2') trong chuỗi con đó đều là số CHẴN, và chuỗi con này 
 * biểu diễn số có giá trị LỚN NHẤT có thể.
 * 
 * PHÂN TÍCH LUỒNG GIẢI (FLOW):
 * 1. Vì chuỗi con chỉ chứa các chữ số dương ('1' và '2'), một số có nhiều chữ số hơn 
 *    (độ dài lớn hơn) sẽ luôn luôn lớn hơn số có ít chữ số hơn. Do đó, mục tiêu đầu tiên 
 *    là tìm độ dài lớn nhất (maxLen) của chuỗi con thỏa mãn điều kiện tần suất chẵn.
 * 2. Để tìm maxLen trong O(N):
 *    - Ta theo dõi tính chẵn lẻ (parity) của số lượng '1' và '2' xuất hiện từ đầu chuỗi.
 *    - Có 4 trạng thái parity có thể biểu diễn bằng 2 bits (bitmask):
 *      + 00 (0): Cả '1' và '2' đều chẵn.
 *      + 01 (1): '1' lẻ, '2' chẵn.
 *      + 10 (2): '1' chẵn, '2' lẻ.
 *      + 11 (3): Cả '1' và '2' đều lẻ.
 *    - Một chuỗi con S[i...j] có số lượng các chữ số là chẵn nếu và chỉ nếu trạng thái 
 *      parity tại index j và index i-1 là trùng nhau.
 *    - Ta lưu lại vị trí xuất hiện đầu tiên (first) và cuối cùng (last) của mỗi trạng thái.
 *    - Độ dài lớn nhất của trạng thái s là `last[s] - first[s]`.
 * 3. Sau khi tìm được maxLen:
 *    - Nếu maxLen == 0, nghĩa là không có chuỗi con nào thỏa mãn (trả về chuỗi rỗng "").
 *    - Nếu có nhiều chuỗi con cùng đạt độ dài maxLen, ta cần chọn chuỗi con biểu diễn 
 *      số lớn nhất (tức là so sánh theo thứ tự từ điển lớn nhất - lexicographically largest).
 *    - Ta thu thập tất cả các chỉ số bắt đầu (start) thỏa mãn điều kiện và so sánh chúng 
 *      để tìm ra chuỗi lớn nhất.
 * 
 * ĐỘ PHỨC TẠP:
 * - Thời gian: O(N) - duyệt qua chuỗi tối ưu.
 * - Không gian: O(N) (lưu trạng thái prefix để so sánh nhanh).
 */
public class one_two_digits {
    public String solution(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        int n = S.length();
        int[] prefixState = new int[n + 1];
        int[] first = new int[4];
        int[] last = new int[4];
        Arrays.fill(first, -2);
        Arrays.fill(last, -2);

        // Trạng thái ban đầu trước khi xét ký tự nào (độ dài 0) là 0
        prefixState[0] = 0;
        first[0] = 0;
        last[0] = 0;

        int currentState = 0;
        for (int i = 1; i <= n; i++) {
            char c = S.charAt(i - 1);
            if (c == '1') {
                currentState ^= 1; // Đảo bit thứ 0
            } else if (c == '2') {
                currentState ^= 2; // Đảo bit thứ 1
            }
            prefixState[i] = currentState;

            if (first[currentState] == -2) {
                first[currentState] = i;
            }
            last[currentState] = i;
        }

        // Tìm độ dài lớn nhất
        int maxLen = 0;
        for (int s = 0; s < 4; s++) {
            if (first[s] != -2) {
                maxLen = Math.max(maxLen, last[s] - first[s]);
            }
        }

        if (maxLen == 0) {
            return "";
        }

        // Tìm tất cả các vị trí bắt đầu thỏa mãn độ dài maxLen
        List<Integer> candidates = new ArrayList<>();
        for (int start = 0; start <= n - maxLen; start++) {
            if (prefixState[start] == prefixState[start + maxLen]) {
                candidates.add(start);
            }
        }

        // Tìm chuỗi con lớn nhất về mặt giá trị số (lexicographically largest)
        String bestSub = "";
        for (int start : candidates) {
            String sub = S.substring(start, start + maxLen);
            if (sub.compareTo(bestSub) > 0) {
                bestSub = sub;
            }
        }

        return bestSub;
    }

    // Nếu đề bài yêu cầu trả về ĐỘ DÀI lớn nhất (kiểu int) thay vì chuỗi số:
    public int solutionLength(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        int n = S.length();
        int[] first = new int[4];
        int[] last = new int[4];
        Arrays.fill(first, -2);
        Arrays.fill(last, -2);
        first[0] = 0;
        last[0] = 0;

        int currentState = 0;
        for (int i = 1; i <= n; i++) {
            char c = S.charAt(i - 1);
            if (c == '1') currentState ^= 1;
            else if (c == '2') currentState ^= 2;

            if (first[currentState] == -2) first[currentState] = i;
            last[currentState] = i;
        }

        int maxLen = 0;
        for (int s = 0; s < 4; s++) {
            if (first[s] != -2) {
                maxLen = Math.max(maxLen, last[s] - first[s]);
            }
        }
        return maxLen;
    }

    // Hàm Main để chạy thử nghiệm trực tiếp
    public static void main(String[] args) {
        one_two_digits solver = new one_two_digits();
        
        // Test case 1: "12122" 
        // Các chuỗi con chẵn: "1212" (độ dài 4), "22" (độ dài 2)
        // Lớn nhất là "1212"
        System.out.println("S = \"12122\" -> Số lớn nhất: " + solver.solution("12122")); 
        
        // Test case 2: "221122" -> chuỗi con "221122" (độ dài 6)
        System.out.println("S = \"221122\" -> Số lớn nhất: " + solver.solution("221122"));
        
        // Test case 3: "21121122" -> "21121122"
        System.out.println("S = \"21121122\" -> Số lớn nhất: " + solver.solution("21121122"));
    }
}
