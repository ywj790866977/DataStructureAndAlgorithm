package com.yanlaoge.prim;

import javax.sound.midi.Soundbank;
import java.time.chrono.IsoChronology;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * @ClassName: PrimAlogrithm
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/26 9:44
 **/
public class PrimAlogrithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //1000表示不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建MGruph对象
        MGraph mGraph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.show(mGraph);
        minTree.prim(mGraph,1);
    }
}

class MinTree{

    /**
     * @Description:  创建邻接矩阵
     * @Date:  2019/11/26
     * @Param: graph 图对象
     * @Param: vertx 图对应的顶点个数
     * @Param: data  图的各个顶点的值
     * @Param: weight 图的邻接矩阵
     * @return:  void
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight){
        int i , j ;
        for (i = 0;i< verxs;i++){
            graph.data[i] = data[i];
            for(j = 0;j <verxs ;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void show(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @Description:  得到最小生成树
     * @Date:  2019/11/26
     * @Param: graph 图
     * @Param: v 起始顶点
     * @return:  void
     */
    public void prim(MGraph graph,int v){
        //创建一个数组,记录已经访问的节点
        int[] visited = new int[graph.verxs];
        //标记当前节点,置为已访问
        visited[v] = 1;
        //定义一个存储当前最小的边
        int minWeight = 10000;
        //保存下标
        int h1 = 0;
        int h2 = 0;
        //因为prim 算法的结果是 顶点-1 条边, 所以我们需要循环 顶点-1 次,每次得出的就是每天边
        for (int k = 1;k<graph.verxs ; k++) {
            //循环遍历,看那条边离节点近
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //如果找到,就将这个节点保存并输出
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值 : "+ minWeight);
            //输出完之后,需要将h2置为已访问
            visited[h2] = 1;
            //重置minWeight,下次循环使用
            minWeight = 10000;
        }
    }


}

class MGraph{
    int verxs; // 顶点个数
    char[] data; // 节点值
    int[][] weight; //存放边,  邻接矩阵

    public MGraph(int n){
        this.verxs = n;
        data = new char[n];
        weight = new int[n][n];
    }

    @Override
    public String toString() {
        return "MGraph{" +
                "verxs=" + verxs +
                ", data=" + Arrays.toString(data) +
                ", weight=" + Arrays.toString(weight) +
                '}';
    }
}
