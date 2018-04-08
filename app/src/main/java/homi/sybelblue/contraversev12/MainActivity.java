package homi.sybelblue.contraversev12;


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

//asrugh
public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    public static final int NUM_TOPICS = 1;
    public static UserDBHandler userDBHandler;
    public static User currentUser;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setTitle("Welcome to Contraverse");

        String[]btns = {"Religion", "Politics", "The Great Pumpkin"} ;
        LinearLayout p1 = (LinearLayout)findViewById(R.id.p11);

        for (int i=0; i < btns.length; i++) {
            Button b1 = new Button(MainActivity.this);
            b1.setId(i+1);
            b1.setText(btns[i]);
            b1.setTag(i);
            p1.addView(b1);
            b1.setOnClickListener(MainActivity.this);
        }
        String[]btns2 = {"Religion", "Politics", "The Great Pumpkin"} ;
        LinearLayout p2 = (LinearLayout)findViewById(R.id.p12);

        for (int i=0; i < btns.length; i++) {
            Button b1 = new Button(MainActivity.this);
            b1.setId(i+1);
            b1.setText(btns[i]);
            b1.setTag(i);
            p2.addView(b1);
            b1.setOnClickListener(MainActivity.this);
        }

        Button buttonNew = new Button(MainActivity.this);
        String but = "New Conversation" ;
        LinearLayout p3 = (LinearLayout)findViewById(R.id.p13);
        buttonNew.setText("New Conversation");
        buttonNew.setTag(-1);
        p3.addView(buttonNew);
        buttonNew.setOnClickListener(MainActivity.this);


//        Button sett = new Button(MainActivity.this);
//        LinearLayout p4 = (LinearLayout)findViewById(R.id.p14);
//        sett.setTag(-2);
//        p4.addView(sett);
//        sett.setOnClickListener(MainActivity.this);



        userDBHandler = new UserDBHandler(this, null, NUM_TOPICS);

        // Check whether the user has set up their "account" yet
        preferences = getSharedPreferences(getString(R.string.preferences_filename), 0);

        //TODO for debugging the login activity only
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(getString(R.string.user_id_pref_key), -1);
        editor.commit();
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
                Toast.makeText(getApplicationContext(), "we don here " , Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    @Override
//    protected void onStart(){
//        super.onStart();
//
//        long userID = preferences.getLong(getString(R.string.user_id_pref_key), -1); // i = -1 means the default return val is -1
//        toastRelay(userID + "");
//        // If they have not, start the user profile setup activity
//        if(userID == -1){
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }
//        // if they have, get the user's data from our database
//        else{
//            currentUser = userDBHandler.findUser(userID);
//            if(currentUser != null){
//                toastRelay("Welcome " + currentUser.name + "!");
//            } else {
//                toastRelay("Welcome! We didn't find your info.");
//                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + userDBHandler.getTableString());
//            }
//
//        }
//    }

    private void toastRelay(CharSequence toast) {
        Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        if (str.equals("-1")) {
            startActivity(new Intent(MainActivity.this, AddMenu.class));
        }
        else {
            Toast.makeText(getApplicationContext(), "going to conversation " , Toast.LENGTH_SHORT).show();
        }
    }
}
