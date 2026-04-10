package com.chess.service;

import java.util.ArrayList;

public abstract class BaseService<T extends com.chess.model.Person> implements CRUDService<T> {
    protected ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item){
        list.add(item);
    }

    @Override
    public ArrayList<T> getAll(){
        return list;
    }
}
