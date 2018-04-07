package homi.sybelblue.contraversev12;

import java.util.ArrayList;

public class User {

    public String name;
    public final long ID;
    private int[] sfrates;

    public ArrayList<Topic> topics;

    public User(long ID) {
        this.ID = ID;

    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public int getSs() {
        return ss;
    }

    public int getSf() {
        return sf;
    }

    public int getFs() {
        return fs;
    }

    public int getFf() {
        return ff;
    }
}
