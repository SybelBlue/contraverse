package homi.sybelblue.contraversev12.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import homi.sybelblue.contraversev12.ContraverseUtils;
import homi.sybelblue.contraversev12.R;

import static homi.sybelblue.contraversev12.ContraverseUtils.toastRelay;

public class ConvoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convo);

        setTitle(getIntent().getStringExtra(getString(R.string.sq_topic_key)));
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            default:
                toastRelay(this, "onClick!");
        }
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


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
