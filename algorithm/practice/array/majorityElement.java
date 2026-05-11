class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        if (nums.length <= 1) {
            return (nums.length == 1) ? nums[0] : -1;
        }

        for(int value: nums){
            if (count == 0) {
                candidate = value;
            }

            if (value == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}