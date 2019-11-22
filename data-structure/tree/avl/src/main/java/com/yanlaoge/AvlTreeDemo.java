package com.yanlaoge;

/**
 * @ClassName: AvlTreeDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/21 13:47
 **/
public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());      // 4
        System.out.println(avlTree.getRoot().leftHeight());  // 1
        System.out.println(avlTree.getRoot().rightHeight()); // 3
        System.out.println(avlTree.getRoot());


    }
}

class AvlTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * @Description:  删除右子树的最小值
     * @Date:  2019/11/21
     * @Param: [node]
     * @return:  com.yanlaoge.Node
     */
    public Node delRightTreeMin(Node node){
        Node temp = node;
        //传一个查找数的父节点开始查找
        while (temp.left != null){
            temp = temp.left;
        }
        //循环结束,就是最小值
        //删除
        del(temp.value);
        return temp;
    }


    /**
     * @Description:  删除节点
     * @Date:  2019/11/21
     * @Param: [value]
     * @return:  void
     */
    public void del(int value){
        //判断是否有根节点
        if(root ==null){
            System.out.println("树为空!");
            return;
        }else{
            //不为空
            //先获取要删除的节点
            Node targetNode = searchValue(value);
            if(targetNode == null){
                //说明, 没有要删除的节点
                System.out.println("删除节点不存在");
                return;
            }else{
                //有删除的节点
                //有如下几种情况
                //1.删除的是叶子节点
                //2.删除只有一个子树的节点
                //3.删除有两个子树的节点

                //如果当前树只有一个节点, 就直接删除
                if(root.left ==null && root.right ==null){
                    root = null;
                    return;
                }

                //删除节点是叶子节点
                //需要从父节点删除
                Node parentNode = searchParent(value);
                if(targetNode.left ==null && targetNode.right ==null){
                    //判断删除节点是父节点的右节点还是左节点
                    if(parentNode.left !=null && parentNode.left.value == value){
                        //是左边节点, 就直接值为空
                        parentNode.left =null;
                    }else if(parentNode.right !=null && parentNode.right.value == value){
                        //右节点
                        parentNode.right =null;
                    }
                }else if(targetNode.left !=null && targetNode.right !=null){
                    //删除有两个子树的节点
                    //找到最小值,删除,然后赋值给targetNode就可以了
                    //定义一个方法来完成查找,删除最小节点
                    Node min = delRightTreeMin(targetNode.right);
                    targetNode.value = min.value;
                } else{
                    //删除一个子树的节点
                    //判断是左还是右
                    if(targetNode.left !=null){
                        //左
                        if(parentNode != null){
                            //判断删除节点是父节点的左边还是右边
                            if(parentNode.left.value ==value){
                                parentNode.left = targetNode.left;
                            }else{
                                parentNode.right = targetNode.left;
                            }
                        }else{
                            root = targetNode.left;
                        }
                    }else{
                        //右
                        if(parentNode != null){
                            //判断删除节点是父节点的左边还是右边
                            if(parentNode.left.value == value){
                                parentNode.left = targetNode.right;
                            }else{
                                parentNode.right = targetNode.right;
                            }
                        }else{
                            root = targetNode.right;
                        }

                    }
                }


            }
        }
    }

    /**
     * @Description:  查询删除的节点
     * @Date:  2019/11/21
     * @Param: [value]
     * @return:  Node
     */
    public Node searchValue(int value){
        if(root == null){
            return null;
        }else{
            return root.searchValue(value);
        }
    }


    /**
     * @Description:  查询删除节点的父节点
     * @Date:  2019/11/21
     * @Param: [value]
     * @return:  Node
     */
    public Node searchParent(int value){
        if(root == null){
            return  null;
        }else{
            return root.searchParent(value);
        }
    }

    /**
     * @Description:  添加
     * @Date:  2019/11/20
     * @Param: [node]
     * @return:  void
     */
    public void add(Node node){
        if(root == null){
            root= node;
        }else{
            root.add(node);
        }
    }

    /**
     * @Description:  中序遍历
     * @Date:  2019/11/21
     * @Param: []
     * @return:  void
     */
    public void infixOrder(){
        if(root !=null){
            root.infixOrder();
        }else {
            System.out.println("为空");
        }

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * @Description:  返回左子树高度
     * @Date:  2019/11/21
     * @Param: []
     * @return:  int
     */
    public int leftHeight(){
        if(left ==null){
            return 0;
        }
        return left.height();
    }

    /**
     * @Description:  返回做子树高度
     * @Date:  2019/11/21
     * @Param: []
     * @return:  int
     */
    public int rightHeight(){
       if(right ==null){
           return 0;
       }
       return right.height();
    }

    /**
     * @Description:  返回当前节点的高度,以当前节点为根的高度
     * @Date:  2019/11/21
     * @Param: []
     * @return:  int
     */
    public int height(){
        return  Math.max(left == null? 0:left.height(),right == null? 0:right.height()) +1;
    }

    /**
     * @Description:  左旋
     * @Date:  2019/11/21
     * @Param: []
     * @return:  void
     */
    public void leftRotate(){
        //创建新的节点,为当前根节点的值
        Node newNode  = new Node(value);
        //把新节点的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换为右节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newNode;
    }

    /**
     * @Description:  右旋转
     * @Date:  2019/11/21
     * @Param: []
     * @return:  void
     */
    public void rightRotate(){
        //和左旋类似
        //创建一个新的节点值未当前节点的值
        Node newNode = new Node(value);
        //新节点的右子树为当前节点的右子树
        newNode.right = right;
        //新节点的左子树为当前节点的左子树的右子树
        newNode.left = left.right;
        //当前节点值改为当前节点左子树的值
        value = left.value;
        //当前节点的左子树为当前节点的左子树的左子树
        left = left.left;
        //当前节点右子树为新的节点
        right = newNode;
    }

    /**
     * @Description:  查找删除节点的父节点
     * @Date:  2019/11/21
     * @Param: [value]
     * @return:  Node
     */
    public Node searchParent(int value){
        //判断当前节点是否为父节点
        if((this.left != null &&this.left.value == value) || (this.right !=null && this.right.value == value)){
            return this;
        }else{
            if(value < this.value && this.left != null){
                //如果当前节点不是, 需要递归在左右两边查找
                return  this.left.searchParent(value);
            }else if(value > this.value && this.right != null){
                //查找右边
                return  this.right.searchParent(value);
            }else{
                //没找到
                return null;
            }
        }
    }

    /**
     * @Description:  查找删除节点
     * @Date:  2019/11/21
     * @Param: [value]
     * @return:  Node
     */
    public Node searchValue(int value){
        if(value == this.value){
            return this;
        } else if(value < this.value){
            //向左边查找
            if(this.left ==null){
                return null;
            }
            return this.left.searchValue(value);
        }else{
            //向右边查找
            if(this.right ==null){
                return null;
            }
            return this.right.searchValue(value);
        }
    }

    /**
     * @Description: 添加
     * @Date:  2019/11/20
     * @Param: [node]
     * @return:  void
     */
    public void add(Node node){
        if(node ==null){
            return;
        }
        if(node.value < this.value){
            if(this.left ==null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right =node;
            }else{
                this.right.add(node);
            }
        }

        //当添加完一个条件完成后,
        //如果(右子树的高度-左子树的高度) >1 那么左旋
        if((rightHeight() - leftHeight()) >1){
            //如果他的 右子树的左子树 大于 右子树的右子树 ,进行右旋转
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
            return; //!! 需要退出
        }
        //如果(左子树的高度-右子树的高度) >1 那么左旋
        if((leftHeight() - rightHeight())>1){
            //如果它的 左子树的右子树大于 左子树先对左子树 进行左旋转
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
            }
        }
    }

    /**
     * @Description:  中序遍历
     * @Date:  2019/11/21
     * @Param: []
     * @return:  void
     */
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}