package com.yanlaoge;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: huffmanTree
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/19 18:23
 **/
public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node root = createHuffman(arr);
        preOrder(root);
    }

    public static  void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("空");
        }
    }

    public static Node createHuffman(int[] arr){
        //将数组放入列表中,进行排序
        List<Node > nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }


        //开始
        while (nodes.size() > 1){
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出前两个
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //然后创建一个父节点
            Node parent = new Node(left.value + right.value);
            //挂载带父节点
            parent.left = left;
            parent.right = right;
            //删除两个节点
            nodes.remove(left);
            nodes.remove(right);
            //添加新节点
            nodes.add(parent);

        }

        return nodes.get(0);
    }

}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right != null){
            this.right.preOrder();
        }
    }
}