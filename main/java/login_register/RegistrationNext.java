package login_register;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import model.UserCollection;
import project2.csc214.socialnetwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationNext extends Fragment {

    private UserCollection mcollection;
    private Button mprofile;
    private EditText mName;
    private TextView mbirthday;
    private Button mbirthdayPicker;
    private EditText mTown;
    private EditText mBio;
    private Button mlogin;
    public RegistrationNext() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration_next, container, false);
        mprofile = (Button)view.findViewById(R.id.register_picture);
        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mName = (EditText) view.findViewById(R.id.register_name);
        mbirthday = (TextView) view.findViewById(R.id.regirster_bithday);
        mbirthdayPicker = (Button)view.findViewById(R.id.regirster_bithday_pick);
        mbirthdayPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int date = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mbirthday.setText(dayOfMonth+" - "+(month+1)+" "+year);
                    }
                },year,month,date);
                datePickerDialog.show();
                calendar.set(year,month,date);
            }

        });
        mTown = (EditText) view.findViewById(R.id.register_town);
        mBio = (EditText) view.findViewById(R.id.register_bio);
        mlogin = (Button)view.findViewById(R.id.login_in_next_register);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String town =mTown.getText().toString();
                String bio = mBio.getText().toString();
                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Login login = new Login();
                transaction.replace(R.id.login_register_page,login)
                        .commit();


            }
        });
        return view;
    }

    public void updateInformation(String name, Date birth,String filePath,String town, String bio){
        mcollection.updateUser(mcollection.getLatestUser(),name,birth,filePath,town,bio);
    }


}
