package nl.tue.demothermostat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.thermostatapp.util.GlobalResources;
import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.InvalidInputValueException;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    TextView day, night;
    android.widget.Switch vacSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        findViewById(R.id.daySet).setOnClickListener(this);
        findViewById(R.id.nightSet).setOnClickListener(this);

        day = (TextView) findViewById(R.id.daytemp);
        night = (TextView) findViewById(R.id.nighttemp);

        day.setText(String.valueOf(((GlobalResources) getApplication()).dayTemp));
        night.setText(String.valueOf(((GlobalResources) getApplication()).nightTemp));

//        day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditText e = (EditText) v;
//                if (e.getText().length() != 0) {
//                    if (e.getText().toString().length() > 4)
//                        e.setText(e.getText().toString().substring(0, 4));
//
//                    if (Double.parseDouble(e.getText().toString()) > 30.0) {
//                        e.setText("30.0");
//                    } else if (Double.parseDouble(e.getText().toString()) < 5.0) {
//                        e.setText("5.0");
//                    }
//                }
//            }
//        });
//
//        night.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditText e = (EditText) v;
//                if (e.getText().length() != 0) {
//                    if (e.getText().toString().length() >= 4)
//                        e.setText(e.getText().toString().substring(0, 4));
//
//                    if (Double.parseDouble(e.getText().toString()) > 30.0) {
//                        e.setText("30.0");
//                    } else if (Double.parseDouble(e.getText().toString()) < 5.0) {
//                        e.setText("5.0");
//                    }
//                }
//            }
//        });

        vacSwitch = ((android.widget.Switch) findViewById(R.id.vacSwitch));

        if (((GlobalResources) getApplication()).vac) {
            vacSwitch.setChecked(true);
        } else {
            vacSwitch.setChecked(false);
        }

        vacSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("weekProgramState", "off");
                            } catch (InvalidInputValueException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    ((GlobalResources) getApplication()).vac = true;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("weekProgramState", "on");
                            } catch (InvalidInputValueException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    ((GlobalResources) getApplication()).vac = false;
                }
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ((GlobalResources) getApplication()).getWeekProgramFromServer();
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (((GlobalResources) getApplication()).vac) {
            vacSwitch.setChecked(true);
        } else {
            vacSwitch.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
//        findViewById(R.id.linearfocus).requestFocus();
//
//        if (v.getId() == R.id.daySet) {
//            double dayT = Double.parseDouble(day.getText().toString());
//
//            if (dayT > 30.0) dayT = 30.0;
//            else if (dayT < 5.0) dayT = 5.0;
//
//            ((GlobalResources) getApplication()).dayTemp = dayT;
//
//            final double finalDayT = dayT;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        HeatingSystem.put("dayTemperature", String.valueOf(finalDayT));
//                    } catch (InvalidInputValueException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//
//        } else {
//            double nightT = Double.parseDouble(night.getText().toString());
//
//            if (nightT > 30.0) nightT = 30.0;
//            else if (nightT < 5.0) nightT = 5.0;
//
//            ((GlobalResources) getApplication()).nightTemp = nightT;
//
//            final double finalNightT = nightT;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        HeatingSystem.put("nightTemperature", String.valueOf(finalNightT));
//                    } catch (InvalidInputValueException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

//        if (v.getId() == R.id.daySet) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        HeatingSystem.put("dayTemperature", day.getText().toString());
//                    } catch (InvalidInputValueException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        } else {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        HeatingSystem.put("nightTemperature", night.getText().toString());
//                    } catch (InvalidInputValueException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//
//
//        Toast t = Toast.makeText(getApplicationContext(), "Temperature set", Toast.LENGTH_SHORT);
//        t.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
//        t.show();

        if (v.getId() == R.id.daySet) {
            Intent i = new Intent(getApplicationContext(), TemperaturePicker.class);
            i.putExtra("day", true);
            finish();
            startActivity(i);
        } else {
            Intent i = new Intent(getApplicationContext(), TemperaturePicker.class);
            i.putExtra("day", false);
            finish();
            startActivity(i);
        }
    }

}