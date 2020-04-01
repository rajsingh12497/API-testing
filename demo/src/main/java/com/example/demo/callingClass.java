package com.example.demo;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class callingClass extends Thread {

    public synchronized void run() {
        try {
            demo.callAPIs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void callAPIs() throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callingClass thread = new callingClass();
                thread.start();
            }
        },new Date(),10);
    }
}
