package com.chess.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chess.model.Player;

public class ConsoleUI {
    public void printMenu(){
        System.out.println("\n--- QUAN LY GIAI DAU CO VUA ---");
        System.out.println("1. Xem bang xep hang (Elo giam dan)");
        System.out.println("2. Them ki thu moi");
        System.out.println("3. Thong ke (Elo max, trung binh)");
        System.out.println("4. Tim kiem ki thu theo ten");
        System.out.println("5. Xoa 1 ki thu theo id");
        System.out.println("6. Tim kiem ki thu trong khoang tw chon");
        System.out.println("7. Cap nhat elo moi cho ki thu theo id");
        System.out.println("8. Thong ke ki thu theo cap bac");
        System.out.println("9. Loc danh sach ki thu theo cap bac");
        System.out.println("0. Thoat va Luu du lieu");
    }

    public void displayPlayers(ArrayList<Player> list){
        if(list.isEmpty()){
            System.out.println("Danh sach trong!");
        }
        else{
            for(Player p:list) System.out.println(p);
        }
    }

    public int getIntInput(Scanner sc, String message){
        while(true){
            try{
                System.out.println(message);
                return sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Vui long nhap so nguyen!");
                sc.nextLine();
            }
        }
    }

    public String getStringInput(Scanner sc, String message){
        while(true){
            System.out.println(message+": ");
            String input = sc.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            }
            System.out.println("Loi: ten khong de trong!");
        }
    }

    public boolean getConfirm(Scanner sc, String message){
        while(true){
            System.out.println(message+ " (Y/N): " );
            String choice = sc.next().trim().toUpperCase();
            if(choice.equals("Y")) return true;
            if(choice.equals("N")) return false;
            System.out.println("loi: vui long chi chon Y hoac N");
        }
    }
}
