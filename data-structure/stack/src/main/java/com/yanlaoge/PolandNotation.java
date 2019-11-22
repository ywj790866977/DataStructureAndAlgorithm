package com.yanlaoge;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: PolandNotation
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/10 9:53
 **/
public class PolandNotation {
    public static void main(String[] args) {
        //表达式
        // (3 + 4) * 5 - 6
        //逆波兰表达式
//        String  suffixExpression = "3 4 + 5 * 6 - ";
//        List<String> listString = getListString(suffixExpression);
//        System.out.println(listString);
//        int rs = calculate(listString);
//        System.out.println(rs);

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println(strings);
        List<String> strings1 = parseSuffixExpressionList(strings);
        System.out.println(strings1);

        System.out.println("exoression="+calculate(strings1));

    }

    //中缀转后缀
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //创建一个数栈
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        //开始遍历判断
        for(String item : ls){
            //如果是数字直接放入数组
            if(item.matches("\\d+")){
                //数字
                s2.add(item);
            }else if(item.equals("(")){
                //如果是( 放入栈中
                s1.push(item);
            }else if(item.equals(")")){
                //如果是 )
                //依次弹出s1栈顶的运算符,并压入s2, 直到遇到 ( 位置位置结束, 并丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                //丢弃 (
                s1.pop();
            }else{
                //当item的优先级小于等于栈顶运算符,将s1栈顶的运算符弹出压入s2,再次执行与新的比较
                while (s1.size() != 0 &&   Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将剩余的数据添加到s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀转换为对应的list
    public static List<String> toInfixExpressionList(String s){
        //创建一个列表
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str; // 多位拼接
        char c ;   // 当前字符
        do {
            if((c = s.charAt(i)) <48 || (c = s.charAt(i)) >57  ){
                //非数字
                ls.add(""+c);
                i++;
            }else{
                //数字
                str = "";
                //先判断是否为多位数
                if(i<s.length() && (c = s.charAt(i)) >=48 && (c = s.charAt(i))<=57  ){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    //将suffix转换为列表
    public static List<String > getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String el:split){
            list.add(el);
        }
        return list;
    }
    /*
    * 1. 从左至右扫描,将3 ,4 压入栈
    * 2. 遇到 + 运算符,将 3, 4 弹出栈, 3 + 4 结果为7,将7压入栈
    * 3. 将5 入栈
    * 4. 遇到 x 运算符, 将 5 和 7 弹出栈, 5 X 7 = 35 ;将35 入栈
    * 5. 将6 入栈
    * 6. 遇到运算符 - , 将6 和 35 弹出, 计算35- 6 = 29 结果,为29
    * */
    public static int calculate(List<String> ls){
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历列表
        for (String item : ls) {
            //判断item为什么类型
            if(item.matches("\\d+")){
                //是数字
                stack.push(item);
            }else{
                //运算符
                //弹出两个数,进行运算,再将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //运算
                if(item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw  new RuntimeException("运算符不正确");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String oper){
        int res = 0;
        switch (oper){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("没有对应的运算符");
                break;
        }
        return res;
    }
}