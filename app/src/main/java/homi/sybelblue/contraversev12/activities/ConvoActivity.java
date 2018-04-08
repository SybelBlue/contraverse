package homi.sybelblue.contraversev12.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import homi.sybelblue.contraversev12.R;

public class ConvoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convo);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

        }
    }

}
