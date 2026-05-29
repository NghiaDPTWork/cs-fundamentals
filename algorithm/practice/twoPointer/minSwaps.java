//  Min Swaps to Group Red Balls.
// Given an array of balls, where each ball is either red (R) or white (W), 
// determine the minimum number of swaps required to group all the red balls together. 
// A swap consists of exchanging the positions of any two balls in the array.
// Example:
// Input: "RRWRRRWWWW"
// Output: 2
// Explanation: We can swap the second 'R' with the first 'W' to get
// "RRWRRRWWWW", and then swap the third 'R' with the second 'W' to get "RRRWRRWWWW".
// Note: The input will be a string consisting of characters 'R' and 'W' only.
public class minSwaps{
    public static int minSwaps(String balls) {
        int totalRedCount = 0;
        for(char ball : balls.toCharArray()) {
            if (ball == 'R') {
                totalRedCount++;
            }
        }

        if (totalRedCount <= 1) {
            return 0;
        }

        int left = 0;
        int right = balls.length() - 1;
        int swapCount = 0;
        int whiteBalls = 0;

        while(left < right){
            if(balls.charAt(left) != 'R'){
                left++;
            }else if(balls.charAt(right) != 'R'){
                right--;
            }else{
                whiteBalls = (right - left - 1) - (totalRedCount - 2);
                swapCount += whiteBalls;

                if(swapCount > 1_000_000_000L){
                    return -1;
                }

                left++;
                right--;
                totalRedCount -= 2;
            }
        }
        return swapCount;
    }

    public static void main(String[] args) {
        String input = "RRWRRRWWWW";
        System.out.println(minSwaps(input)); // Output: 2
        input = "RWRWRWRW";
        System.out.println(minSwaps(input)); // Output: 4
        input = "RRRRR";
        System.out.println(minSwaps(input)); // Output: 0
        input = "WWWWR";
        System.out.println(minSwaps(input)); // Output: 0
        input = "WWWWWRRWWWWRRRRRRRRRRRWWWWRWRWRWWWWWRRRRRR";
        System.out.println(minSwaps(input)); // Output: 89
    }
}

