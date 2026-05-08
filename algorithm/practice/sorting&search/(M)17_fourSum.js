/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
  let result = [];
  let left = 0;
  let right = 0;
  let mid = 0;
  let sum = 0;

  nums.sort((a, b) => a - b);
  for (let i = 0; i <= nums.length - 3; i++) {
    left = i + 1;
    right = nums.length - 1;
    mid = left - (left + right) / 2;
    if (i > 0 && nums[i] === nums[i - 1]) continue;

    while (left > mid && mid < right) {
      sum = nums[i] + nums[left] + nums[mid] + nums[right];

      if (sum === target) {
        result.push([nums[i], nums[left], nums[mid], nums[right]]);

        left++;
        right--;
      } else if (sum < target) {
        mid++;
      } else {
        mid--;
      }
    }
  }
  return result;
};
