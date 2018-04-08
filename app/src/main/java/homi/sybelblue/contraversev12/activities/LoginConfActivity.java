package homi.sybelblue.contraversev12.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;

import homi.sybelblue.contraversev12.R;

public class LoginConfActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView confirmationText;
    private String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_conf);

        findViewById(R.id.login_submit).setOnClickListener(this);
        displayName = getIntent().getStringExtra("displayName");
    }

    @Override
    public void onStart(){
        super.onStart();

        confirmationText = findViewById(R.id.login_confirmation_text);
        confirmationText.setText("Your Display Name: " + displayName);
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.login_submit:
                // save the user's ID in the sharedpreferences
                //TODO generate an actual user ID????
                SharedPreferences preferences = getSharedPreferences(getString(R.string.preferences_filename), 0);
                SharedPreferences.Editor editor = preferences.edit();
                long id = System.currentTimeMillis();
                editor.putLong(getString(R.string.user_id_pref_key), id);
                editor.commit();

                homi.sybelblue.contraversev12.User user =
                        new homi.sybelblue.contraversev12.User(displayName, id, new int[MainActivity.NUM_TOPICS]);

                MainActivity.userDBHandler.addUser(user);

                //set the user up for SendBird messaging service
//                SendBird.connect(id+"", new SendBird.ConnectHandler() {
//                    @Override
//                    public void onConnected(com.sendbird.android.User user, SendBirdException e) {
//                        if (e != null) {
//                             Error.
//                            return;
//                        }
//                    }
//                });

                finish();
        }

    }

}
