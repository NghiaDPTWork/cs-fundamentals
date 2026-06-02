/**
 * Bubble Sort Algorithm implementation in JavaScript.
 * Sắp xếp nổi bọt là thuật toán sắp xếp đơn giản hoạt động bằng cách liên tục
 * hoán đổi các phần tử kề nhau nếu chúng không đúng thứ tự.
 *
 * Độ phức tạp thời gian (Time Complexity):
 * - Tốt nhất (Best Case): O(n) - khi mảng đã được sắp xếp sẵn (sử dụng tối ưu hóa `swapped`).
 * - Trung bình (Average Case): O(n^2)
 * - Tệ nhất (Worst Case): O(n^2)
 *
 * Độ phức tạp không gian (Space Complexity): O(1) - sắp xếp tại chỗ (in-place).
 */

function bubbleSort(arr) {
  const n = arr.length;

  // Duyệt qua tất cả các phần tử của mảng
  for (let i = 0; i < n - 1; i++) {
    // Biến tối ưu hóa: kiểm tra xem có sự hoán đổi nào xảy ra ở lượt này không
    let swapped = false;

    // Các phần tử cuối cùng đã được sắp xếp đúng vị trí, nên giảm giới hạn vòng lặp trong
    for (let j = 0; j < n - i - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        // Hoán đổi hai phần tử arr[j] và arr[j+1]
        let temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;

        swapped = true;
      }
    }

    // Nếu không có phần tử nào được hoán đổi trong vòng lặp trong, mảng đã được sắp xếp
    if (!swapped) break;
  }

  return arr;
}

// --- Ví dụ chạy thử (Sample Usage) ---
const unsortedArray = [64, 34, 25, 12, 22, 11, 90];
console.log("Mảng chưa sắp xếp:", unsortedArray);

// Dùng spread operator để không thay đổi mảng gốc
const sortedArray = bubbleSort([...unsortedArray]);
console.log("Mảng sau khi sắp xếp nổi bọt:", sortedArray);
