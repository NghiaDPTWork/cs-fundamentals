/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let myMap = new Map();
    let expect = 0;

    for(let i = 0; i <= nums.length - 1; i++){
        expect = target - nums[i];

        if(myMap.has(expect)){
            return  [myMap.get(expect), i];
        }
        myMap.set(nums[i], i);
    }
};