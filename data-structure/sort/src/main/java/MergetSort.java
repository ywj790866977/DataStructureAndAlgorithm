import java.time.chrono.MinguoChronology;
import java.util.Arrays;

/**
 * @ClassName: MergetSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/15 18:38
 **/
public class MergetSort {

    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];

        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    //拆分+合并
    public static void mergeSort(int[] arr, int left ,int right,int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }


    public static void merge(int[] arr,int left ,int mid, int right, int[] temp){
        //右侧下标
        int i = left;
        //左侧下标
        int j = mid +1;
        //temp 下标
        int t = 0;


        //1.对比两组数, 将小的依次拷贝到temp
        //拷贝之后下标后移
        while (i<=mid && j <=right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
                t++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2.上面执行完之后,可能两组会有剩余的,将其拷贝到temp
        while (i<=mid){
            temp[t] = arr[i];
            i++;
            t++;
        }

        while (j<=right){
            temp[t] = arr[j];
            j++;
            t++;
        }

        //3.将temp的元素拷贝到arr
        //每次拷贝时, 其实, 都不一样
        //第一次: tempLeft = 0, right = 1;
        //最后一次: tempLeft = 0 ,right = 7;
        t = 0;
        int tempLeft = left;  //每次分割,合并都不一样.
        System.out.println("left:"+tempLeft);
        System.out.println("right:"+right);
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t ++;
            tempLeft ++;
        }
    }
}
