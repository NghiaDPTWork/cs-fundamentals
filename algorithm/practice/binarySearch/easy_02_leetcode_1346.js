/**
 * [LeetCode 1346] Check If N and Its Double Exist
 * Độ khó: Easy
 * Link: https://leetcode.com/problems/check-if-n-and-its-double-exist/
 *
 * ĐỀ BÀI:
 * Cho một mảng số nguyên `arr`. Hãy kiểm tra xem có tồn tại hai chỉ số `i` và `j`
 * khác nhau (i != j) sao cho:
 *     arr[i] == 2 * arr[j]
 * hay không.
 *
 * Trả về `true` nếu có, ngược lại trả về `false`.
 *
 * Ví dụ:
 * Input: arr = [10,2,5,3]
 * Output: true (vì 10 = 2 * 5)
 *
 * HƯỚNG GIẢI BẰNG SORT & BINARY SEARCH:
 * 1. Sử dụng Bubble Sort để sắp xếp mảng `arr` tăng dần.
 * 2. Duyệt qua từng phần tử `arr[i]`.
 * 3. Tính giá trị cần tìm: `target = 2 * arr[i]`.
 * 4. Sử dụng Binary Search để tìm `target` trong mảng.
 *    LƯU Ý: Đảm bảo không tìm trúng chính phần tử tại index `i` (nhất là với số 0).
 */

/**
 * @param {number[]} arr
 * @return {boolean}
 */
var checkIfExist = function (arr) {
  // 1. Hàm Bubble Sort
  function bubbleSort(array) {
    const n = array.length;
    for (let i = 0; i < n - 1; i++) {
      let swapped = false;
      for (let j = 0; j < n - i - 1; j++) {
        if (array[j] > array[j + 1]) {
          let temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped) break;
    }
    return array;
  }

  // 2. Hàm Binary Search
  function binarySearch(array, target, excludeIndex) {
    let left = 0;
    let right = array.length - 1;

    while (left <= right) {
      let mid = Math.floor((left + right) / 2);
      if (array[mid] === target) {
        if (mid !== excludeIndex) {
          return true;
        }
        if (mid + 1 < array.length && array[mid + 1] === target) return true;
        if (mid - 1 >= 0 && array[mid - 1] === target) return true;
        return false;
      } else if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return false;
  }

  const sortedArr = bubbleSort([...arr]);

  // 3. Lặp và check
  for (let i = 0; i < sortedArr.length; i++) {
    const target = sortedArr[i] * 2;
    if (binarySearch(sortedArr, target, i)) {
      return true;
    }
  }

  return false;
};
