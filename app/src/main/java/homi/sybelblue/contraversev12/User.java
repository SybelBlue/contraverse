package homi.sybelblue.contraversev12;

import java.util.ArrayList;

public class User {

    public String name;
    private long ID;
    private int ss;
    private int sf;
    private int fs;
    private int ff;

    public ArrayList<Topic> topics;

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
