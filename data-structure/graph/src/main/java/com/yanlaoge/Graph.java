package com.yanlaoge;

import java.util.*;

/**
 * @ClassName: Graph
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/22 8:31
 **/
public class Graph {
    private List<String> vertexList;
    private int[][] edges;
    private int numOfedges;
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] Vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

//        graph.show();
//        graph.dfs();
        graph.bfs();
    }


    /**
     * @Description:  构造方法, 初始化变量
     * @Date:  2019/11/22
     * @Param: [n]
     * @return:
     */
    public Graph(int n) {
        vertexList = new ArrayList<>();
        edges = new int[n][n];
        numOfedges = 0;
        isVisited = new boolean[n];
    }


    public void bfs(){
        for (int i =0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }


    /**
     * @Description:  一个节点进行广度优先遍历
     * @Date:  2019/11/22
     * @Param: [isVisited, i]
     * @return:  void
     */
    private void bfs(boolean[] isVisited,int i){
        int u; // 标识队列的头节点对应下标
        int w; // 邻接节点
        //队列,记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i)+"->");
        //将节点置为已访问
        isVisited[i] = true;
        //将节点添加到队列
        queue.addLast(i);
        //队列非空就一直循环
        while ( !queue.isEmpty()){
            //取出队列元素
            u = queue.removeFirst();
            //获取第一个邻接节点下标
            w = getFirstNeighbor(u);
            //如果w存在就进行
            while (w != -1){
                //判断有没有被访问
                if(!isVisited[w]){
                    //进行访问
                    System.out.print(getValueByIndex(w)+"->");
                    //访问之后置为已访问
                    isVisited[w] = true;
                    //访问之后需要将元素放入已访问的队列里, 如果下次有不能访问的时候,就取出已访问的过的, 进行遍历
                    queue.addLast(w);
                }
                //已经访问
                //以u为前驱,找w后面的下一个邻接节点
                //假如: A 的C已经访问了,  下一次, 就A的C进行,看对D访问了没有
                //体现出广度优先
                w = getNextNeighbor(u,w);
            }
        }

    }

    /**
     * @Description:  循环遍历所有节点
     * @Date:  2019/11/22
     * @Param: []
     * @return:  void
     */
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
        
    }


    /**
     * @Description:  遍历能够直接访问的节点
     * @Date:  2019/11/22
     * @Param: [isVisited, i]
     * @return:  void
     */
    private void dfs(boolean[] isVisited,int i){
        //访问节点
        System.out.print(getValueByIndex(i)+ "->");
        //先将当前下标设置为已经访问
        isVisited[i] = true;
        //查找下一个邻接节点
        int w = getFirstNeighbor(i);
        //循环递归遍历
        while (w != -1){
            //需要先判断有没有被访问过
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果被访问过
            //获取下一个邻接节点
            w = getNextNeighbor (i,w);
        }

        //如果w不存在就退出循环,
        //在重载的方法里开始下一轮查找
    }


    /**
     * @Description:  查找邻接节点
     * @Date:  2019/11/22
     * @Param: [index]
     * @return:  int
     */
    public int getFirstNeighbor(int index){
        for (int i = 0;i<vertexList.size();i++){
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * @Description:  根据前一个邻接节点的下标,获取下一个邻接节点
     * @Date:  2019/11/22
     * @Param: []
     * @return:  int
     */
    public int getNextNeighbor( int v1,int v2){
        for (int i = v2 +1; i < vertexList.size(); i++) {
            if(edges[v1][i]> 0){
                return i;
            }
        }
        return  -1;
    }


    /**
     * @Description:  遍历
     * @Date:  2019/11/22
     * @Param: []
     * @return:  void
     */
    public void show(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * @Description:  返回顶点个数
     * @Date:  2019/11/22
     * @Param: []
     * @return:  int
     */
    public int getNumOfVertex(){
       return vertexList.size();
    }

    /**
     * @Description:  返回边的数量
     * @Date:  2019/11/22
     * @Param: []
     * @return:  int
     */
    public int getNumofEdges(){
        return numOfedges;
    }

    /**
     * @Description:  通过索引获取顶点对应的数据
     * @Date:  2019/11/22
     * @Param: [index]
     * @return:  java.lang.String
     */
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * @Description:  获取权重
     * @Date:  2019/11/22
     * @Param: [v1, v2]
     * @return:  int
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * @Description:  插入顶点
     * @Date:  2019/11/22
     * @Param: [vertex]
     * @return:  void
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * @Description:  添加边
     * @Date:  2019/11/22
     * @Param: [v1, v2, weight]
     * @return:  void
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfedges ++;
    }


}
