package homi.sybelblue.contraversev12.questions;

import homi.sybelblue.contraversev12.SFCode;

public class SpecificQuestion {

    public static enum Status {
        open, closed
    }

    public final Prompt<Integer> multipleChoice;
    public final Prompt<String> rationale;

    public short rating;

    private SFCode sfCode;
    private Status status;

    public SpecificQuestion(Prompt<Integer> multipleChoice, Prompt<String> rationale, short rating) {
        this.multipleChoice = multipleChoice;
        this.rationale = rationale;
        status = Status.open;
        this.rating = rating;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isOpen() {
        return status == Status.open;
    }

    public SFCode getSFCode() {
        return sfCode;
    }

    public void setSFCode(SFCode sfCode) {
        this.sfCode = sfCode;
    }
}
