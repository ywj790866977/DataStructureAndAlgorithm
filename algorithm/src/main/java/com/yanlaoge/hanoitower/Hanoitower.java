package com.yanlaoge.hanoitower;

/**
 * @ClassName: Hanoitower
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/22 19:24
 **/
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    public static void hanoiTower(int num ,char a, char b, char c){
        //如果只有一个盘
        if(num == 1){
            System.out.println("第1个盘从 "+a+" -> "+c);
        }else{
            //如果n>2的情况,我们总是可以看做两个盘   1是最下面的一个盘, 2是上面的所有盘
            //1. 把最上面的盘  A->B
            hanoiTower(num -1,a,c,b);
            //2. 把最下面的盘  A->C
            System.out.println("第"+num+"个盘从 "+a+ " -> "+c);
            //3. 把B塔所有的盘从 B->C ;  移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}

