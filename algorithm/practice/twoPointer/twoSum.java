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
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;

        while (leftPointer < rightPointer) {
            int currentSum = numbers[leftPointer] + numbers[rightPointer];
            if (currentSum == target) {
                return new int[] {leftPointer + 1, rightPointer + 1}; // Trả về kết quả 1-indexed
            } else if (currentSum < target) {
                leftPointer++; // Cần tổng lớn hơn -> dịch con trỏ trái sang phải
            } else {
                rightPointer--; // Cần tổng nhỏ hơn -> dịch con trỏ phải sang trái
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
            int neededValue = target - numbers[i];
            int searchStart = i + 1;
            int searchEnd = n - 1;

            while (searchStart <= searchEnd) {
                int middleIndex = searchStart + (searchEnd - searchStart) / 2;
                if (numbers[middleIndex] == neededValue) {
                    return new int[] {i + 1, middleIndex + 1};
                } else if (numbers[middleIndex] < neededValue) {
                    searchStart = middleIndex + 1;
                } else {
                    searchEnd = middleIndex - 1;
                }
            }
        }

        return new int[] {};
    }
}
