package com.yanlaoge.kruskal;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.text.EditorKit;
import java.rmi.MarshalException;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

/**
 * @ClassName: KruskalCase
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/26 15:39
 **/
public class KruskalCase {

    private int edgeNum;  // 边的个数
    private char[] vertexs; // 顶点数组
    private int[][] matrix;  // 邻接矩阵
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.showMatrix();

//        EData[] edges = kruskalCase.getEdges();
//        System.out.println(Arrays.toString(edges));
//        kruskalCase.sortEdges(edges);
//        System.out.println("排序之后");
//        System.out.println(Arrays.toString(edges));

        kruskalCase.kruskal();
    }

    public void kruskal(){
        //准备
        int index = 0; // 表示最后结果的数组的索引
        int[] ends = new int[edgeNum]; // 用于保存"已有最小生成树" 中每个顶点在最小生成树中的终点
        //创建结果集
        EData[] res = new EData[edgeNum];
        //获取图中 所有边的集合,
        EData[] edges = getEdges();
        System.out.println(Arrays.toString(edges));
        //进行排序
        sortEdges(edges);
//        System.out.println(Arrays.toString(edges));
        //遍历edges数组,将边值最小的添加到最小生成树,在添加时,判时是否为回路,
        for (int i = 0; i <edgeNum; i++) {
            //获取第一条边的顶点(起点)
            int p1 = getPostion(edges[i].start);
            //获取第一条边的第二个顶点
            int p2 = getPostion(edges[i].end);
            //获取第一个顶点在ends数组中的终点
            int m = getEnd(ends, p1);
            //获取第二个顶点在ends数组中的终点
            int n = getEnd(ends, p2);
            //判断两个终点是否相同,也就是是否构成回路
            if(m != n) {
                //如果不相同就
                //修改m的终点为n  例如: <E,F> [0,0,0,0,5,0,0,0,0,0,0]  5 代表的是n
                //不用设置m的终点, 因为在获取end时,做了判断, 如果为0就返回自身
                ends[m] = n;
                // 添加到到结果集
                res[index++] = edges[i];
            }
        }

        System.out.println("结果: " + Arrays.toString(res));

    }

    /**
     * @Description:  对边进行排序
     * @Date:  2019/11/26
     * @Param: [edatas]
     * @return:  void
     */
    private void sortEdges(EData[] edatas){
        for (int i = 0; i < edatas.length -1; i++) {
            for (int j = 0; j < edatas.length -1 -i; j++) {
                if(edatas[j].weight > edatas[j+1].weight){
                    EData temp = edatas[j];
                    edatas[j] = edatas[j+1];
                    edatas[j+1] = temp;
                }
            }
        }
    }

    /**
     * @Description:  获取图中额边,放到EData[] 数组中
     * @Date:  2019/11/26
     * @Param: []
     * @return:  com.yanlaoge.kruskal.EData[]
     */
    public EData[] getEdges(){
        //创建一个个数组,存放
        EData[] edges = new EData[edgeNum];
        //数组的下标
        int index = 0;
        for (int i = 0; i <matrix.length; i++) {
            for (int j = i+1; j < matrix.length; j++) { // i+1 不和自己比较
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j],matrix[i][j]);
                }
            }
        }

        return edges;
    }


    /**
     * @Description:  获取下标为i的顶点的终点,用于判断终点是否相同
     * @Date:  2019/11/26
     * @Param: ends 数组记录的是各个顶点对应的终点是哪个,ends数组在遍历中,逐步形成
     * @Param: i  顶点对应的下标
     * @return:  int
     */
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    /**
     * @Description:  根据传入的顶点,返回顶点的下边,找不到返回-1
     * @Date:  2019/11/26
     * @Param: [ch]
     * @return:  int
     */
    public int getPostion(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch){
                return i;
            }
        }
        return  -1;
    }

    /**
     * @Description:  初始化工作
     * @Date:  2019/11/26
     * @Param: [vertexs, martrix]
     * @return:
     */
    public KruskalCase(char[] vertexs,int[][] martrix){
        int len = vertexs.length;
        this.vertexs  = new char[len];
        //初始化顶点数组
        for (int i = 0; i < len; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化邻接矩阵
        this.matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.matrix[i][j] = martrix[i][j];
            }
        }

        //统计边
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if(this.matrix[i][j] != INF){
                    this.edgeNum ++;
                }
            }
        }
    }

    /**
     * @Description:  显示临街矩阵
     * @Date:  2019/11/26
     * @Param: []
     * @return:  void
     */
    public void showMatrix(){
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
}


class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                "> = " + weight +
                '}';
    }
}