package homi.sybelblue.contraversev12;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kajb3 on 4/7/2018.
 */

public class Convo {

    // whenever the Date object is instantiated, the system time will be stored as the date. (This represents the time the convo object as created.)
    public final Date timestamp;
    public final User userA;
    public final User userB;
    public final SpecificQuestion specificQuestion;
    private ArrayList<Response> convoTexts;


    public Convo(Date timestamp, User userA, User userB, SpecificQuestion specificQuestion, Response... convoTexts) {
        this.timestamp = timestamp;
        this.userA = userA;
        this.userB = userB;
        this.specificQuestion = specificQuestion;
        this.convoTexts = new ArrayList<Response>(convoTexts);
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

    public Response[] getConvoTexts() {
        return convoTexts;
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

}
