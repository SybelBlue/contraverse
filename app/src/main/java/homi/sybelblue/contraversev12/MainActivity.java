package homi.sybelblue.contraversev12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean doToast = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastRelay("Hi!");
            }
        });

        RadioButton radioButton = findViewById(R.id.testRadio0);
        radioButton.setOnCheckedChangeListener(new RadioGroupDefualtOnCheckedChangeListener());

        radioButton = findViewById(R.id.testRadio1);
        radioButton.setOnCheckedChangeListener(new RadioGroupDefualtOnCheckedChangeListener());

        Switch aSwitch = findViewById(R.id.switch2);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doToast = isChecked;
            }
        });
    }

    private class RadioGroupDefualtOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) toastRelay(buttonView.getText());
        }
    }

    private void toastRelay(CharSequence toast) {
        if (doToast)
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
