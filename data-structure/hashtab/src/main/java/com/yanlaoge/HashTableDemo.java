package com.yanlaoge;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @ClassName: com.yanlaoge.HashTableDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/17 15:33
 **/
public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出菜单");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找的雇员ID");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}


class HashTab{
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //初始化每个链表
        for (int i =0; i< size; i++){
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public int hashFun(int id){
        return id % size;
    }

    public void add(Emp emp){
        //生成放入哪条链表位置
        int empLinkedListNo = hashFun(emp.id);
        //调用链表的添加方法添加
        empLinkedLists[empLinkedListNo].add(emp);
    }

    public void list(){
        for (int i = 0; i <size ; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp empById = empLinkedLists[empLinkedListNo].findEmpById(id);
        if(empById != null){
            System.out.println("在"+empLinkedListNo+1+"链表找到了该雇员..");
        }else{
            System.out.println("没有找到该雇员...");
        }
    }
}

class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        //先判断是否是第一个元素,如果是第一个直接添加,不是的话添加到链表的最后
        if(head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while (true){
            if(temp.next ==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }

    public void list(int no){
        if(head ==null){
            System.out.println("第"+(no+1)+"链表为空");
            return;
        }
        Emp temp = head;
        System.out.println("第"+(no+1)+"链表信息");
        while (true){
            System.out.print(" => "+temp);
            if(temp.next == null){
                break;
            }
            temp  = temp.next;
        }
    }

    public Emp findEmpById(int id){
        if(head ==null){
            System.out.println("链表为空");
            return null;
        }

        Emp temp = head;
        while (true){
            if(temp.id == id){
                break;
            }
            if(temp.next ==null){
                temp=null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    public void deleteById(int id){
        if(head ==null){
            System.out.println("链表为空");
            return;
        }
        Emp temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //找到删除元素的前一个
            temp.next = temp.next.next;
        }else{
            System.out.println("没有找到该元素");
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.yanlaoge.Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
