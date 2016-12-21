package com.luseen.simplepermissions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.simplepermission.permissions.MultiplePermissionCallback;
import com.luseen.simplepermission.permissions.Permission;
import com.luseen.simplepermission.permissions.PermissionFragment;

import java.util.List;

public class BlankFragment extends PermissionFragment {

    public static final String TAG = BlankFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Permission[] permissions = new Permission[]{
                Permission.CALL_PHONE,
                Permission.CAMERA,
                Permission.GET_ACCOUNTS,
                Permission.FINE_LOCATION};

        requestPermissions(permissions, new MultiplePermissionCallback() {
            @Override
            public void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions) {
                Log.d(TAG, "All permissions is granted  = " + allPermissionsGranted);
                for (Permission grantedPermission : grantedPermissions) {
                    Log.d(TAG, "Granted permissions " + grantedPermission);
                }
            }

            @Override
            public void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions) {
                for (Permission deniedPermission : deniedPermissions) {
                    Log.d(TAG, "Denied permissions " + deniedPermission);
                }

                for (Permission deniedPermission : foreverDeniedPermissions) {
                    Log.d(TAG, "Forever denied permissions" + deniedPermission);
                }
            }
        });
    }
}
