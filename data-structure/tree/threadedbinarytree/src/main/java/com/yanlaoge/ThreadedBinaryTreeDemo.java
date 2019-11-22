package com.yanlaoge;

import javax.sound.midi.Soundbank;

/**
 * @ClassName: ThreadedBinaryTreeDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/18 19:28
 **/
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr= {};
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
//        threadedBinaryTree.infixThreadedNodes();
//        threadedBinaryTree.preThreadedNodes();
        threadedBinaryTree.postThreadedNodes();

        //查看10号节点如果是3号节点那就是对的
//        HeroNode left = node5.getLeft();
//        System.out.println(left);
        //查看10号节点如果是1号节点那就是对的
//        HeroNode right = node5.getRight();
//        System.out.println(right);


//        threadedBinaryTree.threadedList();
//        threadedBinaryTree.preThreadedList();
        threadedBinaryTree.postThreadedList();
    }
}

class ThreadedBinaryTree{
    private HeroNode root;

    private HeroNode pre;

    public ThreadedBinaryTree() {

    }

    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }

    public void preThreadedNodes(){
        this.preThreadedNodes(root);
    }
    public void postThreadedNodes(){
        this.postThreadedNodes(root);
    }

    public ThreadedBinaryTree(HeroNode root) {
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

    //中序线索化节点
    public void infixThreadedNodes(HeroNode node){
        if(node ==null){
            return;
        }
        //中序处理线索化节点
        //处理左边
        infixThreadedNodes(node.getLeft());
        //处理自己(主要)
        //判断left是否为空,如果为空,设置前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //因为一次只能处理一个节点, 所以在处理后继节点的时候, 其实是第下一次递归了,
        // 下次节点就是本次节点的后继节点
        if(pre != null && pre.getRight() ==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动pre节点
        pre = node;

        //处理右边
        infixThreadedNodes(node.getRight());
    }

    //前序线索化节点
    public void preThreadedNodes(HeroNode node){
        if(node ==null){
            return;
        }
        //处理自己(主要)
        //判断left是否为空,如果为空,设置前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //因为一次只能处理一个节点, 所以在处理后继节点的时候, 其实是第下一次递归了,
        // 下次节点就是本次节点的后继节点
        if(pre != null && pre.getRight() ==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动pre节点
        pre = node;


        //中序处理线索化节点
        //处理左边
        if(node.getLeft() != null && node.getLeftType() !=1){
            preThreadedNodes(node.getLeft());
        }
        //处理右边
        if(node.getRight()!=null && node.getRightType() !=1){
            preThreadedNodes(node.getRight());
        }
    }


    //后序线索化节点
    public void postThreadedNodes(HeroNode node){
        if(node ==null){
            return;
        }

        //中序处理线索化节点
        //处理左边
        if(node.getLeft() != null && node.getLeftType() !=1){
            postThreadedNodes(node.getLeft());
        }
        //处理右边
        if(node.getRight()!=null && node.getRightType() !=1){
            postThreadedNodes(node.getRight());
        }
        //处理自己(主要)
        //判断left是否为空,如果为空,设置前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //因为一次只能处理一个节点, 所以在处理后继节点的时候, 其实是第下一次递归了,
        // 下次节点就是本次节点的后继节点
        if(pre != null && pre.getRight() ==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动pre节点
        pre = node;
    }



    //中序线索化遍历
    public void threadedList(){
        //定义一个node变量
        HeroNode node = root;

        //如果Node不为空就一直循环
        while (node != null){
            //中序遍历,应该先输出最左边的,最左边的节点, 也就是他的leftType == 1
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //找到之后, 输出
            System.out.println(node);
            //输出完之后,找他的后继节点, 然后输出
            while (node.getRightType() ==1){
                node = node.getRight();
                System.out.println(node);
            }
            //如果没有后继节点了, 就移动指针
            node = node.getRight();
        }
    }
    //前序线索化遍历
    public void preThreadedList(){
        //定义一个node变量
        HeroNode node = root;
        //如果Node不为空就一直循环
        while (node != null){
            //找到之后, 输出
            System.out.println(node);
            //中序遍历,应该先输出最左边的,最左边的节点, 也就是他的leftType == 1
            while ( node.getLeftType() == 0){
                node = node.getLeft();
                System.out.println(node);
            }
            //输出完之后,找他的后继节点, 然后输出
            while (node.getRightType() ==1){
                node = node.getRight();
                System.out.println(node);
            }
            //如果没有后继节点了, 就移动指针
            node = node.getRight();
        }
    }

    //后序线索化遍历
    public void postThreadedList(){
        //定义一个node变量
        HeroNode node = root;
        //如果Node不为空就一直循环
        HeroNode preNode = null;
        while (node != null){
            //中序遍历,应该先输出最左边的,最左边的节点, 也就是他的leftType == 1
            while ( node.getLeftType() == 0){
                node = node.getLeft();

            }

            while (node.getRightType() ==1){
                System.out.println(node);
                preNode = node;
                node = node.getRight();
            }

            System.out.println(node);

            //如果当前节不是没有后继节点
            if(node.getRightType() !=1){
                //当前节点是主节点, 结束
                if(node ==root){
                    return;
                }
                //上一个节点和当前节点的右节点是同一个节点
                if(preNode == node.getRight()){
                    //那就说明, 当前左子树遍历完成,
                    //回到当前节点的父节点, 遍历他的右子树
                    node = node.getParent().getRight();
                }

            }else{
                node = node.getRight();
            }
        }
    }

}


class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private HeroNode parent;

    private int leftType; // 0表示指向左子树,如果1则表示指向前驱节点
    private int rightType; // 0表示指向右子树,如果1则表示指向后继节点


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

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

