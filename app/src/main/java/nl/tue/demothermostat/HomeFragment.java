package nl.tue.demothermostat;

import android.content.Intent;
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
import org.thermostatapp.util.TemperatureCheck;


public class HomeFragment extends Fragment implements View.OnClickListener {
    public static String currentTemp = "20.0", sDay = "Sunday", sTime = "12:34";
    static TemperatureCheck t = new TemperatureCheck("20.0");
    static TemperatureCheck day = new TemperatureCheck("Sunday");
    static TemperatureCheck time = new TemperatureCheck("12:34");
    public static Handler handler = new Handler(Looper.getMainLooper());
    boolean pause = false;
    Object wait = new Object();
    Thread threadTemp, threadDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getView().findViewById(R.id.relativeLayout5).setOnClickListener(this);
        getView().findViewById(R.id.relativeLayout6).setOnClickListener(this);
        getView().findViewById(R.id.relativeLayout7).setOnClickListener(this);
        getView().findViewById(R.id.daytemp).setOnClickListener(this);

        CurrTempTextView tempView = (CurrTempTextView) getView().findViewById(R.id.celciuscurrent);
        tempView.setText(String.valueOf(t.getValue()));

        t.setListener(tempView);

        CurrTempTextView dayView = (CurrTempTextView) getView().findViewById(R.id.serverDay);
        dayView.setText(String.valueOf(day.getValue()));

        day.setListener(dayView);

        CurrTempTextView timeView = (CurrTempTextView) getView().findViewById(R.id.serverTime);
        timeView.setText(String.valueOf(time.getValue()));

        time.setListener(timeView);

        ((TextView) getView().findViewById(R.id.daytemp)).setText(String.valueOf(((GlobalResources) getActivity().getApplication()).dayTemp));
        ((TextView) getView().findViewById(R.id.nighttemp)).setText(String.valueOf(((GlobalResources) getActivity().getApplication()).nightTemp));

    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView) getView().findViewById(R.id.daytemp)).setText(String.valueOf(((GlobalResources) getActivity().getApplication()).dayTemp));
        ((TextView) getView().findViewById(R.id.nighttemp)).setText(String.valueOf(((GlobalResources) getActivity().getApplication()).nightTemp));

        if (((GlobalResources) getActivity().getApplication()).vac)
            ((TextView) getView().findViewById(R.id.vacationmode)).setText("enabled");
        else
            ((TextView) getView().findViewById(R.id.vacationmode)).setText("disabled");
    }

    public static void tempChanged() {
        t.setValue(currentTemp);
    }

    public static void dateAndTimeChanged() {
        day.setValue(sDay);
        time.setValue(sTime);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getContext(), ConfigurationActivity.class);
        startActivity(i);
    }
}
