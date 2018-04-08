package homi.sybelblue.contraversev12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class AddMenu extends AppCompatActivity implements View.OnClickListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        String options[] = {"x","y","z",};
        Random rand = new Random();
        int i = rand.nextInt(options.length);

        TextView ti = new TextView(AddMenu.this);
        String title = options[i];
        LinearLayout P1 = (LinearLayout)findViewById(R.id.p1);
        ti.setText(options[i]);
        ti.setTag(-1);
        P1.addView(ti);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
//        String str=v.getTag().toString();
//        if(str.equals("buttonNew")) {
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Going to conversation ", Toast.LENGTH_SHORT).show();
//        }
    }


}
