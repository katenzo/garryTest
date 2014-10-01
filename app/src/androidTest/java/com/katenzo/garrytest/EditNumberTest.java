package com.katenzo.garrytest;

import android.content.Context;
import android.test.AndroidTestCase;


public class EditNumberTest extends AndroidTestCase {

    EditNumber mEditNumber;

    public void setUp() throws Exception {
        super.setUp();
        Context context = getContext();
        mEditNumber = new EditNumber(context);
    }

    public final void testClear() {
        final String value = "123.45";
        mEditNumber.setText(value);
        mEditNumber.clear();
        final String expected = "";
        final String actual = mEditNumber.getText().toString();
        assertEquals(expected, actual);
    }

    public final void testSetNumber() {
        final double d = 123.45;
        mEditNumber.setNumber(d);
        final String expected = Double.toString(d);
        final String actual = mEditNumber.getText().toString();
        assertEquals(expected, actual);
    }

    public void testGetNumber() throws Exception {
        final double expected = 123.45;
        mEditNumber.setNumber(expected);
        final double actual = mEditNumber.getNumber();

        assertEquals(expected, actual);

    }
}
