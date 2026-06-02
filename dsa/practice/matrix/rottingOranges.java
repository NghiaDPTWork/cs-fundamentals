/**
 * [LeetCode 994] Rotting Oranges
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA CÁCH GIẢI:
 * 
 * CÁCH 1: BFS dùng Queue thông thường (Multi-source BFS)
 * - Ý tưởng: Tìm toàn bộ cam hỏng ban đầu cho vào Queue và đếm số lượng cam còn tươi.
 *            Chạy BFS theo cấp độ (mỗi cấp tương đương 1 phút trôi qua). Ở mỗi bước,
 *            loạt cam hỏng hiện tại sẽ làm hỏng các cam tươi xung quanh, giảm số lượng cam tươi và thêm chúng vào Queue.
 *            Nếu kết thúc BFS mà số cam tươi vẫn lớn hơn 0 thì trả về -1, ngược lại trả về số phút.
 * - Ưu điểm:
 *   + Giải thuật chuẩn, trực quan, đảm bảo tìm được thời gian ngắn nhất (do tính chất của BFS trên đồ thị không trọng số).
 * - Nhược điểm:
 *   + Tốn chi phí bộ nhớ cho việc lưu trữ các mảng `int[]` đại diện cho tọa độ trong Queue.
 * 
 * CÁCH 2: Tối ưu hàng đợi bằng mảng 1D (Array Queue Simulation)
 * - Ý tưởng: Thay vì dùng `LinkedList` hoặc `ArrayDeque` và bọc tọa độ trong `new int[]{r, c}`,
 *            ta có thể dùng một mảng số nguyên 1D lớn làm hàng đợi mô phỏng. Mã hóa tọa độ (r, c) thành `r * cols + c`.
 * - Ưu điểm:
 *   + Tối ưu hóa bộ nhớ và tốc độ chạy vượt trội do loại bỏ thao tác boxing/unboxing và cấp phát vùng nhớ liên tục.
 * - Nhược điểm:
 *   + Phức tạp hơn trong việc cài đặt và tính toán chỉ số.
 */

import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;
        
        // Thêm tất cả cam hỏng vào queue và đếm cam tươi
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }
        
        if (freshOranges == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    // Nếu gặp cam tươi xung quanh thì làm hỏng nó
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        freshOranges--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }
        
        return freshOranges == 0 ? minutes : -1;
    }
}

//// cách 2
class Solution2 {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] queue = new int[rows * cols];
        int head = 0, tail = 0;
        int freshOranges = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue[tail++] = r * cols + c;
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) return 0;

        int minutes = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (head < tail && freshOranges > 0) {
            int size = tail - head;
            for (int i = 0; i < size; i++) {
                int curr = queue[head++];
                int r = curr / cols;
                int c = curr % cols;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        freshOranges--;
                        queue[tail++] = nr * cols + nc;
                    }
                }
            }
            minutes++;
        }

        return freshOranges == 0 ? minutes : -1;
    }
}
