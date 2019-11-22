package com.yanlaoge;

import javax.sound.midi.Soundbank;
import java.io.*;

/**
 * @ClassName: SparseArray
 * @Description:
 * @Author: niunan
 * @Create: 2019/11/4 18:21
 **/
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始数组,11*11
        //0表示没,1 黑, 2 蓝
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("==========原始数组==========");
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
//        System.out.println(sum);
        //创建稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //添加数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("==========稀疏数组==========");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

        File file = new File("C:\\Users\\niunan\\Desktop\\a.txt");
        FileWriter fileWriter = new FileWriter(file);

        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
                fileWriter.write(anInt+"\t");
            }
            fileWriter.write("\r\n");
        }
        fileWriter.close();


        //读取稀疏数组
        String line;
        int row = 0;
        int sparseArray2[][] = new int[3][3];
        BufferedReader in = new BufferedReader(new FileReader(file));
        while ((line = in.readLine()) != null ){
            String[] temp = line.split("\t");
            row++;
            for (int i = 0; i < temp.length; i++) {
                sparseArray2[row][i] = Integer.parseInt(temp[i]) ;
            }

        }
        sparseArray2[0][0] = 11;
        sparseArray2[0][1] = 11;
        sparseArray2[0][2] = row;
        System.out.println();
        System.out.println("=========文档中得稀疏数组==============");
        for (int[] ints : sparseArray2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }


        // 将稀疏数组 转换为  原始数组
        int chessArr2[][]  = new int[sparseArray[0][0]][sparseArray[0][1]];
//        for (int[] ints : chessArr2) {
//            for (int anInt : ints) {
//                System.out.printf("%d\t",anInt);
//            }
//            System.out.println();
//        }
        for (int i = 1; i < sparseArray.length ; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("==========y原始数组==========");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
