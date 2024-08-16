package com.udb.utils;

import java.util.Scanner;

public class Console {
    public static String ReadString(String prompt) {
        System.out.print(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public static void WriteString(String prompt, boolean linebreak) {
        if (linebreak){
            System.out.println(prompt);
        }else{
            System.out.print(prompt);
        }
    }
}
