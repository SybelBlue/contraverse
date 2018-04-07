package homi.sybelblue.contraversev12;

public class Rating {

    public static enum SFCode {
        S, F, SS, SF, FS, FF
    }

    private long rating;

    public Rating() {
        this.rating = 1;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    //public void incrementBy(SFCode code) { }
}
