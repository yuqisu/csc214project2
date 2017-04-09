package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuqisu on 3/27/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String TABLE_NAME = "social_table";
    public DatabaseHelper(Context context) {
        super(context, Schema.DATABASE_NAME, null, Schema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Schema.User.USERS_NAME
                + "(_id integer primary key autoincrement, "
                +Schema.User.Info.EMAIL + ", "
                +Schema.User.Info.PASSWORD + ", "
                +Schema.User.Info.FULL_NAME + ", "
                +Schema.User.Info.BIRTHDAY + ", "
                +Schema.User.Info.PICTURE_PATH + ", "
                +Schema.User.Info.HOME_TOWN + ", "
                +Schema.User.Info.BIO + ")"
        );

        db.execSQL("CREATE TABLE " + Schema.Feed.FEEDS +
                "(_id integer primary key autoincrement, "
                +Schema.Feed.Info.EMAIL + " , "
                +Schema.Feed.Info.CONTENT + " , "
                +Schema.Feed.Info.FEED_PICTURE_PATH + " , "
                +Schema.Feed.Info.POST_DATE + " ) "
        );

        db.execSQL("CREATE TABLE " + Schema.Favorite.FAVORITES+
                "(_id integer primary key autoincrement, "
                +Schema.Favorite.Info.EMAIL + " , "
                +Schema.Favorite.Info.ID + " , "
                +Schema.Favorite.Info.FAVORITE + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
