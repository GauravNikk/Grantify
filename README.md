# Grantify

## Description

Grantify is an Android library designed to simplify the process of requesting and managing runtime permissions across various Android versions. It provides an easy-to-use API for handling permissions, ensuring that your app complies with the latest Android permission requirements and improving the user experience.

## Features

- **Support for Multiple Android Versions**: Handles permissions based on the Android version.
- **Simplified Permission Requests**: Easily request multiple permissions at once.
- **Callbacks for Permission Results**: Handle permissions granted or denied with a callback interface.
- **Easy Integration**: Simple setup and usage in your Android projects.

## Repository Link

You can find the source code and additional information on the [Grantify GitHub repository](https://github.com/yourusername/grantify).

## How to Use

### Step 1: Add Dependency

Include the following dependency in your `build.gradle` file:

```gradle
implementation 'com.example:grantify:1.0.0'
```

# Step 2: Initialize and Request Permissions

**Use the Grantify library to request permissions in your activity:**

```MainActivity.java
package com.example.grantifyexample;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grantify.Grantify;
import com.example.grantify.GrantifyManager;

public class MainActivity extends AppCompatActivity {

final String[] LIST_PERMISSIONS = new String[]{
                "android.permission.INTERNET",
                "android.permission.READ_MEDIA_IMAGES",
                "android.permission.READ_MEDIA_VIDEO",
                "android.permission.READ_PHONE_STATE",
                "android.permission.WRITE_MEDIA_EXTERNAL_STORAGE",
                "android.permission.ACCESS_WIFI_STATE",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.CALL_PHONE",
                "android.permission.POST_NOTIFICATIONS",
                "android.permission.SYSTEM_ALERT_WINDOW",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.FOREGROUND_SERVICE",
                "android.permission.FOREGROUND_SERVICE_LOCATION",
                "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE",
                "android.permission.CAMERA",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.ACCESS_MEDIA_LOCATION",
                "android.permission.RECEIVE_BOOT_COMPLETED",
                "com.google.android.providers.gsf.permission.READ_GSERVICES",
                "android.permission.FOREGROUND_SERVICE_DATA_SYNC"
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request permissions using Grantify
        GrantifyManager.getInstance().requestPermissions(this, LIST_PERMISSIONS, new GrantifyManager.PermissionResultCallback() {
            @Override
            public void onPermissionsGranted() {
                // Permissions granted, proceed with your method
                Toast.makeText(MainActivity.this, "Permissions granted!", Toast.LENGTH_SHORT).show();
                // Your method or functionality that requires permissions
                performAction();
            }

            @Override
            public void onPermissionsDenied() {
                // Permissions denied, handle accordingly
                Toast.makeText(MainActivity.this, "Permissions are required to proceed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performAction() {
        // Your method or functionality that requires permissions
    }
}
```


## Explanation

-   **Package Declaration**: Ensure the package name matches your app's package structure.
-   **Imports**: Import necessary classes, including those from `Grantify`.
-   **Activity Definition**: Extend `AppCompatActivity` and override the `onCreate` method.
-   **Request Permissions**: Use `GrantifyManager.getInstance().requestPermissions()` to request permissions. Provide the current activity, the list of permissions from `Grantify.getAllPermissions()`, and a callback to handle the result.
-   **Handle Permissions**: In the callback, handle cases where permissions are granted or denied.
-   **Perform Action**: Define a method (`performAction()`) that will be executed if permissions are granted.

### Note

-   Make sure to replace `com.example.grantify` with the actual package name of the `Grantify` library in your project.
-   Ensure `activity_main.xml` exists in the `res/layout` directory and contains the layout you want for the `MainActivity`.
-   Feel free to adjust the `performAction()` method and other parts of the code according to your specific use case and requirements.
