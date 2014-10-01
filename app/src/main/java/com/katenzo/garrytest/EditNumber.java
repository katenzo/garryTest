package com.katenzo.garrytest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditNumber extends EditText {

    public EditNumber(Context context) {
        super(context);
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void clear() {
        this.setText("");
    }

    public double getNumber() {
        final String value = getText().toString();
        if ("".equals(value)) {
            return Double.NaN;
        }
        return Double.valueOf(value);
    }

    public void setNumber(double number) {
        this.setText(String.valueOf(number));
    }


}
