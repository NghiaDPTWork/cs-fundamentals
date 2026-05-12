class MyHashMap {
    private final int  ARRAY_SIZE = 769;
    private LinkedList <int[]>[] buckets;

    public MyHashMap() {
        buckets = new LinkedList[ARRAY_SIZE];
        for(int i = 0; i <= ARRAY_SIZE - 1; i++){
            buckets[i] = new LinkedList<>();
        }
        
    }

    private int hash(int key){
        return key%ARRAY_SIZE;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        LinkedList<int[]> bucket = buckets[index];

        for(int[] pair : bucket){
            if(pair[0] == key){
                pair[1] = value;
                return;
            }
        }

        bucket.add(new int[] {key, value});
    }
    
    public int get(int key) {
        int index = hash(key);
        LinkedList<int[]> bucket = buckets[index];
        
        for (int[] pair : bucket) {
            if (pair[0] == key) {
                return pair[1]; 
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        LinkedList<int[]> bucket = buckets[index];

        for (int[] pair : bucket) {
            if (pair[0] == key) {
                bucket.remove(pair);
                return;
            }
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */