package com.katenzo.garrytest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;


public class TemperaturConverterActivity extends Activity {

    private EditNumber mCelcius;
    private EditNumber mFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatur_converter);
        mCelcius = (EditNumber) findViewById(R.id.mCelcius);
        mFahrenheit = (EditNumber) findViewById(R.id.mFahrenheit);

        mCelcius.addTextChangedListener(new TemperatureChangeWatcher(mCelcius, mFahrenheit) {

            @Override
            protected double convert(double temperature) {
                return TemperatureConverter.celciusToFahrenheit(temperature);
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFahrenheit.addTextChangedListener(new TemperatureChangeWatcher(mFahrenheit, mCelcius) {
            @Override
            protected double convert(double temperature) {
                return TemperatureConverter.fahrenheitToCelcius(temperature);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.temperatur_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public abstract class TemperatureChangeWatcher implements TextWatcher {
        private EditNumber mSource;
        private EditNumber mDest;

        public TemperatureChangeWatcher(EditNumber source, EditNumber destination) {
            this.mSource = source;
            this.mDest = destination;
        }

        protected abstract double convert(double temperature);

        @Override
        public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            if (!mDest.hasWindowFocus() || mDest.hasFocus() || text == null) return;
            final String str = text.toString();
            if ("".equals(str)) {
                mDest.setText("");
                return;
            }

            try {
                final double result = convert(Double.parseDouble(str));
                mDest.setNumber(result);

            } catch (NumberFormatException e) {

            } catch (Exception e) {
                mSource.setError("Error:" + e.getLocalizedMessage());
            }
        }


    }


}
