package homi.sybelblue.contraversev12;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

public class UserDBHandler extends SQLiteOpenHelper {
    private final int NUM_TOPICS;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users";
    private static final String TABLE_NAME = "Users";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_SS = "SS";
    private static final String COLUMN_SF = "SF";
    private static final String COLUMN_FS = "FS";
    private static final String COLUMN_FF = "FF";

    public UserDBHandler(Context context, SQLiteDatabase.CursorFactory factory, int numTopics) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        NUM_TOPICS = numTopics;
    }

    public String getTableString() {
        StringBuilder result = new StringBuilder();
        String query = "SELECT*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result.append(String.valueOf(result_0));
            result.append(" ");
            result.append(result_1);
            result.append(System.getProperty("line.separator"));
        }
        cursor.close();
        db.close();
        return result.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder CREATE_USER_TOPICS = new StringBuilder("CREATE TABLE " + "Topics" + "(");
        StringBuilder CREATE_USERS_TABLE = new StringBuilder("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_SS +
                " INTEGER," + COLUMN_SF + " INTEGER," + COLUMN_FS + " INTEGER," + COLUMN_FF + " INTEGER,");

        for (int i = 0; i < NUM_TOPICS; i++) {
            String var = "Topic" + i;

            CREATE_USER_TOPICS.append("Yellow");
            CREATE_USER_TOPICS.append(" TEXT,");

            CREATE_USERS_TABLE.append(var);
            CREATE_USERS_TABLE.append(" INTEGER,");
        }

        CREATE_USER_TOPICS.replace(CREATE_USER_TOPICS.length() - 1, CREATE_USER_TOPICS.length(), "");
        CREATE_USERS_TABLE.replace(CREATE_USERS_TABLE.length() - 1, CREATE_USERS_TABLE.length(), "");
        CREATE_USER_TOPICS.append(")");
        CREATE_USERS_TABLE.append(")");
        db.execSQL(CREATE_USERS_TABLE.toString());
        db.execSQL(CREATE_USER_TOPICS.toString());
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.ID);
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_SS, user.getSS());
        values.put(COLUMN_SF, user.getSF());
        values.put(COLUMN_FS, user.getFS());
        values.put(COLUMN_FF, user.getFF());
        for (int i = 0; i < NUM_TOPICS; i++) {
            values.put("Topic" + i, user.topicQuestions[i]);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public User findUser(long ID){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        long foundID;
        String name;
        int SS;
        int SF;
        int FS;
        int FF;
        int[] topicQuestions = new int[NUM_TOPICS];
        if (cursor.moveToFirst()){
            foundID = (long) cursor.getInt(0);
            name = cursor.getString(1);
            SS = cursor.getInt(2);
            SF = cursor.getInt(3);
            FS = cursor.getInt(4);
            FF = cursor.getInt(5);
            for (int i = 6; i < NUM_TOPICS; i++) {
                topicQuestions[i - 6] = cursor.getInt(i);
            }
            User user = new User(foundID, topicQuestions);
            user.name = name;
            user.setSS(SS);
            user.setSF(SF);
            user.setFS(FS);
            user.setFF(FF);
            cursor.close();
            db.close();
            return user;
        }
        db.close();
        return null;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
