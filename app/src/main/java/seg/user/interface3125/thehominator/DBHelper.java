package seg.user.interface3125.thehominator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Khalid on 2015-07-23.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "App.db";
    public static final int DATABASE_VERSION = 1;

    // DB Variables
    public static final String TABLE1 = "users";
    public static final String TABLE1_UID = "uid";
    public static final String TABLE1_FNAME = "firstName";
    public static final String TABLE1_LNAME = "lastName";
    public static final String TABLE1_ADDRESS = "address";
    public static final String TABLE1_ROLE = "role";
    public static final String TABLE1_EMAIL = "email";

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

    // DB Create tables
    private static final String TABLE1_CREATE = "create table "
            + TABLE1 + "(" + TABLE1_UID
            + " integer primary key autoincrement unique, " + TABLE1_FNAME
            + " text not null, " + TABLE1_LNAME + " text not null, " + TABLE1_ADDRESS + " text not null, "
            + TABLE1_EMAIL + " text not null);";

    private static final String TABLE2_CREATE = "create table "
            + TABLE2 + "(" + TABLE2_LID
            + " integer primary key autoincrement unique not null, " + TABLE2_UID + " integer not null, " + TABLE2_PROP
            + " text not null, " + " FOREIGN KEY (" + TABLE2_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + ") );";

    private static final String TABLE3_CREATE = "create table "
            + TABLE3 + "(" + TABLE3_BID
            + " integer primary key autoincrement unique not null, " + TABLE3_UID + " integer not null," + TABLE3_BNAME
            + " text not null, " + TABLE3_BDESC + " text not null, " + TABLE3_BVAL + " text not null, " +  "FOREIGN KEY (" + TABLE3_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + ") );";

    private static final String TABLE4_CREATE = "create table "
            + TABLE4 + "(" + TABLE4_CID
            + " integer primary key autoincrement unique not null, " + TABLE4_UID + " integer not null," + TABLE4_LID
            + " integer not null," +  TABLE4_CHEAD + " text not null, " + TABLE4_CDESC + " text not null, " +  "FOREIGN KEY (" + TABLE4_UID + ") REFERENCES " + TABLE1 + "(" + TABLE1_UID + "), "
            +  "FOREIGN KEY (" + TABLE4_LID + ") REFERENCES " + TABLE2 + "(" + TABLE2_LID + ") );";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create tables
        sqLiteDatabase.execSQL(TABLE1_CREATE);
        sqLiteDatabase.execSQL(TABLE2_CREATE);
        sqLiteDatabase.execSQL(TABLE3_CREATE);
        sqLiteDatabase.execSQL(TABLE4_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Upgrade DB if changes made
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4);
        onCreate(sqLiteDatabase);
    }
}
