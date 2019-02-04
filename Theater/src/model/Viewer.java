package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Viewer implements Runnable
{
    public long viewTime;
    public int viewerNumberID;
    long  start = new Date().getTime();
    long end ;

    public Viewer(int viewerNumberID) {
        this.viewerNumberID = viewerNumberID;
        this.viewTime = getRandomViewTime();
        this.end = start + (6 * 1000) ;
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
        int value = rand.nextInt(6 )  + 9;
        return value;
    }
    public int getViewerNumberID() {
        return viewerNumberID;
    }
    @Override
    public void run() {
    }

    public boolean isDone(){
        if(  new Date().getTime() >= end){ return true; }
        return false;
    }
}
