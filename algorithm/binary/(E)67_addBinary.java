class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int result = 0, carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while(carry != 0 || i >= 0 || j >= 0){
            result = carry;

            if(i >= 0){
                result += a.charAt(i) - '0';
                i--;
            }

            if(j >= 0){
                result += b.charAt(j) - '0';
                j--;
            }

            sb.append(result%2);
            carry = result/2;
        }

        return sb.reverse().toString();
    }
}