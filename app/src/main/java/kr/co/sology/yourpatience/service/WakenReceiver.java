package kr.co.sology.yourpatience.service;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import java.util.Date;

import kr.co.sology.yourpatience.MainActivity;
import kr.co.sology.yourpatience.TimeUtil;

public class WakenReceiver extends BroadcastReceiver {

    String strLogId = "WakenReceiver";
    long   start = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isLocked = false;
        String strAction = intent.getAction();
        KeyguardManager km = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        assert strAction != null;
        if (strAction.contains(Intent.ACTION_SCREEN_OFF) || strAction.contains(Intent.ACTION_SCREEN_ON)) {
            assert km != null;
            if (km.inKeyguardRestrictedInputMode()) {
                isLocked = true;
            }
            else {
                PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
                assert pm != null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) isLocked = !pm.isInteractive();
                else isLocked = !pm.isScreenOn();
            }
            if (isLocked) {
                start = new Date().getTime();
            } else {
                if (start>0) {
                    long end = new Date().getTime();
                    long diff = (end - start)/1000;
                    if (MainActivity.getInstance()!=null) MainActivity.getInstance().update(start, end, diff);
                    start = 0;
                }
            }

            Log.d(strLogId, String.format("%s", isLocked?"locked":"unlocked"));
        }

    }





}
