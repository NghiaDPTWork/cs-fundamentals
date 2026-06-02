/**
 * [LeetCode 169] Majority Element
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Thuật toán bầu phiếu Boyer-Moore (Boyer-Moore Voting Algorithm)
 * - Ý tưởng: Duyệt qua mảng và duy trì một phần tử ứng cử viên (candidate) cùng biến đếm số phiếu (count).
 *            Nếu count == 0, chọn phần tử hiện tại làm ứng cử viên mới. 
 *            Nếu gặp phần tử giống ứng cử viên thì count++, ngược lại thì count--. 
 *            Vì phần tử đa số luôn xuất hiện nhiều hơn N/2 lần, nên ứng cử viên cuối cùng sẽ là phần tử đa số cần tìm.
 * - Ưu điểm:
 *   + Tối ưu thời gian tuyệt đối: Độ phức tạp thời gian là O(N) vì chỉ cần duyệt qua mảng đúng 1 lần.
 *   + Tối ưu bộ nhớ tuyệt đối: Độ phức tạp không gian là O(1) do không sử dụng thêm cấu trúc dữ liệu phụ nào.
 * - Nhược điểm:
 *   + Ý tưởng thuật toán có tính đặc thù cao và khó nghĩ ra ngay từ đầu nếu chưa từng biết qua.
 *   + Yêu cầu ràng buộc: Thuật toán chỉ hoạt động đúng khi chắc chắn có phần tử đa số (xuất hiện > N/2 lần).
 * 
 * CÁCH 2: Sắp xếp mảng (Sorting Approach)
 * - Ý tưởng: Sắp xếp mảng theo thứ tự tăng dần. Vì phần tử đa số xuất hiện nhiều hơn N/2 lần trong mảng,
 *            nên sau khi sắp xếp, phần tử ở vị trí chính giữa mảng (nums.length / 2) chắc chắn luôn luôn là phần tử đa số đó.
 * - Ưu điểm:
 *   + Cực kỳ dễ hiểu và cài đặt ngắn gọn (chỉ mất 1-2 dòng code).
 * - Nhược điểm:
 *   + Hiệu năng trung bình: Độ phức tạp thời gian là O(N log N) do chi phí của thuật toán sắp xếp (Arrays.sort()), 
 *     chậm hơn đáng kể so với O(N) của cách 1 khi mảng đầu vào có kích thước lớn.
 *   + Làm thay đổi vị trí các phần tử trong mảng gốc (nếu muốn giữ nguyên mảng gốc thì phải tốn thêm bộ nhớ O(N) để nhân bản mảng).
 */

import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {
        
        int presistence = 0;
        int len = nums.length;
        int value = 0;
        int idx = 0;

        while(idx <= len - 1){
            if(value == 0){
                presistence = nums[idx];
            }
            if(nums[idx] == presistence){
                value++;
            }else{
                value--;
            }
            idx++;
        }

        return presistence;
    }
}

//// cách 2
class Solution2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        return nums[nums.length/2];
    }
}
