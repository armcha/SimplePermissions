package com.luseen.simplepermission.permissions;

/**
 * Created by Chatikyan on 24.11.2016.
 */

public interface SinglePermissionCallback {

    void onPermissionResult(Permission permission, boolean permissionGranted, boolean isPermissionForeverDenied);
}
