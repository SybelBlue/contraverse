package homi.sybelblue.contraversev12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        button.setOnClickListener(v -> toastRelay("Hi!"));

        RadioButton radioButton = findViewById(R.id.testRadio0);
        radioButton.setOnCheckedChangeListener(this::defaultOnCheckedChanged);

        radioButton = findViewById(R.id.testRadio1);
        radioButton.setOnCheckedChangeListener(this::defaultOnCheckedChanged);

        Switch aSwitch = findViewById(R.id.switch2);
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> doToast = isChecked);
    }

    private void defaultOnCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) toastRelay(buttonView.getText());
    }

    private void toastRelay(CharSequence toast) {
        if (doToast)
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
