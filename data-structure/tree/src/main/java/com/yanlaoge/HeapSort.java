package com.yanlaoge;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName: HeapSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/19 14:21
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] arr= {4,6,8,5,9};
        heapSort(arr);
    }

    /**
     * @Description:  排序
     * @Date:  2019/11/19
     * @Param: [arr]
     * @return:  void
     */
    public static void heapSort(int[] arr) {

        //进行构建大顶堆
        //两次循环从上之下,进行构建
        for(int i = arr.length/2 -1; i>=0;i--){
            adjustHeap(arr,i,arr.length);
            System.out.println(Arrays.toString(arr));
        }
        //排序
        //1.将堆顶值与堆尾值进行互换
        //2.互换后,需要继续构建为大顶堆
        int temp = 0;
        for (int j = arr.length -1;j>0;j-- ){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void adjustHeap(int[] arr ,int i ,int length){
        //i: 就是子树的父节点
        //需要一个变量来保存父节点
        int temp = arr[i];
        //开始构建大顶堆,进行构建的应该是i的左子树
        //第一次循环就是I的左子树
        // i * 2 + 1 = 3  arr[3]的位置就是 arr[1]的子树
        for(int k = i * 2 + 1; k<length; k = k*2+1){
            //先让左右两子树的值进行判断, 如果有变小于左边, 就让k移动
            //如果k+1 < length,限制在子树范围内
            if(k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            //将k值和父节点值进行对比,如果大,就进行替换
            if(arr[k] > temp){
                arr[i] = arr[k];
                //替换之后需要改变i的值为当前的k.然后进行继续循环比较
                //因为在替换完之后, 有可能 被替换的左子树状态会有问题
                i = k;
            }
        }
        //如果循环完,将temp 赋值给i位置
        arr[i] = temp;
    }


}
