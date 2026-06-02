/**
 * Kiểm tra phần tử trùng lặp trong khoảng cách k (Contains Duplicate II)
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng Cửa sổ trượt kết hợp HashSet (Sliding Window & HashSet)
 * - Ý tưởng: Duyệt qua mảng và lưu các phần tử vào một HashSet. Tại mọi thời điểm, kích thước của HashSet không vượt quá k.
 *            Nếu kích thước vượt quá k, ta loại bỏ phần tử ở ngoài cửa sổ (chỉ số i - k). 
 *            Nếu gặp phần tử đã tồn tại trong HashSet, trả về true.
 * - Ưu điểm:
 *   + Tiết kiệm không gian: Độ phức tạp không gian là O(min(N, k)) do ta chỉ duy trì tối đa k phần tử trong HashSet tại bất kỳ thời điểm nào.
 * - Nhược điểm:
 *   + Thao tác thêm/xóa khỏi HashSet liên tục mỗi khi cửa sổ trượt dịch chuyển.
 * 
 * CÁCH 2: Sử dụng HashMap lưu chỉ số cuối cùng (HashMap - Last Index Mapping)
 * - Ý tưởng: Sử dụng một HashMap để lưu cặp giá trị và chỉ số xuất hiện cuối cùng của nó {nums[i] -> i}.
 *            Khi duyệt qua nums[i], nếu nums[i] đã tồn tại trong HashMap, ta kiểm tra hiệu chỉ số hiện tại và chỉ số cũ. 
 *            Nếu hiệu số <= k, trả về true. Ngược lại, cập nhật chỉ số mới của nums[i] vào Map.
 * - Ưu điểm:
 *   + Cài đặt trực quan, không cần quản lý cửa sổ trượt chủ động (không cần xóa phần tử khỏi Map).
 * - Nhược điểm:
 *   + Tốn bộ nhớ hơn: Độ phức tạp không gian là O(N) trong trường hợp xấu nhất vì HashMap lưu trữ mọi phần tử duy nhất trong mảng.
 */

import java.util.HashSet;
import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();

        for(int i = 0; i <= nums.length - 1; i++){
            if(window.contains(nums[i])){
                return true;
            }

            window.add(nums[i]);

            if(window.size() > k){
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}

//// cách 2
class Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> lastIndexMap = new HashMap<>();

        for (int i = 0; i <= nums.length - 1; i++) {
            if (lastIndexMap.containsKey(nums[i])) {
                if (i - lastIndexMap.get(nums[i]) <= k) {
                    return true;
                }
            }
            lastIndexMap.put(nums[i], i);
        }
        return false;
    }
}