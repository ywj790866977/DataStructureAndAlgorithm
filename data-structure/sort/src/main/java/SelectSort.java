import java.util.Arrays;

/**
 * @ClassName: SelectSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/14 14:04
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        selectSort(arr);

        /*
        int minIndex = 0;
        int min = arr[0];
        //循环遍历, 因为第一个数时默认最小数, 所以从第二个数开始
        for (int i = 0 +1; i < arr.length ; i++) {
            // 判断当前的最小数和后面的数,挨个比较, 如果比当前最小数小, 就将值赋值给min, 同时将当前的下表也赋值给minIdex
            if(min > arr[i]){
                min = arr[i];
                minIndex = i;
            }
        }
        //最后将找到的最小值, 和初始值进行交换

        arr[minIndex] = arr[0];  // 将初始值赋值给最小值
        arr[0] = min;  // 将最后得到的最小值,赋值给第一个位置
        System.out.println(Arrays.toString(arr));

        minIndex = 1;
        min = arr[1];
        //循环遍历, 因为第一个数时默认最小数, 所以从第二个数开始
        for (int i = 1 +1; i < arr.length ; i++) {
            // 判断当前的最小数和后面的数,挨个比较, 如果比当前最小数小, 就将值赋值给min, 同时将当前的下表也赋值给minIdex
            if(min > arr[i]){
                min = arr[i];
                minIndex = i;
            }
        }
        //最后将找到的最小值, 和初始值进行交换
        if(minIndex != 1){
            arr[minIndex] = arr[1];  // 将初始值赋值给最小值
            arr[1] = min;  // 将最后得到的最小值,赋值给第一个位置
        }
        System.out.println(Arrays.toString(arr));

        minIndex = 2;
        min = arr[2];
        //循环遍历, 因为第一个数时默认最小数, 所以从第二个数开始
        for (int i = 2 +1; i < arr.length ; i++) {
            // 判断当前的最小数和后面的数,挨个比较, 如果比当前最小数小, 就将值赋值给min, 同时将当前的下表也赋值给minIdex
            if(min > arr[i]){
                min = arr[i];
                minIndex = i;
            }
        }
        //最后将找到的最小值, 和初始值进行交换
        if(minIndex != 2){
            arr[minIndex] = arr[2];  // 将初始值赋值给最小值
            arr[2] = min;  // 将最后得到的最小值,赋值给第一个位置
        }
        System.out.println(Arrays.toString(arr));

         */

    }

    public static void selectSort(int[] arr ){

        for (int i = 0; i <arr.length -1 ; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++){
                if(min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            //优化 如果minIndex 和当前i相等, 也就是没有进行交换就没有比较赋值了
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println(Arrays.toString(arr));
        }


    }
}
