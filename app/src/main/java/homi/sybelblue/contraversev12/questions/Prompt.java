package homi.sybelblue.contraversev12.questions;

public class Prompt<T> {

    public final String text;

    private Response<T> response;

    public Prompt(String text) {
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
