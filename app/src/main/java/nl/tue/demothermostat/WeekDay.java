package nl.tue.demothermostat;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class WeekDay extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView x, selectedDay;
    TextView dayswitch1, dayswitch2, dayswitch3, dayswitch4, dayswitch5, nightswitch1, nightswitch2, nightswitch3, nightswitch4, nightswitch5;
    Switch switch1, switch2, switch3, switch4, switch5, nSwitch1, nSwitch2, nSwitch3, nSwitch4, nSwitch5;
    private int mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day);

        Bundle extras = getIntent().getExtras();
        mDay = extras.getInt("day");

        selectedDay = (TextView) findViewById(R.id.selectedDay);

        selectDay();

        dayswitch1 = (TextView) findViewById(R.id.dayswitch1);
        dayswitch1.setOnClickListener(this);
        dayswitch2 = (TextView) findViewById(R.id.dayswitch2);
        dayswitch2.setOnClickListener(this);
        dayswitch3 = (TextView) findViewById(R.id.dayswitch3);
        dayswitch3.setOnClickListener(this);
        dayswitch4 = (TextView) findViewById(R.id.dayswitch4);
        dayswitch4.setOnClickListener(this);
        dayswitch5 = (TextView) findViewById(R.id.dayswitch5);
        dayswitch5.setOnClickListener(this);
        nightswitch1 = (TextView) findViewById(R.id.nightswitch1);
        nightswitch1.setOnClickListener(this);
        nightswitch2 = (TextView) findViewById(R.id.nightswitch2);
        nightswitch2.setOnClickListener(this);
        nightswitch3 = (TextView) findViewById(R.id.nightswitch3);
        nightswitch3.setOnClickListener(this);
        nightswitch4 = (TextView) findViewById(R.id.nightswitch4);
        nightswitch4.setOnClickListener(this);
        nightswitch5 = (TextView) findViewById(R.id.nightswitch5);
        nightswitch5.setOnClickListener(this);

        switch1 = (Switch) findViewById(R.id.toggle1);
        switch2 = (Switch) findViewById(R.id.toggle2);
        switch3 = (Switch) findViewById(R.id.toggle3);
        switch4 = (Switch) findViewById(R.id.toggle4);
        switch5 = (Switch) findViewById(R.id.toggle5);
        nSwitch1 = (Switch) findViewById(R.id.ntoggle1);
        nSwitch2 = (Switch) findViewById(R.id.ntoggle2);
        nSwitch3 = (Switch) findViewById(R.id.ntoggle3);
        nSwitch4 = (Switch) findViewById(R.id.ntoggle4);
        nSwitch5 = (Switch) findViewById(R.id.ntoggle5);

        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        switch3.setOnCheckedChangeListener(this);
        switch4.setOnCheckedChangeListener(this);
        switch5.setOnCheckedChangeListener(this);
        nSwitch1.setOnCheckedChangeListener(this);
        nSwitch2.setOnCheckedChangeListener(this);
        nSwitch3.setOnCheckedChangeListener(this);
        nSwitch4.setOnCheckedChangeListener(this);
        nSwitch5.setOnCheckedChangeListener(this);

    }

    private void selectDay() {
        switch (mDay) {
            case 0:
                selectedDay.setText("Monday");
                break;
            case 1:
                selectedDay.setText("Tuesday");
                break;
            case 2:
                selectedDay.setText("Wednesday");
                break;
            case 3:
                selectedDay.setText("Thursday");
                break;
            case 4:
                selectedDay.setText("Friday");
                break;
            case 5:
                selectedDay.setText("Saturday");
                break;
            case 6:
                selectedDay.setText("Sunday");
                break;

        }
    }


    @Override
    public void onClick(View v) {
        x = (TextView) v;
        // Get Current Time
        final Calendar c;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
        }


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


    @Override
    public void onCheckedChanged(CompoundButton b, boolean isChecked) {

    }
}

