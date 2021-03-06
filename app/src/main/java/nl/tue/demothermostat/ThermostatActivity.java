package nl.tue.demothermostat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThermostatActivity extends Activity {

    int vtemp = 160;
    int atemp = vtemp + 50;
    TextView temp;
    SeekBar seekBar;
    BottomNavigationView bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_heating);


        ImageView bPlus = (ImageView) findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.add_button);


        ImageView bMinus = (ImageView) findViewById(R.id.bMinus);
        temp = (TextView) findViewById(R.id.temp);


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(250);
        seekBar.setProgress(vtemp);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                vtemp = i;
                atemp = i + 50;
                temp.setText(atemp / 10 + "." + atemp % 10 + " \u2103");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp < 250) {
                    vtemp++;
                    atemp = vtemp + 50;
                    temp.setText(atemp / 10 + "." + atemp % 10 + " \u2103");
                    seekBar.setProgress(vtemp);
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
                    seekBar.setProgress(vtemp);
                }

            }
        });

        bottom = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomBar(item.getItemId());
                return true;
            }
        });

    }

    private void bottomBar(int id) {
        Intent intent = null;
        switch (id) {
            case R.id.action_home:
                intent = new Intent(this, HomeActivity.class);
                ;
            case R.id.action_heating:
                intent = new Intent(this, ThermostatActivity.class);
                ;
            case R.id.action_schedule:
                intent = new Intent(this, WeekOverview.class);
                ;
        }
        startActivity(intent);
    }
}

