package homi.sybelblue.contraversev12;

import java.util.ArrayList;
import static homi.sybelblue.contraversev12.ContraverseUtils.*;

public class User {

    public String name;
    public final long ID;
    private int[] sfrates;

    public ArrayList<Topic> topics;

    public User(long ID) {
        this.ID = ID;

        sfrates = new int[SFCode.values().length];
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
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
    }

    public int getSS() {
        return sfrates[numberFromCode(SFCode.SS)];
    }

    public int getSF() {
        return sfrates[numberFromCode(SFCode.SF)];
    }

    public int getFS() {
        return sfrates[numberFromCode(SFCode.FS)];
    }

    public int getFF() {
        return sfrates[numberFromCode(SFCode.FF)];
    }


}
