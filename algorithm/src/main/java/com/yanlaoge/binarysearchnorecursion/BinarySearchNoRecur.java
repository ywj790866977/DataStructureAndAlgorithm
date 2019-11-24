package com.yanlaoge.binarysearchnorecursion;

/**
 * @ClassName: BinarySearchNoRecur
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/22 15:00
 **/
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr, 1);
        System.out.println("下标: "+ index);
    }


    /**
     * @Description:  非递归二分查找
     * @Date:  2019/11/22
     * @Param: [arr, target]
     * @return:  int
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while (left <=right){
            int mid = (left + right) /2;
            if(arr[mid] == target){
                return mid;
            }else if(target < arr[mid]){
                right = mid -1;
            }else if(target > arr[mid]){
                left = mid +1;
            }
        }
        return -1;
    }
}
