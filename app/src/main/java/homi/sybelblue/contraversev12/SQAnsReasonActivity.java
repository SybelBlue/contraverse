package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQAnsReasonActivity extends AppCompatActivity implements View.OnClickListener {

    private String topic;
    private String questionText;
    private String response; // the text that was on the radio button the user picked

    private TextView questionTextView;
    private TextView radioResponseTextView;
    private EditText editText;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_ans_reason);

        // gets the strings passed in by the activity that started this one.
        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");
        questionText = intent.getStringExtra("questionText");
        response = intent.getStringExtra("response");

        // find all the views we need
        questionTextView = findViewById(R.id.sq_ans_reason_question);
        radioResponseTextView = findViewById(R.id.sq_ans_reason_radio_response);
        editText = findViewById(R.id.sq_ans_reason_edit_text);
        submit = findViewById(R.id.sq_ans_reason_submit);

        submit.setOnClickListener(this);

        // populate the text views with the question and radio button response
        questionTextView.setText("TOPIC: " + topic + "\n" + questionText);
        radioResponseTextView.setText("YOUR RESPONSE: " + response);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.sq_ans_reason_submit:
                // TODO go to next activity!
                String reason = editText.getText().toString();
                Intent intent;
                break;
        }
    }

}
