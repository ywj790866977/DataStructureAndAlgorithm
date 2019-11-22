import javax.management.NotificationEmitter;
import java.lang.reflect.Parameter;

/**
 * @ClassName: BinarySortTreeDemo
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/20 18:18
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i <arr.length ; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        binarySortTree.del(7);
        System.out.println("删除后");
        binarySortTree.infixOrder();

    }

}

class BinarySortTree{
    private Node root;

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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
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
    }

    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}
