import java.lang.reflect.AnnotatedArrayType;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName: BubbleSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/13 18:32
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3 , 9 ,-1 ,10 ,-2};

        bubbleSort(arr);


//        for (int j = 0;j< arr.length -1 ;j++){
//            if(arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//
//        for (int j = 0;j< arr.length -1 -1 ;j++){
//            if(arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//
//        for (int j = 0;j< arr.length -1 -2 ;j++){
//            if(arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//
//        for (int j = 0;j< arr.length -1 -3 ;j++){
//            if(arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        //外循环数组-1次,就是把所有的数都排序了, 因为最后一个数, 已经不用排序, 所以-1
        for (int i = 0; i < arr.length -1 ; i++) {
            //内排序依次, 就得出一次最大的数,
            for (int j = 0;j< arr.length -1 -i ;j++){
                //当前数, 大于后一个数, 就互换
                if(arr[j] > arr[j+1]){
                    //优化:如果一次循环结束, 没有进入判断, 就结束循环
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
            if(flag){
                flag = false;
            }else{
                break;
            }
        }
    }
}
