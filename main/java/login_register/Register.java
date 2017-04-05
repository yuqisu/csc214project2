package login_register;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.User;
import model.UserCollection;
import project2.csc214.socialnetwork.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    EditText emailbox;
    EditText passwordbox;
    Button rButton;
    Button logtransButton;
    public static UserCollection mcollection;
    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mcollection = UserCollection.get(getActivity());

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        emailbox = (EditText)view.findViewById(R.id.register_email);
        passwordbox = (EditText)view.findViewById(R.id.register_password);
        rButton = (Button)view.findViewById(R.id.Button_register);
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Log.i("test","being clicked on");
                User user = new User();
                String email = emailbox.getText().toString();
                Log.i("emailbox",emailbox.getText().toString());
                String password = passwordbox.getText().toString();
                Log.i("password",passwordbox.getText().toString());
                if (email==null){
                    Toast.makeText(getActivity(),"please input your email",Toast.LENGTH_SHORT).show();
                }else if (password.length()<6){
                    Toast.makeText(getActivity(),"please choose a longer password",Toast.LENGTH_SHORT).show();
                }else if(mcollection.checkEmail(email)){
                    Toast.makeText(getActivity(),"this email is already been created!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),"new account created!",Toast.LENGTH_SHORT).show();
                    user.setEmail(email);
                    user.setPassword(password);
                    mcollection.addUser(user);
                    FragmentManager manager =getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RegistrationNext next = new RegistrationNext();
                    transaction.replace(R.id.login_register_page,next)
                            .commit();


                }


            }
        });
        logtransButton = (Button)view.findViewById(R.id.Button_login_transfer);
        logtransButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Login login = new Login();
                transaction.replace(R.id.login_register_page,login);
                transaction.commit();

            }
        });

        return view;
    }

}
