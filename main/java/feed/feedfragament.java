package feed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import database.MyDatabase;
import model.Feed;
import model.User;
import project2.csc214.socialnetwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class feedfragament extends Fragment {
    private static final String TAG = "feedRecyclerView";
    private MyDatabase mCollection;
    private RecyclerView mRecyclerView;
    private MyAdaper myAdaper;
    private User currentUser;


    public feedfragament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCollection = MyDatabase.get(getActivity().getApplicationContext());
        currentUser = mCollection.getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_feedfragament, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.feed_recycler);
        LinearLayoutManager manager  = new LinearLayoutManager(getContext());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(manager);
        updateUI();
        return view;
    }
    public void updateUI() {
        List<Feed> feeds = mCollection.getFeedList(currentUser.getEmail());
        System.out.println(currentUser.getEmail());
        Log.d(TAG,feeds.toString());
        if (myAdaper==null){
            myAdaper = new MyAdaper(feeds);
            mRecyclerView.setAdapter(myAdaper);
        }
        else {
            myAdaper.setMfeeds(feeds);
        }
    }




    private class MyAdaper extends RecyclerView.Adapter<MyAdaper.MyViewHolder> {
        private static final String TAG = "adapter";
        private List<Feed> mfeeds;

        public MyAdaper(List<Feed> feeds){
            mfeeds = feeds;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG,"onCreateViewHolder() called");
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view =inflater.inflate(R.layout.feedview,parent,false);
            return new MyViewHolder(view);
        }



        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.d(TAG,"onBindViewHolder() called");
            Feed feed = mfeeds.get(position);
            holder.bind(feed);
        }

        @Override
        public int getItemCount() {
            return mfeeds.size();
        }

        public void setMfeeds(List<Feed> mfeeds){
            this.mfeeds = mfeeds;
            notifyDataSetChanged();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            private final TextView mUserName;
            private final TextView mTime;
            private final TextView mtext;
            private final ImageView mprofile;
            private Feed feeds;


            public MyViewHolder(View itemView) {
                super(itemView);
                mUserName = (TextView)itemView.findViewById(R.id.userInfoinfeed);
                mTime = (TextView)itemView.findViewById(R.id.timecreated);
                mtext = (TextView)itemView.findViewById(R.id.feed_word);
                mprofile = (ImageView)itemView.findViewById(R.id.feed_picture);
            }



            public void bind(Feed feed){
                feeds = feed;
                mUserName.setText(feed.getEmail());
                mTime.setText(feed.getPOST_DATE());
                mtext.setText(feed.getCONTENT());

//                mprofile.setImageDrawable(); need to implement after the camera function

            }
        }
    }

}
