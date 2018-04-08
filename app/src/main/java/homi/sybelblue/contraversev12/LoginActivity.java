package homi.sybelblue.contraversev12;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout confirmationLayout;
    private LinearLayout form;

    private EditText displayNameView;

    private TextView confirmationText;

    private String displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Make Account");

        // set onClickListeners for all buttons
        findViewById(R.id.login_back).setOnClickListener(this);
        findViewById(R.id.login_next).setOnClickListener(this);
        findViewById(R.id.login_submit).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        displayNameView = findViewById(R.id.login_disp_name);
        confirmationLayout = findViewById(R.id.login_confirmation);
        confirmationText = findViewById(R.id.login_confirmation_text);
        form = findViewById(R.id.login_form);

        // make the form visible so user can answer the questions; make the confirmation text invisible
        //TODO make these separate activities so it isn't a fucking monster
        form.setVisibility(View.VISIBLE);
        confirmationLayout.setVisibility(View.GONE);

        //TODO actually ask the startup questions (I think they are officially called SpecificQuestion Questions)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_next:
                displayName = displayNameView.getText().toString();
                //TODO get the responses for the rest of the startup questions

                confirmationLayout.setVisibility(View.VISIBLE);
                confirmationText.setText(displayName);
                break;

            case R.id.login_back:
                confirmationLayout.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
                break;

            case R.id.login_submit:
                // save the user's ID in the sharedpreferences
                //TODO generate an actual user ID????
                SharedPreferences preferences = getSharedPreferences(getString(R.string.preferences_filename), 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(getString(R.string.user_id_pref_key), 3); //TODO store the data in the database
                editor.commit();

                User user = new User(3, new int[]{4});
                user.name = displayName;

                MainActivity.userDBHandler.addUser(user);

                finish();
        }
    }

}
