package com.yanlaoge;

import javax.xml.catalog.Catalog;
import java.util.Scanner;

/**
 * @ClassName: ArrayStackDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/9 13:27
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show: 查看栈");
            System.out.println("exit: 退出菜单");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("输入入栈的数据:");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据: "+res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;

            }

        }
        System.out.println("退出成功");
    }
}

class ArrayStack{

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断是否为满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //入栈
    public void push(int val){
        //判断是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=val;
    }

    //出栈
    public int pop(){
        //判断空
        if(isEmpty()){
            throw  new RuntimeException("栈空");
        }
        int val = stack[top];
        top--;
        return val;
    }

    //查看栈
    public void show(){
        //判断空
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}


