package com.chess;

import java.util.*;

import com.chess.model.Player;
import com.chess.service.FileService;
import com.chess.service.PlayerService;
import com.chess.service.FileService;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileService fileService = new FileService();
        PlayerService playerService = new PlayerService();
        
        // Nạp dữ liệu một lần duy nhất khi khởi động
        ArrayList<Player> listPlayer = fileService.loadFromFile("players.txt");

        while (true) {
            System.out.println("\n--- QUAN LY GIAI DAU CO VUA ---");
            System.out.println("1. Xem bang xep hang (Elo giam dan)");
            System.out.println("2. Them ki thu moi");
            System.out.println("3. Thong ke (Elo max, trung binh)");
            System.out.println("0. Thoat va Luu du lieu");
            System.out.print("Lua chon cua ban: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    listPlayer.sort((p1, p2) -> p2.getElo() - p1.getElo());
                    System.out.println("DANH SACH KI THU:");
                    for (Player p : listPlayer) System.out.println(p);
                    break;
                case 2:
                    System.out.print("Nhap so ki thu can them: ");
                    int n = sc.nextInt();
                    int i=0;
                    while(i<n){
                        try{
                            int id = sc.nextInt();
                            sc.nextLine();
                            String name  = sc.nextLine();
                            int elo = sc.nextInt();
                            listPlayer.add(new Player(id, name, elo));
                            i++;
                        } catch(Exception e){
                            System.out.println("loi: "+e.getMessage());
                            sc.nextLine();
                            System.out.println("Nhap lai");
                        }
                    }
                    fileService.saveToFile("players.txt", listPlayer);
                    break;
                case 3:
                    if (!listPlayer.isEmpty()) {
                        System.out.println("Elo Max: " + playerService.findMaxElo(listPlayer));
                        System.out.println("Elo TB: " + playerService.calculateAverage(listPlayer));
                    }
                    break;
                case 0:
                    fileService.saveToFile("players.txt", listPlayer);
                    System.out.println("Cam on ban da su dung! Tam biet.");
                    return; // Thoát chương trình
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
