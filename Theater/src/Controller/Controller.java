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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Viewer;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller{

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
    private ListView<String> ListViewForViewers;

    public int mode = 0;
    //-1 viewerblock
    //0 no init
    //1 Reserverblock

    public List<Viewer> ViewerList = new LinkedList<Viewer>();
    @FXML
    void onReserveButtonClick(ActionEvent event) {

    }

    @FXML
    void onViewButtonClick(ActionEvent event){
        if(mode == 0 || mode == 1){
            new Thread(genViewers).start();
        }
    }

    Task<Void> genViewers = new Task<Void>() {
        // Have to do this in a Task otherwise the program crashs
        @Override
        protected Void call() throws Exception {
            int viewerNumberID = 0;
            while (true) {
                try {
                    Thread.sleep(getSleepTime());
                } catch (Exception e) {
                }
                Viewer v = new Viewer(viewerNumberID);
                ViewerList.add(v);

                /*ListViewForViewers.setItems(FXCollections.observableList(
                        ViewerList.stream()
                                .map(x -> Integer.toString(v.getViewerNumberID())).collect(Collectors.toList())
                ));*/

                System.out.println("ViewID            " + viewerNumberID);
                viewerNumberID++;
            }
        }
    };

    int getSleepTime()
    {
        Random rand = new Random();
        int value = rand.nextInt(3 )  + 3;
        return value *1000;
    }

    @FXML
    void onTextfieldClick(ActionEvent event) {

    }



}
