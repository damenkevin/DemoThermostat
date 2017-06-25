package nl.tue.demothermostat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.thermostatapp.util.GlobalResources;
import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.InvalidInputValueException;

import io.feeeei.circleseekbar.CircleSeekBar;

public class TemperaturePicker extends AppCompatActivity {
    int vtemp = 160;
    int atemp = vtemp + 50;
    CircleSeekBar seekBar;
    TextView temp;
    boolean day = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_picker);

        ImageView bPlus = (ImageView) findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.add_button);


        ImageView bMinus = (ImageView) findViewById(R.id.bMinus);
        temp = (TextView) findViewById(R.id.temp);

        seekBar = (CircleSeekBar) findViewById(R.id.seekBar);
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

        day = getIntent().getBooleanExtra("day", false);

        Button setTemp = (Button) findViewById(R.id.setTemp);
        setTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ConfigurationActivity.class);
                if (day) {
                    ((GlobalResources) getApplication()).dayTemp = Double.valueOf(atemp / 10 + "." + atemp % 10);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("dayTemperature", atemp / 10 + "." + atemp % 10);
                            } catch (InvalidInputValueException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    ((GlobalResources) getApplication()).nightTemp = Double.valueOf(atemp / 10 + "." + atemp % 10);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("nightTemperature", atemp / 10 + "." + atemp % 10);
                            } catch (InvalidInputValueException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                finish();

                Toast t = Toast.makeText(getApplicationContext(), "Temperature set", Toast.LENGTH_LONG);
                t.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
                t.show();

                startActivity(i);
            }
        });
    }

}
