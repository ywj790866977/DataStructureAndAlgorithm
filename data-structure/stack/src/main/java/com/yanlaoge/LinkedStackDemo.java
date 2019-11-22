package com.yanlaoge;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.text.html.HTMLWriter;
import java.beans.beancontext.BeanContext;
import java.security.PublicKey;

/**
 * @ClassName: LinkedStackDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/9 14:00
 **/
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        LinkeNode linkeNode1 = new LinkeNode(1);
        LinkeNode linkeNode2 = new LinkeNode(2);
        LinkeNode linkeNode3 = new LinkeNode(3);
        stack.push(linkeNode1);
        stack.push(linkeNode2);
        stack.push(linkeNode3);
        stack.show();

//        stack.pop();
//        stack.pop();
//        stack.pop();

    }
}

class LinkedStack{
    private LinkeNode head = new LinkeNode(0);
    private LinkeNode last = head;

    public LinkeNode getHead(){
        return head;
    }

    //添加
    public void push(LinkeNode node){
        //创建辅助节点
        LinkeNode temp = head;
        //查找最后一个节点
        while (true){
            if(temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
        last = node;
    }

    //删除
    public void pop(){
        //创建辅助节点
        LinkeNode temp = head;
        //找到要删除节点的前一个节点
        boolean flag = false;
        while (true){
            if(temp.getNext() == null){
                break;
            }
            if(temp.getNext().getNo() == last.getNo()){
                //找到
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if(flag){
            //修改
            System.out.println(last);
            last = temp;
        }else{
            //没找到
            System.out.println("栈为空");
        }
    }

    //显示
    //
    public void show(){
        if(head.getNext() == null){
            System.out.println("栈为空");
            return;
        }
        LinkeNode temp = head;
        LinkeNode cur = last;
        while (true){
            if(temp == cur){
                System.out.println(cur);
                break;
            }
            if(temp.getNext().getNo() == cur.getNo()){
                System.out.println(cur);
                cur = temp;
                temp = head;
            }
            temp = temp.getNext();
        }
    }
}

class LinkeNode{
    private int no;
    private LinkeNode next;

    public LinkeNode(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "LinkeNode{" +
                "no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public LinkeNode getNext() {
        return next;
    }

    public void setNext(LinkeNode next) {
        this.next = next;
    }
}
