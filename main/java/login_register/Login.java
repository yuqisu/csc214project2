package login_register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;

import model.UserCollection;
import project2.csc214.socialnetwork.MainActivity;
import project2.csc214.socialnetwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    EditText userNameBox;
    EditText loginPassBox;
    Button lButton;
    UserCollection mcollection;
    String email;
    String password;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mcollection = UserCollection.get(getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameBox = (EditText)view.findViewById(R.id.userName);
          email = userNameBox.getText().toString();

        loginPassBox = (EditText)view.findViewById(R.id.login_password);
           password = loginPassBox.getText().toString();
        lButton = (Button)view.findViewById(R.id.login_button);
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOGINemail",email);
                if (mcollection.checkEmail(email)){
//                    if (checkPassword(email,password)){
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
//                    }else{
//                        Toast.makeText(getActivity(),"check your password",Toast.LENGTH_LONG).show();
//                    }
                }else{
                    Log.i("LOGINemail",email);
                    Toast.makeText(getActivity(),"check your email",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    public boolean checkPassword(String email,String password){
        return mcollection.checkPassword(email,password);
    }

}
