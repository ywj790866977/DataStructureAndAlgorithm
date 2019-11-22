package com.yanlaoge;

/**
 * @ClassName: Calculator
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/9 16:46
 **/
public class Calculator {
    public static void main(String[] args) {
        //表达式
        String expression = "70+2*6-2";
        //准备需要的变量
        int index = 0;  // 索引
        int num1 = 0;
        int num2 = 0;
        int oper = 0;  // 运算符
        int res = 0;   // 结果
        char ch = ' ';
        String keepNum = "";
        //准备需要的栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //开始扫描express
        while (true){
            //取出第一个字符
            ch = expression.substring(index, index+1).charAt(0);
            //判断是什么类型
            if(operStack.isOper(ch)){
                //是运算符
                //入栈前判断栈是否为空
                if(!operStack.isEmpty()){
                    //不为空, 需要判断当前运算符和上一个运算符等级,
                    if(operStack.priority(ch) <= operStack.priority(operStack.getTopOper())){
                        //当前运算符等级小于上一个,把前面两个数进行运算
                        //取出
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将计算结果放入num栈
                        numStack.push(res);
                        //将当前运算符放入oper栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //为空
                    operStack.push(ch);
                }
            }else{
                //numStack.push(ch - 48); //对应asc码
                //数字
                //发现数字不能立即入栈,因为数字可能是多位
                keepNum += ch;
                //判断是否为最后一个, 最后一个直接添加,
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个数是否为字符
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //是运算符,就添加
                        numStack.push(Integer.parseInt(keepNum)); //对应asc码
                        // 一定要清零
                        keepNum = "";
                    }
                }


            }
            //索引增加
            index ++;
            //判断是否为结尾
            if(index >= expression.length()){
                break;
            }
        }
        //将栈中数进行运算
        while (true) {
            //判断运算符栈是否为空, 为空就代表没有运算了
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("表达式 "+expression+" = "+numStack.pop());
    }
}

class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断是否为满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //查看栈顶的运算符
    public int getTopOper(){
        return stack[top];
    }

    //判断运算符优先级,用数字表示,数字越大,优先级越高
    public int priority(int oper){
        if(oper == '+' || oper == '-'){
            return 0;
        }else if(oper == '*' || oper == '/'){
            return 1;
        }else{
            return -1;
        }
    }

    //判断是否是运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 前数减后数
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 * num1; //分清楚除数与被除数
                break;
            default:
                break;
        }
        return res;
    }

    //入栈
    public void push(int val){
        //判断是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=val;
    }

    //出栈
    public int pop(){
        //判断空
        if(isEmpty()){
            throw  new RuntimeException("栈空");
        }
        int val = stack[top];
        top--;
        return val;
    }

    //查看栈
    public void show(){
        //判断空
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}


