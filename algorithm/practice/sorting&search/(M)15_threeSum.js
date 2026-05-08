var threeSum = function (nums) {
  let result = [];
  let left = 0;
  let right = 0;
  let sum = 0;

  nums.sort((a, b) => a - b);

  for (let i = 0; i <= nums.length - 1; i++) {
    if (i > 0 && nums[i] === nums[i - 1]) continue;
    left = i + 1;
    right = nums.length - 1;

    while (left < right) {
      sum = nums[i] + nums[left] + nums[right];

      if (sum === 0) {
        result.push([nums[i], nums[left], nums[right]]);
        while (left < right && nums[left] === nums[left + 1]) left++;
        while (left < right && nums[right] === nums[right - 1]) right--;
        left++;
        right--;
      } else if (sum < 0) {
        left++;
      } else {
        right--;
      }
    }
  }
  return result;
};
