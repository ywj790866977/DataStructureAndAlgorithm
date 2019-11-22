package com.yanlaoge;

/**
 * @ClassName: InsertValueSearch
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/17 10:34
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i <100 ; i++) {
            arr[i] = i+1;
        }
        int res = insertValueSearch(arr, 0, arr.length - 1, 31);
        System.out.println(res);
    }

    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        System.out.println("调用");
        //递归出口
        if(left>right || findVal > arr[arr.length-1] || findVal < arr[0]){
            return  -1;
        }
        // 获取中间值
        int mid = left + (right-left) * (findVal -arr[left]) / (arr[right]-arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            return  insertValueSearch(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return  insertValueSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
