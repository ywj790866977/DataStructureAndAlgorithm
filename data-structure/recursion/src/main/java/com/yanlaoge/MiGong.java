package com.yanlaoge;

import java.io.PushbackInputStream;

/**
 * @ClassName: MiGong
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/12 14:41
 **/
public class MiGong {

    public static void main(String[] args) {
        int[][]  map = new int[8][7];
        for (int i = 0; i <7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i <8 ; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图初始化");

        for (int i = 0; i <8 ; i++) {
            for(int j = 0 ;j<7; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("走完的");
        boolean b = setWay(map, 1, 1);
        if(b){
            for (int i = 0; i <8 ; i++) {
                for(int j = 0 ;j<7; j++){
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
        }
    }

    //使用递归回溯给小球找路
    // 1.map为地图
    // 2. i,j 标识地图从哪个位置开始
    // 3. 如果小球能到 map[6][5]说成功
    // 4. map[i][j] == 0 ,为没走过, 1 为墙, 2 为可通过, 3 为走过,但不通
    // 5. 策略: 下->右->上->左 ,走不通, 再回溯
    public static boolean setWay(int[][] map ,int i, int j){
        //首先判断出口
        if(map[6][5] == 2){
            //成功
            return true;
        }else{
            //不是终点执行
            //判断是否走过没有
            if(map[i][j] == 0 ){
                //没有走过
                //首先将此节点标记为走过的节点
                map[i][j] =2;
                if(setWay(map,i+1,j)){
                    // 向下走
                    return true;
                }else if(setWay(map,i,j+1)){
                    // 向右走
                    return true;
                }else if(setWay(map,i-1,j)){
                    // 向上走
                    return true;
                }else if(setWay(map,i,j-1)){
                    // 向左走
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
