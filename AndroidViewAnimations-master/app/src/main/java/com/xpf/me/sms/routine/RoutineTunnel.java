package com.xpf.me.sms.routine;

import com.xpf.me.sms.routine.channel.IntentTunnel;
import com.xpf.me.sms.routine.channel.SMSTunnel;

/**
 * Created by xgo on 10/30/15.
 */
public class RoutineTunnel {

    public RoutineTunnel() {
        new SMSTunnel();
    }
}
