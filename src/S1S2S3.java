/**
 * @Description 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
 * @Author Create by junwei.liang on 2020/3/11
 */
public class S1S2S3 {
    static final String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
    static final char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();

    static boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

    public static void main(String[] args) {
        final boolean interleave = isInterleave(c1, c2, c3);
        System.out.println(interleave);
        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                System.out.print((dp[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInterleave(char[] s1, char[] s2, char[] s3) {
        if (s3.length != s1.length + s2.length) {
            return false;
        }
        if (s1.length == 0) {
            return s2 == s3;
        }
        if (s2.length == 0) {
            return s1 == s3;
        }
        dp[0][0] = true;
        for (int i = 1; i <= s1.length; i++) {
            dp[i][0] = dp[i - 1][0] && (s3[i - 1] == s1[i - 1]);
        }
        for (int i = 1; i <= s2.length; i++) {
            dp[0][i] = dp[0][i - 1] && (s3[i - 1] == s2[i - 1]);
        }
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                int t = i + j;
                if (s1[i - 1] == s3[t - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
                if (s2[j - 1] == s3[t - 1]) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        return dp[s1.length][s2.length];

    }

}
