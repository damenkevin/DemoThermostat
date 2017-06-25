package org.thermostatapp.util;

/**
 * Created by gsmet on 23-Jun-17.
 */

public class TemperatureCheck {
    private String mValue;
    private TemperatureListener listener;

    public TemperatureCheck(String mValue) {
        this.mValue = mValue;
    }

    public void setListener(TemperatureListener listener) {
        this.listener = listener;
    }

    public void setValue(String mValue) {
        this.mValue = mValue;
        if (listener != null)
            listener.onValueChanged(mValue);
    }

    public String getValue() {
        return mValue;
    }

    public static interface TemperatureListener {
        void onValueChanged(String newValue);
    }
}
