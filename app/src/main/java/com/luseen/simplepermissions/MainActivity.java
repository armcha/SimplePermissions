package com.luseen.simplepermissions;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.luseen.simplepermission.permissions.Permission;
import com.luseen.simplepermission.permissions.PermissionActivity;
import com.luseen.simplepermission.permissions.PermissionUtils;
import com.luseen.simplepermission.permissions.SinglePermissionCallback;

public class MainActivity extends PermissionActivity {

    public static final String TAG = "SimplePermission";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionUtils.isMarshmallowOrHigher()) {
                    requestPermission(Permission.READ_SMS, new SinglePermissionCallback() {
                        @Override
                        public void onPermissionResult(boolean permissionGranted, boolean isPermissionDeniedForever) {
                            textView.setText("Permission granted = " + permissionGranted +
                                    "\nPermission denied forever = " + isPermissionDeniedForever);

                        }
                    });
                } else {
                    textView.setText("PermissionUtils.isMarshmallowOrHigher() = false");
                }
            }
        });
    }
}
