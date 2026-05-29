/**
 * [LeetCode 875] Koko Eating Bananas
 * Độ khó: Medium
 * Link: https://leetcode.com/problems/koko-eating-bananas/
 *
 * ĐỀ BÀI:
 * Koko thích ăn chuối. Có `n` nải chuối, nải thứ `i` có piles[i] quả chuối.
 * Bảo vệ đã rời đi và sẽ quay lại sau `h` giờ.
 * Koko có thể quyết định tốc độ ăn chuối là `k` quả mỗi giờ. Mỗi giờ, cô ấy chọn một nải chuối
 * và ăn `k` quả từ nải đó. Nếu nải đó có ít hơn `k` quả, cô ấy sẽ ăn hết và không ăn thêm quả nào
 * khác trong giờ đó.
 * Koko muốn ăn chậm nhất có thể nhưng vẫn phải ăn hết chuối trước khi bảo vệ quay lại.
 * Hãy trả về số nguyên `k` tối thiểu sao cho cô ấy có thể ăn hết chuối trong vòng `h` giờ.
 *
 * Ý TƯỞNG GIẢI QUYẾT (Sử dụng Binary Search trên miền kết quả):
 * - Tốc độ ăn tối thiểu (left) là 1 nải/giờ.
 * - Tốc độ ăn tối đa (right) là số lượng chuối lớn nhất trong một nải (Math.max(...piles)).
 * - Ta thực hiện Binary Search để tìm tốc độ nhỏ nhất (mid) thỏa mãn việc ăn hết chuối trong `h` giờ.
 */

/**
 * @param {number[]} piles
 * @param {number} h
 * @return {number}
 */
var minEatingSpeed = function (piles, h) {
  /**
   * Hàm kiểm tra xem với tốc độ `speed` cho trước, Koko có thể ăn hết chuối trong `h` giờ không.
   * @param {number[]} piles
   * @param {number} speed
   * @param {number} h
   * @returns {boolean}
   */
  function canFinish(piles, speed, h) {
    let totalHours = 0;
    for (const pile of piles) {
      totalHours += Math.ceil(pile / speed);
    }
    return totalHours <= h;
  }

  let left = 1;
  // Thay vì dùng Arrays.sort() mất O(N log N), ta tìm MAX mất O(N)
  let right = Math.max(...piles);

  while (left < right) {
    const mid = Math.floor(left + (right - left) / 2);

    // Nếu tốc độ mid có thể ăn xong -> Cố gắng giảm tốc độ xuống (tìm sang bên trái)
    if (canFinish(piles, mid, h)) {
      right = mid;
    } else {
      // Nếu tốc độ mid không đủ -> Tăng tốc độ lên
      left = mid + 1;
    }
  }
  return left;
};
