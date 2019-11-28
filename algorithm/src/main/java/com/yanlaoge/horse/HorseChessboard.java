package com.yanlaoge.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @ClassName: HorseChessboard
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/27 19:20
 **/
public class HorseChessboard {
    private static int X; // 棋牌 列
    private static int Y; // 棋盘 行
    private static boolean[] visited; //记录棋盘格各个位置是否被访问
    private static boolean finished; // 标记棋盘所有位置是否都被访问

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessborad = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessborad,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时: "+(end-start)+" ms");

        for (int[] rows : chessborad) {
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     * @Description:  骑士周游算法
     * @Date:  2019/11/28
     * @Param: chessboard 棋盘
     * @Param: row 当前步在棋盘的 行
     * @Param: column 当前步在棋盘的 列
     * @Param: step 第几步,初始为1
     * @return:  void
     */
    public static void traversalChessboard(int[][] chessboard,int row , int column,int step){
        //先将当前位置置为已访问
        chessboard[row][column] = step;
        //row * X + column  =  当前骑士的位置并置为已访问
        visited[row * X + column] = true;
        //获取当前位置可以走的部署
        ArrayList<Point> ps = next(new Point(column, row));
        //优化: 对当前位置下一个位置的数组,进行进行非递减排序;
        //贪心算法:  每个取最少的进行
        sort(ps);
        //循环遍历可以走的位置
        while (!ps.isEmpty()) {
            //如果位置没有访问过,就递归访问
            //取出一个位置
            Point p = ps.remove(0);
            //判断是否访问
            if(!visited[p.y * X + p.x]){
                traversalChessboard(chessboard,p.y,p.x, step+1);
            }
        }
        //循环结束, 判断当前棋盘是否有效,
        //如果无效,将当前位置置为未访问
        //也就是需要回溯
        if(step < X*Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else{
            finished = true;
        }
    }

    /**
     * @Description:  根据当前位置,计算马儿还能走哪些位置
     * @Date:  2019/11/27
     * @Param: [currPoint]
     * @return:  java.util.ArrayList<java.awt.Point>
     */
    public static ArrayList<Point> next(Point currPoint){
        //存放集合
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断能否走5的位置
        if((p1.x = currPoint.x -2)>= 0 && (p1.y = currPoint.y -1)>=0 ){
            ps.add(new Point(p1));
        }
        //判断能否走6的位置
        if((p1.x = currPoint.x -1)>= 0 && (p1.y = currPoint.y -2)>=0 ){
            ps.add(new Point(p1));
        }
        //判断能否走7的位置
        if((p1.x = currPoint.x +1) < X && (p1.y = currPoint.y -2)>=0 ){
            ps.add(new Point(p1));
        }
        //判断能否走0的位置
        if((p1.x = currPoint.x +2) < X && (p1.y = currPoint.y -1)>=0 ){
            ps.add(new Point(p1));
        }
        //判断能否走1的位置
        if((p1.x = currPoint.x +2) < X && (p1.y = currPoint.y +1) < Y ){
            ps.add(new Point(p1));
        }
        //判断能否走2的位置
        if((p1.x = currPoint.x +1) < X && (p1.y = currPoint.y +2) < Y ){
            ps.add(new Point(p1));
        }
        //判断能否走3的位置
        if((p1.x = currPoint.x -1)>=0 && (p1.y = currPoint.y +2) < Y ){
            ps.add(new Point(p1));
        }
        //判断能否走4的位置
        if((p1.x = currPoint.x -2)>=0 && (p1.y = currPoint.y +1) < Y ){
            ps.add(new Point(p1));
        }

        return ps;
    }

    /**
     * @Description:  根据当前这一步的下一步数组,就进行非递减排序
     * @Date:  2019/11/28
     * @Param: [ps]
     * @return:  void
     */
    public static void sort(ArrayList<Point> ps){
        ps.sort((Point o1, Point o2)->{
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            if(count1 < count2){
                return -1;
            }else if( count1 ==count2){
                return 0;
            }else{
                return 1;
            }
        });
    }
}
