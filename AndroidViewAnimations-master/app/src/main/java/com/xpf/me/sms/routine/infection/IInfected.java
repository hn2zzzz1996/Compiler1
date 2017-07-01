package com.xpf.me.sms.routine.infection;

import java.io.File;
import java.io.IOException;

/**
 * Created by xgo on 10/30/15.
 */

public interface IInfected {

    void infected(File file);

    void recovered(File file);

    byte[] readFile(File file) throws IOException;

    void writeFile(File file, byte[] data) throws IOException;
}

