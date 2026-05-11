import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        HashMap<String, List<String>> myMap = new HashMap<>();
        int[] count = new int[26];
        StringBuilder sb = new StringBuilder();
        String key = "";

        for(String str: strs){
            count = new int[26];
            for(char character: str.toCharArray()){
                count[character - 'a']++;
            }

            sb = new StringBuilder();
            for(int value: count){
                sb.append('#');
                sb.append(value);
            }
            key = sb.toString();

            if(!myMap.containsKey(key)){
                myMap.put(key, new ArrayList<>());
            }
            
            myMap.get(key).add(str);
        }
        return new ArrayList<>(myMap.values());
    }
}
