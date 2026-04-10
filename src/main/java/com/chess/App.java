package com.chess;

import java.util.*;

import com.chess.exception.InvalidDataException;
import com.chess.model.Player;
import com.chess.service.*;
import com.chess.view.ConsoleUI;
import com.chess.model.Person;
import com.chess.model.Referee;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileService fileService = new FileService();
        ConsoleUI consoleUI = new ConsoleUI();
        
        // Nạp dữ liệu một lần duy nhất
        ArrayList<Player> data = fileService.loadFromFile("players.txt");
        PlayerService playerService = new PlayerService(data);

        ArrayList<Person> tournamentStaff = new ArrayList<>();

        tournamentStaff.add(new Player(1, "Nguyen Van A", 2800));
        tournamentStaff.add(new Referee(101,"Ong trong tai A","Quoc te"));

        System.out.println("-- DANH SACH NHAN SU GIAI DAU --");
        for(Person p:tournamentStaff){
            System.out.println(p.getName()+"|"+p.getRoleInfo());
        }

        while (true) {
            consoleUI.printMenu();
            int choice = consoleUI.getIntInput(sc, "Lua chon cua ban");
            
            switch (choice) {
                case 1: // Xem và Sắp xếp
                    System.out.println("1. Elo (Giam) | 2. ID (Tang) | 3. Ten (A-Z)");
                    int sortChoice = consoleUI.getIntInput(sc, "Chon kieu hien thi");
                    if(sortChoice == 1) playerService.getAll().sort(Comparator.comparingInt(Player::getElo).reversed());
                    else if(sortChoice == 2) playerService.sortByID();
                    else if(sortChoice == 3) playerService.sortByName();
                    consoleUI.displayPlayers(playerService.getAll());
                    break;
                
                case 2: // Thêm kì thủ
                    int n = consoleUI.getIntInput(sc, "Nhap so ki thu can them");
                    for(int i=0; i<n; i++){
                        try{
                            int id = playerService.getNextId();
                            sc.nextLine(); // Clear buffer
                            String name = consoleUI.getStringInput(sc, "Nhap ten");
                            int elo = consoleUI.getIntInput(sc, "Nhap Elo");
                            playerService.add(new Player(id, name, elo));
                        }catch(InvalidDataException e){
                            System.out.println("loi: "+e.getMessage());
                            i--;
                            System.out.println("Vui long thu lai cho ki thu nay.");
                        }
                    }
                    fileService.saveToFile("players.txt", playerService.getAll());
                    break;

                case 3: // Thống kê nhanh
                    if (!playerService.getAll().isEmpty()) {
                        System.out.println("Elo Max: " + playerService.findMaxElo());
                        System.out.println("Elo TB: " + playerService.calculateAverage());
                        System.out.println("Top 3 Ky thu: " + playerService.getTop3Players());
                    }
                    break;

                case 4: // Tìm theo tên
                    String searchName = consoleUI.getStringInput(sc, "Nhap ten can tim");
                    consoleUI.displayPlayers(playerService.searchByName(searchName));
                    break;

                case 5: // Xóa
                    int deleteId = consoleUI.getIntInput(sc, "Nhap ID can xoa");
                    if(consoleUI.getConfirm(sc, "Chac chan xoa?")){
                        if(playerService.deleteById(deleteId)){
                            System.out.println("Thanh cong!");
                            fileService.saveToFile("players.txt", playerService.getAll());
                        } else System.out.println("Khong tim thay ID!");
                    }
                    break;

                case 6: // Tìm theo khoảng Elo
                    int min = consoleUI.getIntInput(sc, "Min Elo");
                    int max = consoleUI.getIntInput(sc, "Max Elo");
                    consoleUI.displayPlayers(playerService.searchByEloRange(min, max));
                    break;

                case 7: // Cập nhật Elo
                    try{
                        int uId = consoleUI.getIntInput(sc, "ID can update");
                        int newElo = consoleUI.getIntInput(sc, "Elo moi");
                        if(playerService.updatePlayerElo(uId, newElo)){
                            System.out.println("Cap nhat thanh cong!");
                            fileService.saveToFile("players.txt", playerService.getAll());
                        } else System.out.println("That bai!");
                    } catch(InvalidDataException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8: // Thống kê chi tiết
                    playerService.showStatistics();
                    break;

                case 9: // Lọc theo cấp bậc
                    String rankName = consoleUI.getStringInput(sc, "Nhap cap bac (Dai su, Kien tuong...)");
                    consoleUI.displayPlayers(playerService.filterByRank(rankName));
                    break;

                case 0: // Thoát
                    fileService.saveToFile("players.txt", playerService.getAll());
                    System.out.println("Cam on ban! Tam biet.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}