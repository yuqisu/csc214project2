package login_register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.MyDatabase;
import project2.csc214.socialnetwork.MainActivity;
import project2.csc214.socialnetwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    EditText userNameBox;
    EditText loginPassBox;
    Button lButton;
    static MyDatabase mcollection;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mcollection = MyDatabase.get(getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameBox = (EditText)view.findViewById(R.id.login_email);

        loginPassBox = (EditText)view.findViewById(R.id.login_password);

        lButton = (Button)view.findViewById(R.id.login_button);
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userNameBox.getText().toString();
                final String password = loginPassBox.getText().toString();
                if (mcollection.checkEmail(email)){
                    if (mcollection.getUser(email,password)!=null){
                       mcollection.setCurrentUser(mcollection.getUser(email,password));
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    }else{
                        System.out.println(password+" ???");
                        Toast.makeText(getActivity(),"check your password",Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(getActivity(),"check your email",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
