package com.chess.util;

public class MenuHelper {
    public static void printHeader(String title){
        System.out.println("\n===========================");
        System.out.println("   "+title.toUpperCase());
        System.out.println("===========================");
    }

    public static void printSuccess(String msg){
        System.out.println("[SUCCESS] "+ msg);
    }
}
