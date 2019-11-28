package com.yanlaoge.dijkstra;

import java.awt.geom.Area;
import java.rmi.MarshalException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @ClassName: DijkstraAlgorithm
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/27 9:22
 **/
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexs ={'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertexs.length][vertexs.length];
        final int N = 65535;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertexs, matrix);
        graph.show();
        graph.dsj(6);
        graph.showDijkstra();
    }
}

class Graph{
    private char[] vertexs;
    private int[][] matrix;
    private  VisitedVertex vv;

    public Graph(char[] vertexs, int[][] matrix){
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    public void show(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showDijkstra(){
        vv.show();
    }

    /**
     * @Description:  迪杰斯特拉
     * @Date:  2019/11/27
     * @Param: [index]
     * @return:  void
     */
    public void dsj(int index){
        vv = new VisitedVertex(vertexs.length, index);
        //更新当前节点
        update(index);
        //循环遍历所有顶点
        for (int i = 0; i <vertexs.length ; i++) {
            //获取新的访问顶点
            index = vv.updateArr();
            //更新节点
            update(index);
        }
        
    }

    /**
     * @Description:  更新顶点信息
     * @Date:  2019/11/27
     * @Param: [index]
     * @return:  void
     */
    private void update(int index){
        int len =0;
        //遍历邻接矩阵matrix[index] 行
        for (int j=0;j< matrix[index].length;j++){
            //出发顶点到index顶点的距离 +  从index顶点到j顶点的距离之和
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过,并且 len 小于出发 顶点到j顶点的距离,就需要更新
            if(!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j,index); // 更新j顶点的前驱节点为其实节点
                vv.updateDis(j,len); // 更新出发顶点到j点的距离
            }
        }
    }
}

class VisitedVertex{
    //记录各个顶点是否访问过, 1表示访问过, 0,未访问
    public int[] alread_arr;
    //每个下标对应的值未前一个顶点的下标,
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离
    public int[] dis;

    /**
     * @Description: 初始化
     * @Date:  2019/11/27
     * @Param: lenght 顶点个数
     * @Param: index  出发顶点
     * @return:
     */
    public VisitedVertex(int length,int index){
        alread_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        //初始化
        Arrays.fill(dis,65535);
        //设置出发顶点已被访问
        alread_arr[index] = 1;
        //将自己设为0
        dis[index] = 0;
    }


    /**
     * @Description:  判断顶点是否被访问
     * @Date:  2019/11/27
     * @Param: [index]
     * @return:  boolean
     */
    public boolean in(int index){
        return alread_arr[index] == 1;
    }
    
    /**
     * @Description:  更新出发顶点到index顶点距离
     * @Date:  2019/11/27
     * @Param: [index, len]
     * @return:  void
     */
    public void updateDis(int index , int len){
        dis[index] = len;
    } 
    
    /**
     * @Description:  更新pre这个顶点的前驱顶点为index顶点
     * @Date:  2019/11/27
     * @Param: [pre, index]
     * @return:  void
     */
    public void updatePre(int pre , int index){
        pre_visited[index] = pre;
    }

    /**
     * @Description:  返回出发顶点到index 的距离
     * @Date:  2019/11/27
     * @Param: [index]
     * @return:  int
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * @Description:  继续选择并返回新的访问顶点,比如G完了之后,  就是A最为新的顶点访问
     * @Date:  2019/11/27
     * @Param: []
     * @return:  int
     */
    public int updateArr(){
        int min = 65535,index = 0;
        //遍历已访问数组,查找没有访问的,并且值小于min
        //循环结束,就找到了最小值
        for (int i = 0; i < alread_arr.length; i++) {
            if(alread_arr[i] ==0 && getDis(i) < min){
                min = dis[i];
                index = i;
            }
        }
        //取出之后,置为已访问
        alread_arr[index] = 1;
        return index;
    }


    public void show(){
        System.out.println("=========================");
        System.out.println(Arrays.toString(alread_arr));
        System.out.println(Arrays.toString(pre_visited));
//        System.out.println(" = "+Arrays.toString(dis));
        char[] vertexs ={'A','B','C','D','E','F','G'};
        for (int i = 0; i <vertexs.length; i++) {
            System.out.print(vertexs[i]+"("+dis[i]+") ");
        }
    }
}
