/**
 * Tính tích của mảng ngoại trừ chính nó (Product of Array Except Self)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Tối ưu bộ nhớ O(1) không gian phụ trợ (Single Output Array)
 * - Ý tưởng: Tận dụng trực tiếp mảng kết quả `result` để lưu tích lũy các phần tử bên trái (Prefix Product).
 *            Sau đó dùng một biến đơn `right` để lưu tích lũy các phần tử bên phải (Suffix Product) 
 *            khi duyệt ngược từ cuối mảng về đầu, nhân trực tiếp giá trị này vào `result[i]`.
 * - Ưu điểm:
 *   + Tiết kiệm bộ nhớ tối đa: Đạt độ phức tạp không gian phụ trợ O(1) (ngoại trừ mảng kết quả đầu ra).
 * - Nhược điểm:
 *   + Logic cập nhật biến `right` và nhân trực tiếp hơi trừu tượng và khó hình dung hơn đối với người mới học.
 * 
 * CÁCH 2: Sử dụng 2 mảng phụ lưu tích luỹ Trái và Phải (Prefix & Suffix Arrays)
 * - Ý tưởng: Xây dựng riêng biệt 2 mảng phụ: `left[i]` chứa tích các phần tử bên trái chỉ số `i`, 
 *            và `right[i]` chứa tích các phần tử bên phải chỉ số `i`. 
 *            Kết quả tại mỗi vị trí được tính bằng: `result[i] = left[i] * right[i]`.
 * - Ưu điểm:
 *   + Cực kỳ trực quan, rõ ràng và dễ triển khai logic.
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Độ phức tạp không gian phụ trợ là O(N) do phải khởi tạo thêm 2 mảng phụ có kích thước bằng mảng đầu vào.
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums.length < 2)
            return nums;

        int n = nums.length;
        int[] result = new int[n];

        // left
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // right
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
    }
}

//// cách 2
class Solution2 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return nums;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }
}
