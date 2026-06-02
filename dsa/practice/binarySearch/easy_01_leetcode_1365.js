/**
 * [LeetCode 1365] How Many Numbers Are Smaller Than the Current Number
 * Độ khó: Easy
 * Link: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * ĐỀ BÀI:
 * Cho một mảng số nguyên `nums`. Hãy trả về một mảng kết quả, trong đó mỗi phần tử
 * tại vị trí `i` cho biết có bao nhiêu số trong mảng nhỏ hơn `nums[i]`.
 *
 * Ví dụ:
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Giải thích:
 * Với nums[0]=8 có 4 số nhỏ hơn nó (1, 2, 2, 3).
 * Với nums[1]=1 không có số nào nhỏ hơn.
 *
 * HƯƠNG GIẢI BẰNG SORT & BINARY SEARCH:
 * 1. Tạo một bản sao của mảng `nums` và sắp xếp nó tăng dần (dùng Bubble Sort).
 *    VD: [8,1,2,2,3] -> [1,2,2,3,8]
 * 2. Với mỗi số `num` trong mảng ban đầu, dùng Binary Search để tìm VỊ TRÍ ĐẦU TIÊN
 *    của số đó xuất hiện trong mảng đã sắp xếp.
 *    Chỉ số (index) này chính là số lượng các phần tử nhỏ hơn nó!
 */

/**
 * @param {number[]} nums
 * @return {number[]}
 */
var smallerNumbersThanCurrent = function (nums) {
  // 1. Tạo bản sao mảng và bubbleSort mảng bản sao
  function bubbleSort(arr) {
    const n = arr.length;
    for (let i = 0; i < n - 1; i++) {
      let swapped = false;
      for (let j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
          swapped = true;
        }
      }
      if (!swapped) break;
    }
    return arr;
  }

  // 2. Hàm Binary Search biến thể: tìm Vị trí đầu tiên xuất hiện
  function findFirstOccurrence(arr, target) {
    let left = 0;
    let right = arr.length - 1;
    let result = -1;

    while (left <= right) {
      let mid = Math.floor((left + right) / 2);
      if (arr[mid] === target) {
        result = mid;
        right = mid - 1;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }

  const sortedNums = bubbleSort([...nums]);
  const answer = [];

  // 3. Loop qua nums ban đầu và search vị trí đầu tiên trong sortedNums
  for (let i = 0; i < nums.length; i++) {
    const count = findFirstOccurrence(sortedNums, nums[i]);
    answer.push(count);
  }

  return answer;
};
