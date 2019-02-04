package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Viewer extends Thread
{
    public long viewTime;
    public int viewerNumberID;
    public boolean IsDone;

    public Viewer(int viewerNumberID) {
        this.viewerNumberID = viewerNumberID;
        this.viewTime = getRandomViewTime();
        this.IsDone = false;
    }

    public long getViewTime() {
        return viewTime;
    }
    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }

    // every viewer gets a random view time
    int getRandomViewTime(){
        Random rand = new Random();
        int value = rand.nextInt(6 )  + 10;
        return value;
    }
    public int getViewerNumberID() {
        return viewerNumberID;
    }

    @Override
    public void run() {
        try {
            System.out.println("run viewer" + this.getViewerNumberID());
            Thread.sleep(viewTime * 1000);
            System.out.println("            End Viewer" + this.getViewerNumberID());
            this.IsDone = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
