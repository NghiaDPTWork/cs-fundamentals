public class isPalindrome {
    
}
class Solution {
    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int len = str.length();
        int left = (len - 1) / 2;
        int right = len / 2;

        if (len <= 1) return true;

        String result = processPalindrome(str, left, right);

        return len == result.length();
    }

    public String processPalindrome(String s, int left, int right){
        while(left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
    
}
