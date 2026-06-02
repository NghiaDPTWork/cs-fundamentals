/**
 * [LeetCode 706] Design HashMap
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Băm chuỗi (Chaining with LinkedList)
 * - Ý tưởng: Sử dụng một mảng có kích thước cố định (chọn số nguyên tố 769 để giảm thiểu xung đột băm) chứa các LinkedList.
 *            Mỗi phần tử trong LinkedList là một cặp key-value dưới dạng mảng hai phần tử int[] {key, value}.
 *            Khi thực hiện put, get, remove, ta tính chỉ số bucket bằng hàm băm (hash) và thao tác trực tiếp trên LinkedList tương ứng.
 * - Ưu điểm:
 *   + Giải quyết xung đột băm hiệu quả: Các phần tử trùng mã băm chỉ đơn giản được thêm vào cuối LinkedList của bucket đó.
 *   + Bộ nhớ linh hoạt: Chỉ cấp phát các Node LinkedList thực tế khi có phần tử được thêm vào, tối ưu hơn mảng tĩnh khổng lồ.
 *   + Không giới hạn cứng dung lượng: Có thể chứa nhiều phần tử hơn ARRAY_SIZE do LinkedList có thể phát triển động liên tục.
 * - Nhược điểm:
 *   + Hiệu suất giảm khi xảy ra xung đột cao: Trong trường hợp xấu nhất, nếu nhiều key bị băm vào cùng một bucket, 
 *     các thao tác put, get, remove sẽ mất thời gian O(K) với K là độ dài danh sách (duyệt tuyến tính), không còn là O(1) tối ưu.
 *   + Tốn chi phí bộ nhớ phụ: Cấu trúc LinkedList của Java tiêu tốn bộ nhớ cho các con trỏ liên kết (next, prev) của từng Node.
 *   + Kích thước mảng cố định: Không hỗ trợ tự động mở rộng kích thước mảng (rehashing/resizing) khi số lượng phần tử tăng lên quá nhiều, 
 *     dẫn đến hệ số tải (load factor) tăng cao và suy giảm hiệu năng.
 * 
 * CÁCH 2: Dò tuyến tính (Open Addressing - Linear Probing)
 * - Ý tưởng: Sử dụng một mảng kích thước cố định (ví dụ 20003, lớn hơn số lượng thao tác tối đa của đề bài) lưu các Entry.
 *            Khi xảy ra xung đột băm (ô đã bị chiếm bởi key khác), ta dịch chuyển sang ô kế tiếp (chỉ số (index + 1) % SIZE) cho đến khi tìm thấy ô trống hoặc tìm thấy key trùng để cập nhật.
 *            Đối với việc xóa (remove), ta đánh dấu ô đó là một Entry đặc biệt DELETED để tránh làm đứt quãng chuỗi dò tuyến tính phía sau.
 * - Ưu điểm:
 *   + Tận dụng tối đa bộ nhớ đệm (Cache Locality): Do các phần tử nằm liên tiếp trên mảng thay vì phân tán qua các con trỏ như LinkedList.
 *   + Tiết kiệm bộ nhớ phụ: Không tốn thêm chi phí bộ nhớ cho cấu trúc Node LinkedList phức tạp.
 * - Nhược điểm:
 *   + Hiện tượng tụ cụm (Primary Clustering): Khi xảy ra xung đột, các phần tử có xu hướng bám tụm lại với nhau tạo thành các khối dài, 
 *     làm tăng đáng kể số phép thử dò tuyến tính trong các thao tác sau.
 *   + Giới hạn dung lượng: Số lượng phần tử tối đa bị giới hạn bởi kích thước mảng. Khi mảng quá đầy, hiệu năng giảm nghiêm trọng và cần resize/rehash.
 */

import java.util.LinkedList;

class MyHashMap {
    private final int ARRAY_SIZE = 769;
    private LinkedList<int[]>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[ARRAY_SIZE];
        for(int i = 0; i < ARRAY_SIZE; i++){
            buckets[i] = new LinkedList<>();
        }
    }
    
    private int hash(int key){
        return Math.abs(Integer.hashCode(key)) % ARRAY_SIZE;
    }

    public void put(int key, int value) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                pair[1] = value;
                return;
            }
        }
        bucket.add(new int[]{key, value});
    }
    
    public int get(int key) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                return pair[1];
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int idx = hash(key);
        LinkedList<int[]> bucket = buckets[idx];

        for(int[] pair: bucket){
            if(pair[0] == key){
                bucket.remove(pair);
                return;
            }
        }
    }
}

//// cách 2
class MyHashMap2 {
    private static class Entry {
        int key;
        int value;
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 20003; // Sử dụng kích thước lớn hơn số lượng thao tác tối đa (10^4) để giảm xung đột
    private Entry[] table = new Entry[SIZE];
    private final Entry DELETED = new Entry(-1, -1);

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int idx = hash(key);
        int deletedIdx = -1;
        
        while (table[idx] != null) {
            if (table[idx].key == key) {
                table[idx].value = value;
                return;
            }
            if (table[idx] == DELETED && deletedIdx == -1) {
                deletedIdx = idx;
            }
            idx = (idx + 1) % SIZE;
        }
        
        if (deletedIdx != -1) {
            table[deletedIdx] = new Entry(key, value);
        } else {
            table[idx] = new Entry(key, value);
        }
    }

    public int get(int key) {
        int idx = hash(key);
        while (table[idx] != null) {
            if (table[idx].key == key) {
                return table[idx].value;
            }
            idx = (idx + 1) % SIZE;
        }
        return -1;
    }

    public void remove(int key) {
        int idx = hash(key);
        while (table[idx] != null) {
            if (table[idx].key == key) {
                table[idx] = DELETED;
                return;
            }
            idx = (idx + 1) % SIZE;
        }
    }
}

