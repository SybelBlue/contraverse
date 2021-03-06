package homi.sybelblue.contraversev12.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import homi.sybelblue.contraversev12.ContraverseUtils;
import homi.sybelblue.contraversev12.R;
import homi.sybelblue.contraversev12.User;
import homi.sybelblue.contraversev12.UserDBHandler;


public class End extends AppCompatActivity implements View.OnClickListener   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setTitle("End Conversation");



//
//        Button Suc = new Button(End.this);
//        LinearLayout p1 = (LinearLayout)findViewById(R.id.p11);
//        Suc.setText("New Conversation");
//        Suc.setTag(-1);
//        p1.addView(Suc);
//        Suc.setOnClickListener(End.this);
//
//        Button Fail = new Button(End.this);
//        LinearLayout p3 = (LinearLayout)findViewById(R.id.p13);
//        Fail.setText("New Conversation");
//        Fail.setTag(1);
//        p3.addView(Fail);
//        Fail.setOnClickListener(End.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                ContraverseUtils.toastRelay(this, "we don here");

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {

        String str = v.getTag().toString();
        if (str.equals("-1")) {

            startActivity(new Intent(End.this, Review.class));
        }
        else if (str.equals("1")){
            startActivity(new Intent(End.this, Review.class));
        }
    }
}
