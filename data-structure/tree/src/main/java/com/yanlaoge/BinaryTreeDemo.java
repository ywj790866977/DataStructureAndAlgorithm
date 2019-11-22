package com.yanlaoge;

import com.sun.security.auth.NTNumericCredential;

/**
 * @ClassName: BinaryTreeDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/18 13:52
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴勇");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();


//        int findNo = 5;
//        HeroNode resNode = binaryTree.preOrderSearch(findNo);
//        if(resNode != null){
//            System.out.println("找到的节点信息: "+resNode);
//        }else{
//            System.out.println("没有找到 no = "+findNo+"的节点");
//        }


//        resNode = binaryTree.infixOrderSearch(findNo);
//        if(resNode != null){
//            System.out.println("找到的节点信息: "+resNode);
//        }else{
//            System.out.println("没有找到 no = "+findNo+"的节点");
//        }


//        resNode = binaryTree.postOrderSearch(findNo);
//        if(resNode != null){
//            System.out.println("找到的节点信息: "+resNode);
//        }else{
//            System.out.println("没有找到 no = "+findNo+"的节点");
//        }
        System.out.println("之前");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("之后");
        binaryTree.preOrder();


    }
}

class BinaryTree{
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder(){
        if(this.root !=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder(){
        if(this.root !=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public HeroNode preOrderSearch(int no){
        if(this.root != null){
            return this.root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if(this.root != null){
            return this.root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if(this.root != null){
            return this.root.postOrderSearch(no);
        }else{
            return null;
        }
    }

    public void delNode(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
            }else{
                root.del(no);
            }
        }else {
            System.out.println("空数, 不能删除!");
        }
    }
}


class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder(){
        //先打印当前节点
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        if(this.right != null){
            this.right.infixOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        System.out.println("后序查找..");
        //先判断root节点是不是
        if(this.no == no){
            return this;
        }
        //顶一个接受变量
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    //中序查找
    public HeroNode infixOrderSearch(int no){
        //接受变量
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("后序查找..");
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    //后序查找
    public HeroNode postOrderSearch(int no){
        //接受变量
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("后序查找..");
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    //删除节点
    public void delNode(int no){
        //判断左边节点是否为删除节点,
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //判断右边
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //进行左递归
        if(this.left !=null){
            this.left.delNode(no);
        }
        //右递归
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    //删除节点
    public void del(int no){
        //判断左边节点是否为删除节点,
        if(this.left != null && this.left.no == no){
            //找到左边的为删除节点,
            //进行判断,右边有无节点,如果没有,就直接替代
            if(this.left.left !=null && this.left.right ==null){
                this.left = this.left.left;
            }else if(this.left.left !=null && this.left.right !=null){
                this.left = this.left.right;
            }else if(this.left.left == null && this.left.right ==null){
                this.left =null;
            }
            return;
        }
        //判断右边
        HeroNode temp = null;
        if(this.right != null && this.right.no == no){
            if(this.right.right !=null && this.right.left ==null){
                this.right = this.right.right;
            }else if(this.right.right != null && this.right.left !=null) {
//                this.right = this.right.left;
                temp = this.right.left;
                temp.right = this.right.right;
                this.right = temp;

            }
            else if (this.right.left ==null && this.right.right ==null){
                this.right =null;
            }
            return;
        }
        //进行左递归
        if(this.left !=null){
            this.left.delNode(no);
        }
        //右递归
        if(this.right != null){
            this.right.delNode(no);
        }
    }
}
