package com.yanlaoge;

import javax.print.attribute.standard.OrientationRequested;
import java.util.FormatFlagsConversionMismatchException;

/**
 * @ClassName: Josepfu
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/8 9:55
 **/
public class Josepfu {
    public static void main(String[] args) {

        CircleSingleLinkedList singleLinkedList = new CircleSingleLinkedList();
        singleLinkedList.addBoy(5);
        singleLinkedList.show();

        singleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList{
    //first节点
    private Boy first = null;

    //添加
    //思路:
    //1.传入一个数值, 需要添加多少个boy;
    //2.1.第一个boy需要特殊处理, 初始化时,已经创建了一个first节点, 需要将第一个赋值给first
    //将first节点的下一个节点指向自己,将辅助节点也指向自己
    //因为first节点不用动, 需要使用辅助节点来移动.
    //2.2如果不是第一个节点,就正常赋值, 将新的节点赋值给cur.next
    //新节点的next为first;
    //移动cur节点为 新节点
    public void addBoy(int nums){
        //如果新增
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;
        //从1开始遍历
        for (int i = 1; i <=nums ; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;  //将1号boy赋值给first
                first.setNext(first); // 将first的下一个指向自己,因为只有一个,自循环
                curBoy = first; // 将指针指向first
            }else{
                curBoy.setNext(boy); // 将指针当前节点的下一个指向新节点
                boy.setNext(first); // 将节点的下一个指向第一个节点
                curBoy = boy; // 移动指针
            }
        }
    }

    //查看链表内容
    public void show(){
        //判断链表是否为空
        if(first ==null){
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true){
            System.out.println("编号:"+cur.getNo());
            //如果当前的boy的下一个节点时first那么久到最后了
            if(cur.getNext() ==first){
                break;
            }
            cur = cur.getNext();
        }
    }

    public void countBoy(int startNo,int countNum,int nums){
        //1.先判断参数是否可用
        if(first ==null || startNo < 1|| startNo > nums){
            System.out.println("参数不正确");
        }
        //2.创建辅助指针helper, ,
        Boy helper = first;
        //3.将helper指针指向最后一个节点
        while (true){
            if(helper.getNext() == first){
                //如果相等,就是最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //4.first指针移动到报数前一个位置 (k-1)
        for (int i = 0; i <startNo -1 ; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //5.开始报数,将指针移动到结束位置
        while (true){
            if(helper == first){
                break;
            }
            for (int i = 0; i <countNum -1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈的编号:"+first.getNo());
            //6.移除结束节点,修改helper和first指向
            first = first.getNext();
            helper.setNext(first);
        }
        //打印最后一个
        System.out.println("最后一个编号:"+first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}
