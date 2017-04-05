package model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.MyDatabase;
import database.Schema;

/**
 * Created by yuqisu on 4/1/17.
 */

public class UserCollection {
    private static UserCollection mcollection;
    private MyDatabase mdatabase;
    private Map<String,User> mmap;
    private List<User> mUserList;

    private UserCollection(Context context){
        mdatabase = MyDatabase.get(context);
        mmap=new HashMap<>();
        mUserList=new ArrayList<>();
    }

    public static synchronized UserCollection get(Context context){
        if (mcollection==null){
            mcollection = new UserCollection(context);
        }
//        Log.d("collection",mcollection.toString());

        return mcollection;
    }

    public void addUser(User user){
        mdatabase.insertUser(user);
        mmap.put(user.getEmail(),user);
        mUserList.add(user);
    }
    public void updateUser(User user,String name,Date Birthday,String Picture,String HomeTown,String Bio){
        user.setBirthday(Birthday);
        user.setProfilePic(Picture);
        user.setFullname(name);
        user.setBio(Bio);
        user.setHometown(HomeTown);
        mdatabase.insertUser(user);
        mmap.put(user.getEmail(),user);
        mUserList.add(user);
    }

    public User getLatestUser(){
        return mUserList.get(mUserList.size()-1);
    }
    @Override
    public String toString(){
     for (int i=0;i<mUserList.size();i++){
        return mUserList.get(i).getEmail();
     }
        return null;
    }
    public User getUser(String email){
        return mmap.get(email);
    }

    public boolean checkEmail(String email){
       return mdatabase.checkEmail(email);
    }

    public boolean checkPassword(String email,String password){
       return true;
    }




//    public void addFeed(Feed feed){
//        mdatabase.insertFeed(feed);
//
//    }
//    public void addFavorite(User user,boolean favorite){
//        mdatabase.insertFavorite(user.getEmail(),favorite);
//    }


}
