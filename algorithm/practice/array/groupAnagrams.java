class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> myMap = new HashMap<>();
        char[] formatChar; 
        String format = "";


        for(int i = 0; i <= strs.length - 1; i++){
            formatChar = strs[i].toCharArray(); 
            Arrays.sort(formatChar);
            format = String.valueOf(formatChar);

            myMap.putIfAbsent(format, new ArrayList<>());

            myMap.get(format).add(strs[i]);
        }

        return new ArrayList<>(myMap.values());
    }
}
