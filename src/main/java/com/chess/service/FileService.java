package com.chess.service;

import java.io.*;
import java.util.*;
import com.chess.exception.InvalidDataException;
import com.chess.model.Player;

public class FileService {
    public void saveToFile(String fileName, ArrayList<Player> list){
        try(PrintWriter pw = new PrintWriter(new File(fileName))){
            for(Player p:list){
                pw.println(p.getId()+","+p.getName()+","+p.getElo());
            }
            System.out.println("Luu file thanh cong vao: "+fileName);
        } catch(Exception e){
            System.out.println("Loi khi luu file: "+e.getMessage());
        }
    }

    public ArrayList<Player> loadFromFile(String nameFile){
        ArrayList<Player> list = new ArrayList<>();
        // mở luồng đọc
        try(BufferedReader bs = new BufferedReader(new FileReader(nameFile))){
            // đọc từng dòng
            String line;
            while((line = bs.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                // chẻ nhỏ dòng đó ra để lấy dữ liệu
                StringTokenizer st = new StringTokenizer(line,",");
                int id = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                int elo = Integer.parseInt(st.nextToken());

                list.add(new Player(id, name, elo));
            }
        } catch(Exception e){
            System.out.println("Chua co du lieu cu hoac loi doc file: "+ e.getMessage());
        }
        return list;
    }
}
