package nl.tue.demothermostat;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Activity;

public class WeekDay extends AppCompatActivity implements
        View.OnClickListener{
    TextView dayswitch1, dayswitch2, dayswitch3, dayswitch4, dayswitch5, nightswitch1, nightswitch2, nightswitch3, nightswitch4, nightswitch5;
    TextView x;
    private int mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day);
        dayswitch1=(TextView) findViewById(R.id.dayswitch1);
        dayswitch1.setOnClickListener(this);
        dayswitch2=(TextView) findViewById(R.id.dayswitch2);
        dayswitch2.setOnClickListener(this);
        dayswitch3=(TextView) findViewById(R.id.dayswitch3);
        dayswitch3.setOnClickListener(this);
        dayswitch4=(TextView) findViewById(R.id.dayswitch4);
        dayswitch4.setOnClickListener(this);
        dayswitch5=(TextView) findViewById(R.id.nightswitch5);
        dayswitch5.setOnClickListener(this);
        nightswitch1=(TextView) findViewById(R.id.nightswitch1);
        nightswitch1.setOnClickListener(this);
        nightswitch2=(TextView) findViewById(R.id.nightswitch2);
        nightswitch2.setOnClickListener(this);
        nightswitch3=(TextView) findViewById(R.id.nightswitch3);
        nightswitch3.setOnClickListener(this);
        nightswitch4=(TextView) findViewById(R.id.nightswitch4);
        nightswitch4.setOnClickListener(this);
        nightswitch5=(TextView) findViewById(R.id.nightswitch5);
        nightswitch5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            x = (TextView) v;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        x.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }


}

