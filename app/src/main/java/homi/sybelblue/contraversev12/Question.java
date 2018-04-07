package homi.sybelblue.contraversev12;

public class Question<T> {

    public final String text;

    private Response<T> response;

    public Question(String text) {
        this.text = text;
    }

    public boolean hasResponse() {
        return response != null;
    }

    public Response<T> getResponse() {
        return response;
    }

    public void setResponse(Response<T> response) {
        this.response = response;
    }
}
