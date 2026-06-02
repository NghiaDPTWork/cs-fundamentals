/**
 * Tìm giá trị trung bình lớn nhất của mảng con có độ dài k (Maximum Average Subarray I)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Cửa sổ trượt (Sliding Window)
 * - Ý tưởng: Duyệt qua mảng con đầu tiên gồm k phần tử để tính tổng khởi đầu. 
 *            Sau đó trượt cửa sổ sang phải từ chỉ số k đến cuối mảng: cộng thêm phần tử mới vào bên phải, trừ đi phần tử cũ ở bên trái.
 *            Cập nhật giá trị tổng lớn nhất trong suốt quá trình trượt.
 * - Ưu điểm:
 *   + Tối ưu thời gian: O(N) vì chỉ cần duyệt qua mảng đúng 1 lần.
 *   + Tiết kiệm không gian: O(1) do chỉ dùng các biến tính toán trực tiếp mà không cần cấu trúc dữ liệu bổ trợ.
 * - Nhược điểm:
 *   + Cần viết logic khởi tạo riêng cho cửa sổ đầu tiên.
 * 
 * CÁCH 2: Mảng cộng dồn (Prefix Sum Array)
 * - Ý tưởng: Xây dựng một mảng cộng dồn `prefixSum` có kích thước N + 1, trong đó `prefixSum[i]` lưu tổng của `i` phần tử đầu tiên.
 *            Tổng của mảng con độ dài k kết thúc tại chỉ số `i` sẽ được tính nhanh bằng công thức: `prefixSum[i + 1] - prefixSum[i + 1 - k]`.
 *            Duyệt qua các vị trí hợp lệ để tìm tổng lớn nhất.
 * - Ưu điểm:
 *   + Code rõ ràng, đồng nhất, không cần xử lý riêng biệt cho cửa sổ đầu tiên.
 *   + Mảng Prefix Sum có thể tái sử dụng cho nhiều truy vấn tính tổng đoạn con khác nhau (nếu bài toán mở rộng).
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Cần thêm mảng phụ kích thước N + 1, độ phức tạp không gian là O(N).
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;

        for(int i = 0; i <= k - 1; i++){
            windowSum += nums[i];
        }

        maxSum = windowSum;

        for(int i = k; i <= nums.length - 1; i++){
            windowSum += nums[i] - nums[i - k];

            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }
}

//// cách 2
class Solution2 {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            int currentSum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return (double) maxSum / k;
    }
}