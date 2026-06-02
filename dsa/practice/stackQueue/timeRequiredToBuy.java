/**
 * [LeetCode 2073] Time Required to Buy Tickets
 * 
 * PHÂN TÍCH ƯU & NHƯỢC ĐIỂM CỦA 2 CÁCH GIẢI:
 * 
 * CÁCH 1: Mô phỏng bằng Hàng đợi (Queue Simulation)
 * - Ý tưởng: Sử dụng một Queue lưu chỉ số của từng người từ 0 đến n-1. 
 *            Lần lượt lấy người ở đầu hàng đợi ra, giảm số vé cần mua đi 1, tăng tổng thời gian thêm 1 giây.
 *            Nếu người đó là k và đã mua đủ vé (số vé còn lại = 0), trả về tổng thời gian.
 *            Ngược lại, nếu chưa đủ vé thì đưa họ trở lại cuối hàng đợi.
 * - Ưu điểm:
 *   + Trực quan, mô phỏng chính xác hành vi thực tế của bài toán.
 *   + Dễ hiểu, dễ cài đặt và gỡ lỗi.
 * - Nhược điểm:
 *   + Kém hiệu quả về mặt thời gian và không gian khi số vé cần mua (tickets[i]) lớn.
 *     Độ phức tạp thời gian: O(N * max(tickets)) với N là số lượng người và max(tickets) là số vé nhiều nhất của một người.
 *     Độ phức tạp không gian: O(N) để lưu trữ chỉ số trong Queue.
 * 
 * CÁCH 2: Tính toán trực tiếp bằng Toán học (One Pass)
 * - Ý tưởng: Duyệt qua mảng vé một lần duy nhất. Với mỗi người ở vị trí i:
 *            + Nếu i <= k (người đứng trước hoặc chính là k): số giây tối đa họ mua được là Math.min(tickets[i], tickets[k])
 *              vì khi k mua xong vé thứ tickets[k], những người đứng trước k chỉ có thể mua tối đa số vé bằng tickets[k].
 *            + Nếu i > k (người đứng sau k): số giây tối đa họ mua được là Math.min(tickets[i], tickets[k] - 1)
 *              vì khi k mua xong vé cuối cùng, hàng đợi kết thúc ngay lập tức, những người đứng sau k chưa kịp thực hiện lượt mua đó.
 * - Ưu điểm:
 *   + Tối ưu tuyệt đối về tốc độ và bộ nhớ.
 *     Độ phức tạp thời gian: O(N) - duyệt qua mảng đúng một lần.
 *     Độ phức tạp không gian: O(1) - không sử dụng thêm cấu trúc dữ liệu phụ trợ.
 * - Nhược điểm:
 *   + Đòi hỏi tư duy logic toán học để nhận ra quy luật, khó nghĩ ra ngay hơn so với cách mô phỏng.
 */

import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int currentPerson = 0;
        int total = 0;

        for(int i = 0; i <= tickets.length - 1; i++){
            queue.offer(i);
        }

        while(!queue.isEmpty()){
            currentPerson = queue.poll();

            tickets[currentPerson]--;
            total++;

            if(currentPerson == k && tickets[currentPerson] == 0){
                return total;
            }

            if(tickets[currentPerson] > 0){
                queue.offer(currentPerson);
            };
        }
        
        return -1;
    }
}


class Solution2 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                // Người đứng trước k
                time += Math.min(tickets[i], tickets[k]);
            } else {
                // Người đứng sau k
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        
        return time;
    }
}