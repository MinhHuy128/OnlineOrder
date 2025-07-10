package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class File_Writer {
    FileWriter file_writer;
    BufferedReader buff_reader;

    public FileWriter Writer(String file_path){
        try{
            File file = new File(file_path);
            this.file_writer = new FileWriter(file, true);
        }catch(Exception e){
            System.out.println("Cannot Write!");
            e.printStackTrace();
        }
        return file_writer;
    }

    public List<String[]> Reader(String file_path){
        List<String[]> datas = new ArrayList<>();
        String line;
        try {
            File file = new File(file_path);
            this.buff_reader = new BufferedReader(new FileReader(file));
            while((line=buff_reader.readLine())!=null){
                String[] data = line.split(",");
                datas.add(data);
            }
        } catch (Exception e) {
            System.out.println("Cannot Read!");
            e.printStackTrace();
        }
        return datas;
    }
}