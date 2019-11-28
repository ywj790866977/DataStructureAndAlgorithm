package com.yanlaoge.floyd;

import java.security.spec.DSAGenParameterSpec;
import java.util.Arrays;

/**
 * @ClassName: FloydAlogrithm
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/27 16:11
 **/
public class FloydAlogrithm {
    public static void main(String[] args) {
        char[] vertex ={'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] vertex; //顶点数组
    private int[][] dis;   // 保存各个顶点出发到其他顶点的距离
    private int[][] pre;   // 保存到达目标的前驱顶点

    public Graph(int len ,int[][] matrix,char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[len][len];
        //初始化pre表,默认值就是当前下标,也就是自己
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    /**
     * @Description:  弗洛伊德算法
     * @Date:  2019/11/27
     * @Param: []
     * @return:  void
     */
    public void floyd(){
        int len = 0; // 保存距离值
        //遍历中间顶点
        for (int k = 0 ; k< dis.length;k++){
            //遍历起始顶点
            for (int i = 0; i < dis.length; i++) {
                //遍历到达姐顶点
                for (int j = 0; j < dis.length; j++) {
                    //获取len
                    len = dis[i][k] + dis[k][j] ;// 从i求经过k的到j 路径值
                    //判断当前经过的路径值是否小于前一次值,如果小于, 就重新赋值
                    if(len < dis[i][j]){
                        //更新路径值
                        dis[i][j] = len;
                        //更新前驱表
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }


    /**
     * @Description:  显示
     * @Date:  2019/11/27
     * @Param: []
     * @return:  void
     */
    public void show(){
        char[] vertex ={'A','B','C','D','E','F','G'};
        for (int k = 0; k< dis.length; k++){
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"路径"+dis[k][i]+ ") ");
            }
            System.out.println();
            System.out.println();
        }
    }
}