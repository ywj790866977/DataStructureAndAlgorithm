import javax.swing.*;
import java.io.Serializable;
import java.time.temporal.Temporal;
import java.util.Arrays;

/**
 * @ClassName: ShellSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/14 16:52
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        shellSort(arr);

        shellSort(arr);

        /*
        int temp =0;
        for (int i = 5; i <arr.length ; i++) {
            for (int j = i -5;j>=0;j-=5){
                if(arr[j] > arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));

        for (int i = 2; i <arr.length ; i++) {
            for (int j = i -2;j>=0;j-=2){
                if(arr[j] > arr[j+2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i <arr.length ; i++) {
            for (int j = i -1;j>=0;j-=1){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));

         */

    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length /2 ; gap > 0 ; gap /= 2){
            for (int i = gap; i <arr.length ; i++) {
                for (int j = i -gap;j>=0;j-=gap){
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }

            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void shellSort2(int[] arr){
        for (int gap = arr.length /2 ; gap > 0 ; gap /= 2){
            for (int i = gap; i <arr.length ; i++) {
                int j = i;
                int temp = arr[j];
                //判断当前数, 是否大于前一个步长的数
                if(arr[j] < arr[j-gap]){
                    // 循环判断, 当前的数, 是否小于前一个步长的数
                    while (j-gap>=0 && temp < arr[j-gap]){
                        //如果小于, 就让大的数等于小的数, 也就是后移
                        arr[j] = arr[j - gap];
                        // 后移之后,将下表再减去步长, 往前移移动,再次进行比较
                        j-= gap;
                    }
                    //循环完之后, 就得到了应该插入的位置
                    arr[j] = temp;
                }

            }
            }
            System.out.println(Arrays.toString(arr));
    }
}
