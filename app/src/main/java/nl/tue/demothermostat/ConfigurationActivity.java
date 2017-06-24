package nl.tue.demothermostat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import org.thermostatapp.util.GlobalResources;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText day, night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        findViewById(R.id.daySet).setOnClickListener(this);
        findViewById(R.id.nightSet).setOnClickListener(this);

        day = (EditText) findViewById(R.id.daytemp);
        night = (EditText) findViewById(R.id.nighttemp);

        day.setText(String.valueOf(((GlobalResources) getApplication()).dayTemp));
        night.setText(String.valueOf(((GlobalResources) getApplication()).nightTemp));

        day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText e = (EditText) v;
                if (e.getText().length() != 0) {
                    if (Double.parseDouble(e.getText().toString()) > 30.0) {
                        e.setText("30.0");
                    } else if (Double.parseDouble(e.getText().toString()) < 5.0) {
                        e.setText("5.0");
                    }
                }
            }
        });

        night.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText e = (EditText) v;
                if (e.getText().length() != 0) {
                    if (Double.parseDouble(e.getText().toString()) > 30.0) {
                        e.setText("30.0");
                    } else if (Double.parseDouble(e.getText().toString()) < 5.0) {
                        e.setText("5.0");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.linearfocus).requestFocus();

        if (v.getId() == R.id.daySet) {
            ((GlobalResources) getApplication()).dayTemp = Double.valueOf(day.getText().toString());
        } else {
            ((GlobalResources) getApplication()).nightTemp = Double.valueOf(night.getText().toString());
        }

        if(((GlobalResources) getApplication()).dayTemp > 30.0){
            ((GlobalResources) getApplication()).dayTemp = 30.0;
        } else if (((GlobalResources) getApplication()).dayTemp < 5.0){
            ((GlobalResources) getApplication()).dayTemp = 5.0;
        }

        if(((GlobalResources) getApplication()).nightTemp > 30.0){
            ((GlobalResources) getApplication()).nightTemp = 30.0;
        } else if (((GlobalResources) getApplication()).nightTemp < 5.0){
            ((GlobalResources) getApplication()).nightTemp = 5.0;
        }

        Toast t = Toast.makeText(getApplicationContext(), "Temperature set", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
        t.show();
    }

}