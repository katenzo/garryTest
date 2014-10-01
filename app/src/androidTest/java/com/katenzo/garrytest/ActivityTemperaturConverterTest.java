package com.katenzo.garrytest;


import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.view.Gravity;
import android.view.View;


public class ActivityTemperaturConverterTest extends ActivityInstrumentationTestCase2<TemperaturConverterActivity> {

    private TemperaturConverterActivity mActivity;
    private EditNumber mCelcius;
    private EditNumber mFahrenheit;

    public ActivityTemperaturConverterTest() {
        super(TemperaturConverterActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull(mActivity);
        mCelcius = (EditNumber) mActivity.findViewById(R.id.mCelcius);
        assertNotNull(mCelcius);
        mFahrenheit = (EditNumber) mActivity.findViewById(R.id.mFahrenheit);
        assertNotNull(mFahrenheit);

    }

    public void testFieldOnScreen() {
        final View origin = mActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(origin, mCelcius);
        ViewAsserts.assertOnScreen(origin, mFahrenheit);

    }

    public void testAlignment() {
        ViewAsserts.assertRightAligned(mCelcius, mFahrenheit);
        ViewAsserts.assertLeftAligned(mCelcius, mFahrenheit);
    }

    public void testFieldsShouldStartEmpty() {
        assertTrue("".equals(mCelcius.getText().toString()));
        assertTrue("".equals(mFahrenheit.getText().toString()));
    }

    public void testJustification() {
        final int expected = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        assertEquals(expected, mCelcius.getGravity());
        assertEquals(expected, mFahrenheit.getGravity());
    }

    @UiThreadTest
    public void testFahrenheitToCelciusConverter() {
        mCelcius.clear();
        mFahrenheit.clear();
        final double f = 32.5;
        mFahrenheit.requestFocus();
        mFahrenheit.setNumber(f);
        mCelcius.requestFocus();
        final double expected = TemperatureConverter.fahrenheitToCelcius(f);
        final double actual = mCelcius.getNumber();
        final double delta = Math.abs(expected - actual);
        assertTrue(delta < 0.0005);
    }


}
