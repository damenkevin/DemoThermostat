package nl.tue.demothermostat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import io.feeeei.circleseekbar.CircleSeekBar;


public class HeatingFragment extends Fragment {
    int vtemp = 160;
    int atemp = vtemp+50;
    CircleSeekBar seekBar;
    TextView temp, test;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thermostat, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView bPlus = (ImageView)getView().findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.add_button);


        ImageView bMinus = (ImageView)getView().findViewById(R.id.bMinus);
        temp = (TextView)getView().findViewById(R.id.temp);
        Button weekOverview = (Button)getView().findViewById(R.id.week_overview);

        weekOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });

        Button testingWS = (Button)getView().findViewById(R.id.testing_ws);

        testingWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TestingWS.class);
                startActivity(intent);
            }
        });

        seekBar = (CircleSeekBar) getView().findViewById(R.id.seekBar);
        seekBar.setMaxProcess(250);
        seekBar.setCurProcess(vtemp);
        seekBar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekBar, int i) {
                vtemp = i;
                atemp = i+50;
                temp.setText(atemp/10 + "." + atemp%10 + " \u2103");
            }

        });
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vtemp<250){
                    vtemp++;
                    atemp = vtemp+50;
                    temp.setText(atemp/10 + "." + atemp%10 + " \u2103");
                    seekBar.setCurProcess(vtemp);
                }
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vtemp>0){
                    vtemp--;
                    atemp = vtemp+50;
                    temp.setText(atemp/10 + "." + atemp%10 + " \u2103");
                    seekBar.setCurProcess(vtemp);
                }

            }
        });




    }


}
