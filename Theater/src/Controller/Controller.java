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
    void onReserveButtonClick(ActionEvent event) {

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

    Task<Void> genViewers = new Task<Void>() {
        // Have to do this in a Task otherwise the program crashs
        @Override
        protected Void call() throws Exception {
            int viewerNumberID = 0;
            while (true) {
                try {
                    Viewer v = new Viewer(viewerNumberID);
                    v.run();
                    seats.addViewer(v);
                    waity.add(v);
                    System.out.println(ViewerList.size());
                    //TODO ADD Viewers to the listView then display them
                    listViewID.setItems(FXCollections.observableList(seats.getFullList()));
                    listViewID.refresh();
                    viewerNumberID++;
                    /*
                    if(waity.getFirst().IsDone){
                        seats.removeViewer(v);
                        listViewID.setItems(FXCollections.observableList(seats.getFullList()));
                        listViewID.refresh();
                    }*/
                    Thread.sleep(getSleepTime());

                }
                catch (Exception e) {
                }

            }
        }
    };

    int getSleepTime()
    {
        Random rand = new Random();
        int value = rand.nextInt(2 )  + 2 ;
        return value *1000;
    }

    @FXML
    void onTextfieldClick(ActionEvent event) {
        reserverBlock = true;
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
