package in.jaaga.learning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 27/2/16.
 */
public class ProgressDbHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "progress.db";

    public ProgressDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DETAILS_TABLE = "CREATE TABLE " + ProgressContract.UserDetails.TABLE_NAME
                + " ( " + ProgressContract.UserDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ProgressContract.UserDetails.COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ProgressContract.UserDetails.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                ProgressContract.UserDetails.COLUMN_CREATION_DATE + " TEXT, " +
                ProgressContract.UserDetails.COLUMN_POINTS + " REAL NOT NULL " +
                " )";
        final String SQL_CREATE_DATA_TABLE = "CREATE TABLE " + ProgressContract.UserData.TABLE_NAME +
                " ( " + ProgressContract.UserData._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProgressContract.UserData.COLUMN_USER_ID + " INTEGER NOT NULL, " +
                ProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED + " TEXT NOT NULL, " +
                ProgressContract.UserData.COLUMN_POINTS_SCORED + " REAL NOT NULL, " +
                "FOREIGN KEY ( " + ProgressContract.UserData.COLUMN_USER_ID + " ) " +
                "REFERENCES " + ProgressContract.UserDetails.TABLE_NAME+ " ( "  + ProgressContract.UserDetails._ID
                + " ) " + " ) ";
        db.execSQL(SQL_CREATE_DETAILS_TABLE);
        db.execSQL(SQL_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProgressContract.UserDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProgressContract.UserData.TABLE_NAME );
    }
}
