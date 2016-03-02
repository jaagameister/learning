package in.jaaga.learning.android;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import in.jaaga.learning.DB;
import in.jaaga.learning.Database.ProgressContract;

public class AndroidDB extends DB {

    public void addName(String name) {
        name = name.toLowerCase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProgressContract.UserDetails.COLUMN_USER_NAME,name);
        contentValues.put(ProgressContract.UserDetails.COLUMN_PASSWORD,"qwerty");
        contentValues.put(ProgressContract.UserDetails.COLUMN_CREATION_DATE,"Feb,29");
        contentValues.put(ProgressContract.UserDetails.COLUMN_POINTS,100);
        Uri uri = ProgressContract.UserDetails.CONTENT_URI;
        S.getActivity().getContentResolver().insert(uri,contentValues);
    }

    public boolean containsName(String name) {
        name = name.toLowerCase();
        Cursor cursor;
        Uri uri = ProgressContract.UserDetails.buildUriWithUserName(name);
        String selection = ProgressContract.UserDetails.userNameSelection;
        cursor = S.getActivity().getContentResolver().query(uri,null,selection,new String[]{name},null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public int getUidFromUserName(String name) {
        name = name.toLowerCase();
        Uri uri = ProgressContract.UserDetails.buildUriWithUserName(name);
        int uid = -1;
        String selection = ProgressContract.UserDetails.userNameSelection;
        Cursor cursor = S.getActivity().getContentResolver().query(uri,null,selection,new String[]{name},null);
        if (cursor != null && cursor.moveToFirst()) {
            uid = cursor.getInt(cursor.getColumnIndex(ProgressContract.UserDetails._ID));
            Log.v("yes UID is ", Integer.toString(uid));
        }
        cursor.close();
        return uid;
    }

    public boolean skillAttemptedBefore(String name,String skill) {
        name = name.toLowerCase();
        skill = skill.toLowerCase();
        int user_id = getUidFromUserName(name);
        Cursor cursor;
        //Uri uri = ProgressContract.UserData.buildUriWithUidAndSkill(user_id,skill);
        Uri uri = ProgressContract.UserData.CONTENT_URI;
        String selection = ProgressContract.UserData.userIdAndSkillsAttemptedSelection;

        cursor = S.getActivity().getContentResolver().query(uri,null,selection,new String[]
                {Integer.toString(user_id),skill},null);
        //Log.v("this is the skill attempted",cursor.getString(cursorProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED));
        if (cursor != null && cursor.moveToFirst()) {
            Log.v("yes skill attempted",cursor.getString(cursor.getColumnIndex
                    (ProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED)));
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public int getPointsFromDatabase(String name,String skill) {
        name = name.toLowerCase();
        int pointsScored = -2;
        skill = skill.toLowerCase();
        int user_id = getUidFromUserName(name);
        Uri uri = ProgressContract.UserData.buildUriWithUidAndSkill(user_id,skill);
        String selection = ProgressContract.UserData.userIdAndSkillsAttemptedSelection;
        Cursor cursor = S.getActivity().getContentResolver().query(uri,null,selection,
                new String[]{Integer.toString(user_id),skill},null);
        if (cursor != null && cursor.moveToFirst()) {
            pointsScored = cursor.getInt(cursor.getColumnIndex(ProgressContract.UserData.COLUMN_POINTS_SCORED));
            Log.v("yes point Scored ", Integer.toString(pointsScored));
        }
        cursor.close();
        return pointsScored;
    }

    public void insertSkillAttemptedInDatabase(String name, String skill,int points) {
        name = name.toLowerCase();
        skill = skill.toLowerCase();
        int user_id = getUidFromUserName(name);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProgressContract.UserData.COLUMN_USER_ID,user_id);
        contentValues.put(ProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED,skill);
        contentValues.put(ProgressContract.UserData.COLUMN_POINTS_SCORED,points);
        S.getActivity().getContentResolver().insert(ProgressContract.UserData.CONTENT_URI,contentValues);
        addPointsToDetails(name);
    }

    public void updatePointsScored(String name, String skill, int points) {
        name = name.toLowerCase();
        skill = skill.toLowerCase();
        int user_id = getUidFromUserName(name);
        int initialPoints = getPointsFromDatabase(name,skill);
        int finalPoints = initialPoints+points;
        String selection = ProgressContract.UserData.userIdAndSkillsAttemptedSelection;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProgressContract.UserData.COLUMN_POINTS_SCORED,Integer.toString(finalPoints));
        int linesUpdated = S.getActivity().getContentResolver().update(ProgressContract.UserData.CONTENT_URI,contentValues,
                selection,new String[]{Integer.toString(user_id),skill});
        addPointsToDetails(name);
    }

    public String getData(String name,String skill) {
        name = name.toLowerCase();
        int uid = -1;
        skill  = skill.toLowerCase();
        String skillAttempted = "";
        int pointsScored = -1;
        int user_id = getUidFromUserName(name);
        Uri uri = ProgressContract.UserData.buildUriWithUidAndSkill(user_id,skill);
        String selection = ProgressContract.UserData.userIdAndSkillsAttemptedSelection;
        Cursor cursor = S.getActivity().getContentResolver().query(uri,null,selection,
                new String[]{Integer.toString(user_id),skill},null);
        if (cursor != null && cursor.moveToFirst()) {
            uid = cursor.getInt(cursor.getColumnIndex(ProgressContract.UserData.COLUMN_USER_ID));
            skillAttempted = cursor.getString(cursor.getColumnIndex(ProgressContract.UserData.COLUMN_SKILLS_ATTEMPTED));
            pointsScored = cursor.getInt(cursor.getColumnIndex(ProgressContract.UserData.COLUMN_POINTS_SCORED));
        }
        cursor.close();
        return "Congratulations, " +name+". Your database ID is " + Integer.toString(uid)+ " .You have practiced " + skillAttempted +
                " and you have " + Integer.toString(pointsScored) + " points in this skill ."+ " Your lifetime points are "
                +Integer.toString(getPointsFromDetails(name))+ " .Some more practice then you are a Yoda!";
    }
    public boolean addPointsToDetails(String name) {
        Cursor cursor;
        int sum = 0;
        name = name.toLowerCase();
        int user_id = getUidFromUserName(name);
        Uri uri = ProgressContract.UserData.CONTENT_URI;
        String selection = ProgressContract.UserData.userIdSelection;
        cursor = S.getActivity().getContentResolver().query(uri,null,selection,new String[]{Integer.toString(user_id)},null);
        while (cursor.moveToNext()) {
            int skillPoints =cursor.getInt(cursor.getColumnIndex(ProgressContract.UserData.COLUMN_POINTS_SCORED));
            sum  = sum + skillPoints;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProgressContract.UserDetails.COLUMN_POINTS,sum);
        Uri uriDetail = ProgressContract.UserDetails.CONTENT_URI;
        String selectionDetail = ProgressContract.UserDetails.userNameSelection;
        int linesUpdated = S.getActivity().getContentResolver().update(uriDetail,contentValues,selectionDetail,new String[] {name});
        if (linesUpdated > 0) {
            return  true;
        }
        else return false;
    }
    public int getPointsFromDetails(String name) {
        Cursor cursor;
        name = name.toLowerCase();
        int points = 0;
        Uri uri = ProgressContract.UserDetails.buildUriWithUserName(name);
        String selection = ProgressContract.UserDetails.userNameSelection;
        cursor = S.getActivity().getContentResolver().query(uri,null,selection,new String[]{name},null);
        if (cursor != null && cursor.moveToFirst()) {
            points = cursor.getInt(cursor.getColumnIndex(ProgressContract.UserDetails.COLUMN_POINTS));
        }
        return points;
    }
}