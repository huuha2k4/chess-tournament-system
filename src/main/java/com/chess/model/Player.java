package com.chess.model;
import com.chess.exception.InvalidDataException;

public class Player extends Person {
    private int elo;

    public Player(int id, String name, int elo) {
        super(id, name); // Person sẽ lo id, name và validate name
        setElo(elo);
    }

    public int getElo() { return elo; }

    public void setElo(int elo) {
        if (elo < 0 || elo > 3000) {
            throw new InvalidDataException("Elo phai tu 0 den 3000. Ban nhap: " + elo);
        }
        this.elo = elo;
    }

    public Rank getRank(){
        return Rank.fromElo(this.elo);
    }

    @Override
    public String getRoleInfo() {
        return "Role: Ky thu - Elo: " + elo;
    }

    @Override
    public String toString() {
        return String.format("%d - %s - Elo: %d", id, name, elo);
    }
}