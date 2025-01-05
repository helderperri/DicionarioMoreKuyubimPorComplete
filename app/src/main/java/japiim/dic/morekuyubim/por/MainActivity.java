package japiim.dic.morekuyubim.por;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import japiim.dic.morekuyubim.por.R;

public class MainActivity extends AppCompatActivity {

    public static int nightMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nightMode =0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            if(this.getResources().getConfiguration().isNightModeActive() == true){

                nightMode =1;
            }else{

                nightMode =0;


            }


        }

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //disabled two lines below for NoActionBar app
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_source_lang, R.id.navigation_target_lang, R.id.navigation_alphabetic).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        //disabled two lines below for NoActionBar app
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        //disable saveInstanceState for navigationController
        NavigationUI.setupWithNavController(navView, navController,false);

        //disabled two lines below for NoActionBar app
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    public void toIntroduction(View view) {

        Log.d("Main", " GNM-JPP toIntroduction");
    }
    protected void onStart() {
        super.onStart();
        Log.i("GNM-JPP", "onStart: MAIN ACTIVITY");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("GNM-JPP", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("GNM-JPP", "onPause: ");
    }

    protected void onStop() {
        super.onStop();
        Log.i("GNM-JPP", "onStop: ");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("GNM-JPP", "onRestart: ");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("GNM-JPP", "onDestroy: ");
    }

}