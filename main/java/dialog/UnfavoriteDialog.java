package dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import database.MyDatabase;
import model.User;
import project2.csc214.socialnetwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnfavoriteDialog extends DialogFragment {
    private static final String KEYEMAIL = "email";
    private static MyDatabase mDatabase;
    private static User currentUser;

    public UnfavoriteDialog() {
        // Required empty public constructor
    }



    public static UnfavoriteDialog newInstance(User user){

        Bundle args = new Bundle();
        args.putString(KEYEMAIL,user.getEmail());
        UnfavoriteDialog dialogFragment  = new UnfavoriteDialog();
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
                .setTitle("Unfavorite?")
                .setView(view)
                .setPositiveButton("Yes! I want to unfollow this user", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("ccccc"+email);
                        System.out.println("ccccc"+currentUser.getEmail());
                        mDatabase.updateFavorite(currentUser.getEmail(),email,"false");
                    }
                })
                .setNegativeButton("Continue following ",null)
                .create();
    }
}
