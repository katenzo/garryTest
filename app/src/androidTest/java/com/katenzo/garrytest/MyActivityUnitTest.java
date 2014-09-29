package com.katenzo.garrytest;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

/**
 * Created by katenzo on 9/23/14.
 */
public class MyActivityUnitTest extends ActivityUnitTestCase<MyActivity> {
    private int buttonId;
    private MyActivity activity;

    public MyActivityUnitTest() {
        super(MyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MyActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testLayoutButtonLabelIsStart() {
        buttonId = R.id.button;
        assertNotNull(activity.findViewById(buttonId));
        Button button = (Button) activity.findViewById(buttonId);
        assertEquals("Incorrect label of the button", "Start", button.getText());
    }

    public void testIntentTriggerViaOnClick() {
        buttonId = R.id.button;
        Button button = (Button) activity.findViewById(buttonId);
        assertNotNull("Button not allowed to be null", button);

        button.performClick();


        // TouchUtils cannot be used, only allowed in
        // InstrumentationTestCase or ActivityInstrumentationTestCase2

        // Check the intent which was started
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);
        String data = triggeredIntent.getExtras().getString("URL");

        assertEquals("Incorrect data passed via the intent",
                "http://www.vogella.com", data);
    }

    public void testCorrectIntentTriggerVinButtonOnClick() {
        buttonId = R.id.button;
        Button button = (Button) activity.findViewById(buttonId);
        assertNotNull("Button not allowed to be null", button);

        button.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);
        String data = triggeredIntent.getExtras().getString("URL");
        assertEquals("Incorrect Data Intent =  URL", "http://www.vogella.com", data);
    }
}
