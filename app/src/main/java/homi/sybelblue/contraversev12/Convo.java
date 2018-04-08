package homi.sybelblue.contraversev12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import homi.sybelblue.contraversev12.questions.Response;
import homi.sybelblue.contraversev12.activities.MainActivity;
import homi.sybelblue.contraversev12.questions.SpecificQuestion;

/**
 * Created by kajb3 on 4/7/2018.
 */

public class Convo {

    // whenever the Date object is instantiated, the system time will be stored as the date. (This represents the time the convo object as created.)
    public final Date timestamp;
    public final User userA;
    public final User userB;
    public final SpecificQuestion specificQuestion;
    private ArrayList<Response<String>> convoTexts;

    private static final String SEP = "%>%";


    public Convo(Date timestamp, User userA, User userB, SpecificQuestion specificQuestion, Response<String>... convoTexts) {
        this.timestamp = timestamp;
        this.userA = userA;
        this.userB = userB;
        this.specificQuestion = specificQuestion;
        this.convoTexts = new ArrayList<>(Arrays.asList(convoTexts));
    }

    public Convo(Date timestamp, User userA, User userB, SpecificQuestion specificQuestion) {
        this(timestamp, userA, userB, specificQuestion, new Response[0]);
    }

    public Convo(User userA, User userB, SpecificQuestion specificQuestion) {
        this(new Date(), userA, userB, specificQuestion);
    }

    // --------------------------------------------------------------------
    // Getters and setters
    // --------------------------------------------------------------------

    public Response<String>[] getConvoTexts() {
        return (Response<String>[]) convoTexts.toArray();
    }

    /**
     * Gets a message in the conversation that was sent at a certain time.
     * @param convoTextTimestamp
     * @return The message with given timestamp, or null if no such message
     */
    public Response getConvoTextAt(Date convoTextTimestamp){
        for(Response convoText : convoTexts){
            if(convoText.timestamp.equals(convoTextTimestamp)){
                return convoText;
            }
        }
        return null;
    }


    public String encode(){
        String encoded = userB.name + SEP + userB.ID;
        for(Response<String> response : convoTexts ){
            encoded += SEP + response.response + SEP + (response.user.equals(userA) ? "A" : "B");
        }

        return encoded;
    }

    public static Convo decode(String encoded){
        String[] parts = encoded.split(SEP);
        String userBName = parts[0];
        long userBID = Long.parseLong(parts[1]);

        User userA = MainActivity.currentUser;
        User userB = new User(userBName, userBID, new int[0]);

        Convo convo = new Convo(userA, userB, null);
        convo.convoTexts = new ArrayList<Response<String>>();

        for(int i = 2; i < parts.length; i+=2){
            User user = (parts[i+1].equals("A") ? userA : userB);
            Response<String> response = new Response<>(user, parts[i]);
            convo.convoTexts.add(response);
        }

        return convo;
    }

}
