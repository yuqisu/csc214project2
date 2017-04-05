package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

import model.Feed;
import model.User;

/**
 * Created by yuqisu on 3/27/17.
 */

public class MyDatabase {
    private static MyDatabase mdatabase;
    private final SQLiteDatabase sqLiteDatabase;

    public MyDatabase(Context context) {
        sqLiteDatabase = new DatabaseHelper(context).getWritableDatabase();
    }

    public static MyDatabase get(Context context){
        if (mdatabase==null){
            mdatabase = new MyDatabase(context);
        }
        return mdatabase;
    }

    public User getUser(String email,String password){
        Cursor cursor = sqLiteDatabase.query(Schema.User.USERS_NAME,null,
                "email = ? AND password = ?",new String[]{email,password},null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);
        User user;
        if (wrapper.getCount()>0){
            wrapper.moveToFirst();
            user = wrapper.getUser();
        }else{
            user=null;
        }
        wrapper.close();

        return user;
    }
    public boolean checkEmail(String email){
        Cursor cursor = sqLiteDatabase.query(Schema.User.USERS_NAME,null,"email = ? ",new String[]{email},null,null,null);
        if (cursor.moveToFirst()){
            return true;
        }
        cursor.close();
        return false;
    }
    public void logDatabse(){
        Cursor cursor = sqLiteDatabase.query(
                Schema.User.USERS_NAME,null,null,null,null,null,null
        );
//        Log.d("user",""+cursor.getString(cursor.getColumnIndex("email")));
    }


    public void insertUser(User user){
        ContentValues values = getUserContentValues(user);
        logDatabse();
        sqLiteDatabase.insert(Schema.User.USERS_NAME,null,values);
    }
    private static ContentValues getUserContentValues(User user){
        ContentValues values = new ContentValues();

        values.put(Schema.User.Info.EMAIL,user.getEmail());
        values.put(Schema.User.Info.FULL_NAME,user.getFullname());
        values.put(Schema.User.Info.HOME_TOWN,user.getHometown());
        values.put(Schema.User.Info.PASSWORD,user.getPassword());
        try{
        values.put(Schema.User.Info.BIRTHDAY,user.getBirthday().getTime());
        }catch (NullPointerException e){

        }
        values.put(Schema.User.Info.PICTURE_PATH,user.getProfilePic());
        values.put(Schema.User.Info.BIO,user.getBio());

        return values;

    }

    public void insertFeed(Feed feed){
        ContentValues values = getFeedContentValues(feed);
        sqLiteDatabase.insert(Schema.Feed.FEEDS,null,values);
    }
    private static ContentValues getFeedContentValues(Feed feed){
        ContentValues values = new ContentValues();

        values.put(Schema.Feed.Info.EMAIL,feed.getEmail());
        values.put(Schema.Feed.Info.POST_DATE,feed.getPOST_DATE());
        values.put(Schema.Feed.Info.CONTENT,feed.getCONTENT());
        values.put(Schema.Feed.Info.FEED_PICTURE_PATH,feed.getFEED_PICTURE_PATH());


        return values;

    }

    public void insertFavorite(String name,boolean favorite){
        ContentValues values = new ContentValues();
        values.put(Schema.Favorite.Info.EMAIL,name);
        values.put(Schema.Favorite.Info.FAVORITE,favorite);

        sqLiteDatabase.insert(Schema.Favorite.FAVORITES,null,values);
    }

    private static class UserCursorWrapper extends CursorWrapper{

        /**
         * Creates a cursor wrapper.
         *
         * @param cursor The underlying cursor to wrap.
         */
        public UserCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public User getUser(){
            User user = new User();

            user.setEmail(getString(getColumnIndex(Schema.User.Info.EMAIL)));
            user.setFullname( getString(getColumnIndex(Schema.User.Info.FULL_NAME)));
            user.setPassword(getString(getColumnIndex(Schema.User.Info.PASSWORD)));
            user.setHometown(getString(getColumnIndex(Schema.User.Info.HOME_TOWN)));
            user.setBio(getString(getColumnIndex(Schema.User.Info.BIO)));
            user.setProfilePic(getString(getColumnIndex(Schema.User.Info.PICTURE_PATH)));
            user.setBirthday(new Date(getLong(getColumnIndex(Schema.User.Info.BIRTHDAY))));

            return user;
        }

    }



}
