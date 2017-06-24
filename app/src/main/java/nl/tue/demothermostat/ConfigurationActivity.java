package nl.tue.demothermostat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        findViewById(R.id.daySet).setOnClickListener(this);
        findViewById(R.id.nightSet).setOnClickListener(this);

        final Button btnOpenPopup = (Button)findViewById(R.id.daySet);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT);

                ImageView btnDismiss = (ImageView) popupView.findViewById(R.id.setTemp);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

            }});


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.daySet){
            ((GlobalResources)getApplication()).dayTemp = Double.valueOf(((TextView) findViewById(R.id.daytemp)).getText().toString());
        }else{
            ((GlobalResources)getApplication()).nightTemp = Double.valueOf(((TextView) findViewById(R.id.nighttemp)).getText().toString());
        }

        Toast t = Toast.makeText(getApplicationContext(), "Temperature set", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0,50);
        t.show();
    }

}