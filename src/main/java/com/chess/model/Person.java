package com.chess.model;
import com.chess.exception.InvalidDataException;

public abstract class Person {
    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        setName(name); // Kiểm tra ngay từ lớp cha
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Ten khong duoc de trong.");
        }
        this.name = name;
    }

    public abstract String getRoleInfo();
}