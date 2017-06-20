package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeekOverview extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_overview);

        Button monday = (Button)findViewById(R.id.monday);
        Button tuesday = (Button)findViewById(R.id.tuesday);
        Button wednesday = (Button)findViewById(R.id.wednesday);
        Button thursday = (Button)findViewById(R.id.thursday);
        Button friday = (Button)findViewById(R.id.friday);
        Button saturday = (Button)findViewById(R.id.saturday);
        Button sunday = (Button)findViewById(R.id.sunday);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekDay.class);
                startActivity(intent);
            }
        });
    }
}