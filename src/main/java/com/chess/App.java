package com.chess;

import java.util.*;

import com.chess.model.Player;
import com.chess.service.FileService;
import com.chess.service.PlayerService;
import com.chess.view.ConsoleUI;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileService fileService = new FileService();
        PlayerService playerService = new PlayerService();
        ConsoleUI consoleUI = new ConsoleUI();
        // Nạp dữ liệu một lần duy nhất khi khởi động
        ArrayList<Player> listPlayer = fileService.loadFromFile("players.txt");

        while (true) {
            consoleUI.printMenu();
            
            int choice = consoleUI.getIntInput(sc, "Lua chon cua ban");
            
            switch (choice) {
                case 1:
                    System.out.println("1. Sap xep theo Elo (Giam dan)");
                    System.out.println("2. Sap xep theo ID (Tang Dan)");
                    System.out.println("3. Sap xep theo Ten (A-Z)");
                    int sortChoice = consoleUI.getIntInput(sc, "chon kieu hien thi");

                    if(sortChoice==1) listPlayer.sort((p1,p2) -> p2.getElo()-p1.getElo());
                    else if(sortChoice==2) playerService.sortByID(listPlayer);
                    else if(sortChoice==3) playerService.sortByName(listPlayer);

                    consoleUI.displayPlayers(listPlayer);
                    break;
                case 2:
                    int n = consoleUI.getIntInput(sc, "Nhap so ki thu can them");
                    int i=0;
                    while(i<n){
                            int id = playerService.getNextId(listPlayer);
                            sc.nextLine();
                            String name  = consoleUI.getStringInput(sc, "Nhap ten");
                            int elo = consoleUI.getIntInput(sc, "nhap elo");
                            listPlayer.add(new Player(id, name, elo));
                            i++;
                    }
                    fileService.saveToFile("players.txt", listPlayer);
                    break;
                case 3:
                    if (!listPlayer.isEmpty()) {
                        System.out.println("Elo Max: " + playerService.findMaxElo(listPlayer));
                        System.out.println("Elo TB: " + playerService.calculateAverage(listPlayer));
                    }
                    break;
                
                case 4:
                    System.out.print("Nhap ten ki thu can tim: ");
                    sc.nextLine();
                    String searchName = sc.nextLine();
                    ArrayList<Player> found = playerService.searchByName(listPlayer, searchName);
                    consoleUI.displayPlayers(found);
                    break;

                case 5:
                    int deleteId = consoleUI.getIntInput(sc, "Nhap id ki thu can xoa");
                    if(consoleUI.getConfirm(sc, "Ban co chac chan muon xoa ki thu nay khong?")){
                        if(playerService.deletePlayerById(listPlayer, deleteId)){
                            System.out.println("Xoa thanh cong!");
                            fileService.saveToFile("players.txt", listPlayer);
                        }
                        else{
                            System.out.println("Xoa that bai!");
                        }
                    } else{
                        System.out.println("Da huy lenh xoa.");
                    }
                    break;
                case 6:
                    System.out.print("Nhap khoang elo ma ban muon tim: ");
                    int min = consoleUI.getIntInput(sc, "Nhap elo min");
                    int max = consoleUI.getIntInput(sc, "Nhap elo max");
                    ArrayList<Player> found1 = playerService.searchByEloRange(listPlayer, min, max);
                    consoleUI.displayPlayers(found1);
                    break;
                case 7:
                    int updateId = consoleUI.getIntInput(sc, "Nhap ID ki thu can cap nhat Elo: ");
                    int newElo = consoleUI.getIntInput(sc, "Nhap so Elo moi: ");

                    if(playerService.updatePlayerElo(listPlayer, updateId, newElo)){
                        System.out.println("Cap nhat thanh cong!");
                        fileService.saveToFile("players.txt", listPlayer);
                    }else{
                        System.out.println("Khong tim thay ID ki thu");
                    }
                    break;
                case 8:
                    playerService.showStatistics(listPlayer);
                    break;
                case 9:
                    System.out.println("Nhap cap bac muon loc (Dai su, Kien tuong, Chuyen nghiep, Nghiep du");
                    sc.nextLine();
                    String rankName = sc.nextLine();
                    ArrayList<Player> filtered = playerService.filterByRank(listPlayer, rankName);
                    consoleUI.displayPlayers(filtered);
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
