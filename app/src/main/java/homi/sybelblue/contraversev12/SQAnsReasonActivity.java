package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SQAnsReasonActivity extends AppCompatActivity implements View.OnClickListener {

    private String topic;
    private String questionText;
    private String response; // the text that was on the radio button the user picked

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_ans_reason);

        // gets the strings passed in by the activity that started this one.
        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");
        questionText = intent.getStringExtra("questionText");
        response = intent.getStringExtra("response");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

        }
    }

}
