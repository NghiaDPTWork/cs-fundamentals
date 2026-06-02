/**
 * [LeetCode 1] Two Sum
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Sử dụng HashMap (One-pass Hash Table)
 * - Ý tưởng: Duyệt qua mảng, tính toán giá trị cần tìm (target - nums[i]). 
 *            Nếu giá trị đó đã có trong HashMap, trả về chỉ số ngay lập tức. 
 *            Nếu chưa có, lưu phần tử hiện tại vào HashMap với key là giá trị và value là index.
 * - Ưu điểm:
 *   + Tối ưu thời gian chạy: Độ phức tạp thời gian là O(N) vì chỉ cần duyệt qua mảng đúng 1 lần.
 *   + Việc kiểm tra sự tồn tại của phần tử trong HashMap có độ phức tạp trung bình là O(1).
 * - Nhược điểm:
 *   + Tốn bộ nhớ: Độ phức tạp không gian là O(N) do phải tạo thêm một bảng băm HashMap để lưu trữ.
 * 
 * CÁCH 2: Sử dụng 2 vòng lặp lồng nhau (Brute Force)
 * - Ý tưởng: Duyệt qua mọi cặp số có thể có trong mảng để tìm cặp số có tổng bằng target.
 * - Ưu điểm:
 *   + Tiết kiệm bộ nhớ: Độ phức tạp không gian cực kỳ tối ưu O(1) do không sử dụng thêm bất kỳ cấu trúc dữ liệu phụ nào.
 * - Nhược điểm:
 *   + Chạy chậm: Độ phức tạp thời gian lên tới O(N^2) do có hai vòng lặp lồng nhau.
 *   + Đối với mảng có kích thước rất lớn, cách này dễ dẫn đến lỗi vượt quá giới hạn thời gian (Time Limit Exceeded - TLE).
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> myMap = new HashMap<>();
        int expectValue = 0;

        for(int i = 0; i <= nums.length - 1; i++){
            expectValue = target - nums[i];
            if(myMap.containsKey(expectValue)){
                return new int[] {myMap.get(expectValue), i};
            }
            myMap.put(nums[i], i);
        }
        return new int[] {};
    }
}


//// cách 2 
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        
        for(int i = 0; i <= nums.length - 2; i++){
            for(int j = i + 1; j <= nums.length - 1; j++){
                if(nums[i] + nums[j] == target){
                    return new int[] {i, j};
                }
            }
        }

        return new int[] {};
    }
}