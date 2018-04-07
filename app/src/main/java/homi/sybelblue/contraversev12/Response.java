package homi.sybelblue.contraversev12;

import java.util.Date;

public class Response {

    public final Date timestamp;
    public final User user;
    public final String text;

    public Response(User user, String text) {
        this.user = user;
        this.text = text;
        timestamp = new Date();
    }
}
