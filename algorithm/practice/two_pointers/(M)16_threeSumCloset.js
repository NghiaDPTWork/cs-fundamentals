/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
  let sum = 0;
  let left = 0;
  let right = 0;

  nums.sort((a, b) => a - b);
  let closest = nums[0] + nums[1] + nums[2];

  if (closest > target) return closest;

  for (let i = 1; i <= nums.length - 3; i++) {
    left = i + 1;
    right = nums.length - 1;

    while (left < right) {
      sum = nums[i] + nums[left] + nums[right];
      if (sum === target) return target;

      if (Math.abs(sum - target) < Math.abs(closest - target)) {
        closest = sum;
      }

      if (sum < target) {
        left++;
        while (left < right && nums[left] === nums[left - 1]) left++;
      } else {
        right--;
        while (left < right && nums[right] === nums[right + 1]) right--;
      }
    }
  }
  return closest;
};
