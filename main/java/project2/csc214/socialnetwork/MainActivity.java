package project2.csc214.socialnetwork;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import UserList.UserlIst;
import camera.cameraFragament;
import dialog.dialogFragment;
import feed.AddNewFeed;
import feed.feedfragament;


public class MainActivity extends AppCompatActivity implements AddNewFeed.AddNewFeedListner
 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddNewFeed addNewFeed = new AddNewFeed();
        feedfragament feedfragament = new feedfragament();
        cameraFragament camera = new cameraFragament();
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_activity,addNewFeed)
                .add(R.id.main_activity_list,feedfragament)
                .add(R.id.camera,camera)
                .commit();
//        getActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intent2 = new Intent(MainActivity.this,User.class);
                startActivity(intent2);
                break;
            case R.id.profile:
                handled = true;
                break;
            case R.id.logout:
                Intent intent = new Intent(MainActivity.this, Login_createAccount.class);
                startActivity(intent);
                handled = true;
                break;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                handled = true;
            default:
                handled = super.onOptionsItemSelected(item);
                break;
        }
        return handled;
    }

    @Override
    public void postNewFeed() {
        feedfragament feedfragament  = new feedfragament();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.main_activity_list,feedfragament)
                .commit();

    }


    @Override
    public void cancelPost() {

    }

     @Override
     public void onBackPressed(){
         feedfragament feedfragament  = new feedfragament();
         FragmentManager manager = getSupportFragmentManager();
         manager.beginTransaction()
                 .replace(R.id.main_activity_list,feedfragament)
                 .commit();
     }


 }
