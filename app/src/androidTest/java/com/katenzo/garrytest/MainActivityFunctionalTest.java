package com.katenzo.garrytest;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;


public class MainActivityFunctionalTest extends
        ActivityInstrumentationTestCase2<MyActivity> {

    private MyActivity activity;

    public MainActivityFunctionalTest() {
        super(MyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testStartSecondActivity() throws Exception {


        // add monitor to check for the second activity
        Instrumentation.ActivityMonitor monitor =
                getInstrumentation().
                        addMonitor(SecondActivity.class.getName(), null, false);

        // find button and click it
        Button button = (Button) activity.findViewById(R.id.button);

        // TouchUtils handles the sync with the main thread internally
        TouchUtils.clickView(this, button);

        // to click on a click, e.g., in a listview
        // listView.getChildAt(0);

        // wait 2 seconds for the start of the activity
        SecondActivity startedActivity = (SecondActivity) monitor
                .waitForActivityWithTimeout(2000);
        assertNotNull(startedActivity);

        // search for the textView
        TextView textView = (TextView) startedActivity.findViewById(R.id.resultText);

        // check that the TextView is on the screen
        ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
                textView);
        // validate the text on the TextView
        assertEquals("Text incorrect", "Started", textView.getText().toString());

        // press back and click again
        this.sendKeys(KeyEvent.KEYCODE_BACK);

        TouchUtils.clickView(this, button);
    }
}
