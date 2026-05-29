/**
 * Tìm số thao tác tối thiểu để quay lại thư mục gốc (Crawler Log Folder)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng cấu trúc dữ liệu Stack (java.util.Stack)
 * - Ý tưởng: Sử dụng một Stack để lưu trữ đường dẫn các thư mục hiện tại. 
 *            Duyệt qua từng thao tác trong `logs`:
 *            + Gặp "../": Nếu Stack không rỗng, pop thư mục trên cùng ra (quay lại thư mục cha).
 *            + Gặp "./": Giữ nguyên thư mục hiện tại (không làm gì cả).
 *            + Gặp "x/": Push thư mục "x/" vào Stack (di chuyển vào thư mục con).
 *            Độ dài cuối cùng của Stack chính là số bước tối thiểu để quay về thư mục gốc.
 * - Ưu điểm:
 *   + Mô phỏng chính xác và trực quan cơ chế hoạt động của thư mục và đường dẫn.
 *   + Dễ hiểu, dễ bảo trì, ít xảy ra sai sót logic.
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Độ phức tạp không gian là O(N) trong trường hợp xấu nhất (nhiều thao tác chuyển vào thư mục con).
 *   + Tốc độ chậm hơn: Do overhead từ việc cấp phát vùng nhớ cho Stack và các đối tượng String.
 * 
 * CÁCH 2: Sử dụng biến đếm độ sâu thư mục (Counter Simulation)
 * - Ý tưởng: Thực chất ta chỉ cần biết khoảng cách (độ sâu) từ thư mục hiện tại đến thư mục gốc.
 *            Thay vì dùng Stack để lưu tên thư mục, ta dùng biến đếm `depth` bắt đầu từ 0.
 *            Duyệt qua các thao tác trong `logs`:
 *            + Gặp "../": Nếu `depth > 0`, giảm `depth` đi 1.
 *            + Gặp "./": Giữ nguyên.
 *            + Gặp "x/": Tăng `depth` thêm 1.
 *            Kết quả trả về là giá trị của `depth`.
 * - Ưu điểm:
 *   + Tối ưu hóa bộ nhớ vượt trội: Độ phức tạp không gian là O(1) do chỉ dùng một biến đếm nguyên thủy.
 *   + Tốc độ thực thi cực nhanh nhờ giảm thiểu tối đa các thao tác cấp phát vùng nhớ.
 * - Nhược điểm:
 *   + Không lưu trữ được lịch sử cụ thể các thư mục đã đi qua (tuy nhiên bài toán này không yêu cầu).
 */

import java.util.Stack;

class Solution {
    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String log : logs) {
            if (log.equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!log.equals("./")) {
                stack.push(log);
            }
        }
        return stack.size();
    }
}

//// cách 2
class Solution2 {
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                if (depth > 0) {
                    depth--;
                }
            } else if (!log.equals("./")) {
                depth++;
            }
        }
        return depth;
    }
}