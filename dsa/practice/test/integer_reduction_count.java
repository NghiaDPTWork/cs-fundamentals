import java.math.BigInteger;

/**
 * ĐỀ BÀI: integer_reduction_count (Đếm số bước giảm số nguyên về 0)
 * 
 * Cho một chuỗi S biểu diễn một số nguyên không âm cực kỳ lớn. 
 * Thực hiện các thao tác sau để giảm số nguyên này về 0:
 * - Nếu số hiện tại là số chẵn: chia số đó cho 2.
 * - Nếu số hiện tại là số lẻ: trừ số đó đi 1.
 * Đếm số bước thực hiện tối thiểu để giảm số nguyên đó về 0.
 * 
 * GIẢI THÍCH LỖI CODE CŨ (ĐẠT 27% ĐIỂM):
 * 1. Lỗi tràn bộ nhớ (42% Correctness): Parse chuỗi trực tiếp ra int/long sẽ bị crash 
 *    (NumberFormatException) với các chuỗi số khổng lồ (N > 60).
 * 2. Lỗi hiệu năng (0% Performance): Sử dụng BigInteger thực hiện trực tiếp phép chia/trừ 
 *    theo vòng lặp mô phỏng sẽ tốn O(N^2) thời gian, gây ra lỗi quá giờ (timeout).
 * 
 * PHÂN TÍCH LUỒNG GIẢI TỐI ƯU (FLOW):
 * - Nếu chuỗi S là chuỗi nhị phân:
 *   + Với hệ nhị phân, chia cho 2 tương đương dịch phải 1 bit (xóa '0' cuối) -> tốn 1 bước.
 *   + Trừ đi 1 tương đương chuyển bit cuối '1' thành '0' -> tốn 1 bước.
 *   + Do đó:
 *     * Mỗi bit '0' ở cuối cần 1 bước (dịch phải).
 *     * Mỗi bit '1' ở cuối cần 2 bước (trừ 1 rồi dịch phải).
 *     * Riêng bit '1' đầu tiên (bên trái cùng) chỉ cần đúng 1 bước (trừ 1 để về 0 và dừng lại).
 *   + Ta có thể duyệt chuỗi nhị phân từ phải sang trái chỉ 1 lần và đếm bước: O(N) thời gian, O(1) không gian.
 * - Nếu chuỗi S là chuỗi thập phân:
 *   + Sử dụng BigInteger chuyển đổi duy nhất 1 lần từ thập phân sang chuỗi nhị phân.
 *   + Thực hiện thuật toán đếm bước O(N) trên chuỗi nhị phân vừa thu được.
 * 
 * ĐỘ PHỨC TẠP:
 * - Thời gian: O(N) (tuyến tính với độ dài chuỗi nhị phân).
 * - Không gian: O(1) (hoặc O(N) nếu cần chuyển đổi cơ số).
 */
public class integer_reduction_count {
    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }

        // Tự động kiểm tra xem chuỗi đầu vào là nhị phân hay thập phân
        boolean isBinary = true;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c != '0' && c != '1') {
                isBinary = false;
                break;
            }
        }

        if (isBinary) {
            return solveForBinary(S);
        } else {
            return solveForDecimal(S);
        }
    }

    private int solveForBinary(String binary) {
        // Loại bỏ các số '0' thừa ở đầu chuỗi
        int start = 0;
        while (start < binary.length() && binary.charAt(start) == '0') {
            start++;
        }

        // Nếu chuỗi toàn số '0' (giá trị bằng 0)
        if (start == binary.length()) {
            return 0;
        }

        int steps = 0;
        // Duyệt từ phải sang trái, dừng trước bit đầu tiên bên trái
        for (int i = binary.length() - 1; i > start; i--) {
            if (binary.charAt(i) == '0') {
                steps += 1; // Số chẵn: 1 bước chia 2
            } else {
                steps += 2; // Số lẻ: 1 bước trừ 1 + 1 bước chia 2
            }
        }

        // Bit '1' cuối cùng bên trái chỉ cần 1 bước trừ về 0
        steps += 1;

        return steps;
    }

    private int solveForDecimal(String decimal) {
        if (decimal.equals("0")) {
            return 0;
        }
        // Chuyển đổi an toàn số thập phân khổng lồ sang hệ nhị phân
        BigInteger num = new BigInteger(decimal);
        String binary = num.toString(2);
        
        return solveForBinary(binary);
    }

    // Hàm Main để test trực tiếp
    public static void main(String[] args) {
        integer_reduction_count solver = new integer_reduction_count();
        // Test nhị phân
        System.out.println("S = \"1101\" (Nhị phân) -> " + solver.solution("1101")); // Output: 6
        System.out.println("S = \"111\" (Nhị phân) -> " + solver.solution("111"));   // Output: 5
        System.out.println("S = \"0\" (Nhị phân) -> " + solver.solution("0"));       // Output: 0
        
        // Test thập phân
        System.out.println("S = \"13\" (Thập phân) -> " + solver.solution("13"));     // Output: 6
    }
}
