import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> myMap = new HashMap<>();
        int expect = 0;

        for(int i = 0; i <= nums.length - 1; i++){
            expect = target - nums[i];

            if(myMap.containsKey(expect)){
                return new int[] {myMap.get(expect), i};
            }

            myMap.put(nums[i], i);
        }

        return new int[] {};
    }
}