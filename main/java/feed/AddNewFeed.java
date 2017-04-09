package feed;

import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

import database.MyDatabase;
import model.Feed;
import model.User;
import project2.csc214.socialnetwork.R;


public class AddNewFeed extends Fragment {


    private AddNewFeedListner mListener;

    private EditText editText;
    private Button okButton;
    private Button cancelButton;
    private static User currentU;
    private MyDatabase mCollection;
    public AddNewFeed() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCollection =MyDatabase.get(getActivity().getApplicationContext());
        currentU = mCollection.getCurrentUser();
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_new_feed, container, false);

        editText = (EditText)view.findViewById(R.id.feed_text);
        okButton = (Button)view.findViewById(R.id.buttonOK_feed);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feed feed = new Feed();
                feed.setEmail(currentU.getEmail());
                feed.setCONTENT(editText.getText().toString());
                feed.setPOST_DATE(String.valueOf(Calendar.DAY_OF_MONTH)+"-" +Calendar.MONTH);
                mCollection.insertFeed(feed);
                mListener.postNewFeed();
            }
        });
        cancelButton = (Button)view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.cancelPost();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddNewFeedListner) {
            mListener = (AddNewFeedListner) context;
        }
    }



    public interface AddNewFeedListner {
        public void postNewFeed();
        public void cancelPost();
    }
}
