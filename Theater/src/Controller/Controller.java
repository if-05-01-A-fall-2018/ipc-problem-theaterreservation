package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Reserver;
import model.SeatsDatabase;
import model.Viewer;

import java.net.URL;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private Button viewButton;
    @FXML
    private Button reserveButton;
    @FXML
    private Label freeSeatLable;
    @FXML
    private TextField firstNameTextfield;
    @FXML
    private TextField lastNameTextfield;
    @FXML
    private TextField seatNumberTextfield;

    @FXML
    private TextField emailTextfield;
    @FXML
    private ListView<Viewer> listViewID = new ListView<Viewer>();

    DropShadow shadow = new DropShadow();
    boolean viewerBlock = false;
    boolean reserverBlock = false;
    private SeatsDatabase seats = SeatsDatabase.getSeatsDatabase();

    //-1 viewerblock
    //0 no init
    //1 Reserverblock

    public List<Viewer> ViewerList = new LinkedList<Viewer>();
    @FXML
    void onReserveButtonClick(ActionEvent event) throws InterruptedException {
        Reserver r = new Reserver(firstNameTextfield.getText(), lastNameTextfield.getText(), emailTextfield.getText());
        seats.reserveSeat(r);
        freeSeatLable.setText(String.valueOf(seats.getFreeSeats()) + "/100");
        Thread.sleep(2000);
        firstNameTextfield.clear();
        lastNameTextfield.clear();
        emailTextfield.clear();
        reserverBlock = false;
    }

    @FXML
    void onViewButtonClick(ActionEvent event){
        if(viewerBlock == false && reserverBlock == false) { //application starrted no viewer and writer block active
            reserverBlock = true;
            listViewID.setItems(FXCollections.observableList(seats.getFullList()));
            listViewID.refresh();
            new Thread(genViewers).start();
        }
        else if(viewerBlock == true && reserverBlock == false){ //Reserver button was allready presst

        }
        else if(viewerBlock == false && reserverBlock == true){ // Viewer button was presst a second time

        }

    }

    LinkedList<Viewer> waity = new LinkedList<Viewer>();

    Viewer createViewer(){
        int viewerNumberID = 0;
        Viewer v = new Viewer(viewerNumberID);
        seats.addViewer(v);
        waity.add(v);
        System.out.println(ViewerList.size());
        //TODO ADD Viewers to the listView then display them
        listViewID.setItems(FXCollections.observableList(seats.getFullList()));
        listViewID.refresh();
        System.out.println("ViewID            " + viewerNumberID);
        viewerNumberID++;
        return v;
    }

    public boolean fiveS(long end){
        boolean five = false;
        long now = new Date().getTime();
        if(now >= end){
            five = true;
        }
        return five;
    }

    Task<Void> genViewers = new Task<Void>() {
        // Have to do this in a Task otherwise the program crashs
        @Override
        protected Void call() throws Exception {
            long start = new Date().getTime();
            long end = start + 5000;
            Viewer v = createViewer();


            while (true) {
                try {
                    if(fiveS(end) && !reserverBlock){
                        start = new Date().getTime();
                        end = start + 5000;
                        createViewer();
                    }
                    //Thread.sleep(getSleepTime());

                    if(waity.getFirst()!= null){
                        if(waity.getFirst().isDone()){
                            seats.removeViewer(waity.getFirst());
                            waity.remove(waity.getFirst());
                            listViewID.setItems(FXCollections.observableList(seats.getFullList()));
                            listViewID.refresh();
                        }
                    }

                }
                catch (Exception e) {
                }

            }
        }
    };

    /*int getSleepTime()
    {
        Random rand = new Random();
        int value = rand.nextInt(3 )  + 3;
        return value *1000;
    }*/

    @FXML
    void onTextfieldClick(ActionEvent event) {
        reserverBlock = true;
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        freeSeatLable.setText("100/100");
    }
}
