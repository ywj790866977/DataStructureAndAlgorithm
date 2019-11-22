package com.yanlaoge;




import com.sun.jdi.connect.spi.TransportService;

import java.util.Calendar;
import java.util.Stack;

/**
 * @ClassName: SingleLinkedList
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/5 19:47
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "超哥", "豹头");
        HeroNode hero6 = new HeroNode(6, "呵呵", "傻逼");

        SingleLinkedLis singleLinkedLis1 = new SingleLinkedLis();
//        singleLinkedLis.add(hero1);
//        singleLinkedLis.add(hero2);
//        singleLinkedLis.add(hero3);
//        singleLinkedLis.add(hero4);

        singleLinkedLis1.addByOrder(hero1);
        singleLinkedLis1.addByOrder(hero3);
        singleLinkedLis1.addByOrder(hero5);
        System.out.println("========第一个链表");
        singleLinkedLis1.show();

        SingleLinkedLis singleLinkedLis2 = new SingleLinkedLis();
        singleLinkedLis2.addByOrder(hero2);
        singleLinkedLis2.addByOrder(hero6);
        singleLinkedLis2.addByOrder(hero4);
        System.out.println("========第一个链表");
        singleLinkedLis2.show();

        HeroNode concat = SingleLinkedLis.concat(singleLinkedLis1.getHead(), singleLinkedLis2.getHead());
        while (concat != null){
            System.out.println(concat);
            concat = concat.next;
        }
//        SingleLinkedLis concat = SingleLinkedLis.concat(singleLinkedLis1.getHead(), singleLinkedLis2.getHead());
//        concat.show();
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "麒麟臂");
//        singleLinkedLis.show();
//        singleLinkedLis.update(newHeroNode);
//        singleLinkedLis.show();

    }
}

class SingleLinkedLis{
    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");


    public HeroNode getHead(){
        return head;
    }
    //添加
    //1.找到最后一个节点
    //2. 将最后节点的next指向新的节点
    public void add(HeroNode hero){
        //创建一个辅助节点
        HeroNode temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
    }

    //显示链表
    public void show(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //辅助节点
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //根据英雄排名存储
    //如果存在就添加失败
    public void addByOrder(HeroNode hero){
        // 创建一个辅助变量
        // 先找到添加的temp前一个位置
        HeroNode temp = head;
        boolean flag = false;  //是否已存在
        while (true){
            //如果下个节点为空,说明在链表的最后
            if(temp.next == null){
                break;
            }
            if(temp.next.no > hero.no) {
                // 找到位置,在temp的后面插入,hero应该在temp和temp.next之间.
                break;
            }else if(temp.next.no == hero.no){
                // 已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("已存在");
        }else{
            //插入链表
            hero.next = temp.next;
            temp.next  = hero;
        }
    }
    public static HeroNode concat(HeroNode hero1,HeroNode hero2){
        // 创建一个辅助变量
        // 先找到添加的temp前一个位置
        System.out.println("=========合并");
        if(hero1.next == null ){
            return hero2;  //写错
        }
        if(hero2.next ==null){
            return hero1; //写错
        }
        HeroNode temp = new HeroNode(0,"","");
        HeroNode temp1 = hero1.next;
        HeroNode temp2 = hero2.next;
        HeroNode result = temp;
        while (temp1 != null && temp2 != null){
            if(temp1.no<=temp2.no){
                temp.next = temp1;
                temp1 = temp1.next;
            }else{
                temp.next = temp2;
                temp2 = temp2.next;  //写错
            }
            temp = temp.next;
        }
        if(temp1 ==null){
            temp.next = temp2;
        }
        if(temp2 ==null){
            temp.next = temp1;
        }
        return result; // 写错
    }

    //修改节点信息
    public void update(HeroNode heroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //辅助节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //修改
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else{
            System.out.println("没有找到 "+heroNode.no+" 节点");
        }
    }

    public void del(int no ){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的节点不存在");
        }
    }

    //获取有效个数
    public static int getLength(HeroNode head){
        if(head.next == null){
//            System.out.println();
            return 0;
        }
        HeroNode temp = head.next;
        int count =0;
        while (temp.next !=null){
            count++;
        }
        return count;
    }


    // 获取倒数第k个节点
    // 思路:
    // 1. 统计有多少个有效节点
    // 2. 有效节点减去  倒数节点就是结果
    public static HeroNode findByLastNode(HeroNode head ,int index ){
        if(head.next == null){
            return null;
        }
        int size = getLength(head);
        if(index> size || index<=0){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size-index ; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表进行翻转
    public static void reversetList(HeroNode head){
        //如果head的下一个节点为空, 或者只有一个节点,则直接返回
        if(head.next == null || head.next.next ==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;  //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表, 取出每个节点,放入新链表的最前端
        // reverseHead.next 就代表的是最前端,
        while (cur != null){
            next = cur.next;  // 先暂时保存当前节点的下一个节点,因为后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur 链接到新的链表上
            cur = next; // 让cur后移
        }
        head.next = reverseHead.next;
    }

    //逆序打印, 使用栈结构
    public static void reversePrint(HeroNode head){
        if(head.next ==null){
            System.out.println("链表为空");
        }
        HeroNode cur = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }


}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
