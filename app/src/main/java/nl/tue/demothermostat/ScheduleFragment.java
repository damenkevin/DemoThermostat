package nl.tue.demothermostat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.thermostatapp.util.GlobalResources;
import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ScheduleFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_schedule, container, false);

        Button monday = (Button) myFragmentView.findViewById(R.id.monday);
        Button tuesday = (Button) myFragmentView.findViewById(R.id.tuesday);
        Button wednesday = (Button) myFragmentView.findViewById(R.id.wednesday);
        Button thursday = (Button) myFragmentView.findViewById(R.id.thursday);
        Button friday = (Button) myFragmentView.findViewById(R.id.friday);
        Button saturday = (Button) myFragmentView.findViewById(R.id.saturday);
        Button sunday = (Button) myFragmentView.findViewById(R.id.sunday);
        Button set = (Button) myFragmentView.findViewById(R.id.setProgram);
        ImageView config = (ImageView) myFragmentView.findViewById(R.id.configBtn);

        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);
        sunday.setOnClickListener(this);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ConfigurationActivity.class);
                startActivity(i);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HeatingSystem.setWeekProgram(((GlobalResources) getActivity().getApplication()).getLocalWeekProgram());
                    }
                });
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(), "Week program set!", Toast.LENGTH_SHORT).show();
            }
        });

        return myFragmentView;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(v.getContext(), WeekDay.class);

        switch (((Button) v).getText().toString()) {
            case "Monday":
                i.putExtra("day", 0);
                break;
            case "Tuesday":
                i.putExtra("day", 1);
                break;
            case "Wednesday":
                i.putExtra("day", 2);
                break;
            case "Thursday":
                i.putExtra("day", 3);
                break;
            case "Friday":
                i.putExtra("day", 4);
                break;
            case "Saturday":
                i.putExtra("day", 5);
                break;
            case "Sunday":
                i.putExtra("day", 6);
                break;
        }

        startActivity(i);
    }
}
