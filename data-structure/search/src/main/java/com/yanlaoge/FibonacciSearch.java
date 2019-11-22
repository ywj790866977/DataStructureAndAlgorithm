package com.yanlaoge;

import java.util.Arrays;

/**
 * @ClassName: FibonacciSearch
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/17 14:17
 **/
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibonacciSearch(arr,8));
    }

    //获取一个斐波那契数列
    public static int[] fb(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2 ; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    public static int fibonacciSearch(int[] arr,int key){
        //1.获取k值;
        int low = 0;
        int height = arr.length -1;
        int k = 0;
        int mid = 0;
        // 斐波那契
        int[] f = fb();

        //获取k值
        while (f[k]-1 < height){
            k++;
        }
        //k值可能大于数组长度,进行补齐
        int[] temp = Arrays.copyOf(arr, f[k]);
        //将0替换为数组最后一个数
        for (int i = height+1; i<temp.length ; i++){
            temp[i] = arr[height];
        }

        //2.获取mid值, 进行分割
        while (low <= height){
            mid = low + f[k-1] -1;
            if(key < temp[mid]){
                // 在数组的左边查找
                height = mid -1;
                //k--
                // 全部长度  = 前面元素 + 后面元素
                // f[k] = f[k-1] + f[k-2];
                // 所以需要将k的位置往前移动
                // f[k-1] = f[k-2] + f[k-3];
                // 下次循环 mid = low + f[k-1-1] -1
                k --;
            }else if(key > temp[mid]){
                // 右边查找
                low = mid +1;
                // k-=2;
                // 全部长度  = 前面元素 + 后面元素
                // f[k] = f[k-1] + f[k-2];
                // 要走右边就是 f[k-2]
                // 下次循环 mid = low + f[k-1 -2] -1
                k -=2;
            }else{
                //确定返回的下表
                if(mid <= height){
                    return mid;
                }else   {
                    return height;
                }
            }
        }
        return -1;

    }
}
