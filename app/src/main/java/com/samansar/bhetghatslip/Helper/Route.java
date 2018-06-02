package com.samansar.bhetghatslip.Helper;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Route {

    public static void navigateActivity(Context context, Boolean finish, Class className) {
        context.startActivity(new Intent(context, className));
        if (finish)
            ((AppCompatActivity) context).finish();
    }

    public static void attachFragment(AppCompatActivity context, int frameContainer, Fragment fragment, String fragmentTag, boolean addToBackStack) {

        if (addToBackStack) {
            context.getSupportFragmentManager().beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(frameContainer, fragment, fragmentTag)
                    .addToBackStack(null)
                    .commit();
        } else {
            context.getSupportFragmentManager().beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(frameContainer, fragment, fragmentTag)
                    .commit();
        }
    }

}

