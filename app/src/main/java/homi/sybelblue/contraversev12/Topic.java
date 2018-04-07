package homi.sybelblue.contraversev12;

public class Topic extends Question {

    public static enum Status {
        open, closed
    }

    //public final String response; from Question
    public final Question[] subQuestions;
    //public final User madeBy;
    public final Rating rating;

    private Status status;

    public Topic(String primaryText, Question... subQuestions) {
        super(primaryText);
        status = Status.open;
        //this.madeBy = madeBy;
        this.subQuestions = subQuestions;
        rating = new Rating();
    }

    public boolean isOpen() {
        return status == Status.open;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
