package model;

import java.util.Random;

public class Viewer implements Runnable
{

    public int viewTime;
    public int viewerNumberID;

    public Viewer(int viewerNumberID) {
        this.viewerNumberID = viewerNumberID;
        this.viewTime = getRandomViewTime();
    }

    public int getViewTime() {
        return viewTime;
    }
    public void setViewTime(int viewTime) {
        this.viewTime = viewTime;
    }
    // every viewer gets a random view time
    int getRandomViewTime(){
        Random rand = new Random();
        int value = rand.nextInt(3 )  + 3;
        return value;
    }
    public int getViewerNumberID() {
        return viewerNumberID;
    }
    @Override
    public void run() {

    }
}
