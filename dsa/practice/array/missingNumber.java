/**
 * [LeetCode 268] Missing Number
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sắp xếp mảng (Sorting Approach)
 * - Ý tưởng: Sắp xếp mảng tăng dần. Duyệt qua mảng từ i = 0 đến nums.length - 1,
 *            nếu thấy nums[i] != i thì i chính là số bị thiếu.
 *            Nếu duyệt hết mảng mà không thiếu số nào, số bị thiếu chính là nums.length (n).
 * - Ưu điểm:
 *   + Trực quan, dễ hiểu và dễ tư duy cài đặt.
 * - Nhược điểm:
 *   + Hiệu năng trung bình: Độ phức tạp thời gian là O(N log N) do chi phí của Arrays.sort(), chậm hơn O(N) khi dữ liệu lớn.
 *   + Thay đổi mảng gốc: Sắp xếp in-place làm thay đổi mảng ban đầu.
 * 
 * CÁCH 2: Sử dụng công thức toán học tính tổng Gauss (Mathematical Sum)
 * - Ý tưởng: Tổng các số liên tiếp từ 0 đến n được tính bằng công thức: expectedSum = n * (n + 1) / 2.
 *            Ta tính tổng thực tế của toàn bộ các phần tử trong mảng (actualSum).
 *            Số bị thiếu chính bằng: expectedSum - actualSum.
 * - Ưu điểm:
 *   + Tối ưu thời gian tuyệt đối: Độ phức tạp thời gian là O(N) vì chỉ cần duyệt qua mảng đúng 1 lần để tính tổng.
 *   + Tối ưu bộ nhớ tuyệt đối: Độ phức tạp không gian là O(1) do chỉ dùng các biến số nguyên đơn giản để lưu trữ.
 *   + Giữ nguyên mảng gốc.
 * - Nhược điểm:
 *   + Có thể gặp nguy cơ tràn số (Integer Overflow) nếu n cực kỳ lớn trong Java, vì phép nhân n * (n + 1) có thể vượt quá giới hạn kiểu int. 
 *     (Tuy nhiên trên Leetcode với giới hạn n <= 10^4 thì kiểu int hoàn toàn an toàn).
 */

import java.util.Arrays;

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 1; i++){
            if(nums[i] != i)    return i;
        }
        return nums.length; 
    }
}

//// cách 2
class Solution2 {
    public int missingNumber(int[] nums) {
        int len = nums.length;

        int expectedSum = len * (len + 1) / 2;

        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}