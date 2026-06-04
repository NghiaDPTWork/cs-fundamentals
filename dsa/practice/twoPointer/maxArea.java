/**
 * [LeetCode 11] Container With Most Water
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Hai con trỏ (Two Pointers)
 * - Ý tưởng: Dùng hai con trỏ `left` ở đầu mảng (0) và `right` ở cuối mảng (n-1).
 *            Diện tích chứa nước được tính bởi công thức: `area = min(height[left], height[right]) * (right - left)`.
 *            Ta cập nhật diện tích lớn nhất thu được.
 *            Để tối đa hóa diện tích, ta cần dịch chuyển con trỏ trỏ vào cột có chiều cao nhỏ hơn,
 *            vì nếu dịch chuyển cột lớn hơn thì chiều cao giới hạn chắc chắn không tăng được, trong khi chiều rộng chắc chắn giảm đi.
 * - Ưu điểm:
 *   + Tối ưu thời gian tuyệt đối: O(N) vì chỉ cần duyệt qua mảng đúng 1 lần.
 *   + Tiết kiệm không gian: O(1) do chỉ dùng các biến con trỏ cơ bản.
 * - Nhược điểm:
 *   + Cần chứng minh/hiểu rõ cơ sở toán học tại sao dịch chuyển con trỏ có chiều cao thấp hơn là tối ưu.
 * 
 * CÁCH 2: Duyệt trâu (Brute Force - Hai vòng lặp)
 * - Ý tưởng: Xét tất cả các cặp đường thẳng khác nhau, tính diện tích của từng cặp và tìm diện tích lớn nhất.
 * - Ưu điểm:
 *   + Cực kỳ đơn giản, dễ hiểu và dễ cài đặt trực quan.
 * - Nhược điểm:
 *   + Hiệu năng kém: O(N^2) thời gian, dễ gây lỗi Time Limit Exceeded (TLE) trên LeetCode với các test case lớn.
 *   + Tiết kiệm không gian: O(1) không gian.
 */

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWaterArea = 0;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = minHeight * width;
            maxWaterArea = Math.max(maxWaterArea, currentArea);

            // Dịch chuyển con trỏ của cột thấp hơn
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWaterArea;
    }
}

//// cách 2
class Solution2 {
    public int maxArea(int[] height) {
        int maxWaterArea = 0;
        int n = height.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int width = j - i;
                int minHeight = Math.min(height[i], height[j]);
                int currentArea = minHeight * width;
                maxWaterArea = Math.max(maxWaterArea, currentArea);
            }
        }

        return maxWaterArea;
    }
}
