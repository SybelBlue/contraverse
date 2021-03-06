package homi.sybelblue.contraversev12.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import homi.sybelblue.contraversev12.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout form;

    private EditText displayNameView;
    public String displayName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Make Account");

        // set onClickListeners for all buttons
        findViewById(R.id.login_next).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        displayNameView = findViewById(R.id.login_disp_name);
        form = findViewById(R.id.login_form);

        toastRelay("Welcome! We didn't find your info.");
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

                finish();
                break;

        }
    }

    private void toastRelay(CharSequence toast) {
        Toast.makeText(LoginActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

}
