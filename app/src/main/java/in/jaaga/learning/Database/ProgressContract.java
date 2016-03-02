package in.jaaga.learning.Database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import java.net.URI;
import in.jaaga.learning.android.S;

/**
 * Created by root on 27/2/16.
 */
public class ProgressContract {

    public static final String CONTENT_AUTHORITY = "in.jaaga.learning";

    //this will be used to contact content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DETAILS = "details";

    public static final String PATH_DATA = "data";

    public static final class UserDetails implements BaseColumns {

        public static final String TABLE_NAME = "details";
        public static final String COLUMN_USER_NAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CREATION_DATE = "creation_date";
        public static final String COLUMN_POINTS = "points";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DETAILS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DETAILS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DETAILS;

        public static String getUserNameFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildDetailsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static String userNameSelection = COLUMN_USER_NAME + " = ?";

        public static Uri buildUriWithUserName(String name) {
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_USER_NAME,name).build();
        }
    }

    public static final class UserData implements BaseColumns {

        public static final String TABLE_NAME = "data";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_SKILLS_ATTEMPTED = "skills_attempted";
        public static final String COLUMN_POINTS_SCORED = "amount_solved";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DATA).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATA;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATA;

        public static final Uri buildDataUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String userIdAndSkillsAttemptedSelection = COLUMN_USER_ID + " = ? AND "
                + COLUMN_SKILLS_ATTEMPTED + " = ? ";

        public static final String userIdSelection = COLUMN_USER_ID + " = ?";

        public static final int getUserIdFromUri(Uri uri) {
            int user_id = Integer.parseInt(uri.getQueryParameter(COLUMN_USER_ID));
            return user_id;
        }
        public static final String getSkillAttemptedFromUri(Uri uri) {
            String SkillAttempted = uri.getQueryParameter(COLUMN_SKILLS_ATTEMPTED);
            return  SkillAttempted;
        }
        public static final Uri buildUriWithUidAndSkill(int uid, String skillAttempted)  {
            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_USER_ID,String.valueOf(uid)).
                    appendQueryParameter(COLUMN_SKILLS_ATTEMPTED,skillAttempted).build();
        }

    }
}