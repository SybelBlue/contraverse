package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout form;

    private EditText displayNameView;

    public String displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set onClickListeners for all buttons
        findViewById(R.id.login_next).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        displayNameView = findViewById(R.id.login_disp_name);
        form = findViewById(R.id.login_form);

        //TODO actually ask the startup questions (I think they are officially called SpecificQuestion Questions)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_next:
                displayName = displayNameView.getText().toString();
                Intent intent = new Intent(getApplicationContext(), LoginConfActivity.class);
                intent.putExtra("displayName", displayName);
                startActivity(intent);
                //TODO get the responses for the rest of the startup questions
                break;

        }
    }

}
