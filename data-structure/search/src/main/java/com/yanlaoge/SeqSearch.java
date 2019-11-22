package com.yanlaoge;

/**
 * @ClassName: SeqSearch
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/17 10:10
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr ={1,9,11,-1,34,89};
        int res = seqSearch(arr, 9);
        System.out.println(res);
    }

    public static int seqSearch(int[] arr,int findVal){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==findVal){
                return i;
            }
        }
        return  -1;
    }
}
