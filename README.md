# SimplePermissions

Simple android marshmallow permissions


## Download
```groovy
compile 'com.github.armcha:SimplePermissions:1.0.0'
```

## How to use
###Step 1. Extend your activity/fragment from PermissionActivity/PermissionFragment
```java
public class MainActivity extends PermissionActivity
```
or
```java
public class Fragment extends PermissionFragment
```

###Step 2. 
```java
requestPermission(Permission.CAMERA, new SinglePermissionCallback() {
            @Override
            public void onPermissionResult(boolean permissionGranted, boolean isPermissionDeniedForever) {

            }
        });
```

### Request multiple permissions
```java
Permission[] permissions = {Permission.CALL_PHONE, Permission.SEND_SMS, Permission.FINE_LOCATION};
requestPermissions(permissions, new MultiplePermissionCallback() {
            @Override
            public void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions) {
                
            }

            @Override
            public void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions) {

            }
        });
```
