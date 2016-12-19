package com.luseen.simplepermission.permissions;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chatikyan on 24.11.2016.
 */

public abstract class PermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 10;

    private List<String> permissionsToRequest = new ArrayList<>();
    private List<Permission> grantedPermissions = new ArrayList<>();
    private List<Permission> deniedPermissions = new ArrayList<>();
    private List<Permission> foreverDeniedPermissions = new ArrayList<>();
    private MultiplePermissionCallback multiplePermissionCallback;
    private SinglePermissionCallback singlePermissionCallback;
    private boolean isMultiplePermissionRequested = false;

    public void requestPermissions(Permission[] permissions, MultiplePermissionCallback multiplePermissionCallback) {
        if (PermissionUtils.isMarshmallowOrHigher()) {
            isMultiplePermissionRequested = true;
            this.multiplePermissionCallback = multiplePermissionCallback;

            for (Permission permission : permissions) {
                if (!PermissionUtils.isGranted(this, permission)) {
                    permissionsToRequest.add(permission.toString());
                }
            }

            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    public void requestPermission(Permission permission, SinglePermissionCallback singlePermissionCallback) {
        if (PermissionUtils.isMarshmallowOrHigher()) {
            isMultiplePermissionRequested = false;
            this.singlePermissionCallback = singlePermissionCallback;
            permissionsToRequest.add(permission.toString());
            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        PERMISSION_REQUEST_CODE);
            }
        } else {
            // TODO: 02.12.2016 maybe in future we would need this
//            if (isMultiplePermissionRequested) {
//                multiplePermissionCallback.onPermissionGranted(true, new ArrayList<>());
//            } else {
//                singlePermissionCallback.onPermissionResult(permission, true, false);
//            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        onPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void onPermissionsResult(int requestCode, String[] permissions,
                                     int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            grantedPermissions.clear();
            deniedPermissions.clear();
            foreverDeniedPermissions.clear();

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    if (permissionsToRequest.contains(permissions[i])) {
                        grantedPermissions.add(Permission.stringToPermission(permissions[i]));
                    }
                } else {
                    boolean permissionsDeniedForever =
                            ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (permissionsToRequest.contains(permissions[i])) {
                        if (!permissionsDeniedForever) {
                            foreverDeniedPermissions.add(Permission.stringToPermission(permissions[i]));
                        }
                        deniedPermissions.add(Permission.stringToPermission(permissions[i]));
                    }
                }
            }

            boolean allPermissionsGranted = deniedPermissions.isEmpty();
            if (isMultiplePermissionRequested) {
                multiplePermissionCallback.onPermissionGranted(allPermissionsGranted, grantedPermissions);
                multiplePermissionCallback.onPermissionDenied(deniedPermissions, foreverDeniedPermissions);
            } else {
                boolean permissionsDeniedForever = ActivityCompat.shouldShowRequestPermissionRationale(
                        this, permissionsToRequest.get(0));
                if (allPermissionsGranted)
                    permissionsDeniedForever = true;
                Permission permission = Permission.stringToPermission(permissionsToRequest.get(0));
                singlePermissionCallback.onPermissionResult(permission, allPermissionsGranted, !permissionsDeniedForever);
            }
            permissionsToRequest.clear();
        }
    }
}