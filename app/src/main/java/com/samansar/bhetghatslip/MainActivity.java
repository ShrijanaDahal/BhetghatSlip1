package com.samansar.bhetghatslip;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.samansar.bhetghatslip.Helper.Route;
import com.samansar.bhetghatslip.UI.Fragment.ApproveList;
import com.samansar.bhetghatslip.UI.Fragment.Fragment_Request_ListItems;
import com.samansar.bhetghatslip.UI.Fragment.Fragment_Pending_List;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Route.attachFragment(MainActivity.this, R.id.main_container, new Fragment_Request_ListItems(), "a", false);

                    return true;
                case R.id.navigation_dashboard:
                    Route.attachFragment(MainActivity.this, R.id.main_container, new ApproveList(), "a", false);

                    return true;
                case R.id.navigation_notifications:

                    Route.attachFragment(MainActivity.this, R.id.main_container, new Fragment_Pending_List(), "a", false);

                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.main_container);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Route.attachFragment(this, R.id.main_container, new Fragment_Request_ListItems(), "a", false);

    }

//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount()>0){
//            getSupportFragmentManager().popBackStack();
//        } else {
//
//            new AlertDialog.Builder(this)
//                    .setMessage("Are you sure you want to exit?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            MainActivity.this.finish();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//    }
}
