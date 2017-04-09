package dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;


import database.MyDatabase;
import model.User;
import project2.csc214.socialnetwork.R;


public class dialogFragment extends DialogFragment{
    private static final String KEYEMAIL = "email";
    private static MyDatabase mDatabase;
    private static User currentUser;


    public dialogFragment() {
        // Required empty public constructor
    }
    public static dialogFragment newInstance(User user){

        Bundle args = new Bundle();
        args.putString(KEYEMAIL,user.getEmail());
        dialogFragment dialogFragment  = new dialogFragment();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDatabase = MyDatabase.get(getActivity().getApplicationContext());
        currentUser = mDatabase.getCurrentUser();
//        Log.i(TAG,"onCreateDialog() called");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        Bundle bundle  = getArguments();
        final String email = bundle.getString(KEYEMAIL);
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.title)
                .setView(view)
                .setPositiveButton("Yes! I want to follow this user", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("ccccc"+email);
                        mDatabase.insertFavorite(currentUser.getEmail(),email,"true");
                    }
                })
                .setNegativeButton("Maybe next time ",null)
                .create();
    }




}
