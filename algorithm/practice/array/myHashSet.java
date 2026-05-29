/**
 * [LeetCode 705] Design HashSet
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Băm chuỗi (Chaining with LinkedList)
 * - Ý tưởng: Sử dụng một mảng có kích thước cố định (chọn số nguyên tố 769) chứa các LinkedList.
 *            Mã băm của phần tử xác định bucket tương ứng. Thao tác thêm/xóa/kiểm tra được thực hiện trực tiếp trên LinkedList của bucket đó.
 * - Ưu điểm:
 *   + Tiết kiệm bộ nhớ khi số lượng phần tử thực tế ít: Bộ nhớ chỉ được cấp phát động cho các phần tử thực tế được đưa vào HashSet.
 *   + Không giới hạn cứng dung lượng: Có thể chứa nhiều phần tử hơn ARRAY_SIZE do các LinkedList có thể phát triển động liên tục.
 * - Nhược điểm:
 *   + Hiệu suất suy giảm khi xảy ra nhiều xung đột băm.
 *   + Chi phí bộ nhớ phụ (overhead) cho các Node LinkedList và autoboxing của kiểu Integer.
 * 
 * CÁCH 2: Sử dụng Mảng Boolean trực tiếp (Direct Access Array / Boolean Array)
 * - Ý tưởng: Vì các giá trị key trong đề bài LeetCode bị giới hạn trong khoảng [0, 10^6], ta có thể khởi tạo một mảng boolean tĩnh có kích thước 1000001.
 *            Khi thêm key, ta đánh dấu `exists[key] = true`. Khi xóa, đánh dấu `exists[key] = false`. Kiểm tra sự tồn tại chỉ là việc truy cập `exists[key]`.
 * - Ưu điểm:
 *   + Hiệu năng tối đa O(1) tuyệt đối: Thao tác cực kỳ nhanh, chỉ là truy cập chỉ số mảng trực tiếp, không tính toán hash, không có xung đột, không có boxing.
 *   + Cài đặt cực kỳ đơn giản và dễ hiểu.
 * - Nhược điểm:
 *   + Lãng phí bộ nhớ: Khởi tạo ngay lập tức một mảng tĩnh kích thước lớn (10^6 phần tử) bất kể số lượng phần tử thực tế cần lưu là bao nhiêu.
 *   + Ràng buộc dữ liệu: Chỉ khả thi khi tập giá trị đầu vào là số nguyên không âm và có giới hạn trên nhỏ/vừa phải. Không áp dụng được cho kiểu dữ liệu khác như chuỗi hay số thực.
 */

import java.util.LinkedList;

class MyHashSet {
    private final int ARRAY_SIZE = 769; 
    private LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[ARRAY_SIZE];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    
    private int hash(int key) {
        return Math.abs(Integer.hashCode(key)) % ARRAY_SIZE;
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

//// cách 2
class MyHashSet2 {
    private boolean[] exists;

    public MyHashSet2() {
        exists = new boolean[1000001]; // Theo đề bài, key trong khoảng [0, 10^6]
    }
    
    public void add(int key) {
        exists[key] = true;
    }
    
    public void remove(int key) {
        exists[key] = false;
    }
    
    public boolean contains(int key) {
        return exists[key];
    }
}