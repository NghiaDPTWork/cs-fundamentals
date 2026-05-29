/**
 * Kiểm tra mảng đơn điệu (Monotonic Array).
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Một lượt duyệt song song (Single Pass with two flags)
 * - Ý tưởng: Sử dụng 2 cờ hiệu `isIncreasing` và `isDecreasing` cùng được gán ban đầu là true.
 *            Duyệt qua mảng đúng 1 lần. Nếu gặp phần tử sau nhỏ hơn phần tử trước, ta tắt cờ `isIncreasing`.
 *            Nếu gặp phần tử sau lớn hơn phần tử trước, ta tắt cờ `isDecreasing`.
 *            Nếu cả hai cờ đều bị tắt (mảng vừa tăng vừa giảm), trả về false ngay lập tức.
 * - Ưu điểm:
 *   + Hiệu năng tối ưu: Chỉ duyệt qua mảng tối đa đúng 1 lần (Single Pass).
 *   + Cơ chế dừng sớm (short-circuiting): Trả về false ngay khi phát hiện mảng không đơn điệu.
 *   + Tiết kiệm bộ nhớ: O(1) không gian.
 * - Nhược điểm:
 *   + Tên file hiện tại bị sai chính tả nhẹ ("fixingMonotomic" thay vì "fixingMonotonic").
 * 
 * CÁCH 2: Tách biệt hai hàm kiểm tra đơn hướng (Helper Methods)
 * - Ý tưởng: Viết 2 hàm trợ giúp riêng biệt: `isIncreasing` (kiểm tra xem mảng có chỉ tăng/bằng hay không) 
 *            và `isDecreasing` (kiểm tra xem mảng có chỉ giảm/bằng hay không).
 *            Hàm chính chỉ cần trả về `isIncreasing(nums) || isDecreasing(nums)`.
 * - Ưu điểm:
 *   + Mã nguồn cực kỳ sạch sẽ, rõ ràng (Clean Code): Tách nhỏ trách nhiệm giúp dễ đọc, dễ hiểu và bảo trì.
 * - Nhược điểm:
 *   + Trong trường hợp xấu nhất (mảng đơn điệu tăng hoặc giảm), ta phải duyệt mảng 2 lần (lần 1 kiểm tra tăng, lần 2 kiểm tra giảm).
 */
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                isIncreasing = false; 
            }
            if (nums[i] < nums[i + 1]) {
                isDecreasing = false; 
            }
            
            if (!isIncreasing && !isDecreasing) {
                return false;
            }
        }

        return true; 
    }
}

//// cách 2
class Solution2 {
    public boolean isMonotonic(int[] nums) {
        return isIncreasing(nums) || isDecreasing(nums);
    }

    private boolean isIncreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isDecreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
