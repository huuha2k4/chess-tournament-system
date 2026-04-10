package com.chess.service;

import java.util.*;

public interface CRUDService<T> {
    void add(T item);
    boolean deleteById(int id);
    ArrayList<T> getAll();
    ArrayList<T> searchByName(String name);
}
