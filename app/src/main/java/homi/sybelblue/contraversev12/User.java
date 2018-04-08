package homi.sybelblue.contraversev12;

import java.util.ArrayList;
import static homi.sybelblue.contraversev12.ContraverseUtils.*;

public class User {

    public String name;
    public final long ID;
    private int[] sfrates;
    public final int[] topicQuestions;

    private int rating, level;

    public static ArrayList<Topic> topics;

    public User(long ID, int[] topicQuestions) {
        this.ID = ID;
        this.topicQuestions = new int[topics.size()];
        updateRatingAndLevel();

        sfrates = new int[SFCODE_SIZE];
        topics = new ArrayList<>(15);
    }

    public String getName() {
        return name;
    }

    /**
     * Records that User had an incident with code.
     * If code is SF or SS, Success is also incremented.
     * If code is FS or FF, Fail is also incremented.
     *
     * @param code code to record
     */
    public void recordSF(SFCode code) {
        sfrates[numberFromCode(code)]++;

        switch (code) {
            case SF:
            case SS:
                sfrates[numberFromCode(SFCode.Success)]++;
                break;
            case FS:
            case FF:
                sfrates[numberFromCode(SFCode.Fail)]++;
                break;
        }

        updateRatingAndLevel();
    }

    /**
     * Updates the rating and level fields for this user
     * based on the recorded SFCodes
     */
    private void updateRatingAndLevel() {
        rating = 2*getSS() + Math.min(getSS(), getSF() + getFS()) - Math.max(getSF(), getFF());
        level = (int) Math.sqrt(rating() + 4) - 1;
    }

    public int getSS() {
        return getCodeCount(SFCode.SS);
    }

    public int getSF() {
        return getCodeCount(SFCode.SF);
    }

    public int getFS() {
        return getCodeCount(SFCode.FS);
    }

    public int getFF() {
        return getCodeCount(SFCode.FF);
    }

    /**
     * Gets the number of times user recorded code
     *
     * @param code code to retrieve count for
     * @return number of times code was recorded for User
     */
    public int getCodeCount(SFCode code) {
        return sfrates[numberFromCode(code)];
    }

    /**
     * Returns the level of the user
     *
     * @return the user's level
     */
    public int level() {
        return level;
    }

    /**
     * Calculated rating for the user based on recorded SFCodes
     *
     * @return user rating
     */
    public int rating() {
        return rating;
    }

}
