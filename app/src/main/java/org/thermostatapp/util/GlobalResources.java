package org.thermostatapp.util;

import android.app.Application;

import java.net.ConnectException;

/**
 * Created by gsmet on 23-Jun-17.
 */

public class GlobalResources extends Application {
    public WeekProgram wpg;
    public double dayTemp, nightTemp, overrideTemp, vacTemp;

    @Override
    public void onCreate() {
        super.onCreate();

        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/12";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wpg = HeatingSystem.getWeekProgram();
                } catch (ConnectException e) {
                    e.printStackTrace();
                } catch (CorruptWeekProgramException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
