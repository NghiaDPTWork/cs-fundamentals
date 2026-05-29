/**
 * Va chạm tiểu tinh thạch (Asteroid Collision)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Stack (Deque hoặc Stack đối tượng)
 * - Ý tưởng: Duyệt qua từng tiểu tinh thạch. 
 *            Va chạm chỉ xảy ra khi tiểu tinh thạch hiện tại bay sang trái (âm) và đỉnh stack bay sang phải (dương).
 *            Ta sử dụng một cấu trúc dữ liệu Stack chuẩn để giải quyết: pop các tiểu tinh thạch nhỏ hơn bị phá hủy,
 *            xét các điều kiện kích thước bằng nhau hoặc tiểu tinh thạch hiện tại bị phá hủy.
 * - Ưu điểm:
 *   + Trực quan, dễ viết, sử dụng thư viện chuẩn của Java giúp code sạch sẽ và tránh tự quản lý chỉ số mảng.
 * - Nhược điểm:
 *   + Tốn bộ nhớ và thời gian chạy hơn do chi phí khởi tạo đối tượng wrapper (Integer) và đối tượng Stack.
 * 
 * CÁCH 2: Tối ưu hóa bằng mảng nguyên thủy (Custom Array Stack)
 * - Ý tưởng: Sử dụng một mảng số nguyên `stack` có kích thước bằng số lượng tiểu tinh thạch và biến con trỏ `top` để mô phỏng Stack.
 *            Các điều kiện va chạm và phá hủy được xử lý in-place trực tiếp trên mảng này.
 * - Ưu điểm:
 *   + Tốc độ xử lý cực nhanh và tối ưu dung lượng bộ nhớ (0ms - 1ms) nhờ giảm thiểu việc cấp phát bộ nhớ động.
 * - Nhược điểm:
 *   + Phải quản lý con trỏ `top` thủ công và cẩn thận với các điều kiện dừng vòng lặp tránh gây lỗi tràn chỉ số mảng.
 */

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int ast : asteroids) {
            boolean isAlive = true;
            // Va chạm chỉ xảy ra khi đỉnh stack đi sang phải (> 0) và ast đi sang trái (< 0)
            while (isAlive && ast < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int topAst = stack.peek();
                if (topAst < -ast) {
                    stack.pop(); // Tiểu tinh thạch ở đỉnh stack bị phá hủy, tiếp tục xét phần tử tiếp theo
                    continue;
                } else if (topAst == -ast) {
                    stack.pop(); // Cả hai đều bị phá hủy
                }
                isAlive = false; // ast bị phá hủy hoặc cả hai cùng bị phá hủy
            }
            if (isAlive) {
                stack.push(ast);
            }
        }

        int[] result = new int[stack.size()];
        // Deque.pollLast() lấy từ dưới stack lên để giữ đúng thứ tự ban đầu
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pollLast();
        }
        return result;
    }
}

//// cách 2
class Solution2 {
    public int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length;
        int[] stack = new int[len];
        int top = -1;

        for (int ast : asteroids) {
            boolean isAlive = true;
            // Va chạm chỉ xảy ra khi đỉnh stack đi sang phải (> 0) và ast đi sang trái (< 0)
            while (isAlive && ast < 0 && top >= 0 && stack[top] > 0) {
                if (stack[top] < -ast) {
                    top--; // Tiểu tinh thạch đỉnh stack bị phá hủy
                    continue;
                } else if (stack[top] == -ast) {
                    top--; // Cả hai bị phá hủy
                }
                isAlive = false; // ast bị phá hủy
            }
            if (isAlive) {
                top++;
                stack[top] = ast;
            }
        }

        int[] result = new int[top + 1];
        System.arraycopy(stack, 0, result, 0, top + 1);
        return result;
    }
}