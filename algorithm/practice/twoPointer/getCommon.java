/**
 * Tìm giá trị chung nhỏ nhất (Minimum Common Value).
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Hai con trỏ (Two Pointers)
 * - Ý tưởng: Sử dụng hai con trỏ `i` và `j` lần lượt trỏ ở đầu hai mảng `nums1` và `nums2`.
 *            So sánh hai phần tử tại vị trí con trỏ: 
 *            + Nếu bằng nhau: đó chính là giá trị chung nhỏ nhất (vì hai mảng đã sắp xếp tăng dần), trả về ngay.
 *            + Nếu `nums1[i] < nums2[j]`: phần tử bên `nums1` nhỏ hơn nên không thể là phần tử chung, tăng `i`.
 *            + Ngược lại: tăng `j`.
 * - Ưu điểm:
 *   + Cực kỳ tối ưu khi kích thước hai mảng tương đồng nhau (N ≈ M). Độ phức tạp thời gian O(N + M).
 *   + Tiết kiệm bộ nhớ: O(1) không gian.
 * - Nhược điểm:
 *   + Phải duyệt tuần tự qua các phần tử của cả hai mảng.
 * 
 * CÁCH 2: Tìm kiếm Nhị phân (Binary Search)
 * - Ý tưởng: Duyệt qua từng phần tử của mảng có kích thước nhỏ hơn. Với mỗi phần tử, 
 *            ta thực hiện Tìm kiếm Nhị phân trên mảng có kích thước lớn hơn để kiểm tra sự tồn tại của nó.
 * - Ưu điểm:
 *   + Cực kỳ tối ưu khi kích thước hai mảng chênh lệch rất lớn (ví dụ N << M).
 *     Độ phức tạp thời gian lúc này là O(N log M), tốt hơn nhiều so với O(N + M) của Two Pointers.
 *   + Tiết kiệm bộ nhớ: O(1) không gian phụ.
 * - Nhược điểm:
 *   + Kém hiệu quả hơn Two Pointers khi hai mảng có kích thước xấp xỉ nhau vì chi phí log M cho mỗi phép tìm kiếm.
 */
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int i = 0;
        int j = 0;

        while(i <= len1 - 1 && j <= len2 - 1){
            if(nums1[i] == nums2[j]){
                return nums1[i];
            }else if(nums1[i] < nums2[j]){
                i++;
            }else{
                j++;
            }
        }

        return -1;
    }
}

//// cách 2
class Solution2 {
    public int getCommon(int[] nums1, int[] nums2) {
        // Đảm bảo nums1 là mảng nhỏ hơn để tối ưu số lần tìm kiếm nhị phân
        if (nums1.length > nums2.length) {
            return getCommon(nums2, nums1);
        }

        for (int num : nums1) {
            if (binarySearch(nums2, num)) {
                return num;
            }
        }

        return -1;
    }

    private boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}

