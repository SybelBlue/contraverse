package homi.sybelblue.contraversev12;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import java.util.Random;

import homi.sybelblue.contraversev12.activities.MainActivity;

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
                " INTEGER," + COLUMN_SF + " INTEGER," + COLUMN_FS + " INTEGER," + COLUMN_FF + " INTEGER,");
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE QUESTIONS(Topic TEXT, Level INTEGER, Question TEXT);";

        for (int i = 0; i < NUM_TOPICS; i++) {
            String str = "Topic" + i;

            CREATE_USER_TOPICS.append(str);
            CREATE_USER_TOPICS.append(" TEXT,");

            CREATE_USERS_TABLE.append(str);
            CREATE_USERS_TABLE.append(" INTEGER,");
        }

        CREATE_USER_TOPICS.replace(CREATE_USER_TOPICS.length() - 1, CREATE_USER_TOPICS.length(), ");");
        CREATE_USERS_TABLE.replace(CREATE_USERS_TABLE.length() - 1, CREATE_USERS_TABLE.length(), ");");
        db.execSQL(CREATE_USERS_TABLE.toString());
        db.execSQL(CREATE_USER_TOPICS.toString());
        db.execSQL(CREATE_QUESTIONS_TABLE);
    }

    public void addQuestion(String topic, String question, int level) {
        ContentValues values = new ContentValues();
        values.put("Topic", topic);
        values.put("Level", level);
        values.put("Question", question);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Questions", null, values);
        db.close();
    }

    public String getTopicQuestion(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Question FROM Questions WHERE Topic = " + topic + " AND Rating IS NULL;";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        cursor.close();
        db.close();
        return "";
    }

    private boolean isViableTopic(long ID1, long ID2, String topic, int rating1, int rating2) {
        SQLiteDatabase db = this.getWritableDatabase();
        int user1Val;
        int user2Val;
        String query = "SELECT " + topic + " FROM Users WHERE ID = " + ID1 + " OR ID = " + ID2 + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 2) {
            user1Val = cursor.getInt(0);
            user2Val = cursor.getInt(1);
            if (user1Val != user2Val && Math.abs(rating1 - rating2) <= 2) {
                cursor.close();
                return true;
            }
        }
        db.close();
        return false;
    }

    public String getQuestion(long ID1, long ID2) {
        SQLiteDatabase db = this.getWritableDatabase();
        Random random = new Random();
        int rating1 = getRating(ID1);
        int rating2 = getRating(ID2);
        int lowerRating = Math.min(rating1, rating2);
        int topicNum = random.nextInt(MainActivity.NUM_TOPICS);
        String potentialTopic = "Topic" + topicNum;
        if (isViableTopic(ID1, ID2, potentialTopic, rating1, rating2)) {
            String query = "SELECT Question FROM Questions WHERE Topic = " + potentialTopic + " AND Rating <= " + lowerRating + ";";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                return cursor.getString(random.nextInt(cursor.getCount()));
            }
            cursor.close();
        }
        return "";
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
        for (int i = 0; i < NUM_TOPICS; i++) {
            values.put("Topic" + i, user.topicQuestions[i]);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS_TABLE, null, values);
        db.close();
    }

    public User findUser(long ID) {
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
        if (cursor.moveToFirst()) {
            foundID = (long) cursor.getInt(0);
            name = cursor.getString(1);
            SS = cursor.getInt(2);
            SF = cursor.getInt(3);
            FS = cursor.getInt(4);
            FF = cursor.getInt(5);
            for (int i = 6; i < NUM_TOPICS + 6; i++) {
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

    private int generateRating(int SS, int SF, int FS, int FF) {
        return 2 * SS + Math.min(SS, (SF + FS));
    }

    public void incrementSS(long ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET SS = SS + 1 WHERE ID = " + ID + ";";
        db.execSQL(query, null);
        db.close();
    }

    public void incrementSF(long ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET SF = SF + 1 WHERE ID = " + ID + ";";
        db.execSQL(query, null);
        db.close();
    }

    public void incrementFS(long ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET FS = FS + 1 WHERE ID = " + ID + ";";
        db.execSQL(query, null);
        db.close();
    }

    public void incrementFF(long ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Users SET FF = FF + 1 WHERE ID = " + ID + ";";
        db.execSQL(query, null);
        db.close();
    }

    public int getRating(long ID) {
        User user = findUser(ID);
        return generateRating(user.getSS(), user.getSF(), user.getFS(), user.getFF());
    }

    public int getIntegerColumn(long ID, String column, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + column + " FROM " + tableName + " WHERE ID = " + ID + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
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
        String query = "SELECT " + column + " FROM " + tableName + " WHERE ID = " + ID + ";";
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
