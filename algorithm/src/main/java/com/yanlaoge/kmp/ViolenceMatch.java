package com.yanlaoge.kmp;

/**
 * @ClassName: ViolenceMatch
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/24 19:39
 **/
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "鬼鬼鬼 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s2 = "尚硅谷你尚硅你";
        int i = violenceMatch(s1, s2);
        System.out.println(i);
    }

    public static int violenceMatch(String str1,String str2){
        //转为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        //两个的长度
        int s1Len = s1.length;
        int s2Len = s2.length;

        //两个下标
        int i = 0;
        int j = 0;

        //循环比较
        while (i< s1Len && j < s2Len){
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else{
                //让i回到匹配成功的下一个位置
                i = i - (j-1);
                //j重置
                j=0;
            }
        }

        if(j==s2Len){
            return i - j ;
        }
        return -1;
    }
}
