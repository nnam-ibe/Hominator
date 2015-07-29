package seg.user.interface3125.thehominator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Khalid on 2015-07-23.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "App.db";
    public static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    // DB Variables
    public static final String TABLE1 = "users";
    public static final String TABLE1_UID = "uid";
    public static final String TABLE1_FNAME = "firstName";
    public static final String TABLE1_LNAME = "lastName";
    public static final String TABLE1_ADDRESS = "address";
    public static final String TABLE1_USERNAME = "username";
    public static final String TABLE1_PASSWORD = "password";
    public static final String TABLE1_EMAIL = "email";
    public static final String TABLE1_COMM = "community";
    public static final String TABLE1_LOGGEDIN = "loggedIn";

    public static final String TABLE2 = "landlords";
    public static final String TABLE2_LID = "lid";
    public static final String TABLE2_UID = "uid";
    public static final String TABLE2_PROP = "ownedProperty";

    public static final String TABLE3 = "bills";
    public static final String TABLE3_BID = "bid";
    public static final String TABLE3_UID = "uid";
    public static final String TABLE3_BNAME = "billName";
    public static final String TABLE3_BDESC = "billDesc";
    public static final String TABLE3_BVAL = "billVal";

    public static final String TABLE4 = "complaints";
    public static final String TABLE4_CID = "cid";
    public static final String TABLE4_UID = "uid";
    public static final String TABLE4_LID = "lid";
    public static final String TABLE4_CHEAD = "compHeading";
    public static final String TABLE4_CDESC = "compDesc";

    public static final String TABLE5 = "party";
    public static final String TABLE5_PID = "pid";
    public static final String TABLE5_UID = "uid";
    public static final String TABLE5_PARTYDATE = "date";
    public static final String TABLE5_PARTYSTRTTIME = "startTime";
    public static final String TABLE5_PARTYENDTIME = "endTime";

    User user1 = new User("John","Obi Mikel","johnobimikel@gmail.com","JohnJimmyObi","johnObi23","23 Stamford Bridge","Bayshore",0);
    User user2 = new User("Itachi","Uchiha","sharingan1@gmail.com","AkatsukisBest","Itachi123","123 Konoha Way","Downtown",0);
    User user3 = new User("Barack","Obama","whitehousepimpin@gmail.com","DaBoss1","Obama23!","1 White House","Kanata",0);


    // DB Create tables
    private static final String TABLE1_CREATE = "create table "
            + TABLE1 + "(" + TABLE1_UID
            + " integer primary key autoincrement unique, " + TABLE1_FNAME
            + " text not null, " + TABLE1_LNAME + " text not null, " + TABLE1_ADDRESS + " text not null, "
            + TABLE1_EMAIL + " text not null, " + TABLE1_USERNAME + " text not null," + TABLE1_PASSWORD + " text not null," + TABLE1_COMM + " text not null, " + TABLE1_LOGGEDIN + " integer not null);";


    private static final String TABLE2_CREATE = "create table "
            + TABLE2 + "(" + TABLE2_LID
            + " integer primary key autoincrement unique not null, " + TABLE2_UID + " integer not null, " + TABLE2_PROP
            + " text not null, " + " FOREIGN KEY (" + TABLE2_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + ") );";

    private static final String TABLE3_CREATE = "create table "
            + TABLE3 + "(" + TABLE3_BID
            + " integer primary key autoincrement unique not null, " + TABLE3_UID + " integer not null," + TABLE3_BNAME
            + " text not null, " + TABLE3_BDESC + " text not null, " + TABLE3_BVAL + " text not null, " + "FOREIGN KEY (" + TABLE3_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + ") );";

    private static final String TABLE4_CREATE = "create table "
            + TABLE4 + "(" + TABLE4_CID
            + " integer primary key autoincrement unique not null, " + TABLE4_UID + " integer not null," + TABLE4_LID
            + " integer not null," + TABLE4_CHEAD + " text not null, " + TABLE4_CDESC + " text not null, " + "FOREIGN KEY (" + TABLE4_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + "), "
            + "FOREIGN KEY (" + TABLE4_LID + ") REFERENCES " + TABLE2 + "(" + TABLE2_LID + ") );";


    private static final String TABLE5_CREATE = "create table "
            + TABLE5 + "(" + TABLE5_PID
            + " integer primary key autoincrement unique not null, " + TABLE5_UID
            + " integer not null, " + TABLE5_PARTYDATE
            + " date not null, " + TABLE5_PARTYSTRTTIME + " time not null, "
            + TABLE5_PARTYENDTIME + " time not null, " + "FOREIGN KEY (" + TABLE5_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + "));";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void insertUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE1_FNAME, user.getFirstName());
        values.put(TABLE1_LNAME, user.getLastName());
        values.put(TABLE1_EMAIL, user.getEmail());
        values.put(TABLE1_USERNAME, user.getUsername());
        values.put(TABLE1_PASSWORD, user.getPassword());
        values.put(TABLE1_ADDRESS, user.getAddress());
        values.put(TABLE1_COMM, user.getCommunity());
        values.put(TABLE1_LOGGEDIN,user.getLoggedIn());

        db.insert(TABLE1, null, values);
    }

    public Cursor authenticateUsername(DBHelper helper, String username) {

        SQLiteDatabase db = helper.getReadableDatabase();
        String[] column = new String[]{this.TABLE1_USERNAME};
        String whereClause = "username = ?";
        String[] whereArgs = new String[]{username};
        Cursor query = db.query(this.TABLE1, column, whereClause, whereArgs, null, null, null);

        return query;
    }

    public void updateUserLogin(String username, DBHelper helper, int val){

        SQLiteDatabase db = helper.getWritableDatabase();
        //helper.onUpgrade(db,2,3);
        ContentValues cv = new ContentValues();
        cv.put(this.TABLE1_LOGGEDIN, val);
        String [] whereArgs = new String[]{username};

        db.update(this.TABLE1,cv,"username = ?",whereArgs);

    }


    public Cursor authenticateEmail(DBHelper helper, String email) {

        SQLiteDatabase db = helper.getReadableDatabase();
        String[] column = new String[]{this.TABLE1_EMAIL};
        String whereClause = "email = ?";
        String[] whereArgs = new String[]{email};
        Cursor query = db.query(this.TABLE1, column, whereClause, whereArgs, null, null, null);

        return query;
    }

    public Cursor authenticateUserLogin(DBHelper helper, String username, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] columns = new String[]{this.TABLE1_USERNAME, this.TABLE1_PASSWORD};
        String whereClause = "username = ? OR password = ?";
        String[] whereArgs = new String[]{username, password};
        Cursor query = db.query(this.TABLE1, columns, whereClause, whereArgs, null, null, null);

        return query;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create tables
        sqLiteDatabase.execSQL(TABLE1_CREATE);
        this.insertUser(user1);
        this.insertUser(user2);
        this.insertUser(user3);
        sqLiteDatabase.execSQL(TABLE2_CREATE);
        sqLiteDatabase.execSQL(TABLE3_CREATE);
        sqLiteDatabase.execSQL(TABLE4_CREATE);
        sqLiteDatabase.execSQL(TABLE5_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Upgrade DB if changes made
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE1);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE2);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE3);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE4);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE5);
        onCreate(sqLiteDatabase);
    }
}
