package in.jaaga.learning.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Switch;

import in.jaaga.learning.android.S;

/**
 * Created by root on 27/2/16.
 */
public class ProgressProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = buildUriMatcher();
    private ProgressDbHelper mProgressDbHelper;

    static final int DETAILS = 100;
    static final int DETAILS_WITH_USER = 101;
    static final int DATA = 200;
    static final int DATA_WITH_USER_ID_AND_SKILL_ATTEMPTED = 201;
    static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ProgressContract.CONTENT_AUTHORITY, ProgressContract.PATH_DETAILS + "/*", DETAILS_WITH_USER);
        uriMatcher.addURI(ProgressContract.CONTENT_AUTHORITY, ProgressContract.PATH_DETAILS, DETAILS);
        uriMatcher.addURI(ProgressContract.CONTENT_AUTHORITY, ProgressContract.PATH_DATA,DATA);
        uriMatcher.addURI(ProgressContract.CONTENT_AUTHORITY, ProgressContract.PATH_DATA + "/*", DATA_WITH_USER_ID_AND_SKILL_ATTEMPTED);
        return uriMatcher;
    }
    //Selection for user name
    //details.user_name = ?
    private static final String sUserNameAndSkillSelection =
            ProgressContract.UserData.COLUMN_USER_ID + " = ? AND " + ProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED
            + " = ? ";

    private Cursor getCursorFromUidAndSkill(Uri uri,String[] projections, String sortOrder) {
        int userId = ProgressContract.UserData.getUserIdFromUri(uri);
        String skillAttempted = ProgressContract.UserData.getSkillAttemptedFromUri(uri);
        Cursor cursor =  mProgressDbHelper.getReadableDatabase().query(ProgressContract.UserData.TABLE_NAME,
                projections,sUserNameAndSkillSelection,new String[]{Integer.toString(userId),skillAttempted},
                null,null,sortOrder);
        return cursor;
    }
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (uriMatcher.match(uri)) {
            case DETAILS_WITH_USER: {
                //retCursor = getProgressByUserName(uri, projection, sortOrder);
                retCursor = mProgressDbHelper.getReadableDatabase().query(ProgressContract.UserDetails.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
            }
            case DETAILS: {
                retCursor = mProgressDbHelper.getReadableDatabase().query(ProgressContract.
                        UserDetails.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case DATA_WITH_USER_ID_AND_SKILL_ATTEMPTED: {
                retCursor = mProgressDbHelper.getReadableDatabase().query(ProgressContract.UserData.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                //retCursor = getCursorFromUidAndSkill(uri,projection,sortOrder);
                break;
            }
            case DATA: {
                retCursor = mProgressDbHelper.getReadableDatabase().query(ProgressContract.UserData.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown URI: "+uri);
            }
        }
        return retCursor;
    }

    /*private Cursor getProgressByUserName(Uri uri, String[] projection, String sortOrder) {
        String userName = ProgressContract.UserDetails.getUserNameFromUri(uri);
        return mProgressDbHelper.getReadableDatabase().query(ProgressContract.UserDetails.TABLE_NAME, projection,
                sUserNameSelection, new String[]{userName}, null, null, sortOrder);
    }*/
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mProgressDbHelper.getWritableDatabase();
        final int match = buildUriMatcher().match(uri);
        Uri retUri;
        switch (match)  {
            case DETAILS: {
                long _id = db.insert(ProgressContract.UserDetails.TABLE_NAME, null, values);
                if (_id > 0) {
                    retUri = ProgressContract.UserDetails.buildDetailsUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into" + uri);
                }
                break;
            }
            case DATA: {
                long _id = db.insert(ProgressContract.UserData.TABLE_NAME,null,values);
                if(_id > 0) {
                    retUri = ProgressContract.UserData.buildDataUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into" + uri);
                }
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown Uri" + uri);
            }
        }
        //getContext().getContentResolver().notifyChange(uri, null);
        return retUri;
    }
    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = buildUriMatcher().match(uri);
        String retType;
        switch (match) {
            case DETAILS: {
                retType = ProgressContract.UserDetails.CONTENT_TYPE;
                break;
            }
            case DETAILS_WITH_USER: {
                retType = ProgressContract.UserDetails.CONTENT_ITEM_TYPE;
                break;
            }
            case DATA: {
                retType = ProgressContract.UserData.CONTENT_TYPE;
                break;
            }
            case DATA_WITH_USER_ID_AND_SKILL_ATTEMPTED: {
                retType = ProgressContract.UserData.CONTENT_ITEM_TYPE;
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown Uri" + uri);
            }
        }
        return retType;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mProgressDbHelper.getWritableDatabase();
        final int match = buildUriMatcher().match(uri);
        int linesUpdated;
        switch (match) {
            case DETAILS: {
                linesUpdated = db.update(ProgressContract.UserDetails.TABLE_NAME,values,selection,selectionArgs);
                break;
            }
            case DATA: {
                linesUpdated = db.update(ProgressContract.UserData.TABLE_NAME,values,selection,selectionArgs);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown Uri" + uri);
            }
        }
        if(linesUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return linesUpdated;
    }
    @Override
    public boolean onCreate() {
        mProgressDbHelper = new ProgressDbHelper(getContext());
        return true;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mProgressDbHelper.getWritableDatabase();
        final int match = buildUriMatcher().match(uri);
        int linesDeleted;
        switch (match) {
            case DETAILS: {
                linesDeleted = db.delete(ProgressContract.UserDetails.TABLE_NAME,selection,selectionArgs);
                break;
            }
            case DATA: {
                linesDeleted = db.delete(ProgressContract.UserData.TABLE_NAME,selection,selectionArgs);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown Uri " + uri);
            }
        }
        if(linesDeleted>0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return linesDeleted;
    }
}