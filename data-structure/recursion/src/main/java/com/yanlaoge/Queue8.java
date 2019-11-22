package com.yanlaoge;

import java.util.zip.CheckedInputStream;

/**
 * @ClassName: Queue8
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/12 16:53
 **/
public class Queue8 {
    // 放置多少个皇后
    int max = 8;
    //放置结果
    int[] array = new int[max];
    //统计多少个方法
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }

    //
    public void check(int n ){
        //如果 n == max 那么说明到最后一个位置了
        if(n == max){
            //打印位置
            print();
            return;
        }
        //依次放置皇后, 并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第一列
            array[n]= i;
            if(judge(n)){
                // 不冲突
                check(n+1);
            }
        }
        

    }

    //放置的时候, 先判断是否和前面放置的冲突; 同一行,同一列,同一斜线
    public boolean judge(int n){
        // 将当前位置和前面的做比较
        // array[n] == array[i]  判断是否和前一个位置在同一列
        // Math.abs(n-i) == Math.abs(array[n] - array[i])  判断是否和前一个在斜线
        // 假如, 现在n是第二个皇后,  值应该是1  n:1
        // 循环第一次
        // Math.abs(n-i)  --> Math.abs(1-0) = 1
        // Math.abs(array[n] - array[i])  --> Math.abs(array[1] - array[0]) --> 1 - 0 = 1
        //
        for (int i = 0; i <n ; i++) {

            if(array[n] == array[i] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //打印摆放的位置
    public void print(){
        count++;
        for (int i = 0; i < array.length ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
