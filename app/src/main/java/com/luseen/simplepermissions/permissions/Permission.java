package com.luseen.simplepermissions.permissions;

import android.Manifest;

/**
 * Created by Chatikyan on 24.11.2016.
 */

public enum Permission {

    CAMERA(Manifest.permission.CAMERA),
    SEND_SMS(Manifest.permission.SEND_SMS),
    READ_SMS(Manifest.permission.READ_SMS),
    CALL_PHONE(Manifest.permission.CALL_PHONE),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),
    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS),
    BODY_SENSORS(Manifest.permission.BODY_SENSORS),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO),
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS),
    READ_CALENDAR(Manifest.permission.READ_CALENDAR),
    READ_CONTACTS(Manifest.permission.READ_CONTACTS),
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG),
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS),
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG),
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),
    FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
    COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public static Permission stringToPermission(String stringPermission) {
        for (Permission permission : Permission.values()) {
            if (stringPermission.equalsIgnoreCase(permission.toString()))
                return permission;
        }

        return null;
    }

    @Override
    public String toString() {
        return permission;
    }
}
