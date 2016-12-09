package in.jaaga.learning.platform.helper;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.text.WordUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.jaaga.learning.R;

/**
 * Created by Rahul Kumar on 11/25/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/in.jaaga.learning/databases/";
    private static String DB_NAME = "dictionary";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 2);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        myDataBase = sqLiteDatabase;
        loadDictionary();
    }

    private void loadDictionary(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
            }
        }).start();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }
    }

    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            //database does't exist yet.

        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        final Resources resources = myContext.getResources();
        InputStream myInput = resources.openRawResource(R.raw.dictionary);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public Cursor getResults(String word){
        String result;
        Cursor cursor = myDataBase.rawQuery("Select * from entries where word='"+WordUtils.capitalize(word.trim())+"';", null);
        cursor.moveToFirst();
        System.out.println(cursor.getCount());
        if(cursor.getCount() > 0)
        {
            return cursor;
        }else{
            return null;
        }
    }
}
