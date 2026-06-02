class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char tmp;

        while(left <= right){
            tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}