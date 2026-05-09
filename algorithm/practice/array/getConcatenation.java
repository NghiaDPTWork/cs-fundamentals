class Solution {
    public int[] getConcatenation(int[] nums) {
        int[]  concatArray = new int[nums.length*2];
        int len = nums.length;

        for(int i = 0; i <= nums.length - 1; i++){
            concatArray[i] = nums[i];
            concatArray[i + len] = nums[i];
        }

        return concatArray;
    }
}