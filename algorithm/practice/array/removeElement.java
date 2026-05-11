class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int idx = 0;

        while (idx < len) {
            if (nums[idx] == val) {
                nums[idx] = nums[len - 1];
                len--;
            } else {
                idx++;
            }
        }

        return len;
    }
}