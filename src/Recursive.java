import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 递归计算大数平方和
 * @Author Created by liangjunwei on 2018/8/10 16:35
 */
public class Recursive {

    private static String addStr(String str1, String str2) {
        if (str1 == null)
            return str2;
        if (str2 == null)
            return str1;
        StringBuffer s1 = new StringBuffer(str1).reverse();
        StringBuffer s2 = new StringBuffer(str2).reverse();
        StringBuilder res = new StringBuilder();
        int len1 = s1.length();
        int len2 = s2.length();
        int len;
        if (len1 < len2) {
            len = len2;
            int count = len2 - len1;
            while (count-- > 0)
                s1.append('0');
        } else {
            len = len1;
            int count = len1 - len2;
            while (count-- > 0)
                s2.append('0');
        }
        int overflow = 0;
        int num;
        for (int i = 0; i < len; i++) {
            num = s1.charAt(i) - '0' + s2.charAt(i) - '0' + overflow;
            if (num >= 10) {
                overflow = 1;
                num -= 10;
            } else {
                overflow = 0;
            }
            res.append(String.valueOf(num));
        }
        if (overflow == 1)
            res.append(1);
        return res.reverse().toString();
    }

    private static String multiply(String sn) {
        int length = sn.length();
        //用来存储结果的数组，可以肯定的是两数相乘的结果的长度，肯定不会大于两个数各自长度的和。
        int[] num = new int[length + length];
        //第一个数按位循环
        for (int i = 0; i < length; i++) {
            //得到最低位的数字
            int n1 = sn.charAt(length - 1 - i) - '0';
            //保存进位
            int tmp = 0;
            //第二个数按位循环
            for (int j = 0; j < length; j++) {
                int n2 = sn.charAt(length - 1 - j) - '0';
                //拿出此时的结果数组里存的数+现在计算的结果数+上一个进位数
                tmp = tmp + num[i + j] + n1 * n2;
                //得到此时结果位的值
                num[i + j] = tmp % 10;
                //此时的进位
                tmp /= 10;
            }
            //第一轮结束后，如果有进位，将其放入到更高位
            num[i + length] = tmp;
        }

        int i = length * 2 - 1;
        //计算最终结果值到底是几位数，
        while (i > 0 && num[i] == 0) {
            i--;
        }
        StringBuilder result = new StringBuilder();
        //将数组结果反过来放，符合正常读的顺序，
        //数组保存的是：1 2 3 4 5
        //但其表达的是54321，五万四千三百二十一。
        while (i >= 0) {
            result.append(num[i--]);
        }
        return result.toString();
    }
    private static String subStr(String f, String s) {
        // 将字符串翻转并转换成字符数组
        char[] a = new StringBuffer(f).reverse().toString().toCharArray();
        char[] b = new StringBuffer(s).reverse().toString().toCharArray();
        int lenA = a.length;
        int lenB = b.length;
        // 找到最大长度
        int len = lenA > lenB ? lenA : lenB;
        int[] result = new int[len];
        // 表示结果的正负
        char sign = '+';
        // 判断最终结果的正负
        if (lenA < lenB) {
            sign = '-';
        } else if (lenA == lenB) {
            int i = lenA - 1;
            while (i > 0 && a[i] == b[i]) {
                i--;
            }
            if (a[i] < b[i]) {
                sign = '-';
            }
        }
        // 计算结果集，如果最终结果为正，那么就a-b否则的话就b-a
        for (int i = 0; i < len; i++) {
            int aint = i < lenA ? (a[i] - '0') : 0;
            int bint = i < lenB ? (b[i] - '0') : 0;
            if (sign == '+') {
                result[i] = aint - bint;
            } else {
                result[i] = bint - aint;
            }
        }
        // 如果结果集合中的某一位小于零，那么就向前一位借一，然后将本位加上10。其实就相当于借位做减法
        for (int i = 0; i < result.length - 1; i++) {
            if (result[i] < 0) {
                result[i + 1] -= 1;
                result[i] += 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 如果最终结果为负值，就将负号放在最前面，正号则不需要
        if (sign == '-') {
            sb.append('-');
        }
        // 判断是否有前置0
        boolean flag = true;
        for (int i = len - 1; i >= 0; i--) {
            if (result[i] == 0 && flag) {
                continue;
            } else {
                flag = false;
            }
            sb.append(result[i]);
        }
        // 如果最终结果集合中没有值，就说明是两值相等，最终返回0
        if (sb.toString().equals("")) {
            sb.append("0");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(recursive("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
    }
    private static String recursive(String n) {
        if ("1".equals(n)) {
            return "1";
        } else {
            return addStr(multiply(n + ""), multiply(subStr(n , "1")));
        }
    }
}
