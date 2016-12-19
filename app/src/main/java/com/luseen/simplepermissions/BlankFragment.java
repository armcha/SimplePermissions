package com.luseen.simplepermissions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.simplepermission.permissions.MultiplePermissionCallback;
import com.luseen.simplepermission.permissions.Permission;
import com.luseen.simplepermission.permissions.PermissionFragment;

import java.util.List;

public class BlankFragment extends PermissionFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        requestPermission(Permission.CAMERA, new SinglePermissionCallback() {
//            @Override
//            public void onPermissionResult(Permission permission, boolean permissionGranted, boolean isPermissionForeverDenied) {
//                Log.e("permissionGranted ", "" + permissionGranted);
//                Log.e("isPermissionForever ", "" + isPermissionForeverDenied);
//            }
//        });
        requestPermissions(new Permission[]{Permission.CALL_PHONE, Permission.CAMERA,Permission.GET_ACCOUNTS,Permission.FINE_LOCATION},
                new MultiplePermissionCallback() {
            @Override
            public void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions) {
                for (Permission grantedPermission : grantedPermissions) {
                    Log.e("onPermissionGranted ", "" + grantedPermission);
                }
            }

            @Override
            public void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions) {
                for (Permission deniedPermission : deniedPermissions) {
                    Log.e("onPermissionDenied ", "" + deniedPermission);
                }

                for (Permission deniedPermission : foreverDeniedPermissions) {
                    Log.e("foreverDenied ", "" + deniedPermission);
                }
            }
        });
    }
}
