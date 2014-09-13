package abhishek.billcalc.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    // Defining the constants
    private static final String AMOUNT_WITHOUT_TIP = "AMOUNT_WITHOUT_TIP";
    private static final String TOTAL_TIP = "TOTAL_TIP";
    private static final String AMOUNT_WITH_TIP = "AMOUNT_WITH_TIP";

    private EditText amount;
    private EditText tip;
    private EditText total_amount;

    private double resAmount;
    private double resTip;
    private double resTotalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Just Started the app
        if(savedInstanceState == null) {
            resAmount = 0;
            resTip = 0.15;
            resTotalAmount = 0;
        } else {

            resAmount = savedInstanceState.getDouble(AMOUNT_WITHOUT_TIP);
            resTip = savedInstanceState.getDouble(TOTAL_TIP);
            resTotalAmount = savedInstanceState.getDouble(AMOUNT_WITH_TIP);

        }

        amount = (EditText)findViewById(R.id.amountEditText);
        tip = (EditText)findViewById(R.id.tipEditText);
        total_amount = (EditText)findViewById(R.id.totalAmountEditText);

        amount.addTextChangedListener(amountChangedListener);
        tip.addTextChangedListener(tipChangedListener);
    }

    // Setting the text watcher for the fields
    TextWatcher amountChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            try {
                resAmount = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                resAmount=0.0;
            }

            // function for updating the final bill
            updateFinalBill();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher tipChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            updateFinalBill();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // updating final bill
    private void updateFinalBill() {
        resTip = Double.parseDouble(tip.getText().toString());

        resTotalAmount = (resAmount) + (resAmount * resTip);

        total_amount.setText(String.format("%.2f",resTotalAmount));
    }

    // function for storing the form data when the screen rotates
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("AMOUNT_WITHOUT_TIP", resAmount);
        outState.putDouble("TOTAL_TIP", resTip);
        outState.putDouble("AMOUNT_WITH_TIP", resTotalAmount);
    }

    public void ResetFields(View view) {

        resAmount = 0;
        resTotalAmount = 0;
        amount.setText(String.format("%.2f",resAmount));
        total_amount.setText(String.format("%.2f",resTotalAmount));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
