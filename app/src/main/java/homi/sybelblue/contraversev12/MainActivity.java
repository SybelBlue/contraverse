package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart(){
        super.onStart();

        // Check whether the user has set up their "account" yet
        SharedPreferences preferences = getSharedPreferences("", 0);
        int userID = preferences.getInt(getString(R.string.user_id_pref_key), -1); // i = -1 means the default return val is -1
        // If they have not, start the user profile setup activity
        if(userID == -1){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        // if they have, get the user's data from our database
        else{
            //TODO get user data from database
        }
    }

    private void toastRelay(CharSequence toast) {
        Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
