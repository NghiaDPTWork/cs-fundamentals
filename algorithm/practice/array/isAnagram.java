class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];

        char[] charT = t.toCharArray();
        char[] charS = s.toCharArray();

        for(int i = 0; i <= charS.length - 1; i++){
            count[charS[i] - 'a']++;
            count[charT[i] - 'a']--;
        }

        for(int val : count){
            if(val != 0){
                return false;
            }
        }

        return true;
    }
}
