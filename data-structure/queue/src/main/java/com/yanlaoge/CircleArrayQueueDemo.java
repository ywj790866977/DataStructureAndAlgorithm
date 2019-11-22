package com.yanlaoge;

import java.util.Scanner;

/**
 * @ClassName: CircleArrayQueueDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/5 18:58
 **/
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
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

class CircleArrayQueue{
    private int maxSize; // 队列大小
    private int front;   // 获取 数据指针
    private int rear;    // 添加 数据指针
    private int[] arr;   // 队列

    //初始化队列
    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public  boolean isFull(){
        return (rear + 1) % maxSize  == front;
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

        arr[rear] = num;
        rear = (rear +1 )% maxSize;
    }

    //获取
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw  new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = (front +1) % maxSize;
        return value;
    }

    //查看队列
    public void  showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //查看头数据
    public int headerQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
