/**
 * Bài toán Move Zeroes.
 * 
 * ============================================
 * CÁCH 1: Solution (Dịch chuyển và Điền 0 - Two Pass)
 * 
 * Ưu điểm:
 * - Code cực kỳ đơn giản, trực quan, dễ hiểu.
 * - Thao tác ghi trực tiếp, không cần trung gian (swap).
 * 
 * Nhược điểm:
 * - Luôn thực hiện chính xác N thao tác ghi (ghi đè tất cả các phần tử khác 0, sau đó ghi các số 0).
 * 
 * Độ phức tạp:
 * - Thời gian: O(N) - N là số phần tử trong mảng nums.
 * - Không gian: O(1) - không tốn bộ nhớ phụ.
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroInsertIndex = 0; 
        
        for (int scanIndex = 0; scanIndex < nums.length; scanIndex++) {
            if (nums[scanIndex] != 0) {
                nums[nonZeroInsertIndex] = nums[scanIndex];
                nonZeroInsertIndex++;
            }
        }

        while (nonZeroInsertIndex < nums.length) {
            nums[nonZeroInsertIndex] = 0;
            nonZeroInsertIndex++;
        }
    }
}

// ============================================
/**
 * CÁCH 2: Solution2 (Hoán đổi hai con trỏ - One Pass)
 * 
 * Ưu điểm:
 * - Tối ưu số lần ghi (writes): Chỉ ghi khi có hoán đổi phần tử khác 0 với phần tử 0 ở phía trước.
 * - Nếu mảng đã có nhiều số 0 sẵn hoặc các số khác 0 đã ở đúng vị trí, số lần ghi giảm đi rất nhiều.
 * 
 * Nhược điểm:
 * - Code phức tạp hơn do dùng hoán đổi swap.
 * - Trong trường hợp xấu nhất (không có số 0 nào), nếu không kiểm tra `cur != lastNonZeroFoundAt` thì sẽ tốn thao tác swap vô ích.
 * 
 * Độ phức tạp:
 * - Thời gian: O(N)
 * - Không gian: O(1)
 */
class Solution2 {
    public void moveZeroes(int[] nums) {
        int swapTargetIndex = 0;
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            if (nums[currentIndex] != 0) {
                // Tránh swap không cần thiết khi con trỏ trùng nhau
                if (currentIndex != swapTargetIndex) {
                    int temporaryValue = nums[swapTargetIndex];
                    nums[swapTargetIndex] = nums[currentIndex];
                    nums[currentIndex] = temporaryValue;
                }
                swapTargetIndex++;
            }
        }
    }
}