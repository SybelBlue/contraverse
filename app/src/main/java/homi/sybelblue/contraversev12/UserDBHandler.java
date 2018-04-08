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
    private static final String USERS_TABLE = "Users";
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
        String query = "SELECT*FROM " + USERS_TABLE + ";";
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
        StringBuilder CREATE_USERS_TABLE = new StringBuilder("CREATE TABLE " + USERS_TABLE + "(" + COLUMN_ID +
                " BIGINT PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_SS +
                " INTEGER," + COLUMN_SF + " INTEGER," + COLUMN_FS + " INTEGER," + COLUMN_FF + " INTEGER," + "Rating INTEGER,");

        for (int i = 0; i < NUM_TOPICS; i++) {
            String var = "Topic" + i;

            CREATE_USER_TOPICS.append(var);
            CREATE_USER_TOPICS.append(" TEXT,");

            CREATE_USERS_TABLE.append(var);
            CREATE_USERS_TABLE.append(" INTEGER,");
        }

        CREATE_USER_TOPICS.replace(CREATE_USER_TOPICS.length() - 1, CREATE_USER_TOPICS.length(), "");
        CREATE_USERS_TABLE.replace(CREATE_USERS_TABLE.length() - 1, CREATE_USERS_TABLE.length(), "");
        CREATE_USER_TOPICS.append(");");
        CREATE_USERS_TABLE.append(");");
        db.execSQL(CREATE_USERS_TABLE.toString());
        db.execSQL(CREATE_USER_TOPICS.toString());
    }

    public void addUser(User user) {
        int SS = user.getSS();
        int SF = user.getSF();
        int FS = user.getFS();
        int FF = user.getFF();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.ID);
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_SS, SS);
        values.put(COLUMN_SF, SF);
        values.put(COLUMN_FS, FS);
        values.put(COLUMN_FF, FF);
        values.put("Rating", generateRating(SS, SF, FS, FF));
        for (int i = 0; i < NUM_TOPICS; i++) {
            values.put("Topic" + i, user.topicQuestions[i]);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS_TABLE, null, values);
        db.close();
    }

    public User findUser(long ID){
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + COLUMN_ID + " = " + ID + ";";
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
            user.setRating(generateRating(SS, SF, FS, FF));
            cursor.close();
            db.close();
            return user;
        }
        db.close();
        return null;
    }

    private int generateRating(int SS, int SF, int FS, int FF){
        return  2*SS + Math.min(SS, (SF + FS));
    }

    public void incrementSS(long ID) {
        String getValsQuery = "SELECT SS, SF, FS, FF FROM USERS WHERE ID = " + ID + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getValsQuery, null);
        if (cursor.moveToFirst()) {
            int SS = cursor.getInt(0);
            int SF = cursor.getInt(1);
            int FS = cursor.getInt(2);
            int FF = cursor.getInt(3);
            int rating = generateRating(SS, SF, FS, FF);
            String query = "UPDATE Users SET SS = SS + 1, Rating = " + rating + " WHERE ID = " + ID + ";";
            db.execSQL(query);
            cursor.close();
        }
        db.close();
    }

    public int getIntegerColumn(long ID, String column, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + column +  " FROM " + tableName + " WHERE ID = " + ID + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int result = cursor.getInt(0);
            cursor.close();
            db.close();
            return result;
        }
        db.close();
        return -1;
    }

    public String getStringColumn(long ID, String column, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + column +  " FROM " + tableName + " WHERE ID = " + ID + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String result = cursor.getString(0);
            cursor.close();
            db.close();
            return result;
        }
        db.close();
        return "";
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
