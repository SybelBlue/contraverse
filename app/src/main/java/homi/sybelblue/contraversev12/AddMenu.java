package homi.sybelblue.contraversev12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import homi.sybelblue.contraversev12.questions.Prompt;
import homi.sybelblue.contraversev12.questions.SpecificQuestion;

public class AddMenu extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private RadioGroup radioGroup;
    private Button next;

    private SpecificQuestion specificQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        questionTextView = findViewById(R.id.add_menu_question);
        radioGroup = findViewById(R.id.add_menu_radios);
        next = findViewById(R.id.add_menu_next);
        next.setOnClickListener(this);

        // TODO get SpecificQuestion object from database
        // for now I have made one up. I don't know if this is exactly the right idea, but it can be changed :)
        specificQuestion = new SpecificQuestion(
                new Prompt<>("Which way should the toilet paper roll be hung - loose end in front, or loose end in back?"),
                new Prompt<>("Why do you think this?"),
                (short)0);

        questionTextView.setText("TOPIC: " + specificQuestion.topicHeading + "\n"
                + specificQuestion.multipleChoice.text);

        // we will eventually need to get the text for each radio button for the Specific Question, but for now I have hard-coded them in an array:
        String[] radioTexts = {"Loose end in front", "Loose end in back"};

        // make a new radio button for each multiple choice answer in the Specific Question.
        for(String radioText : radioTexts){
            RadioButton button = new RadioButton(this);
            button.setText(radioText);
            // index -1 means the end of the list, and the ViewGroup.LayoutParams just tells it to wrap_content for width and height
            radioGroup.addView(button, -1,
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }


        // this is code that we can use to help mock up having multiple possible questions.
        String options[] = {"x","y","z",};
        Random rand = new Random();
        int i = rand.nextInt(options.length);

        TextView ti = new TextView(AddMenu.this);
        String title = options[i];
        LinearLayout P1 = (LinearLayout)findViewById(R.id.p1);
        ti.setText(options[i]);
        ti.setTag(-1);
        //P1.addView(ti);

        String results[] = new String[options.length]; //The Logan Autocomplete tried correcting this, if not so, fix

    }



// I made the hard-coded radiobutton, radiobutton2, etc visibility=View.GONE in the xml because the
// screen has overlapping radiogroups currently (once the other buttons populate). -Keara
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
            int i;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    i = 1;
                    break;
            case R.id.radioButton2:
                if (checked)
                    i = 2;
                    break;
            case R.id.radioButton3:
                if (checked)
                    i = 3;
                    break;
        }
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

        switch (v.getId()){
            case R.id.add_menu_next:

                // TODO I cannot actually get the intent to go to the next activity! Oh well
                // if a radio button is selected, allow user to move on
                if(radioGroup.getCheckedRadioButtonId() != -1){
                    Intent intent = new Intent(AddMenu.this, SQAnsReasonActivity.class);
                    intent.putExtra("topic", specificQuestion.topicHeading);
                    intent.putExtra("questionText", specificQuestion.multipleChoice.text);
                    Button pickedButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    intent.putExtra("response", pickedButton.getText());
                    startActivity(intent);
                }
                //TODO this does not work maybe? It doesn't make a toast for sure but eh
                else{
                    Toast.makeText(getApplicationContext(), "Please select an option.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

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
