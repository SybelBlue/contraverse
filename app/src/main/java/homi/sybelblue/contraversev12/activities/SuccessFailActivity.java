package homi.sybelblue.contraversev12.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import homi.sybelblue.contraversev12.R;
import homi.sybelblue.contraversev12.SFCode;

public class SuccessFailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_fail);

        setTitle("Rate Conversation");

        Button button = findViewById(R.id.Success);
        button.setOnClickListener((v) ->
        {
            Intent intent;
//            startActivity(intent);
            MainActivity.currentUser.recordSF(SFCode.Success);
            finish();
        });

        button = findViewById(R.id.Failure);
        button.setOnClickListener((v) ->
        {
            Intent intent;
//            startActivity(intent);
            MainActivity.currentUser.recordSF(SFCode.Failure);
            finish();
        });

    }
}
