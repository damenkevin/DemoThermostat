package org.thermostatapp.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class CurrTempTextView extends android.support.v7.widget.AppCompatTextView implements TemperatureCheck.TemperatureListener{
    public CurrTempTextView(Context context) {
        super(context);
    }

    public CurrTempTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void onValueChanged(String newValue) {
        setText(newValue);
    }
}
