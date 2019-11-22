package com.yanlaoge;

/**
 * @ClassName: DouvleLinkedListDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/7 18:56
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();
//        linkedList.add(hero1);
//        linkedList.add(hero2);
//        linkedList.add(hero3);
//        linkedList.add(hero4);
        System.out.println("=========顺序插入=========");
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);
        linkedList.show();

        HeroNode2 node = new HeroNode2(4, "彩笔", "欺凌逼");
        linkedList.update(node);
        System.out.println("=========修改后=========");
        linkedList.show();

        linkedList.del(3);
        System.out.println("=========删除后=========");
        linkedList.show();

    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no,String name,String nickname){
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


class DoubleLinkedList{
    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead(){
        return head;
    }


    //显示链表
    public void show(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //辅助节点
        HeroNode2 temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 hero){
        //创建一个辅助节点
        HeroNode2 temp = head;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //双向链表
        temp.next = hero;
        hero.pre = temp;
    }

    //修改节点信息
    // 就改变了node为hero2
    public void update(HeroNode2 heroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //辅助节点
        HeroNode2 temp = head.next;
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

    //删除
    //直接找到需要删除的节点
    //找到后自我删除
    public void del(int no ){

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //这里有问题
            //如果是最后一个节点,就不需要执行下面的步骤
            if(temp.next !=null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("要删除的节点不存在");
        }
    }

    //顺序插入
    public void addByOrder(HeroNode2 hero){
        // 创建一个辅助变量
        // 先找到添加的temp前一个位置
        HeroNode2 temp = head;
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
            if(temp.next == null){
                temp.next =hero;
                hero.pre = temp;
            }else{
                hero.next =temp.next;
                temp.next.pre = hero;
                hero.pre=temp;
                temp.next = hero;
            }

//            temp.next= hero;
//            hero.pre = temp;
//            hero.next =next;
//            temp.next.pre = hero;
        }
    }
}