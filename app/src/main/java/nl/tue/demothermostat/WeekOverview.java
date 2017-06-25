package nl.tue.demothermostat;

import android.app.Activity;
import android.os.Bundle;

public class WeekOverview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);
    }
}