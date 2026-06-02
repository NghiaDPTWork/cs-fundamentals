/**
 * [LeetCode 2300] Successful Pairs of Spells and Potions
 * Độ khó: Medium
 * Link: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
 *
 * ĐỀ BÀI:
 * Bạn có 2 mảng số nguyên dương `spells` (bùa chú) và `potions` (độc dược).
 * Bạn cũng được cho số nguyên `success`.
 * Một cặp spell và potion được gọi là "Thành công" nếu tích của chúng (spell * potion)
 * lớn hơn hoặc bằng `success`.
 *
 * Yêu cầu: Trả về một mảng `pairs` trong đó pairs[i] là số lượng potion tạo thành cặp
 * thành công với spell[i].
 *
 * Ví dụ:
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Giải thích:
 * - spells[0] = 5: 5 * [2,3,4,5] >= 7 -> Có 4 potions thành công.
 * - spells[1] = 1: không có số nào trong potions * 1 >= 7 -> 0 potions.
 * - spells[2] = 3: 3 * [3,4,5] >= 7 -> Có 3 potions thành công.
 *
 * HƯỚNG GIẢI (SORT & BINARY SEARCH):
 * 1. Sắp xếp mảng `potions` tăng dần (Bạn có thể dùng Bubble Sort cho mảng nhỏ để luyện tập,
 *    tuy nhiên trên Leetcode thật sự nên dùng built-in sort vì lượng data lớn).
 * 2. Với mỗi `spell` trong mảng `spells`:
 *    a. Ta cần tìm số lượng potion thỏa mãn: potion >= success / spell.
 *    b. Tính `minPotionRequired = Math.ceil(success / spell)`.
 *    c. Sử dụng Binary Search để tìm VỊ TRÍ ĐẦU TIÊN trong mảng `potions` (đã sort)
 *       có giá trị >= `minPotionRequired`.
 *    d. Số lượng potions thành công = (Tổng số potions) - (Vị trí vừa tìm được).
 */

/**
 * @param {number[]} spells
 * @param {number[]} potions
 * @param {number} success
 * @return {number[]}
 */
var successfulPairs = function (spells, potions, success) {
  // 1. Sắp xếp potions
  function bubbleSort(arr) {
    return arr.sort((a, b) => a - b);
  }

  const sortedPotions = [...potions].sort((a, b) => a - b);
  const result = [];
  const m = sortedPotions.length;

  // 2. Duyệt qua các spells
  for (let i = 0; i < spells.length; i++) {
    let spell = spells[i];

    let threshold = Math.ceil(success / spell);

    // 3. Binary Search tìm phần tử đầu tiên >= threshold
    let left = 0;
    let right = m - 1;
    let foundIdx = m;

    while (left <= right) {
      let mid = Math.floor((left + right) / 2);

      if (sortedPotions[mid] >= threshold) {
        foundIdx = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    result.push(m - foundIdx);
  }

  return result;
};
