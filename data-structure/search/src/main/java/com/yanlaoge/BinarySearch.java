package com.yanlaoge;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BinarySearch
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/17 8:54
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,8,10,10,89,1000,10,1234};
//        int res = binarySearch(arr, 0, arr.length - 1, 10);
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 10);
        System.out.println(integers);
    }


    /**
     * @Description:  查找单个
     * @Date:  2019/11/17
     * @Param: [arr, left, right, findValue]
     * @return:  int
     */
    public static int binarySearch(int[] arr,int left, int right, int findValue){
        int mid = (left + right) /2;
        int midVal = arr[mid];

        //出口
        if(left>right){
            return  -1;
        }

        if(findValue > midVal){
            return binarySearch(arr,mid+1,right,findValue);
        }else if(findValue < midVal){
            return binarySearch(arr,left,mid-1,findValue);
        }else{
            return mid;
        }
    }

    /**
     * @Description:  查找所有
     * @Date:  2019/11/17
     * @Param: [arr, left, right, findValue]
     * @return:  java.util.List<java.lang.Integer>
     */
    public static List<Integer> binarySearch2(int[] arr,int left, int right, int findValue){
        int mid = (left + right) /2;
        int midVal = arr[mid];

        //出口
        if(left>right){
            return  new ArrayList<>();
        }

        if(findValue > midVal){
            return binarySearch2(arr,mid+1,right,findValue);
        }else if(findValue < midVal){
            return binarySearch2(arr,left,mid-1,findValue);
        }else{
            //返回一个数组,将查找到的所有下表都放进去;
            //1. 找到mid索引值的时候,不要立即返回
            //2. 向mid的左边扫描, 将所以满足findValue的元素下表都添加到ArrayList
            //3. 向mid的右边扫描, 将所以满足findValue的元素下表都添加到ArrayList
            //4. 返回ArrayList

            // 创建列表
            List<Integer> resList = new ArrayList<>();
            resList.add(mid);
            // 左边扫描
            int temp = mid -1;
            //循环遍历左边
            while (true){
                //退出条件: 如果 temp移动到最左边, 说明查找玩了
                // 或者,当前数
                if(temp < 0 ){
                    break;
                }
                if(arr[temp]==findValue){
                    resList.add(temp);
                }
                temp--;
            }


            // 右边扫描
            temp = mid +1;
            //循环遍历左边
            while (true){
                //退出条件: 如果 temp移动到最左边, 说明查找玩了
                // 或者,
                if(temp > arr.length -1 ){
                    break;
                }
                if(arr[temp]==findValue){
                    resList.add(temp);
                }
//                resList.add(temp);
                temp++;
            }

            return resList;

        }
    }
}
