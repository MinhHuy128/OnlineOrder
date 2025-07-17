package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import Log.*;

public class File_Writer {
    BufferedWriter file_writer;
    BufferedReader buff_reader;
    Logger l = Logger.getInstance("fileLog.log");
    public BufferedWriter Writer(String file_path){
        try{
            this.file_writer = new BufferedWriter(new FileWriter(file_path, true));
        }catch(Exception e){
            l.log("Cannot Write in File", 3);
            e.printStackTrace();
        }
        return file_writer;
    }

    public List<String[]> Reader(String file_path){
        List<String[]> datas = new ArrayList<>();
        String line;
        try {
            this.buff_reader = new BufferedReader(new FileReader(file_path));
            while((line=buff_reader.readLine())!=null){
                String[] data = line.split(",");
                datas.add(data);
            }
        } catch (Exception e) {
            l.log("Cannot Read File", 3);
            e.printStackTrace();
        }
        return datas;
    }
}