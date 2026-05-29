/**
 * Hai con trỏ (Two Pointers) cho bài toán Two Sum II - Mảng đầu vào đã sắp xếp.
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Hai con trỏ (Two Pointers)
 * - Ý tưởng: Sử dụng hai con trỏ `left` ở đầu mảng (chỉ số 0) và `right` ở cuối mảng (chỉ số n - 1).
 *            Tính `sum = numbers[left] + numbers[right]`. 
 *            Nếu `sum == target`: trả về kết quả 1-indexed.
 *            Nếu `sum < target`: ta cần tổng lớn hơn nên dịch con trỏ `left` sang phải (`left++`).
 *            Nếu `sum > target`: ta cần tổng nhỏ hơn nên dịch con trỏ `right` sang trái (`right--`).
 * - Ưu điểm:
 *   + Tối ưu thời gian tuyệt đối: Chỉ mất O(N) nhờ tận dụng tính chất mảng đã được sắp xếp tăng dần.
 *   + Tiết kiệm không gian: O(1) bộ nhớ phụ.
 * - Nhược điểm:
 *   + Ràng buộc: Chỉ áp dụng được khi mảng đầu vào ĐÃ SẮP XẾP.
 * 
 * CÁCH 2: Tìm kiếm Nhị phân (Binary Search)
 * - Ý tưởng: Duyệt qua từng số `numbers[i]`. Ta cần tìm giá trị còn thiếu `complement = target - numbers[i]`.
 *            Vì mảng đã sắp xếp, ta có thể tìm kiếm nhị phân giá trị `complement` này trong đoạn còn lại từ `i + 1` đến cuối mảng.
 * - Ưu điểm:
 *   + Hiệu quả tốt hơn cách Brute Force thông thường (O(N^2)), đạt độ phức tạp O(N log N).
 *   + Tiết kiệm không gian: O(1) không gian.
 * - Nhược điểm:
 *   + Chạy chậm hơn so với cách dùng Hai con trỏ (O(N)).
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1}; // Trả về kết quả 1-indexed
            } else if (sum < target) {
                left++; // Cần tổng lớn hơn -> dịch con trỏ trái sang phải
            } else {
                right--; // Cần tổng nhỏ hơn -> dịch con trỏ phải sang trái
            }
        }

        return new int[] {};
    }
}

//// cách 2
class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            int complement = target - numbers[i];
            int left = i + 1;
            int right = n - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == complement) {
                    return new int[] {i + 1, mid + 1};
                } else if (numbers[mid] < complement) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return new int[] {};
    }
}
