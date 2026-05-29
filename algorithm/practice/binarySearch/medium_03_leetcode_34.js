/**
 * [LeetCode 34] Find First and Last Position of Element in Sorted Array
 * Độ khó: Medium
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * ĐỀ BÀI:
 * Cho một mảng các số nguyên `nums` đã được sắp xếp theo thứ tự không giảm,
 * hãy tìm vị trí bắt đầu và kết thúc của một giá trị `target` cho trước.
 * Nếu không tìm thấy `target` trong mảng, trả về [-1, -1].
 * Bạn phải viết một thuật toán có độ phức tạp thời gian O(log n).
 *
 * Ví dụ:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3, 4]
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1, -1]
 *
 * CÁC TIẾP CẬN GIẢI QUYẾT:
 * 1. Cách tiếp cận 1: Lai giữa Binary Search & Mở rộng tuyến tính (Hybrid Approach)
 *    - Dùng Binary Search tìm ra một vị trí chứa `target` bất kỳ.
 *    - Từ vị trí đó loang rộng sang trái và phải để tìm điểm bắt đầu và kết thúc.
 *    - Ưu điểm: Code ngắn gọn, trực quan.
 *    - Nhược điểm: Trong case xấu nhất (tất cả các số giống nhau), độ phức tạp trở thành O(N).
 *
 * 2. Cách tiếp cận 2 (Tối ưu chuẩn LeetCode): Sử dụng 2 lần Binary Search độc lập
 *    - Đảm bảo độ phức tạp THỜI GIAN LUÔN LÀ O(log N).
 */

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function (nums, target) {
  // --- CÁCH 1: HYBRID APPROACH ---
  function findRangeHybrid(arr, target, idx) {
    let start = idx;
    let end = idx;
    // Lùi cho tới khi không khớp nữa
    while (start >= 0 && arr[start] === target) {
      start--;
    }
    // Tiến cho tới khi không khớp nữa
    while (end < arr.length && arr[end] === target) {
      end++;
    }
    // BUGS FIX: Vì khi dừng lại ta đã đi lố qua phần tử không khớp (hoặc văng khỏi mảng)
    // Nên kết quả thực phải là [start + 1, end - 1]
    return [start + 1, end - 1];
  }

  function searchRangeHybrid(arr, target) {
    let left = 0;
    let right = arr.length - 1;

    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      if (arr[mid] === target) {
        // BUGS FIX: Cần có chữ `return` để trả về kết quả của hàm findRangeHybrid
        return findRangeHybrid(arr, target, mid);
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return [-1, -1];
  }

  // --- CÁCH 2: OPTIMAL APPROACH (O(log N)) ---
  function searchRangeOptimal(arr, target) {
    const findBound = (isFirst) => {
      let left = 0;
      let right = arr.length - 1;
      let bound = -1;

      while (left <= right) {
        const mid = Math.floor((left + right) / 2);
        if (arr[mid] === target) {
          bound = mid; // Tạm thời ghi nhận một điểm
          if (isFirst) {
            right = mid - 1; // Tiếp tục tìm biên trái sâu hơn nữa
          } else {
            left = mid + 1; // Tiếp tục tìm biên phải sâu hơn nữa
          }
        } else if (arr[mid] < target) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
      return bound;
    };

    const first = findBound(true);
    if (first === -1) return [-1, -1];
    const last = findBound(false);

    return [first, last];
  }

  // Mặc định chạy cách tối ưu O(log N)
  return searchRangeOptimal(nums, target);
};
