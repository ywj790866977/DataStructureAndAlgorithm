package com.yanlaoge;

import java.security.AlgorithmConstraints;

/**
 * @ClassName: ArrBinaryTreeDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/18 18:46
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

class ArrBinaryTree{
    private  int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index){
        //判断下表是否越界
        if(arr == null || index > arr.length || index < 0 ){
            System.out.println("下表不合法");
        }
        // 首先打印自己
        System.out.println(arr[index]);

        // 向左打印
        if(index * 2 + 1 < arr.length){
            preOrder(index * 2 +1);
        }

        //向右打印
        if(index *2 + 1 <arr.length){
            preOrder(index *2 + 2);
        }
    }

    public void infixOrder(int index){
        //判断下表是否越界
        if(arr == null || index > arr.length || index < 0 ){
            System.out.println("下表不合法");
        }
        // 向左打印
        if(index * 2 + 1 < arr.length){
            infixOrder(index * 2 +1);
        }
        // 首先打印自己
        System.out.println(arr[index]);


        //向右打印
        if(index *2 + 1 <arr.length){
            infixOrder(index *2 + 2);
        }
    }

    public void postOrder(int index){
        //判断下表是否越界
        if(arr == null || index > arr.length || index < 0 ){
            System.out.println("下表不合法");
        }
        // 向左打印
        if(index * 2 + 1 < arr.length){
            postOrder(index * 2 +1);
        }

        //向右打印
        if(index *2 + 1 <arr.length){
            postOrder(index *2 + 2);
        }
        // 首先打印自己
        System.out.println(arr[index]);
    }
}
