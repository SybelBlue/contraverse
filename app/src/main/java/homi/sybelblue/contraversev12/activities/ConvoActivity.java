package homi.sybelblue.contraversev12.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import homi.sybelblue.contraversev12.Response;

import java.util.List;

import homi.sybelblue.contraversev12.ContraverseUtils;
import homi.sybelblue.contraversev12.R;
import homi.sybelblue.contraversev12.User;

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

        //TODO this is just me testing and making up a message
        SharedPreferences preferences = getSharedPreferences("", 0);
        long id = preferences.getLong(getString(R.string.user_id_pref_key), -1);
        User testUser = MainActivity.userDBHandler.findUser(id);
        Response<String> testMessage = new Response(testUser, "Ths is a test message");
        mMessageList.add(testMessage);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MyConvoRecyclerViewAdapter(this, mMessageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));


        setTitle(title = getIntent().getStringExtra(getString(R.string.sq_topic_key)));
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
        }
        return super.onOptionsItemSelected(item);
    }

}
