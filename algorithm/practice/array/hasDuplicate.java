class Solution {
    public boolean hasDuplicate(int[] nums) {
        int checkNum = 0;
        int j = 0;

        for(int i = 0; i <= nums.length - 2; i++){
            checkNum = nums[i];
            j = i + 1;
            while(j <= nums.length - 1){
                if(nums[i] == nums[j]){
                    return true;
                }
                j++;
            }
        }
        return false;
    }
}