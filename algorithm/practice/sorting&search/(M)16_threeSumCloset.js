/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
  let result = Infinity;
  let sum = 0;
  let left = 0;
  let right = 0;

  nums.sort((a, b) => a - b);
  for (let i = 0; i <= nums.length - 2; i++) {
    if (i > 0 && nums[i] === nums[i - 1]) continue;

    left = i + 1;
    right = nums.length - 1;

    while (left < right) {
      sum = nums[i] + nums[left] + nums[right];

      if (sum === target) {
        return target;
      } else if (sum < target) {
        if (Math.abs(target - result) > Math.abs(target - sum)) result = sum;
        left++;
      } else {
        if (Math.abs(target - result) > Math.abs(target - sum)) result = sum;
        right--;
      }
    }
  }
  return result;
};
