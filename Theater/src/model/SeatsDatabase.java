package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class SeatsDatabase {

    private LinkedList<Viewer> list;
    public static SeatsDatabase instance = null;
    public  LinkedList<Reserver> seatArray = new LinkedList<Reserver>();

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

    public boolean reserveSeat(Reserver r){
        if(seatArray.size() <= 100){
            seatArray.add(r);
            return true;
        }
        return false;
    }

    public int getFreeSeats(){
        return 100 - seatArray.size();
    }
}
