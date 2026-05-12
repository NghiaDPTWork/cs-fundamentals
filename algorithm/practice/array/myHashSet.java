class MyHashSet {
    private final int ARRAY_SIZE = 769;
    private LinkedList<Integer>[] buckets;

    public MyHashSet() {
        buckets = new LinkedList[ARRAY_SIZE];
        for (int i = 0; i <= ARRAY_SIZE - 1; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % ARRAY_SIZE;
    }
    
    public void add(int key) {
        int index = hash(key);
        if (!buckets[index].contains(key)) {
            buckets[index].add(key);
        }
    }
    
    public void remove(int key) {
        int index = hash(key);
        buckets[index].remove(Integer.valueOf(key));
    }
    
    public boolean contains(int key) {
        int index = hash(key);
        return buckets[index].contains(key);
    }
}