package nl.tue.demothermostat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.InvalidInputValueException;

import io.feeeei.circleseekbar.CircleSeekBar;


public class HeatingFragment extends Fragment {
    int vtemp = 160;
    int atemp = vtemp + 50;
    CircleSeekBar seekBar;
    TextView temp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heating, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView bPlus = (ImageView) getView().findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.add_button);


        ImageView bMinus = (ImageView) getView().findViewById(R.id.bMinus);
        temp = (TextView) getView().findViewById(R.id.temp);

        seekBar = (CircleSeekBar) getView().findViewById(R.id.seekBar);
        seekBar.setMaxProcess(250);
        seekBar.setCurProcess(vtemp);
        seekBar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekBar, int i) {
                vtemp = i;
                atemp = i + 50;
                temp.setText(atemp / 10 + "." + atemp % 10 + " \u2103");
            }

        });
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp < 250) {
                    vtemp++;
                    atemp = vtemp + 50;
                    temp.setText(atemp / 10 + "." + atemp % 10 + " \u2103");
                    seekBar.setCurProcess(vtemp);
                }
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp > 0) {
                    vtemp--;
                    atemp = vtemp + 50;
                    temp.setText(atemp / 10 + "." + atemp % 10 + " \u2103");
                    seekBar.setCurProcess(vtemp);
                }

            }
        });

        Button setTemp = (Button) getView().findViewById(R.id.setTemp);
        setTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.put("targetTemperature", temp.getText().toString().replace("\u2103", "").trim());
                        } catch (InvalidInputValueException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast t = Toast.makeText(getActivity(), "Temporary temperature set to " + atemp / 10 + "." + atemp % 10 + "\u2103", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
                t.show();
            }
        });


    }


}