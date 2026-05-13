class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Bước 1: Tìm vị trí chốt (pivot) sau khi đã phân đoạn
            int pivotIndex = partition(arr, low, high);

            // Bước 2: Đệ quy sắp xếp nửa bên trái pivot
            quickSort(arr, low, pivotIndex - 1);

            // Bước 3: Đệ quy sắp xếp nửa bên phải pivot
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        // Chọn phần tử cuối cùng làm Pivot
        int pivot = arr[high];
        int i = (low - 1); 
        // Chỉ số của phần tử nhỏ hơn pivot

        for (int j = low; j < high; j++) {
            // Nếu phần tử hiện tại nhỏ hơn hoặc bằng pivot
            if (arr[j] <= pivot) {
                i++;
                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Đưa Pivot về đúng vị trí giữa (i + 1)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}