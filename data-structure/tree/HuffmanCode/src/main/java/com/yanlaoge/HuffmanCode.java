package com.yanlaoge;


import java.io.*;
import java.util.*;


/**
 * @ClassName: HuffmanCode
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/20 8:09
 **/
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] huffmanBytes = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(huffmanBytes));
//
//        byte[] decode = decode(huffmanCodes, huffmanBytes);
//        System.out.println("解码后的: " + new String(decode));
        String srcFile = "C:\\Users\\niunan\\Desktop\\123.png";
        String dstFile = "C:\\Users\\niunan\\Desktop\\321.zip";
        zipFile(srcFile,dstFile);
    }

    /**
     * @Description:  解压
     * @Date:  2019/11/20
     * @Param: [zipFile, dstFile]
     * @return:  void
     */
    public static void unZip(String zipFile,String dstFile){
        //创建文件输入流
        InputStream is =null;
        //文件对象输入流
        ObjectInputStream ois = null;
        //文件输出流
        OutputStream os = null;
        try {
            //读取文件
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取字节
            byte[] huffmanBytes =  (byte[]) ois.readObject();
            //读取赫夫曼表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //写入文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(ois != null){
                    ois.close();
                }
                if(os != null){
                    os.close();
                }
            }catch (Exception e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description:  文件压缩
     * @Date:  2019/11/20
     * @Param: [srcFile, dstFile]
     * @return:  void
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建文件输入流,
        InputStream is = null;
        //创建输出流
        OutputStream os = null;
        //对象输出流
        ObjectOutputStream oos = null;
        try {
            //创建输入流
            is = new FileInputStream(srcFile);
            //将文件流转为byte数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //获取文件输出流
            os = new FileOutputStream(dstFile);
            //获取对象输出流
            oos = new ObjectOutputStream(os);
            //输出压缩后的编码
            //对象输出
            oos.writeObject(huffmanBytes);
            //需要将赫夫曼编码也输出, 用于解码
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(oos !=null){
                    oos.close();
                }
                if(os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * @Description:  解码
     * @Date:  2019/11/20
     * @Param: [huffmanCodes, huffmanBytes]
     * @Param: huffmanCodes 赫夫曼编码表
     * @Param: huffmanBytes 赫夫曼字节数组
     * @return:  byte[]
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //新建一个拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        //循环进行拼接
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag =  i == huffmanBytes.length -1;
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //把字符串按照赫夫曼解码
        //需要先将赫夫曼表key和val对换,生成一个新的map, 解码用
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        //将上面的字符串,进行解码,放入一个集合
        List<Byte> list = new ArrayList<>();
        //需要遍历字符串
        for (int i = 0; i <stringBuilder.length() ;) {
            boolean flag = true;
            int count = 1;
            Byte b = null;
            while (flag){
                b = map.get(stringBuilder.substring(i, i + count));
                if(b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b); //添加到集合
            i += count; // 移动到i+count 的位置
        }
//        System.out.println(list);
        //循环结束,集合中的内容已经是字符了
        // 转换为byte
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * @Description:  讲一个byte转成一个二进制字符串
     * @Date:  2019/11/20
     * @Param: flag 是否补高位
     * @Param: b 单个字节
     * @return:  java.lang.String
     */
    private static String byteToBitString(boolean flag , byte b){
        //使用变量保存b
        int temp =b;
        //如果是正数,需要补高位
        if(flag){
            temp |= 256;
        }
        //转换为二进制码
        String str = Integer.toBinaryString(temp);
        //如果补了高位, 就需要截取
        if(flag){
            return str.substring(str.length()-8);
        }else{
            //没补
            return str;
        }
    }


    private static byte[] huffmanZip(byte[] contentBytes) {
        //1.将byte数组转为NodeList, 每个节点代表的是出现的字符和权重
        List<Node> nodes = getNodes(contentBytes);
        //2.创建为赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //3.根据路径转换为赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //4.将编码压缩为byte数组
        byte[] huffmanBytes = zip(contentBytes, huffmanCodes);
        return huffmanBytes;
    }

    //处理赫夫曼编码后的数组
    // bytes : 原始数组
    // 编码表
    // 返回huffman编码后的数组
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        //先将 huffmancode 转换为 字符串
        for (byte by : bytes) {
            stringBuilder.append(huffmanCodes.get(by));
        }
        //创建一个byte数组
        // 获取byte数组的长度
        int len;
        if(stringBuilder.length() % 8 ==0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 +1;
        }
        // 创建
        byte[] huffmanBytes = new byte[len];
        //将字符串每8 转成一个byte, 组成一个byte数组
        int index = 0; // 数组下标
        for (int i = 0;i<stringBuilder.length(); i+=8){
            String str;
            //如果i+8大于,也就是说, 到最后一个元素了, 这个元素不满足8,就直接全取
            if(i+8 >stringBuilder.length()){
                str = stringBuilder.substring(i);
            }else{
                str = stringBuilder.substring(i, i + 8);
            }
            //转为2byte添加数组
            huffmanBytes[index] = (byte)Integer.parseInt(str,2);
            index ++;
        }
        // 最后返回
        return  huffmanBytes;
    }


    //拼接路径,存储
    static StringBuilder stringBuilder = new StringBuilder();
    //结果集
    static Map<Byte,String> huffmanCodes = new HashMap<>();

    private static Map<Byte,String> getCodes(Node root){
       if(root ==null){
           return null;
       }
       getCodes(root.left,"0",stringBuilder);
       getCodes(root.right,"1",stringBuilder);
       return huffmanCodes;
    }

    //生成赫夫曼编码
    //node: 节点
    //code: 节点的编码
    //stringBuilder: 字符串拼接
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到stringbuilder
        stringBuilder1.append(code);
        //递归调用
        if(node !=null){
            //判断是叶子节点还是非叶子节点,如果是叶子节点, 进行处理
            if(node.data == null){
                //向左递归
                getCodes(node.left,"0",stringBuilder1);
                //向右递归
                getCodes(node.right,"1",stringBuilder1);
            }else{
                //叶子节点,说明拼接完成, 添加到map
                huffmanCodes.put(node.data,stringBuilder1.toString());
            }
        }
    }

    //将一个字节数组转为node的列表
    public static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();
        Map<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.put(b,map.getOrDefault(b,0)+1);
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() >1){
            //排序,将最小的放前面
            Collections.sort(nodes);
            //取出前两个
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //创建一个parent
            Node parent = new Node(null, left.weight + right.weight);
            //将两个挂载到paren上
            parent.left = left;
            parent.right = right;
            //删除前两个元素
            nodes.remove(left);
            nodes.remove(right);
            //添加paren到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("空");
        }
    }
}

class Node implements  Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
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
