package homi.sybelblue.contraversev12;

public class Question {

    public final String text;

    private Response response;

    public Question(String text) {
        this.text = text;
    }

    public boolean hasResponse() {
        return response != null;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
