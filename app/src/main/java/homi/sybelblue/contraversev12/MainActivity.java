package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.content.SharedPreferences;
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

        // Check whether the user has set up their "account" yet
        SharedPreferences preferences = getSharedPreferences("", 0);
        int userID = preferences.getInt(getString(R.string.user_id_pref_key), -1); // i = -1 means the default return val is -1
        // If they have not, start the user profile setup activity
        if(userID == -1){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        // if they have, get the user's data from our database
        else{
            //TODO get user data from database
        }
    }

    private void defaultOnCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) toastRelay(buttonView.getText());
    }

    private void toastRelay(CharSequence toast) {
        if (doToast)
            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
