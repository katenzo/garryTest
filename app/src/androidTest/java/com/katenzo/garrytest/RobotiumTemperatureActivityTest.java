package com.katenzo.garrytest;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

public class RobotiumTemperatureActivityTest extends
        ActivityInstrumentationTestCase2<TemperaturConverterActivity> {

    private Solo solo;

    public RobotiumTemperatureActivityTest() {
        super(TemperaturConverterActivity.class);
    }

    public void setUp() throws Exception {
       solo = new Solo(getInstrumentation(), getActivity());
       solo.unlockScreen();
       setActivityInitialTouchMode(true);


    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testConvert() {
        solo.assertCurrentActivity("wrong activity", TemperaturConverterActivity.class);
        solo.clickOnView(solo.getView(R.id.mCelcius));
        solo.clearEditText((android.widget.EditText) solo.getView(R.id.mCelcius));
        solo.enterText((android.widget.EditText) solo.getView(R.id.mCelcius), "32.0");
        solo.clickOnView(solo.getView(R.id.mFahrenheit));
        solo.waitForActivity(solo.getCurrentActivity().toString());
        assertTrue("Fail Fahrenheit", solo.searchText("89.6"));


    }


}
