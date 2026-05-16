class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> myMap = new HashMap<>();

        for (int num : nums) {
            myMap.put(num, myMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (num1, num2) -> myMap.get(num1) - myMap.get(num2)
        );

        for (int value: myMap.keySet()){
            minHeap.add(value);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for(int i = k - 1; i >= 0; i--){
            result[i] = minHeap.poll();
        }
        
        return result;
    }
}
