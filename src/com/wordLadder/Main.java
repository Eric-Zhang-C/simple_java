package com.wordLadder;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;


public class Main {
    //static
    public static Map<String, Integer> word_sheet = new HashMap<String, Integer>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello");

        read_dict();
        //boolean cond = true;
        while(true){
            if (word_sheet.isEmpty()) System.out.println("empty");

            String first = readString("Word #1 (or Enter to quit)");
            if (first.equals("")) {
                scanner.close();
                return;
            }
            while (word_sheet.get(first) == null) {
                if (first.equals("")) {
                    scanner.close();
                    return;
                }
                first = readString("please enter the correct first word：");
            }

            String last = readString("Word #2 (or Enter to quit)");
            if (first.equals("")) {
                scanner.close();
                return;
            }
            while (word_sheet.get(last) == null || first.length() != last.length() || first.equals(last)) {
                if (first.equals("")) {
                    scanner.close();
                    return;
                }
                last = readString("please enter the correct second word：");
            }

            if (false)
                WordsSolitaire(first, last);
            else
                Word_ladder(first, last);
            for (Map.Entry<String, Integer> entry : word_sheet.entrySet()) {
                word_sheet.put(entry.getKey(),-1);
            }

        }
        //scanner.close();
    }
    public static void read_dict(){
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            String pathname = readString("请输入字典路径：src/com/wordLadder/smalldict1.txt or src/com/wordLadder/dictionary.txt\n");
            //String pathname = "/home/bian/Homework/hwk1/src/com/wordLadder/smalldict1.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            String line = br.readLine();

            while (line != null) {
                word_sheet.put(line,-1);
                line = br.readLine(); // 一次读入一行数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void WordsSolitaire(String first,String last){
        Vector<String> v = new Vector<String>(0);
        v.addElement(first);
        int len = first.length();
        int i = 0;
        char result = (v.get(i)).charAt(len - 1);
        String pre = (String.valueOf(result));
        while( i < v.size() && !last.startsWith(pre)){
            for (Map.Entry<String, Integer> entry : word_sheet.entrySet()) {
                if( (entry.getValue()== -1) && (entry.getKey().startsWith(pre))){
                    word_sheet.put(entry.getKey(), i);
                    v.addElement(entry.getKey());
                }
            }
            i++;
            len = v.get(i).length();
            result = (v.get(i)).charAt(len - 1);
            pre = String.valueOf(result);
        }

        if(last.startsWith(String.valueOf(result))){
            Vector<String> rlist = new Vector<String>(0);
            rlist.addElement(last);
            rlist.addElement(v.get(i));
            int temp = word_sheet.get(v.get(i));
            while(temp != 0){
                rlist.addElement(v.get(temp));
                temp = word_sheet.get(v.get(temp));
            }
            rlist.addElement(first);
            Enumeration vEnum = rlist.elements();
            System.out.println("\nElements in vector:");
            while(vEnum.hasMoreElements())
                System.out.print(vEnum.nextElement() + " ");
        }
    }
    public static void Word_ladder(String first,String last){
        Vector<String> v = new Vector<String>(0);
        v.addElement(first);
        int len = first.length();
        int i = 0;
        while( i < v.size() && !v.get(i).equals(last)){
            for(int j = 0; j < len; j++){
                for(int k = 0; k < 26; k++){
                    char x = "abcdefghijklmnopqrstuvwxyz".charAt(k);
                    String s1 = v.get(i).substring(0,j);
                    String s2 = v.get(i).substring(j+1);
                    String s = s1 + String.valueOf(x) + s2;
                    if(word_sheet.get(s)!=null){
                        if(word_sheet.get(s).equals(-1)){
                            word_sheet.put(s, i);
                            v.addElement(s);
                        }
                    }
                }
            }
            i++;
        }
        if(i >= v.size()){
            System.out.println("failure");
            return;
        }
        if(v.get(i).equals(last)){
            Vector<String> rlist = new Vector<String>(0);
            rlist.addElement(v.get(i));
            int temp = word_sheet.get(v.get(i));
            while(temp != 0){
                rlist.addElement(v.get(temp));
                temp = word_sheet.get(v.get(temp));
            }
            rlist.addElement(first);
            Enumeration vEnum = rlist.elements();
            System.out.println("\nA ladder from data to code:");
            while(vEnum.hasMoreElements())
                System.out.print(vEnum.nextElement() + " ");
            System.out.println("\n");
        }
    }
    private static String readString(String prompt) {
        System.out.print(prompt);
        String str = scanner.nextLine();
        return str;
    }
}
