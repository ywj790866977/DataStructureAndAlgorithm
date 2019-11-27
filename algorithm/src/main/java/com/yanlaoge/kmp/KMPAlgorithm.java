package com.yanlaoge.kmp;

import java.util.Arrays;

/**
 * @ClassName: KMPAlgorithm
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/25 9:21
 **/
public class KMPAlgorithm  {


    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int i = kmpSearch(str1, str2, next);
        System.out.println(i);

    }

    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j=0; i < str1.length(); i++) {
            //两个字符不相等的情况下, 改变j的位置
            //核心修改
            while (j>0 && str1.charAt(i) != str2.charAt(j) ){
                j = next[j-1];
            }

            //判断,如果字符相等,j移动
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            //如果j == str2.length,则说明完成了
            if(j == str2.length()){
                return i - j +1; // 因为最后一次遍历,i并没有++, 所以需要+1
            }
        }
        return -1;
    }


    public static int[] kmpNext(String dest){
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //sh如果字符串长度为1,部分匹配值就是0
        for (int i=1,j=0; i <dest.length() ; i++) { //i=1, 从1下标开始, 因为前面0已经设置了 ,j 代表的是匹配值
            //当dest.charAt(i) != dest.charAt(j)时,需要从next[j-1]获取新的j
            //直到dest.charAt(i) == dest.charAt(j)才退出
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1]; //取上一个j的匹配值
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
