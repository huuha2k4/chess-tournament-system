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
}
