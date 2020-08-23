package com.yoa;

import java.io.*;
import java.math.BigInteger;

public class test {
    public static void test() throws IOException {
        File rf=new File("D:\\1.txt");
        File wf=new File("D:\\2.txt");
        if (!wf.exists()) wf.createNewFile();
        FileReader br = new FileReader(rf);
        FileWriter bw = new FileWriter(wf);
        char[] b=new char[1024];
        int n;
        String a=null;
        while ((n=br.read(b))!=-1) {
            a=new String(b,0,n);
        }
        System.out.println(a+"ss");
        String strArr[] = a.split(",");
        int numArr[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++)
            numArr[i] = Integer.valueOf(strArr[i]);
        //排序
        for (int i = 0; i < numArr.length; i++) {
            for (int j = 0; j < numArr.length; j++) {
                if(numArr[i]<numArr[j]){
                    numArr[i]=numArr[i]+numArr[j];
                    numArr[j]=numArr[i]-numArr[j];
                    numArr[i]=numArr[i]-numArr[j];
                }
            }
        }
        for (int i=0;i<numArr.length;i++)
            if (i!=numArr.length-1)
                bw.write(numArr[i]+",");
            else
                bw.write(numArr[i]+"");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        //a,aa,aaa,aaaa,aaab,aaac,aaad,aaae...

        //b,ba,baa,baaa,baab,baac
        String str="aaba";
        int answer=0;
        switch (str.length()){
            case 1:
                answer=f1(str);
                break;
            case 2:
                answer=f2(str);
                break;
            case 3:
                answer=f3(str);
                break;
            case 4:
                answer=f4(str);
                break;
        }
        System.out.println(answer);
    }
    public static int f4(String str){
        int answer;
        answer=(str.charAt(3)-'a')*(int)Math.pow(25,0)
                +(str.charAt(2)-'a')*(int)Math.pow(25,1)
                +(str.charAt(1)-'a')*(int)Math.pow(25,2)
                +(str.charAt(0)-'a')*(int)Math.pow(25,3)
                +(str.charAt(0)-'a'+1)*3;
        return answer;
    }
    public static int f3(String str){
        int answer;
        answer=f4(str.charAt(0)+"aaa")-1;
        return answer;
    }
    public static int f2(String str){
        int answer;
        answer=f4(str.charAt(0)+"aaa")-2;
        return answer;
    }
    public static int f1(String str){
        int answer;
        answer=f4(str.charAt(0)+"aaa")-3;
        return answer;
    }
}
