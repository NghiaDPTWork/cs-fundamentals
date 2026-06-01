/**
 * [LeetCode 733] Flood Fill
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA CÁC CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Đệ quy DFS (Depth First Search)
 * - Ý tưởng: Bắt đầu từ điểm (sr, sc). Nếu màu gốc khác với màu mới, ta tiến hành đổi màu cho ô hiện tại.
 *            Sau đó, gọi đệ quy bốn hướng xung quanh cho những ô có cùng màu gốc.
 * - Ưu điểm:
 *   + Cực kỳ ngắn gọn, dễ viết và tự nhiên cho các bài toán duyệt đồ thị dạng này.
 *   + Không tốn thêm bộ nhớ cho cấu trúc hàng đợi (Queue) ngoài lời gọi hàm của Stack (Call Stack).
 * - Nhược điểm:
 *   + Có nguy cơ xảy ra StackOverflow nếu ma trận cực kỳ lớn và độ sâu đệ quy vượt quá giới hạn Stack của hệ thống.
 * 
 * CÁCH 2: Sử dụng BFS với Hàng đợi (Breadth First Search Queue)
 * - Ý tưởng: Dùng một hàng đợi (Queue) để lưu trữ các tọa độ ô cần xử lý. Thêm ô bắt đầu vào Queue.
 *            Trong khi Queue không rỗng, lấy tọa độ ra, đổi màu và đưa các ô lân cận hợp lệ (cùng màu gốc) vào Queue.
 * - Ưu điểm:
 *   + An toàn, không bị tràn ngăn xếp (Stack Overflow) ngay cả với ma trận rất lớn.
 * - Nhược điểm:
 *   + Code dài dòng hơn do phải tự quản lý Queue.
 *   + Chi phí bộ nhớ tăng do việc cấp phát đối tượng Queue và các mảng tọa độ `int[]`.
 */

import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor != color) {
            dfs(image, sr, sc, originalColor, color);
        }
        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != originalColor) {
            return;
        }
        image[r][c] = newColor;
        dfs(image, r - 1, c, originalColor, newColor); // Lên
        dfs(image, r + 1, c, originalColor, newColor); // Xuống
        dfs(image, r, c - 1, originalColor, newColor); // Trái
        dfs(image, r, c + 1, originalColor, newColor); // Phải
    }
}

//// cách 2
class Solution2 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor == color) return image;

        int rows = image.length;
        int cols = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = color;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && image[nr][nc] == originalColor) {
                    image[nr][nc] = color;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return image;
    }
}
