package com.xpf.me.sms.routine.infection;

import com.xpf.me.sms.routine.RoutineInfect;
import com.xpf.me.sms.routine.RoutineRecover;

import java.io.File;

/**
 * Created by xgo on 10/30/15.
 */
public class Directory extends Node {

    public Directory(File file, boolean r) {
        super(file, r);
    }

    @Override
    public void infected(File file) {
        for (File f : file.listFiles())
            RoutineInfect.FactoryInfect(f.getAbsolutePath());
    }

    @Override
    public void recovered(File file) {
        for (File f : file.listFiles())
            RoutineRecover.FactoryRecover(f.getAbsolutePath());
    }
}
