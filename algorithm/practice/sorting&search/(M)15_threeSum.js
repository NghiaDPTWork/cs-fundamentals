var threeSum = function (nums) {
  let result = [];
  nums.sort((a, b) => a - b);
  let left = 0;
  let right = nums.length - 1;

  for (let i = 0; i <= nums.length - 1; i++) {
    if (i > 0 && nums[i] === nums[i - 1]) continue;

    left = i + 1;

    while (left < right) {
      let sum = nums[i] + nums[left] + nums[right];

      if (sum === 0) {
        result.push([nums[i], nums[left], nums[right]]);

        // while (left < right && nums[left] === nums[left + 1]) left++;
        // while (left < right && nums[right] === nums[right - 1]) right--;

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
