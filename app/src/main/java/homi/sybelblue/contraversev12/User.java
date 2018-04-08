package homi.sybelblue.contraversev12;

import static homi.sybelblue.contraversev12.ContraverseUtils.SFCODE_SIZE;
import static homi.sybelblue.contraversev12.ContraverseUtils.numberFromCode;

public class User {

    public String name;
    public final long ID;
    private int[] sfrates;
    public final int[] topicQuestions;

    private int rating, level;

    public User(long ID, int[] topicQuestions) {
        this.ID = ID;
        this.topicQuestions = topicQuestions;

        sfrates = new int[SFCODE_SIZE];
        updateRatingAndLevel();
    }

    public User(String name, long ID, int[] topicQuestions) {
        this(ID, topicQuestions);
        this.name = name;
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

    public void setSS(int count) {
        setCodeCount(SFCode.SS, count);
    }

    public void setSF(int count) {
        setCodeCount(SFCode.SF, count);
    }

    public void setFS(int count) {
        setCodeCount(SFCode.FS, count);
    }

    public void setFF(int count) {
        setCodeCount(SFCode.FF, count);
    }

    public void setCodeCount(SFCode code, int count) {
        sfrates[numberFromCode(code)] = count;
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
