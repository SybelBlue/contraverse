package homi.sybelblue.contraversev12.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import homi.sybelblue.contraversev12.Convo;
import homi.sybelblue.contraversev12.dummy.DummyContent;
import homi.sybelblue.contraversev12.questions.Response;

import java.util.List;

import homi.sybelblue.contraversev12.ContraverseUtils;
import homi.sybelblue.contraversev12.R;

import static homi.sybelblue.contraversev12.ContraverseUtils.toastRelay;

public class ConvoActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mMessageRecycler;
    private MyConvoRecyclerViewAdapter mMessageAdapter;
    private List<Response<String>> mMessageList;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convo);

        setTitle(title = getIntent().getStringExtra(getString(R.string.sq_topic_key)).replaceAll("_", " "));

        //TODO this is just me testing and making up a message
//        SharedPreferences preferences = getSharedPreferences(getString(R.string.preferences_filename), 0);
//        long id = preferences.getLong(getString(R.string.user_id_pref_key), -1);
//        User testUser = MainActivity.currentUser;
//        Response<String> testMessage = new Response(testUser, "Ths is a test message");
//        mMessageList = new ArrayList<>();
//        mMessageList.add(testMessage);
//
//        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
//        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
//        mMessageAdapter = new MyConvoRecyclerViewAdapter(getApplicationContext(), mMessageList);
//        mMessageRecycler.setAdapter(mMessageAdapter);

        if (getIntent().getBooleanExtra("displayConvo", false)){
            displayConvo(Convo.decode(getIntent().getStringExtra("convo")));
            toastRelay(this, "Puppers");
        }
    }

    public void displayConvo(Convo c) {
        LinearLayout layout = findViewById(R.id.textHolder);
        Response<String>[] messages = c.getConvoTexts();

        for (int i=0; i < messages.length; i++) {
            Response<String> message = messages[i];
            TextView tv1 = new TextView(this);
            tv1.setText(message.response);
            if (MainActivity.currentUser.equals(message.user)) {
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                tv1.setTextColor(Color.rgb(80,00,80));
            } else {
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                tv1.setTextColor(Color.GRAY);
            }
            tv1.setId(i+1);
            tv1.setTag(i);
            layout.addView(tv1);
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            default:
                toastRelay(this, "onClick!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.convo_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.list_item:
                Intent intent = new Intent(ConvoActivity.this, ViewSQResponsesActivity.class);
                Intent current = getIntent();

                ContraverseUtils.pushOldExtra(this, current, intent,
                        R.string.sq_topic_key,
                        R.string.sq_question_text,
                        R.string.sq_response,
                        R.string.sq_reasoning);

                startActivity(intent);
                break;
            case R.id.endBtn:
                Intent intent1 = new Intent(ConvoActivity.this, End.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
