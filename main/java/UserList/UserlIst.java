package UserList;


import android.graphics.Color;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import database.MyDatabase;
import dialog.UnfavoriteDialog;
import dialog.dialogFragment;
import model.User;
import project2.csc214.socialnetwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserlIst extends Fragment {
    private static final String TAG = "feedRecyclerView";
    private MyDatabase mCollection;
    private RecyclerView mRecyclerView;
    private MyAdaper myAdaper;
    private User currentUser;

    public UserlIst() {
        // Required empty public constructor
    }
//    public static UserlIst newInstance(){
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCollection =MyDatabase.get(getActivity().getApplicationContext());
        currentUser =mCollection.getCurrentUser();
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_userl_ist, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.user_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateUI();
        return view;
    }

    public void updateUI() {
        List<User> users = mCollection.getUserList();
        Log.d(TAG,users.toString());
        if (myAdaper==null){
            myAdaper = new MyAdaper(users);
            mRecyclerView.setAdapter(myAdaper);
        }
        else {

            myAdaper.setUsers(users);
        }
    }

    private class MyAdaper extends RecyclerView.Adapter<MyAdaper.MyViewHolder> {
        private static final String TAG = "adapter";
        private List<User> users;

        public MyAdaper(List<User> users){
            this.users = users;
        }
        @Override
        public MyAdaper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG,"onCreateViewHolder() called");
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view =inflater.inflate(R.layout.userview,parent,false);
            return new MyAdaper.MyViewHolder(view);
        }

        public void setUsers(List<User> user){
            users = user;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(MyAdaper.MyViewHolder holder, int position) {
            Log.d(TAG,"onBindViewHolder() called");
            User user = users.get(position);
            if (mCollection.checkFavorite(currentUser.getEmail(),user.getEmail())){
                holder.itemView.setBackgroundColor(Color.parseColor("#d2def2"));
            }
            holder.bind(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }



        class MyViewHolder extends RecyclerView.ViewHolder{
            private final ImageButton mprofile;
            private final TextView mUserName;
            private final TextView mbirhday;
            private final TextView mhometwon;
            private final TextView mbio;
            private User users;


            public MyViewHolder(final View itemView) {
                super(itemView);
                mprofile =(ImageButton)itemView.findViewById(R.id.user_list_profile) ;
                mUserName = (TextView)itemView.findViewById(R.id.user_list_name);
                mbirhday = (TextView)itemView.findViewById(R.id.user_list_birthday);
                mhometwon = (TextView)itemView.findViewById(R.id.user_list_hometown);
                mbio = (TextView)itemView.findViewById(R.id.user_list_bio);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity)v.getContext();
                        FragmentManager manager = activity.getSupportFragmentManager();

                        if (mCollection.checkFavorite(currentUser.getEmail(),users.getEmail())){
                            UnfavoriteDialog dialog = UnfavoriteDialog.newInstance(users);
                            dialog.show(manager,"confirm");

                        }else{
                            dialogFragment dialog = dialogFragment.newInstance(users);
                            dialog.show(manager,"confirm");
                        }


                    }
                });

            }



            public void bind(User user){
                users = user;
                mUserName.setText(user.getFullname());
//                mprofile.setImageDrawable();
                mbirhday.setText(user.getBirthday());
                mhometwon.setText(user.getHometown());
                mbio.setText(user.getBio());


//                mprofile.setImageDrawable(); need to implement after the camera function

            }
        }
    }


}
