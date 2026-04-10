package com.chess.model;

public class Referee extends Person {
    private String license;

    public Referee(int id, String name,String license){
        super(id,name);
        this.license=license;
    }

    @Override
    public String getRoleInfo(){
        return "Role: trong tai - bang cap:"+license;
    }

    @Override
    public String toString(){
        return String.format("REF [%d] %s - License: %s",id,name,license);
    }
}
