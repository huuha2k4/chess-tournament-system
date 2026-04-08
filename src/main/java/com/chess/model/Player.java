package com.chess.model;

import com.chess.exception.InvalidDataException;

public class Player {
    private int id;
    private String name;
    private int elo;

    public Player(int id, String name,int elo){
        this.id=id;
        setName(name);
        setElo(elo);
    }

    public int getId(){ return id;}
    public String getName(){return name;}
    public int getElo(){ return elo;}

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        if(name.isEmpty()){
            throw new InvalidDataException("Ten khong duoc de trong.");
        }
        this.name=name;
    }
    public void setElo(int elo){
        if(elo<0 || elo>3000){
            throw new InvalidDataException("Elo phai tu 0 den 3000. Ban nhap: "+elo);
        }
        this.elo=elo;
    }

    @Override
    public String toString(){
        return String.format("%d %s %d",id,name,elo);
    }
}
