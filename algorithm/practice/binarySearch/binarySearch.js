/**
 * Binary Search Algorithm implementation in JavaScript.
 * Tìm kiếm nhị phân là thuật toán tìm kiếm hiệu quả trên một MẢNG ĐÃ ĐƯỢC SẮP XẾP.
 * Thuật toán hoạt động bằng cách chia đôi khoảng tìm kiếm liên tục.
 * 
 * Độ phức tạp thời gian (Time Complexity):
 * - Tốt nhất (Best Case): O(1) - phần tử cần tìm nằm ngay giữa mảng ở lượt đầu tiên.
 * - Trung bình (Average Case): O(log n)
 * - Tệ nhất (Worst Case): O(log n)
 * 
 * Độ phức tạp không gian (Space Complexity): O(1) - sử dụng phiên bản lặp (iterative).
 */

/**
 * Tìm kiếm nhị phân phiên bản lặp (Iterative approach)
 * @param {number[]} arr - Mảng đã được sắp xếp tăng dần
 * @param {number} target - Giá trị cần tìm kiếm
 * @returns {number} Chỉ mục (index) của target trong mảng, hoặc -1 nếu không tìm thấy
 */
function binarySearch(arr, target) {
  let left = 0;
  let right = arr.length - 1;

  while (left <= right) {
    // Tránh bị tràn số (overflow) đối với mảng có kích thước cực lớn:
    const mid = Math.floor(left + (right - left) / 2);

    // 1. Nếu tìm thấy phần tử ở giữa
    if (arr[mid] === target) {
      return mid;
    }

    // 2. Nếu target lớn hơn phần tử ở giữa, bỏ qua nửa bên trái
    if (arr[mid] < target) {
      left = mid + 1;
    } 
    // 3. Nếu target nhỏ hơn phần tử ở giữa, bỏ qua nửa bên phải
    else {
      right = mid - 1;
    }
  }

  // Không tìm thấy target trong mảng
  return -1;
}

// --- Ví dụ chạy thử (Sample Usage) ---
const sortedArray = [11, 12, 22, 25, 34, 64, 90]; // MẢNG PHẢI SẮP XẾP SẴN!
const targetToFind = 25;

console.log("Mảng đã sắp xếp:", sortedArray);
console.log(`Đang tìm kiếm giá trị: ${targetToFind}`);

const resultIndex = binarySearch(sortedArray, targetToFind);

if (resultIndex !== -1) {
  console.log(`Tìm thấy ${targetToFind} tại index: ${resultIndex}`);
} else {
  console.log(`Không tìm thấy phần tử ${targetToFind} trong mảng.`);
}
