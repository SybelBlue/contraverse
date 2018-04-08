package homi.sybelblue.contraversev12.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import homi.sybelblue.contraversev12.R;

public class ViewSQResponsesActivity extends AppCompatActivity {

    public static final java.util.Random gen = new Random();
    public static final String DUMMY_STRING = "Tim\n" + (gen.nextBoolean()? "Agrees" : "Disagrees") + "\n" + "No particular reason";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sqresponses);

        ((TextView)findViewById(R.id.recipientResponses)).setText(DUMMY_STRING);
        String opinion = getIntent().getStringExtra(getString(R.string.sq_response));
        String reasoning = getIntent().getStringExtra(getString(R.string.sq_reasoning));
        opinion = opinion.length() > 0? opinion : "Not Found";
        reasoning = reasoning.length() > 0? reasoning : "Not Found";
        String purple = MainActivity.currentUser.name + '\n' +
                opinion + '\n' + reasoning;
        ((TextView)findViewById(R.id.senderResponses)).setText(purple);
    }
}
