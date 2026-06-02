class Solution {
    public void sortColors(int[] nums) {
        // Gọi hàm Quick Sort trên toàn bộ mảng
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] arr, int start, int end) {
        // ĐIỀU KIỆN DỪNG: Khi vùng xét chỉ có 1 phần tử hoặc rỗng
        if (start >= end) {
            return;
        }

        // BƯỚC 1: Phân đoạn (Partition) - Tìm vị trí chốt
        int pivotIndex = partition(arr, start, end);

        // BƯỚC 2: Đệ quy xử lý nửa bên trái của chốt
        quickSort(arr, start, pivotIndex - 1);

        // BƯỚC 3: Đệ quy xử lý nửa bên phải của chốt
        quickSort(arr, pivotIndex + 1, end);
    }

    private int partition(int[] arr, int start, int end) {
        // Chọn phần tử cuối cùng của đoạn làm Pivot (Chốt)
        int pivot = arr[end];

        // boundary: "Cái cọc" đánh dấu ranh giới của nhóm nhỏ hơn hoặc bằng pivot
        int boundary = start - 1;

        // Duyệt current từ đầu đến sát cuối (trước vị trí pivot)
        for (int current = start; current < end; current++) {
            // FLOW: Nếu thấy số nhỏ hơn hoặc bằng chốt
            if (arr[current] <= pivot) {
                boundary++; // Mở rộng biên giới sang phải
                swap(arr, boundary, current); // Đưa số đó về khu vực bên trái
            }
        }

        // Đưa Pivot về đúng vị trí trung tâm (ngay sau boundary)
        int finalPivotIndex = boundary + 1;
        swap(arr, finalPivotIndex, end);

        return finalPivotIndex; // Trả về vị trí để hàm cha chia đôi tiếp
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}