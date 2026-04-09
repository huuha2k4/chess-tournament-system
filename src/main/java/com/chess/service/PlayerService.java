package com.chess.service;

import com.chess.model.Player;
import java.util.*;

public class PlayerService {
    public Player findMaxElo(ArrayList<Player> list){
        Player maxPlayer = list.get(0);
        for(Player play:list){
            if(play.getElo()>maxPlayer.getElo()){
                maxPlayer=play;
            }
        }
        return maxPlayer;
    }

    public double calculateAverage(ArrayList<Player> list){
        double totalElo =0;
        for(Player play:list){
            totalElo+=play.getElo();
        }
        return totalElo/list.size();
    }

    public ArrayList<Player> searchByName(ArrayList<Player> list, String name){
        ArrayList<Player> result = new ArrayList<>();
        for(Player play:list){
            if(play.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(play);
            }
        }
        return result;
    }

    public String getRank(int elo){
        if (elo>=2500) return "Dai su (Grandmaster)";
        if (elo>=2000) return "Kien tuong (Master)";
        if (elo>=1500) return "Chuyen nghiep (Pro)";
        return "Nghiep du (Amateur)";
    }

    public boolean deletePlayerById(ArrayList<Player> list, int id){
        return list.removeIf(p -> p.getId() == id);
    }

    public ArrayList<Player> searchByEloRange(ArrayList<Player> list, int min, int max){
        ArrayList<Player> result = new ArrayList<>();
        for(Player p:list){
            if(p.getElo()>=min && p.getElo()<=max){
                result.add(p);
            }
        }
        return result;
    }

    public void sortByID(ArrayList<Player> list){
        list.sort(Comparator.comparingInt(Player::getId));
    }
    public void sortByName(ArrayList<Player> list){
        list.sort(Comparator.comparing(Player::getName));
    }

    public boolean updatePlayerElo(ArrayList<Player> list , int id, int newElo){
        for(Player p:list){
            if( p.getId() == id){
                p.setElo(newElo);
                return true;
            }
        }
        return false;
    }
    public void showStatistics(ArrayList<Player> list){
        int gm=0, master=0, pro=0, amateur =0;
        for(Player p:list){
            String rank = getRank(p.getElo());
            if(rank.contains("Dai su")) gm++;
            else if(rank.contains("Kien tuong")) master++;
            else if(rank.contains("Chuyen nghiep")) pro++;
            else amateur++;
        }
        System.out.println("--- THONG KE THEO CAP BAC ---");
        System.out.println("Dai su: "+gm);
        System.out.println("Kien tuong: "+master);
        System.out.println("Chuyen nghiep: "+pro);
        System.out.println("Nghiep du: "+amateur);
    }

    public int getNextId(ArrayList<Player> list){
        if(list.isEmpty()) return 1;
        int maxId=0;
        for(Player p:list){
            if(p.getId()>maxId) maxId=p.getId();
        }
        return maxId+1;
    }

    public ArrayList<Player> filterByRank(ArrayList<Player> list, String tagetRank){
        ArrayList<Player> result = new ArrayList<>();
        for(Player p:list){
            if(getRank(p.getElo()).contains(tagetRank)){
                result.add(p);
            }
        }
        return result;
    }
}
