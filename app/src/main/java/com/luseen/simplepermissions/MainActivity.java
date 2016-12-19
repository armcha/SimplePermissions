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

        requestPermission(Permission.CAMERA, new SinglePermissionCallback() {
            @Override
            public void onPermissionResult(Permission permission, boolean permissionGranted, boolean isPermissionForeverDenied) {
                Log.d(TAG, "permission: " + permission);
                Log.d(TAG, "permissionGranted: " + permissionGranted);
                Log.d(TAG, "isPermissionForeverDenied: " + isPermissionForeverDenied);
            }
        });
    }
}
