package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class SeatsDatabase {

    private LinkedList<Viewer> list;
    public static SeatsDatabase instance = null;

    private SeatsDatabase(){
        list = new LinkedList<Viewer>();
    }

    public static SeatsDatabase getSeatsDatabase(){
        if(instance == null){instance = new SeatsDatabase();}
        return instance;
    }

    public void addViewer(Viewer v){
        list.add(v);
    }

    public void removeViewer(Viewer v){
        list.remove(v);
    }

    public ObservableList<Viewer> getFullList(){
        return FXCollections.observableList(list);
    }
}
