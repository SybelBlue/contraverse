package homi.sybelblue.contraversev12.questions;

import java.util.Date;

import homi.sybelblue.contraversev12.User;

public class Response<T> {

    public final Date timestamp;
    public final User user;
    public final T response;

    public Response(User user, T response) {
        this.user = user;
        this.response = response;
        timestamp = new Date();
    }
}
