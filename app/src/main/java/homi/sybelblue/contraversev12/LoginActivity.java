package homi.sybelblue.contraversev12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout confirmationLayout;
    private LinearLayout form;

    private EditText displayNameView;

    private TextView confirmationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        displayNameView = findViewById(R.id.login_disp_name);
        confirmationLayout = findViewById(R.id.login_confirmation);
        confirmationText = findViewById(R.id.login_confirmation_text);
        form = findViewById(R.id.login_form);

        // make the form visible so user can answer the questions; make the confirmation text invisible
        form.setVisibility(View.VISIBLE);
        confirmationLayout.setVisibility(View.GONE);

        //TODO actually ask the startup questions (I think they are officially called Topic Questions)


    }

    public void onClick(View v){
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
                //TODO store the data in the database
        }
    }

}
