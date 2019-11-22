import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName: InsertSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/14 15:13
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,89};
        insertSort(arr);

        /*
        //带插入的数据
        int insertValue = arr[1];
        //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
        int insertIndex = 1-1;
        //{101,34,119,1,-1,89};
        // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //循环结束, 也就是找到了应该插入的位置
        arr[insertIndex+1] = insertValue;
        System.out.println(Arrays.toString(arr));


        //带插入的数据
        insertValue = arr[2];
        //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
        insertIndex = 2-1;
        //{101,34,119,1,-1,89};
        // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //循环结束, 也就是找到了应该插入的位置
        if(insertIndex +1 != 2){
            arr[insertIndex+1] = insertValue;
        }

        System.out.println(Arrays.toString(arr));


        //带插入的数据
        insertValue = arr[3];
        //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
        insertIndex = 3-1;
        //{101,34,119,1,-1,89};
        // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //循环结束, 也就是找到了应该插入的位置
        if(insertIndex +1 != 3){
            arr[insertIndex+1] = insertValue;
        }

        System.out.println(Arrays.toString(arr));


        //带插入的数据
        insertValue = arr[4];
        //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
        insertIndex = 4-1;
        //{101,34,119,1,-1,89};
        // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //循环结束, 也就是找到了应该插入的位置
        if(insertIndex +1 != 4){
            arr[insertIndex+1] = insertValue;
        }

        System.out.println(Arrays.toString(arr));



        //带插入的数据
        insertValue = arr[5];
        //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
        insertIndex = 5-1;
        //{101,34,119,1,-1,89};
        // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //循环结束, 也就是找到了应该插入的位置
        if(insertIndex +1 != 5){
            arr[insertIndex+1] = insertValue;
        }

        System.out.println(Arrays.toString(arr));

         */

    }

    public static void insertSort(int[] arr){
        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1; i <arr.length ; i++) {
            //带插入的数据
            insertValue = arr[i];
            //插入位置, 因为还没有找出来, 就定为有序列表的第一个位置
            insertIndex = i-1;
            //{101,34,119,1,-1,89};
            // 如果带插入数小于前一个数,那么就移动前一个数的位置,并且将带插入下标减1, 和前一个数继续判断;
            while (insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex--;
            }
            //循环结束, 也就是找到了应该插入的位置
            if(insertIndex +1 != i){
                arr[insertIndex+1] = insertValue;
            }
            System.out.println(Arrays.toString(arr));
        }

    }

}

