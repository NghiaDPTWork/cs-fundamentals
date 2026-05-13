class Solution {
    public int[] sortArray(int[] nums) {
        // Base case: Nếu mảng chỉ còn 1 phần tử hoặc rỗng, trả về luôn (đã xong)
        if (nums.length <= 1) return nums;

        // 1. Tìm vị trí ở giữa để chia đôi
        int mid = nums.length / 2;

        // 2. Chia mảng thành 2 nửa: Left và Right
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        // 3. Đệ quy: Tiếp tục chia nhỏ và sắp xếp từng nửa
        // Đây chính là lúc ta "xếp bài" cho từng phần nhỏ
        left = sortArray(left);
        right = sortArray(right);

        // 4. Trộn (Merge) hai nửa đã sắp xếp lại với nhau
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0; // Con trỏ cho mảng left
        int j = 0; // Con trỏ cho mảng right
        int k = 0; // Con trỏ cho mảng kết quả

        // So sánh và nhặt lá bài nhỏ hơn bỏ vào mảng result
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Nếu mảng left vẫn còn phần tử (trong khi right đã hết)
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Nếu mảng right vẫn còn phần tử (trong khi left đã hết)
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}