// ===========================================================================
// ĐỀ BÀI: KIỂM TRA ĐƯỜNG ĐI TRÊN MA TRẬN (GRID PATH VALIDATION)
// ===========================================================================
// Cho một ma trận (mảng 2 chiều) gồm các ô trống (0) và vật cản (1).
// Bạn được cung cấp:
//   - Tọa độ xuất phát `start` dạng [dòng, cột].
//   - Tọa độ đích `dest` dạng [dòng, cột].
//   - Một chuỗi các ký tự đại diện cho các bước di chuyển (ví dụ: "RDDL").
//
// Nhiệm vụ của bạn là kiểm tra xem nếu đi theo đúng chuỗi di chuyển đó,
// có đến được điểm đích thành công hay không.
//
// Các quy tắc di chuyển:
//   - Các hệ ký hiệu được hỗ trợ:
//       + Tiếng Anh: 'U' (Lên), 'D' (Xuống), 'L' (Trái), 'R' (Phải)
//       + Tiếng Việt: 'L' (Lên), 'X' (Xuống), 'T' (Trái), 'P' (Phải)
//       + Mũi tên: '↑', '↓', '←', '→'
//   - Trả về FALSE nếu:
//       + Di chuyển vượt ra ngoài biên của ma trận.
//       + Di chuyển vào ô chứa vật cản (ô có giá trị là 1).
//       + Gặp ký tự di chuyển không hợp lệ.
//       + Đi hết chuỗi di chuyển nhưng không dừng đúng tại tọa độ đích `dest`.
//   - Trả về TRUE nếu:
//       + Đi hết chuỗi di chuyển an toàn và dừng chính xác tại tọa độ đích `dest`.
// ===========================================================================

// HƯỚNG GIẢI:
// 1. Khởi tạo vị trí hiện tại bằng vị trí xuất phát start (currRow, currCol).
// 2. Duyệt qua từng ký tự trong chuỗi di chuyển (moves).
// 3. Giải mã ký tự thành hướng di chuyển tương ứng dựa trên hệ ký hiệu (detectScheme).
// 4. Kiểm tra điều kiện an toàn sau mỗi bước đi:
//    - Tọa độ mới nằm trong ma trận (0 <= row < maxRows, 0 <= col < maxCols).
//    - Ô tại tọa độ mới không phải vật cản (grid[row][col] != 1).
//    - Nếu vi phạm bất kỳ điều kiện nào, trả về false ngay lập tức.
// 5. Sau khi đi hết chuỗi di chuyển, kiểm tra xem vị trí cuối cùng có khớp với tọa độ đích hay không.

public class topic {

    public static boolean isValidPath(int[][] grid, int[] start, int[] dest, String moves) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int currRow = start[0];
        int currCol = start[1];

        // Kiểm tra vị trí bắt đầu có hợp lệ không
        if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
            return false;
        }

        // Tự động nhận diện hệ ký hiệu di chuyển (EN: U/D/L/R, VN: L/X/T/P, ARROW: ↑/↓/←/→)
        String scheme = detectScheme(moves);

        for (int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            int[] offset = getDirectionOffset(move, scheme);
            
            if (offset == null) {
                return false; 
                // Ký tự di chuyển không hợp lệ
            }

            currRow += offset[0];
            currCol += offset[1];

            // Kiểm tra giới hạn biên và vật cản
            if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols || grid[currRow][currCol] == 1) {
                return false;
            }
        }

        // Kiểm tra xem đã đến đúng đích chưa
        return currRow == dest[0] && currCol == dest[1];
    }

    private static String detectScheme(String moves) {
        for (char c : moves.toCharArray()) {
            if (c == 'U' || c == 'D' || c == 'R') return "EN";
            if (c == 'X' || c == 'T' || c == 'P') return "VN";
            if (c == '↑' || c == '↓' || c == '←' || c == '→') return "ARROW";
        }
        return "EN"; // Mặc định
    }

    private static int[] getDirectionOffset(char move, String scheme) {
        if ("EN".equals(scheme)) {
            switch (move) {
                case 'U': return new int[]{-1, 0};
                case 'D': return new int[]{1, 0};
                case 'L': return new int[]{0, -1};
                case 'R': return new int[]{0, 1};
            }
        } else if ("VN".equals(scheme)) {
            switch (move) {
                case 'L': return new int[]{-1, 0};
                case 'X': return new int[]{1, 0};
                case 'T': return new int[]{0, -1};
                case 'P': return new int[]{0, 1};
            }
        } else if ("ARROW".equals(scheme)) {
            switch (move) {
                case '↑': return new int[]{-1, 0};
                case '↓': return new int[]{1, 0};
                case '←': return new int[]{0, -1};
                case '→': return new int[]{0, 1};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 0, 0}
        };

        int[] start = {0, 0};
        int[] dest = {2, 0};

        // Test English: R -> D -> D -> L (Đi vòng qua vật cản)
        System.out.println("Test EN (RDDL): " + isValidPath(grid, start, dest, "RDDL")); // true

        // Test VN: P -> X -> X -> T (Đi vòng qua vật cản)
        System.out.println("Test VN (PXXT): " + isValidPath(grid, start, dest, "PXXT")); // true

        // Test Arrow: → -> ↓ -> ↓ -> ← (Đi vòng qua vật cản)
        System.out.println("Test Arrow (→↓↓←): " + isValidPath(grid, start, dest, "→↓↓←")); // true

        // Test Obstacle: D (Đâm vào vật cản)
        System.out.println("Test Obstacle (D): " + isValidPath(grid, start, dest, "D")); // false

        // Test Out of bounds: L (Vượt biên)
        System.out.println("Test Out of bounds (L): " + isValidPath(grid, start, dest, "L")); // false
    }
}