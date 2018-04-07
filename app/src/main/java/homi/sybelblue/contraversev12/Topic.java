package homi.sybelblue.contraversev12;

public class Topic extends Question {

    public static enum Status {
        open, closed
    }

    //public final String text; from Question
    public final Question[] subQuestions;
    public final User madeBy;
    public final Rating rating;

    private Status status;

    public Topic(String primaryText, User madeBy, Question... subQuestions) {
        super(primaryText);
        status = Status.open;
        this.madeBy = madeBy;
        this.subQuestions = subQuestions;
        rating = new Rating();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
