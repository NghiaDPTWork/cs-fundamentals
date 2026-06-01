/**
 * [LeetCode 55] Jump Game
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA CÁC CÁCH GIẢI:
 * 
 * CÁCH 1: Tiếp cận Greedy xuôi (Max Reachable Index)
 * - Ý tưởng: Duy trì một biến `maxReach` đại diện cho chỉ số xa nhất có thể đạt được.
 *            Duyệt qua mảng từ trái qua phải. Nếu tại chỉ số `i` hiện tại mà `i > maxReach` thì không thể đi tiếp,
 *            trả về false. Ngược lại, cập nhật `maxReach = max(maxReach, i + nums[i])`.
 *            Nếu `maxReach` lớn hơn hoặc bằng chỉ số cuối cùng của mảng, ta có thể trả về true ngay lập tức.
 * - Ưu điểm:
 *   + O(N) thời gian, O(1) không gian.
 *   + Dễ hiểu và trực quan vì đi theo hướng xuôi từ đầu đến cuối.
 * 
 * CÁCH 2: Tiếp cận Greedy ngược (Target Backtracking)
 * - Ý tưởng: Đi ngược từ cuối mảng. Xem vị trí cuối cùng là đích cần đạt tới (`target = nums.length - 1`).
 *            Duyệt ngược từ `i = nums.length - 2` về `0`. Nếu tại vị trí `i`, ta có thể nhảy tới hoặc qua `target`
 *            (`i + nums[i] >= target`), thì cập nhật `target = i`.
 *            Cuối cùng, nếu `target == 0`, có nghĩa ta có thể bắt đầu từ vị trí 0 và đi đến cuối, trả về true.
 * - Ưu điểm:
 *   + O(N) thời gian, O(1) không gian.
 *   + Logic toán học rất rõ ràng, code thậm chí ngắn gọn hơn cách 1.
 */

class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }
        return true;
    }
}

//// cách 2
class Solution2 {
    public boolean canJump(int[] nums) {
        int target = nums.length - 1;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= target) {
                target = i;
            }
        }
        
        return target == 0;
    }
}
