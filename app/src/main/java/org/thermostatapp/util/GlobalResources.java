package org.thermostatapp.util;

import android.app.Application;

import java.net.ConnectException;
import java.util.ArrayList;

import nl.tue.demothermostat.HomeFragment;

/**
 * Created by gsmet on 23-Jun-17.
 */

public class GlobalResources extends Application {
    private WeekProgram wpg;
    public double dayTemp = 30.0, nightTemp = 5.0;
    public boolean vac = false;

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
                    dayTemp = Double.valueOf(HeatingSystem.get("dayTemperature"));
                    nightTemp = Double.valueOf(HeatingSystem.get("nightTemperature"));
                } catch (ConnectException e) {
                    e.printStackTrace();
                } catch (CorruptWeekProgramException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long lastCheck = 0L;
                while (true) {
                    if (System.currentTimeMillis() - lastCheck > 4000) {
                        lastCheck = System.currentTimeMillis();
                        try {
                            HomeFragment.currentTemp = HeatingSystem.get("currentTemperature");
                            HomeFragment.handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    HomeFragment.tempChanged();
                                }
                            });
                        } catch (ConnectException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long lastCheck = 0L;
                while (true) {
                    if (System.currentTimeMillis() - lastCheck > 8000) {
                        lastCheck = System.currentTimeMillis();
                        try {
                            HomeFragment.sDay = HeatingSystem.get("day");
                            HomeFragment.sTime = HeatingSystem.get("time");
                            HomeFragment.handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    HomeFragment.dateAndTimeChanged();
                                }
                            });
                        } catch (ConnectException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public WeekProgram getLocalWeekProgram() {
        return wpg;
    }

    public WeekProgram getWeekProgramFromServer() {
        Thread t = new Thread(new Runnable() {
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
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return wpg;
    }

    public void setDayProgram(ArrayList<Switch> s, String day) {
        wpg.data.put(day, s);
    }

}
