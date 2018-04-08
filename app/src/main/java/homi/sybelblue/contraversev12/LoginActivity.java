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

public class LoginActivity extends Activity implements View.OnClickListener {

    private ConstraintLayout confirmationLayout;
    private LinearLayout form;

    private EditText displayNameView;

    private TextView confirmationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        form.setVisibility(View.VISIBLE);
        confirmationLayout.setVisibility(View.GONE);

        //TODO actually ask the startup questions (I think they are officially called Topic Questions)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_next:
                String displayName = displayNameView.getText().toString();
                //TODO get the responses for the rest of the startup questions

                confirmationLayout.setVisibility(View.VISIBLE);
                confirmationText.setText(displayName);
                break;

            case R.id.login_back:
                confirmationLayout.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
                break;

            case R.id.login_submit:
                //TODO store the data in the
                SharedPreferences preferences = getSharedPreferences("", 0);
                finish();
        }
    }

}
