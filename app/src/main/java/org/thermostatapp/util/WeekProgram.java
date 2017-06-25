/**
 * @author HTI students, Spring 2013, adjusted by N.Stash
 */
package org.thermostatapp.util;

import java.util.ArrayList;
import java.util.HashMap;

public class WeekProgram {
    /* Switches are stored in a hashmap, mapping every day to its
    corresponding set of switches */
    public HashMap<String, ArrayList<Switch>> data = new HashMap<String, ArrayList<Switch>>();
    private int[] nr_switches_active;
    public static String[] valid_days = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"};

    /**
     * Constructor
     */
    public WeekProgram() {
        setDefault();
    }

    /**
     * Creates the default week program
     */
    public void setDefault() {
        nr_switches_active = new int[7];
        for (int i = 0; i < this.valid_days.length; i++) {
            nr_switches_active[i] = 5;
            String day = this.valid_days[i];
            this.data.put(day, new ArrayList<Switch>());
            this.data.get(day).add(new Switch("night", false, "00:00"));
            this.data.get(day).add(new Switch("night", false, "00:00"));
            this.data.get(day).add(new Switch("night", false, "00:00"));
            this.data.get(day).add(new Switch("night", false, "00:00"));
            this.data.get(day).add(new Switch("night", false, "00:00"));
            this.data.get(day).add(new Switch("day", false, "00:00"));
            this.data.get(day).add(new Switch("day", false, "00:00"));
            this.data.get(day).add(new Switch("day", false, "00:00"));
            this.data.get(day).add(new Switch("day", false, "00:00"));
            this.data.get(day).add(new Switch("day", false, "00:00"));
        }
        /* Create the default switches settings*/
        set_durations();
    }

    public ArrayList<Switch> getDayProgram(int day) {
        return this.data.get(valid_days[day]);
    }

    public String toXML() throws NullPointerException {
        StringBuilder build = new StringBuilder();
        String prefix;
        String suffix = "</week_program>";
        if (!HeatingSystem.getVacationMode())
            prefix = "<week_program state=\"on\">";
        else
            prefix = "<week_program state=\"off\">";

        // Add prefix.
        build.append(prefix).append("\n");
        // Construct all the days.
        for (int i = 0; i < this.valid_days.length; i++) {
            // Add the day
            String day = this.valid_days[i];

            build.append("<day name=\"" + day + "\">").append("\n");

            // Add the switches.
            ArrayList<Switch> switches = this.data.get(day);
            if (switches != null) {
                for (Switch sw : switches) {
                    build.append(sw.toXMLString()).append("\n");
                }
            }
            // Closing day tag.
            build.append("</day>").append("\n");
        }

        // Add suffix.
        build.append(suffix);

        return build.toString();
    }

    public boolean duplicates(ArrayList<Switch> switches) {
        boolean duplicatesFound = false;
        for (int i = 0; i < (switches.size() - 2) && !duplicatesFound; i++) {
            for (int j = i + 1; j < switches.size() - 1; j++) {
                if (switches.get(i).getState() && switches.get(j).getState() &&
                        switches.get(i).getType().equals(switches.get(j).getType()) &&
                        switches.get(i).getTime().equals(switches.get(j).getTime())) {
                    duplicatesFound = true;
                    break;
                }
            }
        }
        return duplicatesFound;
    }


    public void set_durations() {
        for (int i = 0; i < this.valid_days.length; i++) {

            for (int j = 0; j < data.get(valid_days[i]).size() - 1; j++) {
                if (data.get(valid_days[i]).get(j + 1).getState())
                    data.get(valid_days[i])
                            .get(j)
                            .setDur(data.get(valid_days[i]).get(j + 1)
                                    .getTime_Int()
                                    - data.get(valid_days[i]).get(j)
                                    .getTime_Int());
                else
                    data.get(valid_days[i])
                            .get(j)
                            .setDur(2400 - data.get(valid_days[i]).get(j)
                                    .getTime_Int());
            }
            if (this.nr_switches_active[i] == 10)
                data.get(valid_days[i])
                        .get(9)
                        .setDur(2400 - data.get(valid_days[i]).get(9)
                                .getTime_Int());
        }

    }

    public void set_switches_active(int i, int nr) {
        this.nr_switches_active[i] = nr;
    }

    //Setting switches. Switches list should always exactly consist out of 10 elements.
    //* @param day
    //* @param switches_list
    //* @param nr_switches
    public void setSwitches(String day, ArrayList<Switch> switches_list,
                            int nr_switches) {
        // Validate input???
        for (String d : this.valid_days) {
            if (d.equalsIgnoreCase(day)) {
                this.data.put(d, switches_list);
                for (int i = 0; i < 7; i++)
                    if (day.equalsIgnoreCase(WeekProgram.valid_days[i]))
                        this.nr_switches_active[i] = nr_switches;
            }
        }
        set_durations();
    }

}