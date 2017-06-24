package nl.tue.demothermostat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thermostatapp.util.CurrTempTextView;
import org.thermostatapp.util.GlobalResources;
import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.TemperatureCheck;

import java.net.ConnectException;


public class HomeFragment extends Fragment {
    String currentTemp = "";
    TemperatureCheck t = new TemperatureCheck("20.0");
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                long lastCheck = 0L;
                while (true) {
                    if (System.currentTimeMillis() - lastCheck > 4000) {
                        lastCheck = System.currentTimeMillis();
                        try {
                            currentTemp = HeatingSystem.get("currentTemperature");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    changed();
                                }
                            });
                        } catch (ConnectException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        CurrTempTextView tempView = (CurrTempTextView)getView().findViewById(R.id.celciuscurrent);
        tempView.setText(String.valueOf(t.getValue()));

        t.setListener(tempView);

        ((TextView)getView().findViewById(R.id.daytemp)).setText(String.valueOf(((GlobalResources)getActivity().getApplication()).dayTemp));
        ((TextView)getView().findViewById(R.id.nighttemp)).setText(String.valueOf(((GlobalResources)getActivity().getApplication()).nightTemp));

    }

    public void changed(){
        t.setValue(currentTemp);
    }
}
