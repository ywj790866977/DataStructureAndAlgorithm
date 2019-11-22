package com.yanlaoge;

import java.awt.*;
import java.lang.reflect.Array;
import java.rmi.MarshalException;
import java.util.Scanner;

/**
 * @ClassName: ArrayQueueDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/5 9:05
 **/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key = ' ';
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获取数据");
            System.out.println("h(header):查看头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':

                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数:");
                    int n = scanner.nextInt();
                    arrayQueue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int val = arrayQueue.getQueue();
                        System.out.println("获取的值是:"+val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int val = arrayQueue.headerQueue();
                        System.out.println("队列头数据是:"+val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop =false;
                    break;
            }

        }
        System.out.println("退出程序");
    }
}

class ArrayQueue{
    private int maxSize; // 队列大小
    private int front;   // 获取 数据指针
    private int rear;    // 添加 数据指针
    private int[] arr;   // 队列

    //初始化队列
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断队列是否满
    public  boolean isFull(){
        return rear == maxSize -1;
    }

    // 判断队列是否为空
    public  boolean isEmpty(){
        return rear == front;
    }

    //添加
    public void addQueue(int num){
        //判断队列是否已满
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;
        arr[rear] = num;
    }

    //获取
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    //查看队列
    public void  showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

    //查看头数据
    public int headerQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front +1];
    }
}