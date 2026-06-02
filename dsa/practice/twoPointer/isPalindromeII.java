class Solution {
    public boolean validPalindrome(String s) {
        String result = null;
        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }else{
                return isPurePalindrome(s, left + 1, right) || isPurePalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    public boolean isPurePalindrome(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}