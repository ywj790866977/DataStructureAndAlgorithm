import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName: RadixSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/16 22:15
 **/
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53,3,4,342,555,13,214};
        radixSort(arr);
    }

    public static void  radixSort(int[] arr){

        //获取最大的位数
        int max = arr[0];
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //位数长度
        int maxLength = (max+"").length();

        //定义一个二维数组,标识10个桶,每个桶就是一个数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶实际存放了多少个数据.顶一个数组来记录各个桶每次放入数据的个数
        int[] buketElementCounts = new int[10];

        //遍历位数
        for (int i = 0,n = 1; i <maxLength ; i++,n*=10) {
            // 第一轮,取个位数
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][buketElementCounts[digitOfElement]] = arr[j];
                buketElementCounts[digitOfElement] ++;
            }

            //按照桶的顺序取出数,放回数组
            int index = 0;
            for (int k = 0;k < buketElementCounts.length; k++){
                //判断桶中是否有数
                if(buketElementCounts[k] != 0){
                    //有.就遍历桶
                    for(int l = 0;l < buketElementCounts[k];l++){
                        //添加到原数组
                        arr[index++] = bucket[k][l];
                    }
                }
                //将每个桶里的数放回去之后, 将每个桶的初始化;
                //方便后面使用
                buketElementCounts[k] = 0;
            }

            System.out.println(Arrays.toString(arr));
        }



    /*
        // 第一轮,取个位数
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] % 10;
            bucket[digitOfElement][buketElementCounts[digitOfElement]] = arr[j];
            buketElementCounts[digitOfElement] ++;
        }

        //按照桶的顺序取出数,放回数组
        int index = 0;
        for (int k = 0;k < buketElementCounts.length; k++){
            //判断桶中是否有数
            if(buketElementCounts[k] != 0){
                //有.就遍历桶
                for(int l = 0;l < buketElementCounts[k];l++){
                    //添加到原数组
                    arr[index++] = bucket[k][l];
                }
            }
            //将每个桶里的数放回去之后, 将每个桶的初始化;
            //方便后面使用
            buketElementCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));


        // 第2轮,取个位数
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] /10 % 10;
            bucket[digitOfElement][buketElementCounts[digitOfElement]] = arr[j];
            buketElementCounts[digitOfElement] ++;
        }

        //按照桶的顺序取出数,放回数组
        index = 0;
        for (int k = 0;k < buketElementCounts.length; k++){
            //判断桶中是否有数
            if(buketElementCounts[k] != 0){
                //有.就遍历桶
                for(int l = 0;l < buketElementCounts[k];l++){
                    //添加到原数组
                    arr[index++] = bucket[k][l];
                }
            }
            //将每个桶里的数放回去之后, 将每个桶的初始化;
            //方便后面使用
            buketElementCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));

        // 第3轮,取个位数
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] /100 % 10;
            bucket[digitOfElement][buketElementCounts[digitOfElement]] = arr[j];
            buketElementCounts[digitOfElement] ++;
        }

        //按照桶的顺序取出数,放回数组
        index = 0;
        for (int k = 0;k < buketElementCounts.length; k++){
            //判断桶中是否有数
            if(buketElementCounts[k] != 0){
                //有.就遍历桶
                for(int l = 0;l < buketElementCounts[k];l++){
                    //添加到原数组
                    arr[index++] = bucket[k][l];
                }
            }
            //将每个桶里的数放回去之后, 将每个桶的初始化;
            //方便后面使用
            buketElementCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));

     */
    }
}
