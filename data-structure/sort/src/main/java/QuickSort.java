import java.util.Arrays;

/**
 * @ClassName: QuickSort
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/15 15:45
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70,23,11,-4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void  quickSort(int[] arr, int left,int right){
        int l = left;
        int r = right;
        //获取中间值
        int pivot  = arr[(l+r)/2];
        int temp = 0;
        //循环
        while (l<r){
            // 左边循环判断是否大于中间值,
            while (arr[l]<pivot){
                l++;
            }
            // 右边循环判断是否小于中间值,
            while (arr[r] > pivot){
                r --;
            }
            // 两个循环结束,如果 左边大于右边了, 那么结束循环
            if(l>=r){
                break;
            }
            // 如果找到, 就将位置进行互换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 位置转换之后, 的值为中间的值的话, 相应的进行++ 或 --,
            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }

        }
        //大循环结束,就已经将数组以中间数,分开了, 但是左右两边并没有顺序
        // 如果l == r , l++ , r-- ,要不然循环退不出来
        if( l == r){
            l++;
            r--;
        }
        // 进行左右两边的递归排序
        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }




}
