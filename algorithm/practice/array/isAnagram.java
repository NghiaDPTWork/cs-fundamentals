import java.util.Arrays;

class Solution {
    public boolean isAnagram(String s, String t) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        s = new String(charArray);
        charArray = t.toCharArray();
        Arrays.sort(charArray);
        t = new String(charArray);

        return s.equals(t);
    }
}
