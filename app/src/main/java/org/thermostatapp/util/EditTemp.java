package org.thermostatapp.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by gsmet on 24-Jun-17.
 */

public class EditTemp extends android.support.v7.widget.AppCompatEditText {
    public EditTemp(Context context) {
        super(context);
    }

    public EditTemp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTemp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            this.clearFocus();
            return true;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
