/**
 * Bình phương của một mảng đã sắp xếp (Squares of a Sorted Array).
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Hai con trỏ (Two Pointers)
 * - Ý tưởng: Vì mảng đầu vào đã sắp xếp, các phần tử có giá trị tuyệt đối lớn nhất sẽ nằm ở hai đầu mảng (trái hoặc phải).
 *            Ta sử dụng 2 con trỏ `left = 0` và `right = n - 1`. 
 *            So sánh bình phương của hai đầu, điền giá trị lớn hơn vào cuối mảng kết quả mới, rồi dịch con trỏ tương ứng.
 * - Ưu điểm:
 *   + Tối ưu thời gian: O(N) vì chỉ cần duyệt qua mảng đúng một lần.
 * - Nhược điểm:
 *   + Cần sử dụng thêm mảng phụ để lưu kết quả, không gian phụ O(N).
 * 
 * CÁCH 2: Bình phương và Sắp xếp (Square & Sort)
 * - Ý tưởng: Duyệt qua mảng, bình phương từng phần tử tại chỗ. 
 *            Sau đó sử dụng thuật toán sắp xếp mặc định của hệ thống `Arrays.sort()` để sắp xếp lại mảng theo thứ tự tăng dần.
 * - Ưu điểm:
 *   + Cài đặt cực kỳ ngắn gọn và đơn giản.
 * - Nhược điểm:
 *   + Thời gian chạy chậm hơn: O(N log N) do phải thực hiện thao tác sắp xếp mảng.
 */

import java.util.Arrays;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] sortedSquare = new int[n];
        
        int left = 0;
        int right = n - 1;

        for(int i = n - 1; i >= 0; i--){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                sortedSquare[i] = nums[left] * nums[left];
                left++;
            }else{
                sortedSquare[i] = nums[right] * nums[right];
                right--;
            }
        }
        return sortedSquare;
    }
}

//// cách 2
class Solution2 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] sortedSquare = new int[n];
        
        for (int i = 0; i < n; i++) {
            sortedSquare[i] = nums[i] * nums[i];
        }
        
        Arrays.sort(sortedSquare);
        return sortedSquare;
    }
}