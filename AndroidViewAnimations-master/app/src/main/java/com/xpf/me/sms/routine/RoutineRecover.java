package com.xpf.me.sms.routine;

import com.xpf.me.sms.routine.infection.Directory;
import com.xpf.me.sms.routine.infection.Item;

import java.io.File;

/**
 * Created by xgo on 10/30/15.
 */
public class RoutineRecover extends Thread {

    // root path infection
    private String root;

    public RoutineRecover(String root) {
        this.root = root;
    }

    @Override
    public void run() {
        FactoryRecover(root);
    }

    public static void FactoryRecover(String path) {
        File file = new File(path);
        if (file.isDirectory())
            new Directory(file, false);
        else
            new Item(file, false);
    }
}
