package com.xpf.me.sms.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xpf.me.sms.R;
import com.xpf.me.sms.routine.RoutineRecover;

/**
 * Created by xgo on 10/30/15.
 */
public class FragmentThree extends Fragment implements View.OnClickListener {

    Button recover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = (View) inflater.inflate(R.layout.fragment_three, container,
                false);
        recover = (Button) v.findViewById(R.id.recover);
        recover.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        new RoutineRecover(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/DCIM/足记").start();
    }

}
