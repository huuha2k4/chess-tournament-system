package com.chess.service;

import com.chess.model.Player;
import com.chess.model.Rank;
import com.chess.util.MenuHelper;

import java.util.*;
import java.util.stream.Collectors;

// Hà nhớ phải có dòng implements này thì @Override mới có tác dụng!
public class PlayerService implements CRUDService<Player> {
    private ArrayList<Player> listPlayer;

    public PlayerService(ArrayList<Player> list) {
        this.listPlayer = list;
    }

    @Override
    public void add(Player p) {
        listPlayer.add(p);
    }

    @Override
    public boolean deleteById(int id) {
        return listPlayer.removeIf(p -> p.getId() == id);
    }

    @Override
    public ArrayList<Player> getAll() {
        return listPlayer;
    }

    @Override
    public ArrayList<Player> searchByName(String name) {
        return listPlayer.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // --- CÁC HÀM NÂNG CAO DÙNG STREAM ---

    public double calculateAverage() {
        return listPlayer.stream()
                .mapToInt(Player::getElo)
                .average()
                .orElse(0.0);
    }

    public List<Player> getTop3Players() {
        return listPlayer.stream()
                .sorted((p1, p2) -> p2.getElo() - p1.getElo())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Player findMaxElo() {
        return listPlayer.stream()
                .max(Comparator.comparingInt(Player::getElo))
                .orElse(null);
    }

    public ArrayList<Player> searchByEloRange(int min, int max) {
        return listPlayer.stream()
                .filter(p -> p.getElo() >= min && p.getElo() <= max)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Player> filterByRank(String targetRank) {
        return listPlayer.stream()
                .filter(p -> getRank(p.getElo()).toLowerCase().contains(targetRank.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // --- CÁC HÀM LOGIC HỖ TRỢ ---

    public String getRank(int elo) {
        if (elo >= 2500) return "Dai su (Grandmaster)";
        if (elo >= 2000) return "Kien tuong (Master)";
        if (elo >= 1500) return "Chuyen nghiep (Pro)";
        return "Nghiep du (Amateur)";
    }

    public void sortByID() {
        listPlayer.sort(Comparator.comparingInt(Player::getId));
    }

    public void sortByName() {
        listPlayer.sort(Comparator.comparing(Player::getName));
    }

    public boolean updatePlayerElo(int id, int newElo) {
        for (Player p : listPlayer) {
            if (p.getId() == id) {
                p.setElo(newElo);
                return true;
            }
        }
        return false;
    }

    public int getNextId() {
        return listPlayer.stream()
                .mapToInt(Player::getId)
                .max()
                .orElse(0) + 1;
    }

    public void showStatistics() {
        long gm = listPlayer.stream().filter(p -> getRank(p.getElo()).contains("Dai su")).count();
        long master = listPlayer.stream().filter(p -> getRank(p.getElo()).contains("Kien tuong")).count();
        long pro = listPlayer.stream().filter(p -> getRank(p.getElo()).contains("Chuyen nghiep")).count();
        long amateur = listPlayer.size() - (gm + master + pro);

        System.out.println("--- THONG KE THEO CAP BAC ---");
        System.out.println("Dai su: " + gm);
        System.out.println("Kien tuong: " + master);
        System.out.println("Chuyen nghiep: " + pro);
        System.out.println("Nghiep du: " + amateur);
    }

    public void showStatistics(){
        MenuHelper.printHeader("Thong ke giai dau");

        for(Rank r: Rank.values()){
            long count = listPlayer.stream().filter(p -> p.getRank()==r).count();
            System.out.println(r.getDisplayName()+": "+count);
        }
    }
}