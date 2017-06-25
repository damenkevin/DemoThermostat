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
import android.widget.Toast;

import org.thermostatapp.util.GlobalResources;

import java.util.ArrayList;

public class WeekDay extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView x, selectedDay;
    TextView dayswitch1, dayswitch2, dayswitch3, dayswitch4, dayswitch5, nightswitch1, nightswitch2, nightswitch3, nightswitch4, nightswitch5;
    Switch switch1, switch2, switch3, switch4, switch5, nSwitch1, nSwitch2, nSwitch3, nSwitch4, nSwitch5;
    private int mDay, mHour, mMinute;
    ArrayList<org.thermostatapp.util.Switch> switches = new ArrayList<>();
    ArrayList<Switch> daySwitches, nightSwitches;
    ArrayList<TextView> dayTimes, nightTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        Bundle extras = getIntent().getExtras();
        mDay = extras.getInt("day");

        selectedDay = (TextView) findViewById(R.id.selectedDay);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        selectDay();

        dayTimes = new ArrayList<>();
        nightTimes = new ArrayList<>();

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

        dayTimes.add(dayswitch1);
        dayTimes.add(dayswitch2);
        dayTimes.add(dayswitch3);
        dayTimes.add(dayswitch4);
        dayTimes.add(dayswitch5);

        nightTimes.add(nightswitch1);
        nightTimes.add(nightswitch2);
        nightTimes.add(nightswitch3);
        nightTimes.add(nightswitch4);
        nightTimes.add(nightswitch5);

        daySwitches = new ArrayList<>();
        nightSwitches = new ArrayList<>();

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

        daySwitches.add(switch1);
        daySwitches.add(switch2);
        daySwitches.add(switch3);
        daySwitches.add(switch4);
        daySwitches.add(switch5);
        nightSwitches.add(nSwitch1);
        nightSwitches.add(nSwitch2);
        nightSwitches.add(nSwitch3);
        nightSwitches.add(nSwitch4);
        nightSwitches.add(nSwitch5);

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

        switches = ((GlobalResources) getApplication()).getLocalWeekProgram().getDayProgram(mDay);
        initializeSwitches();
    }

    private void initializeSwitches() {
        int i = 0;
        for (org.thermostatapp.util.Switch s : switches) {
            if (!s.getState()) {
                i++;
                continue;
            } else {
                if (s.getType().equals("day")) {
                    daySwitches.get(i).setChecked(true);
                    dayTimes.get(i).setText(s.getTime());
                } else {
                    nightSwitches.get(i-5).setChecked(true);
                    nightTimes.get(i-5).setText(s.getTime());
                }
                i++;
            }

        }
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

    private void saveDayProgram() {
        int i = 0, j = 0;
        for (Switch s : daySwitches) {
            switches.get(i).setType("day");
            if (s.isChecked()) {
                switches.get(i).setState(true);
                switches.get(i).setTime(dayTimes.get(i).getText().toString());
            } else {
                switches.get(i).setState(false);
            }
            i++;
        }

        for (Switch s : nightSwitches) {
            switches.get(i).setType("night");
            if (s.isChecked()) {
                switches.get(i).setState(true);
                switches.get(i).setTime(nightTimes.get(j).getText().toString());
            } else {
                switches.get(i).setState(false);
            }
            i++;
            j++;
        }

        ((GlobalResources) getApplication()).setDayProgram(switches, selectedDay.getText().toString().trim());
        Toast.makeText(getApplicationContext(), "Changes saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveDayProgram();
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

