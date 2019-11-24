package com.yanlaoge.knapsackproblem;

/**
 * @ClassName: KnapsackProlem
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/24 17:20
 **/
public class KnapsackProlem {
    public static void main(String[] args) {
        int[] w = {1,4,3};  //物品重量
        int[] val = {1500,3000,2000};  // 物品价值
        int m = 4;  // 背包重量
        int n = val.length; // 物品个数

        //创建二维数组
        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大值, 简单讲就是上一个类型,能放的最大值
        int[][] v = new int[n+1][m+1];
        // 记录商品放入情况数组
        int[][] path = new int[n+1][m+1];

        //将第一个行和第一列置为0
        //默认为0,不用处理也可以
        for (int i = 0; i <v.length ; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }


        //开始处理
        //从1开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j <v[0].length; j++) {
                if(w[i-1]>j){//因为是从1开始, 如果取w数组的第一个应该要-1
                    //当放入的商品大于当前背包重量,就直接使用上一个策略
                    v[i][j] = v[i-1][j];
                }else{
                    //v[i][j] = Math.max(v[i-1][j],val[i-1] +v[i-1][j-w[i-1]]);
                    if(v[i-1][j] < v[i-1][j] + val[i-1] +v[i-1][j-w[i-1]]){
                        //插入后面的商品
                        v[i][j] = v[i-1][j] + val[i-1] +v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //打印一下
        //需要逆向遍历, 因为前面每次放入时都会path插入, 而我们只想要最后一次的
        int i = path.length -1;
        int j = path[0].length -1;
        while (i>0 && j>0){
            if(path[i][j] ==1){
                System.out.println("第"+i+"个商品放入背包");
                j -= w[i-1];
            }
            i--;
        }
    }
}
