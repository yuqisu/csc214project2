package project2.csc214.socialnetwork;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import UserList.UserlIst;
import feed.feedfragament;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        UserlIst userlIst = new UserlIst();
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.user_fragment,userlIst)
                .commit();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean handled;
        switch (item.getItemId()){
            case R.id.users:
                handled = true;
                Toast.makeText(this,"already at user page",Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                handled = true;
                break;
            case R.id.logout:
                Intent intent = new Intent(User.this, Login_createAccount.class);
                startActivity(intent);
                handled = true;
                break;

            default:
                handled = super.onOptionsItemSelected(item);
                break;
        }
        return handled;
    }

}
