/**
 * [LeetCode 463] Island Perimeter
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA CÁC CÁCH GIẢI:
 * 
 * CÁCH 1: Duyệt qua từng ô và kiểm tra 4 hướng (Direct Neighbor Check)
 * - Ý tưởng: Duyệt qua tất cả các ô của ma trận. Nếu gặp ô đất (1), ta kiểm tra 4 ô xung quanh nó.
 *            Nếu ô xung quanh nằm ngoài biên ma trận hoặc là nước (0), ta cộng 1 vào chu vi.
 * - Ưu điểm:
 *   + Trực quan, dễ hiểu và dễ cài đặt.
 *   + Không cần thay đổi dữ liệu trên ma trận gốc.
 * - Nhược điểm:
 *   + Thực hiện nhiều phép kiểm tra trùng lặp (mỗi cạnh giữa 2 ô đất sẽ bị kiểm tra 2 lần từ 2 phía).
 * 
 * CÁCH 2: Duyệt và trừ đi các cạnh chung (Edge Sharing)
 * - Ý tưởng: Duyệt qua các ô của ma trận. Nếu gặp ô đất (1), ta cộng 4 vào chu vi.
 *            Đồng thời, ta chỉ cần kiểm tra xem ô bên phải hoặc ô bên dưới có phải là đất (1) hay không.
 *            Nếu có, ta trừ đi 2 (vì mỗi cạnh chung làm giảm chu vi của cả 2 ô đi 1).
 * - Ưu điểm:
 *   + Số phép kiểm tra giảm đi một nửa (chỉ kiểm tra 2 hướng: phải và dưới thay vì 4 hướng).
 *   + Code ngắn gọn hơn.
 * - Nhược điểm:
 *   + Đòi hỏi tư duy toán học/hình học để suy ra công thức trừ cạnh chung.
 */

class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    // Kiểm tra 4 hướng xung quanh
                    // Hướng Lên
                    if (r == 0 || grid[r - 1][c] == 0) perimeter++;
                    // Hướng Xuống
                    if (r == rows - 1 || grid[r + 1][c] == 0) perimeter++;
                    // Hướng Trái
                    if (c == 0 || grid[r][c - 1] == 0) perimeter++;
                    // Hướng Phải
                    if (c == cols - 1 || grid[r][c + 1] == 0) perimeter++;
                }
            }
        }
        return perimeter;
    }
}

//// cách 2
class Solution2 {
    public int islandPerimeter(int[][] grid) {
        int islands = 0;
        int neighbours = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    islands++; // Đếm số ô đất
                    // Kiểm tra ô bên dưới
                    if (r < rows - 1 && grid[r + 1][c] == 1) neighbours++;
                    // Kiểm tra ô bên phải
                    if (c < cols - 1 && grid[r][c + 1] == 1) neighbours++;
                }
            }
        }
        return islands * 4 - neighbours * 2;
    }
}
