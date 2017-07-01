package com.xpf.me.sms.routine.infection;

import java.io.File;

/**
 * Created by xgo on 10/30/15.
 */
public abstract class Node {

    public Node(File file, boolean r) {
        if (r)
            infected(file);
        else
            recovered(file);
    }

    public abstract void infected(File file);

    public abstract void recovered(File file);
}
