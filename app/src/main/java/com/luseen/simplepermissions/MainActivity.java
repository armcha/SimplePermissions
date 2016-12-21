package com.luseen.simplepermissions;

import android.os.Bundle;
import android.util.Log;

import com.luseen.simplepermission.permissions.Permission;
import com.luseen.simplepermission.permissions.PermissionActivity;
import com.luseen.simplepermission.permissions.SinglePermissionCallback;

public class MainActivity extends PermissionActivity {

    public static final String TAG = "SimplePermission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission(Permission.READ_SMS, new SinglePermissionCallback() {
            @Override
            public void onPermissionResult(boolean permissionGranted, boolean isPermissionDeniedForever) {
                Log.d(TAG, "Permission granted = " + permissionGranted);
                Log.d(TAG, "Permission denied forever = " + isPermissionDeniedForever);
            }
        });
    }
}
