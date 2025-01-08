class Solution {
    public String largestGoodInteger (String num){
        int n = num.length();
        String res = "";
        for (int i = 0; i < n - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i + 1) == num.charAt(i + 2)) {
                String current = num.substring(i, i + 3);
                if (res.compareTo(current) < 0) {
                    res = current;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.largestGoodInteger("2300019"));
    }
}