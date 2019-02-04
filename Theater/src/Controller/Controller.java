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
    private ListView<Viewer> ListViewForViewers = new ListView<Viewer>();
    DropShadow shadow = new DropShadow();
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
                //TODO ADD Viewers to the listView
                ListViewForViewers.setItems(FXCollections.observableList(ViewerList));
                ListViewForViewers.refresh();

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
