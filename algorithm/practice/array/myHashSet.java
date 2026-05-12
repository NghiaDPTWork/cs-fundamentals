/**
 * BẢN CHẤT CỦA BÀI NÀY (Mục đích cốt lõi):
 * Giúp ta hiểu nguyên lý vận hành cơ bản của cấu trúc dữ liệu Bảng Băm (Hash Table).
 * Cụ thể gồm 2 yếu tố then chốt:
 * 1. Cơ chế Hashing (Ánh xạ khóa Key thành chỉ số Array Index một cách nhanh nhất).
 * 2. Cơ chế xử lý va chạm (Collision Handling) bằng phương pháp Separate Chaining (sử dụng LinkedList).
 */
class MyHashSet {
    // 1. TẠI SAO LẠI LÀ 769?
    // 769 là một SỐ NGUYÊN TỐ (Prime Number). 
    // Việc chọn độ dài mảng là số nguyên tố giúp phân tán các phần tử đồng đều hơn vào các buckets,
    // qua đó giảm thiểu tối đa tỷ lệ xảy ra xung đột (Collision) khi các key có quy luật lặp lại nhất định.
    private final int ARRAY_SIZE = 769;
    private LinkedList<Integer>[] buckets;

    public MyHashSet() {
        buckets = new LinkedList[ARRAY_SIZE];
        for (int i = 0; i <= ARRAY_SIZE - 1; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // 2. TẠI SAO DÙNG MOD (%) MÀ KHÔNG XÀI CỘNG TRỪ NHÂN CHIA?
    // Phép chia lấy dư (%) là phép toán DUY NHẤT luôn đảm bảo kết quả trả về (index) 
    // LUÔN LUÔN nằm gói gọn trong phạm vi [0, ARRAY_SIZE - 1]. 
    // Bất kể input key to đến đâu, nó cũng sẽ được "ép" chui vào một ô hợp lệ trong mảng.
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