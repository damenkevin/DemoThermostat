package nl.tue.demothermostat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.thermostatapp.util.GlobalResources;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        findViewById(R.id.daySet).setOnClickListener(this);
        findViewById(R.id.nightSet).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.daySet){
            ((GlobalResources)getApplication()).dayTemp = Double.valueOf(((EditText) findViewById(R.id.daytemp)).getText().toString());
        }else{
            ((GlobalResources)getApplication()).nightTemp = Double.valueOf(((EditText) findViewById(R.id.nighttemp)).getText().toString());
        }

        Toast t = Toast.makeText(getApplicationContext(), "Temperature set", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0,0);
        t.show();
    }
}
